/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014-1-26 14:48:03                           */
/*==============================================================*/


drop table if exists bpm_agent_auth;

drop table if exists bpm_agent_def;

drop table if exists bpm_agent_setting;

drop table if exists bpm_check_opinion;

drop table if exists bpm_check_opinion_hi;

drop table if exists bpm_definition;

drop table if exists bpm_def_config;

drop table if exists bpm_def_data;

drop table if exists bpm_def_form;

drop table if exists bpm_def_rights;

drop table if exists bpm_excutor_con;

drop table if exists bpm_exe_stack;

drop table if exists bpm_form;

drop table if exists bpm_form_inst;

drop table if exists bpm_log;

drop table if exists bpm_message;

drop table if exists bpm_msg_rule;

drop table if exists bpm_node_config;

drop table if exists bpm_node_identity;

drop table if exists bpm_node_rule;

drop table if exists bpm_node_script;

drop table if exists bpm_pro_inst;

drop table if exists bpm_pro_inst_hi;

drop table if exists bpm_pro_status;

drop table if exists bpm_pro_status_hi;

drop table if exists bpm_task;

drop table if exists bpm_task_due;

drop table if exists bpm_task_read;

drop table if exists bpm_task_turn;

drop table if exists bpm_user_candidate;

drop table if exists bpm_user_node;

drop table if exists bpm_var;

drop table if exists bpm_var_data;

drop table if exists bpm_var_inst;

drop table if exists bpm_ws_node;

drop table if exists bpm_ws_node_params;

drop table if exists xbo_attr;

drop table if exists xbo_def;

drop table if exists xbo_inst;

drop table if exists xbo_package;

drop table if exists xbo_rule;

drop table if exists xbo_rule_ref;

drop table if exists xb_db_id;

drop table if exists xb_type;

drop table if exists xb_type_group;

drop table if exists xog_attr;

drop table if exists xog_attr_val;

drop table if exists xog_dimension;

drop table if exists xog_dim_rel;

drop table if exists xog_group;

drop table if exists xog_group_auth;

drop table if exists xog_group_rel;

drop table if exists xog_rank_type;

drop table if exists xog_rel_type;

drop table if exists xog_user;

drop table if exists xog_user_group;

drop table if exists xog_user_rel;

/*==============================================================*/
/* Table: bpm_agent_auth                                        */
/*==============================================================*/
create table bpm_agent_auth
(
   id_                  varchar(64) not null comment '主键',
   setting_id_          varchar(64) not null comment '代理设置ID',
   agent_id_            varchar(64) not null comment '代理人ID',
   agent_name_          varchar(64) not null comment '代理人姓名',
   is_all_              char(1) not null comment '是否全权代理',
   priority_            int not null default 10 comment '优先级。数值越大优先级越高。',
   is_enabled_          char(1) not null default 'Y' comment '是否激活。Y=是；N=否',
   memo_                varchar(255) comment '备注',
   create_time_         datetime not null comment '创建时间',
   primary key (id_)
);

/*==============================================================*/
/* Table: bpm_agent_def                                         */
/*==============================================================*/
create table bpm_agent_def
(
   id_                  varchar(64) not null comment '主键',
   agent_auth_id_       varchar(64) not null comment '代理人授权ID',
   type_                varchar(40) not null comment '目标类型。def=指定流程；nodes=指定流程的若干节点',
   def_key_             varchar(64) not null comment '流程定义KEY',
   def_name_            varchar(64) not null comment '流程名称',
   node_ids_            varchar(1024) comment '节点ID。多个节点使用逗号分隔。',
   is_unconditional_    char(1) not null comment '是否无条件',
   condition_           varchar(4000) comment '授权条件',
   create_time_         datetime not null comment '创建时间',
   primary key (id_)
);

/*==============================================================*/
/* Table: bpm_agent_setting                                     */
/*==============================================================*/
create table bpm_agent_setting
(
   id_                  varchar(64) not null comment '主键',
   subject_             varchar(64) not null comment '标题',
   auth_id_             varchar(64) not null comment '授权人ID',
   auth_name_           varchar(64) not null comment '授权人姓名',
   start_date_          datetime not null comment '开始生效时间',
   end_date_            datetime not null comment '结束日期',
   is_enabled_          char(1) not null comment '是否有效',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime comment '创建时间',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (id_)
);

alter table bpm_agent_setting comment '流程代理设置';

/*==============================================================*/
/* Table: bpm_check_opinion                                     */
/*==============================================================*/
create table bpm_check_opinion
(
   id_                  varchar(64) not null comment '意见ID',
   proc_def_id_         varchar(64) not null comment '流程定义ID',
   sup_inst_id_         varchar(64) comment '父流程实例ID',
   proc_inst_id_        varchar(64),
   task_id_             varchar(64) comment '任务ID',
   task_key_            varchar(64) comment '任务定义Key',
   task_name_           varchar(255) comment '任务名称',
   token_               varchar(64) comment '任务令牌',
   exe_user_id_         varchar(64) comment '执行人ID',
   exe_user_name_       varchar(64) comment '执行人名',
   opinion_             text comment '审批意见',
   check_status_        varchar(40) comment '审批状态。agree=同意；against=反对；return=驳回；abandon=弃权；retrieve=追回',
   form_def_id          varchar(64) comment '表单定义ID',
   form_name_           varchar(64) comment '表单名',
   start_time_          datetime comment '执行开始时间',
   end_time_            datetime comment '结束时间',
   dur_ms_              bigint comment '持续时间(ms)',
   primary key (id_)
);

alter table bpm_check_opinion comment '流程审批意见';

/*==============================================================*/
/* Table: bpm_check_opinion_hi                                  */
/*==============================================================*/
create table bpm_check_opinion_hi
(
   id_                  varchar(64) not null comment '意见ID',
   proc_def_id_         varchar(64) not null comment '流程定义ID',
   sup_inst_id_         varchar(64) comment '父流程实例ID',
   proc_inst_id_        varchar(64),
   task_id_             varchar(64) comment '任务ID',
   task_key_            varchar(64) comment '任务定义Key',
   task_name_           varchar(255) comment '任务名称',
   token_               varchar(64) comment '任务令牌',
   exe_user_id_         varchar(64) comment '执行人ID',
   exe_user_name_       varchar(64) comment '执行人名',
   opinion_             text comment '审批意见',
   check_status_        varchar(40) comment '审批状态。agree=同意；against=反对；return=驳回；abandon=弃权；retrieve=追回',
   form_def_id          varchar(64) comment '表单定义ID',
   form_name_           varchar(64) comment '表单名',
   start_time_          datetime comment '执行开始时间',
   end_time_            datetime comment '结束时间',
   dur_ms_              bigint comment '持续时间(ms)'
);

/*==============================================================*/
/* Table: bpm_definition                                        */
/*==============================================================*/
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
   inform_type_         varchar(128) comment '通知类型',
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

