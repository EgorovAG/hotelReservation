# drop database hotelreserv;
# create database hotelreservTest;

create table IF NOT EXISTS authUser
(
    id       int(64) auto_increment PRIMARY KEY,
    login    varchar(50) not null,
    password varchar(50) not null,
#     role     enum ('ADMIN', 'USER') default 'USER' not null
    role     varchar(50) not null
#     constraint authUser_login_unique unique (login)
);

insert into authUser (login, password, role)
VALUES ('admin', 'admin', 'ADMIN');
#        ('user', 'user', 'USER');
# ----------------------------------------


create table IF NOT EXISTS client
(
    id         int(64) auto_increment PRIMARY KEY,
    firstName  varchar(50)  not null,
    secondName varchar(50)  not null,
    email      varchar(100) not null,
    phone      varchar(50)  not null,
    user_id    int(64)      not null,
    constraint client_authUser_id_FK foreign key (user_id) references authUser (id)
);

# insert into client (firstName, secondName, email, phone, user_id)
# VALUES ('User', 'User', 'user@google.com', '55555', 2);
# ----------------------------------------


create table IF NOT EXISTS room
(
    id         int auto_increment PRIMARY KEY,
    numOfSeats int         not null,
#     classOfAp  enum ('ECONOM', 'STANDART', 'BUSINESS') not null,
    classOfAp  varchar(50) not null,
    price      int         not null
);

insert into room(numOfSeats, classOfAp, price)
values (1, 'ECONOM', 100),
       (1, 'STANDART', 200),
       (1, 'BUSINESS', 300),
       (2, 'ECONOM', 200),
       (2, 'STANDART', 300),
       (2, 'BUSINESS', 400),
       (3, 'ECONOM', 300),
       (3, 'STANDART', 400),
       (3, 'BUSINESS', 500);
# ----------------------------------------


create table IF NOT EXISTS blackList
(
    id         int(64) auto_increment PRIMARY KEY,
    user_id    int(64) not null,
    date_block DATE    not null,
    constraint blackList_authUser_id_FK FOREIGN KEY (user_id) REFERENCES authUser (id)
);

# insert into blackList(user_id, date_block)
# values ('2', '2020-03-05');
# ----------------------------------------


create table IF NOT EXISTS orderClient
(
    id         int(64) auto_increment PRIMARY KEY,
    startDate  varchar(50) not null,
    endDate    varchar(50) not null,
    room_id    int         not null,
    client_id  int(64)     not null,
#     conditions enum ('CONSIDERATION','APPROVED','REJECTED','PAID') default 'CONSIDERATION' not null,
    conditions varchar(50) not null,
    constraint orderClient_client_id_fk foreign key (client_id) references client (user_id),
    constraint orderClient_room_id_fk foreign key (room_id) references room (id)
);

# insert into orderclient(startDate, endDate, room_id, client_id, conditions)
# values ('2020-10-05', '2020-10-07', '3', '2', 'CONSIDERATION')

# select client_id, firstName, secondName, email, phone, startDate, endDate, conditions from client join orderClient oC on client.user_id = oC.client_id
# update orderclient set conditions='APPROVED' where id= 6
#
# select oC.id, firstName, secondName, email, phone, client_id, startDate, endDate, conditions from client join orderClient oC on client.user_id = oC.client_id
#
# delete from orderclient where client_id=4

# ----------------------------------------
# ----------------------------------------
# ----------------------------------------
# ----------------------------------------
# ----------------------------------------
#
# insert into conditionOrder(conditionOrd)
# values ('CONSIDERATION'),
#        ('ApprovedAwaitingPayment'),
#        ('REJECTED'),
#        ('PAID'),
#        ('CanceledClient');
#
#
# select * from authuser join client on id = client_id where login='qwe'
# select * from room where room_id=9
# select * from authuser join client on authuser.id = client.client_id
#
# select order_id, firstName, secondName, email, client.client_id, startDate, endDate, conditionOrd from client
#      join orderClient oC on client.client_id = oC.client_id
#  join conditionOrder cO on oC.cond_id = cO.cond_id
#
# select * from orderclient where client_id =2
#
# select order_id from orderClient
#
# select order_id, firstName, secondName, email, client.client_id, startDate,
#     endDate, conditionOrd from client join orderClient oC on client.client_id = oC.client_id join conditionOrder cO on oC.cond_id = cO.cond_id
#
# update orderClient set cond_id=1 where order_id=1
#
# drop table client
#
# insert into authuser(id, login, password, role) VALUES (10,'asd','asd','asd')
#
# insert into client(firstName,secondName,email,phone,client_id) values ('sad','sd','asd','asd',10);
#
# delete authuser FROM authuser WHERE id=10
#
# insert into authuser(id, login, password, role) VALUES (10,'asd','asd','asd')
#
# insert into client(firstName,secondName,email,phone,client_id) values ('sad','sd','asd','asd',10);
#
# insert into orderclient(startDate, endDate, room_id, client_id, cond_id) VALUES ('123123','231',1,10,1);
# delete orderclient FROM orderclient WHERE client_id=10
#
# delete FROM client where client_id =3
# delete FROM authUser where id=3

# select client_id, firstName, secondName, email, phone, startDate, endDate, conditions from client join orderClient oC on client.user_id = oC.client_id

# select blackList.id, blackList.user_id, date_block, firstName, secondName
# from blacklist
#          join client c on blackList.user_id = c.user_id
#
# select authuser.id, login, password, firstname, secondname, email, phone
# from authuser
#          join client on authuser.id = client.user_id
#
#
# delete
# from authuser
# where password = 'user';
#
# select count(*) as count
# from authuser
# where id = 10;
#
#
# select count(*) as count
# from client
# where id = 5
#
# select *
# from authuser
#          join client on authuser.id = client.user_id
# where login = 'qwe'
#
# select *
# from room
# where id = 1
#
# select price from room join orderClient oC on room.id = oC.room_id where oC.id = 1

# insert into orderclient(startDate, endDate, room_id, client_id, conditions) values ('2020-10-05','2020-10-07','2','4','CONSIDERATION')
#
#
# select oc.id, startDate, endDate,numOfSeats, classOfAp, price from orderclient as oc join room r on oc.room_id = r.id where 2
# select oc.id, startDate, endDate,numOfSeats, classOfAp, price from orderclient as oc join room r on oc.room_id = r.id where oc.id
# select oc.id from orderclient as oc join room r on oc.room_id = r.id where oc.client_id=2
# select id from orderclient where client_id
#
# select client_id from orderclient where id=9
# select login from authuser where login = 'we'
# select blackList.id, blackList.user_id, date_block, firstName, secondName from blacklist join client c on blackList.user_id = c.user_id


select * from authuser join client on authuser.id = client.user_id where login = 'admin'