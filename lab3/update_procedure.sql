create procedure update_free_cells(date_end date) as
begin
    update cells set status='free' where cells.cell_number in (select cell_number from contracts where contracts.end_date<:date_end);
end;
