create procedure delete_ditems as
begin
    delete from history_cell where history_cell.item_id in (select items.item_id from items, contracts where(contracts.contract_id=items.contract_id)and(contracts.end_date<current_date));
    delete from items where item_id in (select items.item_id from items, contracts where(contracts.contract_id=items.contract_id)and(contracts.end_date<current_date));
end;

