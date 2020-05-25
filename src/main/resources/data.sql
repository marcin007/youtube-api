INSERT INTO users(first_name, last_name, user_name, password) values('Jacek','Wrobel', 'JACEX','qwert');
INSERT INTO users(first_name, last_name, user_name, password) values('Gracjan', 'Rostowski', 'FIKUMIKU','1234');

INSERT INTO channels(name, description,user_id) values('JacekKanal', 'sss', 1);
INSERT INTO channels(name, description,user_id) values('LIMMA', 'music', 1);
INSERT INTO channels(name, description,user_id) values('GracjanLive', 'vlog', 2);

INSERT INTO categories(name) values('Vlogs');
INSERT INTO categories(name) values('Comedy');

INSERT INTO FILMS(title, description, url, length, channel_id, category_id)VALUES ( 'Spejson vlog odc 1', 'Lekcja matematyki w TVP1', 'https://www.youtube.com/watch?v=fGlTwlryXSM',13.22, 1,1 );
INSERT INTO FILMS(title, description, url, length, channel_id, category_id)VALUES ( 'Spejson vlog odc 2', 'Spina z Cieślakiem', 'https://www.youtube.com/watch?v=fGlTwlryXSM', 15.22, 1,1 );
INSERT INTO FILMS(title, description, url, length, channel_id, category_id)VALUES ( 'Gracjan opowiada co zrobil w niedziele', 'Gracjan odc 1', 'https://www.youtube.com/watchdawt3wlryXSM', 1.22, 2,2 );
INSERT INTO FILMS(title, description, url, length, channel_id, category_id)VALUES ( 'Gracjan spiewa piosenke',  'Piosenka o kwiatkach', 'https://www.youtube.com/sadasdas',6.22, 2,2 );

INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 1, 2, '15.35', current_timestamp(), current_timestamp() );
INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 1, 1, '15.35', current_timestamp(), current_timestamp() );
INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 1, 3, '15.35', current_timestamp(), current_timestamp() );
INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 2, 4, '34', current_timestamp(), current_timestamp() );
INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 2, 2, '15.35', current_timestamp(), current_timestamp() );


INSERT INTO COMMENTS(film_id, user_id, content, likes, dislikes) VALUES ( 1, 2, 'Komentarz 1 z data.sql', 102, 41 );
INSERT INTO COMMENTS(film_id, user_id, content, likes, dislikes) VALUES ( 3, 2, 'Komentarz 1 do filmu 2 spejsona', 10, 42 );
INSERT INTO COMMENTS(film_id, user_id, content, likes, dislikes) VALUES ( 3, 1, 'Komentarz 2 do filmu 2 spejsona', 13, 4 );
INSERT INTO COMMENTS(film_id, user_id, content, likes, dislikes) VALUES ( 4, 1, 'Komentarz 1 do filmu Gracjana', 30, 46 );

INSERT INTO REPLY(user_id, comment_id, content, likes, dislikes) VALUES ( 1,1, 'Tory były złe.', 220, 200);
INSERT INTO REPLY(user_id, comment_id, content, likes, dislikes) VALUES ( 1,4, 'Gracjan to wybitny człowiek', 26, 488);
INSERT INTO REPLY(user_id, comment_id, content, likes, dislikes) VALUES ( 1,4, 'Swiat nie zasługuje na taką osobe jak on', 80, 87);
INSERT INTO REPLY(user_id, comment_id, content, likes, dislikes) VALUES ( 2,3, 'Re-komentarz do komentarza 3', 2, 46);
INSERT INTO REPLY(user_id, comment_id, content, likes, dislikes) VALUES ( 2,2, 'Spejnon zna księge ulicy na pamięć.', 22, 8);

