# Taller DDD en Español
Pequeño taller de Domain Driven Design (DDD) en castellano (español) codificado en Scala 3.

El objetivo de este taller es enseñar los conceptos básicos del Diseño Dirigido al Dominio (DDD).
Estos conceptos ayudan a los ingenieros a guiar a los asistentes de Inteligencia Artificial a generar mejor código.

El taller se divide en tres partes:
1. **Vibe Coding**: pedirle a la IA que genere código con base en los requisitos de negocio sin más contexto.
2. **Standard DDD & AI**: pedirle a la IA que genere el mismo código con el contexto adicional del DDD.
3. **Scala DDD & AI**: pedirle a la IA que genere el mismo código con los fuertes guardarrailes de Scala.

## Instalación
Sigue [SETUP.md](/SETUP.md) para tener la mejor experiencia trabajando con este taller.

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
./mill backend.run
```

Servido en: `http://localhost:8080/`
