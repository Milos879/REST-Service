PGDMP     0    0                z            bookshop    14.4    14.4 ;    6           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            7           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            8           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            9           1262    17263    bookshop    DATABASE     m   CREATE DATABASE bookshop WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United Kingdom.1252';
    DROP DATABASE bookshop;
                postgres    false            J           1247    17302    role2    TYPE     E   CREATE TYPE public.role2 AS ENUM (
    'customer',
    'employee'
);
    DROP TYPE public.role2;
       public          postgres    false            ?            1259    17465    active_user    TABLE     ?   CREATE TABLE public.active_user (
    active_user_id integer NOT NULL,
    user_id integer NOT NULL,
    jwt_token text NOT NULL
);
    DROP TABLE public.active_user;
       public         heap    postgres    false            ?            1259    17464    active_user_active_user_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.active_user_active_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.active_user_active_user_id_seq;
       public          postgres    false    220            :           0    0    active_user_active_user_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.active_user_active_user_id_seq OWNED BY public.active_user.active_user_id;
          public          postgres    false    219            ?            1259    17265    author    TABLE     ?   CREATE TABLE public.author (
    author_id integer NOT NULL,
    first_name character varying(15) NOT NULL,
    last_name character varying(15) NOT NULL
);
    DROP TABLE public.author;
       public         heap    postgres    false            ?            1259    17264    author_author_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.author_author_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.author_author_id_seq;
       public          postgres    false    210            ;           0    0    author_author_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.author_author_id_seq OWNED BY public.author.author_id;
          public          postgres    false    209            ?            1259    17281    book    TABLE     C  CREATE TABLE public.book (
    book_id integer NOT NULL,
    title character varying(100) NOT NULL,
    isbn character varying(255) NOT NULL,
    published_date date NOT NULL,
    author_id integer NOT NULL,
    genre_id integer NOT NULL,
    CONSTRAINT book_published_date_check CHECK ((published_date < CURRENT_DATE))
);
    DROP TABLE public.book;
       public         heap    postgres    false            ?            1259    17280    book_book_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.book_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.book_book_id_seq;
       public          postgres    false    214            <           0    0    book_book_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.book_book_id_seq OWNED BY public.book.book_id;
          public          postgres    false    213            ?            1259    17272    genre    TABLE     f   CREATE TABLE public.genre (
    genre_id integer NOT NULL,
    name character varying(20) NOT NULL
);
    DROP TABLE public.genre;
       public         heap    postgres    false            ?            1259    17271    genre_genre_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.genre_genre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.genre_genre_id_seq;
       public          postgres    false    212            =           0    0    genre_genre_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.genre_genre_id_seq OWNED BY public.genre.genre_id;
          public          postgres    false    211            ?            1259    17327    orderr    TABLE     ?   CREATE TABLE public.orderr (
    order_id integer NOT NULL,
    user_id integer NOT NULL,
    book_id integer NOT NULL,
    order_at timestamp with time zone NOT NULL
);
    DROP TABLE public.orderr;
       public         heap    postgres    false            ?            1259    17326    order_order_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.order_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.order_order_id_seq;
       public          postgres    false    218            >           0    0    order_order_id_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE public.order_order_id_seq OWNED BY public.orderr.order_id;
          public          postgres    false    217            ?            1259    17308    userr    TABLE     ?  CREATE TABLE public.userr (
    user_id integer NOT NULL,
    first_name character varying(24) NOT NULL,
    last_name character varying(24) NOT NULL,
    date_of_birth date NOT NULL,
    address character varying(35) NOT NULL,
    email character varying(35),
    role public.role2 NOT NULL,
    username character varying(35) NOT NULL,
    password character varying(68) NOT NULL
);
    DROP TABLE public.userr;
       public         heap    postgres    false    842            ?            1259    17307    user_user_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.user_user_id_seq;
       public          postgres    false    216            ?           0    0    user_user_id_seq    SEQUENCE OWNED BY     F   ALTER SEQUENCE public.user_user_id_seq OWNED BY public.userr.user_id;
          public          postgres    false    215            ~           2604    17468    active_user active_user_id    DEFAULT     ?   ALTER TABLE ONLY public.active_user ALTER COLUMN active_user_id SET DEFAULT nextval('public.active_user_active_user_id_seq'::regclass);
 I   ALTER TABLE public.active_user ALTER COLUMN active_user_id DROP DEFAULT;
       public          postgres    false    220    219    220            x           2604    17268    author author_id    DEFAULT     t   ALTER TABLE ONLY public.author ALTER COLUMN author_id SET DEFAULT nextval('public.author_author_id_seq'::regclass);
 ?   ALTER TABLE public.author ALTER COLUMN author_id DROP DEFAULT;
       public          postgres    false    210    209    210            z           2604    17284    book book_id    DEFAULT     l   ALTER TABLE ONLY public.book ALTER COLUMN book_id SET DEFAULT nextval('public.book_book_id_seq'::regclass);
 ;   ALTER TABLE public.book ALTER COLUMN book_id DROP DEFAULT;
       public          postgres    false    214    213    214            y           2604    17275    genre genre_id    DEFAULT     p   ALTER TABLE ONLY public.genre ALTER COLUMN genre_id SET DEFAULT nextval('public.genre_genre_id_seq'::regclass);
 =   ALTER TABLE public.genre ALTER COLUMN genre_id DROP DEFAULT;
       public          postgres    false    211    212    212            }           2604    17330    orderr order_id    DEFAULT     q   ALTER TABLE ONLY public.orderr ALTER COLUMN order_id SET DEFAULT nextval('public.order_order_id_seq'::regclass);
 >   ALTER TABLE public.orderr ALTER COLUMN order_id DROP DEFAULT;
       public          postgres    false    218    217    218            |           2604    17311    userr user_id    DEFAULT     m   ALTER TABLE ONLY public.userr ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);
 <   ALTER TABLE public.userr ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    216    215    216            3          0    17465    active_user 
   TABLE DATA           I   COPY public.active_user (active_user_id, user_id, jwt_token) FROM stdin;
    public          postgres    false    220   ?C       )          0    17265    author 
   TABLE DATA           B   COPY public.author (author_id, first_name, last_name) FROM stdin;
    public          postgres    false    210   ?D       -          0    17281    book 
   TABLE DATA           Y   COPY public.book (book_id, title, isbn, published_date, author_id, genre_id) FROM stdin;
    public          postgres    false    214   5F       +          0    17272    genre 
   TABLE DATA           /   COPY public.genre (genre_id, name) FROM stdin;
    public          postgres    false    212   ?H       1          0    17327    orderr 
   TABLE DATA           F   COPY public.orderr (order_id, user_id, book_id, order_at) FROM stdin;
    public          postgres    false    218   ?H       /          0    17308    userr 
   TABLE DATA           x   COPY public.userr (user_id, first_name, last_name, date_of_birth, address, email, role, username, password) FROM stdin;
    public          postgres    false    216   5I       @           0    0    active_user_active_user_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.active_user_active_user_id_seq', 49, true);
          public          postgres    false    219            A           0    0    author_author_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.author_author_id_seq', 1, false);
          public          postgres    false    209            B           0    0    book_book_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.book_book_id_seq', 1, false);
          public          postgres    false    213            C           0    0    genre_genre_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.genre_genre_id_seq', 1, false);
          public          postgres    false    211            D           0    0    order_order_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.order_order_id_seq', 11, true);
          public          postgres    false    217            E           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 21, true);
          public          postgres    false    215            ?           2606    17476 %   active_user active_user_jwt_token_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.active_user
    ADD CONSTRAINT active_user_jwt_token_key UNIQUE (jwt_token);
 O   ALTER TABLE ONLY public.active_user DROP CONSTRAINT active_user_jwt_token_key;
       public            postgres    false    220            ?           2606    17472    active_user active_user_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.active_user
    ADD CONSTRAINT active_user_pkey PRIMARY KEY (active_user_id);
 F   ALTER TABLE ONLY public.active_user DROP CONSTRAINT active_user_pkey;
       public            postgres    false    220            ?           2606    17474 #   active_user active_user_user_id_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.active_user
    ADD CONSTRAINT active_user_user_id_key UNIQUE (user_id);
 M   ALTER TABLE ONLY public.active_user DROP CONSTRAINT active_user_user_id_key;
       public            postgres    false    220            ?           2606    17270    author author_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (author_id);
 <   ALTER TABLE ONLY public.author DROP CONSTRAINT author_pkey;
       public            postgres    false    210            ?           2606    17350    book book_isbn_key 
   CONSTRAINT     M   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_isbn_key UNIQUE (isbn);
 <   ALTER TABLE ONLY public.book DROP CONSTRAINT book_isbn_key;
       public            postgres    false    214            ?           2606    17288    book book_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public            postgres    false    214            ?           2606    17279    genre genre_name_key 
   CONSTRAINT     O   ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_name_key UNIQUE (name);
 >   ALTER TABLE ONLY public.genre DROP CONSTRAINT genre_name_key;
       public            postgres    false    212            ?           2606    17277    genre genre_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (genre_id);
 :   ALTER TABLE ONLY public.genre DROP CONSTRAINT genre_pkey;
       public            postgres    false    212            ?           2606    17332    orderr order_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.orderr
    ADD CONSTRAINT order_pkey PRIMARY KEY (order_id);
 ;   ALTER TABLE ONLY public.orderr DROP CONSTRAINT order_pkey;
       public            postgres    false    218            ?           2606    17490    userr unique_email 
   CONSTRAINT     N   ALTER TABLE ONLY public.userr
    ADD CONSTRAINT unique_email UNIQUE (email);
 <   ALTER TABLE ONLY public.userr DROP CONSTRAINT unique_email;
       public            postgres    false    216            ?           2606    17488    userr unique_username 
   CONSTRAINT     T   ALTER TABLE ONLY public.userr
    ADD CONSTRAINT unique_username UNIQUE (username);
 ?   ALTER TABLE ONLY public.userr DROP CONSTRAINT unique_username;
       public            postgres    false    216            ?           2606    17313    userr user_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.userr
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 9   ALTER TABLE ONLY public.userr DROP CONSTRAINT user_pkey;
       public            postgres    false    216            ?           2606    17477 $   active_user active_user_user_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.active_user
    ADD CONSTRAINT active_user_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.userr(user_id);
 N   ALTER TABLE ONLY public.active_user DROP CONSTRAINT active_user_user_id_fkey;
       public          postgres    false    220    3214    216            ?           2606    17316    book book_author_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.author(author_id) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.book DROP CONSTRAINT book_author_id_fkey;
       public          postgres    false    214    210    3200            ?           2606    17321    book book_genre_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genre(genre_id) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.book DROP CONSTRAINT book_genre_id_fkey;
       public          postgres    false    214    212    3204            ?           2606    17361    orderr order_book_id_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY public.orderr
    ADD CONSTRAINT order_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.book(book_id);
 C   ALTER TABLE ONLY public.orderr DROP CONSTRAINT order_book_id_fkey;
       public          postgres    false    3208    218    214            ?           2606    17333    orderr order_user_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.orderr
    ADD CONSTRAINT order_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.userr(user_id) ON DELETE CASCADE;
 C   ALTER TABLE ONLY public.orderr DROP CONSTRAINT order_user_id_fkey;
       public          postgres    false    218    216    3214            ?           2606    17482    userr user_username_key    FK CONSTRAINT     ?   ALTER TABLE ONLY public.userr
    ADD CONSTRAINT user_username_key FOREIGN KEY (user_id) REFERENCES public.userr(user_id) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.userr DROP CONSTRAINT user_username_key;
       public          postgres    false    216    216    3214            3   G  x???Ks?0?5?*??a%???F@KxC?	??7v?E7uugΜ3?ݹ*C?q?O6)?O??/????????ME?????!#vM?O)}w?.??3ġ?U??!:F:r-????Fc?3X?#.R?\?!q?{%??诊Y???i?W?a???;??fXl??f[? ??|)?em??&??b??N)?T?2z????2Ą?????gѠXkx??,???v??=????Y?????y? ?$????%?MI/{???_?^U??_?Q??_?ZC??TL?????{??6?5Aje??m?????j?E??
