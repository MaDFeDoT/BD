create view ind_arend_cells as select repository.repository_id, count(contracts.contract_id) as kol from cells,  repository, contracts
where (repository.repository_id=cells.repository_id)and(cells.cell_number=contracts.cell_number)and(contracts.end_date>current_date) group by repository.repository_id;
