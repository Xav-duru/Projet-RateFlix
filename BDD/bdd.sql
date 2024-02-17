CREATE TABLE User ( 
	userId SMALLINT , 
	username VARCHAR(20) , 
	password VARCHAR(20) , 
	email VARCHAR(50),
	birthdate DATE,
	admin BOOLEAN,
	PRIMARY KEY(userId)
 ) ;

CREATE TABLE Movie ( 
	movieId SMALLINT , 
	name VARCHAR(20) , 
	director VARCHAR(20) , 
	average DOUBLE,
	releaseYear SMALLINT,
	image VARCHAR(20),
	PRIMARY KEY(movieId)
 ) ;

CREATE TABLE Notice ( 
	noticeId SMALLINT , 
	type VARCHAR(20) , 
	comment VARCHAR(20) , 
	date DATE, 
	PRIMARY KEY(noticeId)
 ) ;

CREATE TABLE Evaluate ( 
	adviceId SMALLINT , 
	userId SMALLINT, 
	movieId SMALLINT,	
	PRIMARY KEY(adviceId),
	FOREIGN KEY(userId) REFERENCES User(userId),
	FOREIGN KEY(movieId) REFERENCES Movie(movieId)
 ) ;


INSERT INTO Movie VALUES (1, "MAMMA MIA !", "Phyllida Lloyd", 4, 2008, "Mamma Mia !.jpg");

INSERT INTO Movie VALUES (2, "American Sniper", "Clint Eastwood", 4.5, 2014, "American Sniper.jpg");

INSERT INTO Movie VALUES (3, "Fight Club", "David Fincher", 3.5, 1999, "Fight Club.jpg");

INSERT INTO Movie VALUES (4, "Titanic", "James Cameron", 4.7, 1997, "Titanic.jpg");


INSERT INTO User VALUES (1, "Albane", "Carteron", "albane.carteron@gmail.com", "2002-02-08", TRUE);

INSERT INTO User VALUES (2, "Xavier", "Durumain", "xavier.durumain@gmail.com", "2002-03-30", TRUE);

INSERT INTO User VALUES (3, "Eugenie", "Rocheron", "eugenie.rocheron@gmail.com", "2002-03-07", FALSE);

INSERT INTO User VALUES (4, "Jules", "Potiron", "jules.potiron@gmail.com", "2002-01-01", FALSE);