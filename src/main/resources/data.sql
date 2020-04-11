INSERT INTO users(first_name, last_name, user_name, password) values('Jacek','Wrobel', 'JACEX','qwert');
INSERT INTO users(first_name, last_name, user_name, password) values('Gracjan', 'Rostowski', 'FIKUMIKU','1234');

INSERT INTO channels(name, description,user_id) values('JacekKanal', 'sss', 1);
INSERT INTO channels(name, description,user_id) values('LIMMA', 'music', 1);
INSERT INTO channels(name, description,user_id) values('GracjanLive', 'vlog', 2);

INSERT INTO categories(name) values('Vlogs');

INSERT INTO FILMS(title, description, url, length, channel_id, category_id)VALUES ( 'Spejson vlog odc 1', 'Lekcja matematyki w TVP1', 'https://www.youtube.com/watch?v=fGlTwlryXSM',13.22, 1,1 );
INSERT INTO FILMS(title, description, url, length, channel_id, category_id)VALUES ( 'Spejson vlog odc 2', 'Spina z Cieślakiem', 'https://www.youtube.com/watch?v=fGlTwlryXSM', 15.22, 1,1 );
INSERT INTO FILMS(title, description, url, length, channel_id, category_id)VALUES ( 'Gracjan opowiada co zrobil w niedziele', 'Gracjan odc 1', 'https://www.youtube.com/watchdawt3wlryXSM', 1.22, 2,1 );
INSERT INTO FILMS(title, description, url, length, channel_id, category_id)VALUES ( 'Gracjan spiewa piosenke',  'Piosenka o kwiatkach', 'https://www.youtube.com/sadasdas',6.22, 2,1 );

INSERT INTO USER_WATCHED_FILMS(user_id, film_id, time_spent_for_watching, started_at, ended_at) VALUES ( 1, 2, '15.35', current_timestamp(), current_timestamp() );

INSERT INTO COMMENTS(film_id, user_id, content, likes, dislikes) VALUES ( 1, 2, 'adsada', 10, 4 );