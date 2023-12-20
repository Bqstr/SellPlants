CREATE TABLE "shop" (
	"id"	integer NOT NULL,
	"email"	Text NOT NULL UNIQUE,
	"name"	TEXT NOT NULL,
	"adress"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"number"	TEXT NOT NULL,
	"image"	INTEGER NOT NULL,
	"fee"	TEXT NOT NULL,
	PRIMARY KEY("id")
);

INSERT INTO "shop"("email","name","adress","password","number","image","fee") VALUES
("abobus_shop@gmail.com", "abobus_shop", "Abay 07" ,"123", "+777",1,100 );





CREATE TABLE "user" (
	"id"	integer NOT NULL,
	"email"	Text NOT NULL UNIQUE,
	"name"	TEXT NOT NULL,
	"adress"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"number"	TEXT NOT NULL,
	PRIMARY KEY("id")
);

INSERT INTO "user"("email","name","adress","password","number","image","fee") VALUES
("user@gmail.com", "Beknur", "MyAdress" ,"123", "+778");




CREATE TABLE "plants" (
	"id"	INTEGER  NOT NULL,
	"name"	text NOT NULL,
	"description"	text NOT NULL,
	"image"	int NOT NULL,
	"price"	INTEGER NOT NULL,
	"quantity"	integer NOT NULL,
	"shop_id"	INTEGER no null,
	FOREIGN KEY("shop_id") REFERENCES "shop"("id"),
	PRIMARY KEY("id")
);

INSERT INTO "plants"("name","description","image","price","quantity","shop_id") values
("Romashka", "Veryy good plant actually" ,1,667,1,0);







CREATE TABLE "Orders" (
	"id"	INTEGER NOT NULL,
	"user_id"	integer NOT NULL,
	"plant_id"	INTEGER NOT NULL,
	"date"	INTEGER NOT NULL,
	"amount"	INTEGER NOT NULL,
	"price"	INTEGER NOT NULL,
	PRIMARY KEY("id"),
	FOREIGN KEY("plant_id") REFERENCES "plant"("id"),
	FOREIGN KEY("user_id") REFERENCES "user"("id")
);

INSERT INTO "Orders" ("user_id","plant_id","date","amount","price") values
(0, 0,0,10,999);




















