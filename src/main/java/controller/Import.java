/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danijell258
 */

public class Import {
    
    public static void startImport(Properties properties){
        
        // get list of files
        String files[] = new File(String.valueOf(properties.get("import_folder"))).list();
        controller.ImportFile importFile = new controller.ImportFile();
        
        // go through all files
        for(int i = 0; i < files.length; i++){
            
            // check if file is txt
            String file = files[i];
            
            if(file.endsWith(".txt")){
                
                // import file
                String location = String.valueOf(properties.get("import_folder"))+file;
                model.TransitionModel transitionModel = null;
                
                try {
                    
                    transitionModel = importFile.importFile(location);
                    
                } catch (IOException ex) {
                    Logger.getLogger(Import.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // check if model exist
                if(transitionModel != null){
                    
                    // convert to table1 model
                    model.Table1Model table1Model = new model.Table1Model();
                    table1Model.setCommodity(transitionModel.getModel662().getCommodity());
                    table1Model.setCustomer(transitionModel.getModel661().getCustomer());
                    table1Model.setTransmission(LocalDateTime.of(transitionModel.getModel661().getSendDate(), transitionModel.getModel661().getSendTime()));
                    table1Model.setFilname(file);
                    table1Model.setOrderNo(transitionModel.getModel662().getOrderNo());
                    table1Model.setReferenceNo(transitionModel.getModel662().getReferenceNo());
                    table1Model.setSupplier(transitionModel.getModel661().getSupplier());

                    // insert into database
                    database.Database db = new database.Database((Properties) properties.get("connection"));
                    db.insert
                    
                }
                
            }
            
        }
        
    }
    
}
