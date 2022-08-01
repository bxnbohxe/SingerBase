CREATE TABLE Country(
    country_name VARCHAR(30) NOT NULL,
    capital VARCHAR(20),
    continent VARCHAR(15) NOT NULL,
    population INTEGER,
	PRIMARY KEY(country_name)
);

CREATE TABLE Music_Group(
    group_name VARCHAR(30) NOT NULL,
	year_founded INT,
	PRIMARY KEY (group_name)
);

CREATE TABLE Singer(
    SID VARCHAR(7) NOT NULL,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    stage_name VARCHAR(50),
    gender VARCHAR(10),
    country VARCHAR(30) NOT NULL,
	group_name VARCHAR(30),
    PRIMARY KEY(SID),
	FOREIGN KEY (country) REFERENCES Country (country_name),
	FOREIGN KEY (group_name) REFERENCES Music_Group (group_name)
);

CREATE TABLE SB_User(
    user_id VARCHAR(5) NOT NULL,
    username VARCHAR(20) NOT NULL,
    gender VARCHAR(10),
    email VARCHAR(50) NOT NULL,
    mobile_no CHAR(10) NOT NULL,
	country VARCHAR(30),
    PRIMARY KEY(user_id),
	FOREIGN KEY (country) REFERENCES Country (country_name)
);

CREATE TABLE Album(
    AID VARCHAR(5) NOT NULL,
    SID VARCHAR(5) NOT NULL,
    title VARCHAR(20),
    genre VARCHAR(10),
    release_year INT,
    PRIMARY KEY(AID),
    FOREIGN KEY (SID) REFERENCES Singer (SID)
);

CREATE TABLE Song(
    song_id VARCHAR(5) NOT NULL,
    SID VARCHAR(5) NOT NULL, 
    AID VARCHAR(5),
    title VARCHAR(20),
    genre VARCHAR(10),
    release_year INT,
    PRIMARY KEY(song_id),
    FOREIGN KEY (SID) REFERENCES Singer (SID),
    FOREIGN KEY (AID) REFERENCES Album (AID)
);

CREATE TABLE Song_List(
	song_list_id VARCHAR(5) NOT NULL,
    user_id VARCHAR(5) NOT NULL,
	PRIMARY KEY (song_list_id),
    FOREIGN KEY (user_id) REFERENCES SB_User (user_id)
);

CREATE TABLE Song_Song_List_Junction(
	song_list_id VARCHAR(5) NOT NULL,
	song_id VARCHAR(5) NOT NULL,
	PRIMARY KEY (song_list_id, song_id),
	FOREIGN KEY (song_list_id) REFERENCES Song_List (song_list_id),
	FOREIGN KEY (song_id) REFERENCES Song (song_id)
);

CREATE TABLE Thread(
    TID VARCHAR(5) NOT NULL,
    topic VARCHAR(50) NOT NULL,
    user_id VARCHAR(5) NOT NULL,
    PRIMARY KEY(TID),
    FOREIGN KEY (user_id) REFERENCES SB_User (user_id)
);

CREATE TABLE Post(
    PID VARCHAR(5) NOT NULL,
    TID VARCHAR(5) NOT NULL,
    user_id VARCHAR(5) NOT NULL,
    PRIMARY KEY(PID),
    FOREIGN KEY (TID) REFERENCES Thread (TID) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES SB_User (user_id)
);

CREATE TABLE User_Fans_Singer_Junction(
	user_id VARCHAR(5) NOT NULL,
	SID VARCHAR(7) NOT NULL,
	PRIMARY KEY (user_id, SID),
	FOREIGN KEY (user_id) REFERENCES SB_User (user_id),
	FOREIGN KEY (SID) REFERENCES Singer (SID)
);
