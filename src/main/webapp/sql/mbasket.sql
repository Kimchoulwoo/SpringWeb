create table mbasket(
	home varchar(12) not null,
	away varchar(12) not null default 'yet',
	pcode varchar(15) not null
)

select tname, tcode, pcode FROM team
--��ġ��� ������
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


--��ġ ��û �ϸ�
--1)awaylist���� ���õ� ���ڵ� �޾ƿͼ� mbasket���̺� awayĮ���� �־��ش�
UPDATE mbasket
SET pcode='201803192315001'
where home='t20180319001'

--��ġ �����ϸ� away�� tcode�� �־� away�� ����
UPDATE mbasket
SET away='yet'
WHERE home='t20180319002'

--��ġ �����ϸ� match ���̺�� �̵�
insert into matching(home, away)
select home, away from mbasket where away='t20180319001' AND home='t20180317001'

select * from mbasket
select * from team where tcode='t20180315001'