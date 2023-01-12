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
 * @author danijell258
 * class for handling of defined models 661, 662, 664, 669 and importing data from file
 */
public class ImportFile extends Commons{
    
    /**
     * constructor to initialize ImportFile class
     * @param properties are the main settings defined by the user
     */
    public ImportFile(Properties properties){
        super.setProperties(properties);
    }
    
    /**
     * method to import specific file from import folder
     * @param location is the exact path to the file
     * @return TransitionModel which has been created from data imported from the file
     * @throws FileNotFoundException exception if file was not found
     * @throws IOException exception if there is error while reading the file
     */
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
            
        /**
         * create new transitionModel variable where the data will be imported from file
         */
        model.TransitionModel transitionModel = null;
        
        /**
         * buffered reader is required to read the file
         */
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(location)));
        
        /**
         * line is a variable in which the buffered reader is storing currently read line from file
         */
        String line;
        
        /**
         * create model661 variable
         */
        model.Model661 model661 = null;
        
        /**
         * create model662 variable
         */
        model.Model662 model662 = null;  

        /**
         * create and initialize list of 664 models
         */
        List<model.Model664> model664List = new ArrayList();
        
        /**
         * create model669 variable
         */
        model.Model669 model669 = null;
        
        /**
         * read the file and check if current read line is not null
         */
        while((line = bufferedReader.readLine()) != null){
            
            /**
             * check if model661 is not found
             */
            if(model661 == null){
                
                /**
                 * show message on the screen
                 */
                super.log("importing model 661");
                
                /**
                 * import data from the line into model661 variable
                 */
                model661 = read661(line);
                
                /**
                 * check if model661 was not successfully created and if so exit the program
                 */
                if(model661 == null){
                    super.log("model 661 type not found");
                    break;     
                }
              
             /**
              * if model 661 was successfully created continue to next import stage
              */
            }else{
                
                /**
                 * check if model662 wasn't already imported and import it
                 */
                if(model662 == null){
                    
                    super.log("importing model 662");
                    model662 = read662(line);
                    
                    if(model662 == null){
                        super.log("model 662 type not found");
                        break;
                    }
                 
                 /**
                  * if model662 already exist continue to next stage of file import
                  */
                }else{
                    
                    /**
                     * get model664
                     */
                    model.Model664 model664 = read664(line);
                    
                    /**
                     * check if model 664 is not null and add to list
                     */
                    if(model664 != null){
                        super.log("importing model 664");
                        model664List.add(model664);
                     
                     /**
                      * if model664 is not valid check if already list contains at list one model664 which is a requirement
                      */
                    }else if(model664List.isEmpty()){
                        
                        super.log("No model 664 type found");
                        break;
                     
                     /**
                      * if model664 was imported at least once continue
                      */   
                    }else{
                        
                        /**
                         * check if model669 wasn't already imported
                         */
                        if(model669 == null){
                            
                            /**
                             * show message on the screen
                             */
                            super.log("importing model 669");
                            
                            /**
                             * import model669
                             */
                            model669 = read669(line);
                            
                            /**
                             * check if model660 wasn't successfully imported and if not log it and exit the program
                             */
                            if(model669 == null){
                                super.log("No model 669 type found");
                                break;
                                
                            }
                            
                        }
                        
                    }
                    
                }
                
                
            }

        }
        
        /**
         * close the buffered reader
         */
        bufferedReader.close();
        
        /**
         * show message on the screen
         */
        super.log("Verifing imported models");
        
        /**
         * check if all retrieved variable are valid
         */
        if(model661 != null && model662 != null && model664List.size() > 0 && model669 != null && model664List.size() == model669.getSize()){
            
            /**
             * create transitionModel and save all data
             */
            transitionModel = new model.TransitionModel();
            transitionModel.setModel661(model661);
            transitionModel.setModel662(model662);
            transitionModel.setModel664List(model664List);
            transitionModel.setModel669(model669);
            
        }else{
            super.log("imported models incorrect, cannot proceed");
        }
        
        return transitionModel;
        
    }
    
    /**
     * method to read the String and convert it model661
     * @param line is a String to be converted to model661
     * @return model661 data type
     */
    private model.Model661 read661(String line){
        
        // create new model661
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
        
        /**
         * check if 128 characters present
         */
        if(line.length() == 128){
            
            /**
             * idType variable which identifies model
             */
            String idType = line.substring(0, 3);
            
            /**
             * deliveryNumber
             */
            String deliveryNumber = line.substring(3, 10).replace(" ", ""); // can contain space -- need to remove it to be able to convert to number
            
            /**
             * customer number
             */
            String customerNumber = line.substring(10, 17).replace(" ", ""); // can contain space -- need to remove it to be able to convert to number
            
            /**
             * send number
             */
            String sendNumber = line.substring(17, 25).replace(" ", "");
            
            /**
             * send date
             */
            String sendDate = line.substring(25, 33);
            
            /**
             * send time
             */
            String sendTime = line.substring(33, 37);
            
            /**
             * empty space in a line
             */
            String emptySpace = line.substring(37);
            
            /**
             * check if read data is valid
             */
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
    
    /**
     * method to red the line String and convert it to model662
     * @param line is the String to be read
     * @return model662 is the data type to be returned
     */
    private model.Model662 read662(String line){
        
        /**
         * create model662
         */
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
        
        /**
         * check if 128 characters present
         */
        if(line.length() == 128){
            
            /**
             * get id
             */
            String idType = line.substring(0, 3);
            
            /**
             * get order number
             */
            String orderNumber = line.substring(3, 7).replace(" ", "");
            
            /**
             * get order number identification
             */
            String orderNumberIdentification = line.substring(7, 11);
            
            /**
             * get item number
             */
            String itemNumber = line.substring(11, 21).replace(" ", "");
            
            /**
             * get empty space
             */
            String emptySpace1 = line.substring(21, 41);
            
            /**
             * get item type
             */
            String itemType = line.substring(41, 45);
            
            /**
             * get empty space
             */
            String emptySpace2 = line.substring(45, 128);
            
            /**
             * check if read data is valid
             */
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
  
    /**
     * method to read String line and convert it to model664
     * @param line is the String to be read
     * @return model664 type of data
     */
    public model.Model664 read664(String line){
        
        /**
         * create new model664 data variable
         */
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
        
        /**
         * check if 128 characters are present
         */
        if(line.length() == 128){
            
            /**
             * get id type
             */
            String idType = line.substring(0, 3);
            
            /**
             * get article number
             */
            String articleNumber = line.substring(3, 35);
            
            /**
             * get article type
             */
            String articleType = line.substring(35, 39);
            
            /**
             * get size
             */
            String size = line.substring(39, 42);
            
            /**
             * get times
             */
            String times = line.substring(42, 44);
            
            /**
             * get empty space
             */
            String emptySpace = line.substring(44, 128);
            
            /**
             * check if read data is valid
             */
            if(idType.equals("664") && isNumber(size) && times.equals("ST") && isEmptySpace(emptySpace)){
                
                /**
                 * initialize model664 and save data to it
                 */
                model = new model.Model664();
                model.setItemNo(articleNumber);
                model.setItemType(articleType);
                model.setQuantity(Integer.valueOf(size));
                model.setTimes(times);
                
            }
            
        }

        return model;
        
    }
    
    /**
     * method to read String line and convert it to model669
     * @param line is the String to be read
     * @return model669 data type
     */
    public model.Model669 read669(String line){
        
        /**
         * create new model669 variable
         */
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
        
        /**
         * check if 128 characters present
         */
        if(line.length() == 128){
            
            /**
             * get id type
             */
            String idType = line.substring(0, 3);
            
            /**
             * get size
             */
            String size = line.substring(3, 10);
            
            /**
             * get empty space
             */
            String emptySpace = line.substring(10, 128);
            
            /**
             * check if read data is valid
             */
            if(idType.equals(idType) && isNumber(size) && isEmptySpace(emptySpace)){
                
                /**
                 * initialize model669 and save data to it
                 */
                model = new model.Model669();
                model.setSize(Integer.valueOf(size));
                
            }
            
        }
 
        return model;
        
    }
    
    /**
     * method to verify if text is a valid number
     * @param text is the String to be validated
     * @return 
     */
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
    
    /**
     * method to verify that String is a valid Date variable
     * @param text is the String to be validated
     * @return boolean is the true/false status of the validation
     */
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
    
    /**
     * is the method to check if text is a valid time variable format
     * @param text is the String to be validated
     * @return boolean status of the validation
     */
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
    
    /**
     * method verifies if String contains all just empty space ' '
     * @param text is the String to be validated
     * @return is of boolean status
     */
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
