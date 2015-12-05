create view selectAllHistItems as select history_cell.item_ID, name, cost, cell_number, date_acsess from history_cell, items where history_cell.item_id=items.item_id and history_cell.acsess_type=1;
create view ContractsView as select contract_ID, name, cell_number, arend_cost from contracts, clients where contracts.client_id=clients.client_ID and contracts.rule_acsess=0;
