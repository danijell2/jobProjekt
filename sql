



create database job_projekt;
use job_projekt;

# table 1    
create table table1(messageId int not null auto_increment, orderNo int not null, transmission timestamp not null, supplier int not null, customer int not null, 
referenceNo bigint not null, commodity varchar(255) not null, filename varchar(255) not null, primary key(messageId));

# table 2    
create table table2(messageId int not null, position int not null, itemNo varchar(255) not null, quantity int not null, itemType varchar(255) not null, foreign key(messageId) references table1(messageId) on update cascade on delete cascade, constraint uniquePosition unique (messageId, position));
