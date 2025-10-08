import scala.util.control.NoStackTrace

object Dominio:
  trait ID:
    def formateado: String

  object invariantes:

    import errores.*
    // No cambie el orden de la enumeración.
    // El valor ordinal de cada letra corresponde con el número que representan
    enum LetraNIE:
      case X // 0
      case Y // 1
      case Z // 2

    object LetraNIE:

      def disyuncion(letter: String): Either[LetraNIEInvalida, LetraNIE] =
        Either.cond(
          LetraNIE.values.map(_.toString).contains(letter),
          LetraNIE.valueOf(letter),
          LetraNIEInvalida(letter)
        )

    // No cambie el orden de la enumeración.
    // El valor ordinal de cada letra corresponde con el resto del número dividido por 23
    enum LetraDeControl:
      case T // 0
      case R // 1
      case W // 2
      case A // 3
      case G // 4
      case M // 5
      case Y // 6
      case F // 7
      case P // 8
      case D // 9
      case X // 10
      case B // 11
      case N // 12
      case J // 13
      case Z // 14
      case S // 15
      case Q // 16
      case V // 17
      case H // 18
      case L // 19
      case C // 20
      case K // 21
      case E // 22

    object LetraDeControl:

      def disyuncion(letter: String): Either[LetraDeControlInvalida, LetraDeControl] =
        Either.cond(
          LetraDeControl.values.map(_.toString).contains(letter),
          LetraDeControl.valueOf(letter),
          LetraDeControlInvalida(letter)
        )

  end invariantes

  object reglas:

    import invariantes.*

    def inputInvalido(input: String): String =
      s"Entrada inválida: '$input' debe ser alfanumérico y tener 9 caracteres"
    def numeroNIFInvalid(numeroNIF: String): String = s"Número de DNI inválido: '$numeroNIF' debe tener 8 dígitos"
    def numeroNIEInvalido(numeroNIE: String): String = s"Número de NIE inválido: '$numeroNIE' debe tener 7 dígitos"
    def letraNIEInvalida(letraNIE: String): String =
      s"Letra de NIE inválida: '$letraNIE' no es una letra válida de NIE"
    def letraDeControlInvalida(letraDeControl: String): String =
      s"Letra de control inválida: '$letraDeControl' no es una letra de control válida"
    def nifInvalido(numeroNIF: String, letraDeControl: LetraDeControl): String =
      s"DNI inválido: '$numeroNIF' no coincide con la letra de control '$letraDeControl'"
    def nieInvalido(letraNIE: LetraNIE, numeroNIE: String, letraDeControl: LetraDeControl): String =
      s"NIE inválido: '$letraNIE-$numeroNIE' no coincide con la letra de control '$letraDeControl'"

  end reglas

  object errores:

    import invariantes.*
    import reglas.*

    sealed trait ValidacionFallida(val causa: String) extends Exception with NoStackTrace
    case class InputInvalido(input: String) extends ValidacionFallida(inputInvalido(input))
    case class NumeroNIFInvalido(dniNumber: String) extends ValidacionFallida(numeroNIFInvalid(dniNumber))
    case class NumeroNIEInvalido(nieNumber: String) extends ValidacionFallida(numeroNIEInvalido(nieNumber))
    case class LetraNIEInvalida(nieLetter: String) extends ValidacionFallida(letraNIEInvalida(nieLetter))
    case class LetraDeControlInvalida(controlLetter: String)
        extends ValidacionFallida(letraDeControlInvalida(controlLetter))
    case class NIFInvalido(dniNumber: String, letter: LetraDeControl)
        extends ValidacionFallida(nifInvalido(dniNumber, letter))
    case class NIEInvalido(nieLetter: LetraNIE, nieNumber: String, letter: LetraDeControl)
        extends ValidacionFallida(nieInvalido(nieLetter, nieNumber, letter))

  end errores
end Dominio
