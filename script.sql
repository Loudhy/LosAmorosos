SET FOREIGN_KEY_CHECKS = 0;
USE lp2;

DROP TABLE IF EXISTS DATOS_GENERALES;
CREATE TABLE DATOS_GENERALES(
	ID_DATOS_GENERALES INT PRIMARY KEY AUTO_INCREMENT,
	IGV FLOAT NOT NULL,
	SUELDO_MINIMO FLOAT NOT NULL,
    CORREO_EMPRESA VARCHAR(80) NOT NULL,
    CONTRASEÑA_EMPRESA VARCHAR(80) NOT NULL,
    FECHA DATE NOT NULL,
    PLAZO_DE_PAGO INT NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS USUARIO;

DROP TABLE IF EXISTS PRODUCTO;
CREATE TABLE PRODUCTO(
	ID_PRODUCTO INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE_PRODUCTO VARCHAR(80) NOT NULL,
    STOCK_EMPRESA INT NOT NULL,
    STOCK_VENDEDOR INT NOT NULL,
    PRECIO_UNITARIO FLOAT NOT NULL,
    DESCRIPCION VARCHAR(80),
    FOTO LONGBLOB NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS PRESENTACION;
CREATE TABLE PRESENTACION(
	ID_PRESENTACION INT PRIMARY KEY AUTO_INCREMENT,
    ID_PRODUCTO INT,
    FOREIGN KEY(ID_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO),
    DISEÑO VARCHAR(80) NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS DEPARTAMENTO;
CREATE TABLE DEPARTAMENTO(
	ID_DEPARTAMENTO INT PRIMARY KEY AUTO_INCREMENT,
	NOMBRE_DEPARTAMENTO VARCHAR(80) NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS PROVINCIA;
CREATE TABLE PROVINCIA(
	ID_PROVINCIA INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE_PROVINCIA VARCHAR(80) NOT NULL,
    ID_DEPARTAMENTO INT NOT NULL,
    FOREIGN KEY(ID_DEPARTAMENTO) REFERENCES DEPARTAMENTO(ID_DEPARTAMENTO),
    ACTIVE TINYINT(1) NOT NULL
)ENGINE = INNODB;

DROP TABLE IF EXISTS AREA;
CREATE TABLE AREA(
	ID_AREA INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE_AREA VARCHAR(80) NOT NULL,
    CODIGO_AREA INT NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS EMPLEADO;
CREATE TABLE EMPLEADO(
	ID_EMPLEADO INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE_EMPLEADO VARCHAR(80) NOT NULL,
    APELLIDO_PATERNO VARCHAR(80) NOT NULL,
    APELLIDO_MATERNO VARCHAR(80) NOT NULL,
    DNI_EMPLEADO VARCHAR(8) NOT NULL,
    FECHA_NACIMIENTO DATE NOT NULL,
	NOMBRE_USUARIO VARCHAR(80),
    CONTRASEÑA VARCHAR(80),
    TELEFONO_EMPLEADO VARCHAR(10),
    CORREO_EMPLEADO VARCHAR(80),
    ESTADO_CIVIL ENUM('Soltero','Casado') NOT NULL,
    SUELDO FLOAT NOT NULL,
    ID_AREA INT NOT NULL,
    FOREIGN KEY(ID_AREA) REFERENCES AREA(ID_AREA),
    FECHA_INGRESO DATE NOT NULL,
    FOTO LONGBLOB,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;


DROP TABLE IF EXISTS CLIENTE_VENDEDOR;
CREATE TABLE CLIENTE_VENDEDOR(
	ID_CLIENTE_VENDEDOR INT PRIMARY KEY AUTO_INCREMENT,
    ID_CLIENTE INT,
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE),
    ID_VENDEDOR INT,
    FOREIGN KEY (ID_VENDEDOR) REFERENCES EMPLEADO(ID_EMPLEADO),
    ACTIVE TINYINT(1)
)ENGINE=INNODB;

DROP TABLE IF EXISTS META_MENSUAL;
CREATE TABLE META_MENSUAL(
	ID_META_MENSUAL INT PRIMARY KEY AUTO_INCREMENT,
    FECHA_INICIO DATE NOT NULL,
    FECHA_FIN DATE NOT NULL,
    CANTIDAD_OBJETIVO FLOAT NOT NULL,
    DESCRIPCION_META_MENSUAL VARCHAR(80),
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS OBJETIVO_VENDEDOR;
CREATE TABLE OBJETIVO_VENDEDOR(
	ID_OBJETIVO_VENDEDOR INT PRIMARY KEY AUTO_INCREMENT,    
    ID_META_MENSUAL INT NOT NULL,
    FOREIGN KEY (ID_META_MENSUAL) REFERENCES META_MENSUAL(ID_META_MENSUAL),
    MONTO FLOAT NOT NULL,
    ID_VENDEDOR INT,
    FOREIGN KEY(ID_VENDEDOR) REFERENCES EMPLEADO(ID_EMPLEADO),
    COMISION FLOAT NOT NULL,
    BONO FLOAT NOT NULL,
    PROGRESO FLOAT NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;


DROP TABLE IF EXISTS LINEA_PEDIDO;
CREATE TABLE LINEA_PEDIDO(
	ID_LINEA_PEDIDO INT PRIMARY KEY AUTO_INCREMENT,
    CANTIDAD INT NOT NULL,
    ID_PRODUCTO INT NOT NULL,
    ID_PEDIDO INT NOT NULL,
    SUBTOTAL FLOAT NOT NULL,
    FOREIGN KEY(ID_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO),
    FOREIGN KEY(ID_PEDIDO) REFERENCES PEDIDO (ID_PEDIDO),
    ESTADO_LINEA_PEDIDO ENUM('Aceptado','Rechazado','Pendiente'),
    FECHA_ATENCION DATE NOT NULL,
    CANTIDAD_A_ATENDER INT NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS CLIENTE;
CREATE TABLE CLIENTE(
	ID_CLIENTE INT PRIMARY KEY AUTO_INCREMENT,
    RUC VARCHAR(11) NOT NULL,
    RAZON_SOCIAL VARCHAR(80) NOT NULL,
    DIRECCION VARCHAR(80) NOT NULL,
    TELEFONO_CLIENTE VARCHAR(80),
    ID_PROVINCIA INT NOT NULL,
    FOREIGN KEY(ID_PROVINCIA) REFERENCES PROVINCIA(ID_PROVINCIA),
    CORREO_CLIENTE VARCHAR(80),
    PUNTOS INT NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;


DROP TABLE IF EXISTS PEDIDO;
CREATE TABLE PEDIDO(
	ID_PEDIDO INT PRIMARY KEY AUTO_INCREMENT,
	TOTAL_PEDIDO FLOAT NOT NULL,    
    ID_CLIENTE_VENDEDOR INT,
    FOREIGN KEY(ID_CLIENTE_VENDEDOR) REFERENCES CLIENTE_VENDEDOR(ID_CLIENTE_VENDEDOR),
    ESTADO_PEDIDO ENUM('Aceptado','Rechazado','Pendiente','Pagado') NOT NULL,
    FECHA_REGISTRO DATE NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;


DROP TABLE IF EXISTS LINEA_SOLICITUD;
CREATE TABLE LINEA_SOLICITUD(
	ID_LINEA_SOLICITUD INT PRIMARY KEY AUTO_INCREMENT,
    ID_SOLICITUD INT NOT NULL,
    ID_LINEA_PEDIDO INT NOT NULL,
    CANTIDAD INT NOT NULL,
    ESTADO_LINEA_SOLICITUD ENUM('Aceptado','Rechazado','Pendiente') NOT NULL,
    FOREIGN KEY(ID_SOLICITUD) REFERENCES SOLICITUD(ID_SOLICITUD),
    FOREIGN KEY(ID_LINEA_PEDIDO) REFERENCES LINEA_PEDIDO(ID_LINEA_PEDIDO),
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS SOLICITUD;
CREATE TABLE SOLICITUD(
	ID_SOLICITUD INT PRIMARY KEY AUTO_INCREMENT,
    DESCRIPCION_SOLICITUD VARCHAR(80) NOT NULL,
    ESTADO_SOLICITUD ENUM('Aceptado','Rechazado','Pendiente') NOT NULL,
    FECHA_REGISTRO DATE NOT NULL,
    ID_PEDIDO INT NOT NULL,
    ACTIVE TINYINT(1) NOT NULL
)ENGINE=INNODB;

DROP PROCEDURE IF EXISTS INSERTAR_AREA;
DROP PROCEDURE IF EXISTS ACTUALIZAR_AREA;
DROP PROCEDURE IF EXISTS ELIMINAR_AREA;
DROP PROCEDURE IF EXISTS LISTAR_AREA;

DROP PROCEDURE IF EXISTS INSERTAR_CLIENTE;
DROP PROCEDURE IF EXISTS ACTUALIZAR_CLIENTE;
DROP PROCEDURE IF EXISTS LISTAR_CLIENTES_POR_VENDEDOR;
DROP PROCEDURE IF EXISTS BUSCAR_CLIENTE_POR_FILTRO;

DROP PROCEDURE IF EXISTS INSERTAR_CLIENTE_VENDEDOR;
DROP PROCEDURE IF EXISTS ELIMINAR_CLIENTE_VENDEDOR;
DROP PROCEDURE IF EXISTS INSERTAR_DATOS_GENERALES;
DROP PROCEDURE IF EXISTS ENCONTRAR_DATOS_GENERALES_POR_FECHA;

DROP PROCEDURE IF EXISTS INSERTAR_EMPLEADO;
DROP PROCEDURE IF EXISTS ACTUALIZAR_EMPLEADO;
DROP PROCEDURE IF EXISTS ELIMINAR_EMPLEADO;
DROP PROCEDURE IF EXISTS LISTAR_EMPLEADO;
DROP PROCEDURE IF EXISTS LISTAR_EMPLEADO_POR_AREA;
DROP PROCEDURE IF EXISTS BUSCAR_EMPLEADO_POR_CORREO;
DROP PROCEDURE IF EXISTS BUSCAR_EMPLEADO_POR_APELLIDOS;
DROP PROCEDURE IF EXISTS BUSCAR_EMPLEADO_POR_DNI;
DROP PROCEDURE IF EXISTS INSERTAR_DEPARTAMENTO;
DROP PROCEDURE IF EXISTS ACTUALIZAR_DEPARTAMENTO;
DROP PROCEDURE IF EXISTS ELIMINAR_DEPARTAMENTO;
DROP PROCEDURE IF EXISTS LISTAR_DEPARTAMENTO;

DROP PROCEDURE IF EXISTS LISTAR_PEDIDOS_DE_VENDEDOR_POR_RANGO_DE_FECHAS;
DROP PROCEDURE IF EXISTS INSERTAR_USUARIO;
DROP PROCEDURE IF EXISTS ACTUALIZAR_USUARIO;
DROP PROCEDURE IF EXISTS ELIMINAR_USUARIO;
DROP PROCEDURE IF EXISTS LISTAR_USUARIO;
DROP PROCEDURE IF EXISTS BUSCAR_AREA_POR_USUARIO;
DROP PROCEDURE IF EXISTS BUSCAR_USUARIO_POR_EMPLEADO;
DROP PROCEDURE IF EXISTS BUSCAR_USUARIO;

DROP PROCEDURE IF EXISTS INSERTAR_PROVINCIA;
DROP PROCEDURE IF EXISTS ACTUALIZAR_PROVINCIA;
DROP PROCEDURE IF EXISTS ELIMINAR_PROVINCIA;
DROP PROCEDURE IF EXISTS LISTAR_PROVINCIA_POR_DEPARTAMENTO;

DROP PROCEDURE IF EXISTS INSERTAR_LINEA_PEDIDO;
DROP PROCEDURE IF EXISTS ACTUALIZAR_LINEA_PEDIDO;
DROP PROCEDURE IF EXISTS LISTAR_LINEAS_PEDIDO;
DROP PROCEDURE IF EXISTS LISTAR_LINEAS_PEDIDO_POR_RANGO_DE_FECHAS;
DROP PROCEDURE IF EXISTS LISTAR_LINEAS_PEDIDO_POR_PRODUCTO;


DROP PROCEDURE IF EXISTS INSERTAR_META_MENSUAL;
DROP PROCEDURE IF EXISTS ELIMINAR_META_MENSUAL;
DROP PROCEDURE IF EXISTS ACTUALIZAR_META_MENSUAL;
DROP PROCEDURE IF EXISTS LISTAR_META_MENSUAL;

DROP PROCEDURE IF EXISTS INSERTAR_OBJETIVO_VENDEDOR;
DROP PROCEDURE IF EXISTS ACTUALIZAR_OBJETIVO_VENDEDOR;
DROP PROCEDURE IF EXISTS ELIMINAR_OBJETIVO_VENDEDOR;
DROP PROCEDURE IF EXISTS LISTAR_OBJETIVO_VENDEDOR;

DROP PROCEDURE IF EXISTS INSERTAR_PEDIDO;
DROP PROCEDURE IF EXISTS ACTUALIZAR_PEDIDO;
DROP PROCEDURE IF EXISTS ELIMINAR_PEDIDO;
DROP PROCEDURE IF EXISTS LISTAR_PEDIDO_POR_VENDEDOR;
DROP PROCEDURE IF EXISTS LISTAR_PEDIDO_POR_CLIENTE;
DROP PROCEDURE IF EXISTS LISTAR_PEDIDO_POR_VENDEDOR_POR_FECHAS;
DROP PROCEDURE IF EXISTS LISTAR_PEDIDO_POR_ESTADO;
DROP PROCEDURE IF EXISTS INSERTAR_PRODUCTO;
DROP PROCEDURE IF EXISTS ACTUALIZAR_PRODUCTO;
DROP PROCEDURE IF EXISTS ELIMINAR_PRODUCTO;
DROP PROCEDURE IF EXISTS LISTAR_PRODUCTO_DISPONIBLE;
DROP PROCEDURE IF EXISTS BUSCAR_PRODUCTO_POR_NOMBRE;

DROP PROCEDURE IF EXISTS INSERTAR_PRESENTACION;
DROP PROCEDURE IF EXISTS ACTUALIZAR_PRESENTACION;
DROP PROCEDURE IF EXISTS ELIMINAR_PRESENTACION;
DROP PROCEDURE IF EXISTS LISTAR_PRESENTACION;
DROP PROCEDURE IF EXISTS LISTAR_PRODUCTOS_POR_NOMBRE;
DROP PROCEDURE IF EXISTS LISTAR_PROVINCIAS;

DROP PROCEDURE IF EXISTS INSERTAR_SOLICITUD;
DROP PROCEDURE IF EXISTS ACTUALIZAR_SOLICITUD;
DROP PROCEDURE IF EXISTS ELIMINAR_SOLICITUD;
DROP PROCEDURE IF EXISTS LISTAR_LINEAS_SOLICITUD;
DROP PROCEDURE IF EXISTS LISTAR_SOLICITUDES_POR_ESTADO;
DROP PROCEDURE IF EXISTS LISTAR_SOLICITUD;
DROP PROCEDURE IF EXISTS LISTAR_SOLICITUDES_POR_ESTADO;
DROP PROCEDURE IF EXISTS INSERTAR_LINEA_SOLICITUD;
DROP PROCEDURE IF EXISTS ACTUALIZAR_LINEA_SOLICITUD;

DROP PROCEDURE IF EXISTS BUSCAR_SOLICITUD_POR_PEDIDO;
DROP PROCEDURE IF EXISTS BUSCAR_EMPLEADO_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_VENDEDOR_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_CLIENTE_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_PEDIDO_POR_IDPEDIDO;
DROP PROCEDURE IF EXISTS BUSCAR_SOLICITUD_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_PRODUCTO_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_AREA_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_DATOS_GENERALES_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_PROVINCIA_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_DEPARTAMENTO_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_OBJETIVO_VENDEDOR_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_META_MENSUAL_POR_ID;
DROP PROCEDURE IF EXISTS BUSCAR_RELACION_CLIENTE_VENDEDOR;

DROP PROCEDURE IF EXISTS ELIMINAR_CLIENTE;
DROP PROCEDURE IF EXISTS LISTAR_CLIENTES;
DROP PROCEDURE IF EXISTS LISTAR_PRODUCTOS;
DROP PROCEDURE IF EXISTS LISTAR_VENDEDOR;
DROP PROCEDURE IF EXISTS BUSCAR_CLIENTE_VENDEDOR;

DROP PROCEDURE IF EXISTS LISTAR_CLIENTES_POR_NOMBRE;

DELIMITER $

CREATE PROCEDURE INSERTAR_AREA(	
    OUT _ID_AREA INT,
    IN _NOMBRE_AREA VARCHAR(80),
    IN _CODIGO_AREA INT,
    IN _ACTIVE TINYINT(1))
BEGIN
	INSERT INTO AREA(NOMBRE_AREA,CODIGO_AREA,ACTIVE)
	VALUES(_NOMBRE_AREA,_CODIGO_AREA,_ACTIVE);
	SET _ID_AREA = last_insert_id();
END$


CREATE PROCEDURE ACTUALIZAR_AREA(
	IN _ID_AREA INT,
	IN _NOMBRE_AREA VARCHAR(80),
    IN _CODIGO_AREA INT
)
BEGIN
	UPDATE AREA
    SET NOMBRE_AREA = _NOMBRE_AREA, CODIGO_AREA = _CODIGO_AREA
    WHERE ID_AREA = _ID_AREA;
END$


CREATE PROCEDURE ELIMINAR_AREA(
	IN _ID_AREA INT
)
BEGIN
	UPDATE AREA
	SET ACTIVE = false
	WHERE ID_AREA = _ID_AREA;
END$

CREATE PROCEDURE LISTAR_AREA()
BEGIN
SELECT * FROM AREA
WHERE ACTIVE=true;
END$

CREATE PROCEDURE INSERTAR_CLIENTE(
	OUT _ID_CLIENTE INT,
    IN _RUC VARCHAR(11),
    IN _RAZON_SOCIAL VARCHAR(80),
    IN _CORREO_CLIENTE VARCHAR(80),
    IN _ID_PROVINCIA INT,
    IN _DIRECCION VARCHAR(80),
    IN _TELEFONO_CLIENTE VARCHAR(80),
    IN _PUNTOS INT,
    IN _ACTIVE TINYINT(1)
)
BEGIN
INSERT INTO CLIENTE(RUC, RAZON_SOCIAL,CORREO_CLIENTE,ID_PROVINCIA, DIRECCION,TELEFONO_CLIENTE,PUNTOS,ACTIVE)
VALUES (_RUC,_RAZON_SOCIAL,_CORREO_CLIENTE,_ID_PROVINCIA,_DIRECCION,_TELEFONO_CLIENTE,_PUNTOS,_ACTIVE);
SET _ID_CLIENTE = last_insert_id();
END$

DROP PROCEDURE IF EXISTS ACTUALIZAR_CLIENTE;
CREATE PROCEDURE ACTUALIZAR_CLIENTE(
	IN _ID_CLIENTE INT,
    IN _RUC VARCHAR(11),
    IN _RAZON_SOCIAL VARCHAR(80),
    IN _CORREO_CLIENTE VARCHAR(80),
    IN _ID_PROVINCIA INT,
    IN _DIRECCION VARCHAR(80),
    IN _TELEFONO_CLIENTE VARCHAR(80),
    IN _PUNTOS INT
)
BEGIN
UPDATE CLIENTE
SET RUC = _RUC, RAZON_SOCIAL = _RAZON_SOCIAL, CORREO_CLIENTE = _CORREO_CLIENTE,
ID_PROVINCIA = _ID_PROVICINA, DIRECCION = _DIRECCION , TELEFONO_CLIENTE = _TELEFONO_CLIENTE, PUNTOS = _PUNTOS
WHERE ID_CLIENTE = _ID_CLIENTE;
END$

CREATE PROCEDURE LISTAR_CLIENTES_POR_VENDEDOR(
	IN _ID_VENDEDOR INT)
BEGIN	
SELECT * FROM CLIENTE
INNER JOIN PROVINCIA ON  PROVINCIA.ID_PROVINCIA = CLIENTE.ID_PROVINCIA
INNER JOIN DEPARTAMENTO ON DEPARTAMENTO.ID_DEPARTAMENTO = PROVINCIA.ID_DEPARTAMENTO
WHERE CLIENTE_VENDEDOR.ID_VENDEDOR = _ID_VENDEDOR
AND CLIENTE.ID_CLIENTE = CLIENTE_VENDEDOR.ID_CLIENTE;
END$

CREATE PROCEDURE INSERTAR_CLIENTE_VENDEDOR(
	OUT _ID_CLIENTE_VENDEDOR INT,
    IN _ID_CLIENTE INT,
    IN _ID_VENDEDOR INT,
    IN _ACTIVE TINYINT(1))
BEGIN
INSERT INTO CLIENTE_VENDEDOR(ID_CLIENTE,ID_VENDEDOR,ACTIVE)
VALUES (_ID_CLIENTE,_ID_VENDEDOR,_ACTIVE);
SET _ID_CLIENTE_VENDEDOR = last_insert_id();
END$

CREATE PROCEDURE ELIMINAR_CLIENTE_VENDEDOR(
	IN _ID_CLIENTE_VENDEDOR INT)
BEGIN
UPDATE CLIENTE_VENDEDOR
SET ACTIVE = FALSE
WHERE ID_CLIENTE_VENDEDOR = _ID_CLIENTE_VENDEDOR;
END$

CREATE PROCEDURE INSERTAR_DATOS_GENERALES(
	OUT _ID_DATOS_GENERALES INT,
    IN _IGV FLOAT,
    IN _SUELDO_MINIMO FLOAT,
    IN _CORREO_EMPRESA VARCHAR(80),
    IN _CONTRASEÑA_EMPRESA VARCHAR(80),
    IN _FECHA DATE,
    IN _PLAZO_DE_PAGO INT,
    IN _ACTIVE BOOLEAN
)
BEGIN
INSERT INTO DATOS_GENERALES(IGV,SUELDO_MINIMO,CORREO_EMPRESA,CONTRASEÑA_EMPRESA,FECHA,PLAZO_DE_PAGO,ACTIVE)
VALUES (_IGV,_SUELDO_MINIMO,_CORREO_EMPRESA,_CONTRASEÑA_EMPRESA,_FECHA,_PLAZO_DE_PAGO,_ACTIVE);
SET _ID_DATOS_GENERALES = last_insert_id();
END$

CREATE PROCEDURE ENCONTRAR_DATOS_GENERALES_POR_FECHA(
	IN _FECHA DATE
)
BEGIN
SELECT * FROM DATOS_GENERALES
WHERE FECHA = _FECHA AND ACTIVE = TRUE;
END$


CREATE PROCEDURE INSERTAR_DEPARTAMENTO(
	OUT _ID_DEPARTAMENTO INT,
    IN _NOMBRE_DEPARTAMENTO VARCHAR(80),
    IN _ACTIVE TINYINT(1))
BEGIN
INSERT INTO DEPARTAMENTO(NOMBRE_DEPARTAMENTO,ACTIVE)
VALUES (_NOMBRE_DEPARTAMENTO,_ACTIVE);
SET _ID_DEPARTAMENTO = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_DEPARTAMENTO(
	IN _ID_DEPARTAMENTO INT,
    IN _NOMBRE_DEPARTAMENTO VARCHAR(80))
BEGIN
UPDATE DEPARTAMENTO
SET NOMBRE_DEPARTAMENTO = _NOMBRE_DEPARTAMENTO
WHERE ID_DEPARTAMENTO = _ID_DEPARTAMENTO;
END$


CREATE PROCEDURE ELIMINAR_DEPARTAMENTO(
	IN _ID_DEPARTAMENTO INT)
BEGIN
UPDATE DEPARTAMENTO
SET ACTIVE = FALSE
WHERE ID_DEPARTAMENTO = _ID_DEPARTAMENTO;
END$

CREATE PROCEDURE LISTAR_DEPARTAMENTO()
BEGIN
SELECT * FROM DEPARTAMENTO
WHERE ACTIVE = TRUE;
END$


CREATE PROCEDURE INSERTAR_EMPLEADO(
	OUT _ID_EMPLEADO INT,
    IN _DNI_EMPLEADO VARCHAR(8),
    IN _NOMBRE_EMPLEADO VARCHAR(80),
    IN _APELLIDO_PATERNO VARCHAR(80),
    IN _APELLIDO_MATERNO VARCHAR(80),
    IN _FECHA_NACIMIENTO DATE,
    IN _TELEFONO_EMPLEADO VARCHAR(10),
    IN _CORREO_EMPLEADO VARCHAR(80),
    IN _ESTADO_CIVIL ENUM('Soltero','Casado'),
    IN _SUELDO FLOAT,
    IN _ID_AREA INT,
    IN _FECHA_INGRESO DATE,
    IN _FOTO LONGBLOB,
    IN _ACTIVE TINYINT(1)
)
BEGIN
INSERT INTO EMPLEADO(NOMBRE_EMPLEADO, APELLIDO_PATERNO, APELLIDO_MATERNO, DNI_EMPLEADO,FECHA_NACIMIENTO,TELEFONO_EMPLEADO,
CORREO_EMPLEADO,ESTADO_CIVIL,SUELDO,ID_AREA,FECHA_INGRESO,FOTO,ACTIVE)
VALUES (_NOMBRE_EMPLEADO, _APELLIDO_PATERNO,_APELLIDO_MATERNO,_DNI_EMPLEADO,_FECHA_NACIMIENTO,_TELEFONO_EMPLEADO,
_CORREO_EMPLEADO,_ESTADO_CIVIL,_SUELDO,_ID_AREA,_FECHA_INGRESO,_FOTO,_ACTIVE);
SET _ID_EMPLEADO = last_insert_id();
END$



CREATE PROCEDURE ACTUALIZAR_EMPLEADO(
	IN _ID_EMPLEADO INT,
    IN _DNI_EMPLEADO VARCHAR(8),
    IN _NOMBRE_EMPLEADO VARCHAR(80),
    IN _APELLIDO_PATERNO VARCHAR(80),
    IN _APELLIDO_MATERNO VARCHAR(80),
    IN _FECHA_NACIMIENTO DATE,
    IN _TELEFONO_EMPLEADO VARCHAR(10),
    IN _CORREO_EMPLEADO VARCHAR(80),
    IN _ESTADO_CIVIL ENUM('Soltero','Casado'),
    IN _SUELDO FLOAT,
    IN _ID_AREA INT,
    IN _FOTO LONGBLOB,
    IN _FECHA_INGRESO DATE)
BEGIN
UPDATE EMPLEADO
SET DNI_EMPLEADO = _DNI_EMPLEADO, NOMBRE_EMPLEADO = _NOMBRE_EMPLEADO, APELLIDO_PATERNO = _APELLIDO_PATERNO,
APELLIDO_MATERNO = _APELLIDO_MATERNO, FECHA_NACIMIENTO = _FECHA_NACIMIENTO,TELEFONO_EMPLEADO = _TELEFONO_EMPLEADO,CORREO_EMPLEADO = _CORREO_EMPLEADO,
ESTADO_CIVIL = _ESTADO_CIVIL, SUELDO = _SUELDO, AREA = _ID_AREA, FECHA_INGRESO = _FECHA_INGRESO, FOTO = _FOTO
WHERE ID_EMPLEADO = _ID_EMPLEADO;
END$

CREATE PROCEDURE ELIMINAR_EMPLEADO(
	IN _ID_EMPLEADO INT)
BEGIN
UPDATE EMPLEADO
SET ACTIVE = FALSE
WHERE ID_EMPLEADO = _ID_EMPLEADO;
END$


CREATE PROCEDURE LISTAR_EMPLEADO()
BEGIN
SELECT * FROM EMPLEADO
INNER JOIN AREA on EMPLEADO.ID_AREA = AREA.ID_AREA
WHERE EMPLEADO.ACTIVE = TRUE;
END$


CREATE PROCEDURE LISTAR_EMPLEADO_POR_AREA(
	IN _ID_AREA INT)
BEGIN
SELECT * FROM EMPLEADO
INNER JOIN AREA ON EMPLEADO.ID_AREA = AREA.ID_AREA
WHERE EMPLEADO.ACTIVE = TRUE AND EMPLEADO.ID_AREA = _ID_AREA;
END$

CREATE PROCEDURE BUSCAR_EMPLEADO_POR_DNI(
	IN _DNI VARCHAR(8))
BEGIN
SELECT * FROM EMPLEADO
INNER JOIN AREA ON AREA.ID_AREA = EMPLEADO.ID_AREA
WHERE EMPLEADO.ACTIVE = TRUE
AND EMPLEADO.DNI_EMPLEADO = _DNI;
END$

CREATE PROCEDURE BUSCAR_EMPLEADO_POR_APELLIDOS(
	IN _APELLIDO_PATERNO VARCHAR(80),
    IN _APELLIDO_MATERNO VARCHAR(80))
BEGIN
SELECT * FROM EMPLEADO
INNER JOIN AREA ON AREA.ID_AREA = EMPLEADO.ID_AREA
WHERE EMPLEADO.ACTIVE = TRUE
AND EMPLEADO.APELLIDO_PATERNO = _APELLIDO_PATERNO AND EMPLEADO.APELLIDO_MATERNO = _APELLIDO_MATERNO;
END$

CREATE PROCEDURE BUSCAR_EMPLEADO_POR_CORREO(
	IN _CORREO VARCHAR(80))
BEGIN
SELECT * FROM EMPLEADO
INNER JOIN AREA ON AREA.ID_AREA = EMPLEADO.ID_AREA
WHERE EMPLEADO.ACTIVE = TRUE
AND EMPLEADO.CORREO_EMPLEADO = _CORREO;
END$

CREATE PROCEDURE INSERTAR_LINEA_PEDIDO(
	OUT _ID_LINEA_PEDIDO INT,
    IN _CANTIDAD FLOAT,
    IN _ID_PEDIDO INT,
    IN _ID_PRODUCTO INT,
    IN _SUBTOTAL FLOAT,
    IN _ESTADO_LINEA_PEDIDO ENUM('Aceptado','Rechazado','Pendiente'),
    IN _FECHA_ATENCION DATE,
    IN _CANTIDAD_A_ATENDER INT)
BEGIN
INSERT INTO LINEA_PEDIDO(CANTIDAD,ID_PEDIDO,ID_PRODUCTO,SUBTOTAL,ESTADO_LINEA_PEDIDO,FECHA_ATENCION,CANTIDAD_A_ATENDER)
VALUES(_CANTIDAD,_ID_PEDIDO,_ID_PRODUCTO,_SUBTOTAL,_ESTADO_LINEA_PEDIDO,_FECHA_ATENCION,_CANTIDAD_A_ATENDER);
SET _ID_LINEA_PEDIDO = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_LINEA_PEDIDO(
    IN _ID_LINEA_PEDIDO INT,
    IN _CANTIDAD FLOAT,
    IN _ID_PEDIDO INT,
    IN _ID_PRODUCTO INT,
    IN _SUBTOTAL FLOAT,
    IN _ESTADO_LINEA_PEDIDO ENUM('Aceptado','Rechazado','Pendiente'),
    IN _FECHA_ATENCION DATE,
    IN _CANTIDAD_A_ATENDER INT
)
BEGIN
UPDATE LINEA_PEDIDO
SET CANTIDAD = _CANTIDAD, ID_PEDIDO = _ID_PEDIDO, ID_PRODUCTO = _ID_PRODUCTO, SUBTOTAL = _SUBTOTAL,
ESTADO_LINEA_PEDIDO = _ESTADO_LINEA_PEDIDO, FECHA_ATENCION = _FECHA_ATENCION,
CANTIDAD_A_ATENDER = _CANTIDAD_A_ATENDER
WHERE ID_LINEA_PEDIDO = _ID_LINEA_PEDIDO;
END$

CREATE PROCEDURE LISTAR_LINEAS_PEDIDO(
	IN _ID_PEDIDO INT)
BEGIN
SELECT * FROM LINEA_PEDIDO
INNER JOIN PRODUCTO ON PRODUCTO.ID_PRODUCTO = LINEA_PEDIDO.ID_PRODUCTO
WHERE ID_PEDIDO = _ID_PEDIDO;
END$

CREATE PROCEDURE LISTAR_LINEAS_PEDIDO_POR_RANGO_DE_FECHAS(
	IN FECHA_INI DATE,
    IN FECHA_FIN DATE)
BEGIN
SELECT * FROM LINEA_PEDIDO
INNER JOIN PEDIDO ON PEDIDO.ID_PEDIDO = LINEA_PEDIDO.ID_PEDIDO
WHERE LINEA_PEDIDO.FECHA_ATENCION BETWEEN FECHA_INI AND FECHA_FIN;
END$

CREATE PROCEDURE LISTAR_LINEAS_PEDIDO_POR_PRODUCTO(
	IN _ID_PRODUCTO INT)
BEGIN
SELECT CANTIDAD, FECHA_ATENCION FROM LINEA_PEDIDO
INNER JOIN PRODUCTO ON LINEA_PEDIDO.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO
WHERE PRODUCTO.ID_PRODUCTO = _ID_PRODUCTO
AND LINEA_PEDIDO.ESTADO_LINEA_PEDIDO = 'Aceptado';
END$

CREATE PROCEDURE INSERTAR_META_MENSUAL(
	OUT _ID_META_MENSUAL INT,
    IN _FECHA_INICIO DATE,
    IN _FECHA_LIMITE DATE,
    IN _CANTIDAD_OBJETIVO FLOAT,
    IN _DESCRIPCION_META_MENSUAL VARCHAR(80),
    IN _ACTIVE TINYINT(1)
)
BEGIN
INSERT INTO META_MENSUAL(FECHA_INICIO,FECHA_LIMITE,CANTIDAD_OBJETIVO,DESCRIPCION_META_MENSUAL,ACTIVE)
VALUES (_FECHA_INICIO,_FECHA_LIMITE,_CANTIDAD_OBJETIVO,_DESCRIPCION_META_MENSUAL,_ACTIVE);
SET _ID_META_MENSUAL = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_META_MENSUAL(
	IN _ID_META_MENSUAL INT,
    IN _FECHA_INICIO DATE,
    IN _FECHA_LIMITE DATE,
    IN _CANTIDAD_OBJETIVO FLOAT,
    IN _DESCRIPCION_META_MENSUAL VARCHAR(80))
BEGIN
UPDATE META_MENSUAL
SET FECHA_INICIO = _FECHA_INICIO, FECHA_LIMITE = _FECHA_LIMITE,CANTIDAD_OBJETIVO = _CANTIDAD_OBJETIVO,
DESCRIPCION_META_MENSUAL = _DESCRIPCION_META_MENSUAL
WHERE ID_META_MENSUAL = _ID_META_MENSUAL;
END$


CREATE PROCEDURE ELIMINAR_META_MENSUAL(
	IN _ID_META_MENSUAL INT)
BEGIN
UPDATE META_MENSUAL
SET ACTIVE = FALSE
WHERE ID_META_MENSUAL = _ID_META_MENSUAL;
END$

CREATE PROCEDURE LISTAR_META_MENSUAL()
BEGIN
SELECT * FROM META_MENSUAL
WHERE ACTIVE = TRUE;
END$

CREATE PROCEDURE INSERTAR_OBJETIVO_VENDEDOR(
	OUT _ID_OBJETIVO_VENDEDOR INT,
    IN _ID_META_MENSUAL INT,
    IN _MONTO FLOAT,
    IN _COMISION FLOAT,
    IN _BONO FLOAT,
    IN _PROGRESO FLOAT,
    IN _ID_VENDEDOR INT,
    IN _ACTIVE TINYINT(1))
BEGIN
INSERT INTO OBJETIVO_VENDEDOR(ID_META_MENSUAL,MONTO,COMISION,BONO,ID_VENDEDOR,PROGRESO,ACTIVE)
VALUES(_ID_META_MENSUAL,_MONTO,_COMISION,_BONO,_ID_VENDEDOR,_PROGRESO,_ACTIVE);
SET _ID_OBJETIVO_VENDEDOR = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_OBJETIVO_VENDEDOR(
	IN _ID_OBJETIVO_VENDEDOR INT,
    IN _ID_META_MENSUAL INT,
    IN _MONTO FLOAT,
    IN _COMISION FLOAT,
    IN _BONO FLOAT,
    IN _PROGRESO FLOAT)
BEGIN
UPDATE OBJETIVO_VENDEDOR
SET ID_META_MENSUAL = _ID_META_MENSUAL, MONTO = _MONTO, COMISION = _COMISION, BONO = _BONO, PROGRESO = _PROGRESO;
END$

CREATE PROCEDURE LISTAR_OBJETIVO_VENDEDOR()
BEGIN
SELECT * FROM OBJETIVO_VENDEDOR
WHERE ACTIVE = TRUE;
END$

CREATE PROCEDURE ELIMINAR_OBJETIVO_VENDEDOR(
	IN _ID_OBJETIVO_VENDEDOR INT)
BEGIN
UPDATE OBJETIVO_VENDEDOR
SET ACTIVE = FALSE
WHERE ID_OBJETIVO_VENDEDOR = _ID_OBJETIVO_VENDEDOR;
END$

CREATE PROCEDURE INSERTAR_PEDIDO(
	OUT _ID_PEDIDO INT,
    IN _TOTAL_PEDIDO FLOAT,
    IN _ID_CLIENTE_VENDEDOR INT,
    IN _ESTADO_PEDIDO ENUM('Aceptado','Rechazado','Pendiente','Pagado'),
    IN _FECHA_REGISTRO DATE,
	IN _ACTIVE TINYINT(1))
BEGIN
INSERT INTO PEDIDO(TOTAL_PEDIDO,ID_CLIENTE_VENDEDOR,ESTADO_PEDIDO,FECHA_REGISTRO,ACTIVE)
VALUES(_TOTAL_PEDIDO,_ID_CLIENTE_VENDEDOR,_ESTADO_PEDIDO,_FECHA_REGISTRO,_ACTIVE);
SET _ID_PEDIDO = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_PEDIDO(
	IN _ID_PEDIDO INT,
    IN _TOTAL_PEDIDO FLOAT,
    IN _ESTADO_PEDIDO ENUM('Aceptado','Rechazado','Pendiente','Pagado'),
    IN _FECHA_REGISTRO DATE)
BEGIN
UPDATE PEDIDO
SET TOTAL_PEDIDO=_TOTAL_PEDIDO, ESTADO_PEDIDO = _ESTADO_PEDIDO, FECHA_REGISTRO = _FECHA_REGISTRO
WHERE ID_PEDIDO = _ID_PEDIDO;
END$

CREATE PROCEDURE ELIMINAR_PEDIDO(
	IN _ID_PEDIDO INT)
BEGIN
UPDATE PEDIDO
SET ACTIVE = FALSE
WHERE ID_PEDIDO = _ID_PEDIDO;
END$


CREATE PROCEDURE LISTAR_PEDIDO_POR_VENDEDOR(
	IN _ID_VENDEDOR INT)
BEGIN
SELECT PEDIDO.ID_PEDIDO, PEDIDO.TOTAL_PEDIDO, PEDIDO.ID_CLIENTE_VENDEDOR, PEDIDO.ESTADO_PEDIDO, PEDIDO.FECHA_REGISTRO, PEDIDO.ACTIVE FROM PEDIDO, CLIENTE_VENDEDOR
WHERE CLIENTE_VENDEDOR.ID_CLIENTE_VENDEDOR = PEDIDO.ID_CLIENTE_VENDEDOR 
AND CLIENTE_VENDEDOR.ID_VENDEDOR = _ID_VENDEDOR
AND PEDIDO.ACTIVE = TRUE;
END$


CREATE PROCEDURE LISTAR_PEDIDO_POR_VENDEDOR_POR_FECHAS(
	IN _FECHA_INI DATE,
    IN _FECHA_FIN DATE,
	IN _ID_VENDEDOR INT)
BEGIN
SELECT PEDIDO.ID_PEDIDO, PEDIDO.TOTAL_PEDIDO, PEDIDO.ID_CLIENTE_VENDEDOR, PEDIDO.ESTADO_PEDIDO, PEDIDO.FECHA_REGISTRO, PEDIDO.ACTIVE FROM PEDIDO, CLIENTE_VENDEDOR
WHERE CLIENTE_VENDEDOR.ID_VENDEDOR = _ID_VENDEDOR
AND PEDIDO.ID_CLIENTE_VENDEDOR = CLIENTE_VENDEDOR.ID_CLIENTE_VENDEDOR
AND PEDIDO.FECHA_REGISTRO BETWEEN _FECHA_INI AND _FECHA_FIN;
END$

CREATE PROCEDURE LISTAR_PEDIDO_POR_CLIENTE(
	IN _ID_CLIENTE INT)
BEGIN
SELECT PEDIDO.ID_PEDIDO, PEDIDO.TOTAL_PEDIDO, PEDIDO.ID_CLIENTE_VENDEDOR, PEDIDO.ESTADO_PEDIDO, PEDIDO.FECHA_REGISTRO, PEDIDO.ACTIVE FROM PEDIDO, CLIENTE_VENDEDOR
WHERE CLIENTE_VENDEDOR.ID_CLIENTE = _ID_CLIENTE
AND PEDIDO.ID_CLIENTE_VENDEDOR = CLIENTE_VENDEDOR.ID_CLIENTE_VENDEDOR;
END$

CREATE PROCEDURE LISTAR_PEDIDO_POR_ESTADO()
BEGIN
SELECT * FROM PEDIDO
ORDER BY ESTADO_PEDIDO;
END$

CREATE PROCEDURE INSERTAR_PRODUCTO(
	OUT _ID_PRODUCTO INT,
    IN _NOMBRE_PRODUCTO VARCHAR(80),
    IN _STOCK_EMPRESA INT,
    IN _STOCK_VENDEDOR INT,
    IN _PRECIO_UNITARIO FLOAT,
    IN _DESCRIPCION VARCHAR(80),
    IN _FOTO LONGBLOB,
    IN _ACTIVE TINYINT(1)
    )
BEGIN
INSERT INTO PRODUCTO(NOMBRE_PRODUCTO,STOCK_EMPRESA,STOCK_VENDEDOR,PRECIO_UNITARIO,DESCRIPCION,FOTO,ACTIVE)
VALUES (_NOMBRE_PRODUCTO,_STOCK_EMPRESA,_STOCK_VENDEDOR,_PRECIO_UNITARIO,_DESCRIPCION,_FOTO,_ACTIVE);
SET _ID_PRODUCTO = last_insert_id();
END$

CREATE PROCEDURE INSERTAR_PRESENTACION(
	OUT _ID_PRESENTACION INT,
    IN _ID_PRODUCTO INT,
    IN _DISEÑO VARCHAR(80),
    IN _ACTIVE TINYINT(1)
)
BEGIN
INSERT INTO PRESENTACION(ID_PRODUCTO,DISEÑO,ACTIVE)
VALUES (_ID_PRODUCTO,_DISEÑO,_ACTIVE);
SET _ID_PRESENTACION = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_PRESENTACION(
	IN _ID_PRESENTACION INT,
    IN _DISEÑO VARCHAR(80)
)
BEGIN
UPDATE PRESENTACION
SET DISEÑO = _DISEÑO
WHERE ID_PRESENTACION = _ID_PRESENTACION;
END$

CREATE PROCEDURE ELIMINAR_PRESENTACION(
	IN _ID_PRESENTACION INT
)
BEGIN
UPDATE PRESENTACION
SET ACTIVE = FALSE
WHERE ID_PRESENTACION = _ID_PRESENTACION;
END$

CREATE PROCEDURE LISTAR_PRESENTACION(
	IN _ID_PRODUCTO INT
)
BEGIN
SELECT * FROM PRESENTACION
WHERE ID_PRODUCTO = _ID_PRODUCTO AND ACTIVE = TRUE;
END$

CREATE PROCEDURE ACTUALIZAR_PRODUCTO(
	IN _ID_PRODUCTO INT,
    IN _NOMBRE_PRODUCTO VARCHAR(80),
    IN _STOCK_EMPRESA INT,
    IN _STOCK_VENDEDOR INT,
    IN _PRECIO_UNITARIO FLOAT,
    IN _FOTO LONGBLOB,
    IN _DESCRIPCION VARCHAR(80))
BEGIN
UPDATE PRODUCTO
SET NOMBRE_PRODUCTO = _NOMBRE_PRODUCTO, STOCK_EMPRESA = _STOCK_EMPRESA, STOCK_VENDEDOR = _STOCK_VENDEDOR,
PRECIO_UNITARIO = _PRECIO_UNITARIO, DESCRIPCION = _DESCRIPCION, FOTO = _FOTO
WHERE ID_PRODUCTO = _ID_PRODUCTO;
END$


CREATE PROCEDURE ELIMINAR_PRODUCTO(
	IN _ID_PRODUCTO INT)
BEGIN
UPDATE PRODUCTO
SET ACTIVE = FALSE
WHERE ID_PRODUCTO = _ID_PRODUCTO;
END$


CREATE PROCEDURE LISTAR_PRODUCTO_DISPONIBLE()
BEGIN
SELECT * FROM PRODUCTO
WHERE STOCK_EMPRESA > 0 AND ACTIVE = TRUE;
END$

CREATE PROCEDURE BUSCAR_PRODUCTO_POR_NOMBRE(
	IN _NOMBRE_PRODUCTO VARCHAR(80))
BEGIN
SELECT * FROM PRODUCTO
WHERE NOMBRE_PRODUCTO = _NOMBRE_PRODUCTO AND ACTIVE = TRUE;
END$

CREATE PROCEDURE INSERTAR_PROVINCIA(
	OUT _ID_PROVINCIA INT,
    IN _NOMBRE_PROVINCIA VARCHAR(80),
    IN _ID_DEPARTAMENTO INT,
    IN _ACTIVE TINYINT(1))
BEGIN
INSERT INTO PROVINCIA(NOMBRE_PROVINCIA,ID_DEPARTAMENTO,ACTIVE)
VALUES (_NOMBRE_PROVINCIA,_ID_DEPARTAMENTO,_ACTIVE);
SET _ID_PROVINCIA = last_insert_id();
END$


CREATE PROCEDURE ACTUALIZAR_PROVINCIA(
	IN _ID_PROVINCIA INT,
    IN _NOMBRE_PROVINCIA VARCHAR(80),
    IN _ID_DEPARTAMENTO INT)
BEGIN
UPDATE PROVINCIA
SET NOMBRE_PROVINCIA = _NOMBRE_PROVINCIA, ID_DEPARTAMENTO = _ID_DEPARTAMENTO
WHERE ID_PROVINCIA = _ID_PROVINCIA;
END$

CREATE PROCEDURE ELIMINAR_PROVINCIA(
	IN _ID_PROVINCIA INT)
BEGIN
UPDATE PROVINCIA
SET ACTIVE = FALSE
WHERE ID_PROVINCIA = _ID_PROVINCIA;
END$

CREATE PROCEDURE LISTAR_PROVINCIA_POR_DEPARTAMENTO(
	IN _ID_DEPARTAMENTO INT)
BEGIN
SELECT * FROM PROVINCIA
INNER JOIN DEPARTAMENTO ON DEPARTAMENTO.ID_DEPARTAMENTO = _ID_DEPARTAMENTO
WHERE PROVINCIA.ACTIVE = TRUE;
END$


CREATE PROCEDURE INSERTAR_SOLICITUD(
	OUT _ID_SOLICITUD INT,
    IN _ESTADO_SOLICITUD ENUM('Aceptado','Rechazado','Pendiente'),
    IN _ID_PEDIDO INT,
    IN _FECHA_REGISTRO DATE,
    IN _ACTIVE TINYINT(1))
BEGIN
INSERT INTO SOLICITUD(ESTADO_SOLICITUD,ID_PEDIDO,FECHA_REGISTRO,ACTIVE)
VALUES (_ESTADO_SOLICITUD,_ID_PEDIDO,_FECHA_REGISTRO,_ACTIVE);
SET _ID_SOLICITUD = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_SOLICITUD(
	IN _ID_SOLICITUD INT,
    IN _ESTADO_SOLICITUD ENUM('Aceptado','Rechazado','Pendiente'),
    IN _ID_PEDIDO INT,
    IN _FECHA_REGISTRO DATE)
BEGIN
UPDATE SOLICITUD
SET ESTADO_SOLICITUD = _ESTADO_SOLICITUD, ID_PEDIDO = _ID_PEDIDO,
FECHA_REGISTRO = _FECHA_REGISTRO
WHERE ID_SOLICITUD = _ID_SOLICITUD;
END$

CREATE PROCEDURE ELIMINAR_SOLICITUD(
	IN _ID_SOLICITUD INT)
BEGIN
UPDATE SOLICITUD
SET ACTIVE = FALSE
WHERE ID_SOLICITUD = _ID_SOLICITUD;
END$

CREATE PROCEDURE INSERTAR_LINEA_SOLICITUD(
	OUT _ID_LINEA_SOLICITUD INT,
	IN _ID_SOLICITUD INT,
    IN _ID_LINEA_PEDIDO INT,
    IN _CANTIDAD INT,
    IN _ESTADO_SOLICITUD ENUM('Aceptado','Rechazado','Pendiente'),
    IN _ACTIVE TINYINT(1))
BEGIN
INSERT INTO LINEA_SOLICITUD(ID_SOLICITUD,ID_LINEA_PEDIDO,CANTIDAD,ESTADO_LINEA_SOLICITUD,ACTIVE)
VALUES(_ID_SOLICITUD,_ID_LINEA_PEDIDO,_CANTIDAD,_ESTADO_SOLICITUD,_ACTIVE);
SET _ID_LINEA_SOLICITUD = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_LINEA_SOLICITUD(
	OUT _ID_LINEA_SOLICITUD INT,
	IN _ID_SOLICITUD INT,
    IN _ID_LINEA_PEDIDO INT,
    IN _CANTIDAD INT,
    IN _ESTADO_SOLICITUD ENUM('Aceptado','Rechazado','Pendiente'))
BEGIN
UPDATE LINEA_SOLICITUD
SET ID_SOLICITUD = _ID_SOLICITUD, ID_LINEA_PEDIDO = _ID_LINEA_PEDIDO,
CANTIDAD = _CANTIDAD, ESTADO_LINEA_SOLICITUD = _ESTADO_SOLICITUD;
END$

CREATE PROCEDURE LISTAR_LINEAS_SOLICITUD(
	IN _ID_SOLICITUD INT)
BEGIN
SELECT LINEA_SOLICITUD.ID_LINEA_SOLICITUD, LINEA_SOLICITUD.ESTADO_LINEA_SOLICITUD,
LINEA_PEDIDO.CANTIDAD, LINEA_PEDIDO.CANTIDAD_A_ATENDER, LINEA_PEDIDO.ESTADO_LINEA_PEDIDO,
PRODUCTO.NOMBRE_PRODUCTO, PRODUCTO.DESCRIPCION FROM LINEA_SOLICITUD, LINEA_PEDIDO,PRODUCTO
WHERE LINEA_SOLICITUD.ID_SOLICITUD = _ID_SOLICITUD
AND LINEA_PEDIDO.ID_LINEA_PEDIDO = LINEA_SOLICITUD.ID_LINEA_PEDIDO
AND PRODUCTO.ID_PRODUCTO = LINEA_PEDIDO.ID_PRODUCTO;
END$

CREATE PROCEDURE LISTAR_SOLICITUD()
BEGIN
SELECT * FROM SOLICITUD
WHERE ACTIVE = TRUE;
END$

CREATE PROCEDURE LISTAR_SOLICITUDES_POR_ESTADO(
	IN _ESTADO_SOLICITUD ENUM('Aceptado','Rechazado','Pendiente'))
BEGIN
SELECT * FROM SOLICITUD
WHERE ACTIVE = TRUE
AND ESTADO_LINEA_SOLICITUD = _ESTADO_SOLICITUD;
END$

CREATE PROCEDURE BUSCAR_SOLICITUD_POR_PEDIDO(
	IN _ID_PEDIDO INT)
BEGIN
SELECT s.ID_SOLICITUD, s.ESTADO_SOLICITUD, s.FECHA_REGISTRO
FROM SOLICITUD s, PEDIDO p
WHERE p.ID_PEDIDO = _ID_PEDIDO;
END$


CREATE PROCEDURE INSERTAR_USUARIO(	
    OUT _ID_USUARIO INT,
    IN _ID_EMPLEADO INT,
    IN _NOMBRE_USUARIO VARCHAR(80),
    IN _CONTRASEÑA VARCHAR(80))
BEGIN
	UPDATE EMPLEADO SET NOMBRE_USUARIO = _NOMBRE_USUARIO AND CONTRASEÑA = _CONTRASEÑA
	WHERE ID_EMPLEADO = _ID_EMPLEADO AND ACTIVE = 1;
	SET _ID_USUARIO = _ID_EMPLEADO;
END$

CREATE PROCEDURE ACTUALIZAR_USUARIO(
	IN _ID_USUARIO INT,
	IN _NOMBRE_USUARIO VARCHAR(80),
    IN _CONTRASEÑA VARCHAR(80)
)
BEGIN
	UPDATE EMPLEADO
    SET NOMBRE_USUARIO = _NOMBRE_USUARIO, CONTRASEÑA = _CONTRASEÑA
    WHERE ID_USUARIO = _ID_USUARIO;
END$

CREATE PROCEDURE ELIMINAR_USUARIO(
	IN _ID_USUARIO INT
)
BEGIN
	UPDATE EMPLEADO
	SET NOMBRE_USUARIO = NULL, CONTRASEÑA = NULL
	WHERE ID_EMPLEADO = _ID_USUARIO;
END$


CREATE PROCEDURE LISTAR_USUARIO()
BEGIN
SELECT * FROM USUARIO
INNER JOIN EMPLEADO on EMPLEADO.ID_EMPLEADO = USUARIO.ID_EMPLEADO
WHERE USUARIO.ACTIVE = TRUE;
END$

CREATE PROCEDURE BUSCAR_AREA_POR_USUARIO(
        IN	_NOMBRE_USUARIO VARCHAR(80))
BEGIN
SELECT NOMBRE_AREA FROM USUARIO,EMPLEADO,AREA
WHERE USUARIO.NOMBRE_USUARIO = _NOMBRE_USUARIO
AND EMPLEADO.ID_EMPLEADO = USUARIO.ID_USUARIO
AND AREA.ID_AREA = EMPLEADO.ID_AREA;
END$

CREATE PROCEDURE BUSCAR_USUARIO_POR_EMPLEADO(
	IN _DNI_EMPLEADO VARCHAR(8))
BEGIN
SELECT * FROM USUARIO,EMPLEADO
WHERE EMPLEADO.DNI_EMPLEADO = _DNI_EMPLEADO
AND USUARIO.ID_EMPLEADO = EMPLEADO.ID_EMPLEADO;
END$

CREATE PROCEDURE BUSCAR_USUARIO(
	IN _NOMBRE_USUARIO VARCHAR(80),
    IN _CONTRASEÑA VARCHAR(80))
BEGIN
SELECT NOMBRE_USUARIO, CONTRASEÑA FROM EMPLEADO
WHERE EMPLEADO.NOMBRE_USUARIO = _NOMBRE_USUARIO
AND EMPLEADO.CONTRASEÑA = _CONTRASEÑA
AND EMPLEADO.ACTIVE = 1;
END$


CREATE PROCEDURE LISTAR_PEDIDOS_DE_VENDEDOR_POR_RANGO_DE_FECHAS(
	IN _FECHA_INI DATE,
    IN _FECHA_FIN DATE,
    IN _ID_VENDEDOR INT)
BEGIN
SELECT * FROM PEDIDO
INNER JOIN CLIENTE ON CLIENTE_VENDEDOR.ID_CLIENTE_VENDEDOR = PEDIDO.ID_CLIENTE_VENDEDOR
WHERE CLIENTE_VENDEDOR.ID_VENDEDOR = _ID_VENDEDOR AND PEDIDO.FECHA_REGISTRO BETWEEN _FECHA_INI AND _FECHA_FIN;
END$

CREATE PROCEDURE BUSCAR_EMPLEADO_POR_ID(
			IN _ID_EMPLEADO INT)
BEGIN
			SELECT * FROM EMPLEADO
            INNER JOIN AREA ON EMPLEADO.ID_AREA = AREA.ID_AREA
            WHERE EMPLEADO.ACTIVE = TRUE AND EMPLEADO.ID_EMPLEADO = _ID_EMPLEADO;
END$      

CREATE PROCEDURE BUSCAR_VENDEDOR_POR_ID(
			IN _ID_VENDEDOR INT)
BEGIN
			SELECT * FROM EMPLEADO
            INNER JOIN AREA ON EMPLEADO.ID_AREA = AREA.ID_AREA
            AND AREA.NOMBRE_AREA = "VENTAS"
            WHERE EMPLEADO.ACTIVE = TRUE AND EMPLEADO.ID_EMPLEADO = _ID_EMPLEADO;
END$ 

CREATE PROCEDURE BUSCAR_CLIENTE_POR_ID(
			IN _ID_CLIENTE INT)
BEGIN
			SELECT * FROM CLIENTE
            INNER JOIN PROVINCIA ON CLIENTE.ID_PROVINCIA = PROVINCIA.ID_PROVINCIA
            INNER JOIN DEPARTAMENTO ON PROVINCIA.ID_DEPARTAMENTO = DEPARTAMENTO.ID_DEPARTAMENTO
            WHERE CLIENTE.ACTIVE = TRUE AND CLIENTE.ID_CLIENTE = _ID_CLIENTE;
END$ 


CREATE PROCEDURE BUSCAR_PEDIDO_POR_ID(
			IN _ID_PEDIDO INT)
BEGIN
SELECT * FROM PEDIDO
WHERE PEDIDO.ACTIVE = TRUE AND PEDIDO.ID_PEDIDO = _ID_PEDIDO;
END$   

DROP PROCEDURE IF EXISTS BUSCAR_SOLICITUD_POR_ID;
CREATE PROCEDURE BUSCAR_SOLICITUD_POR_ID(
			IN _ID_SOLICITUD INT)
BEGIN
			SELECT * FROM SOLICITUD
            INNER JOIN PEDIDO ON SOLICITUD.ID_PEDIDO = PEDIDO.ID_PEDIDO
            WHERE SOLICITUD.ACTIVE = TRUE AND SOLICITUD.ID_SOLICITUD = _ID_SOLICITUD;
END$ 

CREATE PROCEDURE BUSCAR_PRODUCTO_POR_ID(
			IN _ID_PRODUCTO INT)
BEGIN
			SELECT * FROM PRODUCTO
            WHERE ACTIVE = TRUE AND ID_PRODUCTO = _ID_PRODUCTO;
END$ 

CREATE PROCEDURE BUSCAR_AREA_POR_ID(
			IN _ID_AREA INT)
BEGIN
			SELECT * FROM AREA
            WHERE ACTIVE = TRUE AND ID_AREA = _ID_AREA;
END$ 

CREATE PROCEDURE BUSCAR_DATOS_GENERALES_POR_ID(
			IN _ID_DATOS_GENERALES INT)
BEGIN
			SELECT * FROM DATOS_GENERALES
            WHERE ACTIVE = TRUE AND ID_DATOS_GENERALES = _ID_DATOS_GENERALES;
END$ 

CREATE PROCEDURE BUSCAR_PROVINCIA_POR_ID(
			IN _ID_PROVINCIA INT)
BEGIN
			SELECT * FROM PROVINCIA
            INNER JOIN DEPARTAMENTO ON PROVINCIA.ID_DEPARTAMENTO = DEPARTAMENTO.ID_DEPARTAMENTO
            WHERE PROVINCIA.ACTIVE = TRUE AND PROVINCIA.ID_PROVINCIA = _ID_PROVINCIA;
END$

CREATE PROCEDURE BUSCAR_DEPARTAMENTO_POR_ID(
			IN _ID_DEPARTAMENTO INT)
BEGIN
			SELECT * FROM DEPARTAMENTO
            WHERE ACTIVE = TRUE AND ID_DEPARTAMENTO = _ID_DEPARTAMENTO;
END$

CREATE PROCEDURE BUSCAR_OBJETIVO_VENDEDOR_POR_ID(
			IN _ID_OBJETIVO_VENDEDOR INT)
BEGIN
			SELECT * FROM OBJETIVO_VENDEDOR
            INNER JOIN META_MENSUAL ON OBJETIVO_VENDEDOR.ID_META_MENSUAL = META_MENSUAL.ID_META_MENSUAL
            WHERE OBJETIVO_VENDEDOR.ACTIVE = TRUE AND OBJETIVO_VENDEDOR.ID_OBJETIVO_VENDEDOR = _ID_OBJETIVO_VENDEDOR;
END$

CREATE PROCEDURE BUSCAR_META_MENSUAL_POR_ID(
			IN _ID_META_MENSUAL INT)
BEGIN
			SELECT * FROM META_MENSUAL
            WHERE ACTIVE = TRUE AND ID_META_MENSUAL = _ID_META_MENSUAL;
END$

CREATE PROCEDURE LISTAR_PROVINCIAS()
BEGIN
SELECT * FROM PROVINCIA
WHERE ACTIVE = TRUE;
END$

CREATE PROCEDURE ELIMINAR_CLIENTE(
		IN _ID_CLIENTE INT)
BEGIN
	DROP TABLE CLIENTE; 
END$

CREATE PROCEDURE LISTAR_CLIENTES()
BEGIN
	SELECT * FROM CLIENTE C
    INNER JOIN PROVINCIA P ON C.ID_PROVINCIA = P.ID_PROVINCIA
    INNER JOIN DEPARTAMENTO D ON P.ID_DEPARTAMENTO = D.ID_DEPARTAMENTO
    WHERE C.ACTIVE = TRUE;
END$

CREATE PROCEDURE LISTAR_PRODUCTOS()
BEGIN
	SELECT * FROM PRODUCTO
    WHERE ACTIVE = TRUE;
END$


CREATE PROCEDURE LISTAR_CLIENTES_POR_NOMBRE(
	IN _RAZON_SOCIAL VARCHAR(80)
)
BEGIN
SELECT * FROM CLIENTE
WHERE CLIENTE.ACTIVE = TRUE AND (RAZON_SOCIAL LIKE CONCAT('%',_RAZON_SOCIAL,'%'));
END$

CREATE PROCEDURE BUSCAR_CLIENTE_POR_FILTRO(
	IN _FILTRO VARCHAR(80))
BEGIN
SELECT * FROM CLIENTE
WHERE RUC LIKE CONCAT('%',_FILTRO,'%') OR RAZON_SOCIAL LIKE CONCAT('%',_FILTRO,'%');
END$

CREATE PROCEDURE BUSCAR_RELACION_CLIENTE_VENDEDOR(
	IN _ID_CLIENTE INT,
    IN _ID_VENDEDOR INT)
BEGIN
SELECT * FROM CLIENTE_VENDEDOR
WHERE ID_CLIENTE = _ID_CLIENTE
AND ID_VENDEDOR = _ID_VENDEDOR;
END$

CREATE PROCEDURE LISTAR_PRODUCTOS_POR_NOMBRE(
	IN _NOMBRE_PRODUCTO VARCHAR(80))
BEGIN
SELECT * FROM PRODUCTO
WHERE NOMBRE_PRODUCTO LIKE CONCAT('%',_NOMBRE_PRODUCTO,'%')
AND ACTIVE = true;
END$

CREATE PROCEDURE BUSCAR_CLIENTE_VENDEDOR(
	IN _ID_CLIENTE_VENDEDOR INT)
BEGIN
SELECT * FROM CLIENTE_VENDEDOR
WHERE ID_CLIENTE_VENDEDOR = _ID_CLIENTE_VENDEDOR
AND ACTIVE = 1;
END$