create procedure insert_items (item_ID integer, contracts_ID integer,  name varchar(50), cost integer)
as
begin
    insert into items(item_ID, contract_ID, name, cost) values (:item_ID, :contracts_ID, :name, :cost);
end;
create procedure insert_history_cell (ID integer, cell_number integer,  acsess_type integer, date_acsess date, item_id integer)
as
begin
    insert into history_cell(ID, cell_number, acsess_type, date_acsess, item_ID) values (:ID, :cell_number, :acsess_type, :date_acsess, :item_ID);
end;
create procedure insert_cells(cell_number integer, repository_ID integer, status varchar(15))
as
begin
    insert into cells(cell_number, repository_ID, status) values (:cell_number, :repository_ID, :status);
end;
create procedure insert_repository(repository_ID integer,  adress varchar(100), director varchar(32))
as
begin
    insert into repository(repository_ID, adress, director) values (:repository_ID, :adress, :director);
end;
create procedure insert_contract(contract_ID integer,  start_date date,  end_date date,  arend_cost integer,  cell_number integer,  client_ID integer,  group_ID integer,  rule_acsess integer)
as
begin
    insert into contracts(contract_ID, start_date, end_date, arend_cost, cell_number, client_ID, group_ID, rule_acsess) values(:contract_ID, :start_date, :end_date, :arend_cost, :cell_number, :client_ID, :group_ID, :rule_acsess);
end;
create procedure insert_client(client_ID integer,  name varchar(50), adress varchar(100), born_date date)
as
begin
    insert into clients(client_ID, name, adress, born_date) values(:client_ID, :name, :adress, :born_date);
end;
create procedure insert_group(group_ID integer,  date_create date)
as
begin
    insert into groups(group_ID, date_create) values(:group_ID, :date_create);
end;
create procedure insert_group_client(ID integer, client_ID integer, group_ID integer)
as
begin
    insert into groups_clients(ID, client_ID, group_ID) values(:ID, :client_ID, :group_ID);
end;
