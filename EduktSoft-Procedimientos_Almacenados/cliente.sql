DROP PROCEDURE IF EXISTS INSERTAR_CLIENTE;
DROP PROCEDURE IF EXISTS ACTUALIZAR_CLIENTE;
DROP PROCEDURE IF EXISTS LISTAR_CLIENTES_POR_VENDEDOR;
DELIMITER $
CREATE PROCEDURE INSERTAR_CLIENTE(
	OUT _ID_CLIENTE INT,
    IN _RUC VARCHAR(11),
    IN _RAZON_SOCIAL VARCHAR(80),
    IN _CORREO_CLIENTE VARCHAR(80),
    IN _ID_PROVINCIA INT,
    IN _DIRECCION VARCHAR(80),
    IN _TELEFONO_CLIENTE VARCHAR(80),
    IN _ACTIVE TINYINT(1)
)
BEGIN
INSERT INTO CLIENTE(RUC, RAZON_SOCIAL,CORREO_CLIENTE,ID_PROVINCIA, DIRECCION,TELEFONO_CLIENTE,ACTIVE)
VALUES (_RUC,_RAZON_SOCIAL,_CORREO_CLIENTE,_ID_PROVINCIA,_DIRECCION,_TELEFONO_CLIENTE,_ACTIVE);
SET _ID_CLIENTE = last_insert_id();
END$

CREATE PROCEDURE ACTUALIZAR_CLIENTE(
	IN _ID_CLIENTE INT,
    IN _RUC VARCHAR(11),
    IN _RAZON_SOCIAL VARCHAR(80),
    IN _CORREO_CLIENTE VARCHAR(80),
    IN _ID_PROVINCIA INT,
    IN _DIRECCION VARCHAR(80),
    IN _TELEFONO_CLIENTE VARCHAR(80)
)
BEGIN
UPDATE CLIENTE
SET RUC = _RUC, RAZON_SOCIAL = _RAZON_SOCIAL, CORREO_CLIENTE = _CORREO_CLIENTE,
ID_PROVINCIA = _ID_PROVICINA, DIRECCION = _DIRECCION , TELEFONO_CLIENTE = _TELEFONO_CLIENTE
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