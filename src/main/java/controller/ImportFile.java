/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author danijell258
 */
public class ImportFile {
    
    public void importFile() throws FileNotFoundException, IOException{
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("./test.txt")));
        String line;
        model.Model661 model661 = null;
        
        while((line = bufferedReader.readLine()) != null){
            
            // check type661
            if(model661 == null){
                
                model661 = read661(line);
                
                // check for error
                if(model661 == null){
                    break;
                }
              
             // if type661 exist continue
            }else{
                
                // check for 662
                
            }
            
            //
            /*
            661120    4711   15      202305011440
            6627641M1002301323456                    A0
            664A9183738  ZX                    MT  004ST
            664PK2782938                       MT  001ST
            6643B0976456AXZEU                  PT  002ST
            6649B5567801  TXA                  PT  001ST
            664A83748393                       XT  003ST
            6690000005
            
            */
            
            
        }
        
        bufferedReader.close();
        
    }
    
    // convert line to type661 model
    private model.Model661 read661(String line){
        
        model.Model661 model = null;
        
        // check if 128 characters present
        if(line.length() == 128){
            
            // seperate to string
            String idType = line.substring(0, 3);
            String deliveryNumber = line.substring(3, 10).replace(" ", ""); // can contain space -- need to remove it to be able to convert to number
            String customerNumber = line.substring(10, 17).replace(" ", "");
            String sendNumber = line.substring(17, 25).replace(" ", "");
            String sendDate = line.substring(25, 33).replace(" ", "");
            String sendTime = line.substring(33, 37).replace(" ", "");
            
            // check if valid
            if(idType.equals("661") && isNumber(deliveryNumber) && isNumber(customerNumber) && isNumber(sendNumber)
                    && isDate(sendDate) && isTime(sendTime)){
                
                // save to model
                model = new model.Model661();
                model.setId(Integer.valueOf(idType));
                model.setDeliveryNumber(Integer.valueOf(deliveryNumber));
                model.setCustomerNumber(Integer.valueOf(customerNumber));
                model.setSendDate(LocalDate.parse(sendDate, DateTimeFormatter.ofPattern("YYYYMMDD")));
                model.setSendTime(LocalTime.parse(sendTime, DateTimeFormatter.ofPattern("HHMM")));
                
            }else{
                model = null;
            }
            
        }else{
            model = null;
        }
        
        /*
        #
        661120    4711   15      202305011440
        
        661 constraints
        - id -- 661, starts at 1, ends at 3
        - delivery -- id of delivery, starts at 4 - ends at 10
        - customer -- id of customer, starts at 11 - ends at 17
        - sendNumber -- send number, starts at 18 - ends at 25
        - sendDate -- sending date, format YYYYMMDD, starts at 26 - ends at 33
        - sendTime -- sending time, format HHMM, starts at 34 - ends at 37
        - empty space, starts at 38 - ends at 128
        */
        
        return model;
        
    }
    
    private boolean isNumber(String text){
        
        boolean state = false;
        
        try{
            
            int number = Integer.valueOf(text);
            state = true;
            
        }catch(NumberFormatException ex){
            state = false;
        }
        
        return state;
        
    }
    
    private boolean isDate(String text){
        
        boolean state = false;
        
        try{
            
            LocalDate date = LocalDate.parse(text, DateTimeFormatter.ofPattern("YYYYMMDD"));
            state = true;
            
        }catch(DateTimeParseException exception){
            state = false;
        }
        
        return false;
        
    }
    
    private boolean isTime(String text){
        
        boolean state = false;
        
        try{
            
            LocalTime time = LocalTime.parse(text, DateTimeFormatter.ofPattern("HHMM"));
            state = true;
            
        }catch(Exception exception){
            state = false;
        }
        
        return state;
        
    }
    
}
