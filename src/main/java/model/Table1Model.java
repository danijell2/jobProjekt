/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author danijell258
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
    
    public Table1Model(){
        
    }
    
    private int messageId;
    private String filename;
    private int orderNo;
    private LocalDateTime transmission;
    private int supplier;
    private int customer;
    private int referenceNo;
    private String commodity;
    
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
    
    public int getReferenceNo(){
        return referenceNo;
    }
    
    public void setReferenceNo(int referenceNo){
        this.referenceNo = referenceNo;
    }
    
    public String getCommodity(){
        return commodity;
    }
    
    public void setCommodity(String commodity){
        this.commodity = commodity;
    }
    
}
