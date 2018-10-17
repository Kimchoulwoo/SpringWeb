create table bbs(
  bbsno    int      not null auto_increment primary key
 ,id    varchar(10)    not null
 ,subject  varchar(50)   not null
 ,content  varchar(255)  not null
 ,readcnt  int       default 0
 ,regdt    datetime not null
 ,grpno    int      not null
 ,indent   int       default 0
 ,ansnum   int       default 0
 ,ip       varchar(20)    not null
);

drop table bbs

INSERT INTO bbs(id, subject, content, passwd,
regdt, grpno, ip)
VALUES('1111', '1111', '1111', '1111', now(),
(SELECT IFNULL(MAX(bbsno),0)+1 FROM bbs AS TB), '11');

select * from bbs where bbsno=36;

delete from bbs where bbsno=38;