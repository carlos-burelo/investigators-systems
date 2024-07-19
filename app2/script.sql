CREATE TABLE IF NOT EXISTS cientificos (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nombre TEXT NOT NULL CHECK (nombre <> ''),
    telefono TEXT NOT NULL CHECK (telefono <> ''),
    correo TEXT NOT NULL CHECK (correo <> ''),
    grado_academico TEXT NOT NULL CHECK (grado_academico <> ''),
    categoria TEXT NOT NULL CHECK (categoria <> '')
);

CREATE TABLE IF NOT EXISTS proyectos (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nombre TEXT NOT NULL CHECK (nombre <> ''),
    area TEXT NOT NULL CHECK (area <> ''),
    monto REAL CHECK (monto >= 0),
    fecha_inicio DATE NOT NULL,
    `fecha_fin` DATE NOT NULL,
    duracion DECIMAL(10,2) CHECK (duracion >= 0),
    terminacion DECIMAL(10,2) CHECK (terminacion >= 0),
    avance DECIMAL(10,2) CHECK (avance >= 0)
);

CREATE TABLE IF NOT EXISTS proyecto_cientificos (
    id_cientifico INTEGER NOT NULL,
    id_proyecto INTEGER NOT NULL,
    PRIMARY KEY (id_cientifico, id_proyecto),
    FOREIGN KEY (id_cientifico) REFERENCES cientificos(id),
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(clave)
);

CREATE TABLE IF NOT EXISTS avances (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    id_proyecto INTEGER NOT NULL,
    fecha DATE NOT NULL,
    texto TEXT NOT NULL CHECK (texto <> ''),
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(clave)
);