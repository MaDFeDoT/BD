create exception error_items 'Error. Cant delete or update in table items';
create trigger control_items for items before delete or update
as
begin
    if(old.item_id in (select item_id from history_cell)) then
        exception error_items;
end
