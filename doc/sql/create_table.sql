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






