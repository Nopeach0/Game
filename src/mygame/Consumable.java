/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author behda
 */
public class Consumable extends Items {
    public Map<String, Map<String, String>> potionList = new HashMap<>();
    
    public Consumable(String name, String type, String effect, String rarity, String lore) {
        super(name, type, effect, rarity, lore);
        this.getItems();
    }
    
    public Consumable(){
        super();
        this.getItems();
    }
    
    @Override
    public void getItems(){
        this.itemsList = DataLoader.LoadEntitiesFromFile("resources/items_levels_story_start.txt", "===== Leveling System =====");
        for (String itemName: this.itemsList.keySet()){
            Map<String, String> itemDetails = this.itemsList.get(itemName);
            String itemType = itemDetails.getOrDefault("Type", "");
            
            if (itemType.equalsIgnoreCase("Potion") || itemType.equalsIgnoreCase("Consumable")){
                this.name = itemName;
                this.type = itemType;
                this.effect = itemDetails.getOrDefault("Effect", "");
                this.rarity = itemDetails.getOrDefault("Rarity", "");
                this.lore = itemDetails.getOrDefault("Lore", "");
                
                //Add to WeaponList
                potionList.put(itemName, itemDetails);
                
                System.out.println("Potion: " + name + " " + effect);
            }
        }
    }
    @Override
    public int use() {
        if (this.effect.toLowerCase().contains("fully restores health")) {
            Player.increaseHealth(Player.MAX_HEALTH); // Full restore
            System.out.println(this.name + " fully restored your health.");
            return Player.MAX_HEALTH;
        }else if (this.effect.toLowerCase().contains("Restores")){
            //then get the percentage to gainHealth
            int percent = Items.extractPercentage(this.effect);
            int amountToHeal = (int) (Player.MAX_HEALTH * (percent / 100.0));
            Player.increaseHealth(amountToHeal);
            System.out.println(this.name + " restored " + amountToHeal + " health.");
            return amountToHeal;
        } else if (this.effect.toLowerCase().contains("revives")) {
            // Revive logic to be implemented where death handling occurs
            System.out.println(this.name + " will revive the player on death.");
            return 0;
        } else {
            System.out.println(this.name + " used: " + this.effect);
            return 0;
        }
    }
    
}
