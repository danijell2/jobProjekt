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
 *
 * @author danijell258
 */

public class Commons {
    
    private Properties properties;
    
    public void setProperties(Properties properties){
        this.properties = properties;
    }
    
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
    
    // delete everything from last log
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
