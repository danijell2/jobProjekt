/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* @author danijell258
* class for importing data from file and inserting it into database
*/
public class Import extends Commons{
    
    /**
    * constructor for initializing Import class
    * @param properties is a variable with user defined settings
    */
    public Import(Properties properties){
        super.setProperties(properties);
    }
    
    /**
    * method for importing files into database
    */
    public void startImport(){
        
        /**
        * display on screen what is the program doing
        */
        super.log("Starting importing files");
        
        /**
         * total number of failed imported files
         */
        int errorFiles = 0;
        
        /**
         * total number of successfully imported files
         */
        int successFiles = 0;

        /**
         * list of all retrieved files
         */
        String files[] = new File(String.valueOf(super.getProperties().get("importFolder"))).list();
        
        /**
         * check if files found
         */
        if(files != null && files.length > 0){

            /**
            * initializing controller for importing files
            */
            controller.ImportFile importFile = new controller.ImportFile(super.getProperties());
            
            /**
            * boolean variable to log if there is an error while importing files
            */
            boolean error = false;

            /**
            * going through all files
            */
            for(int i = 0; i < files.length; i++){

                /**
                * get single file
                */
                String file = files[i];
                
                /**
                * display on screen what is the program doing
                */
                super.log("Importing file "+file);
                
                /**
                * if file is of type txt
                */
                if(file.endsWith(".txt")){

                    /**
                     * get the exact file path
                     */
                    String location = String.valueOf(super.getProperties().get("importFolder"))+file;
                    
                    /**
                     * prepare transitionModel for storing data
                     */
                    model.TransitionModel transitionModel = null;

                    /**
                     * import models from file into transitional model
                     */
                    try {
                        super.log("Starting import of models");
                        transitionModel = importFile.importFile(location);
                        
                    } catch (IOException ex) {
                        super.log("Error while reading the file:"+ex);
                        error = true;
                    }

                    /**
                     * check if model exist
                     */
                    if(transitionModel != null){

                        /**
                        * convert to table1 model for sql database
                        */
                        model.Table1Model table1Model = convertToTable1Model(transitionModel, file);

                        /**
                        * initialize database for sql operation
                        */
                        database.Database db = new database.Database(super.getProperties());
                        
                        /**
                        * perform insert operation and get tableModel data
                        */
                        table1Model = db.insertTable1(table1Model);

                        /**
                        * check if insert was successful
                        */
                        if(table1Model != null){
                            
                            /**
                             * display on screen what is the program doing
                             */
                            super.log("Successfully inserted data into table1.\n Now going to table2");
                            
                            /**
                             * convert to table2Model, get a list of all data for table2 of sql database
                             */
                            List<model.Table2Model> table2ModelList = convertToTable2ModelList(transitionModel.getModel664List(), table1Model);
                            
                            /**
                             * insert into database all table2 data and get inserted table2 data list
                             */
                            table2ModelList = db.insertTable2ModelList(table2ModelList);
                            
                            /**
                             * check if insert was successful
                             */
                            if(table2ModelList != null){
                                super.log("Successfully inserted data into table2");
                                
                                /**
                                 * move file to archive folder
                                 */
                                moveToArchiveFolder(String.valueOf(super.getProperties().get("archiveFolder")), file, location);
                                
                                /*
                                * count successful file
                                */
                                successFiles++;
                             /**
                              * if there is an error in sql operation show the message on the screen
                              */   
                            }else{
                                super.log("error trying to insert into table2.\n Skipping this file");
                                error = true;
                            }
                         
                         /**
                         * if there is an error in sql operation show the message on the screen
                         */
                        }else{
                            super.log("Error trying to insert data into table1");
                            error = true;
                        }
                     /**
                      *  if data couldn't be imported from the file show the message on the screen
                      */   
                    }else{
                        error = true;
                        super.log("Error in importing all required models");
                    }
                    
                    /**
                     * if error during file reading, transfer the file to error folder
                     */
                    if(error){
                        moveToErrorFolder(String.valueOf(super.getProperties().get("errorFolder")), file, location);
                        
                        // count errorFile file
                        errorFiles++;
                        
                    }
                 
                 /**
                  * if file is not txt format show the messsage on the screen
                  */
                }else{
                    
                    super.log("File not txt format, skipping file");
                    // count successfull file
                    errorFiles++;
                    
                }
                
            }
         
         /**
          * if files not found in the user defined folder show the message on the screen
          */
        }else{
            super.log("No files found, please check import folder path");
        }
        
        /**
         * show at the end of files import how many were successful and how many failed
         */
        super.log("Imported successfully "+successFiles+" files, while "+errorFiles+" failed");
        
    }
    
