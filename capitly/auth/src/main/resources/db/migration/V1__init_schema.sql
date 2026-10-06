CREATE TABLE clearances (
    clearance_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    level SMALLINT NOT NULL UNIQUE,
    label TEXT NOT NULL UNIQUE
);

CREATE TABLE scopes (
    scope_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    label TEXT NOT NULL UNIQUE
);

CREATE TABLE users (
    user_id UUID PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL
);

CREATE TABLE roles (
    role_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    rolename TEXT NOT NULL UNIQUE,
    clearance_id SMALLINT NOT NULL,
    CONSTRAINT fk_roles_clearance
        FOREIGN KEY (clearance_id)
        REFERENCES clearances (clearance_id)
);

CREATE TABLE user_roles (
    user_id UUID NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id)
        REFERENCES roles (role_id)
        ON DELETE CASCADE
);

CREATE TABLE role_scopes (
    role_id INTEGER NOT NULL,
    scope_id INTEGER NOT NULL,
    PRIMARY KEY (role_id, scope_id),
    CONSTRAINT fk_role_scopes_role
        FOREIGN KEY (role_id)
        REFERENCES roles (role_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_role_scopes_scope
        FOREIGN KEY (scope_id)
        REFERENCES scopes (scope_id)
        ON DELETE CASCADE
);

CREATE TABLE refresh_tokens (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    token_hash TEXT NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    revoked_at TIMESTAMPTZ,
    CONSTRAINT fk_refresh_tokens_user
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE
);