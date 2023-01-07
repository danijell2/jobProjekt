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
        
        System.out.println("Starting configuration import");
        
        Properties properties = null;
        String importFolder = null;
        String archiveFolder = null;
        String errorFolder = null;
        String username = null;
        String password = null;
        String port = null;
        String path = null;
        String protocolFolder = null;
        String timeInterval = null;
        
        try {
            
            // import configuration
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("configuration")));
            String line;
            
            while((line = bufferedReader.readLine()) != null){
                
                if(line.contains("import_folder=") && (importFolder = line.substring(line.indexOf("import_folder=")+13)).length() > 1){
                    
                    importFolder = importFolder.substring(1);
                    
                }else if(line.contains("archive_folder=") && (archiveFolder= line.substring(line.indexOf("archive_folder=")+14)).length() > 1){
                    
                    archiveFolder = archiveFolder.substring(1);
                    
                }else if(line.contains("error_folder=") && (errorFolder = line.substring(line.indexOf("error_folder=")+12)).length() > 1){
                    
                    errorFolder = errorFolder.substring(1);
                    
                }else if(line.contains("connection_username=") && (username = line.substring(line.indexOf("connection_username=")+19)).length() > 1){
                    
                    username = username.substring(1);
                    
                }else if(line.contains("connection_password=") && (password = line.substring(line.indexOf("connection_password=")+19)).length() > 1){
                    
                    password = password.substring(1);
                    
                }else if(line.contains("connection_port=") && (port = line.substring(line.indexOf("connection_port=")+15)).length() > 1){
                    
                    port = port.substring(1);
                    
                }else if(line.contains("connection_path=") && (path = line.substring(line.indexOf("connection_path=")+15)).length() > 1){
                    
                    path = path.substring(1);
                    
                }else if(line.contains("protocol_folder=") && (protocolFolder = line.substring(line.indexOf("protocol_folder=")+15)).length() > 1){
                    
                    protocolFolder = protocolFolder.substring(1);
                    
                }else if(line.contains("time_interval=") && (timeInterval = line.substring(line.indexOf("time_interval=")+14)).length() > 1){
                    
                    timeInterval = timeInterval.substring(1);
                    
                }
                
            }
            
            bufferedReader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // validate imported data
        if(importFolder != null && archiveFolder != null && errorFolder != null && protocolFolder != null
                && username != null && password != null && port != null && path != null && timeInterval != null && isNumber(timeInterval)){
            
            properties = new Properties();
            properties.put("importFolder", importFolder);
            properties.put("archiveFolder", archiveFolder);
            properties.put("errorFolder", errorFolder);
            properties.put("protocolFolder", protocolFolder);
            properties.put("timeInterval", timeInterval);
            
            // save connection settings
            Properties connection = new Properties();
            connection.put("username", username);
            connection.put("password", password);
            connection.put("port", port);
            connection.put("path", path);
            properties.put("connection", connection);
            
            System.out.println("Configuration imported succesffully");
            
        }
        
        return properties;
        
    }
    
    private static boolean isNumber(String text){
        
        boolean state = false;
        
        try{
            
            int number = Integer.valueOf(text);
            state = true;
            
        }catch(NumberFormatException ex){
            System.out.println("Set time interval for folder scan not valid.\n terminating program");
            System.exit(0);
        }
        
        return state;
        
    }
    
}