/*==============================================================*/
/* Table: bpm_def_config                                        */
/*==============================================================*/
create table bpm_def_config
(
   id_                  varchar(64) not null comment '流程定义ID',
   inform_type_         varchar(40) not null comment '类型 - 通知类型',
   start_info_type_     varchar(40) comment '类型 - 归档时发送消息通知发起人类型',
   skip_first_node_     char(1) not null default 'N' comment '参数 - 启动后是否跳过第一个节点',
   choose_first_assignee_ char(1) not null default 'N' comment '参数 - 是否选择第一个节点的执行人',
   skip_same_user_      char(1) not null default 'N' comment '参数 - 相邻节点相同执行人跳过',
   allow_copy_to_       char(1) not null comment '参数 - 允许抄送',
   allow_trans_to_      char(1) not null comment '参数 - 是否允许转办',
   direct_start_        char(1) not null comment '参数 - 允许直接启动',
   del_draft_           char(1) not null comment '参数 - 允许删除草稿',
   allow_ref_           char(1) not null comment '参数 - 允许参考',
   ref_count_           int not null comment '允许参考的数量',
   allow_print_form_    char(1) not null comment '参数 - 允许打印表单',
   primary key (id_)
);

alter table bpm_def_config comment '流程定义配置参数';

/*==============================================================*/
/* Table: bpm_def_data                                          */
/*==============================================================*/
create table bpm_def_data
(
   id_                  varchar(64) not null comment '流程定义ID',
   def_xml_             longtext comment '流程定义XML',
   bpmn_xml_            longtext comment '流程定义BPMN格式XML',
   primary key (id_)
);

alter table bpm_def_data comment '@名称：BPM_DEF_DATA 【流程定义大数据值】
@描述：流程定义大数据值，1对1依赖流程定义表
                                 -';

/*==============================================================*/
/* Table: bpm_def_form                                          */
/*==============================================================*/
create table bpm_def_form
(
   id_                  varchar(64) not null comment '主键',
   form_name            varchar(255) comment '表单名称',
   form_key_            varchar(64) not null comment '表单业务主键',
   form_type_           varchar(40) comment '表单类型',
   use_type_            varchar(40) not null comment '表单用途类型。global=全局表单；instance=实例表单；print=打印表单',
   pro_def_id           varchar(64) comment '流程定义ID',
   node_id_             varchar(64) comment '任务节点ID',
   node_name_           varchar(64) comment '节点名称',
   primary key (id_)
);

alter table bpm_def_form comment '流程表单设置';

/*==============================================================*/
/* Table: bpm_def_rights                                        */
/*==============================================================*/
create table bpm_def_rights
(
   id_                  varchar(64) not null comment '主键',
   def_key_             varchar(64) not null comment '流程定义KEY',
   auth_type_           varchar(40) not null comment '授权类型。everyone，user等',
   author_id_           varchar(64) not null comment '授权设置ID',
   author_name_         varchar(64) not null comment '授权设置名称',
   primary key (id_)
);

alter table bpm_def_rights comment '流程定义授权';

/*==============================================================*/
/* Table: bpm_excutor_con                                       */
/*==============================================================*/
create table bpm_excutor_con
(
   id                   varchar(64) not null comment '主键',
   proc_def_id_         varchar(64) not null comment '流程定义ID',
   node_id_             varchar(255) comment '节点ID',
   condition_code_      text comment '条件',
   condition_show_      text comment '显示条件',
   sn_                  bigint comment '排序',
   condition_type_      varchar(20) comment '条件类型
            flow.流程人员
            copyTo.抄送人员
            mail.邮件',
   group_               smallint comment '分组编号',
   form_identity_       varchar(40) comment '表单标识作用:当表单改变时,条件自动不生效,使用表做标识。',
   ext_info_            varchar(4000) comment '扩展消息
            像抄送配置的消息类型就可以配置到这里',
   primary key (id)
);

alter table bpm_excutor_con comment '流程人员计算规则';

/*==============================================================*/
/* Table: bpm_exe_stack                                         */
/*==============================================================*/
create table bpm_exe_stack
(
   stack_id_            varchar(64) not null,
   prco_def_id_         varchar(64) comment '流程定义ID',
   node_id_             varchar(255) not null comment '节点ID',
   node_name_           varchar(255) comment '节点名',
   start_time_          datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   assignees            varchar(1000) comment '执行人IDS，如1,2,3',
   ismultitask          char(1) not null comment '1=是
            0=否',
   parent_id_           varchar(64) comment '父ID',
   proc_inst_id_        varchar(64),
   task_ids_            varchar(512),
   node_path_           varchar(1000) comment '节点路径
            格式如：
            0.1.2.
            其中2则为本行记录的主键值',
   depth_               int comment '节点层',
   task_token_          varchar(128) comment '是针对分发任务时，携带的令牌，方便查找其父任务堆栈',
   primary key (stack_id_)
);

alter table bpm_exe_stack comment '流程执行堆栈树';

/*==============================================================*/
/* Index: idx_stack_instid                                      */
/*==============================================================*/
create index idx_stack_instid on bpm_exe_stack
(
   proc_inst_id_
);

/*==============================================================*/
/* Index: idx_stack_parentid                                    */
/*==============================================================*/
create index idx_stack_parentid on bpm_exe_stack
(
   parent_id_
);

/*==============================================================*/
/* Index: idx_stack_taskid                                      */
/*==============================================================*/
create index idx_stack_taskid on bpm_exe_stack
(
   task_ids_
);

/*==============================================================*/
/* Table: bpm_form                                              */
/*==============================================================*/
create table bpm_form
(
   id_                  varchar(64) not null comment '表单ID',
   name_                varchar(64) not null comment '表单名称',
   form_key_            varchar(64) not null comment '表单业务主键',
   desc_                varchar(255) comment '表单描述',
   form_html_           longtext comment '表单设计（HTML代码）',
   ds_key_              varchar(64) comment '绑定的数据源业务主键',
   status_              varchar(40) not null comment '表单状态。draft=草稿；deploy=发布',
   form_type_           varchar(40) comment '表单类型',
   type_id_             varchar(64) comment '所属分类ID',
   is_main_             char(1) not null comment '是否主版本',
   version_             int comment '表单版本号',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime comment '创建时间',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (id_),
   unique key ak_k_form_key (form_key_)
);

alter table bpm_form comment '@名称：BPM_FORM 流程任务表单
@描述：流程任务表单
@作者：Winston Yan
                             ';

/*==============================================================*/
/* Table: bpm_form_inst                                         */
/*==============================================================*/
create table bpm_form_inst
(
   id_                  varchar(64) not null comment '表单实例',
   form_id_             varchar(64) not null comment '表单ID',
   form_key_            varchar(64) not null comment '表单业务主键',
   form_html_           longtext comment '表单设计（HTML代码）',
   form_data_           longtext comment '表单运行数据',
   ds_key_              varchar(64) comment '绑定的数据源业务主键',
   biz_key_             varchar(64) comment '关联数据业务主键',
   proc_inst_id_        varchar(64) comment '流程实例ID',
   proc_inst_subject_   varchar(128) comment '流程实例标题',
   proc_def_id_         varchar(64) comment '流程定义ID',
   version_             int comment '版本号',
   primary key (id_)
);

