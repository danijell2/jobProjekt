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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author danijell258
 */
public class Database extends Config{
    
    public Database(Properties settings){     
        super.setSettings(settings);
    }
    
    public model.Table1Model insertTable1(model.Table1Model table1){

        boolean duplicate = false;
        
        try(Connection conn = super.conn()){
            
            String sql = "insert into table1(orderNo, transmission, supplier, customer, referenceNo, commodity) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, table1.getOrderNo());
            ps.setTimestamp(2, Timestamp.valueOf(table1.getTransmission()));
            ps.setInt(3, table1.getSupplier());
            ps.setInt(4, table1.getCustomer());
            ps.setLong(5, table1.getReferenceNo());
            ps.setString(6, table1.getCommodity());
            int count = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id;
            
            // check if insert successfull
            if(count > 0){
                
                // get id
                while(rs.next()){
                    table1.setMessageId(rs.getInt(1));
                }
                
            }   
            
            conn.close();
            
        }catch(SQLException ex){
            
            table1 = null;
            System.out.println(ex);
            
            if(ex.getErrorCode() == 1062){
                System.out.println("Filename already in database, skipping this file");
            }
            
        }

        return table1;
        
    }
    
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
            System.out.println(ex);
            list = null;
        }
        
        return list;
        
    }
    
}

/*

MESSAGEID eindeutige NachrichtenID
POSITION fortlaufende Positionsnummer zur NachrichtenID (ab 1)
ITEMNO Artikelnummer
QUANTITY bestellte Menge
ITEMTYPE Teileart

*/
