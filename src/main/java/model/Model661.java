/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author danijell258
 */
public class Model661 {
    /*
    # 661 constraints
- id -- 661, starts at 1, ends at 3     
- delivery -- id of delivery, starts at 4 - ends at 10
- customer -- id of customer, starts at 11 - ends at 17
- sendNumber -- send number, starts at 18 - ends at 25
- sendDate -- sending date, format YYYYMMDD, starts at 26 - ends at 33
- sendTime -- sending time, format HHMM, starts at 34 - ends at 37
- empty space, starts at 38 - ends at 128 
        */
    
    public Model661(){
        
    }
    
    private int id;
    private int deliveryNumber;
    private int customerNumber;
    private int sendNumber;
    private LocalDate sendDate;
    private LocalTime sendTime;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getDeliveryNumber(){
        return deliveryNumber;
    }
    
    public void setDeliveryNumber(int deliveryNumber){
        this.deliveryNumber = deliveryNumber;
    }
    
    public int getCustomerNumber(){
        return customerNumber;
    }
    
    public void setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
    }
     
    public int getSendNumber(){
        return sendNumber;
    }
    
    public void setSendNumber(int sendNumber){
        this.sendNumber = sendNumber;
    }
    
    public LocalDate getSendDate(){
        return sendDate;
    }
    
    public void setSendDate(LocalDate sendDate){
        this.sendDate = sendDate;
    }
    
    public LocalTime getSendTime(){
        return sendTime;
    }
    
    public void setSendTime(LocalTime sendTime){
        this.sendTime = sendTime;
    }
    
}