alter table bpm_form_inst comment '@名称：BPM_FORM_INST 流程运行的表单实例
@描述：流程运行的表单实例。
@作者：W';

/*==============================================================*/
/* Table: bpm_log                                               */
/*==============================================================*/
create table bpm_log
(
   log_id_              varchar(64) not null,
   proc_inst_id_        varchar(64),
   proc_def_id_         varchar(64),
   proc_subject_        varchar(255),
   task_id_             varchar(64),
   task_name            varchar(255),
   log_content_         text,
   exe_time_            datetime,
   exe_user_id          varchar(64),
   exe_result_          varchar(50) comment 'sucess 成功
            failure  失败
            none 执行无结果',
   exe_result_msg       text,
   primary key (log_id_)
);

alter table bpm_log comment '流程的执行日志';

/*==============================================================*/
/* Table: bpm_message                                           */
/*==============================================================*/
create table bpm_message
(
   msg_id_              varchar(64) not null comment '消息ID',
   proc_inst_id_        varchar(64) comment '流程实例ID',
   subject_             varchar(128) not null comment '消息标题',
   content_             text comment '消息内容',
   msg_template_        text comment '消息模板',
   sender_id_           varchar(64) comment '发送用户ID',
   receiver_id_         varchar(64) comment '接收用户ID',
   msg_type_            varchar(40) comment '消息类型',
   status_              varchar(40) comment '状态',
   send_time_           datetime comment '发送时间',
   primary key (msg_id_)
);

alter table bpm_message comment '@名称：BPM_MESSAGE 【BPM消息】
@描述：BPM消息，含邮件、短信、内部消息的基本信息
                                -&';

/*==============================================================*/
/* Table: bpm_msg_rule                                          */
/*==============================================================*/
create table bpm_msg_rule
(
   id_                  varchar(64) not null comment '消息节点配置ID',
   proc_def_id_         varchar(64) comment '流程定义ID',
   config_id_           varchar(64) comment '节点配置ID',
   biz_type_            varchar(40) not null comment '消息业务类型。def=流程；node=节点（公共）；msg=消息节点',
   msg_type_            varchar(40) not null comment '消息类型。mail 邮件；sms 短信； inner 站内消息。',
   subject_rule_        varchar(255) comment '消息主题规则。对于短信类型不需要标题。',
   template_            longtext not null comment '消息模板。对于短信类型注意在界面限制消息长度。',
   primary key (id_)
);

/*==============================================================*/
/* Table: bpm_node_config                                       */
/*==============================================================*/
create table bpm_node_config
(
   config_id_           varchar(64) not null comment '节点配置ID',
   node_id_             varchar(64) not null comment '节点ID',
   node_name_           varchar(64) comment '节点名称（快照）',
   proc_def_id_         varchar(64) not null comment '流程定义ID',
   node_type_           varchar(40) not null comment '节点类型。user：用户节点；msg：消息节点；script：脚本节点；ws：WEBSERVICE节点。',
   sn_                  int comment '排序号',
   memo_                varchar(255) comment '配置备注',
   is_setting_sub_      char(1) not null default 'N' comment '是否已配置相应子表',
   primary key (config_id_)
);

alter table bpm_node_config comment '@名称：BPM_NODE_CONFIG 【节点基本配置】
@描述：节点配置
@作者：Winsto';

/*==============================================================*/
/* Table: bpm_node_identity                                     */
/*==============================================================*/
create table bpm_node_identity
(
   id_                  varchar(64) not null,
   condition_id_        varchar(64) not null,
   id_type_             varchar(40),
   extract_type_        varchar(40),
   calc_type_           varchar(40),
   sn_                  int,
   values_              text,
   labels_              text,
   primary key (id_)
);

alter table bpm_node_identity comment '节点人员配置';

/*==============================================================*/
/* Table: bpm_node_rule                                         */
/*==============================================================*/
create table bpm_node_rule
(
   id_                  varchar(64) not null comment '主键',
   name_                varchar(64) not null comment '规则名称',
   rule_code            text comment '规则表达式',
   proc_def_id_         varchar(64) comment 'Act流程发布ID',
   node_id_             varchar(64) comment '流程节点ID',
   dest_node_id_        varchar(64) comment '跳转节点',
   dest_node_name_      varchar(64) comment '跳转节点名称',
   priority_            smallint comment '优先级别',
   memo_                varchar(1024) comment '跳转规则备注',
   primary key (id_)
);

alter table bpm_node_rule comment '流程定义跳转规则';

/*==============================================================*/
/* Table: bpm_node_script                                       */
/*==============================================================*/
create table bpm_node_script
(
   id_                  varchar(64) not null comment '脚本节点配置ID',
   config_id_           varchar(64) not null comment '节点配置ID',
   script_              longtext not null comment '脚本',
   script_type_         varchar(40) not null comment '脚本类型。before.事件前置脚本；after.事件后置脚本；script 脚本节点；',
   primary key (id_)
);

alter table bpm_node_script comment '节点的脚本配置';

/*==============================================================*/
/* Table: bpm_pro_inst                                          */
/*==============================================================*/
create table bpm_pro_inst
(
   id_                  varchar(64) not null comment '流程实例ID',
   subject_             varchar(64) not null comment '流程实例标题',
   proc_def_id_         varchar(64) not null comment '流程定义ID',
   bpmn_def_id_         varchar(64) comment 'BPMN流程定义ID',
   proc_def_key_        varchar(128) comment '流程定义Key',
   proc_def_name_       varchar(128) not null comment '流程名称',
   biz_key_             varchar(64) comment '关联数据业务主键',
   form_key_            varchar(64) comment '绑定的表单主键',
   status_              varchar(40) comment '实例状态',
   end_time_            datetime comment '实例结束时间',
   duration_            bigint comment '持续时间(ms)',
   type_id_             varchar(64) comment '所属分类ID',
   result_type_         varchar(40) comment '流程结束后的最终审批结果，agree=同意；refuse=拒绝
            ',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime comment '创建时间',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (id_)
);

alter table bpm_pro_inst comment '@名称：BPM_PROCESS_INST  【流程实例】
@描述：流程实例
@作者：Winsto';

