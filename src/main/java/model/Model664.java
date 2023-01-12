/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * @author danijell258
 * class to store model664 data
 */
public class Model664 {
    
    /*
    # example
664A9183738  ZX                    MT  004ST           

id   idArticle         idArticleType size  times
664  'A9183738  ZX'    MT            00    4ST           

# details
- id 664, number, starts at 1 - ends at 3
- idArticle 664, text with numbers, starts at 4 - ends at 35
- idArticleType 664, text with numbers, starts at 36 - ends at 39
- size 664, number, starts at 40- ends at 42
- times 664, text with numbers, starts at 43 - ends at 44 -- contains ST at the end
- empty space, starts at 45 - ends at 128

# begins after 662 at least once and can be present unlimited times

#
Feld Von Bis Typ Bedeutung
'664' 1 3 numerisch Kennzeichen des 664-Satzes
Sachnr 4 35 alphanumerisch bestellte Artikelnummer
Teileart 36 39 alphanumerisch Teileart der Artikelnummer
Menge 40 42 numerisch bestellte Menge
ME 43 44 alphanumerisch Mengeneinheit (konstant 'ST')
(leer) 45 128 alphanumerisch konstant ' ' (Leerzeichen)
    */
    
    /**
     * Default constructor
     */
    public Model664(){
        
    }
    
    /**
     * Id number (664 -- file position from 1 ends at 3)
     */
    private int id;
    
    /**
     * Item number (file position from 4 to 35)
     */
    private String itemNo;
    
    /**
     * Item type (file position 36 to 39)
     */
    private String itemType;
    
    /**
     * Quantity amount (file position from 40 to 42)
     */
    private int quantity;
    
    /**
     * Type of quantity, like per Item (file position from 43 to 44)
     */
    private String times; // Mengeneinheit, but don't see a need to calculate -- to be used in the program
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getItemNo(){
        return itemNo;
    }
    
    public void setItemNo(String itemNo){
        this.itemNo = itemNo;
    }
    
    public String getItemType(){
        return itemType;
    }
    
    public void setItemType(String itemType){
        this.itemType = itemType;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public String getTimes(){
        return times;
    }
    
    public void setTimes(String times){
        this.times = times;
    }
    
}
