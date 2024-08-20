-- DROP TABLE IF EXISTS USER;
CREATE TABLE goods
(
    goods_id      INTEGER      NOT NULL AUTO_INCREMENT,
    brand_id      INTEGER      NOT NULL,
    category_name VARCHAR(255) NOT NULL,
    price         INTEGER      NOT NULL,
    PRIMARY KEY (goods_id)
);

CREATE TABLE brand
(
    brand_id   INTEGER      NOT NULL AUTO_INCREMENT,
    brand_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (brand_id)
);