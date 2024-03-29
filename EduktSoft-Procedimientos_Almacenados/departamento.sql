DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_DEPARTAMENTO;
CREATE PROCEDURE INSERTAR_DEPARTAMENTO(
	OUT _ID_DEPARTAMENTO INT,
    IN _NOMBRE_DEPARTAMENTO VARCHAR(80),
    IN _ACTIVE TINYINT(1))
BEGIN
INSERT INTO DEPARTAMENTO(NOMBRE_DEPARTAMENTO,ACTIVE)
VALUES (_NOMBRE_DEPARTAMENTO,_ACTIVE);
SET _ID_DEPARTAMENTO = last_insert_id();
END$

DROP PROCEDURE IF EXISTS ACTUALIZAR_DEPARTAMENTO;
CREATE PROCEDURE ACTUALIZAR_DEPARTAMENTO(
	IN _ID_DEPARTAMENTO INT,
    IN _NOMBRE_DEPARTAMENTO VARCHAR(80))
BEGIN
UPDATE DEPARTAMENTO
SET NOMBRE_DEPARTAMENTO = _NOMBRE_DEPARTAMENTO
WHERE ID_DEPARTAMENTO = _ID_DEPARTAMENTO;
END$

DROP PROCEDURE IF EXISTS ELIMINAR_DEPARTAMENTO;
CREATE PROCEDURE ELIMINAR_DEPARTAMENTO(
	IN _ID_DEPARTAMENTO INT)
BEGIN
UPDATE DEPARTAMENTO
SET ACTIVE = FALSE
WHERE ID_DEPARTAMENTO = _ID_DEPARTAMENTO;
END$

DROP PROCEDURE IF EXISTS LISTAR_DEPARTAMENTO;
CREATE PROCEDURE LISTAR_DEPARTAMENTO()
BEGIN
SELECT * FROM DEPARTAMENTO
WHERE ACTIVE = TRUE;
END$