    /**
     * 
     * @param archiveFolder the location of the archive folder
     * @param filename name of the current file being imported
     * @param location is the current location of the file in import folder
     */
    private void moveToArchiveFolder(String archiveFolder, String filename, String location){
        
        /**
         * final path with the path and the filename together
         */
        String newLocation = String.valueOf(archiveFolder+filename);
        
        /**
         * initialize file with old location
         */
        File oldLocationFile = new File(location);
        
        /**
         * boolean status of the file being moved
         * move file from import folder to the archive folder
         */
        boolean isMoved = oldLocationFile.renameTo(new File(newLocation));
        
        /**
         * check if file was successfully moved
         */
        if(isMoved){
            super.log("File "+filename+" moved to archive folder");
        }
        
        /**
         * if moving wasn't successful log and show the message on the screen
         */
        else{
            super.log("Error trying to move "+filename+" file");
        }
        
    }
    
    /**
     * 
     * @param errorFolder location of the error folder
     * @param filename name of the file being moved
     * @param oldLocation current location of the file in import folder
     */
    private void moveToErrorFolder(String errorFolder, String filename, String oldLocation){
        
        /**
         * set new location with the error folder path and the filename
         */
        String newLocation = errorFolder+filename;
        
        /**
         * create file variable for the file being moved with the information where is it currently located
         */
        File oldLocationFile = new File(oldLocation);
        
        /**
         * boolean variable status of the moving operation
         * move the file to new location
         */
        boolean isMoved = oldLocationFile.renameTo(new File(newLocation));
        
        /**
         * if file moving was successful log and show it on the screen
         */
        if(isMoved){
            super.log("File "+filename+" moved to error folder");
        }
        
        /**
         * if file moving wasn't successful log and show it on the screen
         */
        else{
            super.log("Error trying to move "+filename+" file");
        }
        
    }

    /**
     * method to convert data from transitionalModel to table1Model for sql database
     * @param transitionModel model where initially the imported data from files is being held
     * @param filename name of the file from which the imported data is origination
     * @return table1Model which was created from transitionalModel
     */
    private static model.Table1Model convertToTable1Model(model.TransitionModel transitionModel, String filename){

        model.Table1Model table1Model = new model.Table1Model();
        table1Model.setCommodity(transitionModel.getModel662().getCommodity());
        table1Model.setCustomer(transitionModel.getModel661().getCustomer());
        table1Model.setTransmission(LocalDateTime.of(transitionModel.getModel661().getSendDate(), transitionModel.getModel661().getSendTime()));
        table1Model.setFilname(filename);
        table1Model.setOrderNo(transitionModel.getModel662().getOrderNo());
        table1Model.setReferenceNo(transitionModel.getModel662().getReferenceNo());
        table1Model.setSupplier(transitionModel.getModel661().getSupplier());
        
        return table1Model;
        
    }
    
    /**
     * method to convert to table2Model data for sql database
     * @param list is a list of model664 which are the main part of table2Model data
     * @param table1Model contains important information id for creating table2Model data
     * @return a list of model664 data type
     */
    private static List<model.Table2Model> convertToTable2ModelList(List<model.Model664> list, model.Table1Model table1Model){
        
        /*
        
        MESSAGEID eindeutige NachrichtenID
POSITION fortlaufende Positionsnummer zur NachrichtenID (ab 1)
ITEMNO Artikelnummer
QUANTITY bestellte Menge
ITEMTYPE Teileart
        
        */
        
        List<model.Table2Model> table2ModelList = new ArrayList();
        
        for(int i = 0; i < list.size(); i++){
            
            model.Table2Model model = new model.Table2Model();
            model.setItemNo(list.get(i).getItemNo());
            model.setItemType(list.get(i).getItemType());
            model.setMessageId(table1Model.getMessageId());
            model.setQuantity(list.get(i).getQuantity());
            model.setPosition(i+1);
            table2ModelList.add(model);
            
        }
        
        return table2ModelList;
        
    }
    
}
