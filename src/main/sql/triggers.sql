create or replace function delete_training_if_not_used()
    returns trigger as $$
begin
    delete from collective_event where
        id in (select id_collective_event from training where id not in (select id_training from player_training));
    return new;
end;
$$ language plpgsql;

create trigger trigger_delete_training_if_not_used
    after delete on player_training
    for each row
execute function delete_training_if_not_used();



create or replace function delete_match_if_not_used()
    returns trigger as $$
begin
    delete from collective_event where
        id in (select id_collective_event from match where id not in (select id_match from player_match));
    return new;
end;
$$ language plpgsql;

create trigger trigger_delete_match_if_not_used
    after delete on player_match
    for each row
execute function delete_match_if_not_used();



create or replace function delete_custom_if_not_used()
    returns trigger as $$
begin
    delete from collective_event where
        id in (select id_collective_event from custom where id not in (select id_custom from player_custom));
    return new;
end;
$$ language plpgsql;

create trigger trigger_delete_custom_if_not_used
    after delete on player_custom
    for each row
execute function delete_custom_if_not_used();
