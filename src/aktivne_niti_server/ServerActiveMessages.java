/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aktivne_niti_server;

import controller.Controller;
import forme.ServerskaForma;
import forme.model.ModelTabelePoruke;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class ServerActiveMessages extends Thread{

    
    ServerskaForma sf;

   

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }
    
    
    
    
    
    
    @Override
    public void run() {
      
        
        while(true){
               
        ModelTabelePoruke mtp = new ModelTabelePoruke(Controller.getInstance().getPoslatePoruke());
        sf.getjTablePoruke().setModel(mtp);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerActiveMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
    }
    
    
    
    
    
    
}
