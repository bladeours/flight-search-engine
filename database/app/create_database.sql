CREATE TABLE authorities (
    id serial primary key NOT NULL,
    name varchar(45) NOT NULL
);

CREATE TABLE carts(
    id serial primary key NOT NULL
);

CREATE TABLE cart_items(
    id serial primary key NOT NULL,
    id_from_api integer NOT NULL,
    cart_id integer NOT NULL,
    amount integer default 1,
    CONSTRAINT fk_carts
        FOREIGN KEY(cart_id)
            REFERENCES carts(id)
);

CREATE TABLE users(
    id serial primary key NOT NULL,
    username varchar(45) NOT NULL,
    password varchar(500) NOT NULL,
    enabled boolean NOT NULL,
    cart_id integer NOT NULL,
    CONSTRAINT fk_carts
        FOREIGN KEY(cart_id)
            REFERENCES carts(id)
);

CREATE TABLE users_authorities(
    user_id integer NOT NULL,
    authority_id integer NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES users(id),
    CONSTRAINT fk_authority
        FOREIGN KEY(authority_id)
            REFERENCES authorities(id)
);

INSERT INTO authorities VALUES (1, 'ADMIN');
INSERT INTO authorities VALUES (2, 'USER');
INSERT INTO carts VALUES (1);
INSERT INTO users VALUES (1, 'admin', '$2a$10$rAjqRLmxqmA/kqbVxGUVVO2.r5os/IuuWeoVOCsmo9AqHzaAb6eU.', true, 1);
INSERT INTO users_authorities VALUES (1, 1);




