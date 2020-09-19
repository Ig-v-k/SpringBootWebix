create extension if not exists pgcrypto;

update usr set password = crypt(password, gen_salt('bf'));
-- update usr set password = encode(sha256(password::bytea), 'hex');