/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * model class for table1 in database
 */
public class Table1Model {
    
    /*
    MESSAGEID eindeutige NachrichtenID
FILENAME Dateiname der eingelesenen Datei
ORDERNO Bestellnummer
TRANSMISSION Übertragungsdatum und –zeit
SUPPLIER Lieferantennummer
CUSTOMER Kundennummer
REFERENCENO Kennnummer des Bauteils
COMMODITY Teileartgruppe des Bauteils
    
    */
    
    /**
     * Default constructor
     */
    public Table1Model(){
        
    }
    
    /**
     * Message id from sql table
     */
    private int messageId;
    
    /**
     * filename  from sql table
     */
    private String filename;
    
    /**
     * Order number from sql table
     */
    private int orderNo;
    
    /**
     * Date and time of the records from sql table
     */
    private LocalDateTime transmission;
    
    /**
     * Supplier number from sql table
     */
    private int supplier;
    
    /**
     * Customer number from sql table
     */
    private int customer;
    
    /**
     * Reference number from sql table
     */
    private long referenceNo;
    
    /**
     * Commodity type from sql table
     */
    private String commodity;
    
    /**
     * List of all table2 data for one table1 record
     */
    private List<model.Table2Model> table2List;
    
    public int getMessageId(){
        return messageId;
    }
    
    public void setMessageId(int messageId){
        this.messageId = messageId;
    }
    
    public String getFilename(){
        return filename;
    }
    
    public void setFilname(String filename){
        this.filename = filename;
    }
    
    public int getOrderNo(){
        return orderNo;
    }
    
    public void setOrderNo(int orderNo){
        this.orderNo = orderNo;
    }
    
    public LocalDateTime getTransmission(){
        return transmission;
    }
    
    public void setTransmission(LocalDateTime transmission){
        this.transmission = transmission;
    }
    
    public int getSupplier(){
        return supplier;
    }
    
    public void setSupplier(int supplier){
        this.supplier = supplier;
    }
    
    public int getCustomer(){
        return customer;
    }
    
    public void setCustomer(int customer){
        this.customer = customer;
    }
    
    public long getReferenceNo(){
        return referenceNo;
    }
    
    public void setReferenceNo(long referenceNo){
        this.referenceNo = referenceNo;
    }
    
    public String getCommodity(){
        return commodity;
    }
    
    public void setCommodity(String commodity){
        this.commodity = commodity;
    }
    
    public List<model.Table2Model> getTable2List(){
        return table2List;
    }
    
    public void setTable2List(List<model.Table2Model> table2List){
        this.table2List = table2List;
    }
    
}
