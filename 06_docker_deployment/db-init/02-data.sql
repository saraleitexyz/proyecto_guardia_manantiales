USE linguardia;

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

