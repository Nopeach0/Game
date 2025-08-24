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

public class GoodEntities extends Npc{
    String randomGoodEntityName = "";
    public Map<String, Map<String, String>> entity = new HashMap<>();
    
    public GoodEntities(){
        super();
        this.placeNpc();
        this.entity.putAll(this.npcList);
    }
    
    @Override
    public void runIntraction() {
        System.out.println("You have encountered " + randomGoodEntityName + "\n");
        for (Map.Entry<String, String> entry : this.entity.get(randomGoodEntityName).entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");
    }
}
