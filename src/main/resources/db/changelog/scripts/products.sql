CREATE TABLE IF NOT EXISTS products
(
    name character varying(255) NOT NULL,
    description character varying(4096),
    in_sight boolean DEFAULT false,
    price double precision DEFAULT 0.0 CHECK(price >= 0.0),
    CONSTRAINT products_pkey PRIMARY KEY (name)
)