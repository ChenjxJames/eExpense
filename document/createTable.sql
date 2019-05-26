create table e_branch(
    id    varchar2(10) primary key,
    name  varchar2(20),
    place varchar2(100)
);

create table e_user(
    id       varchar2(10) primary key,
    password varchar2(20),
    name     varchar2(20),
    branch   varchar2(10) REFERENCES e_branch (id),
    position varchar2(20)
);

create table e_expense_account(
    id          number primary key,
    reason      varchar2(100),
    creator     varchar2(10) references e_user (id),
    createTime date,
    conductor   varchar2(10) references e_user (id),
    price       number(9, 2),
    state       varchar2(20)
);

create sequence e_expense_account_id_seq;

create table e_expense_account_info(
    id                 number primary key,
    expenseAccountId number references e_expense_account (id),
    expenseClass      varchar2(20),
    price              number(9, 2),
    info               varchar2(100)
);

create sequence e_expense_account_info_id_seq;

create table e_conduct_record(
    id                 number primary key,
    expenseAccountId number references e_expense_account (id),
    conductor          varchar2(10) references e_user (id),
    conduct_time       date,
    conduct_class      varchar2(20),
    conduct_result     varchar2(20),
    info               varchar2(100)
);

create sequence e_conduct_record_id_seq;
