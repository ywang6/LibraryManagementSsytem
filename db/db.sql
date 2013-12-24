drop table losebook;
drop table exceedtime;
drop table manager;
drop table record;
drop table orderreport;
drop table student;
drop table book;

create table losebook
(
   LBNO int primary key,
   StuNO int,
   BookNO int,
   BookName varchar(50)
);

create table exceedtime
(
   StuNO int,
   BookNO int,
   BookName varchar(50),
   DelayTime int,
   primary key(StuNO,BookName)
);

create table manager
(
   mgNo int primary key,
   permitted varchar(50),
   password varchar(50)
);

create table record
(
   BookNO int primary key,
   StuNO int,
   BorrowTime varchar(50),
   ReturnTime varchar(50),
   Borrowed varchar(50),
   Ordered varchar(50)
);

create table orderreport
(
   BookNO int primary key,
   StuName varchar(50),
   Class varchar(50),
   BookName varchar(50),
   StuNO int,
   Author varchar(50)
);


create table student
(
  StuNO int primary key,
  StuName varchar(50),
  StuAge int,
  StuSex varchar(50),
  Class varchar(50),
  Department varchar(50),
  Tel char(11),
  Permitted varchar(50),
  Password varchar(20)
);

create table book
(
   BookNO int primary key,
   BookName varchar(50),
   Author varchar(50),
   Publishment varchar(50),
   ButTime varchar(50),
   Borrowed varchar(50),
   Ordered varchar(50)
);
insert into exceedtime values(10001,10001,'oracle快速入门',50);
insert into exceedtime values(10002,10002,'数据库项目案例',50);
insert into exceedtime values(10003,10001,'oracle快速入门',50);
insert into exceedtime values(10004,10002,'数据库项目案例',50);

insert into manager values(1001,'1','1001');

insert into student values(10001,'陈小诗',20,'女','计算机1班','计算机系',2592921,'是','number1');
insert into student values(10002,'李飞',21,'女','计算机1班','计算机系',13730120123,'是','number2');
insert into student values(10003,'孙亚',20,'男','计算机1班','计算机系',13633654578,'是','number3');
insert into student values(10004,'何二',22,'男','计算机1班','计算机系',2568975,'是','number4');
insert into student values(10005,'唐雨',21,'女','计算机1班','计算机系',13936968956,'是','number5');
insert into student values(10006,'宋江',20,'男','计算机2班','计算机系',1234667,'是','number6');

insert into book values(10001,'oracle快速入门','王海亮','水利出版社','2003.1.12','否','否');
insert into book values(10002,'数据库项目案例','周兴华','清华出版社','2003.3.16','否','否');
insert into book values(10003,'大学英语','王海','外文出版社','2007.1.12','否','否');
insert into book values(10004,'体育','张亮','体育出版社','2003.1.12','否','否');
insert into book values(10005,'英语大全','jeans','外文出版社','2003.1.12','否','否');
insert into book values(10006,'计算机网络','谢希任','高教出版社','2003.11.04','否','否');
insert into book values(10007,'考验英语','李阳','文化出版社','2003.3.12','否','否');
insert into book values(10008,'数值分析','王文超','实践出版社','2008.1.15','否','否');
insert into book values(10009,'java me','秦一杰','人民邮电出版社','2004.1.12','否','否');
insert into book values(10010,'思想理论','吴俊','同济大学出版社','2003.1.12','否','否');
