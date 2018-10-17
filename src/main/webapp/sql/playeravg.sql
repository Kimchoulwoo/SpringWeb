create table playeravg(
	pgno      int not null auto_increment PRIMARY KEY,
	mcode     int not null,
	shooting  int not null,
	stamina   int not null,
	manner    int not null,
	pass      int not null,
	comment   varchar(50) not null,
	player    varchar(10) not null,
	writer    varchar(10) not null,
	constraint playeravg_mcode FOREIGN KEY (mcode) REFERENCES matching(mcode)
)

drop table playeravg

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 1,2,5,2,'너무못함','soldesk','soldesk66');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 1,2,5,2,'너무못함','soldesk','soldesk4');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,3,4,5,'보통','soldesk','soldesk7');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 4,2,3,4,'잘함','soldesk','soldesk16');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,1,1,2,'너무못함','soldesk4','soldesk');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,3,5,5,'보통','soldesk4','soldesk7');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 1,4,3,4,'잘함','soldesk4','soldesk16');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,1,5,'너무못함','soldesk7','soldesk1');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 1,1,1,1,'보통','soldesk7','soldesk4');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,4,3,2,'잘함','soldesk7','soldesk16');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,5,5,5,'너무못함','soldesk16','soldesk');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,2,1,1,'보통','soldesk16','soldesk4');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,5,3,'잘함','soldesk16','soldesk7');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 5,5,5,5,'잘함','soldesk555','soldesk55');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,5,5,5,'너무못함','soldesk5','soldesk');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,2,1,1,'보통','soldesk5','soldesk4');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,5,3,'잘함','soldesk5','soldesk7');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,5,3,'잘함','soldesk5','soldesk7');


insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,1,5,3,'잘함','soldesk6','soldesk7');


insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,1,1,'잘함','soldesk6','soldesk7');


insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,3,4,3,'잘함','soldesk13','soldesk7');



--평균 평점 구하기
--1) 한경기 평점
select (shooting+stamina+manner+pass)/4 from playeravg where pgno=1;

--2)한사람의 평점 갯수
select count(player) from playeravg where player='soldesk'

--3)한사람의 평균 평점
select sum((shooting+stamina+manner+pass)/4)/(select count(player) from playeravg where player='soldesk') as avg
from playeravg
where player='soldesk'

--팀 맴버의 정보 리스트
--1)소속 팀원의 아이디, 생일, 소속클럽
SELECT DISTINCT A.id, A.birth, B.cname 
FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
		  FROM tmember TM JOIN member M 
		  on TM.tid=M.id where tcode ='t20180308001') A
JOIN club B ON A.ccode=B.ccode
ORDER BY A.id ASC;

--한사람의 평균 평점 구하기
select sum((shooting+stamina+manner+pass)/4)/(select count(player) from playeravg where player='soldesk') as avg
from playeravg
where player='soldesk'

--평점 테이블까지 조인
SELECT DISTINCT D.id, D.birth, D.cname, E.shooting,E.stamina,E.manner,E.pass
	  FROM (SELECT DISTINCT A.id, A.birth, B.cname
			FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
		  		  FROM tmember TM JOIN member M 
		          on TM.tid=M.id where tcode ='t20180308002') A
		    JOIN club B ON A.ccode=B.ccode) D 
JOIN playeravg E ON D.id=E.player 


--마지막
SELECT DISTINCT D.id, D.birth, D.cname, ifnull(sum(E.shooting+E.stamina+E.manner+E.pass)/(count(D.id)*4), 0) as avg
	  FROM (SELECT DISTINCT A.id, A.birth, B.cname
			FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
		  		  FROM tmember TM JOIN member M 
		          on TM.tid=M.id where tcode ='t20180308004') A
		    JOIN club B ON A.ccode=B.ccode) D 
LEFT JOIN playeravg E ON D.id=E.player 
group by D.id

--팀원 평균평점으로 팀 평균 평점
SELECT sum(F.avg)/count(F.id)
FROM(SELECT DISTINCT D.id, D.birth, D.cname, sum(E.shooting+E.stamina+E.manner+E.pass)/(count(D.id)*4) as avg
	  FROM (SELECT DISTINCT A.id, A.birth, B.cname
			FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
		  		  FROM tmember TM JOIN member M 
		          on TM.tid=M.id where tcode ='t20180308002') A
		    JOIN club B ON A.ccode=B.ccode) D 
JOIN playeravg E ON D.id=E.player 
group by D.id) F


