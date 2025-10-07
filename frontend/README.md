## Inicio rápido

```sh
cd frontend/tyrian
npm install
npm run start
```

En una segunda terminal

```sh
cd frontend/tyrian
sbt
fastLinkJS
```

Servido en: `http://localhost:1234/`

Para ver cambios en el frontend, recarga el servidor sbt con `fastLinkJS`.

## Instrucciones de configuración

Para ejecutar el programa en un navegador, necesitarás tener yarn (o npm) instalado.

Antes de tu primera ejecución y para que tus pruebas funcionen, **debes** instalar las dependencias de node con:

```sh
yarn install
```

Este ejemplo usa Parcel.js como nuestro empaquetador y servidor de desarrollo; hay muchas otras opciones que podrías
preferir como Webpack, scalajs-bundler o incluso solo JavaScript vanilla.

Te recomendamos tener dos pestañas de terminal abiertas en el directorio que contiene este archivo README.

En la primera, ejecutaremos sbt.

```sh
sbt
```

A partir de ahora, podemos recompilar la aplicación con `fastLinkJS` o `fullLinkJS`, pero ten en cuenta que el archivo 
`tyrianapp.js` en la raíz espera la salida de `fastLinkJS`.

Ejecuta `fastLinkJS` para obtener una compilación inicial en su lugar.

Luego inicia tu servidor de desarrollo con:

```sh
yarn start
```

Ahora navega a [http://localhost:1234/](http://localhost:1234/) para ver tu sitio en funcionamiento.

Si dejas que el servidor de desarrollo de parcel corra, todo lo que tienes que hacer es otro `fastLinkJS` o `fullLinkJS`,
y tu aplicación en el navegador debería recargar el nuevo código.

## Tipos de efectos soportados

A partir de la versión `0.6.0`, Tyrian soporta tanto Cats Effect 3 como ZIO 2.0. Este plantilla predetermina a CE3 y a 
IO (ya que es el hábito del autor), pero hay un ejemplo de [un proyecto Tyrian con ZIO](https://github.com/PurpleKingdomGames/tyrian/tree/main/examples)
disponible, y la conversión es bastante directa.

La [configuración](https://github.com/PurpleKingdomGames/tyrian/blob/main/examples/build.sbt#L153) para el ejemplo de 
ZIO tiene bibliotecas que debes añadir/reemplazar. Necesitas configurar los [imports correctos](https://github.com/PurpleKingdomGames/tyrian/blob/main/examples/zio/src/main/scala/example/Main.scala#L6)
y reemplazar `IO` con [`Task`](https://github.com/PurpleKingdomGames/tyrian/blob/main/examples/zio/src/main/scala/example/Main.scala#L13).

De lo contrario, es idéntico.

## Herramientas de construcción soportadas

Tyrian funciona igual de bien con sbt o Mill. La mayoría de los ejemplos se dan en sbt, y este g8 plantilla también usa 
sbt. Sin embargo, hay un [ejemplo de Mill](https://github.com/PurpleKingdomGames/tyrian/tree/main/examples/mill) que 
sirve como punto de partida decente.
