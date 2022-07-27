CREATE TABLE Singer(
    SID VARCHAR(7) NOT NULL,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    stage_name VARCHAR(50),
    gender VARCHAR(6),
    country_name VARCHAR(20) NOT NULL,
    PRIMARY KEY(SID)
);

INSERT INTO Singer
VALUES('SI1', 'Waruntorn', 'Paonil', 'Ink Waruntorn', 'f', 'Thailand');

INSERT INTO Singer
VALUES('SI2', 'Minh Hang', 'Nguyen', 'MIN', 'f', 'Vietnam');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI3', 'Khanh Linh', 'Phung', 'f', 'Vietnam');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI4', 'Duc Cuong', 'Nguyen', 'm', 'Vietnam');

INSERT INTO Singer
VALUES('SI5', 'Cherprang', 'Areekul', 'Cherprang', 'f', 'Thailand');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI6', 'Yuri', 'Shatunov', 'm', 'Russia');

INSERT INTO Singer
VALUES('SI7', 'Thuy Tien', 'Huynh Nu', 'Tien Tien', 'f', 'Vietnam');

INSERT INTO Singer
VALUES('SI8', 'Minh Trang', 'Ngo', 'Trang', 'f', 'Vietnam');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI9', 'Vu', 'Thanh Van', 'f', 'Vietnam');

INSERT INTO Singer
VALUES('SI10', 'Angele', 'van Laeken', 'Angele', 'f', 'Belgium');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI11', 'Jun', 'Togawa', 'f', 'Japan');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI12', 'Erke', 'Esmahan', 'f', 'Kazakhstan');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI13', 'Toregali', 'Toreali', 'm', 'Kazakhstan');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI14', 'Yuki', 'Saito', 'f', 'Japan');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI15', 'Chisato', 'Moritaka', 'f', 'Japan');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI16', 'Melanie', 'Martinez', 'f', 'USA');

INSERT INTO Singer(SID, fname, lname, gender, country_name)
VALUES('SI17', 'Megan', 'James', 'f', 'Canada');

INSERT INTO Singer
VALUES('SI18', 'Caroline', 'Muller', 'C.C. Catch', 'f', 'Germany');

INSERT INTO Singer
VALUES('SI19', 'Heeyeon', 'Ahn', 'Hani', 'f', 'South Korea');

INSERT INTO Singer
VALUES('SI20', 'Robyn', 'Fenty', 'Rihanna', 'f', 'Barbados');

CREATE TABLE SB_User(
    user_id VARCHAR(5) NOT NULL,
    username VARCHAR(20) NOT NULL,
    gender VARCHAR(6),
    email VARCHAR(50) NOT NULL,
    mobile_no CHAR(11) NOT NULL,
    PRIMARY KEY(user_id)
);

INSERT INTO SB_User
VALUES('U1', 'flutecitylightsfly', 'f', 'yohomi7023@altpano.com', '14025785765');

INSERT INTO SB_User
VALUES('U2', 'catmandolinneoaliens', 'f', 'ddeagal@googl.win', '17625437434');

INSERT INTO SB_User
VALUES('U3', 'hakureirei', 'f', 'hakureimiko@gmail.com', '81437492469');

INSERT INTO SB_User
VALUES('U4', 'porchpetticoat', 'm', 'tcfresher822@cupremplus.com', '17255716637');

INSERT INTO SB_User
VALUES('U5', 'glandexactly', 'm', 'junior58@gotcertify.com', '12424282097');

INSERT INTO SB_User
VALUES('U6', 'bibpercentage', 'f', 'nataligentle@playfunplus.com', '14505242296');

INSERT INTO SB_User
VALUES('U7', 'guiltypears', 'f', 'prancvyt@hacktoy.com', '74861788527');

INSERT INTO SB_User
VALUES('U8', 'shadowclatter', 'm', 'elmarslove@encuestan.com', '27800619848');

INSERT INTO SB_User
VALUES('U9', 'kourindou', 'm', 'morichika_r@gmail.com', '81767290753');

INSERT INTO SB_User
VALUES('U10', 'heathspoonbill', 'm', 'runeab@nbobd.com', '61392152935');

CREATE TABLE Album(
    AID VARCHAR(5) NOT NULL,
    SID VARCHAR(5) NOT NULL,
	title VARCHAR(20),
    genre VARCHAR(10),
    release_year INT,
    PRIMARY KEY(AID),
	CONSTRAINT FK_AlbumSinger FOREIGN KEY(SID)
    REFERENCES Singer(SID)
);

INSERT INTO Album
VALUES('A1', 'SI17', 'another eternity', 'electronic', 2015);

