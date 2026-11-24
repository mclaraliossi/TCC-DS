-- Executar depois que a tabela usuarios existir.
-- Substitua os valores antes de utilizar.

CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO usuarios (
    nome,
    email,
    senha,
    role
)
VALUES (
    'Administrador',
    'admin@gmail.com',
    crypt(
        'admin1234567',
        gen_salt('bf', 12)
    ),
    'ADMIN'
)
ON CONFLICT (email) DO NOTHING;