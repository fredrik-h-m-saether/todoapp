CREATE TABLE IF NOT EXISTS messages (
    id       VARCHAR(60)  PRIMARY KEY,
    text     VARCHAR      NOT NULL
);

CREATE TABLE IF NOT EXISTS todos_v01 (
    id       VARCHAR(60)  PRIMARY KEY,
    title     VARCHAR      NOT NULL,
    description     VARCHAR      NOT NULL,
    finished NUMBER NOT NULL
);