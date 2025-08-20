create table if not exists wishlist_item (
    id bigserial primary key,
    title varchar(255) not null,
    url varchar(1024)
);
