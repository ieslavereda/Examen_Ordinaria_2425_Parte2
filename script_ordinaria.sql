#---------------------------------------------------------- 
#-- Creamos la BD y tablespace 
#---------------------------------------------------------- 
DROP DATABASE IF EXISTS examen2425ord;
CREATE DATABASE examen2425ord;
USE examen2425ord;

#---------------------------------------------------------- 
#-- Creamos un usuario para el acceso a la base de datos 
#---------------------------------------------------------- 
DROP USER IF EXISTS 'usuarioEx2425ord'@'%';
CREATE USER 'usuarioEx2425ord'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON examen2425ord.* TO 'usuarioEx2425ord'@'%';
GRANT all PRIVILEGES on mysql.proc TO 'usuarioEx2425ord'@'%';

#---------------------------------------------------------- 
#-- Creamos las tablas
#---------------------------------------------------------- 
CREATE TABLE cliente(
                      id int auto_increment primary key,
                      nombre varchar(50) not null,
                      apellidos varchar(50) not null,
                      pais varchar(50) not null,
                      mail varchar(80) unique not null
);

DELIMITER @@
DROP PROCEDURE IF EXISTS obtenerMail @@
CREATE PROCEDURE obtenerMail(OUT mailOUT varchar(255), IN idIN varchar(10))
BEGIN
    DECLARE existeCliente INT DEFAULT 0;
    IF idIN IS NOT NULL
    THEN
		SELECT COUNT(*) INTO existeCliente FROM cliente WHERE id=idIN;
		IF existeCliente=0
		THEN   
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El cliente no existe en la BD', MYSQL_ERRNO = 1000;
		ELSE
			SELECT mail INTO mailOUT FROM cliente WHERE id=idIN;
		END IF;
	ELSE
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El id no puede estar vacio', MYSQL_ERRNO = 1000;
	END IF;
END @@ 
DELIMITER ;
