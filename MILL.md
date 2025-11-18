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

## Compilar
1. `poc`
```shell
./mill poc.compile
```
2. `mvp`
 ```shell
./mill mvp.compile
```
3. `refined` 
```shell
./mill refined.compile
```

## Probar
1. `poc`
```shell
./mill poc.test
```
2. `mvp`
 ```shell
./mill mvp.test
```
3. `refined` 
```shell
./mill refined.test
```

## Formatear

### Código Scala
De acuerdo a la configuarión en [.scalafmt.conf](/.scalafmt.conf).

```shell
./mill mill.scalalib.scalafmt/
```
- Para validar que todo está formateado bien
```shell
./mill mill.scalalib.scalafmt/checkFormatAll
```

### Imports
De acuerdo a la configuración en [.scalafix.conf](/.scalafix.conf).

1. `poc`

```shell
./mill poc.fix
./mill poc.test.fix
```

2. `mvp`

 ```shell
./mill mvp.fix
./mill mvp.test.fix
```

3. `refined` 

```shell
./mill refined.fix
./mill refined.test.fix
```
