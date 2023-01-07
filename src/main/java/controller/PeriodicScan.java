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
 *
 * @author danijell258
 */

public class PeriodicScan extends Commons{
    
    public PeriodicScan(Properties properties){
        super.setProperties(properties);
    }
    
    // set scheduled task
    private ScheduledExecutorService scheduler;
    
    public void startListening(Properties properties){
        
        scheduler = Executors.newScheduledThreadPool(2);
        ScanRunnable scan = new ScanRunnable();
        scan.setProperties(properties);
        
        // initialize task scheduler -- set every minute to rescan the folder
        String time = String.valueOf(properties.get("timeInterval"));
        scheduler.scheduleAtFixedRate(scan, 0, Integer.valueOf(time), TimeUnit.MINUTES);
        
    }
    
}
