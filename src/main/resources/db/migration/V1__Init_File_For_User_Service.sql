CREATE TABLE address
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    updated_at      datetime NULL,
    deleted         BIT(1) NOT NULL,
    street          VARCHAR(255) NULL,
    city            VARCHAR(255) NULL,
    number          INT    NOT NULL,
    zipcode         VARCHAR(255) NULL,
    geo_location_id BIGINT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE geo_location
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    lat        VARCHAR(255) NULL,
    lng        VARCHAR(255) NULL,
    CONSTRAINT pk_geolocation PRIMARY KEY (id)
);

CREATE TABLE name
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    CONSTRAINT pk_name PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    role_name  VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE token
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    value      VARCHAR(255) NULL,
    user_id    BIGINT NULL,
    expiry_at  datetime NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    email      VARCHAR(255) NULL,
    username   VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    name_id    BIGINT NULL,
    address_id BIGINT NULL,
    phone      VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_GEO_LOCATION FOREIGN KEY (geo_location_id) REFERENCES geo_location (id);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_NAME FOREIGN KEY (name_id) REFERENCES name (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);