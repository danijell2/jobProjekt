/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author danijell258
 * class for setting up the database connection with imported parameters
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
    
    /**
     * method for creating a database connection
     * @return 
     */
    public Connection conn(){
        
        /**
         * create a new connection variable
         */
        Connection conn = null;
        
        try {
            
            /**
             * load the driver for sql connection -- in this case mysql
             */
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            /**
             * get user defined settings for connection
             */
            Properties settings = (Properties) getProperties().get("connection");
            
            /**
             * get username for mysql connection
             */
            String username = String.valueOf(settings.get("username"));
            
            /**
             * get password  for mysql connection
             */
            String password = String.valueOf(settings.get("password"));
            
            /**
             * get url path for mysql connection
             */
            String path = String.valueOf(settings.get("path"));
            
            /**
             * establish a connection to the database
             */
            conn = (Connection) DriverManager.getConnection(path, username, password);
            
            /**
             * create statement with the connection
             */
            conn.createStatement();
            
            // Class.forName("org.h2.Driver");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            super.log("Error while trying to establish connection to database: "+String.valueOf(ex));
        }
        
        return conn;
        
    }
    
    /**
     * method to verify that connection to the database is working
     * @param properties are the user defined settings for the database connection
     * @return boolean status of the connection
     */
    public boolean isWorking(Properties properties){
        
        super.setProperties(properties);
        
        boolean state = false;
        
        try(Connection conn = conn()){
            
            if(conn != null){
                state = true;
            }
            
        } catch (SQLException ex) {
            super.log("Error while trying to establish connection to database: "+String.valueOf(ex));
        }
        
        return state;
        
    }
    
    @Override
    public void log(String text){
        super.log(text);
    }
    
}
