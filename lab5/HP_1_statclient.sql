create procedure ClientStat
returns(
    ID integer, IDname varchar(50),  sred_cost float
) as
declare variable cell integer;
declare variable start_d date;
declare variable end_d date;
declare variable acsess_d date;
declare variable item integer;
declare variable acsess_t integer;
declare variable cost_i integer;
declare variable year_start integer;
declare variable year_finish integer;
declare variable prev_year_cost integer;
declare variable r integer;
declare variable year_cost integer;
declare variable sred_year_cost_up float;
declare variable kol integer;
declare variable i integer;
begin
    for select client_id, name from clients
    order by client_id into :ID, :IDname do
    begin
        i=0;
        sred_cost=0;
        for select cell_number, start_date, end_date from contracts where (client_id=:ID)and(rule_acsess=0)
        order by cell_number into :cell, :start_d, :end_d do
        begin
            year_start=extract(year from start_d);
            year_finish=extract(year from end_d);
            prev_year_cost=0;
            sred_year_cost_up=0;
            kol=0;
            year_cost=0;
            while (year_start<year_finish+1) do
            begin
                for select date_acsess, item_id, acsess_type from history_cell
                where (cell_number=:cell)and(extract(year from date_acsess)=:year_start)and(date_acsess>:start_d)and(date_acsess<:end_d)
                order by date_acsess into :acsess_d, :item, :acsess_t do
                begin
                    select cost from items where (item_id=:item) into :cost_i;
                    if (acsess_t=1) then
                    begin
                        year_cost=year_cost+cost_i;
                    end
                    else if (acsess_t=2) then
                    begin
                        year_cost=year_cost-cost_i;
                    end
                end
                r=year_cost-prev_year_cost;
                prev_year_cost=year_cost;
                sred_year_cost_up=sred_year_cost_up+r;
                kol=kol+1;
                year_start=year_start+1;
            end
            if(kol<>0) then
            begin
                sred_year_cost_up=sred_year_cost_up/kol;
                sred_cost=sred_cost+sred_year_cost_up;
                i=i+1;
            end
        end
        if(i<>0) then
        begin
            sred_cost=sred_cost/i;
        end
        suspend;
    end
end
