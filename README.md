# Taller DDD en Español
Pequeño taller de Domain Driven Design (DDD) en castellano (español) codificado en Scala 3.

El objetivo de este taller es enseñar los conceptos básicos del Diseño Dirigido al Dominio (DDD).
Estos conceptos ayudan a los ingenieros a guiar a los asistentes de Inteligencia Artificial a generar mejor código.

El taller se divide en tres partes:
1. **Vibe Coding**: pedirle a la IA que genere código con base en los requisitos de negocio sin más contexto.
2. **Standard DDD & AI**: pedirle a la IA que genere el mismo código con el contexto adicional del DDD.
3. **Scala DDD & AI**: pedirle a la IA que genere el mismo código con los fuertes guardarrailes de Scala.

## Instalación
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

## Herramientas de construcción
Para poder ejecutar los artefactos del _front end_ y el _back end_ se utilizan dos tecnologías:
1. **Front end:** _Yarn_ - [Safe, stable, reproducible projects](https://yarnpkg.com/)
2. **Back end:** _Mill_ - [Mill: A Better Build Tool for Java, Scala, & Kotlin](https://mill-build.org/mill/index.html)

Ambas dependencias están inclúidas en el entorno de terminales aisladas de ``DevBox``.
Hay una pequeña guía práctica de mill [aquí](MILL.md).

## Ejecución
Para poder ejecutar el proyecto, te recomiendo abrir dos terminales y ejecutar en cada una de ellas el _front end_ y el 
_back end_ respectivamente. Recuerda iniciar ``DevBox`` en las dos terminales.

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
