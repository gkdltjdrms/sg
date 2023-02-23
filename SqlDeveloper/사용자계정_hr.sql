select employee_id, last_name, salary from employees;

select employee_id as 사원번호 , last_name as "이름", salary as "급 여"from employees;

select employee_id as 사원번호 , last_name as "이름", salary*12 as "연봉"from employees;

select employee_id as 사원번호  , first_name||'  '||last_name as "이 름"
, salary*12 || '달러' from employees;

select last_name || ' is a' || job_id
from employees;


select first_name||'  '||last_name as "이 름" from employees;

select distinct department_id from employees;

select last_name, hire_date, department_id from employees
where department_id=10 or department_id=90;

select last_name, hire_date, salary from employees
where salary>=2500 and salary<3500;

select * from employees where last_name='King';

select * from employees where lower(last_name)='king';

select last_name, job_id, department_id from employees where job_id like '%MAN%';

select last_name, job_id, department_id from employees where job_id like 'IT%';


--커미션을 받는 사원들의 이름과 급여, 커미션을 출력하시오
select last_name, salary, commission_pct from employees 
where commission_pct is not null;

--커미션을 받지 않는 사람들의 이름과 급여, 커미션 출력
select last_name, salary, commission_pct
from employees
where commission_pct is null;




select employee_id, last_name, job_id from employees
where job_id='FI_MGR' or job_id='FI_ACCOUNT';

select employee_id, last_name, job_id from employees
where job_id in('FI_MGR', 'FI_ACCOUNT');

select employee_id, last_name, salary from employees
where salary>=10000 and salary<=20000;

select employee_id, last_name, salary from employees
where salary between 10000 and 20000;


--exam 3 
--[문제3] 급여가 2500이하 이거나 3000이상이면서 90번 부서인 사원의 이름, 급여, 부서ID를 출력하시오.
--조건1) 제목은 사원명, 월급, 부서코드로 하시오
--조건2) 급여 앞에 $를 붙이시오
--조건3) 사원명은 first_name과 last_name을 연결해서 출력하시오
select first_name||'  '||last_name as 사원명,
    '$'||
    salary as 월급,
    department_id as 부서코드
    from employees
    where (salary<=2500 or salary>=3000) and department_id=90;

-- 업무id 의 개수를 확인할수 있다.
select distinct job_id from employees;


--
--exam4
select last_name as 이름,
job_id as "업무 id",
salary|| '원' as "급여"
from employees
where salary > 10000 and
job_id in('SA_REP', 'AD_PRES');
--exam5
select distinct job_id from employees;

--exam6
select employee_id as "사원번호",
first_name||'  '||last_name as "이름",
hire_date as "입사일" from employees
where hire_date between '2005-01-01' and '2005-12-31';








