create trigger fin_contract for history_cell after insert
as
declare variable contract_i integer;
declare variable item_i integer;
declare variable flag integer;
begin
    if(new.acsess_type=2) then
    begin
        select contract_id from contracts 
        where cell_number=new.cell_number
        into :contract_i;
        flag=2;
        for select item_id from items 
        where contract_id=:contract_i into item_i do
        begin
            select first 1 acsess_type from history_cell
            where (item_id=:item_i)and(cell_number=new.cell_number)
            order by date_acsess desc into :flag;
            if(flag<>2) then
            begin
                exit;
            end
        end
        update contracts set end_date=current_date where contract_ID=:contract_i;
        update cells set status='free' where cell_number=new.cell_number;
    end
end
