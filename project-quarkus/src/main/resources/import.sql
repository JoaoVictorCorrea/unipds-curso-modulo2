INSERT INTO Person(id, name, yearOfBirth) VALUES (1, 'João', 2003);
INSERT INTO Person(id, name, yearOfBirth) VALUES (2, 'Pedro', 2001);
INSERT INTO Person(id, name, yearOfBirth) VALUES (3, 'Lucas', 2011);
INSERT INTO Person(id, name, yearOfBirth) VALUES (4, 'Maria', 1900);
INSERT INTO Person(id, name, yearOfBirth) VALUES (5, 'Tiago', 1950);
INSERT INTO Person(id, name, yearOfBirth) VALUES (6, 'Jesus', 0001);

ALTER SEQUENCE person_seq RESTART WITH 7;