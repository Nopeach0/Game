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
public class Armor extends Items {
    public Map<String, Map<String, String>> armorList = new HashMap<>();
    
    public Armor(String name, String type, String effect, String rarity, String lore) {
        super(name, type, effect, rarity, lore);
        this.getItems();
    }
    
    public Armor(){
        super();
        this.getItems();
    }
    
    @Override
    public void getItems(){
        this.itemsList = DataLoader.LoadEntitiesFromFile("resources/items_levels_story_start.txt", "===== Leveling System =====");
        for (String itemName: this.itemsList.keySet()){
            Map<String, String> itemDetails = this.itemsList.get(itemName);
            String itemType = itemDetails.getOrDefault("Type", "");
            
            if (itemType.equalsIgnoreCase("Armor")){
                this.name = itemName;
                this.type = itemType;
                this.effect = itemDetails.getOrDefault("Effect", "");
                this.rarity = itemDetails.getOrDefault("Rarity", "");
                this.lore = itemDetails.getOrDefault("Lore", "");
                
                //Add to WeaponList
                armorList.put(itemName, itemDetails);
                
                System.out.println("Armor: " + name + " " + effect);
            }
        }
    }

    @Override
    public int use() {
        if (this.effect.toLowerCase().contains("reduce damage taken")){
            int percent = Items.extractPercentage(this.effect);
            System.out.println(this.name + " reduces damage by " + percent + "%.");
            return percent;
        } else if (this.effect.toLowerCase().contains("reflects")){
            int reflectPercent = Items.extractPercentage(this.effect);
            System.out.println(this.name + " reflects " + reflectPercent + "% damage.");
            return reflectPercent; // Reflect logic can be applied in enemy attack
        } else {
            System.out.println(this.name + " grants passive bonus: " + this.effect);
            return 0;
        }
    }
    
    
    
    
    
}
