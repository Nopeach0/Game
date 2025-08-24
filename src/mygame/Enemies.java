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

public class Enemies extends Npc{
//Or for better understanding the badEnemies
    public int health;
    Attack attack = new Attack();
    public String randomEnemyEntityName = "";//Generated from pickRandomNpc method in the main class (Rpg game)
    protected Map<String, Map<String, String>> entity = new HashMap<>();
    
    
    public Enemies(){
        super();
        this.filePath = "./resources/enemy_lore_data.txt";
        this.placeNpc();
        this.entity.putAll(this.npcList);
    }
    
    public void initialiseEnemyHealth(){
        if (randomEnemyEntityName != null && entity.containsKey(randomEnemyEntityName)){
            Map<String, String> details = entity.get(randomEnemyEntityName);
            String healthStr = details.getOrDefault("Health", "");
            try {
                this.health = Integer.parseInt(healthStr);
            } catch (NumberFormatException ex){
                System.out.println("Error parsing health for: " + randomEnemyEntityName);
                this.health = 0;
            }
        }
    }
    
    
    @Override
    public void runIntraction() {
        System.out.println("You have Encountered " + randomEnemyEntityName + "\n");
        for (Map.Entry<String, String> entry: this.entity.get(randomEnemyEntityName).entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");
    }
    
    public void damagePlayer() {
        //Run attack
        int damage = attack.getAttackDamage(this.entity.get(randomEnemyEntityName), "enemy");
        System.out.println(randomEnemyEntityName + " Attacks you for " + damage + " damage");
        Player.takeDamage(damage);
        if (Player.currentPlayer.health == 0){
            System.out.println("You have been defeated, by " + randomEnemyEntityName +" you can come back to this location and try again.");
        }
    }
    
    //Make the enemy get damage when player attacks
    public void takeDamage(Weapon weapon){
       int damage = weapon.use();
       //if the health of enemy is 0 then we remove that enemy 
       if (this.health == 0){
           System.out.println("the enemy: " + randomEnemyEntityName + " was defeated. (Congrats)");
           this.entity.remove(randomEnemyEntityName);
           this.randomEnemyEntityName = "";
       }
       this.health -= damage;
       if (this.health < 0){ this.health = 0;}
       System.out.println("Enemy took "+damage + " damage");
       System.out.println("Enemies total remaining health: " +this.health);
    }
    
}
