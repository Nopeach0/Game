/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

import java.util.Map;

/**
 *
 * @author behda
 */
public class Attack {
    //Balancing the attack for player and enemies
    public int getAttackDamage(Map <String, String> details, String sourceType){
        String value = details.get("Health") != null ? details.get("Health") : details.get("Effect");
        //Remove all the non-Numeric digits
        String numericPart = value.replaceAll("[^\\d]", "");
        //Check if we got no number in the String if we have then we parseInt else we print No value found
        if (!numericPart.isEmpty()){
            int base = Integer.parseInt(numericPart);
            //Base damage for example is 30 from 300 health
            // and we assign random decimal number between 0 to 1 and mutliply by the base 0.7 * 30 = 21 each time is it dealing different damage
            //enemy deals at least 5 damage minimum but player deals 10 damage at least minimum;
            if (sourceType.equalsIgnoreCase("enemy")){
                base /= 25;
                return (int)(Math.random() * base) + 5;
            } else if (sourceType.equalsIgnoreCase("player")){
                base /= 2;
                return (int)(Math.random() * base) + 20;
            }
        } else {
            System.out.println("No numeric value found in: " + value);
        }
        return 0;
    }
}
