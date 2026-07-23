### Function
Citizens sleep at night in the Residence they're assigned to; building/upgrading Residences is also the colony's main lever for increasing total population. Like the Tavern and Quarry, **this building has no dedicated Java class** — registered as a plain `DefaultBuildingInstance` (`"residence"`, max level 5) in [`ModBuildingsInitializer`](../../../minecolonies/src/main/java/com/minecolonies/apiimp/initializer/ModBuildingsInitializer.java).

- Home module: `HOME` (`HomeBuildingModule`)
- Living module: `LIVING` (`LivingBuildingModule`)

### Levels
Max building level: 5. Confirmed: 1/2/3/4/5 housed citizens per level 1–5 (`LivingBuildingModule` sizing, the standard per-level-equals-capacity pattern shared with several other simple assignment modules in this codebase).
- Reaching **Residence level 3** is the trigger for *both* the Library's "Keen" and the School's "Higher Learning" unlock researches (see those docs) — a Residence-level gate, not a Farmer/Blacksmith-style production-building gate, which is easy to miss if you're not expecting citizen-housing progression to unlock civic buildings.
- Reaching **Residence level 4** and **level 5** are themselves prerequisites for the "Outpost" and "Hamlet" population-cap researches respectively (see Research below).

### Research
The base colony population cap (confirmed in [`CitizenConstants`](../../../minecolonies/src/main/java/com/minecolonies/api/util/constant/CitizenConstants.java): `CITIZEN_LIMIT_DEFAULT = 25`, with named tiers `CITIZEN_LIMIT_OUTPOST = 50`, `_HAMLET = 100`, `_VILLAGE = 150`, and an absolute ceiling `CITIZEN_LIMIT_MAX = 500` "including config file") is raised via a four-tier research chain, all adding to a shared `citizencapaddition` effect:
1. [`outpost.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/outpost.json) — **Residence level 4**, 64 Cooked Beef, chains off the Library's own "Keen" research.
2. [`hamlet.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/hamlet.json) — **Residence level 5**, 128 Cooked Beef, chains off Outpost.
3. [`village.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/village.json) — **Town Hall level 4** (note: switches to gating off Town Hall, not Residence, once housing itself is maxed out), 256 Cooked Beef, chains off Hamlet.
4. [`city.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/city.json) — **Town Hall level 5**, 512 Cooked Beef, chains off Village.

This confirms the "Outpost → Hamlet → Village → City" chain exactly, including the non-obvious detail that the *later* tiers pivot from gating off Residence level to gating off Town Hall level once Residences are already maxed at level 5.

### Skills
N/A — the Residence has no worker of its own.

### Limits
- **Housing limits are enforced in code** (`LivingBuildingModule`'s size cap) — not just a soft/UI-level suggestion.
- The tier names/order/building-level gates for the `citizencapaddition` research chain are confirmed above; the precise resulting population number at each tier could not be pinned down — like the Graveyard's `resurrectchanceaddition` research, the numeric magnitude for a given effect id/level isn't stored in the research JSON itself, and the generic effect-registration code (`ModResearchEffectInitializer`/`GlobalResearchEffect`) doesn't reveal where that mapping lives. Genuinely unresolved after checking the same set of files; the tier names/order are solid, the exact population numbers at each tier are inferred from `CitizenConstants`' named constants (Outpost=50, Hamlet=100, Village=150) rather than confirmed against the effect definitions directly.
