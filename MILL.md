# Guía rápida de Mill Build Tool

Este proyecto utiliza la versión 1 del compilador Mill Build Tool.

## Configuración
Para instalar el paquete de autocompletado de la línea de comandos, ejecuta:
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
