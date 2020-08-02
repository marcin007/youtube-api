DELETE FROM REPLY WHERE 1 = 1;
DELETE FROM COMMENTS WHERE 1=1;
DELETE FROM USER_WATCHED_FILMS WHERE 1=1;
DELETE FROM FILMS WHERE 1=1;
DELETE FROM categories WHERE 1=1;
DELETE FROM channels WHERE 1=1;
DELETE FROM users WHERE 1=1;

INSERT INTO users(id, first_name, last_name, user_name, password) values(1, 'Jacek','Wrobel', 'JACEX','qwert');
INSERT INTO users(id, first_name, last_name, user_name, password) values(2, 'Gracjan', 'Rostowski', 'FIKUMIKU','1234');

INSERT INTO channels(id, name, description,user_id) values(1, 'JacekKanal', 'sss', 1);
INSERT INTO channels(id, name, description,user_id) values(2, 'LIMMA', 'music', 1);
INSERT INTO channels(id, name, description,user_id) values(3, 'GracjanLive', 'vlog', 2);

INSERT INTO categories(id, name) values(1, 'Vlogs');
INSERT INTO categories(id, name) values(2, 'Comedy');

INSERT INTO FILMS(id, title, description, url, length, channel_id, category_id) VALUES (1,  'Spejson vlog odc 1', 'Lekcja matematyki w TVP1', 'https://www.youtube.com/watch?v=fGlTwlryXSM',13.22, 1,1 );
INSERT INTO FILMS(id, title, description, url, length, channel_id, category_id) VALUES (2,  'Spejson vlog odc 2', 'Spina z Cieślakiem', 'https://www.youtube.com/watch?v=fGlTwlryXSM', 15.22, 1,1 );
INSERT INTO FILMS(id, title, description, url, length, channel_id, category_id) VALUES (3,  'Gracjan opowiada co zrobil w niedziele', 'Gracjan odc 1', 'https://www.youtube.com/watchdawt3wlryXSM', 1.22, 2,2 );
INSERT INTO FILMS(id, title, description, url, length, channel_id, category_id) VALUES (4,  'Gracjan spiewa piosenke',  'Piosenka o kwiatkach', 'https://www.youtube.com/sadasdas',6.22, 2,2 );

INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 1, 2, '15.35', current_timestamp(), current_timestamp() );
INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 1, 1, '15.35', current_timestamp(), current_timestamp() );
INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 1, 3, '15.35', current_timestamp(), current_timestamp() );
INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 2, 4, '34', current_timestamp(), current_timestamp() );
INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 2, 2, '15.35', current_timestamp(), current_timestamp() );

INSERT INTO COMMENTS(id, film_id, user_id, content, likes, dislikes) VALUES (1,  1, 2, 'Komentarz 1 z data', 102, 41 );
INSERT INTO COMMENTS(id, film_id, user_id, content, likes, dislikes) VALUES (2,  3, 2, 'Komentarz 1 do filmu 2 spejsona', 10, 42 );
INSERT INTO COMMENTS(id, film_id, user_id, content, likes, dislikes) VALUES (3,  3, 1, 'Komentarz 2 do filmu 2 spejsona', 13, 4 );
INSERT INTO COMMENTS(id, film_id, user_id, content, likes, dislikes) VALUES (4,  4, 1, 'Komentarz 1 do filmu Gracjana', 30, 46 );

INSERT INTO REPLY(id, user_id, comment_id, content, likes, dislikes) VALUES (1,  1,1, 'Tory były złe.', 220, 200);
INSERT INTO REPLY(id, user_id, comment_id, content, likes, dislikes) VALUES (2,  1,4, 'Gracjan to wybitny człowiek', 26, 488);
INSERT INTO REPLY(id, user_id, comment_id, content, likes, dislikes) VALUES (3,  1,4, 'Swiat nie zasługuje na taką osobe jak on', 80, 87);
INSERT INTO REPLY(id, user_id, comment_id, content, likes, dislikes) VALUES (4,  2,3, 'Re-komentarz do komentarza 3', 2, 46);
INSERT INTO REPLY(id, user_id, comment_id, content, likes, dislikes) VALUES (5,  2,2, 'Spejnon zna księge ulicy na pamięć.', 22, 8);

