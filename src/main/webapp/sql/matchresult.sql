create table matchresult(
	mrno int not null AUTO_INCREMENT primary key,
	mcode int not null,
	hpoint int null,
	apoint int null
)ENGINE=MyISAM DEFAULT CHARSET="UTF8";


select * from matchresult
drop table matchresult;

select input from matchresult where mcode=2

SELECT writer, shooting, stamina, manner, pass, comment,
ifnull(sum(shooting+stamina+manner+pass)/4, 0) as avg FROM playeravg
WHERE player='clevel1' and mcode='1'

SELECT
ifnull(sum(shooting+stamina+manner+pass)/4, 0) as avg FROM playeravg
WHERE player='clevel1' and mcode='1'
	
SELECT B.tid, B.ttime, B.tname, B.tcode, m.mcode, m.input FROM
		(SELECT ttime, tname, tid, A.tcode FROM team JOIN(
					SELECT tcode FROM tmember WHERE tid='clevel1'
					)A  ON team.tcode=A.tcode)B JOIN matching m on B.tcode=m.home or B.tcode=m.away
			
					
					
					
					
					
					
					
					
SELECT writer, shooting, stamina, manner, pass, comment
FROM playeravg
WHERE player='clevel1' and mcode='1'					
					
					
SELECT writer, shooting, stamina, manner, pass, comment,
ifnull((shooting+stamina+manner+pass)/4, 0) as avg FROM playeravg
WHERE player='clevel1' and mcode='1'

SELECT
ifnull(sum(shooting+stamina+manner+pass)/4, 0) as avg FROM playeravg
WHERE player='clevel1' and mcode='1'	

SELECT writer, shooting+stamina+manner+pass, (shooting+stamina+manner+pass)/4
FROM playeravg
WHERE player='clevel1' and mcode='1'




					
					
					
					
					
					
					
					
					
					
					
					
