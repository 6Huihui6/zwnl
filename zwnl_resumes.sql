create database if not exists zwnl_resumes;
use zwnl_resumes;
create table if not exists educations
(
    education_id     int auto_increment comment '教育背景ID'
        primary key,
    resume_id        int          null comment '简历ID',
    institution_name varchar(255) not null comment '学校或教育机构名称',
    degree           varchar(255) null comment '学位',
    major            varchar(255) null comment '专业',
    start_date       date         null comment '开始时间',
    end_date         date         null comment '结束时间'
)
    comment '教育经历表';

create table if not exists resumes
(
    resume_id       int auto_increment comment '简历ID'
        primary key,
    seeker_id       int                                null comment '求职者ID',
    title           varchar(255)                       not null comment '简历标题',
    summary         text                               null comment '个人简介',
    contact_info    varchar(255)                       null comment '联系方式',
    resume_file_url varchar(255)                       null comment '简历附件URL',
    created_time    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '简历表';

create table if not exists skills
(
    skill_id    int auto_increment comment '技能ID'
        primary key,
    resume_id   int               null comment '简历ID',
    skill_name  varchar(255)      not null comment '技能名称',
    proficiency tinyint default 1 null comment '熟练程度： 1-入门   2-熟练  3--精通'
)
    comment '技能表';

create table if not exists work_experiences
(
    experience_id    int auto_increment comment '工作经历ID'
        primary key,
    resume_id        int          null comment '简历ID',
    company_name     varchar(255) not null comment '公司名称',
    position         varchar(255) not null comment '职位名称',
    cstart_date      date         null comment '开始时间',
    cend_date        date         null comment '结束时间',
    responsibilities text         null comment '职责和业绩'
)
    comment '工作经历表';
