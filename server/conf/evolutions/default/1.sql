# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table channel (
  channel_id                    integer auto_increment not null,
  channel_name                  varchar(255),
  constraint pk_channel primary key (channel_id)
);

create table post (
  post_id                       integer auto_increment not null,
  is_profane                    tinyint(1) default 0,
  text                          varchar(255),
  date_created                  datetime,
  constraint pk_post primary key (post_id)
);

create table tag (
  tag_id                        integer auto_increment not null,
  tag_name                      varchar(255),
  constraint pk_tag primary key (tag_id)
);

create table interest_followers (
  tag_tag_id                    integer not null,
  user_profile_id               integer not null,
  constraint pk_interest_followers primary key (tag_tag_id,user_profile_id)
);

create table user_profile (
  id                            integer auto_increment not null,
  username                      varchar(255),
  password                      varchar(255),
  fname                         varchar(255),
  lname                         varchar(255),
  access_level                  varchar(255),
  date_created                  datetime,
  constraint pk_user_profile primary key (id)
);

alter table interest_followers add constraint fk_interest_followers_tag foreign key (tag_tag_id) references tag (tag_id) on delete restrict on update restrict;
create index ix_interest_followers_tag on interest_followers (tag_tag_id);

alter table interest_followers add constraint fk_interest_followers_user_profile foreign key (user_profile_id) references user_profile (id) on delete restrict on update restrict;
create index ix_interest_followers_user_profile on interest_followers (user_profile_id);


# --- !Downs

alter table interest_followers drop foreign key fk_interest_followers_tag;
drop index ix_interest_followers_tag on interest_followers;

alter table interest_followers drop foreign key fk_interest_followers_user_profile;
drop index ix_interest_followers_user_profile on interest_followers;

drop table if exists channel;

drop table if exists post;

drop table if exists tag;

drop table if exists interest_followers;

drop table if exists user_profile;

