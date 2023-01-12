/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package controller;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author danijell258
 * class for starting rescan/reindex of files in a folder
 */
public class PeriodicScan extends Commons{
    
    /**
     * constructor to initialize PeriodicScan class
     * @param properties is the required properties variable with user settings
     */
    public PeriodicScan(Properties properties){
        super.setProperties(properties);
    }
    
    /**
     * sceduler is type of class variable which enables periodic execution of defined tasks
     */
    private ScheduledExecutorService scheduler;
    
    /**
     * method to start the task for scheduler
     * @param properties are the imported settings defined by the user
     */
    public void startListening(Properties properties){
        
        /**
         * set how many threads to be keps alive
         */
        scheduler = Executors.newScheduledThreadPool(2);
        
        /**
         * runnable class for the task to be performed
         */
        ScanRunnable scan = new ScanRunnable();
        
        /**
         * set properties to runnabel scan class
         */
        scan.setProperties(properties);
        
        /**
         * get the user defined time for scanning of the folder
         */
        String time = String.valueOf(properties.get("timeInterval"));
        
        /**
         * initialize task scheduler and set it to the user defined time
         */
        scheduler.scheduleAtFixedRate(scan, 0, Integer.valueOf(time), TimeUnit.MINUTES);
        
    }
    
}
