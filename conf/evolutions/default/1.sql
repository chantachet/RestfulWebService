# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table task (
  id                            bigint not null,
  subject                       varchar(255),
  constraint pk_task primary key (id)
);
create sequence task_seq;

create table task_detail (
  id                            bigint not null,
  title                         varchar(255),
  status                        varchar(255),
  task_id                       bigint,
  constraint pk_task_detail primary key (id)
);
create sequence task_detail_seq;

alter table task_detail add constraint fk_task_detail_task_id foreign key (task_id) references task (id) on delete restrict on update restrict;
create index ix_task_detail_task_id on task_detail (task_id);


# --- !Downs

alter table task_detail drop constraint if exists fk_task_detail_task_id;
drop index if exists ix_task_detail_task_id;

drop table if exists task;
drop sequence if exists task_seq;

drop table if exists task_detail;
drop sequence if exists task_detail_seq;

