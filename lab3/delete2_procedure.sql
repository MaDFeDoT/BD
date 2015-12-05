create procedure del_unused_items as
begin
    delete from items where item_id not in(select contract_id from contracts);
end;