INSERT INTO Album
VALUES('A2', 'SI16', 'K-12', 'pop', 2019);

INSERT INTO Album
VALUES('A3', 'SI16', 'Cry Baby', 'pop', 2015);

INSERT INTO Album
VALUES('A4', 'SI10', 'Nonante-Cinq', 'pop', 2021);

INSERT INTO Album
VALUES('A5', 'SI10', 'Brol', 'pop', 2018);

INSERT INTO Album
VALUES('A6', 'SI2', '50/50', 'pop', 2022);

INSERT INTO Album
VALUES('A7', 'SI5', 'RIVER', 'pop', 2018);

INSERT INTO Album
VALUES('A8', 'SI8', 'Chay Trong Voi Nhau', 'indie', 2022);

INSERT INTO Album
VALUES('A9', 'SI4', 'Kobukovu', 'rap', 2016);

INSERT INTO Album
VALUES('A10', 'SI3', 'yesteryear', 'pop', 2020);

CREATE TABLE Song(
	song_id VARCHAR(5) NOT NULL,
    SID VARCHAR(5) NOT NULL, 
	AID VARCHAR(5),
    title VARCHAR(20),
    genre VARCHAR(10),
    release_year INT,
    PRIMARY KEY(song_id),
    CONSTRAINT FK_SongSinger FOREIGN KEY(SID)
    REFERENCES Singer(SID),
    CONSTRAINT FK_SongAlbum FOREIGN KEY(AID)
    REFERENCES Album(AID)
);

INSERT INTO Song
VALUES('S1', 'SI10', 'A5', 'Balance ton quoi', 'pop', 2018);

INSERT INTO Song
VALUES('S2', 'SI10', 'A5', 'Taxi', 'pop', 2021);

INSERT INTO Song
VALUES('S3', 'SI5', 'A7', 'RIVER', 'pop', 2018);

INSERT INTO Song
VALUES('S4', 'SI2', 'A6', 'Ca Phe', 'pop', 2022);

INSERT INTO Song(song_id, SID, title, genre, release_year)
VALUES('S5', 'SI14', 'Jounetsu', 'pop', 1988);

INSERT INTO Song(song_id, SID, title, genre, release_year)
VALUES('S6', 'SI14', 'Shiroi Honoo', 'pop', 1988);

INSERT INTO Song(song_id, SID, title, genre, release_year)
VALUES('S7', 'SI10', 'La Thune', 'pop', 2018);

INSERT INTO Song(song_id, SID, title, genre, release_year)
VALUES('S8', 'SI12', 'Kayda?', 'pop', 2016);

INSERT INTO Song(song_id, SID, title, genre, release_year)
VALUES('S9', 'SI13', 'Do Re Mi', 'pop', 2015);

INSERT INTO Song
VALUES('S10', 'SI3', 'A10', 'sao anh khong hieu?', 'pop', 2020);

CREATE TABLE Song_List(
	user_id VARCHAR(5),
	song_list VARCHAR(20),
    CONSTRAINT FK_ListUser FOREIGN KEY(user_id)
    REFERENCES SB_User(user_id)
);

INSERT INTO Song_List
VALUES('U1', 'S4, S7, S2');

INSERT INTO Song_List
VALUES('U2', 'S3, S9');

INSERT INTO Song_List
VALUES('U8', 'S10, S3, S4, S1');

INSERT INTO Song_List
VALUES('U5', 'S8, S2');

INSERT INTO Song_List
VALUES('U3', 'S9');

CREATE TABLE Thread(
	TID VARCHAR(5) NOT NULL,
    topic VARCHAR(50) NOT NULL,
    user_id VARCHAR(5) NOT NULL,
    PRIMARY KEY(TID),
    CONSTRAINT FK_UserThread FOREIGN KEY(user_id)
    REFERENCES SB_User(user_id)
);

INSERT INTO Thread
VALUES('T1', 'Popular dance moves', 'U8');

INSERT INTO Thread
VALUES('T2', 'Songs about heartbreak', 'U3');

INSERT INTO Thread
VALUES('T3', 'Music for kids', 'U9');

INSERT INTO Thread
VALUES('T4', 'Songs for parents', 'U1');

INSERT INTO Thread
VALUES('T5', 'Songs you dislike', 'U2');

INSERT INTO Thread
VALUES('T6', 'Songs you sleep to', 'U8');

INSERT INTO Thread
VALUES('T7', 'K-pop vs. J-pop', 'U7');

INSERT INTO Thread
VALUES('T8', 'European stuff', 'U3');

INSERT INTO Thread
VALUES('T9', 'Angry time songs', 'U8');

