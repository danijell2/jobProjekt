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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author danijell258
 */
public class ImportFile extends Commons{
    
    public ImportFile(Properties properties){
        super.setProperties(properties);
    }
    
    public model.TransitionModel importFile(String location) throws FileNotFoundException, IOException{
        
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
            
        
        model.TransitionModel transitionModel = null;
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(location)));
        String line;
        model.Model661 model661 = null;
        model.Model662 model662 = null;
        boolean model661Exist = false;
        boolean model662Exist = false;
        int count = 0;
        List<model.Model664> model664List = new ArrayList();
        model.Model669 model669 = null;
        
        while((line = bufferedReader.readLine()) != null){
            
            // check type661
            if(model661 == null){
                
                model661 = read661(line);
                
                // check for error
                if(model661 == null){
                    super.log("model 661 type not found");
                    break;     
                }
              
             // if type661 exist continue
            }else{
                
                // check for 662
                if(model662 == null){
                    model662 = read662(line);
                    
                    if(model662 == null){
                        super.log("model 662 type not found");
                        break;
                    }
                    
                }else{
                    
                    // continue to model 664
                    model.Model664 model664 = read664(line);
                    
                    if(model664 != null){
                        
                        model664List.add(model664);
                        
                    }else if(model664List.isEmpty()){
                        
                        super.log("No model 664 type found");
                        break;
                        
                    }else{
                        
                        // continue with 669 model
                        if(model669 == null){
                            
                            model669 = read669(line);
                            
                            if(model669 == null){
                                super.log("No model 669 type found");
                                break;
                                
                            }
                            
                        }
                        
                    }
                    
                }
                
                
            }

        }
        
        bufferedReader.close();
        
        // check if everything alright
        if(model661 != null && model662 != null && model664List.size() > 0 && model669 != null && model664List.size() == model669.getSize()){
            
            transitionModel = new model.TransitionModel();
            transitionModel.setModel661(model661);
            transitionModel.setModel662(model662);
            transitionModel.setModel664List(model664List);
            transitionModel.setModel669(model669);
            
        }
        
        return transitionModel;
        
    }
    
    // convert line to type661 model
    private model.Model661 read661(String line){
        
        model.Model661 model = null;
        
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
        
        // check if 128 characters present
        if(line.length() == 128){
            
            // seperate to string
            String idType = line.substring(0, 3);
            String deliveryNumber = line.substring(3, 10).replace(" ", ""); // can contain space -- need to remove it to be able to convert to number
            String customerNumber = line.substring(10, 17).replace(" ", "");
            String sendNumber = line.substring(17, 25).replace(" ", "");
            String sendDate = line.substring(25, 33);
            String sendTime = line.substring(33, 37);
            String emptySpace = line.substring(37);
            
            // check if valid
            if(idType.equals("661") && isNumber(deliveryNumber) && isNumber(customerNumber) && isNumber(sendNumber)
                    && isDate(sendDate) && isTime(sendTime) && isEmptySpace(emptySpace)){
                
                // save to model
                model = new model.Model661();
                model.setSupplier(Integer.valueOf(deliveryNumber));
                model.setCustomer(Integer.valueOf(customerNumber));
                model.setSendDate(LocalDate.parse(sendDate, DateTimeFormatter.ofPattern("yyyyMMdd")));
                model.setSendTime(LocalTime.parse(sendTime, DateTimeFormatter.ofPattern("HHMM")));
                
            }else{
                model = null;
            }
            
        }else{
            model = null;
        }

        return model;
        
    }
    
    private model.Model662 read662(String line){
        
        model.Model662 model662= null;
        
        /*
        # example
6627641M1002301323456                    A0                                                                                     

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
        
        // check if 128 characters present
        if(line.length() == 128){
            
            // convert to individual String
            String idType = line.substring(0, 3);
            String orderNumber = line.substring(3, 7).replace(" ", "");
            String orderNumberIdentification = line.substring(7, 11);
            String itemNumber = line.substring(11, 21).replace(" ", "");
            String emptySpace1 = line.substring(21, 41);
            String itemType = line.substring(41, 45);
            String emptySpace2 = line.substring(45, 128);
            
            // check if valid
            if(idType.equals(idType) && isNumber(orderNumber) && isNumber(itemNumber) && isEmptySpace(emptySpace1) && isEmptySpace(emptySpace2)){
                
                // save to model
                model662 = new model.Model662();
                model662.setOrderNo(Integer.valueOf(orderNumber));
                model662.setOrderNumberIdentification(orderNumberIdentification);
                model662.setReferenceNo(Long.valueOf(itemNumber));
                model662.setCommodity(itemType);
                
            }else{
                model662 = null;
            }
            
        }else{
            model662 = null;
        }

        return model662;
        
    }
    
    public model.Model664 read664(String line){
        
        model.Model664 model = null;
        
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
        
        // check if 128 characters
        if(line.length() == 128){
            
            // get individual strings
            String idType = line.substring(0, 3);
            String articleNumber = line.substring(3, 35);
            String articleType = line.substring(35, 39);
            String size = line.substring(39, 42);
            String times = line.substring(42, 44);
            String emptySpace = line.substring(44, 128);
            
            // check if valid
            if(idType.equals("664") && isNumber(size) && times.equals("ST") && isEmptySpace(emptySpace)){
                
                // convert to model
                model = new model.Model664();
                model.setItemNo(articleNumber);
                model.setItemType(articleType);
                model.setQuantity(Integer.valueOf(size));
                model.setTimes(times);
                
            }
            
        }

        return model;
        
    }
    
    public model.Model669 read669(String line){
        
        model.Model669 model = null;
        
        /*
        
        
# example                                                                               
6690000005                                                                                                                      

id   sizeOf664 emptySpace
669  0000005

# details
- id, number, starts at 1 - ends at 3
- sizeOf664, number, starts at 4 - ends at 10
- emptySpace, number, starts at 11 - ends at 128

#
'669' 1 3 numerisch Kennzeichen des 669-Satzes
Anz.664 4 10 numerisch Anzahl der 664er-Sätze in dieser Datei
(leer) 11 128 alphanumerisch konstant ' ' (Leerzeichen) 

        
        */
        
        // check if 128 characters
        if(line.length() == 128){
            
            String modelType = line.substring(0, 3);
            String size = line.substring(3, 10);
            String emptySpace = line.substring(10, 128);
            
            // validate data
            if(modelType.equals(modelType) && isNumber(size) && isEmptySpace(emptySpace)){
                
                // convert to model
                model = new model.Model669();
                model.setSize(Integer.valueOf(size));
                
            }
            
        }
 
        return model;
        
    }
    
    private boolean isNumber(String text){
        
        boolean state = false;
        
        try{
            
            Double number = Double.valueOf(text);
            state = true;
            
        }catch(NumberFormatException ex){
            state = false;
        }
        
        return state;
        
    }
    
    private boolean isDate(String text){
        
        boolean state = false;
        
        try{
            
            LocalDate date = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyyMMdd"));
            state = true;
            
        }catch(DateTimeParseException exception){
            state = false;
        }
        
        return state;
        
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
    
    private boolean isEmptySpace(String text){
        
        boolean state = true;
        
        for(int i = 0; i < text.length(); i++){
            
            if(!String.valueOf(text.charAt(i)).equals(" ")){
                return false;
            }
            
        }
        
        return state;
        
    }
    
}
