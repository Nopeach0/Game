/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

/**
 *
 * @author behda
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public abstract class Npc {
    protected Map<String, Map<String, String>> npcList = new HashMap<>();
    protected String name = "";
    protected String dialouge = "";
    protected String npcRole = "";
    protected String npcLocation = "";
    //Refactored filePath as for Enemies class it is a different file using the same method
    protected String filePath = "./resources/npc_locations_quests.txt";
    
    //for no constructor parameters and constructor if the user want to change the npc details
    public Npc() { }
    
    public Npc(String name, String dialouge, String npcRole, String npcLocation){
        this.setNpcName(name);
        this.setDialouge(dialouge);
        this.npcRole = npcRole;
        this.npcLocation = npcLocation;
    }
    
    //Using the set of String for the npcList where we take items such as npcName
    //and etc from the npc_locations_quests.txt
   
    public void placeNpc(){
        this.npcList = DataLoader.LoadEntitiesFromFile(filePath, "===== Locations =====");
        for (String npcName: this.npcList.keySet()){
            Map<String, String> details = this.npcList.get(npcName);
            this.npcRole = details.getOrDefault("Role", "");
            this.npcLocation = details.getOrDefault("Location", "");
            this.dialouge = details.getOrDefault("Dialouge", "");
        }
    }
    
    //Set and Get mehthods for npcRole, npcLocation, npcName, npcDialouge
    //Set methods
    public void setNpcName(String name){
        this.name = name;
    }
    
    public void setDialouge(String dialouge){
        this.dialouge = dialouge;
    }
    
    //Get methods
    public String getNpcName(){
        return name;
    }
    
    public String getDialouge(){
        return dialouge;
    }
    
    public String getNpcRole(){
        return npcRole;
    }
    
    public String getNpcLocation(){
        return npcLocation;
    }
    
    //Abstract method for runIntraction
    public abstract void runIntraction();
    
    public static void main(String[] args) {
        //Npc npc1 = new Npc();
        //npc1.placeNpc();
        //System.out.println("You have encountered " + npc1.getNpcName() + ". " + "'Hi traveler I am " + npc1.getNpcName() + " \nand my Role in this " + npc1.getNpcLocation() + " is " + npc1.getNpcRole() + "'\n" + npc1.getDialouge());
    }
    
    
}
