DDL
```mysql
create table users
(
    username varchar(50) not null primary key,
    password varchar(256) not null,
    enabled  boolean     not null
);

create table authorities
(
    username  varchar(50) not null,
    authority varchar(256) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

create table persistent_logins
(
    series    varchar(64)                         not null
        primary key,
    username  varchar(50)                         not null,
    token     varchar(64)                         not null,
    last_used timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint persistent_logins_users_username_fk
        foreign key (username) references users (username)
);
```

user/password
```mysql
INSERT INTO `test-db`.users (username, password, enabled) VALUES ('user', '$2a$10$bsFVJ76zvQZipRmKtkkHF.FXX3K59r4QpuvsZpqs3Hjt5DEfT5jIS', 1);
```
Bcrypt each time generates different password...
