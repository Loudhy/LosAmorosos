USE lp2;
DROP PROCEDURE IF EXISTS INSERTAR_AREA;
DROP PROCEDURE IF EXISTS ACTUALIZAR_AREA;
DROP PROCEDURE IF EXISTS ELIMINAR_AREA;
DROP PROCEDURE IF EXISTS LISTAR_AREA;
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
SELECT * FROM AREA
WHERE ID_AREA=true;
END$