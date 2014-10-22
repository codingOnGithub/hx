drop table if exists EX_STUDENT;

/*==============================================================*/
/* Table: EX_STUDENT                                            */
/*==============================================================*/
create table EX_STUDENT
(
   STU_ID_              VARCHAR(20) not null,
   NAME_                VARCHAR(20) not null comment '名称',
   BIRTHDAY_            DATETIME comment '生日',
   SEX_                 SMALLINT comment '姓别',
   DESC_                VARCHAR(200) comment '描述',
   primary key (STU_ID_)
);

alter table EX_STUDENT comment '学生表';
