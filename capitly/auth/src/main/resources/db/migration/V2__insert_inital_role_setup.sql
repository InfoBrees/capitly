INSERT INTO clearances (level, label)
VALUES
    (1, 'LOW'),
    (2, 'HIGH');

INSERT INTO scopes (label)
VALUES
    ('health.view'),
    ('portfolio.read'),
    ('portfolio.write'),
    ('portfolio.delete'),
    ('accounts.read'),
    ('accounts.write'),
    ('accounts.delete');

INSERT INTO roles (rolename, clearance_id)
SELECT 'ADMIN', clearance_id
FROM clearances
WHERE level = 2
UNION ALL
SELECT 'USER', clearance_id
FROM clearances
WHERE level = 1;


INSERT INTO role_scopes (role_id, scope_id)
SELECT r.role_id, s.scope_id
FROM roles AS r
JOIN scopes AS s ON s.label IN (
    'health.view',
    'portfolio.read',
    'portfolio.write',
    'portfolio.delete',
    'accounts.read',
    'accounts.write',
    'accounts.delete'
)
WHERE r.rolename = 'ADMIN';

INSERT INTO role_scopes (role_id, scope_id)
SELECT r.role_id, s.scope_id
FROM roles AS r
JOIN scopes AS s ON s.label IN (
    'portfolio.read',
    'portfolio.write',
    'portfolio.delete',
    'accounts.read',
    'accounts.write',
    'accounts.delete'
)
WHERE r.rolename = 'USER';
