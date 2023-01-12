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
* @author danijell258 
* class for importing configuration file where user can specify settings of the program
*/
public class ImportConfiguration extends Commons{
    
    /**
     * method to import configuration settings defined by user
     * @return properties containing all the user defined values
     */
    public Properties importConfiguration(){
        
        /**
         * show message on the screen
         */
        System.out.println("Starting configuration import");
        
        /**
         * properties variable where all user settings are being stored
         */
        Properties properties = null;
        
        /**
         * location of the import folder
         */
        String importFolder = null;
        
        /**
         * location of the archive folder
         */
        String archiveFolder = null;
        
        /**
         * location of the error folder
         */
        String errorFolder = null;
        
        /**
         * username for mysql connection
         */
        String username = null;
        
        /**
         * password for mysql connection
         */
        String password = null;
        
        /**
         * port for mysql connection
         */
        String port = null;
        
        /**
         * url path to mysql connection
         */
        String path = null;
        
        /**
         * location of the log folder
         */
        String logFolder = null;
        
        /**
         * time interval how many minutes to rescan the import folder
         */
        String timeInterval = null;
        
        try {
            
            /**
             * bufferedReader is being used to read the defined file
             */
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("configuration")));
            
            /**
             * line is the variable where current read line from bufferedReader is being stored
             */
            String line;
            
            /**
             * read line by line while not null
             */
            while((line = bufferedReader.readLine()) != null){

                /**
                 * check if import folder found
                 */
                if(line.contains("import_folder=") && (importFolder = line.substring(line.indexOf("import_folder=")+13)).length() > 1){
                    
                    /**
                     * show message on the screen
                     */
                    System.out.println("Reading import folder");
                    
                    /**
                     * get path to import folder
                     */
                    importFolder = importFolder.substring(1);
                    
                    /**
                     * check if import folder is actually there
                     */
                    if(!isFolder(importFolder)){
                        importFolder = null;
                    }

                /**
                 * check if error folder found 
                 */
                }else if(line.contains("error_folder=") && (errorFolder = line.substring(line.indexOf("error_folder=")+12)).length() > 1){
                    
                    /**
                     * show message on the screen
                     */
                    System.out.println("Reading error folder");
                    
                    /**
                     * get file path to error folder
                     */
                    errorFolder = errorFolder.substring(1);
                    
                    /**
                     * check if error folder is actually there
                     */
                    if(!isFolder(errorFolder)){
                        errorFolder = null;
                    }
                    
                 /**
                  * check if archive folder found
                  */
                }else if(line.contains("archive_folder=") && (archiveFolder= line.substring(line.indexOf("archive_folder=")+14)).length() > 1){
                    
                    /**
                     * show message on the screen
                     */
                    System.out.println("Reading archive folder");
                    
                    /**
                     * get the file path of the archive folder
                     */
                    archiveFolder = archiveFolder.substring(1);
                    
                    /**
                     * check if archive folder actually exist, is there
                     */
                    if(!isFolder(archiveFolder)){
                        archiveFolder = null;
                    }
                 
                 /**
                  * if username for mysql connection has been found
                  */
                }else if(line.contains("connection_username=") && (username = line.substring(line.indexOf("connection_username=")+19)).length() > 1){
                    
                    /**
                     * show message to the screen
                     */
                    System.out.println("Reading username for database connection");
                    
                    /**
                     * save username for mysql connection
                     */
                    username = username.substring(1);
                 
                 /**
                  * if password for mysql connection has been found
                  */
                }else if(line.contains("connection_password=") && (password = line.substring(line.indexOf("connection_password=")+19)).length() > 1){
                    
                    /**
                     * show message to the screen
                     */
                    System.out.println("Reading password for database connection");
                    
                    /**
                     * save password for mysql connection
                     */
                    password = password.substring(1);
                 
                 /**
                  *  if port for mysql connection has been found
                  */
                }else if(line.contains("connection_port=") && (port = line.substring(line.indexOf("connection_port=")+15)).length() > 1){
                    
                    /**
                     * show message to the screen
                     */
                    System.out.println("Reading port to database connection");
                    
                    /**
                     * save port for the mysql connection (not in use currently)
                     */
                    port = port.substring(1);
                 
                 /**
                  * if url path for mysql connection has been found
                  */ 
                }else if(line.contains("connection_path=") && (path = line.substring(line.indexOf("connection_path=")+15)).length() > 1){
                    
                    /**
                     * show message to the screen
                     */
                    System.out.println("Reading path to database connection");
                    
                    /**
                     * save url to the mysql connection
                     */
                    path = path.substring(1);
                 
                 /**
                  * if log folder has been found
                  */
                }else if(line.contains("log_folder=") && (logFolder = line.substring(line.indexOf("log_folder=")+10)).length() > 1){
                    
                    /**
                     * show message to the screen
                     */
                    System.out.println("Reading log folder");
                    
                    /**
                     * save file path to the log folder
                     */
                    logFolder = logFolder.substring(1);
                    
                    /**
                     * create file variable out of log String path
                     */
                    File file = new File(logFolder);
                    
                    /**
                     * check if file with log folder exist
                     */
                    if(file != null && !file.isFile()){
                        logFolder = null;
                    }
                 
                 /**
                  * if time setting for rescaning of the import folder has been found
                  */
                }else if(line.contains("time_interval=") && (timeInterval = line.substring(line.indexOf("time_interval=")+14)).length() > 1){
                    
                    /**
                     * show message to the screen
                     */
                    System.out.println("Reading set time interval for reindexing of import folder");
                    
                    /**
                     * save the set time for rescan of the import folder
                     */
                    timeInterval = timeInterval.substring(1);
                    
                }
                
            }
            
