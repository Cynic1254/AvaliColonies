# Citizen Model & Texture Definition File Format

> [!infobox] File Specification
> **Base Directory:** `data/<namespace>/citizens/`
> **Format:** JSON / Procedural Specification
> **Minecraft Version:** 1.20.1
> **Target System:** Dynamic Mesh & Layered Texture Engine

A **Citizen Definition** file is a JSON file that defines the base geometry model, procedural bone attachments, and dynamic texture layering for a citizen or custom entity.

Citizen files are placed within the `data/<namespace>/citizens/` directory (e.g., `data/yourmod/citizens/avali_default.json`).

---

## Data Types & Conventions Legend

> [!tip] Identifier Conventions
> Identifiers (`<resourceLocation>`) follow [Minecraft's standard Resource Location convention](https://minecraft.wiki/w/Identifier).
> 
> | Asset Type | Implicit Base Path | Expected Extension | Raw Identifier Example | Resolved Disk Path |
> | :--- | :--- | :---: | :--- | :--- |
> | **Geo Model** | `geo/citizen/` | `.geo.json` | `"yourmod:wolf"` | `assets/yourmod/geo/citizen/wolf.geo.json` |
> | **Attachment** | `geo/attachment/` | `.geo.json` | `"yourmod:floppy_ear"` | `assets/yourmod/geo/attachment/floppy_ear.geo.json` |
> | **Texture** | `textures/entity/citizen/` | `.png` | `"yourmod:wolf/base"` | `assets/yourmod/textures/entity/citizen/wolf/base.png` |

> [!info]- Data Types Legend
> * **`<resourceLocation>`**: Minecraft Identifier string using short-path conventions (`"namespace:relative_path"`).
> * **`<colorHex>`**: Hexadecimal color string (`"#RRGGBB"` or `"#AARRGGBB"`).
> * **`Float`**: Floating-point number (e.g., `0.85`).

---

## JSON Structure

A citizen definition file consists of a single root JSON object containing the following keys:

| Key           |                    Type                     |  Default   | Description                                                                  |
| :------------ | :-----------------------------------------: | :--------: | :--------------------------------------------------------------------------- |
| `model`       |            `<resourceLocation>`             | *Required* | Identifier of the base geometry model (resolves relative to `geo/citizen/`). |
| `attachments` | Object \| List of Attachment Groups         |    `[]`    | Optional single attachment group or list of attachment groups.               |
| `texture`     |                   Object                    | *Required* | Defines the base texture and procedural overlay pipeline.                    |

---

### `attachments`

Defines mesh additions attached to target bones on the base model skeleton. Can be defined as a single object or a list of objects.

| Key      |                  Type                  |  Default   |       Bounds       | Description                                                             |
| :------- | :------------------------------------: | :--------: | :----------------: | :---------------------------------------------------------------------- |
| `bone`   |       String \| List of Strings        | *Required* |         —          | Target bone name (or list of bone names) in the base model.             |
| `chance` |                 Float                  |   `1.0`    | $P \in [0.0, 1.0]$ | Probability that this attachment group executes for a bone in the list. |
| `meshes` | `<resourceLocation>` \| Object \| List | *Required* |         —          | Single mesh identifier, single mesh object, or list of mesh choices.    |

#### `attachments.meshes` Elements / Objects

Meshes can be simplified to a single resource location string (implicitly weight `1.0`) or defined as an explicit object:

| Key | Type | Default | Bounds | Description |
| :--- | :---: | :---: | :---: | :--- |
| `mesh` | `<resourceLocation>` | *Required* | — | Identifier of the attachment mesh (resolves relative to `geo/attachment/`). |
| `weight` | Float | `1.0` | $W \ge 0.0$ | Selection weight when picking a random mesh from this list. |

---

### `texture`

The `texture` object configures how the entity's dynamic texture is composited at runtime using native memory buffers.

| Key | Type | Default | Description |
| :--- | :---: | :---: | :--- |
| `base` | Base Layer Object | *Required* | The foundation texture layer. |
| `overlays` | Overlay Group \| List of Overlay Groups | `[]` | Single overlay group or list of sequential overlay groups. |

---

### Base Texture Layer (`texture.base`)

| Key        |                       Type                       |  Default   | Description                                                                   |
| :--------- | :----------------------------------------------: | :--------: | :---------------------------------------------------------------------------- |
| `textures` | `<resourceLocation>` \| Object \| List           | *Required* | Single texture string, single weighted object, or list of textures.          |
| `colors`   | Color Entry \| List of Color Entries             |    `[]`    | Optional color entry or list of color entries used to tint the base layer.    |

---

### Overlay Groups (`texture.overlays`)

Each overlay group defines layer-wide behavior (`chance`, `blend_mode`, `colors`) and candidate textures (`textures`) where **exactly one** texture is picked if the group passes its chance roll. Can be provided as a single group object or a list of group objects.

| Key          |                             Type                              |  Default   |                   Bounds / Values                    | Description                                                                   |
| :----------- | :-----------------------------------------------------------: | :--------: | :--------------------------------------------------: | :---------------------------------------------------------------------------- |
| `textures`   |            `<resourceLocation>` \| Object \| List             | *Required* |                          —                           | Single texture identifier, single object, or array of strings/objects.        |
| `chance`     |                             Float                             |   `1.0`    |                  $P \in [0.0, 1.0]$                  | Probability of applying this overlay group.                                   |
| `blend_mode` |                            String                             | `"normal"` | `"normal"` \| `"multiply"` \| `"add"` \| `"overlay"` | Blending mode used during alpha compositing.                                  |
| `colors`     |              Color Entry \| List of Color Entries             |    `[]`    |                          —                           | Single color or list of colors used to tint the selected texture.             |

#### `textures` Weighted Format (When using Objects)

If provided as an object instead of a direct string, weights can be customized:

| Key       |         Type         |  Default   |   Bounds    | Description                                                                                                                                                                                              |
| :-------- | :------------------: | :--------: | :---------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `texture` | `<resourceLocation>` | *Required* |      —      | Identifier of the overlay PNG file (defaults relative to `textures/entity/citizen/`, if the ID starts with `textures/` or end with `.png` the path is resolved relative to the root `assets` directory). |
| `weight`  |        Float         |   `1.0`    | $W \ge 0.0$ | Selection weight when picking a texture from this group.                                                                                                                                                 |

---

### Color Entry

A color entry can be provided as a single item or inside a list:

1. **Fixed Color:** A string containing a 6-digit or 8-digit Hex code (`"#RRGGBB"` or `"#AARRGGBB"`).
2. **Color Range Object:** An object defining a minimum and maximum color range:

| Key | Type | Default | Description |
| :--- | :---: | :---: | :--- |
| `min` | `<colorHex>` | *Required* | Lower boundary color string (`"#RRGGBB"`). |
| `max` | `<colorHex>` | *Required* | Upper boundary color string (`"#RRGGBB"`). |

---

## Full Example

> [!example]- Expand Example JSON Specification
> **File location:** `assets/yourmod/citizens/avali.json`
> ```json
> {
>   "model": "yourmod:avali_base",
>   "attachments": [
>     {
>       "bone": ["left_ear", "right_ear"],
>       "chance": 0.85,
>       "meshes": [
>         {
>           "mesh": "yourmod:floppy_ear",
>           "weight": 3.0
>         },
>         "yourmod:pointed_ear"
>       ]
>     },
>     {
>       "bone": "tail",
>       "meshes": "yourmod:fluffy_tail"
>     }
>   ],
>   "texture": {
>     "base": {
>       "textures": "yourmod:avali/base",
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
>   }
> }
> ```