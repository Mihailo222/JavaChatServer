/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Poruka;
import model.User;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Acer
 */
public class ObradaKlijentskogZahteva extends Thread {
    
    
    private Socket socket;
    boolean kraj = true;

    public ObradaKlijentskogZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
       
        
        while(kraj && !isInterrupted()){
            
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija()){
                
                case Operacije.LOGIN:
                    User u = Controller.getInstance().loginUser((User)kz.getParam());
                    so.setOdgovor(u);
                    break;
                    
                    
                case Operacije.VRATI_ULOGOVANE:
                    ArrayList<User> ulogovani = Controller.getInstance().vratiUlogovane();
                    so.setOdgovor(ulogovani);
                    break;
                    
                    
                case Operacije.POSALJI_SVIM_ULOGOVANIM:
                    boolean odg = Controller.getInstance().posaljiSvimUlogovanim((Poruka)kz.getParam());
                    so.setOdgovor("USPEH");
                    break;
                    
                case Operacije.POSALJI_JEDNOM:
                    boolean odg1 = Controller.getInstance().posaljiJednomUseru((Poruka)kz.getParam());
                    so.setOdgovor("USPEH");
                    break;
                    
                    
               
                    case Operacije.VRATI_PORUKE_ISTOG_SENDERA:
                    ArrayList<Poruka> porukeIstogSendera = Controller.getInstance().vratiPorukeSendera((User)kz.getParam());
                    so.setOdgovor(porukeIstogSendera);
                    break;
                    
                    case Operacije.VRATI_SVE_PORUKE_KORISNIKA:
                    List<Poruka> porukeZaKorisnika = Controller.getInstance().vratiPorukeZaKorisnika((User)kz.getParam());
                    so.setOdgovor(porukeZaKorisnika);
                    break;
                
            }
            
            
            posaljiOdgovor(so);
            
            
        }
        
        

        
        
        
    }

    
    public void zaustavi(){
      
        interrupt();
        kraj = false;
        
        
        
        
    }
    
    
    
    
    
    
    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return null;
        
        
        
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {



        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
  
    
    
    

    
    
    
}
