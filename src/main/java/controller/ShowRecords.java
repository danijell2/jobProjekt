/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package controller;

import java.util.List;
import java.util.Properties;

/**
 * @author danijell258
 * class for showing all records from database to user
 */
public class ShowRecords extends Commons{

    /**
     * constructor to initialize ShowRecords 
     * @param properties are the user defined settings
     */
    public ShowRecords(Properties properties){
        super.setProperties(properties);
    }
    
    /**
     * method to show all records from the database
     */
    public void show(){
        
        /**
         * initialize the database controller
         */
        database.Database db = new database.Database(super.getProperties());
        
        /**
         * get a list of table1Model data from databsae
         */
        List<model.Table1Model> table1List = db.getTable1List();
        
        /**
         * create variable in which text for display on terminal will be stored
         */
        String text = "\n";
        
        /**
         * check if table1List list contains any records
         */
        if(table1List.size() > 0){
            
            /**
             * show message on the screen
             */
            super.log("Showing in descending order from last modified record");
            
            /**
             * iterate through the list
             */
            for(int i = 0; i < table1List.size(); i++){
                
                /**
                 * get current iteration of table1Model
                 */
                model.Table1Model table1 = table1List.get(i);
                
                /**
                 * add to text variable data from table1 model
                 */
                text += "messageId: "+table1.getMessageId()+"\n"
                        + "filename: "+table1.getFilename()+"\n"
                        + "commodity: "+table1.getCommodity()+"\n"
                        + "customer: "+table1.getCustomer()+"\n"
                        + "orderNo: "+table1.getOrderNo()+"\n"
                        + "referenceNo: "+table1.getReferenceNo()+"\n"
                        + "supplier: "+table1.getSupplier()+"\n"
                        + "transmission: "+table1.getTransmission()+"\n"
                        + "there are "+table1.getTable2List().size()+" items:\n";
                
                /**
                 * go through all table2 list for table1
                 */
                for(int j = 0; j < table1.getTable2List().size(); j++){
                    
                    /**
                     * get current table2 model from iteration
                     */
                    model.Table2Model table2 = table1.getTable2List().get(j);
                    
                    /**
                     * save the data from table2 into text variable
                     */
                    text += "position: "+table2.getPosition()+"\n"
                            +"itemsNo: "+table2.getItemNo()+"\n"
                            +"itemType: "+table2.getItemType()+"\n"
                            +"quantity: "+table2.getQuantity()+"\n";
                    
                }
                
                /**
                 * two new line so it can be recognized that a new table1 model is being displayed
                 */
                text += "\n\n";
                
            }
            
            /**
             * log and show everything to the screen
             */
            super.log(text);
            
        }else{
            super.log("Database empty, nothing to display");
        }
        
    }
    
}
