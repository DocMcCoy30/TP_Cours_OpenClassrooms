--
-- PostgreSQL database dump
--

-- Dumped from database version 11.3
-- Dumped by pg_dump version 11.5

-- Started on 2019-10-05 15:53:35

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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 22820)
-- Name: langues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.langues (
    id integer NOT NULL,
    langue character varying NOT NULL
);


ALTER TABLE public.langues OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 22818)
-- Name: langues_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.langues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.langues_id_seq OWNER TO postgres;

--
-- TOC entry 2838 (class 0 OID 0)
-- Dependencies: 196
-- Name: langues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.langues_id_seq OWNED BY public.langues.id;


--
-- TOC entry 201 (class 1259 OID 22842)
-- Name: str; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.str (
    id integer NOT NULL,
    index integer NOT NULL,
    "timestamp" character varying NOT NULL,
    texte1original character varying,
    texte2original character varying,
    texte1traduit character varying,
    texte2traduit character varying,
    strfiles_id integer NOT NULL
);


ALTER TABLE public.str OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 22840)
-- Name: str_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.str_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.str_id_seq OWNER TO postgres;

--
-- TOC entry 2841 (class 0 OID 0)
-- Dependencies: 200
-- Name: str_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.str_id_seq OWNED BY public.str.id;


--
-- TOC entry 199 (class 1259 OID 22831)
-- Name: strfiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.strfiles (
    id integer NOT NULL,
    titre character varying,
    langue_id integer NOT NULL,
    nom_fichier character varying
);


ALTER TABLE public.strfiles OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 22829)
-- Name: strfiles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.strfiles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.strfiles_id_seq OWNER TO postgres;

--
-- TOC entry 2844 (class 0 OID 0)
-- Dependencies: 198
-- Name: strfiles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.strfiles_id_seq OWNED BY public.strfiles.id;


--
-- TOC entry 2700 (class 2604 OID 22823)
-- Name: langues id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.langues ALTER COLUMN id SET DEFAULT nextval('public.langues_id_seq'::regclass);


--
-- TOC entry 2702 (class 2604 OID 22845)
-- Name: str id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.str ALTER COLUMN id SET DEFAULT nextval('public.str_id_seq'::regclass);


--
-- TOC entry 2701 (class 2604 OID 22834)
-- Name: strfiles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.strfiles ALTER COLUMN id SET DEFAULT nextval('public.strfiles_id_seq'::regclass);


--
-- TOC entry 2704 (class 2606 OID 22828)
-- Name: langues langues_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.langues
    ADD CONSTRAINT langues_pk PRIMARY KEY (id);


--
-- TOC entry 2708 (class 2606 OID 22850)
-- Name: str str_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.str
    ADD CONSTRAINT str_pk PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 22839)
-- Name: strfiles strfiles_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.strfiles
    ADD CONSTRAINT strfiles_pk PRIMARY KEY (id);


--
-- TOC entry 2709 (class 2606 OID 22851)
-- Name: strfiles langues_strfiles_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.strfiles
    ADD CONSTRAINT langues_strfiles_fk FOREIGN KEY (langue_id) REFERENCES public.langues(id);


--
-- TOC entry 2710 (class 2606 OID 22856)
-- Name: str strfiles_str_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.str
    ADD CONSTRAINT strfiles_str_fk FOREIGN KEY (strfiles_id) REFERENCES public.strfiles(id);


--
-- TOC entry 2837 (class 0 OID 0)
-- Dependencies: 197
-- Name: TABLE langues; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.langues TO adm_soustitres;


--
-- TOC entry 2839 (class 0 OID 0)
-- Dependencies: 196
-- Name: SEQUENCE langues_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.langues_id_seq TO adm_soustitres;


--
-- TOC entry 2840 (class 0 OID 0)
-- Dependencies: 201
-- Name: TABLE str; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.str TO adm_soustitres;


--
-- TOC entry 2842 (class 0 OID 0)
-- Dependencies: 200
-- Name: SEQUENCE str_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.str_id_seq TO adm_soustitres;


--
-- TOC entry 2843 (class 0 OID 0)
-- Dependencies: 199
-- Name: TABLE strfiles; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.strfiles TO adm_soustitres;


--
-- TOC entry 2845 (class 0 OID 0)
-- Dependencies: 198
-- Name: SEQUENCE strfiles_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.strfiles_id_seq TO adm_soustitres;


-- Completed on 2019-10-05 15:53:35

--
-- PostgreSQL database dump complete
--

