create database if not exists zwnl_user;
use zwnl_user;
create table user_detail
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

create table users
(
    user_id      int auto_increment comment '用户ID'
        primary key,
    username     varchar(255)                         not null comment '用户名',
    email        varchar(255)                         not null comment '邮箱',
    password     varchar(255)                         not null comment '密码',
    role         tinyint    default 1                 not null comment '用户角色：0-employer 1-employee',
    avatar_url   varchar(255)                         null comment '用户头像',
    status       tinyint    default 1                 null comment '用户状态： 1-正常   2-不活跃  3--禁用',
    created_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    last_login   datetime                             null comment '最后登录时间',
    openid       varchar(45)                          null,
    phone        varchar(11)                          null,
    is_online    tinyint(1) default 0                 null comment '是否在线，1为在线',
    constraint email
        unique (email),
    constraint phone
        unique (phone),
    constraint username
        unique (username)
)
    comment '用户表';

INSERT INTO zwnl_user.user_detail (user_id, type, name, gender, icon, email, qq, birthday, job, province, city, district, intro, photo, role_id, course_amount, create_time, update_time, creater, updater, dep_id) VALUES (1, 1, 'tdfsgjhs ', 0, 'gvhjlk', 'vhjkl', 'hvjkl', '2024-10-11', 'fvgj', 'fxcgj', 'fgj', 'fgj', 'fcgjf', 'fgdjg', 1, 0, '2024-10-11 23:11:20', '2024-10-11 23:11:35', 1, 1, 1);
INSERT INTO zwnl_user.users (user_id, username, email, password, role, avatar_url, status, created_time, updated_time, last_login, openid, phone, is_online) VALUES (1, 'mbhv m,jb', '310kguvhu', '654', 56, null, 1, '2024-10-11 23:04:27', '2024-10-11 23:04:27', null, null, null, 0);
