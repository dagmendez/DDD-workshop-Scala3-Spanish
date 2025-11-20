# Taller DDD en Español
Pequeño taller de Domain Driven Design (DDD) en castellano (español) codificado en Scala 3.

El objetivo de este taller es enseñar los conceptos básicos del Diseño Dirigido al Dominio (DDD).
Estos conceptos ayudan a los ingenieros a guiar a los asistentes de Inteligencia Artificial a generar mejor código.

El taller se divide en tres partes:
1. **Prueba de concepto**: pedirle a la IA que genere código con base en los requisitos de técnicos sin más contexto.
2. **Minimum Viable Product**: pedirle a la IA que genere el mismo código con el contexto adicional de negocio y utilizando los principios del DDD.
3. **Refined**: pedirle a la IA que genere el mismo código con los fuertes guardarrailes de Scala 3, ayudándonos de metaprogramación y la librería `Iron`.

## Instalación
Sigue [SETUP.md](/SETUP.md) para tener la mejor experiencia trabajando con este taller.

## Taller

### Prueba de concepto
```shell
git checkout poc
```
Experimenta con tu asistente de IA para crear una solución al validador de IDs.

### Producto Viable Mínimo
```shell
git checkout mvp
```
Crea un artefacto que cumple todos los requisitos técnicos y de negocio. Apollate en la IA para crea una solución robusta que pase en verde todos los tests.

La solución disponible está implementado utilizando `union type` y `pattern matching`, presentes en muchos lenguajes de programación. 
```shell
git checkout mvp-resolved
```
### Refinado
```shell
git checkout refined
```
Explora las nuevas funcionalidades de Scala 3:
- Metaprogramación accesible con `inline` ([Metaprogramming - Inline](https://docs.scala-lang.org/scala3/reference/metaprogramming/inline.html))
- Operaciones en tiempo de compilación como `constValue[A]` o `ToString[A]` ([Metaprogramming Compile-time operations](https://docs.scala-lang.org/scala3/reference/metaprogramming/compiletime-ops.html))
- Tipos refinados utilizando la librería [Iron](https://iltotore.github.io/iron/docs/)

La solución está implementada de forma concisa para respetar los requisitos de negocio y técnicos. Utiliza estructuras `for-yield` que representan cadenas de `flatMap` y `map`, conceptos centrales de la programación funcional.
```shell
checkout refined-resolved
```

### Resuelto
Esta rama contine las 3 implementaciones y te permite comparar los mensajes de error en el _front end_ y las diferentes implementaciones del _back end_ en un único lugar.
```shell
git checkout resolved
```

## Ejecución
Para poder ejecutar el proyecto, te recomiendo abrir dos terminales y ejecutar en cada una de ellas el _front end_ y el 
_back end_ respectivamente. Si usas ``DevBox``, recuerda iniciarlo en las dos terminales.

**NOTA**: `mill` solo se ejecuta en un hilo, por lo que tienes que lanzar primero el _front end_ antes del _back end_.

### Terminal 1: Front End
Antes de iniciar el _front end_ hay que compilar el código a ``JavaScript``. Para ello ejecutamos:
```shell
mill frontend.compile
```
```shell
mill frontend.fastLinkJS
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

### Terminal 2: Back End
En una nueva consola, compilamos el código y ejecutamos la clase principal:
```shell
mill backend.compile
```
```shell
mill backend.run
```

Servido en: `http://localhost:8080/`

## Colaboración
Si te ha gustado este taller, márcalo con una estrella, compártelo...

Puedes añadir tus propias implementaciones del _back end_ o mejorar el _front end_ forkeando el proyecto y abriéndo una pull request.