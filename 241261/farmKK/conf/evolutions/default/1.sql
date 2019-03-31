# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbagent (
  agent_id                  varchar(255) not null,
  agent_name                varchar(255),
  agent_address             varchar(255),
  agent_tel                 varchar(255),
  picture                   varchar(255),
  constraint pk_tbagent primary key (agent_id))
;

create table tbaddcow (
  cow_id                    varchar(255) not null,
  sex                       varchar(255),
  color                     varchar(255),
  date                      varchar(255),
  age                       varchar(255),
  status                    varchar(255),
  picture                   varchar(255),
  weight                    varchar(255),
  height                    varchar(255),
  price                     varchar(255),
  breed_id                  varchar(255),
  constraint pk_tbaddcow primary key (cow_id))
;

create table tbFoods (
  id                        varchar(255) not null,
  animalfoods_name          varchar(255),
  picture                   varchar(255),
  details                   varchar(255),
  constraint pk_tbFoods primary key (id))
;

create table tbinputcow (
  id                        varchar(255) not null,
  date                      varchar(255),
  confirm                   varchar(255),
  cow_cow_id                varchar(255),
  constraint pk_tbinputcow primary key (id))
;

create table tbInputfood (
  id_ifoods                 varchar(255) not null,
  name_ifoods               varchar(255),
  date_ifoods               varchar(255),
  picture                   varchar(255),
  amount_ifoods             double,
  price_ifoods              double,
  inputfoods_id             varchar(255),
  constraint pk_tbInputfood primary key (id_ifoods))
;

create table tbRecipe (
  rid                       varchar(255) not null,
  recipe1                   varchar(255),
  recipe2                   varchar(255),
  foods_id                  varchar(255),
  inputfood_id_ifoods       varchar(255),
  constraint pk_tbRecipe primary key (rid))
;

create table tbUser (
  id                        varchar(255) not null,
  user_name                 varchar(255),
  user_password             varchar(255),
  position                  varchar(255),
  name                      varchar(255),
  lastname                  varchar(255),
  address                   varchar(255),
  tel                       varchar(255),
  email                     varchar(255),
  age                       varchar(255),
  constraint pk_tbUser primary key (id))
;

create table tbVac (
  id                        varchar(255) not null,
  name                      varchar(255),
  pr                        varchar(255),
  picture                   varchar(255),
  constraint pk_tbVac primary key (id))
;

create table tbBreeds (
  id                        varchar(255) not null,
  name                      varchar(255),
  picture                   varchar(255),
  constraint pk_tbBreeds primary key (id))
;

create table db_coo (
  id                        varchar(255) not null,
  name                      varchar(255),
  address                   varchar(255),
  tel                       varchar(255),
  picture                   varchar(255),
  constraint pk_db_coo primary key (id))
;

create table tbFarm (
  farm_id                   varchar(255) not null,
  farm_details              longtext,
  fram_name                 varchar(255),
  farm_address              varchar(255),
  farm_tel                  varchar(255),
  picture                   varchar(255),
  date_farm                 varchar(255),
  constraint pk_tbFarm primary key (farm_id))
;

create table farmData (
  farm_id                   varchar(255) not null,
  fram_name                 varchar(255),
  farm_address              varchar(255),
  farm_tel                  varchar(255),
  picture                   varchar(255),
  constraint pk_farmData primary key (farm_id))
;

create table tbFood (
  id                        varchar(255) not null,
  animalfoods_name          varchar(255),
  animalfoods_unit          varchar(255),
  picture                   varchar(255),
  details                   varchar(255),
  animalfoods_amount        double,
  animalfoods_price         double,
  constraint pk_tbFood primary key (id))
;

alter table tbaddcow add constraint fk_tbaddcow_breed_1 foreign key (breed_id) references tbBreeds (id) on delete restrict on update restrict;
create index ix_tbaddcow_breed_1 on tbaddcow (breed_id);
alter table tbinputcow add constraint fk_tbinputcow_cow_2 foreign key (cow_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_tbinputcow_cow_2 on tbinputcow (cow_cow_id);
alter table tbInputfood add constraint fk_tbInputfood_inputfoods_3 foreign key (inputfoods_id) references tbFoods (id) on delete restrict on update restrict;
create index ix_tbInputfood_inputfoods_3 on tbInputfood (inputfoods_id);
alter table tbRecipe add constraint fk_tbRecipe_foods_4 foreign key (foods_id) references tbFoods (id) on delete restrict on update restrict;
create index ix_tbRecipe_foods_4 on tbRecipe (foods_id);
alter table tbRecipe add constraint fk_tbRecipe_inputfood_5 foreign key (inputfood_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_tbRecipe_inputfood_5 on tbRecipe (inputfood_id_ifoods);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbagent;

drop table tbaddcow;

drop table tbFoods;

drop table tbinputcow;

drop table tbInputfood;

drop table tbRecipe;

drop table tbUser;

drop table tbVac;

drop table tbBreeds;

drop table db_coo;

drop table tbFarm;

drop table farmData;

drop table tbFood;

SET FOREIGN_KEY_CHECKS=1;

