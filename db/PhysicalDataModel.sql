drop index if exists GENDER_PK;

drop table if exists GENDER;

drop index if exists RL_PERSON_GENDER_FK;

drop index if exists PERSON_PK;

drop table if exists PERSON;

/*==============================================================*/
/* Table: GENDER                                                */
/*==============================================================*/
create table GENDER (
   GENDER_ID            SERIAL               not null,
   GENDER_DESCRIPTION   TEXT                 not null,
   constraint PK_GENDER primary key (GENDER_ID)
);

/*==============================================================*/
/* Index: GENDER_PK                                             */
/*==============================================================*/
create unique index GENDER_PK on GENDER (
GENDER_ID
);

/*==============================================================*/
/* Table: PERSON                                                */
/*==============================================================*/
create table PERSON (
   GENDER_ID            INT4                 not null,
   PERSON_ID            SERIAL               not null,
   PERSON_NAME          TEXT                 not null,
   PERSON_BIRTH_DATE    DATE                 not null,
   PERSON_EMAIL         TEXT                 not null,
   PERSON_CPF           TEXT                 not null,
   PERSON_START_DATE    DATE                 not null,
   PERSON_TEAM          TEXT                 null,
   constraint PK_PERSON primary key (GENDER_ID, PERSON_ID),
   constraint AK_UNIQUE_PERSON unique (PERSON_CPF)
);

/*==============================================================*/
/* Index: PERSON_PK                                             */
/*==============================================================*/
create unique index PERSON_PK on PERSON (
GENDER_ID,
PERSON_ID
);

/*==============================================================*/
/* Index: RL_PERSON_GENDER_FK                                   */
/*==============================================================*/
create  index RL_PERSON_GENDER_FK on PERSON (
GENDER_ID
);

alter table PERSON
   add constraint FK_PERSON_RL_PERSON_GENDER foreign key (GENDER_ID)
      references GENDER (GENDER_ID)
      on delete restrict on update restrict;

/*==============================================================*/
/* Index: POPULATED GENDER TABLE                                */
/*==============================================================*/
insert into gender (gender_description) values ('I prefer not to inform');
insert into gender (gender_description) values ('Female');
insert into gender (gender_description) values ('Male');
insert into gender (gender_description) values ('not binary');

