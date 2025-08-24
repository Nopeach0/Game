/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

/**
 *
 * @author behda
 */

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public abstract class Items {
    Map<String, Map<String, String>> itemsList = new HashMap<>();
    protected String name;
    protected String type;
    protected String effect;
    protected String rarity;
    protected String lore;
    
    
    //For constructor with no parameters and one with parameters
    
    public Items() {}
    
    public Items(String name, String type, String effect, String rarity, String lore){
        this.name = name;
        this.type = type;
        this.effect = effect;
        this.rarity = rarity;
        this.lore = lore;
    }
    
    //The get methods for all the Item details
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getEffect() {
        return effect;
    }

    public String getRarity() {
        return rarity;
    }

    public String getLore() {
        return lore;
    }
    
    public void getItems(){
        //Use the DataLoader class
        this.itemsList = DataLoader.LoadEntitiesFromFile("resources/items_levels_story_start.txt","===== Leveling System =====");
        for (String itemName: this.itemsList.keySet()){
            Map<String, String> itemDetails = this.itemsList.get(itemName);
            this.type = itemDetails.getOrDefault("Type", "");
            this.effect = itemDetails.getOrDefault("Effect", "");
            this.rarity = itemDetails.getOrDefault("Rarity", "");
            this.lore = itemDetails.getOrDefault("Lore", "");
        }
    }
    
    public static int extractPercentage(String text) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(\\d+)%");
        java.util.regex.Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }
    
    //Abstract method for use of items
    public abstract int use();
    
    
}
