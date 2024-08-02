CREATE TABLE IF NOT EXISTS toilets
(
    id      BIGINT NOT NULL,
    name    VARCHAR(255),
    address VARCHAR(255),
    CONSTRAINT pk_toilets PRIMARY KEY (id)
);

