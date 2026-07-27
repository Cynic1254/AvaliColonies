# core.commands

61 classes, 103 internal relationships shown.

```mermaid
classDiagram
direction BT

class ClickEventWithExecutable {
  + ClickEventWithExecutable(Runnable[]) 
   Action action
}
class ColonyIdArgument {
  - ColonyIdArgument() 
  + id() ColonyIdArgument
  + tryGetColony(CommandContext~CommandSourceStack~, String, boolean) IColony?
  + getColonyId(CommandContext~CommandSourceStack~, String) int
  + getColony(CommandContext~CommandSourceStack~, String) IColony
  - resolveByOwner(CommandSourceStack, UUID) int
}
class CommandAddOfficer {
  + CommandAddOfficer() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandArgumentNames {
  + CommandArgumentNames() 
}
class CommandBackup {
  + CommandBackup() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandCanRaiderSpawn {
  + CommandCanRaiderSpawn() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandChangeOwner {
  + CommandChangeOwner() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandCitizenInfo {
  + CommandCitizenInfo() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
  - styleWithTeleport(BlockPos) Style
   String name
}
class CommandCitizenKill {
  + CommandCitizenKill() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandCitizenList {
  + CommandCitizenList() 
  - drawPageSwitcher(CommandContext~CommandSourceStack~, int, int, int, int) void
  + onExecute(CommandContext~CommandSourceStack~) int
  - displayListFor(CommandContext~CommandSourceStack~, int) int
  - executeWithPage(CommandContext~CommandSourceStack~) int
  - getCitizensOnPage(List~ICitizenData~, int, int, int) List~ICitizenData~
  + build() LiteralArgumentBuilder~CommandSourceStack~
  - drawCitizens(CommandContext~CommandSourceStack~, List~ICitizenData~) void
   String name
}
class CommandCitizenModify {
  + CommandCitizenModify() 
  - execute(CommandContext~CommandSourceStack~, ToIntFunction~ICitizenData~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
  - adjust(CommandContext~CommandSourceStack~, Consumer~ICitizenData~, Function~ICitizenData, String~) int
   String name
}
class CommandCitizenReload {
  + CommandCitizenReload() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandCitizenSpawnNew {
  + CommandCitizenSpawnNew() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandCitizenTeleport {
  + CommandCitizenTeleport() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandCitizenTrack {
  + CommandCitizenTrack() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandCitizenTriggerWalkTo {
  + CommandCitizenTriggerWalkTo() 
  + onExecute(CommandContext~CommandSourceStack~) int
  - stop(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandClaimChunks {
  + CommandClaimChunks() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandColonyChunks {
  + CommandColonyChunks() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandColonyInfo {
  + CommandColonyInfo() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandColonyPrintStats {
  + CommandColonyPrintStats() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
  ~ literalAndRemember(String) MutableComponent
   String name
}
class CommandColonyRaidsInfo {
  + CommandColonyRaidsInfo() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandDeleteColony {
  + CommandDeleteColony() 
  - createClickEvent(CommandContext~CommandSourceStack~, boolean) ClickEvent
  - braceButtonComponent(Component) Component
  - executeGuidedConfirm(CommandContext~CommandSourceStack~) int
  + onExecute(CommandContext~CommandSourceStack~) int
  - executeGuidedBuildingAsk(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + checkPreCondition(CommandContext~CommandSourceStack~) boolean
   String name
}
class CommandEntityTrack {
  + CommandEntityTrack() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandExportColony {
  + CommandExportColony() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandGetRanks {
  + CommandGetRanks() 
  - executeWithPage(CommandContext~CommandSourceStack~) int
  + onExecute(CommandContext~CommandSourceStack~) int
  - executeCommand(CommandContext~CommandSourceStack~, int) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandHelp {
  + CommandHelp() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandHomeTeleport {
  + CommandHomeTeleport() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandKillAnimal {
  + CommandKillAnimal() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandKillChicken {
  + CommandKillChicken() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandKillCow {
  + CommandKillCow() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandKillMonster {
  + CommandKillMonster() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandKillPig {
  + CommandKillPig() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandKillRaider {
  + CommandKillRaider() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandKillSheep {
  + CommandKillSheep() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandListColonies {
  + CommandListColonies() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
  - executeCommand(CommandContext~CommandSourceStack~, int) int
  - executeWithPage(CommandContext~CommandSourceStack~) int
   String name
}
class CommandLoadAllBackups {
  + CommandLoadAllBackups() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandLoadBackup {
  + CommandLoadBackup() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandPruneWorld {
  + CommandPruneWorld() 
  + onExecute(CommandContext~CommandSourceStack~) int
  - executeWithPage(CommandContext~CommandSourceStack~) int
  - isFarEnoughFromColonies(int, int, int, List~IColony~) boolean
  + build() LiteralArgumentBuilder~CommandSourceStack~
  - tryPrune(CommandContext~CommandSourceStack~, int) int
   String name
}
class CommandRSReset {
  + CommandRSReset() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandRSResetAll {
  + CommandRSResetAll() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandRaid {
  + CommandRaid() 
  - startRaidTonight(CommandContext~CommandSourceStack~, RaidSettings) int
  + onExecute(CommandContext~CommandSourceStack~) int
  - raidExecute(CommandContext~CommandSourceStack~, RaidSettings) int
  - startRaidNow(CommandContext~CommandSourceStack~, RaidSettings) int
  - onExecuteWithLocation(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
  - onExecuteWithAmount(CommandContext~CommandSourceStack~) int
  - onExecuteWithType(CommandContext~CommandSourceStack~) int
  - getRaidType(CommandContext~CommandSourceStack~) String
   String name
}
class CommandReclaimChunks {
  + CommandReclaimChunks() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandResetPlayerSupplies {
  + CommandResetPlayerSupplies() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandSetAbandoned {
  + CommandSetAbandoned() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandSetDeletable {
  + CommandSetDeletable() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandSetRank {
  + CommandSetRank() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandShowClaim {
  + CommandShowClaim() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
  - buildClaimCommandResult(LevelChunk, BlockPos, ServerLevel) MutableComponent
   String name
}
class CommandTeleport {
  + CommandTeleport() 
  + onExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
   String name
}
class CommandTrackType {
  + CommandTrackType() 
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandTree {
  # CommandTree(String) 
  # addNode(CommandTree) CommandTree
  # addNode(LiteralArgumentBuilder~CommandSourceStack~) CommandTree
  # build() LiteralArgumentBuilder~CommandSourceStack~
}
class CommandUnloadForcedChunks {
  + CommandUnloadForcedChunks() 
  + checkPreCondition(CommandContext~CommandSourceStack~) boolean
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandWhereAmI {
  + CommandWhereAmI() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class CommandWhoAmI {
  + CommandWhoAmI() 
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class EntryPoint {
  - EntryPoint() 
  + register(CommandDispatcher~CommandSourceStack~) void
}
class IMCColonyOfficerCommand {
<<Interface>>
  + checkPreCondition(CommandContext~CommandSourceStack~) boolean
}
class IMCCommand {
<<Interface>>
  + checkPreCondition(CommandContext~CommandSourceStack~) boolean
  + checkPreConditionAndExecute(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
  + newLiteral(String) LiteralArgumentBuilder~CommandSourceStack~
  + newArgument(String, ArgumentType~T~) RequiredArgumentBuilder~CommandSourceStack, T~
  + checkPreConditionAndExecute(CommandContext~CommandSourceStack~, ExecutionHandler) int
  + isPlayerOped(Player) boolean
  + onExecute(CommandContext~CommandSourceStack~) int
   String name
}
class IMCOPCommand {
<<Interface>>
  + checkPreCondition(CommandContext~CommandSourceStack~) boolean
}
class ModArgumentTypes {
  + ModArgumentTypes() 
}
class MultiColonyIdArgument {
  - MultiColonyIdArgument() 
  + id() MultiColonyIdArgument
  + getColonyIds(CommandContext~CommandSourceStack~, String) List~Integer~
  + getColonies(CommandContext~CommandSourceStack~, String) List~IColony~
}
class MultipleOptionsArgument~TValue~ {
  # MultipleOptionsArgument(List~ArgumentOption~TValue~~) 
  # getValue(CommandContext~CommandSourceStack~, String) TValue
  + parse(StringReader) OptionContainer~TValue~
  + listSuggestions(CommandContext~S~, SuggestionsBuilder) CompletableFuture~Suggestions~
}
class ScanCommand {
  + ScanCommand() 
  - execute(CommandSourceStack, BlockPos, BlockPos, Optional~BlockPos~, GameProfile, String) int
  + format(Slot) String
  - onExecute(CommandContext~CommandSourceStack~) int
  + saveStructure(Level, Player, Slot, boolean) void
  - onExecuteWithAnchor(CommandContext~CommandSourceStack~) int
  - onExecuteWithPlayerName(CommandContext~CommandSourceStack~) int
  + build() LiteralArgumentBuilder~CommandSourceStack~
  - onExecuteWithPlayerNameAndFileName(CommandContext~CommandSourceStack~) int
  - onExecuteWithPlayerNameAndFileNameAndAnchorPos(CommandContext~CommandSourceStack~) int
}

ColonyIdArgument  -->  MultipleOptionsArgument~TValue~ 
CommandAddOfficer  ..>  IMCColonyOfficerCommand 
CommandBackup  ..>  IMCOPCommand 
CommandCanRaiderSpawn  ..>  IMCOPCommand 
CommandChangeOwner  ..>  IMCColonyOfficerCommand 
CommandCitizenInfo  ..>  IMCColonyOfficerCommand 
CommandCitizenKill  ..>  IMCColonyOfficerCommand 
CommandCitizenList  ..>  IMCColonyOfficerCommand 
CommandCitizenModify  ..>  IMCColonyOfficerCommand 
CommandCitizenReload  ..>  IMCColonyOfficerCommand 
CommandCitizenSpawnNew  ..>  IMCOPCommand 
CommandCitizenTeleport  ..>  IMCColonyOfficerCommand 
CommandCitizenTrack  ..>  IMCColonyOfficerCommand 
CommandCitizenTriggerWalkTo  ..>  IMCColonyOfficerCommand 
CommandClaimChunks  ..>  IMCOPCommand 
CommandColonyChunks  ..>  IMCColonyOfficerCommand 
CommandColonyInfo  ..>  IMCColonyOfficerCommand 
CommandColonyPrintStats  ..>  IMCOPCommand 
CommandColonyRaidsInfo  ..>  IMCOPCommand 
CommandDeleteColony  ..>  IMCColonyOfficerCommand 
CommandEntityTrack  ..>  IMCColonyOfficerCommand 
CommandExportColony  ..>  IMCOPCommand 
CommandGetRanks  ..>  IMCOPCommand 
CommandHelp  ..>  IMCCommand 
CommandHomeTeleport  ..>  IMCCommand 
CommandKillAnimal  ..>  IMCOPCommand 
CommandKillChicken  ..>  IMCOPCommand 
CommandKillCow  ..>  IMCOPCommand 
CommandKillMonster  ..>  IMCOPCommand 
CommandKillPig  ..>  IMCOPCommand 
CommandKillRaider  ..>  IMCOPCommand 
CommandKillSheep  ..>  IMCOPCommand 
CommandListColonies  ..>  IMCCommand 
CommandLoadAllBackups  ..>  IMCOPCommand 
CommandLoadBackup  ..>  IMCOPCommand 
CommandPruneWorld  ..>  IMCOPCommand 
CommandRSReset  ..>  IMCCommand 
CommandRSResetAll  ..>  IMCOPCommand 
CommandRaid  ..>  IMCOPCommand 
CommandReclaimChunks  ..>  IMCOPCommand 
CommandResetPlayerSupplies  ..>  IMCOPCommand 
CommandSetAbandoned  ..>  IMCColonyOfficerCommand 
CommandSetDeletable  ..>  IMCOPCommand 
CommandSetRank  ..>  IMCOPCommand 
CommandShowClaim  ..>  IMCOPCommand 
CommandTeleport  ..>  IMCColonyOfficerCommand 
CommandTrackType  ..>  IMCOPCommand 
CommandUnloadForcedChunks  ..>  IMCCommand 
CommandWhereAmI  ..>  IMCCommand 
CommandWhoAmI  ..>  IMCCommand 
EntryPoint  ..>  CommandAddOfficer : «create»
EntryPoint  ..>  CommandBackup : «create»
EntryPoint  ..>  CommandCanRaiderSpawn : «create»
EntryPoint  ..>  CommandChangeOwner : «create»
EntryPoint  ..>  CommandCitizenInfo : «create»
EntryPoint  ..>  CommandCitizenKill : «create»
EntryPoint  ..>  CommandCitizenList : «create»
EntryPoint  ..>  CommandCitizenModify : «create»
EntryPoint  ..>  CommandCitizenReload : «create»
EntryPoint  ..>  CommandCitizenSpawnNew : «create»
EntryPoint  ..>  CommandCitizenTeleport : «create»
EntryPoint  ..>  CommandCitizenTrack : «create»
EntryPoint  ..>  CommandCitizenTriggerWalkTo : «create»
EntryPoint  ..>  CommandClaimChunks : «create»
EntryPoint  ..>  CommandColonyChunks : «create»
EntryPoint  ..>  CommandColonyInfo : «create»
EntryPoint  ..>  CommandColonyPrintStats : «create»
EntryPoint  ..>  CommandColonyRaidsInfo : «create»
EntryPoint  ..>  CommandDeleteColony : «create»
EntryPoint  ..>  CommandEntityTrack : «create»
EntryPoint  ..>  CommandExportColony : «create»
EntryPoint  ..>  CommandGetRanks : «create»
EntryPoint  ..>  CommandHelp : «create»
EntryPoint  ..>  CommandHomeTeleport : «create»
EntryPoint  ..>  CommandKillAnimal : «create»
EntryPoint  ..>  CommandKillChicken : «create»
EntryPoint  ..>  CommandKillCow : «create»
EntryPoint  ..>  CommandKillMonster : «create»
EntryPoint  ..>  CommandKillPig : «create»
EntryPoint  ..>  CommandKillRaider : «create»
EntryPoint  ..>  CommandKillSheep : «create»
EntryPoint  ..>  CommandListColonies : «create»
EntryPoint  ..>  CommandLoadAllBackups : «create»
EntryPoint  ..>  CommandLoadBackup : «create»
EntryPoint  ..>  CommandPruneWorld : «create»
EntryPoint  ..>  CommandRSReset : «create»
EntryPoint  ..>  CommandRSResetAll : «create»
EntryPoint  ..>  CommandRaid : «create»
EntryPoint  ..>  CommandReclaimChunks : «create»
EntryPoint  ..>  CommandResetPlayerSupplies : «create»
EntryPoint  ..>  CommandSetAbandoned : «create»
EntryPoint  ..>  CommandSetDeletable : «create»
EntryPoint  ..>  CommandSetRank : «create»
EntryPoint  ..>  CommandShowClaim : «create»
EntryPoint  ..>  CommandTeleport : «create»
EntryPoint  ..>  CommandTrackType : «create»
EntryPoint  ..>  CommandTree : «create»
EntryPoint  ..>  CommandUnloadForcedChunks : «create»
EntryPoint  ..>  CommandWhereAmI : «create»
EntryPoint  ..>  CommandWhoAmI : «create»
IMCColonyOfficerCommand  -->  IMCCommand 
IMCOPCommand  -->  IMCCommand 
MultiColonyIdArgument  -->  MultipleOptionsArgument~TValue~ 
```
