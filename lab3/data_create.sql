insert into clients values (1, 'Sokolov Vasya Aleksandrovich', 'SPb, s.Borovaya, h.5, a.30', '1989-01-01');
insert into clients values (2, 'Polyakov Vasya Petrovich', 'SPb, s.Borovaya, h.10, a.68', '1984-12-03');
insert into clients values (3, 'Medvedev Dima Vasilyevich', 'SPb, s.Griboedov, h.12, a.12', '1990-11-03');
insert into clients values (4, 'Strelcov Nikolai Vladimirovich', 'SPb, s.Pyshkinskay, h.56, a.1', '1985-11-03');
insert into clients values (5, 'Prohorov Artem Denisovich', 'SPb, s.Griboedov, h.45, a.68', '1989-06-30');
insert into clients values (6, 'Gromov Airat Dmitrievich', 'SPb, s.Razejaya, h.189, a.6', '1976-07-21');
insert into clients values (7, 'Kiselev Pavel Rodionovich', 'SPb, s.Kolomenskaya, h.20, a.45', '1963-01-11');
insert into clients values (8, 'Kolobov Jhon Ibragimovich', 'SPb, s.Nevskii, h.127, a.43', '1965-05-15');

insert into groups values (1, '2012-05-15');
insert into groups values (2, '2014-01-01');
insert into groups values (3, '2014-01-01');
insert into groups values (4, '2014-01-01');
insert into groups values (5, '2014-01-01');
insert into groups values (6, '2014-01-01');
insert into groups values (7, '2014-01-01');
insert into groups values (8, '2014-01-01');
insert into groups values (9, '2014-01-01');
insert into groups values (10, '2014-01-01');

insert into groups_clients values (1, 1, 1);
insert into groups_clients values (2, 2, 1);
insert into groups_clients values (3, 3, 1);
insert into groups_clients values (4, 5, 2);
insert into groups_clients values (5, 8, 2);
insert into groups_clients values (6, 1, 3);
insert into groups_clients values (7, 2, 4);
insert into groups_clients values (8, 3, 5);
insert into groups_clients values (9, 4, 6);
insert into groups_clients values (10, 5, 7);
insert into groups_clients values (11, 6, 8);
insert into groups_clients values (12, 7, 9);
insert into groups_clients values (13, 8, 10);

insert into repository values (1, 'SPb, s.Kolomenskaya, h.123', 'Bankov Bank Bankovich');
insert into repository values (2, 'SPb, s.Komendatskii, h.12', 'Direktorov Director Directovich');

insert into cells values (13, 1, 'arrend');
insert into cells values (1, 2, 'arrend');
insert into cells values (6, 1, 'arrend');
insert into cells values (8, 2, 'arrend');
insert into cells values (11, 1, 'arrend');
insert into cells values (21, 2, 'arrend');
insert into cells values (40, 1, 'arrend');
insert into cells values (50, 2, 'arrend');
insert into cells values (4, 2, 'free');
insert into cells values (25, 2, 'free');

insert into contracts values (1, '2014-02-01', '2015-02-01', 100, 13, 1, 3, 0);
insert into contracts values (2, '2014-02-01', '2016-02-01', 200, 1, 2, 1, 1);
insert into contracts values (3, '2014-02-01', '2016-06-01', 250, 6, 5, 2, 1);
insert into contracts values (4, '2014-02-01', '2016-06-01', 250, 8, 5, 2, 1);
insert into contracts values (5, '2014-02-01', '2015-06-01', 150, 11, 4, 6, 0);
insert into contracts values (6, '2014-02-01', '2015-06-01', 150, 21, 8, 10, 0);
insert into contracts values (7, '2015-02-01', '2015-06-01', 40, 40, 5, 7, 0);
insert into contracts values (8, '2015-02-01', '2015-08-01', 70, 40, 7, 9, 0);

insert into items values (1, 1, 'Brilliants', 100000);
insert into items values (2, 1, 'Ruby', 1000);
insert into items values (3, 2, 'Gold', 10000);
insert into items values (4, 3, 'Platina', 20000);
insert into items values (5, 7, 'Gold egg', 25000);
insert into items values (6, 8, 'Redstone', 1000);

insert into history_cell values (1, 13, 1, '2014-02-10', 1);
insert into history_cell values (2, 13, 1, '2014-02-10', 2);
insert into history_cell values (3, 13, 2, '2014-02-10', 2);
insert into history_cell values (4, 1, 1, '2014-02-20', 3);
insert into history_cell values (5, 6, 1, '2014-03-21', 4);
insert into history_cell values (6, 40, 1, '2015-02-20', 5);
insert into history_cell values (6, 40, 1, '2015-02-20', 6);
