/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Properties;

/**
 * @author danijell258
 * class of Runnable type what is needed for ScheduledExecutorService
 */
public class ScanRunnable extends Commons implements Runnable{

    /**
     * method to start the runnable class
     */
    @Override
    public void run() {
        
        /**
         * show message on the screen
         */
        super.log("Task scheduler is initiating new scan");
        
        /**
         * create and initialize the import controller
         */
        controller.Import importContr = new controller.Import(super.getProperties());
        
        /**
         * start the import process
         */
        importContr.startImport();
        
        /**
         * show message on the screen
         */
        super.log("Getting all records from database");
        
        /**
         * create and initialize controller for records from the database
         */
        controller.ShowRecords records = new controller.ShowRecords(super.getProperties());
        
        /**
         * show all records
         */
        records.show();
        
    }
    
    @Override
    public void setProperties(Properties properties){
        super.setProperties(properties);
    }
    
}
