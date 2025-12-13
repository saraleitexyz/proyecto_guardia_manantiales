SET FOREIGN_KEY_CHECKS = 0;

-- =========================
-- DROP (NOTAS: Tiene que ser orden inverso para que funcione)
-- =========================
DROP TABLE IF EXISTS ausencias;
DROP TABLE IF EXISTS guardias;
DROP TABLE IF EXISTS clases;
DROP TABLE IF EXISTS profesores;

-- =========================
-- PROFESORES
-- =========================
CREATE TABLE profesores (
  id VARCHAR(30) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- CLASES
-- =========================
CREATE TABLE clases (
  id INT NOT NULL AUTO_INCREMENT,
  nivel INT NOT NULL,
  tipo VARCHAR(30) NOT NULL,
  letra CHAR(2) NOT NULL,
  id_tutor VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_clases_profesor
    FOREIGN KEY (id_tutor)
    REFERENCES profesores(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- GUARDIAS
-- =========================
CREATE TABLE guardias (
  dia VARCHAR(10) NOT NULL,
  hora CHAR(2) NOT NULL,
  id_tutor VARCHAR(30) NOT NULL,
  PRIMARY KEY (dia, hora, id_tutor),
  CONSTRAINT fk_guardias_profesor
    FOREIGN KEY (id_tutor)
    REFERENCES profesores(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- AUSENCIAS
-- =========================
CREATE TABLE ausencias (
  id INT NOT NULL AUTO_INCREMENT,
  id_profesor_ausencia VARCHAR(30) NOT NULL,
  id_sustituto VARCHAR(30) NOT NULL,
  dia_fk VARCHAR(10) NOT NULL,
  hora_fk CHAR(2) NOT NULL,
  id_tutor_guardia_fk VARCHAR(30) NOT NULL,
  id_clase INT NOT NULL,
  PRIMARY KEY (id),

  CONSTRAINT fk_ausencia_profesor
    FOREIGN KEY (id_profesor_ausencia)
    REFERENCES profesores(id)
    ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT fk_ausencia_sustituto
    FOREIGN KEY (id_sustituto)
    REFERENCES profesores(id)
    ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT fk_ausencia_clase
    FOREIGN KEY (id_clase)
    REFERENCES clases(id)
    ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT fk_ausencia_guardia
    FOREIGN KEY (dia_fk, hora_fk, id_tutor_guardia_fk)
    REFERENCES guardias(dia, hora, id_tutor)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- DATA
-- =========================
INSERT INTO profesores (id, nombre, apellidos) VALUES
('p001', 'Jorge', 'Camacho Vela'),
('p002', 'Francisco', 'Villalba Sánchez'),
('p003', 'Dulcenombre de María', 'Pineda Dorado'),
('p004', 'Alberto', 'García Díaz'),
('p005', 'Nerea', 'Contreras Lizarreta');

INSERT INTO clases (nivel, tipo, letra, id_tutor) VALUES
(1, 'ASIR', 'A', 'p004'),
(2, 'ASIR', 'B', 'p005');

INSERT INTO guardias (dia, hora, id_tutor) VALUES
('Lunes',  '01', 'p004'),
('Lunes',  '02', 'p004'),
('Martes', '01', 'p005'),
('Martes', '02', 'p005');

INSERT INTO ausencias
(id_profesor_ausencia, id_sustituto, dia_fk, hora_fk, id_tutor_guardia_fk, id_clase)
VALUES
('p001', 'p004', 'Lunes',  '01', 'p004', 1),
('p002', 'p005', 'Martes', '01', 'p005', 2),
('p003', 'p004', 'Lunes',  '02', 'p004', 1);

SET FOREIGN_KEY_CHECKS = 1;

