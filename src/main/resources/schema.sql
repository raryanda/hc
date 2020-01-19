create table groups
(
    group_id integer not null,
    group_name varchar(255) not null,
    primary key(group_id)
);

create table users
(
    user_id integer not null,
    user_name varchar(255) not null,
    group_id integer,
    primary key(user_id),
    foreign key(group_id) references groups(group_id)
);

create table modules
(
    module_id integer not null,
    module_name varchar(255) not null,
    primary key(module_id)
);

create table group_modules
(
    module_id integer not null,
    group_id integer not null,
    module_order integer not null,
    primary key(module_id, group_id),
    foreign key(module_id) references modules(module_id),
    foreign key(group_id) references groups(group_id)
);
