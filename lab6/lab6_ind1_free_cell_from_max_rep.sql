create exception dosent_have_free_cells 'Error. Cant find free cell in banks';
create trigger insert_cell_n for contracts before insert
as
declare variable rep_id integer;
declare variable num integer;
begin
    for select repository.repository_id, count(cell_number) as kol from repository, cells
    where(repository.repository_id=cells.repository_id)and(cells.status='arrend')
    group by repository_id order by kol desc into :rep_id, :num do
    begin
        num=-1;
        select min(cell_number) from cells
        where (repository_id=:rep_id)and(status='free') into :num;
        if (num<>-1) then
        begin
            new.cell_number=num;
            update cells set status='arrend' where cell_number=:num;
            exit;
        end
    end
    exception dosent_have_free_cells;
end


