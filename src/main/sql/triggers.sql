create or replace function delete_training_if_not_used()
    returns trigger as $$
begin
    delete from collective_event where
        id in (select id_collective_event from training where (id not in (select id_training from player_training)) and
            (training.id_collective_event not in (select club_event.id_collective_event from club_event)));
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
        id in (select id_collective_event from match where (id not in (select id_match from player_match)) and
            (match.id_collective_event not in (select club_event.id_collective_event from club_event)));
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
        id in (select id_collective_event from custom where (id not in (select id_custom from player_custom)) and
            (custom.id_collective_event not in (select club_event.id_collective_event from club_event)));
    return new;
end;
$$ language plpgsql;

create trigger trigger_delete_custom_if_not_used
    after delete on player_custom
    for each row
execute function delete_custom_if_not_used();



create or replace function check_duplicate_apps()
    returns trigger as $$
begin
    if exists(select 1 from application where id_club = new.id_club and id_player = new.id_player and club_approve = 0)
    then RAISE EXCEPTION 'Unresolved application from this player to club already exists';
    end if;
    return new;
end;
$$ LANGUAGE plpgsql;

create trigger prevent_duplicate_apps before insert on application
    for each row execute function check_duplicate_apps();



create or replace function add_club_membership()
    returns trigger as $$
begin
    if new.club_approve = 1 then
        insert into club_membership (id_player, id_club, begin_date, u_payment, c_salary)
        VALUES (new.id_player, new.id_club, new.creation_date, 0.0, 0.0);
    end if;
    return new;
end;
$$ LANGUAGE plpgsql;

create trigger add_player_to_club after update on application
    for each row execute function add_club_membership();