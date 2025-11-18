# Instalación
¿Usas Linux, Mac o Windows con WSL2? Entonces sigue los pasos de la instalación recomendada.

¿Cuándo debes seguir la instalación alternativa?
1. Si tu distribución de Linux no soporta Nix
2. Si no tienes instalado Windows Subsystem Linux v2 (WSL 2)

## Recomendada
``DevBox`` es una herramienta que permite generar terminales aisladas con todas las dependencias necesarias.

Guía de instalación: https://www.jetify.com/docs/devbox/installing-devbox

Una vez instalado ``DevBox``, abre una terminal en la carpeta donde quieras clonar el proyecto y ejecuta:
```shell
git clone git@github.com:dagmendez/DDD-workshop-Scala3-Spanish.git
```
Nota: Si no tienes ``git`` instalado: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

Para iniciar la consola con todas las dependencias ejecuta el comando:
```shell
devbox shell
```
Seguramente la terminal te pida la contraseña de usuario para poder instalar todas las dependencias necesarias
definidas en el archivo ``devbox.json``. Una vez instalas las dependencias, ya podrás empezar el taller.

Si tienes problemas con ``DevBox``, sigue los pasos de la instalación alternativa

## Alternativa

Para poder ejecutar los artefactos del _front end_ y el _back end_ se utilizan un lenguaje y dos tecnologías:
1. **Lenguaje** _Scala_ - [Scala](https://www.scala-lang.org/)
2. **Front end:** _Yarn_ - [Proyectos seguros, stables y reproducibles](https://yarnpkg.com/)
3. **Back end:** _Mill_ - [Mill: Una herramienta mejor de construcción para Java, Scala, & Kotlin](https://mill-build.org/mill/index.html)

### Scala
- **macOS**:
```shell
brew install coursier/formulas/coursier && cs setup --jvm 21.0.8
```
- **Linux**:
```shell
curl -fL https://github.com/coursier/coursier/releases/latest/download/cs-x86_64-pc-linux.gz | gzip -d > cs && chmod +x cs && ./cs setup --jvm 21.0.8
```
- **Windows**:
[Instalador de Scala para Windows](https://github.com/coursier/coursier/releases/latest/download/cs-x86_64-pc-win32.zip)

### Yarn
[Guía de instalación](https://classic.yarnpkg.com/lang/en/docs/install/)

### Mill
Mill tiene que instalarse directamente en el directorio raíz del proyecto. [Guía de instalación](https://mill-build.org/mill/cli/installation-ide.html).

- **Mac/Linux**:
```shell
curl -L https://repo1.maven.org/maven2/com/lihaoyi/mill-dist/1.0.6/mill-dist-1.0.6-mill.sh -o mill
chmod +x mill
```
- **Windows**:
```shell
curl.exe -L https://repo1.maven.org/maven2/com/lihaoyi/mill-dist/1.0.6/mill-dist-1.0.6-mill.bat -o mill.bat
```
Una vez instalado, se puede comprobar la versión instalada ejecutando:
```shell
> ./mill version
```
Existe una [pequeña guía práctica](MILL.md) para el proyecto.
