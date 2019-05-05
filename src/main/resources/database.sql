-- database script for sqlite
CREATE TABLE person (
 id INTEGER PRIMARY KEY,
 first_name TEXT NOT NULL,
 last_name TEXT NOT NULL,
 UNIQUE (first_name, last_name)
);

CREATE TABLE excursion (
 id INTEGER PRIMARY KEY,
 name TEXT NOT NULL UNIQUE
);

CREATE TABLE person_excursion (
 id INTEGER PRIMARY KEY,
 person_id INTEGER NOT NULL,
 excursion_id INTEGER NOT NULL,
 UNIQUE (person_id, excursion_id)
);