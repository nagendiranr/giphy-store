create table category (id bigint not null, name varchar(255), primary key (id));
create table giphy (id bigint not null, giphy_id varchar(255), url varchar(255), category_id bigint, primary key (id));
create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
create table role (id bigint not null, name varchar(255), primary key (id));
create table user (id bigint not null, password varchar(255), username varchar(255), primary key (id));
create table user_category (user_id bigint, category_id bigint not null, primary key (category_id));
create table user_giphy (giphy_id bigint not null, user_id bigint not null, primary key (giphy_id, user_id));
create table user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));
alter table giphy add constraint FKhj6f39322me2ubd6yv67bihb9 foreign key (category_id) references category (id);
alter table user_category add constraint FKkukst0qag2d8k8d1jlc809u0u foreign key (user_id) references user (id);
alter table user_category add constraint FKjchjxphkf5owj1i5bp95g5mfs foreign key (category_id) references category (id);
alter table user_giphy add constraint FKpe6uotnfrn1n5kty4e076kl2y foreign key (user_id) references user (id);
alter table user_giphy add constraint FKq0g7rl0lfqsong3aegebsukvu foreign key (giphy_id) references giphy (id);
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role (id);
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);