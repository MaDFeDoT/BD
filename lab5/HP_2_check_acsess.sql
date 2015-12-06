create procedure acsess_check_and_write (client integer,  cell integer,  type_a integer,  item integer, name varchar(50), cost integer)
returns(
    res_message varchar(50)
) as
declare variable c_contract integer;
declare variable c_group integer;
declare variable s_date date;
declare variable e_date date;
declare variable flag integer;
declare variable i integer;
begin
    c_contract=-1;
    select contract_ID, start_date, end_date from contracts
    where (client_ID=:client)and(cell_number=:cell)and(rule_acsess=0) into :c_contract, :s_date, :e_date;
    if(c_contract=-1) then
    begin
        for select group_ID from groups_clients
        where (client_ID=:client) into :c_group do
        begin
            select contract_ID, start_date, end_date from contracts
            where (group_ID=:c_group)and(cell_number=:cell)and(rule_acsess=1) into :c_contract, :s_date, :e_date;
        end
    end
    if(c_contract=-1) then
    begin
        res_message='Acsess denied. Cant find contracts for this data';
        suspend;
        exit;
    end
    else if(not ((s_date<current_date)and(e_date>current_date))) then
    begin
        res_message='Acsess denied. Contract ended';
        suspend;
        exit;
    end
    if((type_a=0) or (type_a=2)) then
    begin
        flag=-1;
        select item_ID from items where (contract_id=:c_contract)and(item_ID=:item) into :flag;
        if (flag=-1) then
        begin
            res_message='Acsess denied. Contract doesnt have this item';
            suspend;
            exit;
        end
        flag=-1;
        select first 1 acsess_type from history_cell
        where(acsess_type=2)and(item_id=:item)and(cell_number=:cell)and(date_acsess>:s_date)and(date_acsess<:e_date)
        order by date_acsess desc into :flag;
        if (flag=2) then
        begin
            res_message='Acsess denied. The item is taken from the cell';
            suspend;
            exit;
        end
        select max(ID) from history_cell into :i;
        i=i+1;
        insert into history_cell(ID, cell_number, acsess_type, date_acsess, item_ID) values (:i, :cell, :type_a, current_date, :item);
        res_message='Acsess accept. History_cell update';
        suspend;
        exit;
    end else if(type_a=1) then
    begin
        flag=-1;
        select item_ID from items where (contract_id=:c_contract)and(item_ID=:item) into :flag;
        if (flag=-1) then
        begin
            select max(item_ID) from items into :item;
            item=item+1;
            insert into items(item_ID, contract_ID, name, cost) values (:item, :c_contract, :name, :cost);
        end
        select max(ID) from history_cell into :i;
        i=i+1;
        insert into history_cell(ID, cell_number, acsess_type, date_acsess, item_ID) values (:i, :cell, :type_a, current_date, :item);
        res_message='Acsess accept. History_cell update';
        suspend;
        exit;    
    end else
    begin
        res_message='Error. Check enter data';
        suspend;
        exit;
    end
end
