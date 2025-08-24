# Ashes of Aetheron (Java RPG)

A text-driven RPG game built in **Java 21**, featuring console-based gameplay and a **JavaFX GUI mode**.  
The game combines **lore-driven exploration**, NPC interactions, and a **combat system** with Weapons, Armor, and Consumables, all defined dynamically in external resource files.

---

## 🚀 Features
- **Two Modes**: 
  - Console loop (`RpgGame.main`)
  - GUI (JavaFX / FXGL wrapper via `MainFX`)
- **Player System**: Name input, health management, inventory with equippable items.
- **NPCs**:
  - Friendly NPCs loaded from `npc_locations_quests.txt`
  - Enemies with health, roles, and lore from `enemy_lore_data.txt`
- **Combat**:
  - Player attacks with Weapons
  - Enemies counterattack with variable damage
  - Armor reduces/reflects damage
  - Consumables restore health or give effects
- **Resource-Driven**: All lore, NPCs, items, and enemies are stored in text files → easy to expand.

---

## 🗂 Project Structure
# Ashes of Aetheron (Java RPG)

myGame/
├─ src/
│ ├─ mygame/ # Core game logic
│ │ ├─ RpgGame.java
│ │ ├─ Player.java
│ │ ├─ Npc.java, Enemies.java, GoodEntities.java
│ │ ├─ Items.java, Weapon.java, Armor.java, Consumable.java
│ │ ├─ Attack.java, DataLoader.java
│ └─ mygame/ui/ # GUI layer
│ └─ MainFX.java # JavaFX/FXGL launcher
├─ resources/ # Game data files
│ ├─ complete_game_lore.txt
│ ├─ npc_locations_quests.txt
│ ├─ enemy_lore_data.txt
│ └─ items_levels_story_start.txt
└─ dist/ # JAR build output

---

## 📦 Requirements
- **Java 21**
- **JavaFX SDK 21.0.8** (manual install)
- NetBeans 23 (or IntelliJ / command line)

---

## ▶️ Running the Game

### Console Mode
1. Open project in NetBeans
2. Run `RpgGame.main()`
3. Interact using text commands

### GUI Mode
1. Right-click `mygame.ui.MainFX → Run`
2. Or run manually with:
   ```bash
   java --module-path "C:\Program Files\javafx\javafx-sdk-21.0.8\lib" \
        --add-modules javafx.controls,javafx.fxml,javafx.graphics \
        -cp build\classes mygame.ui.MainFX

📝 Resource Files

Lore: complete_game_lore.txt

NPCs: npc_locations_quests.txt

Enemies: enemy_lore_data.txt

Items: items_levels_story_start.txt

You can extend the game by editing these files or adding new entries.

🔮 Future Work

FXGL grid-based map exploration with sprites

In-game editor to add new Items/Enemies dynamically

Save/load system

Improved balance for enemy vs player combat

👤 Author

Developed by Behdad Khorasani (Course Project / Personal RPG Experiment)