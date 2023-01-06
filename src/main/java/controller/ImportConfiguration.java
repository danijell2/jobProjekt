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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Projekt;

/**
 *
 * @author danijell258
 */

public class ImportConfiguration {
    
    public static Properties importConfiguration(){
        
        Properties properties = null;
        String importFolder = null;
        String archiveFolder = null;
        String errorFolder = null;
        
        try {
            
            // import configuration
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("./configuration")));
            String line;
            
            
            while((line = bufferedReader.readLine()) != null){
                
                if(line.contains("import_folder=") && (importFolder = line.substring(line.indexOf("import_folder=")+14)).length() > 1){
                    
                    importFolder = importFolder.substring(1);
                    
                }else if(line.contains("archive_folder=") && (archiveFolder= line.substring(line.indexOf("archive_folder=")+15)).length() > 1){
                    
                    archiveFolder = archiveFolder.substring(1);
                    
                }else if(line.contains("error_folder=") && (errorFolder = line.substring(line.indexOf("error_folder=")+13)).length() > 1){
                    
                    errorFolder = errorFolder.substring(1);
                    
                }
                
            }
            
            bufferedReader.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // validate imported data
        if(importFolder != null && archiveFolder != null && errorFolder != null){
            
            properties = new Properties();
            properties.put("importFolder", importFolder);
            properties.put("archiveFolder", archiveFolder);
            properties.put("errorFolder", errorFolder);
            
        }
        
        return properties;
        
    }
    
}
