select * from all_tables;

select * from reply;
select * from message;
select * from member;


--drop table member;
--drop table reply;
--drop table message;

-- È¸¿ø
create table member(
	memid varchar(15) primary key,
	name varchar(15),
	passwd varchar(10),
	day date default sysdate
)

-- °Ô½ÃÆÇ   mid -> auto 
create table message(
	mid int primary key,
	memid varchar(15),
	msg varchar(100),
	favcount int default 0,
	replycount int default 0,
	day date default sysdate
)

--´ñ±Û rid -> auto
create table reply(
	rid int primary key,
	mid int,
	memid varchar(15),
	day date default sysdate,
	rmsg varchar(50),
	constraint msgrp foreign key (mid) references message (mid) on delete cascade
)

select * from (select * from message where mid)

select * from reply;
select * from message;
select * from member;



insert into member values('timo', 'Æ¼¸ð', '1234', sysdate);
insert into message values((select nvl(max(mid),0)+1 from message), 'timo','±ÛÀÛ¼º', 1, 2, sysdate);
insert into reply values((select nvl(max(rid),0)+1 from reply),1,'timo',sysdate,'´ñ±Û1');
insert into reply values((select nvl(max(rid),0)+1 from reply),1,'timo',sysdate,'´ñ±Û2');
insert into message values((select nvl(max(mid),0)+1 from message), 'timo','¡Ú¡Ú¡Ú', 2, 3, sysdate);
insert into reply values((select nvl(max(rid),0)+1 from reply),2,'timo',sysdate,'´ñ±Û1');
insert into reply values((select nvl(max(rid),0)+1 from reply),2,'timo',sysdate,'´ñ±Û2');
insert into reply values((select nvl(max(rid),0)+1 from reply),2,'timo',sysdate,'´ñ±Û3');
insert into message values((select nvl(max(mid),0)+1 from message), 'timo','È®ÀÎ', 2, 0, sysdate);

insert into message values((select nvl(max(mid),0)+1 from message), 'aa','»õ·Î¾´±Û', 0, 2, sysdate);
insert into reply values((select nvl(max(rid),0)+1 from reply),3,'aa',sysdate,'´ñ±Û3');
insert into reply values((select nvl(max(rid),0)+1 from reply),3,'timo',sysdate,'´ñ±Û3');
