# Citizen Model Creation Guide

This guide covers what you need to know to build a custom citizen model in **Blockbench**. It does _not_ cover the citizen definition file (the JSON that wires a model up to random textures, attachments, and armor) — that's a separate, more technical document meant for whoever integrates your model into the mod. Your job here is just to produce a model file (and optionally an animation file) that a developer can plug straight into a definition file. Knowing roughly how that later step works is still useful context, so it's mentioned briefly where relevant.

---

## 1. Model Format

You'll build and export your model in Blockbench as normal. The model format used by the mod is **GeckoLib's** own entity model format, which is closely based on — and largely compatible with — Minecraft Bedrock's entity model format. In practice, exporting a "Bedrock Entity" model from Blockbench works correctly today, but it's worth keeping in mind that the two formats aren't guaranteed to always be identical going forward, since GeckoLib's spec is the one that actually matters here.

---

## 2. Bones You Need to Include

Any model can technically be used, but for a citizen to work correctly with the mod's systems (head tracking, held items, armor, hats/attachments), it needs a handful of specific bones. **You can name these bones whatever you like** — just make a note of what you called them so whoever builds the citizen definition file can point at them correctly. If a name is left unspecified in that file, it falls back to a conventional default, shown below — using these default names yourself saves the developer a step.

|Purpose|What it's for|Conventional default name|
|---|---|---|
|Head bone|Lets the citizen's head turn to look around|`b_head`|
|Left hand bone|Anchor point for items held in the off-hand|`b_left_hand`|
|Right hand bone|Anchor point for items held in the main hand|`b_right_hand`|

A "hand" bone doesn't need to look like a hand — it just needs to sit where you want a held item to visually appear, since items are attached directly to it.

### Armor bones

Armor pieces (helmet, chestplate, leggings, boots) are modeled as extra bones directly in your main model — there's no separate armor model file. These bones are normally hidden, and the mod reveals whichever ones correspond to armor the citizen currently has equipped.

Technically, an armor bone just needs to be an **immediate child of some bone in the hierarchy** — it doesn't have to be a child of the specific body part it visually belongs to. That said, it's strongly recommended to parent each armor piece to the body part it sits on (e.g. a helmet bone as a child of the head, chestplate pieces as children of the arm/torso bones). If you don't, the armor piece won't automatically follow that body part's movement, and it will need to be manually keyframed to match in every single animation instead of just inheriting the motion for free.

You can have multiple bones per armor slot if a piece is made of several separate cubes (e.g. a chestplate spanning the torso and both arms as three bones).

### Attachment bones

Attachments (hats, glasses, horns, hairstyles, tool-belt props, etc.) work the same way as armor — they're bones in your main model that get hidden or shown — except instead of being tied to equipped armor, they're toggled based on rules set up in the citizen definition file (randomly per-citizen, or conditionally by job/armor). The same parenting advice applies: attaching an attachment bone to whichever bone it should move with (e.g. hair to the head) means it follows that bone's animation automatically, rather than needing to be animated separately by hand.

An attachment can be made of more than one bone if needed (e.g. a two-part hairstyle) — just keep that in mind when naming/grouping them, since the definition file will treat a group of bones as one togglable unit.

---

## 3. Textures & the Rendering Pipeline

You don't need to worry about _which_ texture files get picked, tinted, or combined at runtime, or how random variation is configured — that's all controlled by the citizen definition file, and is the developer's job. But you're the one who has to actually **paint** the texture files that feed into that system, so it's worth understanding the pipeline a citizen is drawn with. Every citizen is rendered in three passes:

**Pass 1 — Base skin + overlays (combined once per citizen into a single generated texture)**

The base skin is one full texture, chosen from whatever variants you paint (e.g. a few different skin tones or fur patterns). On top of that, any number of optional overlay textures (freckles, scars, warpaint, stripes, etc.) can be layered — each overlay is its own separate texture file you paint, sized and UV-mapped identically to the base. Overlays are combined onto the base using a blend mode, so it's worth painting with the intended blend mode in mind:

- **Normal** — drawn straight on top, respecting the overlay's own transparency.
- **Multiply** — darkens the base wherever the overlay is non-white (good for shading/grime).
- **Add** — brightens the base wherever the overlay is non-black (good for glowing effects).
- **Overlay** — a contrast-preserving mix of the two (darkens dark areas, lightens light areas).

