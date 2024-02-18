create or replace procedure delete_training_by_id(event_id int, user_id int) as
$$
    begin
    delete from player_training where id = event_id and player_training.id_player = user_id;
    end;
$$ language plpgsql;

create or replace procedure delete_match_by_id(event_id int, user_id int) as
$$
begin
    delete from player_match where id = event_id and player_match.id_player = user_id;
end;
$$ language plpgsql;

create or replace procedure delete_custom_by_id(event_id int, user_id int) as
$$
begin
    delete from player_custom where id = event_id and player_custom.id_player = user_id;
end;
$$ language plpgsql;



create or replace function find_player_training_by_id(event_id int, user_id int) returns
setof player_training as
$$
    begin
        return query
        select player_training.id, id_player, id_training, goals, what_liked, what_disliked, what_to_improve, comments from
        player_training left join training on player_training.id_training = training.id left join
        collective_event on training.id_collective_event = collective_event.id
        where collective_event.id =  event_id and player_training.id_player = user_id;
    end;
$$ language plpgsql;

create or replace function find_player_custom_by_id(event_id int, user_id int) returns
    setof player_custom as
$$
begin
    return query
        select player_custom.id, id_player, id_custom, what_liked, what_disliked, what_to_improve, comments from
            player_custom left join custom on player_custom.id_custom = custom.id left join
            collective_event on custom.id_collective_event = collective_event.id
        where collective_event.id =  event_id and player_custom.id_player = user_id;
end;
$$ language plpgsql;

create or replace function find_player_match_by_id(event_id int, user_id int) returns
    setof player_match as
$$
begin
    return query
        select player_match.id, id_player, id_match, goals, field_time, role, assists,
               what_liked, what_disliked, what_to_improve, comments from
            player_match left join match on player_match.id_match = match.id left join
            collective_event on match.id_collective_event = collective_event.id
        where collective_event.id =  event_id and player_match.id_player = user_id;
end;
$$ language plpgsql;



create or replace function find_exercises_by_event(event_id int, user_id int) returns
    setof exercise as
$$
begin
    return query
        select exercise.id, id_player, title, technic, photo_link, video_link, duration,
               amount, muscle_load, equipment, min_people, usage_count, date from
            exercise left join training_target on exercise.id = training_target.id_exercise
            left join training on training_target.id_training = training.id
        where training.id_collective_event = event_id and exercise.id_player = user_id;
end;
$$ language plpgsql;