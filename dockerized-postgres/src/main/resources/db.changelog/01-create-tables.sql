--liquibase formatted sql
--changeset adarsh:create-otp-and-post-table
CREATE TABLE IF NOT EXISTS public.product (
      id serial4 NOT NULL,
      name text NOT NULL UNIQUE,
      CONSTRAINT product_pk PRIMARY KEY (id)
);
