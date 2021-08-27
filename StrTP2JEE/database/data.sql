--
-- PostgreSQL database dump
--

-- Dumped from database version 11.3
-- Dumped by pg_dump version 11.5

-- Started on 2019-10-05 15:52:54

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
-- TOC entry 2833 (class 0 OID 22820)
-- Dependencies: 197
-- Data for Name: langues; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.langues (id, langue) VALUES (1, 'français');
INSERT INTO public.langues (id, langue) VALUES (2, 'anglais');
INSERT INTO public.langues (id, langue) VALUES (3, 'espagnol');


--
-- TOC entry 2835 (class 0 OID 22831)
-- Dependencies: 199
-- Data for Name: strfiles; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2837 (class 0 OID 22842)
-- Dependencies: 201
-- Data for Name: str; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2843 (class 0 OID 0)
-- Dependencies: 196
-- Name: langues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.langues_id_seq', 3, true);


--
-- TOC entry 2844 (class 0 OID 0)
-- Dependencies: 200
-- Name: str_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.str_id_seq', 1262, true);


--
-- TOC entry 2845 (class 0 OID 0)
-- Dependencies: 198
-- Name: strfiles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.strfiles_id_seq', 35, true);


-- Completed on 2019-10-05 15:52:54

--
-- PostgreSQL database dump complete
--

