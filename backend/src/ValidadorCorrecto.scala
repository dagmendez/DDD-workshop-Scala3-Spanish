import Dominio.ID
import Dominio.errores.*
import Dominio.invariantes.*

object ValidadorCorrecto:

  private final class NumeroNIE private (val valor: String) extends AnyVal
  private object NumeroNIE:
    def disyuntiva(numeroNIE: String): Either[ValidacionFallida, NumeroNIE] =
      Either.cond(
        numeroNIE.length == 7 && numeroNIE.forall(_.isDigit),
        new NumeroNIE(numeroNIE),
        NumeroNIEInvalido(numeroNIE)
      )

  private final class NumeroNIF private (val valor: String) extends AnyVal
  private object NumeroNIF:
    def disyuntiva(numeroNIF: String): Either[ValidacionFallida, NumeroNIF] =
      Either.cond(
        numeroNIF.length == 8 && numeroNIF.forall(_.isDigit),
        new NumeroNIF(numeroNIF),
        NumeroNIFInvalido(numeroNIF)
      )

  final class NIF private (numeroNIF: NumeroNIF, letraDeControl: LetraDeControl) extends ID:
    override def formateado: String = s"${numeroNIF.valor}-$letraDeControl"

  object NIF:

    def disyuntiva(input: String): Either[ValidacionFallida, NIF] =
      val (numero, letra) = input.splitAt(input.length - 1)
      for
        numeroNIF <- NumeroNIF.disyuntiva(numero)
        letraDeControl <- LetraDeControl.disyuncion(letra)
        resultado <- Either.cond(
          numeroNIF.valor.toInt % 23 == letraDeControl.ordinal,
          new NIF(numeroNIF, letraDeControl),
          NIFInvalido(numeroNIF.valor, letraDeControl)
        )
      yield resultado

  final class NIE private (letraNIE: LetraNIE, numeroNIE: NumeroNIE, letraDeControl: LetraDeControl) extends ID:
    override def formateado: String = s"$letraNIE-${numeroNIE.valor}-$letraDeControl"

  object NIE:

    def disyuntiva(input: String): Either[ValidacionFallida, NIE] =
      val (primeraLetra, numero, segundaLetra) = input.head.toString *: input.tail.splitAt(input.length - 2)
      for
        letraNIE <- LetraNIE.disyuncion(primeraLetra)
        numeroNIE <- NumeroNIE.disyuntiva(numero)
        letraDeControl <- LetraDeControl.disyuncion(segundaLetra)
        resultado <- Either.cond(
          ((letraNIE.ordinal * 10000000) + numeroNIE.valor.toInt) % 23 == letraDeControl.ordinal,
          new NIE(letraNIE, numeroNIE, letraDeControl),
          NIEInvalido(letraNIE, numeroNIE.valor, letraDeControl)
        )
      yield resultado

  object ID:

    def disyuntiva(input: String): Either[ValidacionFallida, ID] =

      // Pre-procesamos el input
      val inputSaneado =
        input.trim // Eliminamos los espacios en blanco
          .replace("-", "") // Eliminamos los guiones
          .toUpperCase() // Capitalizamos las letras

      if inputSaneado.length == 9 && inputSaneado.forall(_.isLetterOrDigit) then
        if inputSaneado.head.isDigit
        then NIF.disyuntiva(inputSaneado)
        else NIE.disyuntiva(inputSaneado)
      else Left(InputInvalido(input))
