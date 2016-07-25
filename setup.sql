
use test;
create table contacts
(
	phone long not null,
    name varchar (255)
);

INSERT INTO contacts VALUES (9876543210, 'Zara Ali');
INSERT INTO contacts VALUES (9753124680, 'Harry Potter');
INSERT INTO contacts VALUES (9988776655, 'John Snow');
INSERT INTO contacts VALUES (0123456789, 'Pikachu');
	
create table transactions
(
   name varchar (255),
   time varchar (255),
   otp varchar(255)
);