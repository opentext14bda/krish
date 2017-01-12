CREATE TABLE DB2ADMIN.CUSTOMERS (
	C_ID INTEGER NOT NULL,
	C_NAME VARCHAR(255),
	C_SALARY INTEGER,
	PRIMARY KEY (C_ID)
);



CREATE TABLE DB2ADMIN.ITEMS (
	I_ID INTEGER NOT NULL,
	I_NAME VARCHAR(255),
	I_COST INTEGER,
	C_ID INTEGER,
	PRIMARY KEY (I_ID)
);

ALTER TABLE DB2ADMIN.ITEMS
	ADD FOREIGN KEY (C_ID) 
	REFERENCES CUSTOMERS (C_ID);
