create procedure del_history_about_max_cost(acsess_t integer) as
begin
    delete from history_cell where acsess_type = :acsess_t and item_id = (select item_id from items where cost = (select max(cost) from items));
end;
