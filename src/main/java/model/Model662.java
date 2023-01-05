/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author danijell258
 */

public class Model662 {
    
    public Model662(){
        
    }
    
    private int id;
    private int orderNumber;
    private String orderNumberIdentification;
    private int itemNumber;
    private String itemType;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getOrderNumber(){
        return orderNumber;
    }
    
    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }
    
    public String getOrderNumberIdentification(){
        return orderNumberIdentification;
    }
    
    public void setOrderNumberIdentification(String orderNumberIdentification){
        this.orderNumberIdentification = orderNumberIdentification;
    }
    
    public int getItemNumber(){
        return itemNumber;
    }
    
    public void setItemNumber(int itemNumber){
        this.itemNumber = itemNumber;
    }
    
    public String getItemType(){
        return itemType;
    }
    
    public void setItemType(String itemType){
        this.itemType = itemType;
    }
    
    /*
    
    id   orderNumber identificationOrderNumber idItem itemType
662  764         1M1002301323456                    A0                                                                                     

# details
- id -- 662, number, starts at 1 - ends at 3
- orderNumber -- number, starts at 4 - ends at 7
- identificationOrderNumber -- text with number, starts at 8 - ends at 11
- idItem -- number, starts at 12 - ends at 21
- empty space, starts at 22 - ends at 41
- itemType -- text and number, starts at 42 - ends at 45
- empty space, starts at 46 - ends at 128
    
    */
    
}
