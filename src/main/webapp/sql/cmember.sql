create table cmember(
	cmemno int not null auto_increment primary key,
	ccode varchar(12) not null,
	cid varchar(10) not null
);

select * from cmember;

INSERT INTO cmember(ccode, cid)
VALUES('c20180308001', 'soldesk1');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308002', 'soldesk2');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308003', 'soldesk3');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308001', 'soldesk4');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308002', 'soldesk5');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308003', 'soldesk6');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308001', 'soldesk7');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308002', 'soldesk8');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308003', 'soldesk9');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308001', 'soldesk10');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308002', 'soldesk11');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308003', 'soldesk12');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308001', 'soldesk13');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308002', 'soldesk14');

INSERT INTO cmember(ccode, cid)
VALUES('c20180308003', 'soldesk15');


drop table cmember;

delete from cmember where ccode='c20180319001';

alter table cmember modify cid varchar(20)