/*==============================================================*/
/* Table: bpm_pro_inst_hi                                       */
/*==============================================================*/
create table bpm_pro_inst_hi
(
   id_                  varchar(64) not null comment '流程实例ID',
   subject_             varchar(64) not null comment '流程实例标题',
   proc_def_id_         varchar(64) not null comment '流程定义ID',
   bpmn_def_id_         varchar(64) comment 'BPMN流程定义ID',
   def_key_             varchar(128) comment '流程定义Key',
   proc_def_name_       varchar(128) not null comment '流程名称',
   biz_key_             varchar(64) comment '关联数据业务主键',
   form_key_            varchar(64) comment '绑定的表单主键',
   status_              varchar(40) comment '实例状态',
   end_time_            datetime comment '实例结束时间',
   duration_            bigint comment '持续时间',
   type_id_             varchar(64) comment '所属分类ID',
   last_status          varchar(40) comment '流程结束后，整个流程实例中的最终审批状态
            agree=同意
            refuse=拒绝
            ',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime comment '创建时间',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间'
);

alter table bpm_pro_inst_hi comment '流程实例历史';

/*==============================================================*/
/* Table: bpm_pro_status                                        */
/*==============================================================*/
create table bpm_pro_status
(
   id_                  varchar(64) not null comment '主键',
   proc_inst_id_        varchar(64) not null comment '流程实例ID',
   proc_def_id_         varchar(64) not null comment 'ACT流程定义ID',
   node_id_             varchar(255) not null comment '节点ID',
   node_name_           varchar(255) comment '节点名称',
   status_              varchar(50) not null comment '状态',
   last_update_         datetime comment '最后更新时间',
   last_userid_         varchar(64),
   primary key (id_)
);

alter table bpm_pro_status comment '流程节点审批状态';

/*==============================================================*/
/* Table: bpm_pro_status_hi                                     */
/*==============================================================*/
create table bpm_pro_status_hi
(
   id_                  varchar(64) not null comment '主键',
   proc_inst_id_        varchar(64) not null comment '流程实例ID',
   proc_def_id_         varchar(64) not null comment 'ACT流程定义ID',
   node_id_             varchar(255) not null comment '节点ID',
   node_name_           varchar(255) comment '节点名称',
   status_              varchar(50) not null comment '状态',
   last_update_         datetime comment '最后更新时间',
   last_userid_         varchar(64),
   primary key (id_)
);

alter table bpm_pro_status_hi comment '流程节点审批历史状态';

/*==============================================================*/
/* Table: bpm_task                                              */
/*==============================================================*/
create table bpm_task
(
   id_                  varchar(64) not null comment '任务ID',
   name_                varchar(64) not null comment '任务名称',
   subject_             varchar(128) not null comment '待办事项标题',
   task_id_             varchar(64) comment '关联的任务ID',
   exec_id_             varchar(64) comment '关联 - 节点执行ID',
   node_id_             varchar(64) comment '关联 - 任务节点ID',
   proc_inst_id_        varchar(64) not null comment '关联 - 流程实例ID',
   proc_def_id_         varchar(64) not null comment '关联 - 流程定义ID',
   proc_def_key_        varchar(64) comment '关联 - 流程业务主键',
   proc_def_name_       varchar(64) comment '关联 - 流程名称',
   owner_id_            varchar(64) comment '任务所属人ID',
   assignee_id_         varchar(64) comment '任务执行人ID',
   status_              varchar(40) not null comment '任务状态',
   priority_            int comment '任务优先级',
   create_time_         datetime not null comment '任务创建时间',
   due_time_            datetime comment '任务到期时间',
   suspend_state_       smallint default 0 comment '是否挂起(0正常,1挂起)',
   primary key (id_)
);

alter table bpm_task comment '@名称：BPM_TASK 【流程任务】
@描述：流程任务
@作者：Winston Yan
                             -&';

/*==============================================================*/
/* Table: bpm_task_due                                          */
/*==============================================================*/
create table bpm_task_due
(
   id_                  varchar(64) not null comment '催办时间设置ID',
   name_                varchar(64) comment '名称',
   proc_def_id_         varchar(64) comment '流程定义ID',
   node_id_             varchar(64) comment '流程节点ID',
   times_               int comment '催办次数',
   start_ms_            bigint not null comment '催办开始时间(ms)
            (相对于任务创建时间，多少工作日)',
   end_ms_              bigint comment '催办结束时间(相对于催办开始时间)(ms)',
   complete_ms_         bigint comment '办结时间(ms)',
   mail_content_        text comment '催办提醒短信内容',
   msg_content_         text comment '催办邮件模板内容',
   sms_content_         text comment '催办邮件模板内容',
   action_              varchar(40) comment '任务到期处理动作
            no-action=不做任何处理
            auto-next=流程自动往下跳转
            end-process=结束该流程
            call-method=调用指定方法 如 service.xxMethod 该字段直接存储该值',
   script_              varchar(4000) comment '执行脚本',
   condition_exp_       text comment '条件表达式',
   rel_node_id_         varchar(64) comment '相对节点的节点ID',
   rel_node_name_       varchar(64) comment '相对节点的动作
            create=创建
            end=完成',
   rel_type_            varchar(40) comment '相对时间类型
            worktime=工作日
            caltime=日历日',
   primary key (id_)
);

alter table bpm_task_due comment '任务节点催办时间设置';

/*==============================================================*/
/* Table: bpm_task_read                                         */
/*==============================================================*/
create table bpm_task_read
(
   read_id_             varchar(64) not null,
   proc_def_id          varchar(64),
   proc_inst_id_        varchar(64),
   task_id_             varchar(64),
   node_id_             varchar(255),
   read_time_           datetime,
   user_id_             varchar(64),
   user_name_           varchar(32),
   primary key (read_id_)
);

alter table bpm_task_read comment '流程任务查看';

/*==============================================================*/
/* Table: bpm_task_turn                                         */
/*==============================================================*/
create table bpm_task_turn
(
   id_                  varchar(64) not null comment '更改ID',
   task_id_             varchar(64) not null comment '任务ID',
   task_name_           varchar(64) not null comment '任务名称',
   task_subject_        varchar(128) not null comment '待办事项标题',
   node_id_             varchar(64) comment '关联 - 任务节点ID',
   proc_inst_id_        varchar(64) not null comment '关联 - 流程实例ID',
   owner_id_            varchar(64) comment '任务所属人ID',
   owner_name_          varchar(64) comment '任务所属人姓名',
   exec_user_id_        varchar(64) comment '任务执行人ID',
   exec_user_name_      varchar(64) comment '任务执行人姓名',
   assignee_id_         varchar(64) comment '任务承接人ID',
   assignee_name_       varchar(64) comment '任务承接人姓名',
   status_              varchar(40) comment '状态。running 正在运行；finish 完成；cancel 取消。',
   trun_type_           varchar(40) comment '更改类型。agent 代理；turn 转办。',
   type_id_             varchar(64) comment '所属分类ID',
   memo_                varchar(255) comment '备注',
   create_time_         datetime comment '创建时间',
   finish_time_         datetime comment '任务完成时间',
   primary key (id_)
);

