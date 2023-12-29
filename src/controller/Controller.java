/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Poruka;
import model.User;

/**
 *
 * @author Acer
 */
public class Controller {
    private static int kapacitet;
    
    private static Controller instance;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<User> ulogovani = new ArrayList<>();
    private ArrayList<Poruka> poslatePoruke = new ArrayList<>();
    
    
    
    private Controller(){
        User u1=new User("pera1@gmail.com", "k1","Pera 1", "Peric 1");
         User u2=new User("pera2@gmail.com", "k2","Pera 2", "Peric 2");
          User u3=new User("pera3@gmail.com", "k3","Pera 3", "Peric 3");
           User u4=new User("pera4@gmail.com", "k4","Pera 4", "Peric 4");
           
           
           
           users.add(u1);
           users.add(u2);
           users.add(u3);
           users.add(u4);
           
           
           
           
           
           
    }
    
    public static Controller getInstance(){
        if(instance==null)
            instance=new Controller();
        
        return instance;
    }

    public User loginUser(User user) {

        for(User u : users){
            if(u.getEmail().equals(user.getEmail())    &&     u.getPassword().equals(user.getPassword())      && !ulogovani.contains(u)  && ulogovani.size()<kapacitet){
                ulogovani.add(u);
                return u;
            }
                
            }
        
        
        
        
        
        
        
        
        //ako smo stigli do ovde, sada proveravamo da li je osoba napisala pgresne kredencijale ili je vec ulogovana
        
            
            
        
        
        
return null;
    }

    public static int getKapacitet() {
        return kapacitet;
    }

    public static void setKapacitet(int kapacitet) {
        Controller.kapacitet = kapacitet;
    }

    public ArrayList<User> vratiUlogovane() {
     
        
    return ulogovani;
        
        
    }

    public boolean posaljiSvimUlogovanim(Poruka poruka) {


       String SenderUsername = poruka.getSender().getEmail();

        
        for(User u : ulogovani){
            
            if(!(u.getEmail().equals(SenderUsername))){
            
            
           Poruka p = new Poruka();
           p.setDatumVreme(poruka.getDatumVreme());
           p.setSender(poruka.getSender());
            p.setReciever(u);
          p.setText(poruka.getText());
           System.out.println("CONTROLLER: "+p.getSender());
           poslatePoruke.add(p);
            
            }
        
            
        }



        return true;







    }

    
    public void ispisi(){
        for(Poruka p : poslatePoruke)
            System.out.println(p);
    }

    public boolean posaljiJednomUseru(Poruka poruka) {


       Poruka p = new Poruka();
       p.setDatumVreme(poruka.getDatumVreme());
       p.setSender(poruka.getSender());
       p.setReciever(poruka.getReciever());
       p.setText(poruka.getText());
       
       poslatePoruke.add(p);




       return true;




    }

    public ArrayList<Poruka> getPoslatePoruke() {
        return poslatePoruke;
    }

    public void setPoslatePoruke(ArrayList<Poruka> poslatePoruke) {
        this.poslatePoruke = poslatePoruke;
    }

 

    public ArrayList<Poruka> vratiPorukeSendera(User user) {


        ArrayList<Poruka> porukeSendera = new ArrayList<>();
        
        for(Poruka p : poslatePoruke){
            if(p.getSender().getEmail().equals(user.getEmail()))
                porukeSendera.add(p);
        }



        return porukeSendera;



    }

  public List<Poruka> vratiPorukeZaKorisnika(User user) {

     

  List<Poruka> poruke = new ArrayList<>();
  
  for(Poruka p : poslatePoruke){
   if(p.getReciever().getEmail().equals(user.getEmail()))
       poruke.add(p);
    
    
    
    
   }


return poruke;

    }

    
    
    
    
    
}
