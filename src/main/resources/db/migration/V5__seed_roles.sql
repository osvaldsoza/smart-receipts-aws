create extension if not exists pgcrypto;

insert into roles (id, name) values
  (gen_random_uuid(), 'ROLE_VIEWER'),
  (gen_random_uuid(), 'ROLE_UPLOADER')
on conflict (name) do nothing;
