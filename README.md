# CompassHunting
CompassHunting is a simple 1.15.2 Spigot plugin to track other players using a compass.

### How to Use
To get a compass initially, use the command `/givecompass [player]`. If the player holding the compass has the correct permissions, they can simply right click on another player to begin targetting them.

### Features
CompassHunting has several features to make it as useful as possible, including:

- Multi-world support
- Permission system to prevent abuse
- Distance information

### Commands
| Command | Description | Permissions |
|:---|:---|:---|
| `/givecompass [player]` | Give a tracking compass to a specific player (defaults to command sender) | `compasshunting.give`
| `/track {target} [hunter]` | Manually track players instead of right-clicking on them | `compasshunting.track.command`, `compasshunting.track.others`

### Permissions
| Permission | Description |
|:---|:---|
| `compasshunting.give` | Allow access to use the `/givecompass` command
| `compasshunting.track` | Allow the player to right-click on another player with a tracking compass to begin targetting them
| `compasshunting.track.command` | Allow access to use the `/track` command. This only allows access to set the target for yourself - to be able to manually set both the hunter and the target, the permission node `compasshunting.track.others` is required
| `compasshunting.track.others` | Allow access to use the `/track` command to set both the hunter and the target

#### Credits
[@InfinityMage](https://github.com/InfinityMage) and [@Error-204](https://github.com/Error-204)