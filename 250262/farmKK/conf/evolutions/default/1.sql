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
  age                       varchar(255),
  status                    varchar(255),
  picture                   varchar(255),
  date                      datetime,
  weight                    varchar(255),
  height                    varchar(255),
  price                     varchar(255),
  breed_id                  varchar(255),
  constraint pk_tbaddcow primary key (cow_id))
;

create table tbFoods (
  id                        varchar(255) not null,
  animalfoods_name          varchar(255),
  animalfoods_type          varchar(255),
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
  unit                      varchar(255),
  picture                   varchar(255),
  price_ifoods              integer,
  amount_ifoods             integer,
  totalamount               integer,
  amount_kg                 integer,
  total                     integer,
  total_price               integer,
  date                      datetime,
  inputfoods_id             varchar(255),
  constraint pk_tbInputfood primary key (id_ifoods))
;

create table tbPlanVacs (
  id                        varchar(255) not null,
  pvacdate                  datetime,
  pdate                     varchar(255),
  pdate1                    varchar(255),
  status                    varchar(255),
  cow_lst_cow_id            varchar(255),
  vac_id                    varchar(255),
  vacfinaly_id              varchar(255),
  constraint pk_tbPlanVacs primary key (id))
;

create table tbRecipe (
  rid                       varchar(255) not null,
  number                    integer,
  amount_cow                integer,
  am1                       integer,
  am2                       integer,
  am3                       integer,
  am4                       integer,
  total                     integer,
  date_time_recipe          varchar(255),
  date_recipe               datetime,
  foods_id                  varchar(255),
  inputfood_id_ifoods       varchar(255),
  inputfood1_id_ifoods      varchar(255),
  inputfood2_id_ifoods      varchar(255),
  inputfood3_id_ifoods      varchar(255),
  constraint pk_tbRecipe primary key (rid))
;

create table saveFood (
  id                        bigint auto_increment not null,
  datesavefood              datetime,
  round                     integer,
  mount                     integer,
  kg                        integer,
  cowdata_cow_id            varchar(255),
  recip_rid                 varchar(255),
  ifoode_id_ifoods          varchar(255),
  constraint pk_saveFood primary key (id))
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
  constraint pk_tbBreeds primary key (id))
;

create table db_coo (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  address                   varchar(255),
  tel                       varchar(255),
  picture                   varchar(255),
  constraint pk_db_coo primary key (id))
;

create table tbFarm (
  farm_id                   varchar(255) not null,
  farm_details              longtext,
  fram_name                 longtext,
  farm_address              longtext,
  farm_tel                  longtext,
  picture                   longtext,
  date_farm                 datetime,
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
  amount_kg                 varchar(255),
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
alter table tbPlanVacs add constraint fk_tbPlanVacs_cowLst_4 foreign key (cow_lst_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_tbPlanVacs_cowLst_4 on tbPlanVacs (cow_lst_cow_id);
alter table tbPlanVacs add constraint fk_tbPlanVacs_vac_5 foreign key (vac_id) references tbVac (id) on delete restrict on update restrict;
create index ix_tbPlanVacs_vac_5 on tbPlanVacs (vac_id);
alter table tbPlanVacs add constraint fk_tbPlanVacs_vacfinaly_6 foreign key (vacfinaly_id) references tbVac (id) on delete restrict on update restrict;
create index ix_tbPlanVacs_vacfinaly_6 on tbPlanVacs (vacfinaly_id);
alter table tbRecipe add constraint fk_tbRecipe_foods_7 foreign key (foods_id) references tbFoods (id) on delete restrict on update restrict;
create index ix_tbRecipe_foods_7 on tbRecipe (foods_id);
alter table tbRecipe add constraint fk_tbRecipe_inputfood_8 foreign key (inputfood_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_tbRecipe_inputfood_8 on tbRecipe (inputfood_id_ifoods);
alter table tbRecipe add constraint fk_tbRecipe_inputfood1_9 foreign key (inputfood1_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_tbRecipe_inputfood1_9 on tbRecipe (inputfood1_id_ifoods);
alter table tbRecipe add constraint fk_tbRecipe_inputfood2_10 foreign key (inputfood2_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_tbRecipe_inputfood2_10 on tbRecipe (inputfood2_id_ifoods);
alter table tbRecipe add constraint fk_tbRecipe_inputfood3_11 foreign key (inputfood3_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_tbRecipe_inputfood3_11 on tbRecipe (inputfood3_id_ifoods);
alter table saveFood add constraint fk_saveFood_cowdata_12 foreign key (cowdata_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_saveFood_cowdata_12 on saveFood (cowdata_cow_id);
alter table saveFood add constraint fk_saveFood_recip_13 foreign key (recip_rid) references tbRecipe (rid) on delete restrict on update restrict;
create index ix_saveFood_recip_13 on saveFood (recip_rid);
alter table saveFood add constraint fk_saveFood_ifoode_14 foreign key (ifoode_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_saveFood_ifoode_14 on saveFood (ifoode_id_ifoods);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbagent;

drop table tbaddcow;

drop table tbFoods;

drop table tbinputcow;

drop table tbInputfood;

drop table tbPlanVacs;

drop table tbRecipe;

drop table saveFood;

drop table tbUser;

drop table tbVac;

drop table tbBreeds;

drop table db_coo;

drop table tbFarm;

drop table farmData;

drop table tbFood;

SET FOREIGN_KEY_CHECKS=1;

