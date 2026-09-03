# Citizen Model & Texture Definition File Format

> [!infobox] File Specification
> **Base Directory:** `data/<namespace>/citizens/`
> **Format:** JSON / Procedural Specification
> **Minecraft Version:** 1.20.1
> **Target System:** Dynamic Mesh & Layered Texture Engine

A **Citizen Definition** file is a JSON file that defines the base geometry model, bone definitions, dynamic texture layering, and dynamic bone attachments for a citizen or custom entity.

Citizen files are placed within the `data/<namespace>/citizens/` directory (e.g., `data/yourmod/citizens/avali.json`).

---

## Data Types & Conventions Legend

> [!tip] Resource Location Resolution Rules
> Identifiers (`<resourceLocation>`) are resolved at runtime via `ResourcePathResolver` according to specific path matching logic:
> 
> * **Textures (`textures.base`, `textures.overlays`):** If a path begins with `textures/` or ends with `.png`, it resolves directly to that absolute path in assets. Otherwise, it defaults relative to `textures/entity/citizen/base/<path>.png`.
> * **Job Clothing Folders (`clothing_override_path`):** If a path begins with `textures/`, it resolves directly. Otherwise, it defaults relative to `textures/entity/citizen/job/<path>/`.
> 
> | Asset Type | Explicit Override Condition | Default Path Resolution | Raw Identifier Example | Resolved Disk Path |
> | :--- | :--- | :--- | :--- | :--- |
> | **Geo Model** | None | `geo/citizen/` | `"yourmod:wolf"` | `assets/yourmod/geo/citizen/wolf.geo.json` |
> | **Base Texture** | `textures/` or `.png` | `textures/entity/citizen/base/` | `"yourmod:wolf/pelt"` | `assets/yourmod/textures/entity/citizen/base/wolf/pelt.png` |
> | **Direct Texture** | Begins with `textures/` or ends in `.png` | *Direct Path* | `"yourmod:textures/custom/skin.png"` | `assets/yourmod/textures/custom/skin.png` |
> | **Job Clothing** | Begins with `textures/` | `textures/entity/citizen/job/` | `"yourmod:avali"` | `assets/yourmod/textures/entity/citizen/job/avali/` |

> [!info]- Data Types Legend
> * **`<resourceLocation>`**: Minecraft Identifier string using short-path or absolute asset path conventions (`"namespace:relative_path"`).
> * **`<colorHex>`**: Hexadecimal color string (`"#RRGGBB"` or `"#AARRGGBB"`).
> * **`Float`**: Floating-point number (e.g., `0.85`).

---

## JSON Structure

A citizen definition file consists of a single root JSON object containing the following keys:

| Key | Type | Default | Description |
| :--- | :---: | :---: | :--- |
| `model` | `<resourceLocation>` | *Required* | Identifier of the base geometry model (resolves relative to `geo/citizen/`). |
| `bones` | Object | *Required* | Defines core skeleton bone mappings and armor slot bindings. |
| `textures` | Object | *Required* | Defines base textures, overlay groups, and texture path overrides. |
| `attachments` | List of Attachment Groups \| Group | `[]` | Optional single attachment group or list of attachment groups. |

---

### `bones`

Maps logical entity skeletal locations (hand bones, head bone, armor slots) to specific bone names in the Geo model.

| Key | Type | Default | Description |
| :--- | :---: | :---: | :--- |
| `head` | String | `"b_head"` | Bone name designated for head tracking/rendering. |
| `left_hand` | String | `"b_left_hand"` | Bone name where held items in the off-hand are attached. |
| `right_hand` | String | `"b_right_hand"` | Bone name where held items in the main hand are attached. |
| `armor` | Object | *Required* | Map defining bone names designated for armor slots. |

#### `bones.armor`

| Key     |           Type            | Default | Description                                    |
| :------ | :-----------------------: | :-----: | :--------------------------------------------- |
| `head`  | String \| List of Strings |  `[]`   | Bone name(s) shown when equipping head armor.  |
| `chest` | String \| List of Strings |  `[]`   | Bone name(s) shown when equipping chest armor. |
| `legs`  | String \| List of Strings |  `[]`   | Bone name(s) shown when equipping leg armor.   |
| `feet`  | String \| List of Strings |  `[]`   | Bone name(s) shown when equipping foot armor.  |

---

### `textures`

Configures procedural compositing of entity textures as well as path overrides for armor/job clothing assets.

| Key | Type | Default | Description |
| :--- | :---: | :---: | :--- |
| `base` | Base Layer Object | *Required* | Foundation texture layer specification. |
| `overlays` | Overlay Group \| List of Groups | `[]` | Single overlay group or list of dynamic overlay groups. |
| `armor_override_path` | `<resourceLocation>` | `null` | Custom base folder path for looking up armor textures. |
| `clothing_override_path` | `<resourceLocation>` | `null` | Custom folder path for job clothing textures (defaults relative to `textures/entity/citizen/job/<path>/` unless starting with `textures/`). |

---

### Base Texture Layer (`textures.base`)

| Key | Type | Default | Description |
| :--- | :---: | :---: | :--- |
| `textures` | Weighted Entry \| List of Weighted Entries | *Required* | Single texture entry or weighted pool of base textures. |
| `colors` | Color Entry \| List of Color Entries | `[]` | Optional color or color list used to tint the base texture. |

---

### Overlay Groups (`textures.overlays`)

Each overlay group defines layer-wide rules (`chance`, `blend_mode`, `colors`) and candidate weighted textures.

