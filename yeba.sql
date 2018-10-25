DROP database if exists yabe;
CREATE DATABASE IF NOT EXISTS yabe;
USE yabe;


CREATE TABLE User(
	UserID varchar(200) NOT NULL,
	lastName varchar(200) NOT NULL,
	firstName varchar(200) NOT NULL,
	password varchar(200) NOT NULL,
	email varchar(200) NOT NULL,
	phone int(20) NOT NULL,
	streetAddr varchar(200) NOT NULL,
	PRIMARY KEY(UserID)
);


CREATE TABLE Seller(
	UserID varchar(200) NOT NULL,
	Company_name varchar(200) NOT NULL,
	PRIMARY KEY(UserID),
	FOREIGN KEY(UserID) REFERENCES User(UserID)
);




CREATE TABLE Buyer(
	UserID varchar(200) NOT NULL,
	payment_method varchar(200) DEFAULT NULL,
	PRIMARY KEY(UserID),
	FOREIGN KEY(UserID) REFERENCES User(UserID)
);


CREATE TABLE Admin(
	UserID varchar(200) NOT NULL,
	position varchar(200) NOT NULL,
	PRIMARY KEY(UserID),
	FOREIGN KEY(UserID) REFERENCES User(UserID)	
);

CREATE TABLE Product(
	P_id varchar(200) NOT NULL,
	P_description varchar(20000) DEFAULT NULL,
	P_price double NOT NULL,
	P_name varchar(200) NOT NULL,
	PRIMARY KEY(P_id)
);

