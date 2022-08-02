INSERT ALL
	INTO Country VALUES('Australia', 'Canberra', 'Oceania', 25690000)
	INTO Country VALUES('Barbados', 'Bridgetown', 'North America', 287371)
	INTO Country VALUES('Belgium', 'Brussels', 'Europe', 11560000)
	INTO Country VALUES('Canada', 'Ottawa', 'North America', 38000000)
	INTO Country VALUES('France', 'Paris', 'Europe', 67390000)
	INTO Country VALUES('Germany', 'Berlin', 'Europe', 83240000)
	INTO Country VALUES('Japan', 'Tokyo', 'Asia', 125800000)
	INTO Country VALUES('Kazakhstan', 'Nur-Sultan', 'Asia', 18750000)
	INTO Country VALUES('Russia', 'Moscow', 'Europe', 144100000)
	INTO Country VALUES('South Africa', 'Cape Town', 'Africa', 59310000)
	INTO Country VALUES('South Korea', 'Seoul', 'Asia', 51780000)
	INTO Country VALUES('Thailand', 'Bangkok', 'Asia', 69800000)
	INTO Country VALUES('USA', 'Washington D.C.', 'North America', 329500000)
	INTO Country VALUES('Vietnam', 'Hanoi', 'Asia', 97340000)
	INTO Country VALUES('Not listed', null, 'None', null)
SELECT * FROM dual;

INSERT ALL
	INTO Music_Group VALUES('BNK48', 2017)
	INTO Music_Group VALUES('Laskoviy May', 1986)
	INTO Music_Group VALUES('EXID', 2012)
SELECT * FROM dual;

INSERT ALL
	INTO Singer VALUES('SI1', 'Waruntorn', 'Paonil', 'Ink Waruntorn', 'f', 'Thailand', null)
	INTO Singer VALUES('SI2', 'Minh Hang', 'Nguyen', 'MIN', 'f', 'Vietnam', null)
	INTO Singer VALUES('SI3', 'Khanh Linh', 'Phung', null, 'f', 'Vietnam', null)
	INTO Singer VALUES('SI4', 'Duc Cuong', 'Nguyen', null, 'm', 'Vietnam', null)
	INTO Singer VALUES('SI5', 'Cherprang', 'Areekul', 'Cherprang', 'f', 'Thailand', 'BNK48')
	INTO Singer VALUES('SI6', 'Yuri', 'Shatunov', null, 'm', 'Russia', 'Laskoviy May')
	INTO Singer VALUES('SI7', 'Thuy Tien', 'Huynh Nu', 'Tien Tien', 'f', 'Vietnam', null)
	INTO Singer VALUES('SI8', 'Minh Trang', 'Ngo', 'Trang', 'f', 'Vietnam', null)
	INTO Singer VALUES('SI9', 'Vu', 'Thanh Van', null, 'f', 'Vietnam', 'EXID')
	INTO Singer VALUES('SI10', 'Angele', 'van Laeken', 'Angele', 'f', 'Belgium', null)
	INTO Singer VALUES('SI11', 'Jun', 'Togawa', null, 'f', 'Japan', null)
	INTO Singer VALUES('SI12', 'Erke', 'Esmahan', null, 'f', 'Kazakhstan', null)
	INTO Singer VALUES('SI13', 'Toregali', 'Toreali', null, 'm', 'Kazakhstan', null)
	INTO Singer VALUES('SI14', 'Yuki', 'Saito', null, 'f', 'Japan', null)
	INTO Singer VALUES('SI15', 'Chisato', 'Moritaka', null, 'f', 'Japan', null)
	INTO Singer VALUES('SI16', 'Melanie', 'Martinez', null, 'f', 'USA', null)
	INTO Singer VALUES('SI17', 'Megan', 'James', null, 'f', 'Canada', null)
	INTO Singer VALUES('SI18', 'Caroline', 'Muller', 'C.C. Catch', 'f', 'Germany', null)
	INTO Singer VALUES('SI19', 'Heeyeon', 'Ahn', 'Hani', 'f', 'South Korea', null)
	INTO Singer VALUES('SI20', 'Robyn', 'Fenty', 'Rihanna', 'f', 'Barbados', null)
