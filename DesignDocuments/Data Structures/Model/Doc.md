# Citizen Model & Texture Definition File Format

> [!infobox] File Specification
> **Base Directory:** `assets/<namespace>/citizens/`
> **Format:** JSON / Procedural Specification
> **Minecraft Version:** 1.20.1
> **Target System:** Dynamic Mesh & Layered Texture Engine

A **Citizen Definition** file is a JSON file that defines the base geometry model, procedural bone attachments, and dynamic texture layering for a citizen or custom entity.

Citizen files are placed within the `assets/<namespace>/citizens/` directory (e.g., `assets/yourmod/citizens/avali_default.json`).

---

## Data Types & Conventions Legend

> [!tip] Identifier Conventions
> Identifiers (`<resourceLocation>`) follow [Minecraft's standard Resource Location convention](https://minecraft.wiki/w/Identifier).
> 
> | Asset Type | Implicit Base Path | Expected Extension | Raw Identifier Example | Resolved Disk Path |
> | :--- | :--- | :---: | :--- | :--- |
> | **Geo Model** | `geo/citizen/` | `.geo.json` | `"yourmod:wolf"` | `assets/yourmod/geo/citizen/wolf.geo.json` |
> | **Attachment** | `geo/attachment/` | `.geo.json` | `"yourmod:floppy_ear"` | `assets/yourmod/geo/attachment/floppy_ear.geo.json` |
> | **Texture** | `textures/citizen/` | `.png` | `"yourmod:wolf/base"` | `assets/yourmod/textures/citizen/wolf/base.png` |

> [!info]- Data Types Legend
> * **`<resourceLocation>`**: Minecraft Identifier string using short-path conventions (`"namespace:relative_path"`).
> * **`<colorHex>`**: Hexadecimal color string (`"#RRGGBB"` or `"#AARRGGBB"`).
> * **`Float`**: Floating-point number (e.g., `0.85`).

---

## JSON Structure

A citizen definition file consists of a single root JSON object containing the following keys:

| Key           |         Type         |  Default   | Description                                                                  |
| :------------ | :------------------: | :--------: | :--------------------------------------------------------------------------- |
| `model`       | `<resourceLocation>` | *Required* | Identifier of the base geometry model (resolves relative to `geo/citizen/`). |
| `attachments` |   List of Objects    |    `[]`    | Optional list of bone attachment groups used for procedural mesh generation. |
| `texture`     |        Object        | *Required* | Defines the base texture and procedural overlay pipeline.                    |

---

### `attachments`

Each object in the `attachments` array defines a set of potential mesh additions attached to target bones on the base model skeleton.

| Key | Type | Default | Bounds | Description |
| :--- | :---: | :---: | :---: | :--- |
| `bone` | String \| List of Strings | *Required* | — | Target bone name (or list of bone names) in the base model. |
| `chance` | Float | `1.0` | $P \in [0.0, 1.0]$ | Probability that this attachment group executes. |
| `meshes` | List of Objects | *Required* | — | List of potential mesh choices to attach. |

#### `attachments[].meshes`

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
| `overlays` | List of Overlay Groups | `[]` | Sequential list of mutually exclusive overlay groups. |

---

### Base Texture Layer (`texture.base`)

| Key       |         Type          |  Default   | Description                                                                   |
| :-------- | :-------------------: | :--------: | :---------------------------------------------------------------------------- |
| `texture` | `<resourceLocation>`  | *Required* | Base PNG texture identifier (resolves relative to `textures/citizen/`).       |
| `colors`  | List of Color Entries |    `[]`    | Optional list of single colors or color ranges used to tint the base texture. |

---

### Overlay Groups (`texture.overlays[]`)

Each overlay group defines layer-wide behavior (`chance`, `blend_mode`, `colors`) and a set of candidate textures (`textures`) where **exactly one** texture is picked if the group passes its chance roll.

| Key          |                                  Type                                   |  Default   |                   Bounds / Values                    | Description                                                                   |
| :----------- | :---------------------------------------------------------------------: | :--------: | :--------------------------------------------------: | :---------------------------------------------------------------------------- |
| `textures`   | `<resourceLocation>` \| List of `<resourceLocation>` \| List of Objects | *Required* |                          —                           | Single texture identifier, array of identifiers, or weighted texture objects. |
| `chance`     |                                  Float                                  |   `1.0`    |                  $P \in [0.0, 1.0]$                  | Probability of applying this overlay group.                                   |
| `blend_mode` |                                 String                                  | `"normal"` | `"normal"` \| `"multiply"` \| `"add"` \| `"overlay"` | Blending mode used during alpha compositing.                                  |
| `colors`     |                          List of Color Entries                          |    `[]`    |                          —                           | List of single colors or color ranges used to tint the selected texture.      |

#### `texture.overlays[].textures` (When using Weighted Objects)

| Key | Type | Default | Bounds | Description |
| :--- | :---: | :---: | :---: | :--- |
| `texture` | `<resourceLocation>` | *Required* | — | Identifier of the overlay PNG file. |
| `weight` | Float | `1.0` | $W \ge 0.0$ | Selection weight when picking a texture from this group. |

---

### Color Entry

An item in the `colors` array can be represented in one of two formats:

1. **Fixed Color:** A string containing a 6-digit or 8-digit Hex code (`"#RRGGBB"` or `"#AARRGGBB"`).
2. **Color Range Object:** An object defining a minimum and maximum color range to randomly interpolate between:

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
>         {
>           "mesh": "yourmod:pointed_ear",
>           "weight": 1.0
>         }
>       ]
>     }
>   ],
>   "texture": {
>     "base": {
>       "texture": "yourmod:avali/base",
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
>           {
>             "texture": "yourmod:avali/stripes_thin",
>             "weight": 1.0
>           }
>         ],
>         "colors": [
>           "#332211"
>         ]
>       },
>       {
>         "chance": 0.3,
>         "blend_mode": "add",
>         "textures": "yourmod:avali/glowing_warpaint",
>         "colors": [
>           "#00FFCC"
>         ]
>       }
>     ]
>   }
> }
> ```