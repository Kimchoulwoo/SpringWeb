create table ground(
    stacode int   null,
    grcode  int   not null auto_increment PRIMARY KEY,
    grname  varchar(255) not null,
    grday   varchar(20)  not null,
    grtime  varchar(50)  not null,
    grlevel varchar(50) not null DEFAULT 'Y' ,
    
    constraint ground_stacode FOREIGN KEY (stacode) REFERENCES stadium(stacode)
    
);
select * from ground;
select stacode,grcode,grname,grday,grtime,grlevel,staopen,staclose
from ground
inner join stadium
on ground.stacode=stadium.stacode
where stacode='1'


update ground
set grname='1∞Ê±‚¿Â',grday='Weekdays only',grtime='6,7,8',grlevel='Y'
where stacode='1' AND grcode='1'

update ground
set grlevel='N'
where grcode='1'



drop table ground;

select * from ground where grlevel='Y';

delete from ground where grlevel='Y'


insert into ground(stacode,grcode,grname,grday,grtime,grlevel)
value('1',(select ifnull(max(grcode),0)+1 from ground as TB),'1ground,2ground','23','6,7,8','Y');




select stacode,grcode,grname,grday,grtime
from ground
where stacode='1'
order by grcode desc;

select mediano, title, rdate , poster , filename  , filesize , mview , mediagroup
from media
where mview = 'Y' and mediagroupno=?
order by mediano desc; 
