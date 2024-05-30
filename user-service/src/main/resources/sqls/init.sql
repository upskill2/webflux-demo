--drop table if exists users cascade;
--drop table if exists user_transaction cascade;

create table if not exists users (
    id serial primary key,
    user_id UUID unique,
    name varchar(255),
    balance float
   --,primary key (id)
);

create table if not exists user_transaction (
   id serial primary key,
   user_id UUID,
   price float,
   transaction_time timestamp,
   foreign key (user_id) references users(user_id) on delete cascade
    --,primary key (id)
);