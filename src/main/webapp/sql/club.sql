create table club(
	ccode varchar(12) not null primary key,
	cid varchar(10) not null,
	cname varchar(20) not null,
	carea varchar(50) not null,
	ccontent varchar(255) not null,
	cposter varchar(255) not null
);

--테이블 생성시 기본값 입력 시킬것
INSERT INTO club(ccode, cid, cname, carea, ccontent, cposter)
VALUES('c00000000000', '관리자아이디쓰셍~', '무소속', '서울 구로구', '20대', 'cposter.png');


drop table club;

select * from club;


INSERT INTO club(ccode, cid, cname, carea, ccontent, cposter)
VALUES('c20180308001', 'soldesk', 'FCSoldesk', '서울 구로구', '20대', 'realmadrid.jpg');

INSERT INTO club(ccode, cid, cname, carea, ccontent, cposter)
VALUES('c20180308002',  'soldesk2', 'liverpool', '서울 종로구', '30대', 'liverpool.jpg');

INSERT INTO club(ccode, cid, cname, carea, ccontent, cposter)
VALUES('c20180308003',  'soldesk3', 'milan', '경기 성남시', '40대', 'milan.jpg');

INSERT INTO club(ccode, cid, cname, carea, ccontent, cposter)
VALUES('c20180318001',  'soldesk4', 'manu', '경기 성남시', '40대', 'manu.jpg');

INSERT INTO club(ccode, cid, cname, carea, ccontent, cposter)
VALUES('c20180319001',  'soldesk5', 'bayern', '경기 성남시', '40대', 'bayern.jpg');

INSERT INTO club(ccode, cid, cname, carea, ccontent, cposter)
VALUES('c20180319002',  'soldesk6', 'barcelona', '경기 성남시', '40대', 'barcelona.jpg');

UPDATE club
SET ccode='c20180322001'
WHERE cid='soso'

UPDATE club
SET cid='choul3416'
where ccode='c00000000000';

UPDATE club
SET cname='무소속'
WHERE cid='choul3416'


SELECT a.tcode as tcode, a.tarea as tarea, a.tname as tname, a.tid as tid, a.player as player, a.paycode as paycode, a.tmatch as tmatch, a.ttime as ttime, c.ccode as ccode
FROM (SELECT tcode, tarea, tname, tid,  player, paycode, tmatch, ttime
FROM team
WHERE tcode='t20180308003') a JOIN cmember c
ON a.tid = c.cid


select concat('c',(cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))),
			(select if(
					   (
					    select ifnull(max(tcode), (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from team as T
					    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from team as T2) 
					   ) =(select max(tcode) from team as T3) COLLATE utf8_general_ci,
					   (select right(cast(((select right(max(tcode),3) from team as T4)+1)+1000 as char(4)),3)),
					   '001'
					  )
			)) 

delete from club where cid='clevel3'
update member set ccode='C00000000000' where id='soso2'
select ccode from member where id='soso2'
select * from member where id='soso1'
select * from club
INSERT INTO cmember(ccode, id) 
value (c)
delete from cmember where cid='soso3'
delete from club where cid='soso2'


select concat('c',(cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))), 
		(select if( 
				   ( 
			    select ifnull(max(tcode) COLLATE utf8_general_ci , (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from team as T 
			    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from team as T2) COLLATE utf8_general_ci 
			   ) =(select max(tcode) from team as T3) COLLATE utf8_general_ci, 
			   (select right(cast(((select right(max(tcode),3) from team as T4)+1)+1000 as char(4)),3)), '001') 
		)

