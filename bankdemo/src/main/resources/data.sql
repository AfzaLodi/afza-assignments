insert into Account values (111111111,'AUSSavings111','Savings','2020-03-15','AUD',50123.00);

insert into Account values (222222222,'AUSCurrent222','Current','2019-10-18','AUD',60123.00);

insert into Account values (333333333,'INDSavings333','Savings','2018-06-27','IND',70123.00);



insert into Transaction values (1,10.00,'AUD',null,'Amount credited','Credit','2020-03-15',111111111);
insert into Transaction values (2,null,'AUD',20.00,'Amount debited','Debit','2020-02-16',111111111);
insert into Transaction values (3,30.00,'AUD',null,'','Credit','2020-01-17',111111111);

insert into Transaction values (4,100.00,'AUD',null,'Some amount credited','Credit','2019-10-06',222222222);
insert into Transaction values (5,200.00,'AUD',null,'Some amount credited','Credit','2019-09-07',222222222);

insert into Transaction values (6,3000.00,'INR',null,'Account credited with amount','Credit','2018-06-22',333333333);
insert into Transaction values (7,null,'INR',1000.00,'Account debited with amount','Debit','2018-05-05',333333333);