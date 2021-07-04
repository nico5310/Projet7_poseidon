drop database if exists demotest;
create database demotest;
use demotest;

create table bidlist
(
    bid_list_id    int auto_increment
        primary key,
    account        varchar(255) not null,
    ask            double       null,
    ask_quantity   double       null,
    benchmark      varchar(255) null,
    bid            double       null,
    bid_list_date  datetime     null,
    bid_quantity   double       not null,
    book           varchar(255) null,
    commentary     varchar(255) null,
    creation_date  datetime     null,
    creation_name  varchar(255) null,
    deal_name      varchar(255) null,
    deal_type      varchar(255) null,
    revision_date  datetime     null,
    revision_name  varchar(255) null,
    security       varchar(255) null,
    side           varchar(255) null,
    source_list_id varchar(255) null,
    status         varchar(255) null,
    trader         varchar(255) null,
    type           varchar(255) not null
)
    engine = MyISAM;

create table curvepoint
(
    id            int auto_increment
        primary key,
    as_of_date    datetime null,
    creation_date datetime null,
    curve_id      int      not null,
    term          double   not null,
    value         double   not null
)
    engine = MyISAM;

create table hibernate_sequence
(
    next_val bigint null
)
    engine = MyISAM;

create table rating
(
    id            int auto_increment
        primary key,
    fitch_rating  varchar(255) not null,
    moodys_rating varchar(255) not null,
    order_number  int          not null,
    sandprating   varchar(255) not null
)
    engine = MyISAM;

create table rulename
(
    id          int auto_increment
        primary key,
    description varchar(255) not null,
    json        varchar(255) not null,
    name        varchar(255) not null,
    sql_part    varchar(255) not null,
    sql_str     varchar(255) not null,
    template    varchar(255) not null
)
    engine = MyISAM;

create table trade
(
    trade_id       int auto_increment
        primary key,
    account        varchar(255) not null,
    benchmark      varchar(255) null,
    book           varchar(255) null,
    buy_price      double       null,
    buy_quantity   double       null,
    creation_date  datetime     null,
    creation_name  varchar(255) null,
    deal_name      varchar(255) null,
    deal_type      varchar(255) null,
    revision_date  datetime     null,
    revision_name  varchar(255) null,
    security       varchar(255) null,
    sell_price     double       null,
    sell_quantity  double       null,
    side           varchar(255) null,
    source_list_id varchar(255) null,
    status         varchar(255) null,
    trade_date     datetime     null,
    trader         varchar(255) null,
    type           varchar(255) not null
)
    engine = MyISAM;

create table users
(
    id       int          not null
        primary key,
    fullname varchar(255) null,
    password varchar(255) null,
    role     varchar(255) null,
    username varchar(255) null
)
    engine = MyISAM;

