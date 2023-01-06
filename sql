


## table1

    create database job_projekt;
    use database job_projekt;
    
    create table table1(messageId int not null auto_increment, orderNo int not null, transmission timestamp not null, supplier int not null, customer int not null, 
    referenceNo int not null, commodity varchar(255) not null,primary key(id));
    
    create table table2(messageId int not null);

private int messageId;
    private int position;
    private String itemNo;
    private int quantity;
    private String itemType;


Tabelle 2:
MESSAGEID eindeutige NachrichtenID
POSITION fortlaufende Positionsnummer zur NachrichtenID (ab 1)
ITEMNO Artikelnummer
QUANTITY bestellte Menge
ITEMTYPE Teileart
Beide Tabellen sollen über das Feld MESSAGEID in einer 1:n-Relation verbunden 
