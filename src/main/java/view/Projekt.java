/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danijell258
 */
public class Projekt {

    public static void main(String[] args) {
        
        view.Projekt projekt = new view.Projekt();
        projekt.start();
        
    }
    
    private void start(){
        
        // import configuration
        Properties properties = controller.ImportConfiguration.importConfiguration();
        
        if(properties != null){
            
            // set periodic scan
            System.out.println("Setting up task scheduler to scan every minute for new files");
            controller.PeriodicScan scan = new controller.PeriodicScan();
            scan.startListening(properties);
            
//            controller.Import importContr = new controller.Import();
//            importContr.startImport(properties);
            
//            importContr = null;
            
        }else{
            
            System.out.println("Configuration error");
            System.exit(0);
            
        }
 
    }
    
}
