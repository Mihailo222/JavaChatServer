/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class PokreniServer extends Thread {

    

    
    ObradaKlijentskogZahteva okz;
    ServerSocket ssocket;
    Socket socket;
    boolean kraj = false;
    ArrayList<ObradaKlijentskogZahteva> clients = new ArrayList<>();
    
        
        
    @Override
    public void run() {
       
        
      
            
        try {
            ssocket = new ServerSocket(9000);
            System.out.println("Server radi.....");
          
         
       
            
            while(!kraj && !isInterrupted()){
                
                
                
                
                
                
                socket = ssocket.accept();
                System.out.println("Klijent se zakacio...");
                
                
                
                okz = new ObradaKlijentskogZahteva(socket);
                okz.start();
                
                
                dodajKlijenta(okz);
                
                
                
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
  
    }
    
    
    
    
    
    
    public void zaustavi(){

           interrupt();
           kraj=true;
         
       
   
        
        
    }
    
    
    public void dodajKlijenta(ObradaKlijentskogZahteva okz){

        if(!clients.contains(okz))
            clients.add(okz);
            
     }

    public ArrayList<ObradaKlijentskogZahteva> getClients() {
        return clients;
    }

   
    
    

    
}