INSERT INTO Thread
VALUES('T10', 'Best song of 2022', 'U1');

CREATE TABLE Post(
	PID VARCHAR(5) NOT NULL,
    TID VARCHAR(5) NOT NULL,
    user_id VARCHAR(5) NOT NULL,
    PRIMARY KEY(PID),
    CONSTRAINT FK_PostThread FOREIGN KEY(TID)
    REFERENCES Thread(TID)
    ON DELETE CASCADE,
    CONSTRAINT FK_UserPost FOREIGN KEY(user_id)
    REFERENCES SB_User(user_id)
);

INSERT INTO Post
VALUES('P1', 'T3', 'U8');

INSERT INTO Post
VALUES('P2', 'T2', 'U10');

INSERT INTO Post
VALUES('P3', 'T1', 'U9');

INSERT INTO Post
VALUES('P4', 'T4', 'U1');

INSERT INTO Post
VALUES('P5', 'T9', 'U2');

INSERT INTO Post
VALUES('P6', 'T5', 'U3');

INSERT INTO Post
VALUES('P7', 'T6', 'U8');

INSERT INTO Post
VALUES('P8', 'T7', 'U8');

INSERT INTO Post
VALUES('P9', 'T8', 'U9');

INSERT INTO Post
VALUES('P10', 'T10', 'U8');

CREATE TABLE Music_Group(
	name VARCHAR(20) NOT NULL,
	members VARCHAR(50) NOT NULL
);

INSERT INTO Music_Group
VALUES('BNK48', 'SI5');

INSERT INTO Music_Group
VALUES('Laskoviy May', 'SI6');

INSERT INTO Music_Group
VALUES('EXID', 'SI9');

CREATE TABLE Country(
	country_name VARCHAR(20) NOT NULL,
    capital VARCHAR(20),
    continent VARCHAR(15) NOT NULL,
    population INTEGER
);

INSERT INTO Country
VALUES('Australia', 'Canberra', 'Oceania', 25690000);

INSERT INTO Country
VALUES('Canada', 'Ottawa', 'North America', 38000000);

INSERT INTO Country
VALUES('France', 'Paris', 'Europe', 67390000);

INSERT INTO Country
VALUES('Germany', 'Berlin', 'Europe', 83240000);

INSERT INTO Country
VALUES('Japan', 'Tokyo', 'Asia', 125800000);

INSERT INTO Country
VALUES('Kazakhstan', 'Nur-Sultan', 'Asia', 18750000);

INSERT INTO Country
VALUES('Russia', 'Moscow', 'Europe', 144100000);

INSERT INTO Country
VALUES('South Africa', 'Cape Town', 'Africa', 59310000);

INSERT INTO Country
VALUES('South Korea', 'Seoul', 'Asia', 51780000);

INSERT INTO Country
VALUES('Thailand', 'Bangkok', 'Asia', 69800000);

INSERT INTO Country
VALUES('USA', 'Washington D.C.', 'North America', 329500000);

INSERT INTO Country
VALUES('Vietnam', 'Hanoi', 'Asia', 97340000);

CREATE TABLE Singer_Country(
    country_name VARCHAR(20) NOT NULL,
	singer_list VARCHAR(30) NOT NULL
);

INSERT INTO Singer_Country
VALUES('Canada', 'SI17');

INSERT INTO Singer_Country
VALUES('France', 'SI10');

INSERT INTO Singer_Country
VALUES('Germany', 'SI6, SI18');

INSERT INTO Singer_Country
VALUES('Japan', 'SI1, SI14, SI15');

INSERT INTO Singer_Country
VALUES('Kazakhstan', 'SI2, SI13');

INSERT INTO Singer_Country
VALUES('South Korea', 'SI9');

INSERT INTO Singer_Country
VALUES('Thailand', 'SI1, SI5');

INSERT INTO Singer_Country
VALUES('USA', 'SI16, SI20');

INSERT INTO Singer_Country
VALUES('Vietnam', 'SI2, SI3, SI4, SI7, SI8, SI9');

CREATE TABLE User_Country(
	country_name VARCHAR(20) NOT NULL,
    user_list VARCHAR(30) NOT NULL
);

INSERT INTO User_Country
VALUES('Australia', 'U10');

INSERT INTO User_Country
VALUES('Canada', 'U4, U5, U6');

INSERT INTO User_Country
VALUES('Japan', 'U3, U9');

INSERT INTO User_Country
VALUES('Russia', 'U7');

INSERT INTO User_Country
VALUES('South Africa', 'U8');

INSERT INTO User_Country
VALUES('USA', 'U1, U2');
