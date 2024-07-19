-- Tabla de Científicos
CREATE TABLE IF NOT EXISTS cientificos (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nombre TEXT NOT NULL CHECK (nombre <> ''),
    telefono TEXT,
    email TEXT,
    grado_academico TEXT,
    categoria TEXT
);

-- Tabla de Proyectos
CREATE TABLE IF NOT EXISTS proyectos (
    clave INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nombre TEXT NOT NULL CHECK (nombre <> ''),
    area TEXT,
    monto_financiamiento REAL CHECK (monto_financiamiento >= 0),
    fecha_inicio DATE,
    fecha_fin DATE,
    duracion_meses DECIMAL(10,2) CHECK (duracion_meses >= 0),
    terminacion_meses DECIMAL(10,2) CHECK (terminacion_meses >= 0),
    avances_meses DECIMAL(10,2) CHECK (avances_meses >= 0)
);

-- Tabla de Asignación de Científicos a Proyectos
CREATE TABLE IF NOT EXISTS asignacion_cientificos_proyectos (
    cientifico_id INTEGER,
    proyecto_clave INTEGER,
    PRIMARY KEY (cientifico_id, proyecto_clave),
    FOREIGN KEY (cientifico_id) REFERENCES cientificos(id),
    FOREIGN KEY (proyecto_clave) REFERENCES proyectos(clave)
);

-- Tabla de Avance de Proyectos
CREATE TABLE IF NOT EXISTS avance_proyectos (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    proyecto_clave INTEGER,
    fecha DATE,
    descripcion TEXT,
    FOREIGN KEY (proyecto_clave) REFERENCES proyectos(clave)
);
