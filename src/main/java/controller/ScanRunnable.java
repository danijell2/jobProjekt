/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Properties;

/**
 *
 * @author danijell258
 */
public class ScanRunnable implements Runnable{

    private Properties properties;
    
    @Override
    public void run() {
        
        System.out.println("Task scheduler is initiating new scan");
        controller.Import importContr = new controller.Import();
        importContr.startImport(properties);

    }
    
    public void setProperties(Properties properties){
        this.properties = properties;
    }
    
}
