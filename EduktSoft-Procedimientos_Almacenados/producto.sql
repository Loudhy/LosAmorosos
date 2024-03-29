DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_PRODUCTO;
DROP PROCEDURE IF EXISTS ACTUALIZAR_PRODUCTO;
DROP PROCEDURE IF EXISTS ELIMINAR_PRODUCTO;
DROP PROCEDURE IF EXISTS LISTAR_PRODUCTO_DISPONIBLE;
DROP PROCEDURE IF EXISTS BUSCAR_PRODUCTO_POR_NOMBRE;
CREATE PROCEDURE INSERTAR_PRODUCTO(
	OUT _ID_PRODUCTO INT,
    IN _NOMBRE_PRODUCTO VARCHAR(80),
    IN _STOCK_EMPRESA INT,
    IN _STOCK_VENDEDOR INT,
    IN _PRECIO_UNITARIO FLOAT,
    IN _DESCRIPCION VARCHAR(80),
    IN _ACTIVE TINYINT(1)
    )
BEGIN
INSERT INTO PRODUCTO(NOMBRE_PRODUCTO,STOCK_EMPRESA,STOCK_VENDEDOR,PRECIO_UNITARIO,DESCRIPCION,ACTIVE)
VALUES (_NOMBRE_PRODUCTO,_STOCK_EMPRESA,_STOCK_VENDEDOR,_PRECIO_UNITARIO,_DESCRIPCION,_ACTIVE);
SET _ID_PRODUCTO = last_insert_id();
END$


CREATE PROCEDURE ACTUALIZAR_PRODUCTO(
	IN _ID_PRODUCTO INT,
    IN _NOMBRE_PRODUCTO VARCHAR(80),
    IN _STOCK_EMPRESA INT,
    IN _STOCK_VENDEDOR INT,
    IN _PRECIO_UNITARIO FLOAT,
    IN _DESCRIPCION VARCHAR(80))
BEGIN
UPDATE PRODUCTO
SET NOMBRE_PRODUCTO = _NOMBRE_PRODUCTO, STOCK_EMPRESA = _STOCK_EMPRESA, STOCK_VENDEDOR = _STOCK_VENDEDOR,
PRECIO_UNITARIO = _PRECIO_UNITARIO, DESCRIPCION = _DESCRIPCION
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