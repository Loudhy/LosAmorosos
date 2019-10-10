DROP PROCEDURE IF EXISTS INSERTAR_LINEA_PEDIDO;
DROP PROCEDURE IF EXISTS ACTUALIZAR_LINEA_PEDIDO;
DROP PROCEDURE IF EXISTS LISTAR_LINEAS_PEDIDO;
DROP PROCEDURE IF EXISTS LISTAR_LINEAS_PEDIDO_POR_RANGO_DE_FECHAS;
DROP PROCEDURE IF EXISTS LISTAR_LINEAS_PEDIDO_POR_PRODUCTO;
DELIMITER $
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