alter table bpm_task_turn comment '@名称：BPM_TASK_TURN 【任务的执行更改】
@描述：任务的执行更改
@作者：Wins';

/*==============================================================*/
/* Table: bpm_user_candidate                                    */
/*==============================================================*/
create table bpm_user_candidate
(
   id_                  varchar(64) not null comment '主键',
   task_id_             varchar(64) comment '任务ID',
   type_                varchar(20) comment '候选人类型',
   executor_            varchar(64) comment '执行人ID',
   act_inst_id_         varchar(64) comment '流程实例ID',
   primary key (id_)
);

alter table bpm_user_candidate comment '任务候选人';

/*==============================================================*/
/* Index: idx_candidate_taskid                                  */
/*==============================================================*/
create index idx_candidate_taskid on bpm_user_candidate
(
   task_id_
);

/*==============================================================*/
/* Index: idx_candidate_exeid                                   */
/*==============================================================*/
create index idx_candidate_exeid on bpm_user_candidate
(
   executor_,
   type_
);

/*==============================================================*/
/* Table: bpm_user_node                                         */
/*==============================================================*/
create table bpm_user_node
(
   id_                  varchar(64) not null comment '节点配置ID',
   biz_type_            varchar(40) not null comment '业务类型。normal 普通用户节点；fork 分发任务节点；join 汇总任务节点；',
   is_allow_back_       char(1) not null comment '参数 - 是否允许回退',
   is_allow_back_to_start_ char(1) not null comment '参数 - 是否允许回退至发起人',
   is_hide_opinion_field_ char(1) not null comment '参数 - 是否隐藏意见输入框',
   is_hide_exec_path_   char(1) not null comment '参数 - 是否隐藏执行路径',
   node_type_           smallint comment '节点类型(0,普通,1,分发)',
   join_node_id_        varchar(40) comment '汇总节点',
   join_node_name_      varchar(255) comment '汇总节点名称',
   sn_                  bigint comment '节点顺序编号',
   before_handler_      varchar(128) comment '前置处理器',
   after_handler_       varchar(128) comment '后置处理器',
   jump_type_           varchar(20) comment '跳转类型1=正常跳转，2=选择路径跳转，3=自由跳转，值格式如1,2',
   inform_type_         varchar(20) comment '通知类型',
   primary key (id_)
);

/*==============================================================*/
/* Table: bpm_var                                               */
/*==============================================================*/
create table bpm_var
(
   id_                  varchar(64) not null comment '流程变量ID',
   name_                varchar(64) not null comment '变量名称',
   var_key_             varchar(64) not null comment '变量业务主键',
   data_type_           varchar(40) not null comment '变量类型。可选值：string,int,float,double,date,json,xml,bytes',
   node_id_             varchar(64) comment '节点ID',
   proc_def_id_         varchar(64) not null comment '流程定义ID',
   is_required_         char(1) not null comment '是否必需',
   constraint_          text comment '数据约束定义',
   primary key (id_),
   key ak_k_var_key (var_key_)
);

alter table bpm_var comment '@名称：BPM_VAR 【流程变量】
@描述：流程变量
@作者：Winston Yan
                            -&#';

/*==============================================================*/
/* Table: bpm_var_data                                          */
/*==============================================================*/
create table bpm_var_data
(
   id_                  varchar(64) not null comment '变量大数据值ID',
   type_                varchar(40) not null comment '大数值类型。bytes 二进制；text 大文本。',
   bytes_               blob comment '二进制数值',
   text_                text comment '大文本',
   primary key (id_)
);

alter table bpm_var_data comment '@名称：BPM_VARIABLE_DATA 【流程变量大数据值】
@描述：流程变量大数据值
@作';

/*==============================================================*/
/* Table: bpm_var_inst                                          */
/*==============================================================*/
create table bpm_var_inst
(
   id_                  varchar(64) not null comment '变量实例ID',
   node_id_             varchar(64) comment '关联 - 任务节点ID',
   proc_inst_id_        varchar(64) not null comment '关联 - 流程实例ID',
   proc_def_id_         varchar(64) not null comment '关联 - 流程定义ID',
   var_key_             varchar(64) not null comment '变量业务主键',
   value_               varchar(1024) comment '变量值',
   var_data_id_         varchar(64) comment '变量大数据值ID',
   primary key (id_)
);

alter table bpm_var_inst comment '@名称：BPM_VAR_INST 【流程变量实例】
@描述：流程变量实例
@作者：Winston';

/*==============================================================*/
/* Table: bpm_ws_node                                           */
/*==============================================================*/
create table bpm_ws_node
(
   id_                  varchar(64) not null comment 'WS节点配置ID',
   doc_                 longtext not null comment 'webservice地址',
   primary key (id_)
);

/*==============================================================*/
/* Table: bpm_ws_node_params                                    */
/*==============================================================*/
create table bpm_ws_node_params
(
   param_id_            varchar(64) not null comment '节点参数ID',
   id_                  varchar(64) comment 'WS节点配置ID',
   param_type_          varchar(40) comment '参数类型【in：表示输入参数，out：输出参数】',
   var_id_              varchar(64) comment '变量id',
   ws_name_             varchar(128) comment 'WS变量名称',
   data_type_           varchar(40) comment '变量类型【变量类型，string long date,等】',
   primary key (param_id_)
);

/*==============================================================*/
/* Table: xbo_attr                                              */
/*==============================================================*/
create table xbo_attr
(
   id_                  varchar(64) not null comment '属性定义ID',
   def_id_              varchar(64) not null comment '从属对象定义ID',
   name_                varchar(64) not null comment '属性名称',
   desc_                varchar(255) comment '属性描述',
   type_                varchar(40) not null comment '属性类型。base=基本；ref=对象引用（hasOne | hasMany 等）',
   is_list_             char(1) not null comment '是否列表属性',
   data_type_           varchar(40) comment '数据类型。string=字符串；number=数值；datetime=日期（长日期，通过显示格式来限制）；',
   default_value_       varchar(1024) comment '基本默认值',
   format_              varchar(255) comment '基本类型显示格式',
   is_required_         char(1) not null default 'N' comment '是否必填',
   ref_type_            varchar(40) comment '对象引用类型。hasOne=单个对象；hasMany=对象集合；',
   ref_id_              varchar(64) comment '对象引用ID',
   primary key (id_)
);

/*==============================================================*/
/* Table: xbo_def                                               */
/*==============================================================*/
create table xbo_def
(
   id_                  varchar(64) not null comment '对象定义ID',
   package_id_          varchar(64) not null comment '包ID',
   code_                varchar(64) not null comment '对象编码',
   name_                varchar(64) not null comment '对象名称',
   desc_                varchar(255) comment '对象描述',
   package_             varchar(255) comment '包路径',
   version_             int not null comment '版本号',
   is_main              char(1) not null comment '是否主版本',
   is_master_           char(1) not null comment '是否主对象',
   status_              varchar(40) not null comment '状态。inactive=未激活；actived=激活；forbidden=禁用',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   data_type_           varchar(64) comment '实例化时保存数据的格式',
   primary key (id_)
);

