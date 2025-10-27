CREATE DATABASE grupo_linguardia;
USE grupo_linguardia;

CREATE TABLE Profesores (
nombre_apellidos VARCHAR(100) NOT NULL UNIQUE,
email VARCHAR(100) PRIMARY KEY, 
contraseña VARCHAR(30) NOT NULL,
es_Tutor bool, 
CONSTRAINT chk_contraseña CHECK (CHAR_LENGTH(contraseña) >= 8)
);

CREATE TABLE Guardias (
id INT AUTO_INCREMENT PRIMARY KEY,
profesor_id INT NOT NULL,
fecha DATE NOT NULL,
turno VARCHAR(50),
comentarios TEXT,
FOREIGN KEY (profesor_id) REFERENCES Profesores(id),
CONSTRAINT uc_profesor_fecha UNIQUE (profesor_id, fecha)
);

CREATE TABLE Aulas (
cod_clase INT AUTO_INCREMENT PRIMARY KEY,
fecha DATE NOT NULL,
tutor bool,
turno VARCHAR(50),
comentarios TEXT,
FOREIGN KEY (tutor) REFERENCES Profesores(es_Tutor),

CONSTRAINT uc_profesor_fecha UNIQUE (profesor_id, fecha)
);


