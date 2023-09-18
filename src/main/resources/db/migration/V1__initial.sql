CREATE TABLE todo
(
    id      UUID PRIMARY KEY NOT NULL,
    title       VARCHAR(255),
    description VARCHAR(255),
    completed    BOOLEAN
);