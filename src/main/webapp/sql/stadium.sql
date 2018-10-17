create table stadium(
	stacode int not null auto_increment PRIMARY KEY,
	staname varchar(50) not null,
	staaddr1 varchar(50) not null,
	staaddr2 varchar(50) not null,
	staaddr3 varchar(50) not null,
	stapay int not null,
	stainfo varchar(2000) not null,
	stadate datetime not null,
	staclose int not null,
	staopen int not null,
	id varchar(20) not null,
	stalevel    CHAR(1)       DEFAULT 'Y' NOT NULL,
    poster1   VARCHAR(255) DEFAULT 'poster.jpg' NOT NULL,
    poster2   VARCHAR(255),
    poster3   VARCHAR(255),
    poster4   VARCHAR(255),
    poster5   VARCHAR(255)
);

SELECT count(stacode) as count FROM stadium
where id='soso'


alter table stadium add id varchar(20)


drop table stadium

insert into stadium(stacode,staname,staaddr1,staaddr2,stapay,stainfo,stadate,staclose,staopen,stapasswd,stalevel,poster)
value((SELECT IFNULL(MAX(stacode),0)+1 FROM stadium AS TB),'putfoot','����Ư����','���α�','35000','�������� �Դϴ�.',now(),'23','6','1234','Y','123.jsp')

insert into stadium(stacode,staname,staaddr1,staaddr2,stapay,stainfo,stadate,staclose,staopen,stapasswd,stalevel,poster)
value(1,'�ֵ���ũ','����Ư����','���α�','35000','�������� �Դϴ�.',now(),'23','6','1234','Y','123.jsp')



UPDATE stadium SET stalevel='N' WHERE stacode='1' AND stalevel='Y';

drop table stadium;

select * from stadium;

INSERT INTO stadium(stacode, staname, staaddr,stapay,stainfo,stadate,staclose,staopen,stapasswd,stalevel,poster)
VALUE((SELECT IFNULL(MAX(stacode),0)+1 FROM stadium AS TB),'�����Ű����','������','5000','����',now(),'10','24','1234','Y','123.jpg');

INSERT INTO stadium(stacode, staname, staaddr,stapay,stainfo,stadate,staclose,staopen,stapasswd)
VALUE((SELECT IFNULL(MAX(stacode),0)+1 FROM stadium AS TB),?,?,?,?,now(),?,?,?);

update stadium
set stalevel='Y'
where stacode='8'

select poster from stadium

drop table stadium2

SELECT staname, staaddr,stapay,stainfo,stadate,staclose,staopen
FROM stadium
ORDER BY stacode DESC;


SELECT stacode, staname, staaddr,stapay,statel,stainfo,stadate,staclose,staopen 
 FROM(SELECT stacode, staname, staaddr,stapay,statel,stainfo,stadate,staclose,staopen, @ROWNUM := @ROWNUM+1 as ROW 
 	  FROM (SELECT stacode, staname, staaddr,stapay,statel,stainfo,stadate,staclose,staopen 
 	   		FROM stadium
			)A, (SELECT @ROWNUM := 0) 
 B)C 
 WHERE C.ROW>=1 AND C.ROW<=5