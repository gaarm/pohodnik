-- database script for sqlite
CREATE TABLE person (
 id INTEGER PRIMARY KEY,
 name TEXT NOT NULL,
 surname TEXT NOT NULL,
 UNIQUE (name, surname)
);

CREATE TABLE pohod (
 id INTEGER PRIMARY KEY,
 naziv TEXT NOT NULL UNIQUE
);

CREATE TABLE oseba_pohod (
 id INTEGER PRIMARY KEY,
 oseba_id INTEGER NOT NULL,
 pohod_id INTEGER NOT NULL,
 UNIQUE (oseba_id, pohod_id)
);