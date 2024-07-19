CREATE TABLE IF NOT EXISTS scientifics (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT NOT NULL CHECK (name <> ''),
    phone TEXT NOT NULL CHECK (phone <> ''),
    email TEXT NOT NULL CHECK (email <> ''),
    grade TEXT NOT NULL CHECK (grade <> ''),
    category TEXT NOT NULL CHECK (category <> '')
);

CREATE TABLE IF NOT EXISTS projects (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT NOT NULL CHECK (name <> ''),
    area TEXT NOT NULL CHECK (area <> ''),
    financing REAL CHECK (financing >= 0),
    start DATE NOT NULL,
    `end` DATE NOT NULL,
    duration DECIMAL(10,2) CHECK (duration >= 0),
    termination DECIMAL(10,2) CHECK (termination >= 0),
    advance DECIMAL(10,2) CHECK (advance >= 0)
);

CREATE TABLE IF NOT EXISTS project_scientific (
    scientific_id INTEGER NOT NULL,
    project_id INTEGER NOT NULL,
    PRIMARY KEY (scientific_id, project_id),
    FOREIGN KEY (scientific_id) REFERENCES cientificos(id),
    FOREIGN KEY (project_id) REFERENCES proyectos(clave)
);

CREATE TABLE IF NOT EXISTS advances (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    project_id INTEGER NOT NULL,
    date DATE NOT NULL,
    description TEXT NOT NULL CHECK (description <> ''),
    FOREIGN KEY (project_id) REFERENCES proyectos(clave)
);