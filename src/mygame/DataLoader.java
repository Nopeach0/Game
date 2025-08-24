/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author behda
 */
public class DataLoader {
    public static Map<String, Map<String, String>> LoadEntitiesFromFile(String filePath, String stopline){
        Map<String, Map<String, String>> entityMap = new HashMap<>();
        String currentKey = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = br.readLine()) != null && !stopline.equals(line)){
                if (line.trim().isEmpty() || line.startsWith("====") || line.startsWith("-----")) continue;
                String[] result = line.split(":", 2);
                String key = result[0].trim();
                String value = result[1].trim();
                if (key.equalsIgnoreCase("Name") || key.equalsIgnoreCase("Enemy Name")){
                    //Create a new name
                    currentKey = value;
                    entityMap.putIfAbsent(currentKey, new HashMap<>());
                }
                entityMap.get(currentKey).put(key, value);
               
                //if (npcList.keySet().contains(result[0])){
                    //If its there change the name to the new npc Name
                //    npcList.put(result[0], result[1]);
                //    name = npcList.get(result[0]);
                //}
                //npcList.put(result[0], result[1]);
            }
            br.close();
            
        } catch (IOException ex){
            System.out.println("IOExcption " + ex.getMessage());
        }
        return entityMap;
    }
}
