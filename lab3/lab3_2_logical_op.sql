create view selectContracts250 as select * from contracts where arend_cost like 250;
create view selectItemsPrice as select * from items where cost between 5000 and 50000;
create view selectAcess0_1 as select * from history_cell where acsess_type in (0, 1);
