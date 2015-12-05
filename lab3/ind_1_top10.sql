create view top10cells as select first 10 cells.cell_number, repository_id, sum(items.cost)  as MCost from cells,  items, history_cell
where (history_cell.cell_number=cells.cell_number)and(history_cell.item_id=items.item_id)and(history_cell.acsess_type=1)and(history_cell.item_id not in (select item_id from history_cell where item_id=2)) group by cells.cell_number, cells.repository_id order by MCost desc;