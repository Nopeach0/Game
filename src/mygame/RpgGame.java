/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

/**
 *
 * @author behda
 */

import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RpgGame {
    public RpgGame(){};
    
    
    
    public void pickRandomNpc(GoodEntities goodEntity, Enemies enemyEntity ){
        List<String> keys1 = new ArrayList<>(goodEntity.entity.keySet());
        List<String> keys2 = new ArrayList<>(enemyEntity.entity.keySet());
        if (keys1.isEmpty()) return;
        if (keys2.isEmpty()) return;
        Random randomGen = new Random();
        goodEntity.randomGoodEntityName = keys1.get(randomGen.nextInt(keys1.size()));
        enemyEntity.randomEnemyEntityName = keys2.get(randomGen.nextInt(keys2.size()));
    }
    
    
    
    
    public static void main(String[] args) {
        //Main game loop
        RpgGame game = new RpgGame();
        Player player1 = new Player();
        //Get playerName and choices for each location
        player1.getPlayer();
        
        //Assume the player has all the weapons, Consumables and armor
        //Add all the items to the player given the text above
        player1.equipItems();
        player1.addItem(player1.equippedWeapon);
        player1.addItem(player1.equippedArmor);
        player1.addItem(player1.equippedConsumable);
        
        GoodEntities entity1 = new GoodEntities();
        Enemies entity2 = new Enemies();
        
        //Create a HashSet to store the playerChoice and see
        // if it is the first choice (if so intract with enemies entity else with GoodEntities)
        Set visitedLocations = new HashSet<Integer>();
        //main loop
        while (true){
            System.out.println("\nChoose a location (0 to quit): ");
            int choice = player1.playerChoice();
            
            //Exit the loop if playerChoice or choice is 0
            if (choice == 0){
                System.out.println("Exiting the game. Goodbye, " + player1.getName() + "!");
                break;
            }
            
            //Pick pickRandomNpc names
            
            game.pickRandomNpc(entity1, entity2);
            entity2.initialiseEnemyHealth();
            
            if (choice < 1 || choice > 4) continue;
            
            //If it is locations first time visit then only runIntraction with GoodEntities and Enemies but already visited we only runIntraction
            //with and Enemies entity
            if (!visitedLocations.contains(choice)){
                visitedLocations.add(choice);
                
                //Good Npc Intraction
                entity1.runIntraction();
                
                //Enemies Npc Intraction
                entity2.runIntraction();
                
                //entity2 damages player for the first time and then we get the player to attack
                entity2.damagePlayer();
                
                
                System.out.println("Here are your items in the inventory: ");
                for (Items inv: player1.inventory){
                    System.out.println("-" + inv.getName() + " [" + inv.getType() + "]");
                }
                Scanner newItem = new Scanner(System.in);
                System.out.println("Do you want to add new Items to your inventory (Type yes or no): ");
                if (newItem.nextLine().trim().equalsIgnoreCase("yes")){
                    System.out.println("Great enter the Item Type (Weapon, Armor, Consumable): ");
                    String itemType = newItem.nextLine();
                    itemType = itemType.trim().toLowerCase();
                    System.out.println("Great enter the Item name: ");
                    String itemName = newItem.nextLine().trim();
                    boolean exists = false;
                    for (Items it: player1.inventory){
                        if(it.getName().equalsIgnoreCase(itemName)){
                            exists = true;
                            break;
                        }
                    }
                    if (exists){
                        System.out.println("This item already exists");
                    } else {
                        Items item;
                        switch(itemType){
                            case "weapon":
                                item = new Weapon(itemName, "Weapon", "+10 damage", "Common", "User-added weapon");
                                break;
                            case "armor":
                                item = new Armor(itemName, "Armor", "Reduce damage by 5%", "Common", "User-added-Armor");
                                break;
                            case "consumable":
                                item = new Consumable(itemName, "Consumable", "Restores 25% health", "Common", "User-added consumable");
                                break;
                            default:
                                System.out.println("Unkown item Type");
                                return;
                        }
                        
                        //now that we got the item add it to inventory and write to the file
                        player1.inventory.add(item);
                        System.out.println(itemName + " is added to your inventory");
                        try (FileWriter fw = new FileWriter("resources/items_levels_story_start.txt", true)){
                            fw.write("Name: " + item.getName() + System.lineSeparator());
                            fw.write("Type: " + item.getType() + System.lineSeparator());
                            fw.write("Effect: " + item.getEffect() + System.lineSeparator());
                            fw.write("Rarity: " + item.getRarity() + System.lineSeparator());
                            fw.write("Lore: " + item.getLore() + System.lineSeparator());
                        } catch (IOException ex){
                            System.out.println("IO exception " + ex.getMessage());
                        }
                    }
                }
                
                //choose which one they want to use only weapon for now 
                Scanner inventoryWeapon = new Scanner(System.in);
                System.out.println("Which one do you want to use to deal with the enemy (Weapon, Consumable, Armor): ");
                String choiceInput = inventoryWeapon.nextLine().trim();
                if (choiceInput.equalsIgnoreCase("Weapon") && player1.equippedWeapon != null){
                    while (entity2.health > 0 && player1.health > 0 && !choiceInput.equalsIgnoreCase("no")) {
                        entity2.takeDamage(player1.equippedWeapon);
                        if (entity2.health <= 0) break;
                        // get enemy to fight back
                        // if enemy or player is dead brek out of loop
                        entity2.damagePlayer();
                        if (player1.health <= 0) break;
                        System.out.println("Do you want to use Consumable? (type yes or no): ");
                        String usePotion = inventoryWeapon.nextLine().trim();
                        if (usePotion.equalsIgnoreCase("yes") && player1.equippedConsumable != null){
                            player1.equippedConsumable.use();
                        }
                        System.out.println("Do you want to attack again (type yes or no): ");
                        do {
                        choiceInput = inventoryWeapon.nextLine().trim();
                        } while (!((choiceInput.equalsIgnoreCase("yes")) || choiceInput.equalsIgnoreCase("no")));
                        if (choiceInput.equalsIgnoreCase("no")) break;
                    }
                    //once player kills enemy is heals them;
                    player1.equippedConsumable.use();
                } else if (choiceInput.equalsIgnoreCase("Consumable") && player1.equippedConsumable != null){
                    player1.equippedConsumable.use(); //heals player
                } else if (choiceInput.equalsIgnoreCase("Armor") && player1.equippedArmor != null){
                    player1.equippedArmor.use(); //displayes passive effects
                }
            } else {
                System.out.println("You have Already explored this location! but Enemies still lurk... ");
                System.out.println("Do you want to add a new enemy: (Type yes or no): ");
                Scanner newEnemy = new Scanner(System.in);
                
                //add a new enemy
                if (newEnemy.nextLine().trim().equalsIgnoreCase("yes")){
                    System.out.println("Great enter the enemyName: ");
                    String enemyName = newEnemy.nextLine();
                    enemyName = enemyName.trim();
                    boolean exists = entity2.entity.containsKey(enemyName);
                    if (exists){
                        System.out.println("This item already exists");
                    }
                    
                    //Add new enemy to hashMap
                    Map<String, String> details = new HashMap<>();
                    details.put("Type", "Boss");
                    details.put("Health", "200");
                    details.put("Role", "Agressor");
                    details.put("Dialouge", "I will destroy you!");
                    entity2.entity.put(enemyName, details);
                    
                    
                    try (FileWriter fw = new FileWriter("resources/enemy_lore_data.txt", true)) {
                        fw.write("\nName: " + enemyName
                                + "\nType: " + "Boss"
                                + "\nHealth: 200"
                                + "\nRole: Aggressor"
                                + "\n Dialouge: I will destroy you!\n");
                        System.out.println(enemyName + " added to enemy list.");
                    } catch (IOException ex) {
                        System.out.println("IO exception " + ex.getMessage());
                    }
                }
                
                //runIntraction with Enemies entity
                entity2.runIntraction();
                //entity2 damages player for the first time and then we get the player to attack
                entity2.damagePlayer();
                
                Scanner inventoryWeapon = new Scanner(System.in);
                System.out.println("Which one do you want to use to deal with the enemy (Weapon, Consumable, Armor): ");
                String choiceInput = inventoryWeapon.nextLine().trim();
                if (choiceInput.equalsIgnoreCase("Weapon") && player1.equippedWeapon != null){
                    while (entity2.health > 0 && player1.health > 0 && !choiceInput.equalsIgnoreCase("no")) {
                        entity2.takeDamage(player1.equippedWeapon);
                        if (entity2.health <= 0) break;
                        // get enemy to fight back
                        // if enemy or player is dead brek out of loop
                        entity2.damagePlayer();
                        if (player1.health <= 0) break;
                        System.out.println("Do you want to use Consumable? (type yes or no): ");
                        String usePotion = inventoryWeapon.nextLine().trim();
                        if (usePotion.equalsIgnoreCase("yes") && player1.equippedConsumable != null){
                            player1.equippedConsumable.use();
                        }
                        System.out.println("Do you want to attack again (type yes or no): ");
                        do {
                        choiceInput = inventoryWeapon.nextLine().trim();
                        } while (!((choiceInput.equalsIgnoreCase("yes")) || choiceInput.equalsIgnoreCase("no")));
                        if (choiceInput.equalsIgnoreCase("no")) break;
                    }
                    //once player kills enemy is heals them;
                    player1.equippedConsumable.use();
                } else if (choiceInput.equalsIgnoreCase("Consumable") && player1.equippedConsumable != null){
                    player1.equippedConsumable.use(); //heals player
                } else if (choiceInput.equalsIgnoreCase("Armor") && player1.equippedArmor != null){
                    player1.equippedArmor.use(); //displayes passive effects
                }
                
                //Once the enemies is defeated for the first time get use the portion to add more health
                
                if (player1.equippedWeapon != null){
                    entity2.takeDamage(player1.equippedWeapon);
                } else {
                    System.out.println("You don't have a weapon equipped");
                }
                //Holy Shit(I made it work)
            }
        }
    }
}
