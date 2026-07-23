### Function
Where a Teacher levels up children (Pupils) — Paper can be consumed to speed up the process. Children sit on wool-carpet blocks found in the building's own schematic while studying.

- Building: [`BuildingSchool`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingSchool.java) — deliberately minimal; the actual teaching AI is [`EntityAIWorkTeacher`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/education/EntityAIWorkTeacher.java). Confirmed exact XP formula per lesson: `1.5 × (1 + teacherIntelligence/10) × (1 + TEACHING research effect) × (1 + teacherPrimarySkill(Knowledge)/10)`, applied to the pupil's Intelligence skill after the Teacher sits with them for a randomized 2–100 second session (session length shortened by the Teacher's own secondary skill, Mana). Paper is requested and physically handed to each pupil during the lesson, but isn't a distinct multiplier in the XP formula itself — its exact effect on outcomes wasn't identified beyond being a consumed lesson prop/requirement.
- Teacher module: `TEACHER_WORK` (`WorkerBuildingModule`, sized `(b) -> 1` — exactly one Teacher per School regardless of level)
- Pupil module: `PUPIL_WORK` (`ChildrenBuildingModule`, sized `2 × buildingLevel`)

### Levels
Max building level: 5. Confirmed pupil capacity formula `2 × buildingLevel`: 2/4/6/8/10 max pupils for levels 1–5. Teacher count is always exactly 1 regardless of level.

### Research
[`higherlearning.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/higherlearning.json) ("Higher Learning") requires a **Residence at level 3**, costs 3 Books, and its effect (`blockhutschool`) unlocks the School for placement — the same Residence-level-3 prerequisite as the Library's "Keen" research, so both typically become available together.

### Skills
- Teacher — Primary: Knowledge, Secondary: Mana (`TEACHER_WORK`)
- Pupil — Primary: Knowledge, Secondary: Mana (`PUPIL_WORK`) — identical to the Teacher's own skills. Note: this is easy to confuse with the *Library's* separate `student`/`STUDENT_WORK` job (Primary/Secondary Intelligence — see the Library doc), which is a differently-named job at a different building.

(`TEACHER_WORK`/`PUPIL_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Sitting spots are schematic-detected**: `registerBlockPosition()` records every `WoolCarpetBlock` position in the blueprint (`carpet`) — `getRandomPlaceToSit()` picks one at random, discarding it from the list if the carpet block turns out to have been removed since. A blueprint with more carpet tiles supports more simultaneously-seated pupils, independent of the `2 × level` cap on how many pupils can be *assigned* to the school at all.
- Only one Teacher is ever assigned regardless of building level — upgrading the School increases pupil capacity only, not teaching staff.
