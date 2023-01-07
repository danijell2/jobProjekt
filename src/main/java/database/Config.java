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

public class Config extends controller.Commons{

    @Override
    public Properties getProperties(){
        return super.getProperties();
    }
    
    @Override
    public void setProperties(Properties properties){
        super.setProperties(properties);
    }
    
    public Connection conn(){
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties settings = (Properties) getProperties().get("connection");
            String username = String.valueOf(settings.get("username"));
            String password = String.valueOf(settings.get("password"));
            String path = String.valueOf(settings.get("path"));
            
            conn = (Connection) DriverManager.getConnection(path, username, password);
            conn.createStatement();
            
            // Class.forName("org.h2.Driver");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            super.log(String.valueOf(ex));
        }
        
        return conn;
        
    }
    
    public boolean isWorking(Properties properties){
        
        super.setProperties(properties);
        
        boolean state = false;
        
        try(Connection conn = conn()){
            
            if(conn != null){
                state = true;
            }
            
        } catch (SQLException ex) {
            super.log(String.valueOf(ex));
        }
        
        return state;
        
    }
    
    @Override
    public void log(String text){
        super.log(text);
    }
    
}