/*==============================================================*/
/* Table: xbo_inst                                              */
/*==============================================================*/
create table xbo_inst
(
   id_                  varchar(64) not null comment '业务实例ID',
   def_id_              varchar(64) not null comment '对象定义ID',
   inst_data_           longtext comment '实例数据',
   create_time_         datetime not null comment '创建时间',
   primary key (id_)
);

alter table xbo_inst comment '业务对象实例';

/*==============================================================*/
/* Table: xbo_package                                           */
/*==============================================================*/
create table xbo_package
(
   package_id_          varchar(64) not null comment '包ID',
   name_                varchar(64) not null comment '包名',
   path_                varchar(255) comment '包路径',
   depth_               int not null comment '层次',
   desc_                varchar(255) comment '包描述',
   parent_id_           varchar(64) comment '上级包ID',
   status_              varchar(40) not null comment '状态。inactive=未激活；actived=激活；forbidden=禁用',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   primary key (package_id_)
);

alter table xbo_package comment '业务实体包';

/*==============================================================*/
/* Table: xbo_rule                                              */
/*==============================================================*/
create table xbo_rule
(
   id_                  varchar(64) not null comment '规则ID',
   def_id_              varchar(64) not null comment '对象定义ID',
   attr_id_             varchar(64) comment '属性定义ID',
   belong_type_         varchar(40) not null comment '从属类型。def=对象定义；attr=只属于某个属性',
   name_                varchar(40) comment '规则名称',
   rule_type_           varchar(40) comment '规则类型',
   content_             varchar(1024) comment '规则内容',
   tip_info_            varchar(255) comment '提示信息',
   primary key (id_)
);

/*==============================================================*/
/* Table: xbo_rule_ref                                          */
/*==============================================================*/
create table xbo_rule_ref
(
   id_                  varchar(64) not null comment '主键ID',
   rule_id_             varchar(64) not null comment '规则ID',
   attr_id_             varchar(64) comment '属性定义ID',
   primary key (id_)
);

/*==============================================================*/
/* Table: xb_db_id                                              */
/*==============================================================*/
create table xb_db_id
(
   id_                  varchar(64) not null comment 'ID',
   start_               bigint not null comment '开始ID值',
   max_                 bigint not null comment '当前允许最大的ID值',
   mac_name_            varchar(255) not null comment '机器名称',
   primary key (id_)
);

alter table xb_db_id comment '数据库ID增长表';

/*==============================================================*/
/* Table: xb_type                                               */
/*==============================================================*/
create table xb_type
(
   id_                  varchar(64) not null comment '分类ID',
   type_group_key_      varchar(64) not null comment '所属分类组业务主键',
   name_                varchar(128) not null comment '分类名称',
   type_key_            varchar(64) not null comment '节点的分类Key',
   stru_type_           varchar(40) not null comment 'flat 平行；tree 树形',
   parent_id_           varchar(64) comment '父节点',
   depth_               int comment '层次',
   path_                varchar(255) comment '路径',
   is_leaf_             char(1) comment '是否叶子节点。Y=是；N=否',
   sn_                  int not null comment '序号',
   create_by_           varchar(64) not null comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (id_)
);

alter table xb_type comment '总分类表。用于显示平级或树层次结构的分类，可以允许任何层次结构。';

/*==============================================================*/
/* Table: xb_type_group                                         */
/*==============================================================*/
create table xb_type_group
(
   id_                  varchar(64) not null comment '主键ID',
   group_key_           varchar(64) not null comment '分类组业务主键',
   name_                varchar(128) not null comment '分类名',
   flag_                int comment '标识',
   sn_                  int comment '序号',
   type_                varchar(40) comment '类别。0=平铺结构；1=树型结构',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime comment '创建时间',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (id_)
);

alter table xb_type_group comment '系统分类组值表';

/*==============================================================*/
/* Table: xog_attr                                              */
/*==============================================================*/
create table xog_attr
(
   attr_id_             varchar(64) not null comment '属性ID',
   name_                varchar(64) not null comment '属性名称',
   attr_key_            varchar(64) not null comment '属性业务主键',
   belong_type_         varchar(40) not null comment '属性归属类型：group：组属性；user：用户属性',
   data_type_           varchar(40) not null comment '属性数据类型。text 字符串；date 日期；long 长整数；double 双精度浮点。',
   desc_                varchar(255) comment '属性描述',
   create_org_id_       varchar(64) comment '创建者所属组织ID',
   is_deleted           char(1) not null comment '是否删除。是 Y；否 N。',
   primary key (attr_id_),
   unique key ak_k_attr_key_ (attr_key_)
);

alter table xog_attr comment '@名称：XOG_ATTR【用户组织属性扩展表】
@描述：用户组织属性扩展表
@作者：Winsto';

/*==============================================================*/
/* Table: xog_attr_val                                          */
/*==============================================================*/
create table xog_attr_val
(
   val_id_              varchar(64) not null comment '属性值ID',
   attr_id_             varchar(64) not null comment '属性ID',
   attr_key_            varchar(64) not null comment '属性业务主键',
   user_id_             varchar(64) comment '用户ID',
   group_id_            varchar(64) comment '用户组ID',
   data_type_           varchar(40) not null comment '属性数据类型。text 字符串；date 日期；long 长整数；double 双精度浮点。',
   text_val             varchar(1024) comment '字符串属性值',
   date_val             datetime comment '日期类型属性值',
   long_val             bigint comment '长整数类型属性值',
   dec_val              decimal(20,4) comment '双精度类型属性值',
   primary key (val_id_)
);

alter table xog_attr_val comment '@名称：XOG_ATTR_VAL【用户及组属性值】
@描述：用户及组属性值
@作者：Winsto';

/*==============================================================*/
/* Table: xog_dimension                                         */
/*==============================================================*/
create table xog_dimension
(
   dim_id_              varchar(64) not null comment '维度ID',
   name_                varchar(40) not null comment '维度名称',
   dim_key_             varchar(64) not null comment '维度业务主键',
   is_combination_      char(1) not null comment '是否组合维度',
   is_system_           char(1) not null comment '是否系统预设维度',
   sn_                  int not null comment '排序号',
   is_deleted_          char(1) not null comment '是否删除。是 Y；否 N。',
   show_type_           varchar(40) not null comment 'tree=树型数据。flat=平铺数据',
   grade_auth_          char(1) not null comment '是否支持分级授权',
   allow_type_          char(1) not null comment '是否支持等级分类',
   desc_                varchar(255) comment '描述',
   primary key (dim_id_),
   unique key ak_k_dim_key (dim_key_)
);

alter table xog_dimension comment '@名称：XOG_DIMENSION【维度】
@描述：维度
@作者：Winston Yan
                                  -&';

