create table team(
	tcode varchar(12) not null primary key,
	tarea varchar(20) not null,
	tname varchar(20) not null,
	tid varchar(10) not null,
	player int not null,
	pcode varchar(15) null,
	tmatch varchar(1) not null default 'n',
	ttime varchar(12) not null
);

alter table team change paycode pcode varchar(15)

alter table team drop(ttime);

drop table team;

select * from team;

UPDATE team
SET pcode='201803150517001'
WHERE tid='soldesk'

UPDATE team
SET pcode='201803190957003'
WHERE tname='team3'

INSERT INTO team(tcode, tarea, tname, tid,  player, paycode, ttime)
VALUES('t20180308001','서울 종로구', '한판뜨자', 'soldesk',  5,'201803080240001', '2018032615');

INSERT INTO team(tcode,tarea, tname, tid, player, ttime)
VALUES('t20180308002','서울 종로구', '그래', 'soldesk2', 5, '2018032615');

INSERT INTO team(tcode,tarea, tname, tid, player, ttime)
VALUES('t20180308003','서울 마포구', '다나와', 'soldesk6', 5, '2018032615');

INSERT INTO team(tcode,tarea, tname, tid, player, ttime)
VALUES('t20180308004','경기 구리시', '친선', 'choul3416', 5, '2018032709');

delete from team where tcode='t20180315005';


update team
set ttime='2018031723'
where tname='team'

SELECT tcode, tid
FROM team
ORDER BY tcode DESC;



select concat('t',(cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))),
(select if(
		   (
		    select ifnull(max(tcode), (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from team as T1
		    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from team as T2) COLLATE utf8_general_ci
		   ) COLLATE utf8_general_ci =(select max(tcode) from team as T3) COLLATE utf8_general_ci,
		   (select right(cast(((select right(max(tcode),3) from team as T4)+1)+1000 as char(4)),3)),
		   '001'
		  )
))

select * from team;

--tcode 자동 생성
INSERT INTO team(tcode,tarea, tname, tid, player, ttime)
VALUES(            
           (
			select concat('t',(cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))),
			(select if(
					   (
					    select ifnull(max(tcode) COLLATE utf8_general_ci , (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from team as T
					    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from team as T2) COLLATE utf8_general_ci
					   )=(select max(tcode) from team as T3) COLLATE utf8_general_ci,
					   (select right(cast(((select right(max(tcode),3) from team as T4)+1)+1000 as char(4)),3)),
					   '001'
					  )
			))           
           ),'서울 종로구', '그래', 'soldesk2', 5, '2018032615');



select concat('t',(cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))),
			(select if(
					   (
					    select ifnull(max(tcode), (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from team as T
					    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from team as T2) 
					   ) =(select max(tcode) from team as T3) COLLATE utf8_general_ci,
					   (select right(cast(((select right(max(tcode),3) from team as T4)+1)+1000 as char(4)),3)),
					   '001'
					  )
			))         

select A.tid from tmember A
JOIN (select tid from team where tcode='t20180319001') B
ON NOT A.tid=B.tid
where tcode='t20180319001'

SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime
FROM team
where ttime > DATE_FORMAT(now(),'%Y%m%d%H')
ORDER BY tcode DESC


select CAST(DATE_FORMAT(now(),'%Y%m%d%H%i'))

SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter FROM club D JOIN
(SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, ccode
FROM team A JOIN (SELECT ccode, cid FROM cmember) B
ON A.tid=B.cid) C ON C.ccode=D.ccode
WHERE ttime > DATE_FORMAT(now(),'%Y%m%d%H')
ORDER BY tcode DESC


 SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter  
 FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter 
 		  FROM(SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter 
 		           FROM club D JOIN (SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, ccode
											FROM team A JOIN (SELECT ccode, cid FROM cmember) B
				   ON A.tid=B.cid) C ON C.ccode=D.ccode
		  WHERE ttime > DATE_FORMAT(now(),'%Y%m%d%H')
		  ORDER BY tcode DESC 		  
 		  ) E, (SELECT @ROWNUM :=0) F)G 
 WHERE G.ROW>=1 AND G.ROW<=5
 
SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter 
FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter 
			  FROM(SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter 
				           FROM club D JOIN (SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, ccode 
													FROM team A JOIN (SELECT ccode, cid FROM cmember) B 
							   ON A.tid=B.cid) C ON C.ccode=D.ccode			
							   WHERE ttime > DATE_FORMAT(now(),'%Y%m%d%H')
		  ORDER BY tcode DESC ) E, (SELECT @ROWNUM :=0) F)G 
  WHERE G.ROW>=1 AND G.ROW<=10

delete from cmember where ccode='soldesk18'

