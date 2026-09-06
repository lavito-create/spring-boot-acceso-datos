-- Insertar datos en la tabla Empleado
INSERT INTO Empleado (id, nombre, apellido, correo, salario) VALUES
(1, 'Juan', 'Perez', 'juan.perez@example.com', 1000),
(2, 'Maria', 'Gomez', 'maria.gomez@example.com', 1200),
(3, 'Pedro', 'Lopez', 'pedro.lopez@example.com', 1500),
(4, 'Ana', 'Martinez', 'ana.martinez@example.com', 1100),
(5, 'Luis', 'Garcia', 'luis.garcia@example.com', 1300),
(6, 'Elena', 'Hernandez', 'elena.hernandez@example.com', 1400),
(7, 'Carlos', 'Sanchez', 'carlos.sanchez@example.com', 1600),
(8, 'Marta', 'Rodriguez', 'marta.rodriguez@example.com', 1700),
(9, 'Jorge', 'Fernandez', 'jorge.fernandez@example.com', 1800),
(10, 'Laura', 'Diaz', 'laura.diaz@example.com', 1900),
(11, 'Alberto', 'Llano', 'alberto.llano@example.com', 1550);

-- Insertar datos en la tabla Seccion
INSERT INTO Seccion (id, nombre, descripcion) VALUES
(1, 'Ventas', 'Departamento de ventas'),
(2, 'Marketing', 'Departamento de marketing'),
(3, 'IT', 'Departamento de tecnología'),
(4, 'Recursos Humanos', 'Departamento de recursos humanos'),
(5, 'Finanzas', 'Departamento de finanzas'),
(6, 'Logística', 'Departamento de logística'),
(7, 'Producción', 'Departamento de producción'),
(8, 'Calidad', 'Departamento de calidad'),
(9, 'Compras', 'Departamento de compras'),
(10, 'Legal', 'Departamento legal');

-- Insertar datos en la tabla EmpleadoSeccion
INSERT INTO Empleado_Seccion (id_empleado, id_seccion) VALUES
(1, 1), -- Juan en Ventas
(2, 1), -- Maria en Ventas
(3, 1), -- Pedro en Ventas
(4, 2), -- Ana en Marketing
(5, 2), -- Luis en Marketing
(6, 2), -- Elena en Marketing
(7, 2), -- Carlos en Marketing
(8, 2); -- Marta en Marketing