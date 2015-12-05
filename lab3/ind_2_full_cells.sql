create view ind_arend_cells as select repository.repository_id, count(cells.cell_number) as kol from cells,  repository
where (cells.status='arrend')and(repository.repository_id=cells.repository_id)and(cells.cell_number in (select cell_number from history_cell
where (acsess_type=1)and(item_id not in (select item_id from history_cell
where acsess_type=2)))) group by repository.repository_id;
