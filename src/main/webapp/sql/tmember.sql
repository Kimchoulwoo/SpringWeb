create table tmember(
	tmemno int not null auto_increment primary key,
	tcode varchar(12) not null,
	tid varchar(10) null
);

drop table tmember;

SELECT max(tcode) FROM team WHERE tid='soldesk'

select * from tmember ORDER BY tcode DESC;

INSERT INTO tmember(tcode, tid)
VALUES('t20180308001', 'soldesk');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308001', 'soldesk4');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308001', 'soldesk7');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308001', 'soldesk16');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308002', 'soldesk2');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308002', 'soldesk5');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308003', 'soldesk6');

--1 2 4 5 6 7 16
INSERT INTO tmember(tcode, tid)
VALUES('t20180308001', 'soldesk15');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308002', 'soldesk14');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308003', 'soldesk13');

INSERT INTO tmember(tcode, tid)
VALUES('t20180308003', 'soldesk17');

delete from tmember where tid='soldesk16';

--팀 현재원
select tcode, tid from tmember where tid='soldesk2';

SELECT DISTINCT F.id, F.birth, F.cname, avg 
FROM team H JOIN 
(SELECT DISTINCT D.id, D.birth, D.cname, ifnull(sum(E.shooting+E.stamina+E.manner+E.pass)/(count(D.id)*4),0) as avg 
FROM (SELECT DISTINCT A.id, A.birth, B.cname 
	FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
	  		  FROM tmember TM JOIN member M 
	          on TM.tid=M.id where tcode ='t20180315001') A 
    JOIN club B ON A.ccode=B.ccode) D 
LEFT JOIN playeravg E ON D.id=E.player 
group by D.id) F
where tcode='t20180315001' AND ttime > DATE_FORMAT(now(),'%Y%m%d%H')

select tcode,ttime from team

--matching에서 mcode,home,away



SELECT C.mcode, D.tname, D.ttime
FROM team D JOIN (select mcode, home,away FROM matching A JOIN (
select tcode FROM tmember where tid='soldesk2') B
ON A.home=B.tcode OR A.away=B.tcode) C
ON C.home=D.tcode OR C.away=D.tcode

SELECT C.mcode, D.tname, D.ttime
FROM team D JOIN (select mcode, away FROM matching A JOIN (
select tcode FROM tmember where tid='soldesk2') B
ON A.away=B.tcode OR A.away=B.tcode) C
ON C.away=D.tcode

select mcode,C.ttime,C.tname, home, away FROM matching D JOIN
(select B.tcode,ttime,tname FROM team A JOIN (
select tcode FROM tmember where tid='soldesk2') B
ON A.tcode=B.tcode) C
ON D.home=C.tcode OR D.away=C.tcode

select DISTINCT G.mcode, G.tname, G.ttime, G.tcode, H.tid FROM
(SELECT DISTINCT mcode,D.tcode, tname, ttime FROM tmember D JOIN
(select mcode,tcode,tname, ttime FROM team A
JOIN (SELECT mcode,home, away FROM matching) B
ON A.tcode=B.home OR A.tcode=B.away) C
ON D.tcode=C.tcode) G JOIN 
(select mcode, tid FROM matching E JOIN(
select tcode, tid from tmember where tid='soldesk2') F
ON F.tcode=E.home OR F.tcode=E.away) H
ON G.mcode=H.mcode



select mcode, tid FROM matching E JOIN(
select tcode, tid from tmember where tid='soldesk2') F
ON F.tcode=E.home OR F.tcode=E.away

select DISTINCT G.mcode, G.tname, G.ttime, G.tcode, H.tid FROM
(SELECT DISTINCT mcode,D.tcode, tname, ttime FROM tmember D JOIN
(select mcode,tcode,tname, ttime FROM team A
JOIN (SELECT mcode,home, away FROM matching) B
ON A.tcode=B.home OR A.tcode=B.away) C
ON D.tcode=C.tcode) G JOIN 
(select tcode, tid FROM matching E JOIN(
select tcode, tid from tmember where tid='soldesk2') F
ON F.tcode=E.home OR F.tcode=E.away) H
ON G.tcode=H.tcode


