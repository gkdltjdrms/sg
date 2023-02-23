select * from tab;
purge recyclebin;
select * from 연산;
select * from dbtest;

update dbtest set age=age+1 where name like '%홍%';
-- insert, update, delete LOCK이 걸려 있기 때문에 반드시 커밋을 해줘야한다.

insert into dbtest values('홍길동', 25, 185.3, sysdate);
update dbtest set age=30 where name='홍길동';
commit;










select * from emp;
-- 김씨 레코드만 추출

select * from emp where ename like '김%';

--2000년 이전에 입사한 사원만 검색

select * from emp where hdate < '2000-01-01%';

--ENO(번호), ENAME(이름), SAL(월급)으로 검색
--단 1달러= 1234원 일때 월급 원으로 검색하시오
select eno as 번호,
    ename as "이름",
    sal*1234 as "월 급"
from emp;

--커미션(COM) 을 받지 않은 사람은 누구인지 검색하시오
select * from emp WHERE COMM=0 or COMM is NULL;

--개발부의 레코드를 월급으로 내림차순 하시오 
select * from emp where job='개발' ORDER BY SAL DESC;

select * from emp where sal




create table dbtest(
name varchar2(15), -- char(고정형), varchar2(가변형)
age number,
height number(10,2),
logtime date);

select * from dbtest;

select * from recyclebin;
flashback table 연산 to before drop;

create sequence test increment by 2 start with 1 maxvalue 9 cycle nocache;
select test.nextval from dual;
select test.currval from dual;
select * from user_sequences;

create SEQUENCE exam nocycle nocache;
drop sequence test;


select * from 연산;
show recyclebin;

commit;

insert into dbtest(name,age,height,logtime) values('홍길동',25,185.567,sysdate);
insert into dbtest(name,age,height,logtime) values('Hong',30,175.56,sysdate);
insert into dbtest(name,age) values('희동이',3);
insert into dbtest(name, height) values('홍당무', 168.89);
insert into dbtest values('분홍신',5,123.5,sysdate);
insert into dbtest(name) values('진분홍');