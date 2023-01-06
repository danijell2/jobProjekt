/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author danijell258
 */

public class Table2Model {
 
    public Table2Model(){
        
    }
 
    /*
    
    MESSAGEID eindeutige NachrichtenID
POSITION fortlaufende Positionsnummer zur NachrichtenID (ab 1)
ITEMNO Artikelnummer
QUANTITY bestellte Menge
ITEMTYPE Teileart
    
    */
   
    private int messageId;
    private int position;
    private String itemNo;
    private int quantity;
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
