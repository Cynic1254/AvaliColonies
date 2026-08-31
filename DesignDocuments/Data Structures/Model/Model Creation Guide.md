## How to create a model for citizens:
**Special Bone names**
These names are special in a model, they are specifically looked for and used for various behaviours, these names are case sensitive:
- head: the head bone, this is the bone which is used for rotating the citizen's head
- rightHand/leftHand: these represent the attachment points for items, rotate them based on where you want the left and right hand items to be placed
- armor bones: armor bones are custom bones used for armor rendering and they follow the following naming guide: armor\_\<slot>\_\<parent bone name> where `slot` is the name of the slot this armor piece belongs to, is either 'helmet', 'chestplate', 'leggings' or 'boots', 'parent bone name' is the bone name of the parent bone, this is present to prevent duplicate bone names but is still required, any child bones of this bone are considered part of the same armor
**Textures**
Textures are procedurally generated based on the citizen definition, the attachments and the model use the same texture, however the armor pieces will use the textures of the armor, which is either the default texture or a custom texture file specified in the citizen definition
**Attachments**
Attachments should be placed in their own model file, they are attached at the root to whatever bone specified in the citizen definition file
**Animations**
The following animations should be present for the model:
- misc.die: the death animation of the citizen
- misc.rest: sleeping animation of the citizen
- misc.sit: sitting animation of the citizen
- move.swim: swimming animation of the citizen
- move.walk: walking animation of the citizen
- move.run: running animation of the citizen, this animation triggers based on movement speed, not if the "running" flag is set
- misc.idle: idle animation of the citizen
- attack.swing: "working" animation of the citizen, most actions in minecolonies just trigger a swing so this is what we display, in the future we might expand this.