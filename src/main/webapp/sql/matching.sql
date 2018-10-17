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
--team ���� list
SELECT tcode, tname 
FROM team D JOIN (SELECT home
  				   FROM mbasket A JOIN (SELECT tcode FROM tmember WHERE tid='soldesk2') B  
				   ON A.home=B.tcode AND A.away='yet') C 
 ON D.tcode=C.home
 
 --matching�� Ŭ�� ���� ����
SELECT tid, tname, cposter FROM club A JOIN (SELECT tid, tname FROM team WHERE tcode='t20180317003') B ON A.cid=B.tid

--���ð�, ���, �ο��� �°� pcode�� ���� �ٸ� �� �ҷ�����
SELECT ttime, tarea, player, pcode FROM team

-- ���ã��
--1)pcode�� �ִ�
SELECT home, pcode FROM mbasket where home='t20180317001'

--1.1)������ pcode�� ���� ttime, tarea, player�� �ҷ���
SELECT ttime, tarea, player, pcode, tcode 
FROM team A JOIN (SELECT home FROM mbasket where pcode='null') B
ON A.tcode=B.home

--1.2)�� ���� ttime, tarea, player�� �ҷ��´�
SELECT ttime, tarea, player FROM team WHERE tcode='t20180315001'

--1.3)ttime, tarea, player�� ������ �ҷ��´�
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

--2)pcode�� ����
SELECT E.cposter, F.tname
FROM club E JOIN (SELECT C.ttime, C.tarea, C.player, C.tcode, C.tname, C.tid
FROM (SELECT ttime, tarea, player, tcode, tname, tid
FROM team A JOIN (SELECT home FROM mbasket where NOT pcode='null' AND away='yet') B
ON A.tcode=B.home) C JOIN (SELECT ttime, tarea, player FROM team WHERE tcode='t20180317001') D
ON C.ttime=D.ttime AND C.tarea=D.tarea AND C.player=D.player) F
ON F.tid=E.cid

select * from mbasket
select * from team

--��ġ��û�� �����ؾ��ϴ� ����Ʈ
SELECT home, away, pcode FROM mbasket WHERE NOT pcode='null' AND NOT away='yet'


SELECT tcode, tname 
FROM team A JOIN (SELECT away FROM mbasket WHERE NOT pcode='null' AND NOT away='yet') B
ON A.tcode=B.away

SELECT D.tcode, D.tname 
FROM team D JOIN (SELECT tcode
  				   FROM tmember A JOIN (SELECT away FROM mbasket WHERE NOT pcode='null' AND NOT away='yet') B  
				   ON A.tcode=B.away AND A.tid='soldesk2') C 
ON D.tcode=C.tcode

--��Ī���� ������� ���� cposter�� tname
SELECT cposter, tname, tid, tcode
FROM club D	JOIN (SELECT A.tid, A.tname, A.tcode
		          FROM team A JOIN (SELECT away, pcode 
		           				    FROM mbasket 
		           				    WHERE NOT pcode='null' AND NOT away='yet' AND away='t20180317001') B
			      ON A.tcode=B.away) C
ON C.tid=D.cid

