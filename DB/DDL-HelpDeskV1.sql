-- -------------------------------------------
-- --- 	DDL Help Desk V.2    -----------------
-- ---  AUTOR: -----------------
-- ---  FECHA: 14-02-2023    -----------------
-- --- ---------------------------------------
-- --------------------------------------------

DROP database if exists help_desk;
CREATE DATABASE help_desk;
USE help_desk;
CREATE TABLE usuario(
	idUsuario 		int 		not null 	auto_increment 	primary key,
    nombreUsuario 	varchar(40) not null,
    contrasenia 	varchar(20) not null,
    rol 			varchar(20) not null,
    ultimaConexion 	varchar(20) 
);

CREATE TABLE departamento(
	idDepartamento 		 int 			not null  auto_increment primary key,
    nombreDepartamento 	 varchar(30) 	not null,
    sucursal 			 varchar(30) 	not null
);

DROP TABLE IF EXISTS empleado;
CREATE TABLE empleado(
	idEmpleado 		int auto_increment primary key not null,
    nombreEmpleado  varchar(30) not null,
    primerApellido varchar(20) not null,
    segundoApellido varchar(30),
    rfc varchar(15),
    email varchar(40),
    telefono varchar(20),
    foto longtext,
    idDepartamento int not null,
    idUsuario int not null,
    foreign key (idDepartamento) REFERENCES departamento(idDepartamento),
    foreign key (idUsuario) REFERENCES usuario(idUsuario)
);

INSERT INTO departamento(nombreDepartamento, sucursal) VALUES("MARKETING", "PALMA ROJA");
INSERT INTO departamento(nombreDepartamento, sucursal) VALUES("MARKETING", "MIRABELLA");
INSERT INTO departamento(nombreDepartamento, sucursal) VALUES("CAJERO", "SATELITE");

INSERT INTO usuario(nombreUsuario, contrasenia, rol, ultimaConexion) VALUES("ADMIN", "1234", "ADMIN", null);
INSERT INTO usuario(nombreUsuario, contrasenia, rol, ultimaConexion) VALUES("SERGIO", "10012003", "ADMIN", null);
INSERT INTO usuario(nombreUsuario, contrasenia, rol, ultimaConexion) VALUES("MIGUEL", "1023", "EMPLEADO", null);
-- Stored Procedure para insertar nuevos Empleados.
DROP PROCEDURE IF EXISTS insertarEmpleado;
DELIMITER $$
CREATE PROCEDURE insertarEmpleado(	/* Datos Personales */
                                    IN	var_nombreEmpleado          VARCHAR(64),    --  1
                                    IN	var_primerApellido VARCHAR(64),    --  2
                                    IN	var_segundoApellido VARCHAR(64),    --  3
									IN  var_rfc				VARCHAR(13),	-- 4
                                    IN	var_email           VARCHAR(129),   -- 5
                                    IN	var_telefono        VARCHAR(20),    -- 6
                                    
                                    /* Valores de Retorno */
                                    
                                    INOUT	var_idEmpleado      INT,            -- 7
                                    INOUT	var_idDepartamento       INT,            -- 8
                                    INOUT	var_idUsuario       INT            -- 9
                                    
				)                                    
    BEGIN        
        -- Comenzamos insertando los datos de la Persona:
        INSERT INTO empleado (nombreEmpleado, primerApellido, segundoApellido, rfc,
                             email, telefono)
                    VALUES( var_nombreEmpleado, var_primerApellido, var_segundoApellido, 
                            var_rfc, var_email, var_telefono);
                            
                            
        set var_idDepartamento = last_insert_id();
        set var_idUsuario = last_insert_id();
        INSERT INTO empleado (idDepartamento, idUsuario)
                    VALUES(var_idDepartamento, var_idUsuario);
        -- Obtenemos el ID del Empleado que se genero:
        SET var_idEmpleado = LAST_INSERT_ID();
    END
$$
DELIMITER ;




CREATE TABLE ticket(
	idTicket 	int 			not null 	primary key auto_increment,
    idEmpleado 	int 			not null,
    dispositivo varchar(30) 	not null,
    descripcion varchar(200) 	not null,
    fechaHora 	datetime	 	not null,
    estatus 	int 			not null,  -- El estaus puede tener 3 valores 1[registrado], 2[Pendiente], 3[Atendido] 
    foreign key(idEmpleado) references empleado(idEmpleado)
    
);
SELECT*FROM departamento;
select * from empleado;
-- Stored Procedure para insertar nuevos Empleados.
DROP PROCEDURE IF EXISTS insertEmpleado;
DELIMITER $$
CREATE PROCEDURE insertEmpleado( /* Datos Personales */
    IN var_nombreEmpleado VARCHAR(64), -- 1
    IN var_primerApellido VARCHAR(64), -- 2
    IN var_segundoApellido VARCHAR(64), -- 3
    IN var_rfc VARCHAR(13), -- 4
    IN var_email VARCHAR(129), -- 5
    IN var_telefono VARCHAR(20), -- 6
    IN var_foto longtext,

    /* Datos del usuario */
    IN var_nombreUsuario VARCHAR(40), -- 7
    IN var_contrasenia VARCHAR(20), -- 8
    IN var_rol VARCHAR(20), -- 9
    IN var_ultimaConexion varchar(20), -- 10 
    IN var_idDepartamento INT, -- 11
    /* Valores de Retorno */

    OUT var_idEmpleado INT, -- 12
    OUT var_idUsuario INT -- 13
)
BEGIN
    -- Comenzamos insertando los datos de empleado:
    
    INSERT INTO usuario(nombreUsuario, contrasenia, rol, ultimaConexion)
    VALUES (var_nombreUsuario, var_contrasenia, var_rol, var_ultimaConexion);
    SET var_idUsuario = LAST_INSERT_ID();

    INSERT INTO empleado (nombreEmpleado, primerApellido, segundoApellido, rfc,
        email, telefono, foto, idDepartamento, idUsuario)
    VALUES (var_nombreEmpleado, var_primerApellido, var_segundoApellido, 
			var_rfc, var_email, var_telefono, var_foto, var_idDepartamento, var_idUsuario);
    -- Obtenemos el ID del Empleado que se genero:
    SET var_idEmpleado = LAST_INSERT_ID();
END
$$
DELIMITER ;
select * from usuario;
select * from departamento;
select * from empleado;
