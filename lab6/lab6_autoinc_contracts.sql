create generator create_id;
set generator create_id to 8;
create trigger auto_create_id for contracts before insert
as
begin
    new.contract_ID=gen_id(create_id, 1);
end

