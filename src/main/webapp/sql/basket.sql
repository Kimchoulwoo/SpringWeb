create table basket(
	stacode int not null,
	grcode int not null,
	pdate varchar(20) not null,
	ptime  varchar(20) not null,
	cost  int not null,	--가격
	basketdate datetime not null,
	pid varchar(10) not null,
	--KF
	--constraint basket_id FOREIGN key (pid) references member(id),
    --constraint basket_stacode FOREIGN KEY (stacode) REFERENCES stadium(stacode),
    -- constraint basket_grcode FOREIGN KEY (grcode) REFERENCES ground(grcode)
)
--MySQL 한글 인코딩
ENGINE = MyISAM DEFAULT CHARSEX ="UTF8";


drop table basket;

insert into basket(stacode,grcode,rvdate,rvtime,rvpay,basketdate,id)
value('1','1','2018-03-03','13,14,15','35000',now(),'rgh')

select (select staname from stadium where stacode='2'),(select grname from ground where grcode='2'),stacode,grcode,rvdate,rvtime,rvpay,basketdate,id  from basket


(select grname from ground where grcode='2')



insert into poket (stacode,grcode,rvdate,rvpay)
value('1','1','20130309','13,14,15',now(),(SELECT IFNULL(MAX(poketnum),0)+1 FROM poket AS TB));



select basketno,stacode, grcode, rvdate, rvtime, rvpay,  id, basketdate from basket;
