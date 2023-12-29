/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import model.Poruka;

/**
 *
 * @author Acer
 */
public class ModelTabelePoruke extends AbstractTableModel{

    
    ArrayList<Poruka> poruke = new ArrayList<>();
    
    String kolone[] = {" DATE ", " FROM ", " TEXT ", " TO "};

    public ModelTabelePoruke(ArrayList<Poruka> poruke) {
    this.poruke=poruke;
    }
    
    
    
    
    
    @Override
    public int getRowCount() {

        return poruke.size();


    }

    @Override
    public int getColumnCount() {

        return kolone.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Poruka p = poruke.get(rowIndex);
        
        switch(columnIndex){
            
            case 0:
                return p.getDatumVreme();
            case 1:
                return p.getSender();
            case 2:
                
                if(p.getText().length()>20)
                     return p.getText().substring(0,19);
                else
                    return p.getText();
            case 3:
                return p.getReciever();
            default:
                return "N/A";

            
            
        }
        
        
        
    }

    @Override
    public String getColumnName(int column) {

        return kolone[column];
    }

    public ArrayList<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(ArrayList<Poruka> poruke) {
        this.poruke = poruke;
    }
    
    
    
    
    
    
    
    
    
}
