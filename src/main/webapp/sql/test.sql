--�ڵ��ȣ �����ϱ�

create table test(
	tcode varchar(12) not null primary key,
	tdate  datetime not null
);

drop table test

select max(tcode) from test

insert into test(tcode, tdate)
value('t20180313001',now())

delete from test where tcode='t20180313001'

Convert(char(8),getdate(),112)
CAST(DATE_FORMAT(now(),'%Y%m%d') AS CHAR(8))

Convert(char(8),tdate,112)
CAST(DATE_FORMAT(tdate,'%Y%m%d') AS CHAR(8))

SELECT right(cast((SELECT ifnull(MAX(tcode),1) + 1 FROM team ) + 100000000 as char(9)), 3)

SELECT CAST(DATE_FORMAT(now(),'%Y%m%d') AS CHAR(8)) + 
right(cast((SELECT ifnull(MAX(tcode),1) + 1 FROM test 
			 WHERE cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))  = cast(DATE_FORMAT(tdate, '%Y%m%d') as char(8)))  + 100000 as char(6)), 5)
			 
			 
SELECT CAST(DATE_FORMAT(now(),'%Y%m%d') AS CHAR(8))+
right(CAST((SELECT ifnull(MAX(tcode),1) + 1 FROM test WHERE CAST(DATE_FORMAT(now(),'%Y%m%d') AS CHAR(8))=CAST(DATE_FORMAT(tdate,'%Y%m%d') AS CHAR(8)))+ 100000 as char(6)), 5)


SELECT CAST(DATE_FORMAT(now(),'%Y%m%d') AS CHAR(8))

select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))

--1)tcode�� ���� ū ���� ��¥�κа� ���ó�¥�� ���� 
--  ������ ���� ū �� ��� t20180313001
--  �ٸ��� t+���ó�¥ ��� (t20180313 )
select ifnull(max(tcode), (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from test
where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from test) COLLATE utf8_general_ci

--2) ���ڸ��� ����� 
--SELECT right(cast((SELECT ifnull(MAX(tcode),1) + 1 FROM team ) + 100000000 as char(9)), 3)
select right(cast(((select right(max(tcode),3) from test)+1)+100000000 as char(9)),3)



--3) 1)�� �� ���� ������(tcode ���� ū ���̸�)
--   right(max(tcode),3)�ؼ� +1
--   �ٸ��� '001'
select if(
		   (
		    select ifnull(max(tcode), (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from test
		    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from test) COLLATE utf8_general_ci
		   ) COLLATE utf8_general_ci =(select max(tcode) from test) COLLATE utf8_general_ci,
		   (select right(cast(((select right(max(tcode),3) from test)+1)+1000 as char(4)),3)),
		   '001'
		  )

--4) ���ڿ� ��ġ��
select concat('t',(cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))),
(select if(
		   (
		    select ifnull(max(tcode), (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from test
		    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from test) COLLATE utf8_general_ci
		   ) COLLATE utf8_general_ci =(select max(tcode) from test) COLLATE utf8_general_ci,
		   (select right(cast(((select right(max(tcode),3) from test)+1)+1000 as char(4)),3)),
		   '001'
		  )
))

--�ֹ���ȣ
create table payment(
	pcode varchar(15) not null primary key,
	tdate  datetime not null
);

drop table payment

insert into payment(pcode, tdate)
value('201803131723001',now())

SELECT CAST(DATE_FORMAT(now(),'%Y%m%d%H%i') AS CHAR(12))

select concat((CAST(DATE_FORMAT(now(),'%Y%m%d%H%i') AS CHAR(12))),
(select if(
		   (
		    select ifnull(max(pcode), (select concat((select CAST(DATE_FORMAT(now(),'%Y%m%d%H%i') AS CHAR(12)))) ) ) from payment as P1
		    where CAST(DATE_FORMAT(now(),'%Y%m%d%H%i') AS CHAR(12)) = (select substring(ifnull(max(pcode),1),1,12) from payment as P2 ) COLLATE utf8_general_ci
		   ) COLLATE utf8_general_ci =(select max(pcode) from payment as P3) COLLATE utf8_general_ci,
		   (select right(cast(((select right(max(pcode),3) from payment as P4)+1)+1000 as char(4)),3)),
		   '001'
		  )
))

