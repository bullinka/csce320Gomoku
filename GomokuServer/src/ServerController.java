//package gomokuserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;

/**
 *
 * @author PLUCSCE
 */
public class ServerController implements Runnable {
    //fields
    private final ServerModel model;
    private final ServerView view;
    private ServerSocket serverSocket;
    private InputStream in;
    private OutputStream out;
    private Thread worker;
    
    /**
     * constructor to initialize fields
     * @param m
     * @param v 
     */
    public ServerController( ServerModel m, ServerView v)
    {
        model = m;
        view = v;
        try {
            serverSocket = new ServerSocket(view.getPort());
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * tries to accept connections from clients and stores them into a list of connections
     */
    public void startServer()
    {
        while(true)
        {
            try {
                Socket socket = serverSocket.accept();
                in = socket.getInputStream();
                out = socket.getOutputStream();
                model.addConnection(new Player(socket, new DataOutputStream(out), new DataInputStream(in), this));
            } catch (IOException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            setNumConnections();
        }
    }
    
    /**
     * Allows the view to show the number of connections
     */
    public void setNumConnections()
    {
        view.setNumConnections(model.getNumConnections());
    }

    /**
     * Starts the server
     */
    @Override
    public void run() {
       startServer();
    }
    
    /**
     * Starts a new thread of this to accept connections from clients
     */
    public void getConnections()
    {
        //System.out.println("lsdfkajshadfljk");
        worker = new Thread(this);
        //System.out.println("worker initialized");
        worker.start();
        //System.out.println("worker started");
    }
    
    /**
     * Sets the server socket to null, sets all connections to null, and exits the program.
     */
    public void stopServer()
    {
        serverSocket = null;
        model.disconnect();
        //System.exit(0);
    }
    
    /**
     * displays a message sent by a client to the view 
     * @param username
     */
    public void addPlayer(String username)
    {
        view.addNewPlayer(username + "\n");
    }
    
    /**
     * removes a connection from the list of all connections
     * @param c 
     */
    public void removePlayer(Player c)
    {
        model.removeConnection(c);
        view.setNumConnections(model.getNumConnections());
    }
    
    /**
     * registers in the specified player using their username and password
     * return true if successful false otherwise
     * @param uName
     * @param pWord
     * @return 
     */
    public boolean registerPlayer(String uName, String pWord)
    {
        return model.registerPlayer(uName, pWord);
    }
    
    /**
     * logs in the specified player using their username and password
     * return true if successful false otherwise
     * @param uName
     * @param pWord
     * @return 
     */
    public boolean loginPlayer(String uName, String pWord)
    {
        return model.login(uName, pWord);
    }
    
    public String getAllUserNames()
    {
        return model.getAllUsernames();
    }
    /**
     * 
     * @param challengee
     * @param message 
     */
    public void sendChallenge(String challengee, String message) {
        model.sendChallenge(challengee, message);//To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param challenger
     * @param response 
     */
    public void sendResponse(String challenger, String response) {
         model.sendResponse(challenger, response); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   public void addToMatchMaking(String username, Player p){
       model.addToMatchMaking(username, p);
   }
   
   public void sendMessageToAll(String message)
   {
       model.sendMessageToAll(message);
   }
   
   public String getUsersIPAddress(String user)
   {
       return model.getUsersIPAddress(user);
   }
}