            /**
             * close the buffered reader
             */
            bufferedReader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * show the message on the screen
         */
        System.out.println("Verifying imported configuration");
        
        /**
         * verify if created variables exist and valid
         */
        if(importFolder != null && archiveFolder != null && errorFolder != null && logFolder != null
                && username != null && password != null && port != null && path != null && timeInterval != null && isNumber(timeInterval)){

            /**
             * initialize properties variable and save settings
             */
            properties = new Properties();
            properties.put("importFolder", importFolder);
            properties.put("archiveFolder", archiveFolder);
            properties.put("errorFolder", errorFolder);
            properties.put("logFolder", logFolder);
            properties.put("timeInterval", timeInterval);

            /**
             * create addition properties variable for database configuration
             */
            Properties connection = new Properties();
            connection.put("username", username);
            connection.put("password", password);
            connection.put("port", port);
            connection.put("path", path);
            properties.put("connection", connection);
            
            /**
             * check if connection can be established to mysql database
             */
            if(new database.Config().isWorking(properties)){
                
                /**
                 * initialize log file
                 */
                super.setProperties(properties);
                super.resetLog();
                
                /**
                 * show message to the screen
                 */
                super.log("Configuration imported succesffully");
                
            }

             // if connection does not work exit
             else{
                
                /**
                 * initialize log file
                 */
                super.setProperties(properties);
                super.resetLog();
                
                /**
                 * show message to the screen
                 */
                super.log("Connection to database could not be established.\n Terminating program");
                
                /**
                 * exit the program
                 */
                System.exit(0);
                
            }
 
        }
        
        return properties;
        
    }
    
    /**
     * method to verify if a String path is actually a folder
     * @param path is the path to the user defined folder
     * @return boolean status if the folder has been found
     */
    private boolean isFolder(String path){
        
        boolean state = false;
        
        File file = new File(path);
        
        if(file.isDirectory()){
            state = true;
        }
        
        return state;
        
    }
    
    /**
     * method to verify if the text is a number of integer type
     * @param text String to be converted to Integer
     * @return boolean status if it is a valid number
     */
    private boolean isNumber(String text){
        
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