SELECT * FROM dual;

INSERT ALL
	INTO SB_User VALUES('U1', 'flutecitylightsfly', 'password1','f', 'yohomi7023@altpano.com', '4025785765', 'USA')
	INTO SB_User VALUES('U2', 'catmandolinneoaliens', 'password2', 'f', 'ddeagal@googl.win', '7625437434', 'USA')
	INTO SB_User VALUES('U3', 'hakureirei', 'password3', 'f', 'hakureimiko@gmail.com', '1437492469', 'Japan')
	INTO SB_User VALUES('U4', 'porchpetticoat', 'password4', 'm', 'tcfresher822@cupremplus.com', '7255716637', 'Canada')
	INTO SB_User VALUES('U5', 'glandexactly', 'password5', 'm', 'junior58@gotcertify.com', '2424282097', 'Canada')
	INTO SB_User VALUES('U6', 'bibpercentage', 'password6', 'f', 'nataligentle@playfunplus.com', '4505242296', 'Canada')
	INTO SB_User VALUES('U7', 'guiltypears', 'password7', 'f', 'prancvyt@hacktoy.com', '4861788527', 'Russia')
	INTO SB_User VALUES('U8', 'shadowclatter', 'password8', 'm', 'elmarslove@encuestan.com', '7800619848', 'South Africa')
	INTO SB_User VALUES('U9', 'kourindou', 'password9', 'm', 'morichika_r@gmail.com', '1767290753', 'Japan')
	INTO SB_User VALUES('U10', 'heathspoonbill', 'password10', 'm', 'runeab@nbobd.com', '1392152935', 'Australia')
SELECT * FROM dual;

INSERT ALL
	INTO Album VALUES('A1', 'SI17', 'another eternity', 'electronic', 2015)
	INTO Album VALUES('A2', 'SI16', 'K-12', 'pop', 2019)
	INTO Album VALUES('A3', 'SI16', 'Cry Baby', 'pop', 2015)
	INTO Album VALUES('A4', 'SI10', 'Nonante-Cinq', 'pop', 2021)
	INTO Album VALUES('A5', 'SI10', 'Brol', 'pop', 2018)
	INTO Album VALUES('A6', 'SI2', '50/50', 'pop', 2022)
	INTO Album VALUES('A7', 'SI5', 'RIVER', 'pop', 2018)
	INTO Album VALUES('A8', 'SI8', 'Chay Trong Voi Nhau', 'indie', 2022)
	INTO Album VALUES('A9', 'SI4', 'Kobukovu', 'rap', 2016)
	INTO Album VALUES('A10', 'SI3', 'yesteryear', 'pop', 2020)
SELECT * FROM dual;

INSERT ALL
	INTO Song VALUES('S1', 'SI10', 'A5', 'Balance ton quoi', 'pop', 2018)
	INTO Song VALUES('S2', 'SI10', 'A5', 'Taxi', 'pop', 2021)
	INTO Song VALUES('S3', 'SI5', 'A7', 'RIVER', 'pop', 2018)
	INTO Song VALUES('S4', 'SI2', 'A6', 'Ca Phe', 'pop', 2022)
	INTO Song(song_id, SID, title, genre, release_year) VALUES('S5', 'SI14', 'Jounetsu', 'pop', 1988)
	INTO Song(song_id, SID, title, genre, release_year) VALUES('S6', 'SI14', 'Shiroi Honoo', 'pop', 1988)
	INTO Song(song_id, SID, title, genre, release_year) VALUES('S7', 'SI10', 'La Thune', 'pop', 2018)
	INTO Song(song_id, SID, title, genre, release_year) VALUES('S8', 'SI12', 'Kayda?', 'pop', 2016)
	INTO Song(song_id, SID, title, genre, release_year) VALUES('S9', 'SI13', 'Do Re Mi', 'pop', 2015)
	INTO Song VALUES('S10', 'SI3', 'A10', 'sao anh khong hieu?', 'pop', 2020)
SELECT * FROM dual;

