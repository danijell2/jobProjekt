/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Properties;

/**
 * class of Runnable type what is needed for ScheduledExecutorService
 */
public class ScanRunnable extends Commons implements Runnable{

    @Override
    public void run() {
        
        //super.setProperties(properties);
        super.log("Task scheduler is initiating new scan");
        
        controller.Import importContr = new controller.Import(super.getProperties());
        importContr.startImport();
        
        // show all records
        super.log("Getting all records from database");
        controller.ShowRecords records = new controller.ShowRecords(super.getProperties());
        records.show();
        
    }
    
    public void setProperties(Properties properties){
        super.setProperties(properties);
    }
    
}
