--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 9.6.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: v
--

CREATE TABLE categories (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE categories OWNER TO v;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: v
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_id_seq OWNER TO v;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: v
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: v
--

CREATE TABLE users (
    id integer NOT NULL,
    firstname character varying,
    lastname character varying,
    address character varying,
    password character varying,
    location character varying,
    avatar character varying,
    categoryid integer,
    createdon timestamp without time zone
);


ALTER TABLE users OWNER TO v;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: v
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO v;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: v
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: v
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: v
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: v
--

COPY categories (id, name) FROM stdin;
1	Clients
2	Electricians
3	Mechanics
4	House Keeping
5	Plumbers
\.


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: v
--

SELECT pg_catalog.setval('categories_id_seq', 5, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: v
--

COPY users (id, firstname, lastname, address, password, location, avatar, categoryid, createdon) FROM stdin;
10	Tom	Liam	mail.com	dsf44	Nairobi	sfdesfd	1	2018-01-12 16:09:39.465459
5	Victor	Njonge	1775@hotmail.com	67767	Kiambu	ddff	1	2018-01-12 00:21:52.336693
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: v
--

SELECT pg_catalog.setval('users_id_seq', 10, true);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: v
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: v
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

