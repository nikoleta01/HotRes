alter role postgres with password 'coconut';


DROP TABLE lokalita;

CREATE TABLE lokalita {
  id SERIAL,
  city VARCHAR(100),
  country VARCHAR(100),
  PRIMARY KEY(id)
} ;

INSERT INTO lokalita (id, city, country)
VALUES (1, 'Bratislava', 'Slovensko'),
       (2, 'Košice', 'Slovensko'),
       (3, 'Banská Bystrica', 'Slovensko'),
       (4, 'Zvolen', 'Slovensko'),
       (5, 'Krupina', 'Slovensko'),
       (6, 'Bardejov', 'Slovensko'),
       (7, 'Rožňava', 'Slovensko'),
       (8, 'Prešov', 'Slovensko'),
       (9, 'Poprad', 'Slovensko'),
       (10, 'Kežmarok', 'Slovensko'),
       (11, 'Žiar nad Hronom', 'Slovensko'),
       (12, 'Senec', 'Slovensko'),
       (13, 'Púchov', 'Slovensko'),
       (14, 'Žilina', 'Slovensko'),
       (15, 'Pezinok', 'Slovensko'),
       (16, 'Piešťany', 'Slovensko'),
       (17, 'Kremnica', 'Slovensko'),
       (18, 'Trenčín', 'Slovensko'),
       (19, 'Trnava', 'Slovensko'),
       (20, 'Košice', 'Slovensko'),
       (21, 'Spišská Nová Ves', 'Slovensko'),
       (22, 'Praha', 'Česko'),
       (23, 'Brno', 'Česko'),
       (24, 'Plzeň', 'Česko'),
       (25, 'Zlín', 'Česko'),
       (26, 'Olomouc', 'Česko'),
       (27, 'Ostrava', 'Česko'),
       (28, 'Prostějov', 'Česko'),
       (29, 'Jihlava', 'Česko'),
       (30, 'Opava', 'Česko'),
       (31, 'Berlín', 'Nemecko'),
       (32, 'Mníchov', 'Nemecko'),
       (33, 'Ravensburg', 'Nemecko'),
       (34, 'Štuttgart', 'Nemecko'),
       (35, 'Regensburg', 'Nemecko'),
       (36, 'Norimberg', 'Nemecko'),
       (37, 'Frankfurt nad Mohanom', 'Nemecko'),
       (38, 'Augsburg', 'Nemecko'),
       (39, 'Hamburg', 'Nemecko'),
       (40, 'Dortmund', 'Nemecko'),
       (41, 'Miláno', 'Taliansko'),
       (42, 'Florencia', 'Taliansko'),
       (43, 'Janov', 'Taliansko'),
       (44, 'Benátky', 'Taliansko'),
       (45, 'Boloňa', 'Taliansko'),
       (46, 'San Maríno', 'Taliansko'),
       (47, 'Neapol', 'Taliansko'),
       (48, 'Bari', 'Taliansko'),
       (49, 'Palermo', 'Taliansko'),
       (50, 'Catania', 'Taliansko');


select * from lokalita;
