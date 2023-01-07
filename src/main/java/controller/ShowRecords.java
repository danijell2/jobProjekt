/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package controller;

import java.util.List;
import java.util.Properties;

/**
 *
 * @author danijell258
 */

public class ShowRecords extends Commons{

    public ShowRecords(Properties properties){
        super.setProperties(properties);
    }
    
    public void show(){
        
        // get all records
        database.Database db = new database.Database(super.getProperties());
        
        List<model.Table1Model> table1List = db.getTable1List();
        String text = "\n";
        // check if data exist in database
        if(table1List.size() > 0){
            
            super.log("Showing in descending order from last modified record");
            
            // show records
            for(int i = 0; i < table1List.size(); i++){
                
                model.Table1Model table1 = table1List.get(i);
                
                text += "messageId: "+table1.getMessageId()+"\n"
                        + "filename: "+table1.getFilename()+"\n"
                        + "commodity: "+table1.getCommodity()+"\n"
                        + "customer: "+table1.getCustomer()+"\n"
                        + "orderNo: "+table1.getOrderNo()+"\n"
                        + "referenceNo: "+table1.getReferenceNo()+"\n"
                        + "supplier: "+table1.getSupplier()+"\n"
                        + "transmission: "+table1.getTransmission()+"\n"
                        + "there are "+table1.getTable2List().size()+" items:\n";
                
                for(int j = 0; j < table1.getTable2List().size(); j++){
                    
                    model.Table2Model table2 = table1.getTable2List().get(j);
                    
                    text += "position: "+table2.getPosition()+"\n"
                            +"itemsNo: "+table2.getItemNo()+"\n"
                            +"itemType: "+table2.getItemType()+"\n"
                            +"quantity: "+table2.getQuantity()+"\n";
                    
                }
                
                text += "\n\n";
                
            }
            
            // print records
            super.log(text);
            
        }else{
            super.log("Database empty, nothing to display");
        }
        
    }
    
}
