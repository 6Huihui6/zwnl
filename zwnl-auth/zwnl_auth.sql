create database if not exists zwnl_auth;
use zwnl_auth;

create table if not exists account_role
(
    id         bigint auto_increment
        primary key,
    account_id bigint not null comment '账户id',
    role_id    bigint not null comment '角色id'
)
    comment '账户、角色关联表';

create table if not exists login_record
(
    id          bigint auto_increment comment '主键'
        primary key,
    user_id     bigint                                not null comment '用户id',
    cell_phone  varchar(11)                           null comment '手机号码',
    login_time  datetime    default CURRENT_TIMESTAMP not null comment '登录时间',
    logout_time datetime                              null comment '登出时间',
    login_date  date                                  null comment '登录日期',
    duration    bigint      default 0                 null comment '登录时长，单位是秒',
    ipv4        varchar(15) default ''                not null comment 'ip地址'
)
    comment '登录信息记录表';

create table if not exists menu
(
    id           bigint                                 not null comment '主键'
        primary key,
    parent_id    bigint       default 0                 null comment '父菜单id，默认0代表没有父菜单',
    has_children tinyint      default 0                 null comment '是否有子菜单，默认false',
    label        varchar(16)  default ''                null comment '菜单文本',
    path         varchar(255) default ''                null comment '菜单路径',
    icon         varchar(32)  default ''                null comment '菜单图标',
    priority     tinyint      default 127               not null comment '顺序，默认127',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    creater      bigint       default 0                 not null comment '创建者id',
    updater      bigint       default 0                 not null comment '更新者id',
    dep_id       bigint       default 0                 not null comment '部门id',
    deleted      tinyint      default 0                 not null comment '逻辑删除，默认0'
)
    comment '权限表，包括菜单权限和访问路径权限';

create table if not exists privilege
(
    id          bigint                                 not null comment '主键'
        primary key,
    menu_id     bigint                                 null comment '菜单id',
    intro       varchar(255) default ''                null comment '权限说明',
    method      varchar(16)  default ''                null comment 'API权限的请求方式',
    uri         varchar(255) default ''                null comment 'API权限的请求路径',
    internal    tinyint      default 0                 null comment '是否是内部接口权限，默认false',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    creater     bigint       default 0                 not null comment '创建者id',
    updater     bigint       default 0                 not null comment '更新者id',
    dep_id      bigint       default 0                 not null comment '部门id',
    deleted     tinyint      default 0                 not null comment '逻辑删除，默认0'
)
    comment '权限表，包括菜单权限和访问路径权限';

create table if not exists role
(
    id          bigint                             not null comment '主键'
        primary key,
    code        varchar(64)                        not null comment '角色代号，例如：admin',
    name        varchar(255)                       not null comment '角色名称',
    type        tinyint  default 1                 null comment '角色类型：0-固定角色（不可选）1-自定义角色',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    creater     bigint   default 0                 not null comment '创建者id',
    updater     bigint   default 0                 not null comment '更新者id',
    dep_id      bigint   default 0                 not null comment '部门id',
    deleted     tinyint  default 0                 not null comment '逻辑删除，默认0'
)
    comment '角色表';

create table if not exists role_menu
(
    id      bigint auto_increment
        primary key,
    role_id bigint not null comment '角色id',
    menu_id bigint not null comment '菜单id'
)
    comment '账户、角色关联表';

create table if not exists role_privilege
(
    id           bigint auto_increment
        primary key,
    role_id      bigint not null comment '角色id',
    privilege_id bigint not null comment '权限id'
)
    comment '账户、角色关联表';

