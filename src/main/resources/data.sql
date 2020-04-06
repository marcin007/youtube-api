INSERT INTO users(first_name, last_name, user_name, password) values('Jacek','Wrobel', 'JACEX','qwert');
INSERT INTO users(first_name, last_name, user_name, password) values('Gracjan', 'Rostowski', 'FIKUMIKU','1234');

INSERT INTO channels(name, description,user_id) values('JacekKanal', 'sss', 1);
INSERT INTO channels(name, description,user_id) values('LIMMA', 'music', 1);
INSERT INTO channels(name, description,user_id) values('GracjanLive', 'vlog', 2);

INSERT INTO FILMS(description, length, title, url, channel_id)VALUES ( 'Spejson vlog odc 1', 13.22, 'Lekcja matematyki w TVP1', 'https://www.youtube.com/watch?v=fGlTwlryXSM', 1 );
INSERT INTO FILMS(description, length, title, url, channel_id)VALUES ( 'Spejson vlog odc 2', 15.22, 'Spina z Cieślakiem', 'https://www.youtube.com/watch?v=fGlTwlryXSM', 1 );
INSERT INTO FILMS(description, length, title, url, channel_id)VALUES ( 'Gracjan opowiada co zrobil w niedziele', 1.22, 'Gracjan odc 1', 'https://www.youtube.com/watchdawt3wlryXSM', 2 );
INSERT INTO FILMS(description, length, title, url, channel_id)VALUES ( 'Gracjan spiewa piosenke', 6.22, 'Piosenka o kwiatkach', 'https://www.youtube.com/sadasdas', 2 );