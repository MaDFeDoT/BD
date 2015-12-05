create view His_Ac_Items as select acsess_type as "Type", COUNT(acsess_type) as "Count" from history_cell group by acsess_type;
