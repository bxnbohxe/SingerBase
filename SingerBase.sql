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
VALUES('SI4', 'Khanh Linh', 'Phung', 'f', 'Vietnam');

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

CREATE TABLE Album(
    AID VARCHAR(5) NOT NULL,
    SID VARCHAR(5) NOT NULL,
    title VARCHAR(20),
    genre VARCHAR(10),
    YEAR INT,
    PRIMARY KEY(AID),
    CONSTRAINT FK_AlbumSinger FOREIGN KEY(SID)
    REFERENCES Singer(SID)
);

CREATE TABLE Song(
    song_id VARCHAR(5) NOT NULL,
    SID VARCHAR(5) NOT NULL, 
    AID VARCHAR(5) NOT NULL,
    user_id VARCHAR(5),
    title VARCHAR(20),
    genre VARCHAR(10),
    YEAR INT,
    PRIMARY KEY(song_id),
    CONSTRAINT FK_SongSinger FOREIGN KEY(SID)
    REFERENCES Singer(SID),
    CONSTRAINT FK_SongAlbum FOREIGN KEY(AID)
    REFERENCES Album(AID),
    CONSTRAINT FK_UserFavesSong FOREIGN KEY(user_id)
    REFERENCES SB_User(user_id)
);

CREATE TABLE List(
    song_list VARCHAR(20),
    user_id VARCHAR(5),
    CONSTRAINT FK_ListUser FOREIGN KEY(user_id)
    REFERENCES SB_User(user_id)
);

CREATE TABLE Thread(
    TID VARCHAR(5) NOT NULL,
    topic VARCHAR(10) NOT NULL,
    PRIMARY KEY(TID)
);

CREATE TABLE Post(
    PID VARCHAR(5) NOT NULL,
    TID VARCHAR(5) NOT NULL,
    PRIMARY KEY(PID),
    CONSTRAINT FK_PostThread FOREIGN KEY(TID)
    REFERENCES Thread(TID)
);

CREATE TABLE Music_Group(
    name VARCHAR(20) NOT NULL,
    members VARCHAR(50) NOT NULL
);

CREATE TABLE Country(
    country_name VARCHAR(20) NOT NULL,
    capital VARCHAR(20),
    CONTINENT VARCHAR(15) NOT NULL,
    population INTEGER
);

CREATE TABLE Singer_Country(
    SID VARCHAR(5) NOT NULL,
    country_name VARCHAR(20) NOT NULL,
    CONSTRAINT FK_Singer_Country FOREIGN KEY(SID)
    REFERENCES Singer(SID)
);