INSERT ALL
	INTO Song_List VALUES('SL1', 'U1')
	INTO Song_List VALUES('SL2', 'U2')
	INTO Song_List VALUES('SL3', 'U8')
	INTO Song_List VALUES('SL4', 'U5')
	INTO Song_List Values('SL5', 'U3')
SELECT * FROM dual;

INSERT ALL
	INTO Song_Song_List_Junction VALUES('SL1', 'S4')
	INTO Song_Song_List_Junction VALUES('SL1', 'S7')
	INTO Song_Song_List_Junction VALUES('SL1', 'S2')
	INTO Song_Song_List_Junction VALUES('SL2', 'S3')
	INTO Song_Song_List_Junction VALUES('SL2', 'S9')
	INTO Song_Song_List_Junction VALUES('SL3', 'S10')
	INTO Song_Song_List_Junction VALUES('SL3', 'S3')
	INTO Song_Song_List_Junction VALUES('SL3', 'S4')
	INTO Song_Song_List_Junction VALUES('SL3', 'S1')
	INTO Song_Song_List_Junction VALUES('SL4', 'S8')
	INTO Song_Song_List_Junction VALUES('SL4', 'S2')
	INTO Song_Song_List_Junction VALUES('SL5', 'S9')
SELECT * FROM dual;

INSERT ALL
	INTO Thread VALUES('T1', 'Popular dance moves', 'U8')
	INTO Thread VALUES('T2', 'Songs about heartbreak', 'U3')
	INTO Thread VALUES('T3', 'Music for kids', 'U9')
	INTO Thread VALUES('T4', 'Songs for parents', 'U1')
	INTO Thread VALUES('T5', 'Songs you dislike', 'U2')
	INTO Thread VALUES('T6', 'Songs you sleep to', 'U8')
	INTO Thread VALUES('T7', 'K-pop vs. J-pop', 'U7')
	INTO Thread VALUES('T8', 'European stuff', 'U3')
	INTO Thread VALUES('T9', 'Angry time songs', 'U8')
	INTO Thread VALUES('T10', 'Best song of 2022', 'U1')
SELECT * FROM dual;

INSERT ALL
	INTO Post VALUES('P1', 'T3', 'U8')
	INTO Post VALUES('P2', 'T2', 'U10')
	INTO Post VALUES('P3', 'T1', 'U9')
	INTO Post VALUES('P4', 'T4', 'U1')
	INTO Post VALUES('P5', 'T9', 'U2')
	INTO Post VALUES('P6', 'T5', 'U3')
	INTO Post VALUES('P7', 'T6', 'U8')
	INTO Post VALUES('P8', 'T7', 'U8')
	INTO Post VALUES('P9', 'T8', 'U9')
	INTO Post VALUES('P10', 'T10', 'U8')
SELECT * FROM dual;

INSERT ALL
	INTO User_Fans_Singer_Junction VALUES('U1', 'SI4')
	INTO User_Fans_Singer_Junction VALUES('U1', 'SI2')
	INTO User_Fans_Singer_Junction VALUES('U1', 'SI9')
	INTO User_Fans_Singer_Junction VALUES('U1', 'SI5')
	INTO User_Fans_Singer_Junction VALUES('U3', 'SI6')
	INTO User_Fans_Singer_Junction VALUES('U3', 'SI3')
	INTO User_Fans_Singer_Junction VALUES('U3', 'SI20')
	INTO User_Fans_Singer_Junction VALUES('U4', 'SI8')
	INTO User_Fans_Singer_Junction VALUES('U4', 'SI10')
	INTO User_Fans_Singer_Junction VALUES('U4', 'SI20')
	INTO User_Fans_Singer_Junction VALUES('U5', 'SI10')
	INTO User_Fans_Singer_Junction VALUES('U5', 'SI12')
	INTO User_Fans_Singer_Junction VALUES('U7', 'SI3')
	INTO User_Fans_Singer_Junction VALUES('U8', 'SI9')
	INTO User_Fans_Singer_Junction VALUES('U8', 'SI10')
	INTO User_Fans_Singer_Junction VALUES('U9', 'SI11')
	INTO User_Fans_Singer_Junction VALUES('U9', 'SI14')
SELECT * FROM dual;
