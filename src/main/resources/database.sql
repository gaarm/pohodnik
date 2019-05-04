-- database script for sqlite
CREATE TABLE oseba (
 id INTEGER PRIMARY KEY,
 ime TEXT NOT NULL,
 priimek TEXT NOT NULL,
 UNIQUE (ime, priimek)
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