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
value('m001', 1,2,5,2,'�ʹ�����','soldesk','soldesk66');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 1,2,5,2,'�ʹ�����','soldesk','soldesk4');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,3,4,5,'����','soldesk','soldesk7');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 4,2,3,4,'����','soldesk','soldesk16');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,1,1,2,'�ʹ�����','soldesk4','soldesk');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,3,5,5,'����','soldesk4','soldesk7');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 1,4,3,4,'����','soldesk4','soldesk16');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,1,5,'�ʹ�����','soldesk7','soldesk1');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 1,1,1,1,'����','soldesk7','soldesk4');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,4,3,2,'����','soldesk7','soldesk16');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,5,5,5,'�ʹ�����','soldesk16','soldesk');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,2,1,1,'����','soldesk16','soldesk4');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,5,3,'����','soldesk16','soldesk7');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 5,5,5,5,'����','soldesk555','soldesk55');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,5,5,5,'�ʹ�����','soldesk5','soldesk');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 2,2,1,1,'����','soldesk5','soldesk4');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,5,3,'����','soldesk5','soldesk7');

insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,5,3,'����','soldesk5','soldesk7');


insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,1,5,3,'����','soldesk6','soldesk7');


insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,2,1,1,'����','soldesk6','soldesk7');


insert into playeravg(mcode, shooting, stamina, manner, pass, comment, player, writer)
value('m001', 3,3,4,3,'����','soldesk13','soldesk7');



--��� ���� ���ϱ�
--1) �Ѱ�� ����
select (shooting+stamina+manner+pass)/4 from playeravg where pgno=1;

--2)�ѻ���� ���� ����
select count(player) from playeravg where player='soldesk'

--3)�ѻ���� ��� ����
select sum((shooting+stamina+manner+pass)/4)/(select count(player) from playeravg where player='soldesk') as avg
from playeravg
where player='soldesk'

--�� �ɹ��� ���� ����Ʈ
--1)�Ҽ� ������ ���̵�, ����, �Ҽ�Ŭ��
SELECT DISTINCT A.id, A.birth, B.cname 
FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
		  FROM tmember TM JOIN member M 
		  on TM.tid=M.id where tcode ='t20180308001') A
JOIN club B ON A.ccode=B.ccode
ORDER BY A.id ASC;

--�ѻ���� ��� ���� ���ϱ�
select sum((shooting+stamina+manner+pass)/4)/(select count(player) from playeravg where player='soldesk') as avg
from playeravg
where player='soldesk'

--���� ���̺���� ����
SELECT DISTINCT D.id, D.birth, D.cname, E.shooting,E.stamina,E.manner,E.pass
	  FROM (SELECT DISTINCT A.id, A.birth, B.cname
			FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
		  		  FROM tmember TM JOIN member M 
		          on TM.tid=M.id where tcode ='t20180308002') A
		    JOIN club B ON A.ccode=B.ccode) D 
JOIN playeravg E ON D.id=E.player 


--������
SELECT DISTINCT D.id, D.birth, D.cname, ifnull(sum(E.shooting+E.stamina+E.manner+E.pass)/(count(D.id)*4), 0) as avg
	  FROM (SELECT DISTINCT A.id, A.birth, B.cname
			FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
		  		  FROM tmember TM JOIN member M 
		          on TM.tid=M.id where tcode ='t20180308004') A
		    JOIN club B ON A.ccode=B.ccode) D 
LEFT JOIN playeravg E ON D.id=E.player 
group by D.id

--���� ����������� �� ��� ����
SELECT sum(F.avg)/count(F.id)
FROM(SELECT DISTINCT D.id, D.birth, D.cname, sum(E.shooting+E.stamina+E.manner+E.pass)/(count(D.id)*4) as avg
	  FROM (SELECT DISTINCT A.id, A.birth, B.cname
			FROM (SELECT DISTINCT M.id, M.birth, M.ccode 
		  		  FROM tmember TM JOIN member M 
		          on TM.tid=M.id where tcode ='t20180308002') A
		    JOIN club B ON A.ccode=B.ccode) D 
JOIN playeravg E ON D.id=E.player 
group by D.id) F


