-- auto-generated definition
create table t_user
(
    name         varchar,
    family       varchar,
    email        varchar,
    phone_number varchar,
    password     varchar,
    user_id      serial not null
        constraint t_user_pk
            primary key
);

alter table t_user
    owner to sammy;

create unique index t_user_user_id_uindex
    on t_user (user_id);



-- auto-generated definition
create table t_auth_info
(
    auth_info_id serial  not null
        constraint t_auth_pk
            primary key,
    time         numeric,
    "user"       integer not null
        constraint t_auth_t_user_user_id_fk
            references t_user,
    ip           varchar
);

alter table t_auth_info
    owner to sammy;

create unique index t_auth_auth_id_uindex
    on t_auth_info (auth_info_id);


-- auto-generated definition
create table t_image
(
    image_id serial  not null
        constraint t_image_pk
            primary key,
    "user"   integer not null
        constraint t_image_t_user_user_id_fk
            references t_user,
    size     numeric,
    mime     varchar(256),
    name     varchar(256)
);

alter table t_image
    owner to sammy;

create unique index t_image_image_id_uindex
    on t_image (image_id);