Your base texture, every overlay texture, and your attachment bones (hats, hair, horns, etc.) all share **one texture sheet** and are UV-mapped onto that same canvas — make sure everything lines up on a single sheet sized to match your model, since they're composited pixel-for-pixel on top of each other.

Base and overlay texture files are expected under:

```
assets/<namespace>/textures/entity/citizen/base/<file>.png
```

**Pass 2 — Clothing (swapped in per job, drawn as a full second pass)**

Separately from the base/overlay skin above, a citizen's job can swap in a whole alternate texture for their clothes, drawn using the exact same UV layout as the base model. This means any clothing texture you paint needs to line up with the same UV map as your base skin — it's effectively a full alternate skin, not a small patch. If there's no matching texture for a given job, the system falls back to a generic `default.png`, so it's worth always painting one of those as a baseline. These live under:

```
assets/<namespace>/textures/entity/citizen/job/<model name>/<job_id>.png
assets/<namespace>/textures/entity/citizen/job/<model name>/default.png
```

**Pass 3 — Armor**

Armor is drawn last, on its own bones, using a **completely separate UV layout and texture** from the base/clothing skin — this follows the same layout conventions as vanilla Minecraft armor textures (a wide horizontal sheet covering head/body/arms/legs). If you don't need custom-looking armor for your citizen, you don't need to paint anything here at all — it'll just use whatever texture the equipped armor item normally has. If you do want custom armor art per material, those go under:

```
assets/<namespace>/textures/models/armor/<your folder>/<material>_layer_1.png   (helmet/chest/boots)
assets/<namespace>/textures/models/armor/<your folder>/<material>_layer_2.png   (leggings)
```

---

## 4. Animations

If you're also providing animations, here are the animation states the mod expects to be able to play:

|Animation|When it plays|
|---|---|
|`misc.idle`|Default state — plays whenever nothing else below applies.|
|`move.walk`|Walking at normal speed.|
|`move.run`|Moving quickly.|
|`move.swim`|Swimming/floating in water.|
|`misc.sit`|Sitting down or riding another entity.|
|`misc.rest`|Sleeping in a bed.|
|`misc.die`|Plays when the citizen dies.|
|`attack.swing`|A generic "working" swing, currently shared by every job action (mining, chopping, crafting, etc. don't have distinct animations yet).|

For `misc.rest` and `misc.sit`, Minecraft itself handles rotating/positioning the citizen to align with the bed or seat — your animation generally shouldn't add its own full rotation/repositioning on top of that, or you'll end up double-applying it (this has caused models to end up rotated the wrong way in past test files). Stick to fine details like limb bends if you want to refine the pose, rather than moving/rotating the whole body.

There's no "default" animation set that automatically applies to arbitrary models — every model needs its own matching animation file (see naming convention below).

---

## 5. File Placement & Naming

Assuming your mod/namespace is `mymod` and your model is called `farmer`:

```
assets/mymod/geo/citizen/farmer.geo.json               <- your Blockbench-exported model
assets/mymod/animations/citizen/farmer.animation.json  <- your animation file, if you're providing one
```

- The model file and its animation file must share the **same file name** (`farmer.geo.json` pairs with `farmer.animation.json`).
- Inside the `.geo.json` file, the model's `identifier` field should read `geometry.mymod.farmer` (i.e. `geometry.<namespace>.<filename>`).
- The citizen definition file that actually wires your model up to textures, attachments, and armor rules lives elsewhere (`data/mymod/citizens/farmer.json`) and is a separate, more technical file — refer to the citizen definition file format document for that. As the artist, you don't need to write this file, just hand off your model (and bone names) to whoever does.

---

## Quick Checklist

- [ ] Model has a head bone and left/right hand bones (named however you like — default names `b_head`, `b_left_hand`, `b_right_hand` save the developer a step if unspecified).
- [ ] Armor pieces exist as bones in the model, ideally parented to the body part they belong to.
- [ ] Attachment pieces (hats, hair, horns, etc.) exist as their own bones, ideally parented to whichever bone they should move with.
- [ ] Base skin, overlay, and attachment textures are all painted on one shared UV layout.
- [ ] A `default.png` clothing texture exists as a fallback if you're painting job clothing.
- [ ] Any custom armor art uses its own separate UV layout and the `_layer_1`/`_layer_2` naming.
- [ ] Model file and animation file (if provided) share the same file name.
- [ ] Bone names are documented/communicated to whoever builds the citizen definition file.