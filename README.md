# Proyecto Dockerizado - Clase Robin y Round Robin

Este proyecto consiste en Dockerizar dos aplicaciones Java: `robbingsecond` y `roundrobin`, para que puedan ser ejecutadas en contenedores Docker. `robbingsecond` es una aplicación que realiza llamadas HTTP a varios servicios y `roundrobin` es una aplicación web simple que usa el algoritmo Round Robin para distribuir llamadas HTTP entre varios servidores.

## Requisitos Previos

Asegúrate de tener Docker y Docker Compose instalados en tu sistema. Puedes descargarlos desde el sitio web oficial de Docker si aún no los tienes instalados:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

(Opcional)
Si se quiere se puede hacer el pull de las diferentes imagenes del siguiente docker hub:
- [davidval6/areptalleraws](https://hub.docker.com/repository/docker/davidval6/areptalleraws/general)

## Descarga del Proyecto

Para obtener el código fuente del proyecto, clona el repositorio desde GitHub:

```bash
git clone https://github.com/DavidVal6/AREP-TALLER6.git
cd AREP-TALLER6
```
## Construccion de imagenes
Ahora, construiremos las imágenes Docker para las aplicaciones robbingsecond y roundrobin. Asegúrate de estar en la raíz del proyecto antes de ejecutar estos comandos.
### Construccion de las imagenes
```bash
docker build -t robin:latest ./roundrobin
```
O haciendo cd a roundrobbin y ejecutando
```bash
docker build -t robin:latest .
```

Y Para service 
```bash
docker build -t service:latest ./robbingsecond
```
O ubicandose en robbingsecond y ejecutando
```bash
docker build -t service:latest .
```
## Configuración de Docker Compose
El proyecto incluye un archivo docker-compose.yml que define la configuración de los contenedores Docker. Asegúrate de que el archivo docker-compose.yml contenga la siguiente configuración:

```yaml
version: '3'
services:
  robin:
    image: robin:latest
    ports:
      - "4567:4567"
    container_name: robin

  mongo-db:
    image: mongo
    container_name: 'mongo-db'
    volumes:
        - 'mongodb:/data/db'
        - 'mongodb_config:/data/configdb'
    ports:
        - '27017:27017'
    command: 'mongod'

  service-1:
    image: service:latest
    ports:
      - "4568:4568"
    container_name: service-1

  service-2:
    image: service:latest # Ruta al directorio de Clase2
    ports:
      - "4569:4568"
    container_name: service-2

  service-3:
    image: service:latest # Ruta al directorio de Clase2
    ports:
      - "4570:4568"
    container_name: service-3

volumes:
    mongodb:
    mongodb_config:
```
## Ejecución del Proyecto
Con las imágenes Docker construidas y el archivo docker-compose.yml configurado, puedes ejecutar el proyecto con Docker Compose.

Abre una terminal y navega hasta la ubicación del archivo docker-compose.yml.

Ejecuta el siguiente comando para iniciar los contenedores:

```bash
docker-compose up -d 
```
Y una vez corran todos los servicios se debe de usar lo siguiente
```bash
htttp://localhost:4567/
```
Y empezar a probar.

## PRUEBAS DE FUNCIONAMIENTO
![image](https://github.com/DavidVal6/AREP-TALLER6/assets/98176834/4d3e7581-9e70-4d03-8ab9-e858118156a8)
![image](https://github.com/DavidVal6/AREP-TALLER6/assets/98176834/d6d01117-f7dc-455d-a679-dab445729656)

### Video de Prueba de funcionamiento con AWS:
https://pruebacorreoescuelaingeduco.sharepoint.com/:v:/s/yo818/EW2_9B1FcBtPjeWiHRtcPqkBSbOa25tM8qUO_MHJP8IReQ?e=6qlc0I&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZyIsInJlZmVycmFsQXBwUGxhdGZvcm0iOiJXZWIiLCJyZWZlcnJhbE1vZGUiOiJ2aWV3In19
## Autor
Autor : David Eduardo Valencia

## Referencias
1. Santiago Rocha
2. Julian Castillo
