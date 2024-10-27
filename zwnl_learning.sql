create database if not exists zwnl_learning;
use zwnl_learning;
create table points_board_season
(
    id         int auto_increment comment '自增长id，season标示'
        primary key,
    name       varchar(32) null comment '赛季名称，例如：第1赛季',
    begin_time date        not null comment '赛季开始时间',
    end_time   date        not null comment '赛季结束时间'
)
    row_format = DYNAMIC;

create table points_record
(
    id          bigint auto_increment comment '积分记录表id'
        primary key,
    user_id     bigint                             not null comment '用户id',
    type        tinyint                            not null comment '积分方式：1-课程学习，2-每日签到，3-课程问答， 4-课程笔记，5-课程评价',
    points      tinyint                            not null comment '积分值',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '学习积分记录，每个月底清零' row_format = DYNAMIC;

create index idx_create_time
    on points_record (create_time);

create index idx_user_id
    on points_record (user_id, type);

create table sign_record
(
    id        bigint auto_increment comment '主键'
        primary key,
    user_id   bigint  not null comment '用户id',
    year      year    not null comment '签到年份',
    month     tinyint not null comment '签到月份',
    date      date    not null comment '签到日期',
    is_backup bit     not null comment '是否补签'
)
    comment '签到记录表';

