/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

/**
 *
 * @author behda
 */
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {
    public String name;
    public int health;
    //Create an inventory arrayList
    public List<Items> inventory = new ArrayList<>();
    public Weapon equippedWeapon;
    public Armor equippedArmor;
    public Consumable equippedConsumable;
    public static int MAX_HEALTH = 200;
    public static Player currentPlayer = null;
    
    //Automatically setting the health to max
    public Player(){
        this.equippedWeapon = null;
        this.equippedArmor = null;
        this.equippedConsumable = null;
        this.name = "Bob";
        this.health = MAX_HEALTH;
        Player.currentPlayer = this;
    }
   
    //The get methods for the name and health (only the get)
    
    public String getName(){
        return name;
    }
    
    public int getHealth(){
        return health;
    }
    
    //Use Scanner class to the get playerName and print the introduction text from complete_game_lore.txt
    public void getPlayer(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your Name: ");
            String s = input.nextLine();
            this.name = s;
            //Print the intro text from complete_game_lore.txt to the user
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./resources/complete_game_lore.txt"), StandardCharsets.UTF_8));
            String line = "";
            System.out.println("Hello, " + this.name + ". Welcome to ASHES OF AETHERON");
            while ((line = br.readLine()) != null && !"ENDING CHOICES".equalsIgnoreCase(line)){
                if (line.startsWith("---------") || line.startsWith("====================") || line.startsWith("ASHES OF AETHERON - COMPLETE LORE")) continue;
                System.out.println(line);
            }
            br.close();
            System.out.println("You will start with " + this.health + " health, select which area you want to explore ");
        } catch (IOException ex){
            System.out.println("Exception " + ex.getMessage());
        }
    }
    
    public int playerChoice(){
        Scanner input2 = new Scanner(System.in);
        System.out.println("0. Exit");
        System.out.println("1. **The Forest of Silence**, Where you awaken.");
        System.out.println("2. **Wat Thamai Ruins**, Fallen temples haunted by Yaksha guardians.");
        System.out.println("3. **Samsara Spiral**, A labyrinth of time, where death is never the end.");
        System.out.println("4. **Mount Suthanok**, Home to the last Flame of Stillness, where gods once walked.");
        int option = -1;
        try {
            option = input2.nextInt();
            switch (option){
                case 0:
                    System.out.println("Leaving the World...");
                    break;
                case 1:
                    System.out.println("You have choosen **The Forest of Silence**");
                    System.out.println("You are already here");
                    break;
                case 2:
                    System.out.println("You have choosen **Wat Thamai Ruins**");
                    System.out.println("Welcome child");
                    break;
                case 3:
                    System.out.println("You have choosen **Samsara Spiral**");
                    System.out.println("Welcome child");
                    break;
                case 4:
                    System.out.println("You have choosen **Mount Suthanok**");
                    System.out.println("Welcome child");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
                
        } catch (NumberFormatException e){
            System.out.println("You entered a non-number, try again ");
        }
    return option;
    }
    
    //Make the player take damage and change the player health
    public static void takeDamage(int damage){
        if (currentPlayer != null){
            int reducedDamage = damage;
            if (currentPlayer.equippedArmor != null){
                int reductionPercent = currentPlayer.equippedArmor.use();
                reducedDamage = damage - (damage * reductionPercent / 100);
            }
            currentPlayer.health -= reducedDamage;
            if (currentPlayer.health < 0){
                currentPlayer.health = 0;
            }
            System.out.println("you took "+ reducedDamage + " damage. CurrentHealth: " + currentPlayer.health);
        }
    }
    
    public static void increaseHealth(int amount){
        if (currentPlayer != null){
            currentPlayer.health += amount;
            if (currentPlayer.health > MAX_HEALTH){
                currentPlayer.health = MAX_HEALTH;
            }
            System.out.println("Your health is now " + currentPlayer.health);
        }
    }
    
    //Now that the player has taken damage tell the player what item he has
    //and given the info for the item type, ask them which one they want to equip
    
    //But before that we need to add the Item's first;
    
    public void addItem(Items item){
        inventory.add(item);
    }
    
    //Assume the player has all the weapons, Consumables and armor

    public void equipItems(){
        Weapon weaponItem = new Weapon();
        for (String name: weaponItem.weaponList.keySet()){
            Map<String, String> details = weaponItem.weaponList.get(name);
            this.equippedWeapon = new Weapon(
                    name, 
                    details.getOrDefault("Type", ""), 
                    details.getOrDefault("Effect", ""),
                    details.getOrDefault("Rarity", ""),
                    details.getOrDefault("Lore", "")
            );
            break;
        }
        Armor armorItem = new Armor();
        for (String name: armorItem.armorList.keySet()){
            Map<String, String> details = armorItem.armorList.get(name);
            this.equippedArmor = new Armor(
            name,
            details.getOrDefault("Type", ""),
            details.getOrDefault("Effect", ""),
            details.getOrDefault("Rarity", ""),
            details.getOrDefault("Lore", "")
            );
            break;
        }
        Consumable consumableItem = new Consumable();
        for (String name: consumableItem.potionList.keySet()){
            Map<String, String> details = consumableItem.potionList.get(name);
            this.equippedConsumable = new Consumable(
            name,
            details.getOrDefault("Type", ""),
            details.getOrDefault("Effect", ""),
            details.getOrDefault("Rarity", ""),
            details.getOrDefault("Lore", "")
            );
            break;
        }
    }
    
    
}
