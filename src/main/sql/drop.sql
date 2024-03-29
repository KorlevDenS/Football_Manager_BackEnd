drop procedure delete_custom_by_id(event_id integer, user_id integer);

drop trigger prevent_duplicate_apps on application;
drop function check_duplicate_apps();

drop trigger add_player_to_club on application;
drop function add_club_membership();

drop trigger trigger_delete_custom_if_not_used on player_custom;
drop function delete_custom_if_not_used();

drop trigger trigger_delete_match_if_not_used on player_match;
drop function delete_match_if_not_used();

drop trigger trigger_delete_training_if_not_used on player_training;
drop function delete_training_if_not_used();

drop procedure delete_match_by_id(event_id integer, user_id integer);
drop procedure delete_training_by_id(event_id integer, user_id integer);

drop function find_player_training_by_id(event_id integer, user_id integer);
drop function find_player_match_by_id(event_id integer, user_id integer);
drop function find_player_custom_by_id(event_id integer, user_id integer);




drop table training_target;
drop table exercise;

drop table player_training;
drop table player_custom;
drop table player_match;

drop table training;
drop table custom;
drop table match;

drop table club_event;
drop table collective_event;

drop table club_membership;
drop table application;

drop table player;
drop table club_management;
drop table club;
drop table config;
drop table human;