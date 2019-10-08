DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_META_MENSUAL;
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

DROP PROCEDURE IF EXISTS ACTUALIZAR_META_MENSUAL;
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

DROP PROCEDURE IF EXISTS ELIMINAR_META_MENSUAL;
CREATE PROCEDURE ELIMINAR_META_MENSUAL(
	IN _ID_META_MENSUAL INT)
BEGIN
UPDATE META_MENSUAL
SET ACTIVE = FALSE
WHERE ID_META_MENSUAL = _ID_META_MENSUAL;
END$

DROP PROCEDURE IF EXISTS LISTAR_META_MENSUAL;
CREATE PROCEDURE LISTAR_META_MENSUAL()
BEGIN
SELECT * FROM META_MENSUAL
WHERE ACTIVE = TRUE;
END$