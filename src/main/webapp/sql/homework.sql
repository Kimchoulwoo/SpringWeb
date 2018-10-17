select A.stacode, A.pid, count(A.pid) as cnt
from (select stacode, pid from payment where stacode=1) A group by A.pid order by cnt desc
