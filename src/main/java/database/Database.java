/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author danijell258
 * class for sql database operations
 */
public class Database extends Config{
    
    /**
     * constructor to initialize the Database class
     * @param properties are user defined settings
     */
    public Database(Properties properties){     
        super.setProperties(properties);
    }
    
    /**
     * method to perform insert sql operation
     * @param table1 are the data to be inserted into the database
     * @return table1Model after insert operation
     */
    public model.Table1Model insertTable1(model.Table1Model table1){

        /**
         * try to establish the connection
         */
        try(Connection conn = super.conn()){
            
            // verify date and time from record is not before or equal to last inserted date and time of record
            
            /**
             * sql statement to retrieve all data from sql
             */
            String sql = "select * from table1 order by transmission desc limit 1";
            
            /**
             * prepared statement with sql
             */
            PreparedStatement ps = conn.prepareStatement(sql);
            
            /**
             * result set to retrieve data from the sql
             */
            ResultSet rs = ps.executeQuery();
            
            /**
             * variable to store last time when insert of record was made
             */
            Timestamp lastTime = null;
            
            /**
             * get insert date
             */
            while(rs.next()){
                lastTime = rs.getTimestamp("transmission");
            }
            
            /**
             * create boolean variable for validation
             */
            boolean valid = false;
            
            /**
             * lastTime is not present -- no records
             */
            if(lastTime == null){
                valid = true;
             
             // in case the record is present compare the time between current record to be inserted
            }else{
                valid = table1.getTransmission().isAfter(lastTime.toLocalDateTime());
            }
            
            /**
             * if record is newer than the last record in the database continue with the insert operation
             */
            if(valid){
                
                /**
                 * sql statement
                 */
                sql = "insert into table1(orderNo, transmission, supplier, customer, referenceNo, commodity, filename) values(?, ?, ?, ?, ?, ?, ?)";
                
                /**
                 * prepared statement with return generated id
                 */
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                /**
                 * setting the data to be inserted with prepared statement
                 */
                ps.setInt(1, table1.getOrderNo());
                ps.setTimestamp(2, Timestamp.valueOf(table1.getTransmission()));
                ps.setInt(3, table1.getSupplier());
                ps.setInt(4, table1.getCustomer());
                ps.setLong(5, table1.getReferenceNo());
                ps.setString(6, table1.getCommodity());
                ps.setString(7, table1.getFilename());
                
                /**
                 * execute insert and store the returned 0/1 variable -- true/false
                 */
                int count = ps.executeUpdate();
                
                /**
                 * prepare rs for getting new generated id
                 */
                rs = ps.getGeneratedKeys();
                
                /**
                 * id variable
                 */
                int id;
                
                /**
                 * check if insert was successful
                 */
                if(count > 0){
                    
                    /**
                     * get new generated id
                     */
                    while(rs.next()){
                        table1.setMessageId(rs.getInt(1));
                    }
                    
                }
                
                /**
                 * close the connection to the database
                 */
                conn.close();
                
            }else{
                super.log("Date and time cannot be before last inserted record which is "+lastTime.toString());
                table1 = null;
            }
            
        }catch(SQLException ex){
            
            table1 = null;
            super.log(String.valueOf(ex));
            
            if(ex.getErrorCode() == 1062){
                super.log(String.valueOf("Filename already in database, skipping this file"));
                System.out.println("");
            }
            
        }

        return table1;
        
    }
    
    /**
     * method to insert data into table2
     * @param list is a list of all table2Model data to be inserted
     * @return table2Model list after insert
     */
    public List<model.Table2Model> insertTable2ModelList(List<model.Table2Model> list){
        
        try(Connection conn = super.conn()){
            
            for(int i = 0; i < list.size(); i++){
                
                String sql = "insert into table2(messageId, position, itemNo, quantity, itemType) values(?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                model.Table2Model table2Model = list.get(i);
                ps.setInt(1, table2Model.getMessageId());
                ps.setInt(2, table2Model.getPosition());
                ps.setString(3, table2Model.getItemNo());
                ps.setInt(4, table2Model.getQuantity());
                ps.setString(5, table2Model.getItemType());
                ps.executeUpdate();

            }
            
            conn.close();
            
        }catch(SQLException ex){
            super.log(String.valueOf(ex));
            list = null;
        }
        
        return list;
        
    }
    
    /**
     * method to get all data from table1
     * @return  list of table1 data
     */
    public List<model.Table1Model> getTable1List(){
        
        List<model.Table1Model> table1List = null;
        
        try(Connection conn = super.conn()){
            
            String sql = "select * from table1 order by transmission desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            table1List = new ArrayList();
            
            while(rs.next()){
                
                model.Table1Model table1 = new model.Table1Model();
                table1.setCommodity(rs.getString("commodity"));
                table1.setCustomer(rs.getInt("customer"));
                table1.setFilname(rs.getString("filename"));
                table1.setMessageId(rs.getInt("messageId"));
                table1.setOrderNo(rs.getInt("orderNo"));
                table1.setReferenceNo(rs.getLong("referenceNo"));
                table1.setSupplier(rs.getInt("supplier"));
                table1.setTransmission(rs.getTimestamp("transmission").toLocalDateTime());
                
                table1List.add(table1);
                
            }   
            
            /**
             * check if table1 list is not empty
             */
            if(table1List.size() > 0){
                
                /**
                 * iterate through the list
                 */
                for(int i = 0; i < table1List.size(); i++){
                    
                    // get data
                    sql = "select * from table2 where messageId = ? order by position asc";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, table1List.get(i).getMessageId());
                    rs = ps.executeQuery();
                    List<model.Table2Model> table2List = new ArrayList();
                    
                    while(rs.next()){
                        
                        model.Table2Model table2 = new model.Table2Model();
                        table2.setItemNo(rs.getString("itemNo"));
                        table2.setItemType(rs.getString("itemType"));
                        table2.setMessageId(rs.getInt("messageId"));
                        table2.setPosition(rs.getInt("position"));
                        table2.setQuantity(rs.getInt("quantity"));
                        table2List.add(table2);
                        
                    }
                    
                    table1List.get(i).setTable2List(table2List);
                    
                }
                
            }
            
        }catch(SQLException ex){
            super.log(String.valueOf(ex));
        }
        
        return table1List;
        
    }

}

/*

MESSAGEID eindeutige NachrichtenID
POSITION fortlaufende Positionsnummer zur NachrichtenID (ab 1)
ITEMNO Artikelnummer
QUANTITY bestellte Menge
ITEMTYPE Teileart

*/
