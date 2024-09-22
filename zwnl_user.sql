create database if not exists zwnl_user;
use zwnl_user;
create table if not exists user_detail
(
    user_id       int auto_increment comment '关联用户id'
        primary key,
    type          tinyint     default 2                 null comment '用户类型：1-员工, 2-普通学员，3-老师',
    name          varchar(64) default ''                null comment '名字',
    gender        tinyint     default 0                 not null comment '性别：0-男性，1-女性',
    icon          varchar(255)                          null comment '头像地址',
    email         varchar(255)                          null comment '邮箱',
    qq            varchar(18)                           null comment 'QQ号码',
    birthday      date                                  null comment '生日',
    job           varchar(32)                           null comment '岗位',
    province      varchar(32)                           null comment '省',
    city          varchar(32)                           null comment '市',
    district      varchar(32)                           null comment '区',
    intro         varchar(255)                          null comment '个人介绍',
    photo         varchar(255)                          null comment '形象照地址',
    role_id       bigint                                not null comment '角色id',
    course_amount smallint    default 0                 null comment '购买课程数量，学生才有该字段信息',
    create_time   datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    creater       bigint                                null comment '创建者id',
    updater       bigint      default 0                 null comment '更新者id',
    dep_id        bigint      default 0                 not null comment '部门id'
)
    comment '教师详情表';

create fulltext index name_idx
    on user_detail (name);

create table if not exists users
(
    user_id      int auto_increment comment '用户ID'
        primary key,
    username     varchar(255)                       not null comment '用户名',
    email        varchar(255)                       not null comment '邮箱',
    password     varchar(255)                       not null comment '密码',
    role         tinyint  default 1                 not null comment '用户角色：0-employer 1-employee',
    avatar_url   varchar(255)                       null comment '用户头像',
    status       tinyint  default 1                 null comment '用户状态： 1-正常   2-不活跃  3--禁用',
    created_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    last_login   datetime                           null comment '最后登录时间',
    openid       varchar(45)                        null,
    phone        varchar(11)                        null,
    constraint email
        unique (email),
    constraint phone
        unique (phone),
    constraint username
        unique (username)
)
    comment '用户表';