/*==============================================================*/
/* Table: xog_dim_rel                                           */
/*==============================================================*/
create table xog_dim_rel
(
   id_                  varchar(64) not null comment '维度业务关系ID',
   dim_id_              varchar(64) not null comment '当前维度ID',
   rel_dim_id_          varchar(64) not null comment '关联维度ID',
   rel_type_            varchar(40) not null comment '关系类型。combination=组合维度',
   primary key (id_)
);

/*==============================================================*/
/* Table: xog_group                                             */
/*==============================================================*/
create table xog_group
(
   group_id_            varchar(64) not null comment '用户组ID',
   name_                varchar(64) not null comment '用户组名称',
   key_                 varchar(64) not null comment '用户组业务主键',
   dim_key_             varchar(64) not null comment '所属维度业务主键',
   rank_type_key_       varchar(64) comment '所属用户组等级分类',
   status_              varchar(40) not null comment '状态。0 未激活；1 已激活；2 锁定。',
   desc_                varchar(255) comment '描述',
   sn_                  int not null comment '排序号',
   parent_id_           varchar(64) comment '父用户组ID',
   depth_               int comment '层次',
   path_                varchar(1024) comment '路径',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   create_saas_id_      varchar(64) comment '公共 - 创建者所属SAAS ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   form_                varchar(20) comment '来源
            sysem,系统添加,import导入,grade,分级添加的',
   primary key (group_id_),
   unique key ak_k_group_key (key_)
);

alter table xog_group comment '@名称：XOG_GROUP【系统用户组】
@描述：系统用户组
@作者：Winston Yan
                              ';

/*==============================================================*/
/* Table: xog_group_auth                                        */
/*==============================================================*/
create table xog_group_auth
(
   id_                  varchar(64) not null comment '主键',
   rel_type_id_         varchar(64) not null comment '关系类型ID',
   manager_id_          varchar(64) not null comment '分级管理员用户ID',
   group_id_            varchar(64) not null comment '被授权的用户组ID',
   dim_id_              varchar(64) not null comment '用户组所属维度ID',
   show_type_           varchar(40) not null comment 'tree=树型数据。flat=平铺数据',
   group_perms_         varchar(255) comment '针对子用户组的添加、更新和删除。',
   user_perms_          varchar(255) comment '针对用户与组关系的添加、更新和删除。',
   primary key (id_)
);

alter table xog_group_auth comment '分级管理具体授权';

/*==============================================================*/
/* Table: xog_group_rel                                         */
/*==============================================================*/
create table xog_group_rel
(
   id_                  varchar(64) not null comment '用户组关系ID',
   rel_type_id_         varchar(64) comment '关系类型ID',
   group_id_            varchar(64) not null comment '当前用户组ID',
   rel_group_id_        varchar(64) not null comment '关联用户组ID',
   current_dim_id_      varchar(64) not null comment '冗余 - 当前用户组所属维度ID',
   rel_dim_id_          varchar(64) not null comment '冗余 - 关联用户组所属维度ID',
   is_combination_      char(1) not null comment '是否由组合维度创建',
   status_              varchar(40) not null comment '状态。inactive=未激活；actived=激活；forbidden=禁用；archives=归档（历史关系）',
   start_time_          datetime comment '有效时间起始',
   end_time_            datetime comment '有效时间起始',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (id_)
);

/*==============================================================*/
/* Table: xog_rank_type                                         */
/*==============================================================*/
create table xog_rank_type
(
   id_                  varchar(64) not null comment '主键',
   dim_id_              varchar(64) comment '维度ID',
   name_                varchar(255) not null comment '名称',
   key_                 varchar(64) not null comment '分类业务键',
   level_               int not null comment '级别数值',
   primary key (id_)
);

alter table xog_rank_type comment '用户组等级分类';

/*==============================================================*/
/* Table: xog_rel_type                                          */
/*==============================================================*/
create table xog_rel_type
(
   id_                  varchar(64) not null comment '关系类型ID',
   name_                varchar(64) not null comment '关系名',
   key_                 varchar(64) not null comment '关系业务主键',
   type_                varchar(40) not null comment '关系归属。用户关系=user；用户组关系=group；用户与组关系=usergroup',
   const_type_          varchar(40) not null comment '关系约束类型。1对1=one2one；1对多=one2many；多对1=many2one；多对多=many2many',
   current_name_        varchar(128) not null comment '关系当前方名称',
   rel_name_            varchar(128) not null comment '关系关联方名称',
   current_dim_id_      varchar(64) comment '当前方维度ID（仅对用户组关系）',
   rel_dim_id_          varchar(64) comment '关联方维度ID（用户关系忽略此值）',
   status_              varchar(40) not null comment '关系状态。inactive=未激活；actived=激活；forbidden=禁用',
   memo_                varchar(255) comment '关系备注',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   create_saas_id_      varchar(64) comment '公共 - 创建者所属SAAS ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (id_)
);

alter table xog_rel_type comment '用户及组关系类型定义';

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
   create_saas_id_      varchar(64) comment '公共 - 创建者所属SAAS ID',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   from_                varchar(20) comment '来源
            system,系统添加,import,导入,grade,分级添加的',
   primary key (user_id_),
   unique key ak_k_account (account_)
);

alter table xog_user comment '@名称：XOG_USER【系统用户】
@描述：系统用户
@作者：Winston Yan
                             -&#';

/*==============================================================*/
/* Table: xog_user_group                                        */
/*==============================================================*/
create table xog_user_group
(
   rel_id_              varchar(64) not null comment '主键',
   rel_type_id_         varchar(64) not null comment '关系类型ID',
   user_id_             varchar(64) not null comment '用户ID',
   group_id_            varchar(64) not null comment '用户组ID',
   dim_id_              varchar(64) not null comment '维度ID',
   status_              varchar(40) not null comment '状态。inactive=未激活；actived=激活；forbidden=禁用；archives=归档（历史关系）',
   start_time_          datetime comment '有效时间起始',
   end_time_            datetime comment '有效时间起始',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   from_                varchar(20) comment '来源
            system,系统添加,import,系统导入,grade,分级添加的',
   primary key (rel_id_)
);

alter table xog_user_group comment '@名称：XOG_USER_GROUP【用户和组关系表】
@描述：用户和组关系表。用户和组的关系一旦建立就不能';

/*==============================================================*/
/* Table: xog_user_rel                                          */
/*==============================================================*/
create table xog_user_rel
(
   rel_id_              varchar(64) not null comment '用户ID',
   rel_type_id_         varchar(64) comment '关系类型ID',
   user_id_             varchar(64) not null comment '当前用户ID',
   rel_user_id_         varchar(64) not null comment '关联用户ID',
   status_              varchar(40) not null comment '状态。inactive=未激活；actived=激活；forbidden=禁用；archives=归档（历史关系）',
   start_time_          datetime comment '有效时间起始',
   end_time_            datetime comment '有效时间起始',
   create_by_           varchar(64) comment '创建人ID',
   create_time_         datetime not null comment '创建时间',
   update_by_           varchar(64) comment '更新人ID',
   update_time_         datetime comment '更新时间',
   primary key (rel_id_)
);

