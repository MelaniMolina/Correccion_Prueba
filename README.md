## Capturas del Programa Funcional
![Captura de pantalla (29243)](https://user-images.githubusercontent.com/113868310/221300411-81aff206-380f-4d30-aea0-a0e872c0af74.png)
![Captura de pantalla (29244)](https://user-images.githubusercontent.com/113868310/221300589-bfcf5484-a5cf-444f-a8f0-68e3dc0d4377.png)
![Captura de pantalla (29245)](https://user-images.githubusercontent.com/113868310/221300594-1726e9f5-a566-418e-8689-b574e6495193.png)
![Captura de pantalla (29246)](https://user-images.githubusercontent.com/113868310/221300596-abde9e95-63fe-4072-896f-6d415e5691c8.png)
![Captura de pantalla (29247)](https://user-images.githubusercontent.com/113868310/221300597-6fa75eb7-23c3-43f2-948e-f18b8e5b214d.png)
![Captura de pantalla (29248)](https://user-images.githubusercontent.com/113868310/221300601-e25cfe91-5580-4a4e-8d20-9806c8ac5581.png)

## Script de mysql
CREATE DATABASE TIENDAS_ABARROTES

USE  TIENDAS_ABARROTES

 CREATE TABLE PRODUCTOS (
 COD_PRO VARCHAR (5) PRIMARY KEY NOT NULL,
 NOM_PROD VARCHAR(15) NOT NULL,
 UNI_VENDIDA NUMERIC(3) NOT NULL,
 FECHA_CADUCE NUMERIC(5),
 NOMBRE_PROVEEDOR VARCHAR(15),
 PRECIO FLOAT(4,3) 
)

INSERT INTO PRODUCTOS VALUES ('MIL','Leche','2','2026','Mario','2.70')
INSERT INTO PRODUCTOS VALUES ('CERE','Chocapic','3','2028','Luigui','6.70')
INSERT INTO PRODUCTOS VALUES ('CANDY','Caramelos ZOON','1','2020','Wario','1.70')

CREATE TABLE FECHA_CADUCA(
 ANIO NUMERIC(4)
)

DROP TABLE FECHA_CADUCA
INSERT INTO FECHA_CADUCA VALUES ('2020'),('2025'),('2030'),('2035'),('2040')

CREATE TABLE NOMBRES_PROVEEDORES(
 NOMBRES_PORVEE VARCHAR(15)
)

INSERT INTO NOMBRES_PROVEEDORES VALUES ('Mario'),('Luigui'),('Wario'),('Maria'),('Pedor')

SELECT * FROM PRODUCTOS

## Link Video
https://youtu.be/WscXa6G5d-o
