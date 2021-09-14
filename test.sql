select * from all_tables;

select * from reply;
select * from message;
select * from member;


--drop table member;
--drop table reply;
--drop table message;

-- ȸ��
create table member(
	memid varchar(15) primary key,
	name varchar(15),
	passwd varchar(10),
	day date default sysdate
)

-- �Խ���   mid -> auto 
create table message(
	mid int primary key,
	memid varchar(15),
	msg varchar(100),
	favcount int default 0,
	replycount int default 0,
	day date default sysdate
)

--��� rid -> auto
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



insert into member values('timo', 'Ƽ��', '1234', sysdate);
insert into message values((select nvl(max(mid),0)+1 from message), 'timo','���ۼ�', 1, 2, sysdate);
insert into reply values((select nvl(max(rid),0)+1 from reply),1,'timo',sysdate,'���1');
insert into reply values((select nvl(max(rid),0)+1 from reply),1,'timo',sysdate,'���2');
insert into message values((select nvl(max(mid),0)+1 from message), 'timo','�ڡڡ�', 2, 3, sysdate);
insert into reply values((select nvl(max(rid),0)+1 from reply),2,'timo',sysdate,'���1');
insert into reply values((select nvl(max(rid),0)+1 from reply),2,'timo',sysdate,'���2');
insert into reply values((select nvl(max(rid),0)+1 from reply),2,'timo',sysdate,'���3');
insert into message values((select nvl(max(mid),0)+1 from message), 'timo','Ȯ��', 2, 0, sysdate);

insert into message values((select nvl(max(mid),0)+1 from message), 'aa','���ξ���', 0, 2, sysdate);
insert into reply values((select nvl(max(rid),0)+1 from reply),3,'aa',sysdate,'���3');
insert into reply values((select nvl(max(rid),0)+1 from reply),3,'timo',sysdate,'���3');