| Key | Type | Default | Bounds / Values | Description |
| :--- | :---: | :---: | :---: | :--- |
| `textures` | Weighted Entry \| List of Weighted Entries | *Required* | — | Candidate texture options for this overlay layer. |
| `chance` | Float | `1.0` | Probability between 0.0 and 1.0 | Probability that this overlay group is applied. |
| `blend_mode` | String | `"normal"` | `"normal"` \| `"multiply"` \| `"add"` \| `"overlay"` | Alpha compositing blend mode. |
| `colors` | Color Entry \| List of Color Entries | `[]` | — | Single color or color list used to tint the chosen texture. |

#### Weighted Texture Format (`textures` entries)

Textures can be defined as a direct `<resourceLocation>` string (defaults to weight `1.0`) or as a weighted object:

| Key | Type | Default | Bounds | Description |
| :--- | :---: | :---: | :---: | :--- |
| `texture` | `<resourceLocation>` | *Required* | — | Identifier of the texture asset (resolves relative to `textures/entity/citizen/base/<texture>.png` unless starting with `textures/` or ending with `.png`). |
| `weight` | Float | `1.0` | Weight >= 0.0 | Relative probability weight for weighted random selection. |

---

### `attachments`

Attachment groups define optional or procedural bone structures on the skeleton that are toggled dynamically.

| Key | Type | Default | Bounds / Values | Description |
| :--- | :---: | :---: | :---: | :--- |
| `name` | String | `"unnamed_group"` | — | Optional label for this group. |
| `chance` | Float | `1.0` | Probability between 0.0 and 1.0 | Probability that this attachment group evaluates. |
| `exclusive` | Boolean | `true` | `true` \| `false` | If `true`, rolls exactly one weighted option. If `false`, rolls all options. |
| `visibility_rules` | Visibility Rules Object | `null` | — | Conditional hiding/showing rules inherited by options in this group. |
| `options` | List of Attachment Options \| Option | *Required* | Min items: 1 | Attachment choice(s) contained in this group. |

#### `attachments.options` Elements

| Key | Type | Default | Bounds | Description |
| :--- | :---: | :---: | :---: | :--- |
| `name` | String | `"unnamed_option"` | — | Optional label for this option. |
| `bone` | String \| List of Strings | *Required* | — | Geo model bone name(s) associated with this attachment. |
| `weight` | Float | `1.0` | Weight >= 0.0 | Selection weight used when `exclusive` is `true` in the parent group. |
| `visibility_rules` | Visibility Rules Object | `null` | — | Visibility rules specific to this option (merged with parent group rules). |

---

### `visibility_rules`

Defines conditions under which target attachment bones are hidden or shown based on equipment or MineColonies job roles.

| Key | Type | Default | Allowed Values | Description |
| :--- | :---: | :---: | :---: | :--- |
| `hide_on_armor_slots` | List of Strings | `[]` | Array of `"head"`, `"chest"`, `"legs"`, `"feet"` | Hides bones when armor is equipped in these slots. |
| `hide_on_jobs` | List of Strings | `[]` | List of Job IDs (e.g. `"minecolonies:knight"`) | Hides bones when the citizen holds these jobs. |
| `show_on_jobs` | List of Strings | `[]` | List of Job IDs (e.g. `"minecolonies:guard"`) | Restricts bone visibility to specific jobs (hidden by default on others). |

---

### Color Entry

Colors can be specified as a direct hex string (`"#RRGGBB"` / `"#AARRGGBB"`) or a range object:

| Key | Type | Default | Description |
| :--- | :---: | :---: | :--- |
| `min` | `<colorHex>` | *Required* | Lower boundary color string (`"#RRGGBB"`). |
| `max` | `<colorHex>` | *Required* | Upper boundary color string (`"#RRGGBB"`). |

---

## Full Example

> [!example]- Expand Example JSON Specification
> **File location:** `data/yourmod/citizens/avali.json`
> ```json
> {
>   "model": "yourmod:avali_base",
>   "bones": {
>     "head": "b_head",
>     "left_hand": "b_left_hand",
>     "right_hand": "b_right_hand",
>     "armor": {
>       "head": ["b_head_armor"],
>       "chest": ["b_chest_armor"],
>       "legs": ["b_leg_left_armor", "b_leg_right_armor"],
>       "feet": ["b_foot_left_armor", "b_foot_right_armor"]
>     }
>   },
>   "textures": {
>     "clothing_override_path": "yourmod:avali",
>     "base": {
>       "textures": [
>         {
>           "texture": "yourmod:avali/base_default",
>           "weight": 3.0
>         },
>         "yourmod:textures/entity/citizen/custom/avali_special.png"
>       ],
>       "colors": [
>         "#FFFFFF",
>         {
>           "min": "#A0A0A0",
>           "max": "#E0E0E0"
>         }
>       ]
>     },
>     "overlays": [
>       {
>         "chance": 0.75,
>         "blend_mode": "multiply",
>         "textures": [
>           {
>             "texture": "yourmod:avali/stripes_thick",
>             "weight": 2.0
>           },
>           "yourmod:avali/stripes_thin"
>         ],
>         "colors": "#332211"
>       },
>       {
>         "chance": 0.3,
>         "blend_mode": "add",
>         "textures": "yourmod:avali/glowing_warpaint",
>         "colors": "#00FFCC"
>       }
>     ]
>   },
>   "attachments": [
>     {
>       "name": "ears",
>       "chance": 0.85,
>       "exclusive": true,
>       "visibility_rules": {
>         "hide_on_armor_slots": ["head"]
>       },
>       "options": [
>         {
>           "name": "floppy_ears",
>           "bone": ["b_ear_left_floppy", "b_ear_right_floppy"],
>           "weight": 3.0