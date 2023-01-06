/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package controller;

import java.io.File;
import java.io.IOException;
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
                    
                    // insert into database
                    
                }
                
            }
            
        }
        
    }
    
}
