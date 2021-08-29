CREATE TABLE public."person"
(
    id   serial NOT NULL,
    name character varying,
    PRIMARY KEY (id)
);
CREATE TABLE public.users
(
    id serial NOT NULL,
    username character varying,
    first_name character varying,
    last_name character varying,
    email character varying,
    password character varying,
    enabled boolean,
    PRIMARY KEY (id)
);

CREATE TABLE public.roles
(
    id serial NOT NULL,
    name character varying,
    PRIMARY KEY (id)
);

CREATE TABLE public.users_roles
(
    id serial NOT NULL,
    user_id integer,
    role_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE public.word
(
    id serial NOT NULL,
    name character varying,
    translation character varying,
    PRIMARY KEY (id)
);

CREATE TABLE public.example
(
    id serial NOT NULL,
    text character varying,
    PRIMARY KEY (id)
);

CREATE TABLE public.example_words
(
    id serial NOT NULL,
    example_id integer,
    word_id integer,
    PRIMARY KEY (id)
);

ALTER TABLE public.users_roles
    ADD CONSTRAINT users_roles_users_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;

ALTER TABLE public.users_roles
    ADD CONSTRAINT users_roles_roles_fk FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;


ALTER TABLE public.example_words
    ADD CONSTRAINT example_words_examples_fk FOREIGN KEY (example_id)
        REFERENCES public.example (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;

ALTER TABLE public.example_words
    ADD CONSTRAINT example_words_words_fk FOREIGN KEY (word_id)
        REFERENCES public.word (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;
