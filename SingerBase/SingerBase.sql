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
VALUES('U1', 'flutecitylightsfly', 'f', 'yohomi7023@altpano.com', '16014815267');

INSERT INTO SB_User
VALUES('U2', 'catmandolinneoaliens', 'f', 'ddeagal@googl.win', '59164644510');

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
    topic VARCHAR(10) NOT NULL,
    user_id VARCHAR(5) NOT NULL,
    PRIMARY KEY(TID),
    CONSTRAINT FK_UserThread FOREIGN KEY(user_id)
    REFERENCES SB_User(user_id)
);

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

CREATE TABLE Singer_Country(
    SID VARCHAR(5) NOT NULL,
    country_name VARCHAR(20) NOT NULL,
    CONSTRAINT FK_Singer_Country FOREIGN KEY(SID)
    REFERENCES Singer(SID)
);

CREATE TABLE User_Country(
    user_id VARCHAR(5) NOT NULL,
    country_name VARCHAR(20) NOT NULL,
    CONSTRAINT FK_User_Country FOREIGN KEY(user_id)
    REFERENCES SB_User(user_id)
);
