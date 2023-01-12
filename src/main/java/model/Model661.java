/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author danijell258
 * class to store model661 data
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
    
    /**
     * Default constructor
     */
    public Model661(){
        
    }
    
    /**
     * Record id (662)
     */
    private int id;
    
    /**
     * Supplier identification (file position from 4 to 10)
     */
    private int supplier;
    
    /**
     * Customer number (file position from 11 to 17)
     */
    private int customer;
    
    /**
     * Send number (file position from 18 to 25)
     */
    private int sendNumber;
    
    /**
     * Send date (file position from 26 to 33 -- format YYYYMMDD)
     */
    private LocalDate sendDate;
    
    /**
     * Send time (file position from 34 to 37 -- format HHMM)
     */
    private LocalTime sendTime;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getSupplier(){
        return supplier;
    }
    
    public void setSupplier(int supplier){
        this.supplier = supplier;
    }
    
    public int getCustomer(){
        return customer;
    }
    
    public void setCustomer(int customer){
        this.customer = customer;
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
