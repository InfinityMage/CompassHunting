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

### Permissions
| Permission | Description |
|:---|:---|
| `compasshunting.give` | Allow access to use the `/givecompass` command
| `compasshunting.track` | Allow the player to right-click on another player with a tracking compass to begin targetting them

#### Credits
[@InfinityMage](https://github.com/InfinityMage) and [@Error-204](https://github.com/Error-204)