/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * class to store model669 data
 */
public class Model669 {
    
    /*
    
    
# example                                                                               
6690000005                                                                                                                      

id   sizeOf664 emptySpace
669  0000005

# details
- id, number, starts at 1 - ends at 3
- sizeOf664, number, starts at 4 - ends at 10
- emptySpace, number, starts at 11 - ends at 128

#
'669' 1 3 numerisch Kennzeichen des 669-Satzes
Anz.664 4 10 numerisch Anzahl der 664er-Sätze in dieser Datei
(leer) 11 128 alphanumerisch konstant ' ' (Leerzeichen) 

    
    */
    
    /**
     * Default constructor
     */
    public Model669(){
        
    }
    
    /**
     * Identification number (669, file position from 1 to 3)
     */
    private int id;
    
    /**
     * Size of all 664 items (file position 4 to 10)
     */
    private int size;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getSize(){
        return size;
    }
    
    public void setSize(int size){
        this.size = size;
    }
    
}
