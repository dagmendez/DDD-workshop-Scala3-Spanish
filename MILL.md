## Guía rápida de Mill Build Tool

Este proyecto utiliza la versión 1 del compilador Mill Build Tool.

Para ejecutar el proyecto correctamente, debes ubicar tu terminal en la ruta `~/backend/`.

### Instalación de Mill
Consulta la última información de instalación [aquí](https://mill-build.org/mill/cli/installation-ide.html).

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

#### Bash/Zsh Completado de Tabulación - Opcional
```shell
./mill mill.tabcomplete/install
```

### Comandos básicos
Los siguientes 3 grupos de comandos son suficientes para ejecutar la versión mínima viable del Validador de ID en español. Puedes ejecutarlos directamente desde aquí usando una IDE.

#### Compilar
```shell
./mill mvp.compile
```

#### Probar
```shell
./mill mvp.test

```
- Probar pruebas específicas
```shell
./mill mvp.test.testOnly PruebasValidadorObvio

```

#### Formatear
```shell
./mill mill.scalalib.scalafmt/
```

```shell
./mill mill.scalalib.scalafmt/checkFormatAll
```

### Ejecutar el servidor
```shell
./mill backend.runMain CaskServer
```
Si hubiera más clases principales, entonces podríamos indicar la clase principal en el `build.mill` como se explica 
[aquí](https://mill-build.org/mill/scalalib/module-config.html#_specifying_the_main_class).
