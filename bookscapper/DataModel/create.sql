
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS sell;
DROP TABLE IF EXISTS purchase;

CREATE TABLE categories(
	_id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT
);



CREATE TABLE books(
	_id INTEGER PRIMARY KEY AUTOINCREMENT, 
	category_id INTEGER,
	name TEXT, 
	price FLOAT,
	condition TEXT
);

CREATE TABLE purchase(
	book_id INTEGER,
	purchase_time TIMESTAMP
);

CREATE TABLE sell(
	book_id INTEGER,
	sell_time TIMESTAMP
);

CREATE TABLE users(
	_id INTEGER  PRIMARY KEY AUTOINCREMENT,
	first_name TEXT,
	last_name TEXT,
	username TEXT,
	password TEXT
);

CREATE TABLE carts(
	user_id INTEGER,
	book_id INTEGER
);



INSERT INTO users(first_name, last_name, username, password) VALUES
	('chetan', 'nimbalkar', 'chetan', '234a4f23b35ce'),
	('champ', 'nimbalkar', 'champ', '234abef34239fa');

INSERT INTO categories(name) VALUES
         ("Action and Adventure"),
         ("Anthologies"),
         ("Art"),
         ("Autobiographies"),
         ("Biographies"),
         ("Children's"),
         ("Comics"),
         ("Cookbooks"),
         ("Diaries"),
         ("Dictionaries"),
         ("Drama"),
         ("Encyclopedia"),
         ("Educational"),
         ("Fantasy"),
         ("Guide"),
         ("History"),
         ("Horror"),
         ("Math"),
         ("Mystery"),
         ("Poetry"),
         ("Prayer books"),
         ("Religious"),
         ("Romance"),
         ("Satire"),
         ("Science"),
         ("Science Fiction"),
         ("Self help"),
         ("Series"),
         ("Travel"),
         ("Trilogies"),
         ("Fictional");

INSERT INTO books(category_id, name, price, condition) VALUES
	(1, 'sherlock holmes', 20.0, 'OLD'),
	(2, 'harry potter', 30.0, 'NEW');

INSERT INTO sell VALUES
	(1, time()),
	(2, time());

INSERT INTO purchase VALUES
	(1, time()),
	(2, time());
