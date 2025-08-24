/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

/**
 *
 * @author behda
 */
import java.util.Map;
import java.util.HashMap;


public class Weapon extends Items {
    public Map<String, Map<String, String>> weaponList = new HashMap<>();
    Attack attack = new Attack();
    
    public Weapon(String name, String type, String effect, String rarity, String lore) {
        super(name, type, effect, rarity, lore);
        this.getItems(); // TESTIGIIGN
    }
    
    public Weapon(){
        super();
        this.getItems();
    }
    
    @Override
    public void getItems(){
        this.itemsList = DataLoader.LoadEntitiesFromFile("resources/items_levels_story_start.txt", "===== Leveling System =====");
        for (String itemName: this.itemsList.keySet()){
            Map<String, String> itemDetails = this.itemsList.get(itemName);
            String itemType = itemDetails.getOrDefault("Type", "");
            
            if (itemType.equalsIgnoreCase("Weapon")){
                this.name = itemName;
                this.type = itemType;
                this.effect = itemDetails.getOrDefault("Effect", "");
                this.rarity = itemDetails.getOrDefault("Rarity", "");
                this.lore = itemDetails.getOrDefault("Lore", "");
                
                //Add to WeaponList
                weaponList.put(itemName, itemDetails);
                
                System.out.println("Weapon: " + name + " " + effect);
            }
        }
    }

    @Override
    public int use() {
        int damage = attack.getAttackDamage(weaponList.get(this.name), "player");
        System.out.println("You can attack the enemies using "+this.name+ " with " +damage + " damage");
        return damage;
    }
    
}
