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
    private int orderNo;
    private String orderNumberIdentification;
    private int referenceNo;
    private String commodity;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getOrderNo(){
        return orderNo;
    }
    
    public void setOrderNo(int orderNo){
        this.orderNo = orderNo;
    }
    
    public String getOrderNumberIdentification(){
        return orderNumberIdentification;
    }
    
    public void setOrderNumberIdentification(String orderNumberIdentification){
        this.orderNumberIdentification = orderNumberIdentification;
    }
    
    public int getReferenceNo(){
        return referenceNo;
    }
    
    public void setReferenceNo(int referenceNo){
        this.referenceNo = referenceNo;
    }
    
    public String getCommodity(){
        return commodity;
    }
    
    public void setCommodity(String commodity){
        this.commodity = commodity;
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
