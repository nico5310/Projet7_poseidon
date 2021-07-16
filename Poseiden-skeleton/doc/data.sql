CREATE TABLE bidlist
(
    bid_list_id    int auto_increment primary key,
    account        varchar(125) not null,
    type           varchar(125) not null,
    bid_quantity   double       not null,

    ask_quantity   double       null,
    bid            double       null,
    ask            double       null,
    benchmark      varchar(125) null,
    bid_list_date  datetime     null,
    commentary     varchar(125) null,
    security       varchar(125) null,
    status         varchar(125) null,
    trader         varchar(125) null,
    book           varchar(125) null,
    creation_name  varchar(125) null,
    creation_date  datetime     null,
    revision_name  varchar(125) null,
    revision_date  datetime     null,
    deal_name      varchar(125) null,
    deal_type      varchar(125) null,
    source_list_id varchar(125) null,
    side           varchar(125) null
)
    engine = MyISAM;


CREATE TABLE trade
(
    trade_id       int auto_increment primary key,
    account        varchar(125) not null,
    type           varchar(125) not null,
    buy_quantity   double       not null,

    sell_quantity  double       null,
    buy_price      double       null,
    sell_price     double       null,
    benchmark      varchar(125) null,
    trade_date     datetime     null,
    security       varchar(125) null,
    status         varchar(125) null,
    trader         varchar(125) null,
    book           varchar(125) null,
    creation_name  varchar(125) null,
    creation_date  datetime     null,
    revision_name  varchar(125) null,
    revision_date  datetime     null,
    deal_name      varchar(125) null,
    deal_type      varchar(125) null,
    source_list_id varchar(125) null,
    side           varchar(125) null
)
    engine = MyISAM;

CREATE TABLE curvepoint
(
    id            int auto_increment primary key,
    curve_id      int      not null,
    as_of_date    datetime null,
    term          double   not null,
    value         double   not null,
    creation_date datetime null
)
    engine = MyISAM;

CREATE TABLE rating
(
    id            int auto_increment primary key,
    moodys_rating varchar(125) not null,
    sandprating   varchar(125) not null,
    fitch_rating  varchar(125) not null,
    order_number  int          not null
)
    engine = MyISAM;

CREATE TABLE rulename
(
    id          int auto_increment primary key,
    name        varchar(125) not null,
    description varchar(125) not null,
    json        varchar(125) not null,
    template    varchar(125) not null,
    sql_str     varchar(125) not null,
    sql_part    varchar(125) not null
)
    engine = MyISAM;

CREATE TABLE users
(
    id       int auto_increment primary key,
    username varchar(125) null unique,
    password varchar(125) null,
    fullname varchar(125) null,
    role     varchar(125) null
)
    engine = MyISAM;

#insert into Users(fullname, username, password, role) values("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh
# .sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ADMIN")
#insert into Users(fullname, username, password, role) values("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh
# .sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "USER")