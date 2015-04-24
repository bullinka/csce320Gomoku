//package gomokuserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PLUCSCE
 */
public class ServerModel {
    private final String ONLINE = "online"; 
    
   private int numConnections;
   private List<Player> allPlayers;
   private ServerController controller;
   private final RegisteredPlayers registeredPlayers;
   private final MatchMaking matchmaker;
   /**
    * sets number of connections to 0
    */
   @SuppressWarnings("Convert2Diamond")
   public ServerModel()
   {
       registeredPlayers = new RegisteredPlayers();
       allPlayers = new ArrayList<Player>();
       numConnections = 0;
       matchmaker = new MatchMaking();
   }
   
   /**
    * registers the player, return true if successful, false otherwise
    * @param username
    * @param password
    * @return 
    */
   public boolean registerPlayer(String username, String password)
   {
       return registeredPlayers.registerIsRegistered(username, password);
   }
   
   public boolean login(String username, String password)
   {
       return registeredPlayers.loginIsRegistered(username, password);
   }
   
   public void addToMatchMaking(String username, Player p){
       matchmaker.addPlayer(username, p);
   }
   
   
   /**
    * Sets controller to cont argument
    * @param cont 
    */
   public void setController(ServerController cont)
   {
       this.controller = cont;
   }
   
   /**
    * Adds a connection to the list of connections
    * @param c 
    */
   public void addConnection(Player c)
   {
       allPlayers.add(c);
       numConnections++;
       c.getMessages();
   }
   
   /**
    * Removes an instance of a connection from the list of connections
    * @param c 
    */
   public void removeConnection(Player c)
   {
       allPlayers.remove(c);
       numConnections--;
   }
    
   /**
    * Returns the number of clients connected to the server
    * @return 
    */
   public int getNumConnections()
   {
       return numConnections;
   }
   
   /**
    * Resets the list of all connections and sets the number of connections to 0
    */
   public void disconnect()
   {
       allPlayers = new ArrayList<Player>();
       numConnections = 0;
   }
   
   public String getAllUsernames()
   {
       String allNames = new String();
       for(int i = 0; i<allPlayers.size();i++)
       {
           if(allPlayers.get(i).getUsername() != null){
                allNames = allNames + " " + allPlayers.get(i).getUsername();
           }
       }
       return allNames;
   }

   /**
    * 
    * @param challanger
    * @param message 
    */
    public void sendChallenge(String challanger, String message) {
        matchmaker.challenge(challanger, message);
    }

    /**
     * 
     * @param challanger
     * @param response 
     */
    public void sendResponse(String challanger, String response) {
         matchmaker.respond(challanger, response);
    }
    
    public void sendMessageToAll(String message)
    {
        for (Player p : allPlayers)
        {
            p.sendMessage(message);
        }
    }
    
    public String getUsersIPAddress(String user)
    {
        return matchmaker.getUsersIPAddress(user);
    }
}