alter table xog_user_rel comment '@名称：XOG_USER_REL【用户关系表】
@描述：用户关系表
@作者：Winston Ya';

alter table bpm_agent_auth add constraint fk_reference_26 foreign key (setting_id_)
      references bpm_agent_setting (id_) on delete restrict on update restrict;

alter table bpm_agent_def add constraint fk_reference_30 foreign key (agent_auth_id_)
      references bpm_agent_auth (id_) on delete restrict on update restrict;

alter table bpm_check_opinion add constraint fk_reference_23 foreign key (task_id_)
      references bpm_task (id_) on delete restrict on update restrict;

alter table bpm_def_form add constraint fk_definition_r_form foreign key (pro_def_id)
      references bpm_definition (def_id_) on delete restrict on update restrict;

alter table bpm_excutor_con add constraint fk_reference_21 foreign key (proc_def_id_)
      references bpm_definition (def_id_) on delete restrict on update restrict;

alter table bpm_message add constraint fk_reference_22 foreign key (proc_inst_id_)
      references bpm_pro_inst (id_) on delete restrict on update restrict;

alter table bpm_msg_rule add constraint fk_reference_27 foreign key (proc_def_id_)
      references bpm_definition (def_id_) on delete restrict on update restrict;

alter table bpm_msg_rule add constraint fk_node_config_r_node_msg foreign key (config_id_)
      references bpm_node_config (config_id_) on delete restrict on update restrict;

alter table bpm_node_config add constraint fk_reference_6 foreign key (proc_def_id_)
      references bpm_definition (def_id_) on delete cascade on update restrict;

alter table bpm_node_identity add constraint fk_con_r_identity foreign key (condition_id_)
      references bpm_excutor_con (id) on delete restrict on update restrict;

alter table bpm_node_rule add constraint fk_reference_10 foreign key (proc_def_id_)
      references bpm_definition (def_id_) on delete cascade on update restrict;

alter table bpm_node_script add constraint fk_reference_18 foreign key (config_id_)
      references bpm_node_config (config_id_) on delete restrict on update restrict;

alter table bpm_task add constraint fk_reference_20 foreign key (proc_inst_id_)
      references bpm_pro_inst (id_) on delete restrict on update restrict;

alter table bpm_task_due add constraint fk_reference_11 foreign key (proc_def_id_)
      references bpm_definition (def_id_) on delete restrict on update restrict;

alter table bpm_task_read add constraint fk_reference_25 foreign key (proc_inst_id_)
      references bpm_pro_inst (id_) on delete cascade on update restrict;

alter table bpm_task_turn add constraint fk_reference_24 foreign key (task_id_)
      references bpm_task (id_) on delete restrict on update restrict;

alter table bpm_user_candidate add constraint fk_task_r_candidate foreign key (task_id_)
      references bpm_task (id_) on delete restrict on update restrict;

alter table bpm_var add constraint fk_reference_8 foreign key (proc_def_id_)
      references bpm_definition (def_id_) on delete cascade on update restrict;

alter table bpm_ws_node_params add constraint fk_reference_19 foreign key (id_)
      references bpm_ws_node (id_) on delete restrict on update restrict;

alter table xbo_attr add constraint fk_xbo_attr_r_def foreign key (def_id_)
      references xbo_def (id_) on delete restrict on update restrict;

alter table xbo_def add constraint fk_xbo_def_r_package foreign key (package_id_)
      references xbo_package (package_id_) on delete restrict on update restrict;

alter table xbo_inst add constraint fk_xbo_inst_r_def foreign key (def_id_)
      references xbo_def (id_) on delete restrict on update restrict;

alter table xbo_rule add constraint fk_reference_7 foreign key (attr_id_)
      references xbo_attr (id_) on delete restrict on update restrict;

alter table xbo_rule_ref add constraint fk_reference_15 foreign key (rule_id_)
      references xbo_rule (id_) on delete restrict on update restrict;

alter table xbo_rule_ref add constraint fk_reference_29 foreign key (attr_id_)
      references xbo_attr (id_) on delete restrict on update restrict;

alter table xb_type add constraint fk_xb_gl_type_r_type_key foreign key (type_group_key_)
      references xb_type_group (group_key_) on delete cascade on update restrict;

alter table xog_attr_val add constraint fk_reference_3 foreign key (attr_id_)
      references xog_attr (attr_id_) on delete restrict on update restrict;

alter table xog_attr_val add constraint fk_reference_38 foreign key (group_id_)
      references xog_group (group_id_) on delete restrict on update restrict;

alter table xog_attr_val add constraint fk_reference_4 foreign key (user_id_)
      references xog_user (user_id_) on delete restrict on update restrict;

alter table xog_dim_rel add constraint fk_reference_36 foreign key (dim_id_)
      references xog_dimension (dim_id_) on delete restrict on update restrict;

alter table xog_dim_rel add constraint fk_reference_37 foreign key (rel_dim_id_)
      references xog_dimension (dim_id_) on delete restrict on update restrict;

alter table xog_group_auth add constraint fk_reference_39 foreign key (rel_type_id_)
      references xog_rel_type (id_) on delete restrict on update restrict;

alter table xog_group_rel add constraint fk_reference_32 foreign key (group_id_)
      references xog_group (group_id_) on delete restrict on update restrict;

alter table xog_group_rel add constraint fk_reference_34 foreign key (rel_group_id_)
      references xog_group (group_id_) on delete restrict on update restrict;

alter table xog_group_rel add constraint fk_reference_35 foreign key (rel_type_id_)
      references xog_rel_type (id_) on delete restrict on update restrict;

alter table xog_rank_type add constraint fk_dimension_r_type foreign key (dim_id_)
      references xog_dimension (dim_id_) on delete restrict on update restrict;

alter table xog_user_group add constraint fk_reference_1 foreign key (user_id_)
      references xog_user (user_id_) on delete restrict on update restrict;

alter table xog_user_group add constraint fk_reference_13 foreign key (group_id_)
      references xog_group (group_id_) on delete restrict on update restrict;

alter table xog_user_group add constraint fk_reference_16 foreign key (rel_type_id_)
      references xog_rel_type (id_) on delete restrict on update restrict;

alter table xog_user_rel add constraint fk_reference_12 foreign key (rel_user_id_)
      references xog_user (user_id_) on delete restrict on update restrict;

alter table xog_user_rel add constraint fk_reference_14 foreign key (rel_type_id_)
      references xog_rel_type (id_) on delete restrict on update restrict;

alter table xog_user_rel add constraint fk_reference_9 foreign key (user_id_)
      references xog_user (user_id_) on delete restrict on update restrict;

