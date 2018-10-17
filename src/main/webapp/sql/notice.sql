create table notice(
	noticeno int not null AUTO_INCREMENT primary key,
	subject varchar(50) not null,
	content varchar(255) not null,
	readcnt int default 0,
	regdt datetime not null
)

drop table notice

INSERT INTO notice(subject, content, regdt)
VALUE('����','����',now())

SELECT noticeno, subject, content, readcnt, regdt
FROM notice
ORDER BY noticeno DESC;