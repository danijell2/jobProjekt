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
 *
 * @author danijell258
 */

public class Import {
    
    public void startImport(Properties properties){
        
        System.out.println("Starting importing files");
        
        // get list of files
        String files[] = new File(String.valueOf(properties.get("importFolder"))).list();
        
        // check if files found
        if(files != null && files.length > 0){

            controller.ImportFile importFile = new controller.ImportFile();
            boolean error = false;
            
            // go through all files
            for(int i = 0; i < files.length; i++){
                
                // check if file is txt
                String file = files[i];
                
                if(file.endsWith(".txt")){
                    
                    // import file
                    String location = String.valueOf(properties.get("importFolder"))+file;
                    model.TransitionModel transitionModel = null;
                    
                    try {
                        
                        transitionModel = importFile.importFile(location);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Import.class.getName()).log(Level.SEVERE, null, ex);
                        error = true;
                    }
                    
                    // check if model exist
                    if(transitionModel != null){
                        
                        // convert to table1 model
                        model.Table1Model table1Model = convertToTable1Model(transitionModel, file);
                        
                        // insert into database
                        database.Database db = new database.Database((Properties) properties.get("connection"));
                        table1Model = db.insertTable1(table1Model);
                        
                        // check if insert successfull
                        if(table1Model != null){
                            
                            // message to user
                            System.out.println("Successfully inserted data into table1.\n Now going to table2");
                            
                            // convert to table2Model
                            List<model.Table2Model> table2ModelList = convertToTable2ModelList(transitionModel.getModel664List(), table1Model);
                            
                            // insert into table2
                            table2ModelList = db.insertTable2ModelList(table2ModelList);
                            
                            // check if successfull
                            if(table2ModelList != null){
                                System.out.println("Successfully inserted data into table2");
                                
                                // move file to archive folder
                                moveToArchiveFolder(String.valueOf(properties.get("archive_folder")), file, location);
                                
                            }else{
                                System.out.println("error trying to insert into table2.\n Skipping this file");
                                error = true;
                            }
                            
                        }else{
                            System.out.println("Error trying to insert data into table1");
                            error = true;
                        }
                        
                    }else{
                        error = true;
                    }
                    
                    // if error during file reading, transfer the file to error folder
                    if(error){
                        moveToErrorFolder(String.valueOf(properties.get("error_folder")), file, location);
                    }
                    
                }
                
            }
            
        }else{
            System.out.println("No files found, please check import folder path");
        }
        
    }
    
    private void moveToArchiveFolder(String archiveFolder, String filename, String location){
        
        String newLocation = String.valueOf(archiveFolder+filename);
        File oldLocationFile = new File(location);
        boolean isMoved = oldLocationFile.renameTo(new File(newLocation));
        
        if(isMoved){
            System.out.println("File "+filename+" moved to archive folder");
        }else{
            System.out.println("Error trying to move "+filename+" file");
        }
        
    }
    
    private void moveToErrorFolder(String deleteFolder, String filename, String oldLocation){
        
        // move file to archive folder
        String newLocation = deleteFolder+filename;
        File oldLocationFile = new File(oldLocation);
        boolean isMoved = oldLocationFile.renameTo(new File(newLocation));
        
        if(isMoved){
            System.out.println("File "+filename+" moved to error folder");
        }else{
            System.out.println("Error trying to move "+filename+" file");
        }
        
    }
    
    // convert to table1 model
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
    
    // convert to table2Model
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
            model.setQuantity(list.get(i).getQuantity()*list.get(i).getTimes());
            model.setPosition(i+1);
            table2ModelList.add(model);
            
        }
        
        return table2ModelList;
        
    }
    
}
