create database if not exists zwnl_company;

use zwnl_company;
create table if not exists companies
(
    company_id    int auto_increment comment '公司ID'
        primary key,
    user_id       int                                null,
    name          varchar(255)                       not null comment '公司名称',
    industry      varchar(255)                       null comment '所属行业',
    size          tinyint  default 1                 null comment '公司规模1-5代表(''1-50'', ''51-200'', ''201-500'', ''501-1000'', ''1001+'')',
    address       varchar(255)                       null comment '公司地址',
    contact_name  varchar(255)                       null comment '联系人姓名',
    contact_email varchar(255)                       null comment '联系人邮箱',
    contact_phone varchar(20)                        null comment '联系人电话',
    cdescription  text                               null comment '公司描述',
    created_time  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    comstatus     tinyint  default 1                 null comment '公司阶段--1为已上市 --2为未融资'
)
    comment '公司表';

create table if not exists jobs
(
    job_id       int auto_increment comment '职位ID'
        primary key,
    title        varchar(255)                       not null comment '职位名称',
    description  text                               null comment '职位描述',
    requirements text                               null comment '职位要求',
    location     varchar(255)                       null comment '工作地点',
    min_salary   decimal(10, 2)                     null comment '最低薪资',
    max_salary   decimal(10, 2)                     null comment '最高薪资',
    company_id   int                                null comment '公司ID',
    created_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '职位表';

create table if not exists job_views
(
    view_id     int auto_increment comment '浏览记录ID'
        primary key,
    job_id      int                                not null comment '职位ID',
    viewed_time datetime default CURRENT_TIMESTAMP not null comment '浏览时间',
    constraint job_views_ibfk_1
        foreign key (job_id) references jobs (job_id)
            on delete cascade
)
    comment '职位浏览记录表';

create index job_id
    on job_views (job_id);