??,??CF?x      )   /  x?%?[k?0???3?4?=??????
{Qc???6?΂????Iptt?I^??K??Vpd?
??p?)|9i]p롉?kx?1??D)??	gp?6??Yp?N\?>?韑??{q9ǀKX??s&8??aIp?i6?@p??j?6RH?????zBc??
NH?h*8???t?(??Q?TQ:????_?G????CF3Ӊ;+cy?A??5??3?^?@????H'?>q?p??/I'4?MhVp??e?EѷM??9?.0li?vT???$???_?tI??R???Z?ܢ??U???G?}?_~? ?/????      -   ;  x?MS?n1<s?BG?
?z?V7Z 8hr?E????U?]????c?c?)????9?E?K?v?~?m???Z#? ???BI?!X?2???b?#???v??l@26R??Ё?D?hX=֜???3DM TF≙?F69?S?Ţ??b?ʔ6\$??z/???K?`?7Vm?+-׵ё??O?,X???;?vC??????????1?V?????˲????[A'q????&????"n?Y?@?!?q?M?կRע<	6N????M?b??O???.]??~???h?:s01??.??g?Bd.Tp/?y?8?$??/?K??s!y{4???D??&?Uח?-}?O?(C?????8\N$?*?????X'?w?F<????5??^??w???n8?EM??]??W*b?????w???ڍe?????;^???UW?\̾???TX??!(?1?`?W???.?S7|	_6iJ?@p???^̎?,vIL?????@?">??a3e??:??ij?"?q,???2?
裱J[}^?K???-^?AܵE\?׼w????+???(???G$??X????i???=      +   S   x??1
?0?9?0Bt???'pv	m??i!X????Sr1?J??a?n????"??MZV̴????
^(v???@??Wqm8& ???      1   B   x?Uʱ?0E?ڞ?%???@<?ρ(???qY?BhDn]????.?c??[j???Ƿ???/8Y?      /   ?  x???ˎ?@E??Wd???1`?"l0lce?n?м?63_?v??4????R?????s`5&???˥?$(O?ʇ??0Bd??oq?YΓ? d???????r???3(???[??c??[??N8??&?׮+?nv??2?]?Nzj?d6?E????????????l??̃e(????9??E???6??k??D}????a?)B1L??????
ص?	??7$?7??bBU?	?}?_n<??#]?jR??
??/?>???U?????QY?~۰??ͱF.?j:9???'??3?????ÙD???Ա???"?a??iyG?ʇ?71??"???LOݛ??8?69}??7?)??B??[a?>E?K???y1*???H??#???Z?l???j??8Vu?ә???9?{??r     