CREATE TABLE Supply_product(
	P_id varchar(200) NOT NULL,
	S_id varchar(200) NOT NULL,
	PRIMARY KEY(P_id, S_id),
	FOREIGN KEY(P_id) REFERENCES Product(P_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(S_id) REFERENCES Seller(UserID)
);


CREATE TABLE P_Iphone(
	P_id varchar(200) NOT NULL,
	I_storage_inGB int NOT NULL,
	I_type varchar(200) NOT NULL,
	PRIMARY KEY(P_id),
	FOREIGN KEY(P_id) REFERENCES Product(P_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE P_sumsung(
	P_id varchar(200) NOT NULL,
	S_storage_inGB int NOT NULL,
	S_type varchar(200) NOT NULL,
	PRIMARY KEY(P_id),
	FOREIGN KEY(P_id) REFERENCES Product(P_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);


CREATE TABLE P_nokia(
	P_id varchar(200) NOT NULL,
	N_storage_inGB varchar(200) NOT NULL,
	N_type varchar(200) NOT NULL,
	PRIMARY KEY(P_id),
	FOREIGN KEY(P_id) REFERENCES Product(P_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);


CREATE TABLE isInterested(
	B_id varchar(200) NOT NULL,
	P_id varchar(200) NOT NULL,
	PRIMARY KEY(B_id, P_id),
	FOREIGN KEY(P_id) REFERENCES Product(P_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(B_id) REFERENCES Buyer(UserID)
);

CREATE TABLE FAQ(
	Q_id varchar(200) NOT NULL,
	B_id varchar(200) NOT NULL,
	S_id varchar(200) NOT NULL,
	Admin_id varchar(200) NOT NULL,
	question varchar(20000) NOT NULL,
	ans varchar(20000) DEFAULT NULL,
	PRIMARY KEY(Admin_id, Q_id),
	FOREIGN KEY(B_id) REFERENCES Buyer(UserID),
	FOREIGN KEY(S_id) REFERENCES Seller(UserID),
	FOREIGN KEY(Admin_id) REFERENCES Admin(UserID)
);


CREATE TABLE Auction(
	A_id varchar(200) NOT NULL,
	S_id varchar(200) NOT NULL,
	P_id varchar(200) NOT NULL,
	post_date datetime NOT NULL,
	end_date datetime NOT NULL,
	start_price double NOT NULL,
	PRIMARY KEY(A_id, S_id, P_id),
	FOREIGN KEY(S_id) REFERENCES Seller(UserID),
	FOREIGN KEY(P_id) REFERENCES Product(P_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);


CREATE TABLE Bid(
	Buyer_id varchar(200) NOT NULL,
	Auction_id varchar(200) NOT NULL,
	Current_price double NOT NULL,
	PRIMARY KEY(Buyer_id, Auction_id),
	FOREIGN KEY(Buyer_id) REFERENCES Buyer(UserID),
	FOREIGN KEY(Auction_id) REFERENCES Auction(A_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);


CREATE TABLE Auto_bid(
	Buyer_id varchar(200) NOT NULL,
	Auction_id varchar(200) NOT NULL,
	Increm double NOT NULL,
	ceiling double NOT NULL,
	PRIMARY KEY(Buyer_id, Auction_id),
	FOREIGN KEY(Buyer_id) REFERENCES Buyer(UserID),
	FOREIGN KEY(Auction_id) REFERENCES Auction(A_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE	
);


CREATE TABLE Manually_bid(
	Buyer_id varchar(200) NOT NULL,
	Auction_id varchar(200) NOT NULL,
	bid_price double NOT NULL,
	PRIMARY KEY(Buyer_id, Auction_id),
	FOREIGN KEY(Buyer_id) REFERENCES Buyer(UserID),
	FOREIGN KEY(Auction_id) REFERENCES Auction(A_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

insert into User
values('boss','Ding','Zhuozhi','123456','dingzhuozhi@gmail.com',123,'boss');

insert into Admin
values('boss','boss');

insert into User
values('buyer','pengrui','liu','buyer','buyer@gmail.com',123,'buyer');

insert into User
values('seller','qianli','zhang','seller','seller@gmail.com',123,'seller');

insert into Buyer
values('buyer','visa');

insert into Seller
value('seller','ebay');

DELIMITER $$

CREATE PROCEDURE `bid_insert` (aucid varchar(200),buid varchar(200),bidp double)

BEGIN

Declare maxbid varchar(200);
Declare secmaxbid varchar(200);
Declare resultp double;
Declare maxp double;


START TRANSACTION;
  INSERT INTO Bid values (buid,aucid,bidp);
  SET maxbid = (select Buyer_id from Auto_bid a where (a.ceiling in (select max(ceiling) from Auto_bid where Auction_id=aucid and ceiling>bidp)) and a.Auction_id=aucid);
  Select maxbid;
  if (maxbid) is not null then
     SET maxp= (select ceiling from Auto_bid a where(a.Auction_id=aucid and a.Buyer_id=maxbid));
        SET secmaxbid = (select Buyer_id from Auto_bid s where (s.ceiling in (select max(ceiling) from Auto_bid where Auction_id=aucid and ceiling<maxp and ceiling>bidp)) and s.Auction_id=aucid);
	end if;
  Select secmaxbid;
  if(maxbid) is not null and (secmaxbid) is null then
  	     SET resultp= bidp+(select Increm from Auto_bid a where (a.Auction_id=aucid and a.Buyer_id=maxbid));
        Select resultp;
        Update Bid 
        Set Current_price=resultp
        where Auction_id=aucid and Buyer_id=maxbid;
  elseif(maxbid) is not null and (secmaxbid)is not null then
  		 Set resultp=(select ceiling from Auto_bid a where(a.Auction_id=aucid and a.Buyer_id=secmaxbid))+(select Increm from Auto_bid a where (a.Auction_id=aucid and a.Buyer_id=maxbid));
        Select resultp;
        Update Bid 
        Set Current_price=resultp
        where Auction_id=aucid and Buyer_id=maxbid;
	end if;
COMMIT;

END; $$
DELIMITER ;


DELIMITER $$

CREATE PROCEDURE `bid_update` (aucid varchar(200),buid varchar(200),bidp double)

BEGIN

Declare maxbid varchar(200);
Declare secmaxbid varchar(200);
Declare resultp double;
Declare maxp double;


START TRANSACTION;
  Update Bid set Current_price=bidp where Auction_id=aucid and Buyer_id=buid;
  SET maxbid = (select Buyer_id from Auto_bid a where (a.ceiling in (select max(ceiling) from Auto_bid where Auction_id=aucid and ceiling>bidp)) and a.Auction_id=aucid);
  Select maxbid;
  if (maxbid) is not null then
     SET maxp= (select ceiling from Auto_bid a where(a.Auction_id=aucid and a.Buyer_id=maxbid));
        SET secmaxbid = (select Buyer_id from Auto_bid s where (s.ceiling in (select max(ceiling) from Auto_bid where Auction_id=aucid and ceiling<maxp and ceiling>bidp)) and s.Auction_id=aucid);
	end if;
  Select secmaxbid;
  if(maxbid) is not null and (secmaxbid) is null then
  	     SET resultp= bidp+(select Increm from Auto_bid a where (a.Auction_id=aucid and a.Buyer_id=maxbid));
        Select resultp;
        Update Bid 
        Set Current_price=resultp
        where Auction_id=aucid and Buyer_id=maxbid;
  elseif(maxbid) is not null and (secmaxbid)is not null then
  		 Set resultp=(select ceiling from Auto_bid a where(a.Auction_id=aucid and a.Buyer_id=secmaxbid))+(select Increm from Auto_bid a where (a.Auction_id=aucid and a.Buyer_id=maxbid));
        Select resultp;
        Update Bid 
        Set Current_price=resultp
        where Auction_id=aucid and Buyer_id=maxbid;
	end if;
COMMIT;

END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `sale_report` (OUT totalearns double, out earnpitem double,out earntyped double,out earntypet double,out earntypel double, out earnpseller double, out bsi varchar(200), out bb varchar(200))
Begin

Declare titem double;
Declare tuser double;
Declare lapitem double;
Declare tabitem double;
Declare desitem double;
Declare bbbid varchar(200);

create temporary table if not exists finalbid as (select * 
from (select * from Bid,Auction order by Auction_id, Current_price desc, Buyer_id) x
group by Auction_id);
select * from finalbid;
create temporary table if not exists legalfinal as (select * from finalbid f where NOW()>f.end_date);
select * from legalfinal;
Set titem = (select count(*) from legalfinal);
Set tuser=(select count(*) from Seller);
Set lapitem=(select count(*) from P_sumsung);
Set tabitem=(select count(*) from P_Iphone);
Set desitem=(select count(*) from P_nokia);
Set totalearns=(select sum(Current_price) from legalfinal);
Set earnpitem=totalearns/titem;
Set earntyped=(select sum(Current_price) from legalfinal l where l.Auction_id in (select Auction_id from Auction where P_id in (select P_id from P_nokia)))/desitem;
Set earntypet=(select sum(Current_price) from legalfinal l where l.Auction_id in (select Auction_id from Auction where P_id in (select P_id from P_Iphone)))/tabitem;
Set earntypel=(select sum(Current_price) from legalfinal l where l.Auction_id in (select Auction_id from Auction where P_id in (select P_id from P_sumsung)))/lapitem;
Set earnpseller=totalearns/tuser;
Set bsi=(select p.P_name from (SELECT P_name , COUNT(*) AS magnitude FROM Product
GROUP BY P_name 
ORDER BY magnitude DESC LIMIT 1)p);
Set bbbid=(select b.Buyer_id from (SELECT Buyer_id , COUNT(*) AS magnitude FROM legalfinal
GROUP BY Buyer_id 
ORDER BY magnitude DESC LIMIT 1)b);
Set bb=(select firstName from User u where u.UserID=bbbid);

select titem,tuser,lapitem,tabitem,desitem,totalearns,earnpitem,earntyped,earntypet,earntypel,earnpseller,
bsi,bbbid,bb;
Drop temporary table if exists finalbid;
Drop temporary table if exists legalfinal;
END; $$
DELIMITER ;

