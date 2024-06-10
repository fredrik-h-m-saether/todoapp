-- Create table todo_tasks:
CREATE TYPE STATUS AS ENUM ('CREATED', 'DELETED', 'DONE', 'IN_PROGRESS', 'READY', 'UPDATED');

CREATE TABLE IF NOT EXISTS todo_tasks(
        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        title VARCHAR(256),
        description VARCHAR(512),
        status STATUS
);