-- -------------------------------------------- --	
-- 			DDL HELP DESK V 1.0.0				--
-- AUTOR		SERGIO HERNANDEZ TAVARES		--
-- FECHA: 		14-02-2023						--
-- -------------------------------------------- --

DROP DATABASE IF EXISTS help_desk;
create database help_desk;

use help_desk;

create table usuario(
	idUsuario int primary key auto_increment,
    nombreUsuario Varchar(40) not null,
    contrasenia varchar(20) not null,
    rol varchar(20) not null,
    ultimaConexion varchar(20)
);
INSERT INTO usuario(nombreUsuario, contrasenia, rol, ultimaConexion)
VALUES ('SERGIOXD343', '10012003', 'ADMINISTRADOR', '2023-03-02'),
       ('MIKELLOL', 'CONTRASENIA', 'ADMINISTRADOR', '2023-03-01'),
       ('CAMA', 'MIRAMAMA', 'EMPLEADO', '2023-02-28'),
       ('LEO', '0000', 'EMPLEADO', NULL);
       select*from usuario;
create table departamento(
	idDepartamento int not null primary key auto_increment,
    nombreDepartamento varchar(30) not null,
    sucursal varchar(30) not null
);
INSERT INTO departamento(nombreDepartamento, sucursal) VALUES
  ('Recursos Humanos', 'Sucursal A'),
  ('Finanzas', 'Sucursal B'),
  ('Ventas', 'Sucursal C'),
  ('Marketing', 'Sucursal D');
SELECT*FROM departamento;
create table empleado(
	idEmpleado int not null primary key auto_increment,
    nombreEmpleado varchar(30) not null,
    primerApellido varchar(30) not null,
    segundoApellido varchar(30) not null,
    rfc varchar(15),
    email varchar(40),
    telefono varchar(20),
    nombreDepartamento varchar(40),
    idDepartamento int not null,
    idUsuario int not null,
    foreign key (idDepartamento) references departamento(idDepartamento),
    foreign key (idUsuario) references usuario(idUsuario)
);
INSERT INTO empleado(nombreEmpleado, nombreDepartamento, primerApellido, segundoApellido, rfc, email, telefono, idDepartamento, idUsuario) VALUES
  ('Juan','margaritas', 'Pérez', 'García', 'PERG901010', 'juan.perez@mail.com', '555-1234', 1, 1),
  ('María','michoacan', 'López', 'Gómez', 'LOMG890502', 'maria.lopez@mail.com', '555-5678', 2, 2),
  ('Carlos','mami' ,'González', 'Hernández', 'GOCH810703', 'carlos.gonzalez@mail.com', '555-4321', 3, 3),
  ('Ana','culiacan', 'Ramírez', 'Martínez', 'RAMA750412', 'ana.ramirez@mail.com', '555-8765', 4, 4);

select*from empleado;

create table ticket(
	idTicket int not null primary key auto_increment,
    idEmpleado int not null,
    dispositivo varchar(40) not null,
    descripcion varchar(200) not null,
    fechaHora dateTime not null,
    estatus int not null, -- el estatus puede tener 3 valores 1-registrado 2-pendiente y 3 -atendido
    foreign key (idEmpleado) references empleado(idEmpleado)
);

