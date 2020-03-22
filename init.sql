/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/3/19 ������ 17:09:52                       */
/*==============================================================*/


drop table if exists device;

/*==============================================================*/
/* Table: device                                                */
/*==============================================================*/
create table device
(
   id                   bigint not null comment '����',
   dev_no               varchar(32) not null comment '�豸���',
   dev_name             varchar(64) not null comment '�豸����',
   date_create          timestamp comment '����ʱ��',
   date_update          timestamp comment '����ʱ��',
   primary key (id)
);

alter table device comment '�豸';

