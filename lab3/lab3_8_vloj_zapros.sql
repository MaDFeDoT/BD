create view View_Cell_arrend as select * from cells where cell_number in (select cell_number from contracts);

