# MineColonies Class Diagram — Split Reference

Auto-split from the original single `mermaid.md` class diagram (2385 classes / 4950 relationships), which was too large for mermaid renderers to display in one piece.

Each file below is a self-contained Mermaid `classDiagram` covering one package/subsystem grouping. **Only relationships between two classes that are both present in the same file are included** — this keeps every diagram renderable on its own, but cross-package relationships are not shown (see the full original file if you need those). Class bodies (fields/methods) are preserved verbatim from the source diagram.

A handful (~20) of inner/nested classes that share a simple name across multiple outer classes (e.g. `Builder`, `View`, `Factory`, `CraftingModule`) could not be disambiguated by the source export and only the first occurrence is kept.

## Files

- **[api.md](./api.md)** — `api` — 2 classes, 2 relationships
- **[api_advancements.md](./api_advancements.md)** — `api.advancements` — 45 classes, 66 relationships
- **[api_blocks.md](./api_blocks.md)** — `api.blocks` — 23 classes, 31 relationships
- **[api_client.md](./api_client.md)** — `api.client` — 10 classes, 4 relationships
- **[api_colony.md](./api_colony.md)** — `api.colony` — 80 classes, 54 relationships
- **[api_colony_cont_2.md](./api_colony_cont_2.md)** — `api.colony (cont. 2)` — 47 classes, 10 relationships
- **[api_colony_cont_3.md](./api_colony_cont_3.md)** — `api.colony (cont. 3)` — 77 classes, 67 relationships
- **[api_colony_cont_4.md](./api_colony_cont_4.md)** — `api.colony (cont. 4)` — 20 classes, 7 relationships
- **[api_compatibility.md](./api_compatibility.md)** — `api.compatibility` — 16 classes, 18 relationships
- **[api_configuration.md](./api_configuration.md)** — `api.configuration` — 5 classes, 6 relationships
- **[api_crafting.md](./api_crafting.md)** — `api.crafting` — 23 classes, 15 relationships
- **[api_creativetab.md](./api_creativetab.md)** — `api.creativetab` — 1 classes, 0 relationships
- **[api_enchants.md](./api_enchants.md)** — `api.enchants` — 1 classes, 0 relationships
- **[api_entity.md](./api_entity.md)** — `api.entity` — 62 classes, 67 relationships
- **[api_entity_cont_2.md](./api_entity_cont_2.md)** — `api.entity (cont. 2)` — 54 classes, 50 relationships
- **[api_equipment.md](./api_equipment.md)** — `api.equipment` — 2 classes, 0 relationships
- **[api_eventbus.md](./api_eventbus.md)** — `api.eventbus` — 26 classes, 23 relationships
- **[api_inventory.md](./api_inventory.md)** — `api.inventory` — 12 classes, 1 relationships (1 class(es) had no body in source and were skipped)
- **[api_items.md](./api_items.md)** — `api.items` — 9 classes, 0 relationships
- **[api_loot.md](./api_loot.md)** — `api.loot` — 4 classes, 0 relationships
- **[api_network.md](./api_network.md)** — `api.network` — 2 classes, 0 relationships
- **[api_quests.md](./api_quests.md)** — `api.quests` — 18 classes, 7 relationships
- **[api_research.md](./api_research.md)** — `api.research` — 27 classes, 7 relationships (1 class(es) had no body in source and were skipped)
- **[api_sounds.md](./api_sounds.md)** — `api.sounds` — 7 classes, 0 relationships
- **[api_tileentities.md](./api_tileentities.md)** — `api.tileentities` — 11 classes, 0 relationships
- **[api_util.md](./api_util.md)** — `api.util` — 65 classes, 4 relationships
- **[apiimp.md](./apiimp.md)** — `apiimp` — 26 classes, 1 relationships
- **[core.md](./core.md)** — `core` — 2 classes, 0 relationships
- **[core_blocks.md](./core_blocks.md)** — `core.blocks` — 71 classes, 0 relationships
- **[core_client.md](./core_client.md)** — `core.client` — 72 classes, 47 relationships
- **[core_client_cont_2.md](./core_client_cont_2.md)** — `core.client (cont. 2)` — 43 classes, 38 relationships
- **[core_client_cont_3.md](./core_client_cont_3.md)** — `core.client (cont. 3)` — 43 classes, 0 relationships
- **[core_client_cont_4.md](./core_client_cont_4.md)** — `core.client (cont. 4)` — 43 classes, 0 relationships
- **[core_client_cont_5.md](./core_client_cont_5.md)** — `core.client (cont. 5)` — 57 classes, 20 relationships
- **[core_colony.md](./core_colony.md)** — `core.colony` — 61 classes, 62 relationships
- **[core_colony_cont_2.md](./core_colony_cont_2.md)** — `core.colony (cont. 2)` — 43 classes, 16 relationships
- **[core_colony_cont_3.md](./core_colony_cont_3.md)** — `core.colony (cont. 3)` — 75 classes, 73 relationships
- **[core_colony_cont_4.md](./core_colony_cont_4.md)** — `core.colony (cont. 4)` — 48 classes, 38 relationships
- **[core_colony_cont_5.md](./core_colony_cont_5.md)** — `core.colony (cont. 5)` — 76 classes, 56 relationships
- **[core_colony_cont_6.md](./core_colony_cont_6.md)** — `core.colony (cont. 6)` — 76 classes, 80 relationships
- **[core_colony_cont_7.md](./core_colony_cont_7.md)** — `core.colony (cont. 7)` — 14 classes, 11 relationships
- **[core_commands.md](./core_commands.md)** — `core.commands` — 61 classes, 103 relationships
- **[core_compatibility.md](./core_compatibility.md)** — `core.compatibility` — 24 classes, 29 relationships
- **[core_datalistener.md](./core_datalistener.md)** — `core.datalistener` — 10 classes, 1 relationships
- **[core_debug.md](./core_debug.md)** — `core.debug` — 7 classes, 4 relationships
- **[core_enchants.md](./core_enchants.md)** — `core.enchants` — 1 classes, 0 relationships
- **[core_entity.md](./core_entity.md)** — `core.entity` — 17 classes, 2 relationships
- **[core_entity_cont_2.md](./core_entity_cont_2.md)** — `core.entity (cont. 2)` — 75 classes, 83 relationships
- **[core_entity_cont_3.md](./core_entity_cont_3.md)** — `core.entity (cont. 3)` — 65 classes, 20 relationships
- **[core_entity_cont_4.md](./core_entity_cont_4.md)** — `core.entity (cont. 4)` — 41 classes, 74 relationships
- **[core_event.md](./core_event.md)** — `core.event` — 13 classes, 3 relationships
- **[core_generation.md](./core_generation.md)** — `core.generation` — 49 classes, 28 relationships
- **[core_items.md](./core_items.md)** — `core.items` — 51 classes, 36 relationships
- **[core_loot.md](./core_loot.md)** — `core.loot` — 1 classes, 0 relationships
- **[core_network.md](./core_network.md)** — `core.network` — 42 classes, 3 relationships
- **[core_network_cont_2.md](./core_network_cont_2.md)** — `core.network (cont. 2)` — 64 classes, 0 relationships
- **[core_network_cont_3.md](./core_network_cont_3.md)** — `core.network (cont. 3)` — 23 classes, 3 relationships
- **[core_placementhandlers.md](./core_placementhandlers.md)** — `core.placementhandlers` — 23 classes, 20 relationships
- **[core_quests.md](./core_quests.md)** — `core.quests` — 35 classes, 22 relationships
- **[core_recipes.md](./core_recipes.md)** — `core.recipes` — 4 classes, 0 relationships
- **[core_research.md](./core_research.md)** — `core.research` — 11 classes, 6 relationships
- **[core_structures.md](./core_structures.md)** — `core.structures` — 2 classes, 0 relationships
- **[core_tileentities.md](./core_tileentities.md)** — `core.tileentities` — 14 classes, 2 relationships
- **[core_util.md](./core_util.md)** — `core.util` — 22 classes, 0 relationships
- **[external_dependencies_part_1.md](./external_dependencies_part_1.md)** — `external.dependencies (part 1)` — 80 classes, 20 relationships
- **[external_dependencies_part_2.md](./external_dependencies_part_2.md)** — `external.dependencies (part 2)` — 80 classes, 12 relationships
- **[external_dependencies_part_3.md](./external_dependencies_part_3.md)** — `external.dependencies (part 3)` — 80 classes, 16 relationships
- **[external_dependencies_part_4.md](./external_dependencies_part_4.md)** — `external.dependencies (part 4)` — 4 classes, 0 relationships
