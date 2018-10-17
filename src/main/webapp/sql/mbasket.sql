create table mbasket(
	home varchar(12) not null,
	away varchar(12) not null default 'yet',
	pcode varchar(15) not null
)

select tname, tcode, pcode FROM team
--매치등록 누르면
insert into mbasket(home, pcode)
value('t20180315001','201803150517001')


select home, away,pcode from mbasket

UPDATE mbasket
SET pcode='null', away='yet'
WHERE home='t20180319002'

UPDATE mbasket
SET away='yet'
WHERE home='t20180319002'


delete from mbasket where home='t20180314001'


--매치 신청 하면
--1)awaylist에서 선택된 팀코드 받아와서 mbasket테이블 away칼럼에 넣어준다
UPDATE mbasket
SET pcode='201803192315001'
where home='t20180319001'

--매치 거절하면 away에 tcode를 넣어 away로 변경
UPDATE mbasket
SET away='yet'
WHERE home='t20180319002'

--매치 수락하면 match 테이블로 이동
insert into matching(home, away)
select home, away from mbasket where away='t20180319001' AND home='t20180317001'

select * from mbasket
select * from team where tcode='t20180315001'