import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import Dominio.*
import Dominio.errores.*
import Dominio.invariantes.*

object ValidadorCorrectisimo:

  private type InputValido = InputValido.T
  private object InputValido extends RefinedType[String, Alphanumeric & FixedLength[9]]
  private type NumeroNIFValido = NumeroNIFValido.T
  private object NumeroNIFValido extends RefinedType[String, FixedLength[8] & ForAll[Digit]]
  private type NumeroNIEValido = NumeroNIEValido.T
  private object NumeroNIEValido extends RefinedType[String, FixedLength[7] & ForAll[Digit]]

  class NIF private (numeroNIF: NumeroNIFValido, letraDeControl: LetraDeControl) extends ID:
    override def formateado: String = s"$numeroNIF-$letraDeControl"

  object NIF:
    def disyuntiva(input: String): Either[ValidacionFallida, NIF] = {
      val numero = input.dropRight(1)
      for
        numeroNIF <- NumeroNIFValido.either(numero).left.map(_ => NumeroNIFInvalido(numero))
        letra <- LetraDeControl.disyuncion(input.last.toString)
        resultado <- Either.cond(
          letra.ordinal == numero.toInt % 23,
          new NIF(numeroNIF, letra),
          NIFInvalido(numero, letra)
        )
      yield resultado
    }

  class NIE private (letraNIE: LetraNIE, numeroNIE: NumeroNIEValido, letraDeControl: LetraDeControl) extends ID:
    override def formateado: String = s"$letraNIE-$numeroNIE-$letraDeControl"

  object NIE:
    def disyuntiva(input: String): Either[ValidacionFallida, NIE] = {
      val numero = input.tail.dropRight(1)
      for
        letraNIE <- LetraNIE.disyuncion(input.head.toUpper.toString)
        numeroNIE <- NumeroNIEValido.either(numero).left.map(_ => NumeroNIEInvalido(numero))
        letraDeControl <- LetraDeControl.disyuncion(input.last.toString)
        resultado <- Either.cond(
          letraDeControl.ordinal == s"${letraNIE.ordinal}$numeroNIE".toInt % 23,
          new NIE(letraNIE, numeroNIE, letraDeControl),
          NIEInvalido(letraNIE, numeroNIE.value, letraDeControl)
        )
      yield resultado
    }

  object ID:
    def disyuntiva(input: String): Either[ValidacionFallida, ID] =
      val inputSaneado = input.trim.replace("-", "").toUpperCase
      InputValido
        .either(inputSaneado)
        .left
        .map(_ => InputInvalido(input))
        .flatMap: inputValido =>
          if inputValido.value.head.isDigit
          then NIF.disyuntiva(inputValido.value)
          else NIE.disyuntiva(inputValido.value)
