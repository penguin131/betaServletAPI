--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0 (Debian 13.0-1.pgdg100+1)
-- Dumped by pg_dump version 13.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: sammy
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE postgres OWNER TO sammy;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: sammy
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: cinema; Type: SCHEMA; Schema: -; Owner: sammy
--

CREATE SCHEMA cinema;


ALTER SCHEMA cinema OWNER TO sammy;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: t_auth_info; Type: TABLE; Schema: cinema; Owner: sammy
--

CREATE TABLE cinema.t_auth_info (
    auth_info_id integer NOT NULL,
    "time" numeric,
    "user" integer NOT NULL,
    ip character varying
);


ALTER TABLE cinema.t_auth_info OWNER TO sammy;

--
-- Name: t_auth_auth_id_seq; Type: SEQUENCE; Schema: cinema; Owner: sammy
--

CREATE SEQUENCE cinema.t_auth_auth_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cinema.t_auth_auth_id_seq OWNER TO sammy;

--
-- Name: t_auth_auth_id_seq; Type: SEQUENCE OWNED BY; Schema: cinema; Owner: sammy
--

ALTER SEQUENCE cinema.t_auth_auth_id_seq OWNED BY cinema.t_auth_info.auth_info_id;


--
-- Name: t_image; Type: TABLE; Schema: cinema; Owner: sammy
--

CREATE TABLE cinema.t_image (
    image_id integer NOT NULL,
    "user" integer NOT NULL,
    size numeric,
    mime character varying(256),
    name character varying(256)
);


ALTER TABLE cinema.t_image OWNER TO sammy;

--
-- Name: t_image_image_id_seq; Type: SEQUENCE; Schema: cinema; Owner: sammy
--

CREATE SEQUENCE cinema.t_image_image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cinema.t_image_image_id_seq OWNER TO sammy;

--
-- Name: t_image_image_id_seq; Type: SEQUENCE OWNED BY; Schema: cinema; Owner: sammy
--

ALTER SEQUENCE cinema.t_image_image_id_seq OWNED BY cinema.t_image.image_id;


--
-- Name: t_user; Type: TABLE; Schema: cinema; Owner: sammy
--

CREATE TABLE cinema.t_user (
    name character varying,
    family character varying,
    email character varying,
    phone_number character varying,
    password character varying,
    user_id integer NOT NULL
);


ALTER TABLE cinema.t_user OWNER TO sammy;

--
-- Name: t_user_user_id_seq; Type: SEQUENCE; Schema: cinema; Owner: sammy
--

CREATE SEQUENCE cinema.t_user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cinema.t_user_user_id_seq OWNER TO sammy;

--
-- Name: t_user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: cinema; Owner: sammy
--

ALTER SEQUENCE cinema.t_user_user_id_seq OWNED BY cinema.t_user.user_id;


--
-- Name: t_auth_info auth_info_id; Type: DEFAULT; Schema: cinema; Owner: sammy
--

ALTER TABLE ONLY cinema.t_auth_info ALTER COLUMN auth_info_id SET DEFAULT nextval('cinema.t_auth_auth_id_seq'::regclass);


--
-- Name: t_image image_id; Type: DEFAULT; Schema: cinema; Owner: sammy
--

ALTER TABLE ONLY cinema.t_image ALTER COLUMN image_id SET DEFAULT nextval('cinema.t_image_image_id_seq'::regclass);


--
-- Name: t_user user_id; Type: DEFAULT; Schema: cinema; Owner: sammy
--

ALTER TABLE ONLY cinema.t_user ALTER COLUMN user_id SET DEFAULT nextval('cinema.t_user_user_id_seq'::regclass);


--
-- Data for Name: t_auth_info; Type: TABLE DATA; Schema: cinema; Owner: sammy
--



--
-- Data for Name: t_image; Type: TABLE DATA; Schema: cinema; Owner: sammy
--



--
-- Data for Name: t_user; Type: TABLE DATA; Schema: cinema; Owner: sammy
--



--
-- Name: t_auth_auth_id_seq; Type: SEQUENCE SET; Schema: cinema; Owner: sammy
--

SELECT pg_catalog.setval('cinema.t_auth_auth_id_seq', 104, true);


--
-- Name: t_image_image_id_seq; Type: SEQUENCE SET; Schema: cinema; Owner: sammy
--

SELECT pg_catalog.setval('cinema.t_image_image_id_seq', 37, true);


--
-- Name: t_user_user_id_seq; Type: SEQUENCE SET; Schema: cinema; Owner: sammy
--

SELECT pg_catalog.setval('cinema.t_user_user_id_seq', 11, true);


--
-- Name: t_auth_info t_auth_pk; Type: CONSTRAINT; Schema: cinema; Owner: sammy
--

ALTER TABLE ONLY cinema.t_auth_info
    ADD CONSTRAINT t_auth_pk PRIMARY KEY (auth_info_id);


--
-- Name: t_image t_image_pk; Type: CONSTRAINT; Schema: cinema; Owner: sammy
--

ALTER TABLE ONLY cinema.t_image
    ADD CONSTRAINT t_image_pk PRIMARY KEY (image_id);


--
-- Name: t_user t_user_pk; Type: CONSTRAINT; Schema: cinema; Owner: sammy
--

ALTER TABLE ONLY cinema.t_user
    ADD CONSTRAINT t_user_pk PRIMARY KEY (user_id);


--
-- Name: t_auth_auth_id_uindex; Type: INDEX; Schema: cinema; Owner: sammy
--

CREATE UNIQUE INDEX t_auth_auth_id_uindex ON cinema.t_auth_info USING btree (auth_info_id);


--
-- Name: t_image_image_id_uindex; Type: INDEX; Schema: cinema; Owner: sammy
--

CREATE UNIQUE INDEX t_image_image_id_uindex ON cinema.t_image USING btree (image_id);


--
-- Name: t_user_user_id_uindex; Type: INDEX; Schema: cinema; Owner: sammy
--

CREATE UNIQUE INDEX t_user_user_id_uindex ON cinema.t_user USING btree (user_id);


--
-- Name: t_auth_info t_auth_t_user_user_id_fk; Type: FK CONSTRAINT; Schema: cinema; Owner: sammy
--

ALTER TABLE ONLY cinema.t_auth_info
    ADD CONSTRAINT t_auth_t_user_user_id_fk FOREIGN KEY ("user") REFERENCES cinema.t_user(user_id);


--
-- Name: t_image t_image_t_user_user_id_fk; Type: FK CONSTRAINT; Schema: cinema; Owner: sammy
--

ALTER TABLE ONLY cinema.t_image
    ADD CONSTRAINT t_image_t_user_user_id_fk FOREIGN KEY ("user") REFERENCES cinema.t_user(user_id);


--
-- PostgreSQL database dump complete
--

