/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 * class for transition model between imported data and making them ready to be inserted into database
 */
public class TransitionModel {
    
    public TransitionModel(){
        
    }
    
    private Model661 model661;
    private Model662 model662;
    private List<Model664> model664List;
    private Model669 model669;
    
    public Model661 getModel661(){
        return model661;
    }
    
    public void setModel661(model.Model661 model661){
        this.model661 = model661;
    }
    
    public Model662 getModel662(){
        return model662;
    }
    
    public void setModel662(model.Model662 model662){
        this.model662 = model662;
    }
    
    public List<model.Model664> getModel664List(){
        return model664List;
    }
    
    public void setModel664List(List<model.Model664> model664List){
        this.model664List = model664List;
    }
    
    public Model669 getModel669(){
        return model669;
    }
    
    public void setModel669(Model669 model669){
        this.model669 = model669;
    }
    
}
