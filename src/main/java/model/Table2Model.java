/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * model class for table2 in database
 */
public class Table2Model {
 
    /**
     * Default constructor
     */
    public Table2Model(){
        
    }
 
    /*
    
    MESSAGEID eindeutige NachrichtenID
POSITION fortlaufende Positionsnummer zur NachrichtenID (ab 1)
ITEMNO Artikelnummer
QUANTITY bestellte Menge
ITEMTYPE Teileart
    
    */
   
    /**
     * Message id from sql table
     */
    private int messageId;
    
    /**
     * Item position from sql table
     */
    private int position;
    
    /**
     * Item number from sql table
     */
    private String itemNo;
    
    /**
     * Quantity from sql table
     */
    private int quantity;
    
    /**
     * Item type from sql table
     */
    private String itemType;
    
    public int getMessageId(){
        return messageId;
    }
    
    public void setMessageId(int messageId){
        this.messageId = messageId;
    }
    
    public int getPosition(){
        return position;
    }
    
    public void setPosition(int position){
        this.position = position;
    }
    
    public String getItemNo(){
        return itemNo;
    }
    
    public void setItemNo(String itemNo){
        this.itemNo = itemNo;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public String getItemType(){
        return itemType;
    }
    
    public void setItemType(String itemType){
        this.itemType = itemType;
    }
    
}
