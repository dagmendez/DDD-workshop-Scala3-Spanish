# Taller DDD en Español
Pequeño taller de Domain Driven Design (DDD) en castellano (español) codificado en Scala 3.

El objetivo de este taller es enseñar los conceptos básicos del Diseño Dirigido al Dominio (DDD).
Estos conceptos ayudan a los ingenieros a guiar a los asistentes de Inteligencia Artificial a generar mejor código.

El taller se divide en tres partes:
1. **Vibe Coding**: pedirle a la IA que genere código con base en los requisitos de negocio sin más contexto.
2. **Standard DDD & AI**: pedirle a la IA que genere el mismo código con el contexto adicional del DDD.
3. **Scala DDD & AI**: pedirle a la IA que genere el mismo código con los fuertes guardarrailes de Scala.

## Instalación

Para poder tener la mejor experiencia con este taller se recomienda utilizar la herramienta ``DevBox`` para evitar
las tareas de instalación de todas las dependencias. De todas formas, si estás acostumbrado a trabajar con múltiples
versiones de Java simultaneamente, puedes instalar directamente las dependencias.

### Recomendada
Para asegurarse de una mejor experiencia a la hora de prácticar este taller, se va a usar una herramienta que permite
generar terminales aisladas con todas las dependencias necesarias: ``DevBox``

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

### Alternativa

Para poder ejecutar los artefactos del _front end_ y el _back end_ se utilizan un lenguaje y dos tecnologías:
1. **Lenguaje** _Scala_ - [Scala](https://www.scala-lang.org/)
2. **Front end:** _Yarn_ - [Proyectos seguros, stables y reproducibles](https://yarnpkg.com/)
3. **Back end:** _Mill_ - [Mill: Una herramienta mejor de construcción para Java, Scala, & Kotlin](https://mill-build.org/mill/index.html)

#### Scala
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

#### Yarn
[Guía de instalación](https://classic.yarnpkg.com/lang/en/docs/install/)

#### Mill
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

## Ejecución
Para poder ejecutar el proyecto, te recomiendo abrir dos terminales y ejecutar en cada una de ellas el _front end_ y el 
_back end_ respectivamente. Si usas ``DevBox``, recuerda iniciarlo en las dos terminales.

### Front End
Antes de iniciar el _front end_ hay que compilar el código a ``JavaScript``. Para ello ejecutamos:
```shell
./mill frontend.compile
```
```shell
./mill frontend.fastLinkJS
```

Acto seguido, entramos en la carpeta /frontend
```shell
cd frontend
```
Una vez allí, necesitamos instalar los módulos con ``yarn``:
```shell
yarn install
```
Y finalmente lanzamos la página web con:
```shell
yarn start
```
Servido en: `http://localhost:1234/`

### Back End
En una nueva consola, compilamos el código y ejecutamos la clase principal:
```shell
./mill backend.compile
```
```shell
./mill backend.runMain CaskServer
```

Servido en: `http://localhost:8080/`

Más información sobre el _back end_ [aquí](backend/README.md).
