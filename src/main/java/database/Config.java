/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danijell258
 */

public class Config {
    
    private Properties settings;
    
    public Properties getSettings(){
        return settings;
    }
    
    public void setSettings(Properties settings){
        this.settings = settings;
    }
    
    public Connection conn(){
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:8080/jobProjekt", "root", "123");
            conn.createStatement();

           // Class.forName("org.h2.Driver");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
        
    }
    
}
