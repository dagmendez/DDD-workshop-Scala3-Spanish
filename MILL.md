# Guía rápida de Mill Build Tool

Este proyecto utiliza la versión 1 del compilador Mill Build Tool. Ya que se utiliza ``DevBox`` para gestionar las 
dependencias, no es necesario instalarlo manualmente.

## Configuración
``DevBox`` no incluye la instalación del paquete de autocompletado de la línea de comandos. Para instalar ejecuta:
```shell
./mill mill.tabcomplete/install
```

## Como saber que ejecutar
Mill permite conocer qué comandos están a tu disposición con el commando ``resolve``. Por ejemplo:
```shell
./mill resolve _
```
Este comando muestra una lista del primer nivel de comandos. Entre ellos están ``frontend`` y ``backend`` que son el
nombre de los dos módulos del proyecto. Para conocer los comandos que puedes utilizar en los siguientes niveles, solo 
hay que repetir la operación añadiendo el nombre del comando delante. Por ejemplo:
```shell
./mill resolve backend._
```
Una vez sabes que comando quieres utilizar, basta con borrar ``resolve`` y ejecutar. [Aquí](backend/README.md) puedes
encontrar los comandos básicos para trabajar con el _back end_.

## Opcional - Instalación de Mill en tu equipo
Si deseas instalar mill en tu equipo, puedes consultar la última información de instalación [aquí](https://mill-build.org/mill/cli/installation-ide.html).

#### Mac/Linux
- Instalar
```shell
curl -L https://repo1.maven.org/maven2/com/lihaoyi/mill-dist/1.0.3/mill-dist-1.0.3-mill.sh -o mill
```
- Dar permisos de ejecución a mill
```shell
chmod +x mill
```

#### Windows
```shell
curl.exe -L https://repo1.maven.org/maven2/com/lihaoyi/mill-dist/1.0.3/mill-dist-1.0.3-mill.bat -o mill.bat
```
