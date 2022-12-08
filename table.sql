create table users(
	id int primary key auto_increment,
    last_name varchar(100) ,
    first_name  varchar(100),
    email varchar(500),
    password varchar(500)
);
create table categories(
	id int primary key auto_increment,
    category_name varchar(100)
);
create table products(
	id int primary key auto_increment,
    product_name text,
    desciption text,
    prices double,
    category_id int,
    image text
);
create table reviews(
	id int primary key auto_increment,
    users_id int,
    product_id int,
    contents text,
    starts int
);
create table orders(
	id int primary key auto_increment,
    user_id int,
    issue_date date
);
create table order_detail(
	product_id int,
    order_id int,
    quantity int,
    constraint order_product_pk primary key(product_id, order_id)
);
create table rule_user(
	id int primary key auto_increment,
    user_id int,
    rule_name varchar(100)
);
create table digital_signature(
    user_id int ,
    order_id int,
    hash_string text,
    constraint ds_pk primary key(user_id, order_id)
);
create table key_user(
	id int primary key auto_increment,
    user_id int,
    public_key text,
    active int
);
ALTER TABLE products ADD FOREIGN KEY (category_id) REFERENCES categories(id);
ALTER TABLE reviews ADD FOREIGN KEY (users_id) REFERENCES users(id);
ALTER TABLE reviews ADD FOREIGN KEY (product_id) REFERENCES products(id);
ALTER TABLE orders ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE order_detail ADD FOREIGN KEY (product_id) REFERENCES products(id);
ALTER TABLE order_detail ADD FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE rule_user ADD FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE digital_signature ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE digital_signature ADD FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE key_user ADD FOREIGN KEY (user_id) REFERENCES users(id);

