create view Max_And_Sred as select max(end_date) as "max_date", avg(arend_cost) as "avg" from contracts;
