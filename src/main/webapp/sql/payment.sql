create table payment(	
	pcode varchar(15) not null primary key,
 	pid varchar(10) not null,
	stacode int not null,
	grcode int not null,
	pdate varchar(20) not null,
	ptime  varchar(20) not null,
	cost  int not null	
)

select * from payment;

drop table payment;

insert into payment(pcode, pid, stacode, grcode, pdate, ptime, cost)
value('201803190957003', 'soldesk3',1,1,'20180325', '17,18',35000)

update payment
set pid='soldesk2'
where pcode='201803190957002'


insert into payment(stacode, grcode, RVdate, RVtime, RVpay,  id, costdate)
select stacode, grcode, RVdate, RVtime, RVpay, id, now() from basket
where id='rgh'


select * from basket	
where id='rgh10041'

select * from payment
where id='rgh'
		
		
select concat((CAST(DATE_FORMAT(now(),'%Y%m%d%H%i') AS CHAR(12))), 

(select if(

		   (

		    select ifnull(max(pcode), (select concat((select CAST(DATE_FORMAT(now(),'%Y%m%d%H%i') AS CHAR(12)))) ) ) from payment as P1

		    where CAST(DATE_FORMAT(now(),'%Y%m%d%H%i') AS CHAR(12)) = (select substring(ifnull(max(pcode),1),1,12) from payment as P2 ) COLLATE utf8_general_ci

		   ) COLLATE utf8_general_ci =(select max(pcode) from payment as P3) COLLATE utf8_general_ci,

		   (select right(cast(((select right(max(pcode),3) from payment as P4)+1)+1000 as char(4)),3)),

		   '001'

		  )

))		
		
		
		
		
		
		
		
insert into payment(ccode,costdate)
		select ccode,costdate from basket		
		
		
		
		
		