## Comandos básicos

### Compilar
```shell
./mill backend.compile
```

### Probar
```shell
./mill backend.test
```
- Para ejecutar solamente pruebas específicas
```shell
./mill backend.test.testOnly PruebasValidadorObvio
```

### Formatear
```shell
./mill mill.scalalib.scalafmt/
```
- Para validar que todo está formateado bien
```shell
./mill mill.scalalib.scalafmt/checkFormatAll
```

### Ejecutar el servidor
```shell
./mill backend.runMain CaskServer
```
Si hubiera más clases principales, entonces podríamos indicar la clase principal en el `build.mill` como se explica
[aquí](https://mill-build.org/mill/scalalib/module-config.html#_specifying_the_main_class).
