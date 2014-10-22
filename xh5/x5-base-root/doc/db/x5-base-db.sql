drop table if exists XB_DB_ID;

/*==============================================================*/
/* Table: XB_DB_ID                                              */
/*==============================================================*/
create table XB_DB_ID
(
   ID_                  integer not null,
   START_               DECIMAL(20,0) not null comment '开始ID值',
   MAX_                 DECIMAL(20,0) not null comment '当前允许最大的ID值',
   MAC_NAME_            VARCHAR(256) not null comment '机器名称',
   primary key (ID_)
);

alter table XB_DB_ID comment '数据库ID增长表';
