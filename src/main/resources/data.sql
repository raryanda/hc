insert into groups values(1, 'Group A');
insert into groups values(2, 'Group B');
insert into groups values(3, 'Group C');

insert into users values(1, 'User A', 1);
insert into users values(2, 'User B', 2);
insert into users values(3, 'User C', 3);

insert into modules values(1, 'Category');
insert into modules values(2, 'History');
insert into modules values(3, 'Promo');
insert into modules values(4, 'FlashSale');
insert into modules values(5, 'News');

insert into group_modules values(3, 1, 1);
insert into group_modules values(1, 1, 2);
insert into group_modules values(4, 1, 3);
insert into group_modules values(2, 1, 4);
insert into group_modules values(5, 1, 5);

insert into group_modules values(3, 2, 1);
insert into group_modules values(5, 2, 2);
insert into group_modules values(4, 2, 3);
insert into group_modules values(1, 2, 4);
insert into group_modules values(2, 2, 5);

insert into group_modules values(3, 3, 1);
insert into group_modules values(4, 3, 2);
insert into group_modules values(1, 3, 3);
insert into group_modules values(5, 3, 4);
insert into group_modules values(2, 3, 5);
