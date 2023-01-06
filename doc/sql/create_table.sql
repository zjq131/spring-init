create database if not exists hn_patent;

-- user表
-- auto-generated definition
create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    username     varchar(255)                        null comment '用户名',
    userPassword varchar(255)                        not null comment '密码',
    avatarUrl    varchar(255)                        null comment '用户头像地址',
    userProfile  text                                null comment '用户描述',
    userRole     tinyint   default 0                 null comment '角色标识 0-普通管理员 1-超级管理员',
    phone        varchar(255)                        null comment '电话',
    email        varchar(255)                        null comment '邮箱',
    createTime   timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   timestamp default CURRENT_TIMESTAMP null comment '修改时间',
    isDelete     tinyint   default 0                 null comment '逻辑删除'
)
    comment '用户表';

create index user__name
    on user (username);

-- 专利信息表
-- auto-generated definition
create table patent_information
(
    id                    bigint                              not null comment 'id'
        primary key,
    applicant             varchar(255)                        null comment '申请人  【某个组织】',
    patentNumber          varchar(50)                         null comment '专利号',
    patentName            varchar(255)                        null comment '专利名称',
    customerId            bigint                              null comment '客户Id  【发明人】',
    partner               varchar(255)                        null comment '合作方',
    patentApplicationTime timestamp                           null comment '专利申请日',
    applicationFeeTime    timestamp                           null comment '申请费缴费时间',
    authorizationTime     timestamp                           null comment '授权日',
    billingFeeTime        timestamp                           null comment '办登费缴费时间',
    nextFeeTime           timestamp                           null comment '下次缴纳年费时间',
    vintages              int                                 null comment '年费年份',
    isBill                tinyint                             null comment '是否开票',
    annualFee             int                                 null comment '年费',
    createTime            timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime            timestamp default CURRENT_TIMESTAMP null comment '修改时间',
    isDelete              tinyint   default 0                 null comment '逻辑删除',
    constraint patent_information_patentNumber_uindex
        unique (patentNumber)
)
    comment '专利信息表';

create index patent_information__name
    on patent_information (patentName);





