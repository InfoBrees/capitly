CREATE TABLE clearance (
    level INTEGER PRIMARY KEY,
    label TEXT NOT NULL UNIQUE
);

CREATE TABLE scope (
    id UUID PRIMARY KEY,
    label TEXT NOT NULL UNIQUE
);

CREATE TABLE app_user (
    user_id UUID PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL
);

CREATE TABLE role (
    role_id UUID PRIMARY KEY,
    rolename TEXT NOT NULL UNIQUE,
    clearance_id INTEGER NOT NULL,
    CONSTRAINT fk_role_clearance
        FOREIGN KEY (clearance_id)
        REFERENCES clearance (level)
);

CREATE TABLE user_role (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_role_user
        FOREIGN KEY (user_id)
        REFERENCES app_user (user_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_user_role_role
        FOREIGN KEY (role_id)
        REFERENCES role (role_id)
        ON DELETE CASCADE
);

CREATE TABLE role_scope (
    role_id UUID NOT NULL,
    scope_id UUID NOT NULL,
    PRIMARY KEY (role_id, scope_id),
    CONSTRAINT fk_role_scope_role
        FOREIGN KEY (role_id)
        REFERENCES role (role_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_role_scope_scope
        FOREIGN KEY (scope_id)
        REFERENCES scope (id)
        ON DELETE CASCADE
);

CREATE TABLE refresh_token (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    token_hash TEXT NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    revoked_at TIMESTAMPTZ,
    CONSTRAINT fk_refresh_token_user
        FOREIGN KEY (user_id)
        REFERENCES app_user (user_id)
        ON DELETE CASCADE
);