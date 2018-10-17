create table matching(
	mcode int not null auto_increment primary key,
	home varchar(12) not null,
	away varchar(12) not null,
	input varchar(2) not null default 'n',
	constraint match_home FOREIGN KEY (home) REFERENCES team(tcode),
	constraint match_away FOREIGN KEY (away) REFERENCES team(tcode)
)

drop table matching

alter table matching add input varchar(2) not null default 'n'

insert into matching(home, away)
value('t20180319001', 't20180319004');

delete FROM matching where mcode=2

select * from matching
select * from mbasket
select * from team
--team 선택 list
SELECT tcode, tname 
FROM team D JOIN (SELECT home
  				   FROM mbasket A JOIN (SELECT tcode FROM tmember WHERE tid='soldesk2') B  
				   ON A.home=B.tcode AND A.away='yet') C 
 ON D.tcode=C.home
 
 --matching에 클럽 엠블럼 띄우기
SELECT tid, tname, cposter FROM club A JOIN (SELECT tid, tname FROM team WHERE tcode='t20180317003') B ON A.cid=B.tid

--경기시간, 장소, 인원이 맞고 pcode가 나와 다른 팀 불러오기
SELECT ttime, tarea, player, pcode FROM team

-- 상대찾기
--1)pcode가 있다
SELECT home, pcode FROM mbasket where home='t20180317001'

--1.1)팀에서 pcode가 없는 ttime, tarea, player를 불러옴
SELECT ttime, tarea, player, pcode, tcode 
FROM team A JOIN (SELECT home FROM mbasket where pcode='null') B
ON A.tcode=B.home

--1.2)내 팀의 ttime, tarea, player를 불러온다
SELECT ttime, tarea, player FROM team WHERE tcode='t20180315001'

--1.3)ttime, tarea, player가 같은걸 불러온다
SELECT H.cposter, G.tname, G.tcode, G.tid
FROM club H JOIN 
(SELECT ccode, tname, tcode, tid
FROM cmember E JOIN (SELECT C.ttime, C.tarea, C.player, C.tcode, C.tname, C.tid
	 				 FROM (SELECT ttime, tarea, player, tcode, tname, tid
						   FROM team A JOIN (SELECT home FROM mbasket where pcode='null' AND away='yet') B
						   ON A.tcode=B.home) C JOIN (SELECT ttime, tarea, player FROM team WHERE tcode='t20180315001') D
					 ON C.ttime=D.ttime AND C.tarea=D.tarea AND C.player=D.player) F
ON F.tid=E.cid) G 	
ON G.ccode=H.ccode

--2)pcode가 없다
SELECT E.cposter, F.tname
FROM club E JOIN (SELECT C.ttime, C.tarea, C.player, C.tcode, C.tname, C.tid
FROM (SELECT ttime, tarea, player, tcode, tname, tid
FROM team A JOIN (SELECT home FROM mbasket where NOT pcode='null' AND away='yet') B
ON A.tcode=B.home) C JOIN (SELECT ttime, tarea, player FROM team WHERE tcode='t20180317001') D
ON C.ttime=D.ttime AND C.tarea=D.tarea AND C.player=D.player) F
ON F.tid=E.cid

select * from mbasket
select * from team

--매치신청을 수락해야하는 리스트
SELECT home, away, pcode FROM mbasket WHERE NOT pcode='null' AND NOT away='yet'


SELECT tcode, tname 
FROM team A JOIN (SELECT away FROM mbasket WHERE NOT pcode='null' AND NOT away='yet') B
ON A.tcode=B.away

SELECT D.tcode, D.tname 
FROM team D JOIN (SELECT tcode
  				   FROM tmember A JOIN (SELECT away FROM mbasket WHERE NOT pcode='null' AND NOT away='yet') B  
				   ON A.tcode=B.away AND A.tid='soldesk2') C 
ON D.tcode=C.tcode

--매칭수락 대기중인 팀의 cposter와 tname
SELECT cposter, tname, tid, tcode
FROM club D	JOIN (SELECT A.tid, A.tname, A.tcode
		          FROM team A JOIN (SELECT away, pcode 
		           				    FROM mbasket 
		           				    WHERE NOT pcode='null' AND NOT away='yet' AND away='t20180317001') B
			      ON A.tcode=B.away) C
ON C.tid=D.cid

