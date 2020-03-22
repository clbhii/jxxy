/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/3/19 星期四 17:09:52                       */
/*==============================================================*/


drop table if exists device;

/*==============================================================*/
/* Table: device                                                */
/*==============================================================*/
create table device
(
   id                   bigint not null comment '主键',
   dev_no               varchar(32) not null comment '设备编号',
   dev_name             varchar(64) not null comment '设备名称',
   date_create          timestamp comment '创建时间',
   date_update          timestamp comment '更新时间',
   primary key (id)
);

alter table device comment '设备';

