  --创建 2013年12月13日 by winston 

create table bpm_definition
(
   def_id_              varchar(64) not null comment '流程定义ID',
   name_                varchar(64) not null comment '流程名称',
   inst_subject_rule_   varchar(128) not null comment '流程实例标题规则',
   def_key_             varchar(64) not null comment '流程业务主键',
   desc_                varchar(1024) comment '流程描述',
   type_id_             varchar(64) comment '所属分类ID',
   status_              varchar(40) not null comment '流程状态。草稿、发布、禁用',
   test_status_         varchar(40) not null comment '测试状态',
   inform_type_         varchar(40) comment '通知类型',
   bpmn_def_id_         varchar(64) comment 'BPMN - 流程定义ID',
   bpmn_deploy_id_      varchar(64) comment 'BPMN - 流程发布ID',
   version_             int comment '版本 - 当前版本号',
   main_def_id_         varchar(64) comment '版本 - 主版本流程ID',
   is_main_             char(1) not null comment '版本 - 是否主版本',
   reason_              varchar(1024) comment '版本 - 变更理由',
   help_id_             varchar(64) comment '其它 - 流程帮助附件ID',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime comment '创建时间',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (def_id_)   
);

alter table bpm_definition comment '@名称：BPM_DEFINITION 【流程定义】
@描述：流程定义实体接口
@作者：Winst';

create table bpm_node_config
(
   config_id_           varchar(64) not null comment '节点配置ID',
   node_id_             varchar(64) not null comment '节点ID',
   node_name_           varchar(64) comment '节点名称（快照）',
   proc_def_id_         varchar(64) not null comment '流程定义ID',
   node_type_           varchar(40) not null comment '节点类型。user：用户节点；msg：消息节点；script：脚本节点；ws：WEBSERVICE节点。',
   sn_                  int comment '排序号',
   memo_                varchar(255) comment '配置备注',
   is_mail_             char(1) not null comment 'Y=是
            N=否',
   is_short_msg_        char(1) not null comment 'Y=是
            N=否',
   is_inmsg_            char(1) not null comment 'Y=是
            N=否',
   primary key (config_id_)
);

alter table bpm_node_config comment '@名称：BPM_NODE_CONFIG 【节点基本配置】
@描述：节点配置
@作者：Winsto';

alter table bpm_node_config add constraint fk_reference_6 foreign key (proc_def_id_)
      references bpm_definition (def_id_) on delete cascade on update restrict;
      
create table bpm_def_data
(
   id_                  varchar(64) not null comment '流程定义ID',
   def_xml_             longtext comment '流程定义XML',
   bpmn_xml_            longtext comment '流程定义BPMN格式XML',
   primary key (id_)
);

alter table bpm_def_data comment '@名称：BPM_DEF_DATA 【流程定义大数据值】
@描述：流程定义大数据值，1对1依赖流程定义表-';

create table bpm_def_config
(
   id_                  varchar(64) not null comment '流程定义ID',
   is_skip_first_       char(1) not null comment '参数 - 启动后是否跳过第一个节点',
   is_first_assignee_   char(1) not null comment '参数 - 是否选择第一个节点的执行人',
   is_mail_             char(1) not null comment '参数 - 是否发邮件通知任务执行人。Y=是，N=否',
   is_short_msg_        char(1) not null comment '参数 - 是否发短信通知任务执行人。Y=是；N=否',
   is_inmsg_            char(1) not null comment '参数 - 是否发内部消息通知任务执行人。Y=是；N=否',
   primary key (id_)
);

alter table bpm_def_config comment '流程定义配置参数';

-- winston 2013-12-17 增加字段 
alter table bpm_node_config add column is_setting_sub_ char(1) not null default "N" comment "是否已配置相应子表" after memo_;
      
--winston 2013-12-19 增加bpm_node_script
alter table bpm_def_data drop COLUMN mail_;
alter table bpm_def_data drop COLUMN sms_;
alter table bpm_def_data drop COLUMN inner_msg_;

drop table BPM_NODE_CONFIG_DATA;

create table bpm_user_node
(
   id_                  varchar(64) not null comment '节点配置ID',
   biz_type_            varchar(40) not null comment '业务类型。normal 普通用户节点；fork 分发任务节点；join 汇总任务节点；',
   is_allow_back_       char(1) not null comment '参数 - 是否允许回退',
   is_allow_back_to_start_ char(1) not null comment '参数 - 是否允许回退至发起人',
   is_hide_opinion_field_ char(1) not null comment '参数 - 是否隐藏意见输入框',
   is_hide_exec_path_   char(1) not null comment '参数 - 是否隐藏执行路径',
   primary key (id_)
);


create table bpm_msg_rule
(
   id_                  varchar(64) not null comment '消息节点配置ID',
   config_id_           varchar(64) comment '节点配置ID',
   proc_def_id_         varchar(64) comment '流程定义ID',
   biz_type_            varchar(40) not null comment '消息业务类型。def=流程；node=节点（公共）；msg=消息节点',
   msg_type_            varchar(40) not null comment '消息类型。mail 邮件；sms 短信； inner 站内消息。',
   subject_rule_        varchar(255) comment '消息主题规则。对于短信类型不需要标题。',
   template_            longtext not null comment '消息模板。对于短信类型注意在界面限制消息长度。',
   primary key (id_)
);

alter table bpm_msg_rule add constraint fk_reference_16 foreign key (proc_def_id_)
      references bpm_definition (def_id_) on delete restrict on update restrict;

alter table bpm_msg_rule add constraint fk_node_config_r_node_msg foreign key (config_id_)
      references bpm_node_config (config_id_) on delete restrict on update restrict;

create table bpm_node_script
(
   id_                  varchar(64) not null comment '脚本节点配置ID',
   config_id_           varchar(64) not null comment '节点配置ID',
   script_              longtext not null comment '脚本',
   script_type_         varchar(40) not null comment '脚本类型。before.事件前置脚本；after.事件后置脚本；script 脚本节点；assign 任务分配脚本',
   primary key (id_)
);

alter table bpm_node_script add constraint fk_reference_18 foreign key (config_id_)
      references bpm_node_config (config_id_) on delete restrict on update restrict;
      
      
----------增加用户表      
drop table if exists xog_user;

/*==============================================================*/
/* Table: xog_user                                              */
/*==============================================================*/
create table xog_user
(
   user_id_             varchar(64) not null comment '用户ID',
   account_             varchar(64) not null comment '用户账号',
   fullname_            varchar(64) comment '姓名',
   status_              varchar(40) not null comment '状态。0 未激活；1 已激活；2 锁定
            -1 删除
            。',
   pwd_                 varchar(128) not null comment '密码',
   sex_                 varchar(20) comment 'male
            female',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (user_id_),
   unique key ak_k_account (account_)
);

alter table xog_user comment '@名称：XOG_USER【系统用户】
@描述：系统用户
@作者：Winston Yan
                             -&#';

                             
-- 2014-02-19 何一帆  添加字段记录BODef用什么格式保存实例化中的数据 
alter table xbo_def add DATA_TYPE_ VARCHAR(64);
comment on column xbo_def.DATA_TYPE_ is '实例化时保存数据的格式';

