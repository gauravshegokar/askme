# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table channel (
  channel_id                    integer auto_increment not null,
  channel_name                  varchar(255),
  channel_owner_id              integer,
  channel_description           varchar(255),
  date_created                  datetime,
  constraint pk_channel primary key (channel_id)
);

create table channel_user_profile (
  channel_channel_id            integer not null,
  user_profile_id               integer not null,
  constraint pk_channel_user_profile primary key (channel_channel_id,user_profile_id)
);

create table channel_tag (
  channel_channel_id            integer not null,
  tag_tag_id                    integer not null,
  constraint pk_channel_tag primary key (channel_channel_id,tag_tag_id)
);

create table comment (
  comment_id                    bigint auto_increment not null,
  comment_text                  varchar(255),
  date                          datetime(6),
  user_id                       integer,
  post_post_id                  integer,
  constraint pk_comment primary key (comment_id)
);

create table post (
  post_id                       integer auto_increment not null,
  date_created                  datetime,
  author                        integer,
  is_profane                    tinyint(1) default 0,
  channel_channel_id            integer,
  text                          varchar(255),
  constraint pk_post primary key (post_id)
);

create table post_tag_mapper (
  post_post_id                  integer not null,
  tag_tag_id                    integer not null,
  constraint pk_post_tag_mapper primary key (post_post_id,tag_tag_id)
);

create table tag (
  tag_id                        integer auto_increment not null,
  tag_name                      varchar(255),
  constraint pk_tag primary key (tag_id)
);

create table tag_post (
  tag_tag_id                    integer not null,
  post_post_id                  integer not null,
  constraint pk_tag_post primary key (tag_tag_id,post_post_id)
);

create table interest_followers (
  tag_tag_id                    integer not null,
  user_profile_id               integer not null,
  constraint pk_interest_followers primary key (tag_tag_id,user_profile_id)
);

create table user_profile (
  id                            integer auto_increment not null,
  date_created                  datetime,
  username                      varchar(255),
  password                      varchar(255),
  fname                         varchar(255),
  lname                         varchar(255),
  access_level                  varchar(255),
  monthly_subscription_price    double,
  constraint pk_user_profile primary key (id)
);

alter table channel add constraint fk_channel_channel_owner_id foreign key (channel_owner_id) references user_profile (id) on delete restrict on update restrict;
create index ix_channel_channel_owner_id on channel (channel_owner_id);

alter table channel_user_profile add constraint fk_channel_user_profile_channel foreign key (channel_channel_id) references channel (channel_id) on delete restrict on update restrict;
create index ix_channel_user_profile_channel on channel_user_profile (channel_channel_id);

alter table channel_user_profile add constraint fk_channel_user_profile_user_profile foreign key (user_profile_id) references user_profile (id) on delete restrict on update restrict;
create index ix_channel_user_profile_user_profile on channel_user_profile (user_profile_id);

alter table channel_tag add constraint fk_channel_tag_channel foreign key (channel_channel_id) references channel (channel_id) on delete restrict on update restrict;
create index ix_channel_tag_channel on channel_tag (channel_channel_id);

alter table channel_tag add constraint fk_channel_tag_tag foreign key (tag_tag_id) references tag (tag_id) on delete restrict on update restrict;
create index ix_channel_tag_tag on channel_tag (tag_tag_id);

alter table comment add constraint fk_comment_user_id foreign key (user_id) references user_profile (id) on delete restrict on update restrict;
create index ix_comment_user_id on comment (user_id);

alter table comment add constraint fk_comment_post_post_id foreign key (post_post_id) references post (post_id) on delete restrict on update restrict;
create index ix_comment_post_post_id on comment (post_post_id);

alter table post add constraint fk_post_author foreign key (author) references user_profile (id) on delete restrict on update restrict;
create index ix_post_author on post (author);

alter table post add constraint fk_post_channel_channel_id foreign key (channel_channel_id) references channel (channel_id) on delete restrict on update restrict;
create index ix_post_channel_channel_id on post (channel_channel_id);

alter table post_tag_mapper add constraint fk_post_tag_mapper_post foreign key (post_post_id) references post (post_id) on delete restrict on update restrict;
create index ix_post_tag_mapper_post on post_tag_mapper (post_post_id);

alter table post_tag_mapper add constraint fk_post_tag_mapper_tag foreign key (tag_tag_id) references tag (tag_id) on delete restrict on update restrict;
create index ix_post_tag_mapper_tag on post_tag_mapper (tag_tag_id);

alter table tag_post add constraint fk_tag_post_tag foreign key (tag_tag_id) references tag (tag_id) on delete restrict on update restrict;
create index ix_tag_post_tag on tag_post (tag_tag_id);

alter table tag_post add constraint fk_tag_post_post foreign key (post_post_id) references post (post_id) on delete restrict on update restrict;
create index ix_tag_post_post on tag_post (post_post_id);

alter table interest_followers add constraint fk_interest_followers_tag foreign key (tag_tag_id) references tag (tag_id) on delete restrict on update restrict;
create index ix_interest_followers_tag on interest_followers (tag_tag_id);

alter table interest_followers add constraint fk_interest_followers_user_profile foreign key (user_profile_id) references user_profile (id) on delete restrict on update restrict;
create index ix_interest_followers_user_profile on interest_followers (user_profile_id);


# --- !Downs

alter table channel drop foreign key fk_channel_channel_owner_id;
drop index ix_channel_channel_owner_id on channel;

alter table channel_user_profile drop foreign key fk_channel_user_profile_channel;
drop index ix_channel_user_profile_channel on channel_user_profile;

alter table channel_user_profile drop foreign key fk_channel_user_profile_user_profile;
drop index ix_channel_user_profile_user_profile on channel_user_profile;

alter table channel_tag drop foreign key fk_channel_tag_channel;
drop index ix_channel_tag_channel on channel_tag;

alter table channel_tag drop foreign key fk_channel_tag_tag;
drop index ix_channel_tag_tag on channel_tag;

alter table comment drop foreign key fk_comment_user_id;
drop index ix_comment_user_id on comment;

alter table comment drop foreign key fk_comment_post_post_id;
drop index ix_comment_post_post_id on comment;

alter table post drop foreign key fk_post_author;
drop index ix_post_author on post;

alter table post drop foreign key fk_post_channel_channel_id;
drop index ix_post_channel_channel_id on post;

alter table post_tag_mapper drop foreign key fk_post_tag_mapper_post;
drop index ix_post_tag_mapper_post on post_tag_mapper;

alter table post_tag_mapper drop foreign key fk_post_tag_mapper_tag;
drop index ix_post_tag_mapper_tag on post_tag_mapper;

alter table tag_post drop foreign key fk_tag_post_tag;
drop index ix_tag_post_tag on tag_post;

alter table tag_post drop foreign key fk_tag_post_post;
drop index ix_tag_post_post on tag_post;

alter table interest_followers drop foreign key fk_interest_followers_tag;
drop index ix_interest_followers_tag on interest_followers;

alter table interest_followers drop foreign key fk_interest_followers_user_profile;
drop index ix_interest_followers_user_profile on interest_followers;

drop table if exists channel;

drop table if exists channel_user_profile;

drop table if exists channel_tag;

drop table if exists comment;

drop table if exists post;

drop table if exists post_tag_mapper;

drop table if exists tag;

drop table if exists tag_post;

drop table if exists interest_followers;

drop table if exists user_profile;

