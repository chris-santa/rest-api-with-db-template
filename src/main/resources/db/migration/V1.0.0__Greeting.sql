CREATE TABLE IF NOT EXISTS GREETING (
    id serial primary key,
    text character varying(50),
    greetingTime timestamp without time zone
);
