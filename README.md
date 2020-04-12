**DB Scripts:**

create table parent (id serial);    
create table child (id serial, parent_id integer not null, sex character varying(10));

INSERT INTO public.parent values(default);  
INSERT INTO public.parent values(default); 

INSERT INTO public.child
(parent_id, sex)
VALUES(1, 'girl');

INSERT INTO public.child
(parent_id, sex)
VALUES(1, 'girl');

INSERT INTO public.child
(parent_id, sex)
VALUES(1, 'boy');

GRANT ALL ON TABLE parent TO mb_core;  
GRANT ALL ON TABLE child TO mb_core;