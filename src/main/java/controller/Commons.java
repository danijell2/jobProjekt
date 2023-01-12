/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *@author daijell258
 * class for common methods used through the lifetime of the program 
 */
public class Commons {
    
    /**
     * variable to save all user settings
     * @param properties variable where all user defined data is stored
     */
    private Properties properties;
    
    /**
     * setter method for properties variable
     * @param properties variable where all user defined data is stored
     */
    public void setProperties(Properties properties){
        this.properties = properties;
    }
    
    /**
     * getter method for properties variable
     * @param  properties variable where all user defined data is stored
     * @return properties returns the properties variable
     */
    public Properties getProperties(){
        return this.properties;
    }

    /**
     * method for print out in terminal message to the user and save the message to user specified log folder
     * @param message user defined String that is being logged
     * @return properties returns the properties variable
     */
    public void log(String message){

        try {
            
            String file = String.valueOf(properties.getProperty("logFolder"));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
            bufferedWriter.append(new Date()+": "+ message+"\n");
            bufferedWriter.close();
            
            System.out.println(message);
            
        } catch (IOException ex) {
            
            System.out.println("Error trying to write to log file. Please check the configuration to log folder and file. Error "+ex
            +"\n Cannot continue, terminating program");
            System.exit(0);
            
        }
        
        
    }
    
    /**
     * method which erases all data that existed in the log file and makes the log ready for reuse
     */
    public void resetLog(){

        try {
            
            String file = String.valueOf(properties.getProperty("logFolder"));
            PrintWriter reset = new PrintWriter(file);
            reset.print("");
            reset.close();

        } catch (IOException ex) {
            
            System.out.println("Error trying to write to log file. Please check the configuration to log folder and file. Error "+ex
            +"\n Cannot continue, terminating program");
            System.exit(0);
            
        }
        
        
    }
    
}
