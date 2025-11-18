import domain.Rules.*
import api.ID
import api.API
import domain.Invariants.*

object MinimumViableProduct extends API:

  private class Number private (val value: String) extends AnyVal
  private object Number:
    def apply(number: String): Number | String =
      isValidNumber(number) match
        case error: String => error
        case _: Boolean    => new Number(number)

  class NIENumber private (val value: String) extends AnyVal
  private object NIENumber:
    def apply(number: String): NIENumber | String =
      Number(number) match
        case error: String => error
        case _: Number =>
          isValidNIENumber(number) match
            case error: String => error
            case _: Boolean    => new NIENumber(number)

  class NIFNumber private (val value: String) extends AnyVal
  private object NIFNumber:
    def apply(number: String): NIFNumber | String =
      Number(number) match
        case error: String => error
        case _: Number =>
          isValidNIFNumber(number) match
            case error: String => error
            case _: Boolean    => new NIFNumber(number)

  class NIF private (val nif_number: NIFNumber, val control_letter: ControlLetter) extends ID:
    override def toUpperCaseWithDash: String = s"${nif_number.value}-$control_letter"

  object NIF:
    def apply(input: String): NIF | String =
      val (number, letter) = input.splitAt(input.length() - 1)
      NIFNumber(number) match
        case error: String => error
        case nif_number: NIFNumber =>
          ControlLetter(letter) match
            case error: String => error
            case control_letter: ControlLetter =>
              isValidNIF(nif_number.value, control_letter) match
                case error: String => error
                case _: Boolean    => new NIF(nif_number, control_letter)

  class NIE private (val nie_letter: NieLetter, val nie_number: NIENumber, control_letter: ControlLetter) extends ID:
    override def toUpperCaseWithDash: String = s"$nie_letter-${nie_number.value}-$control_letter"

  object NIE:
    def apply(input: String): NIE | String =
      val (head, tail) = (input.head.toString(), input.tail.toString())
      val (number, letter) = tail.splitAt(tail.length() - 1)
      NieLetter(head) match
        case error: String => error
        case nie_letter: NieLetter =>
          NIENumber(number) match
            case error: String => error
            case nie_number: NIENumber =>
              ControlLetter(letter) match
                case error: String => error
                case control_letter: ControlLetter =>
                  isValidNIE(nie_letter, nie_number.value, control_letter) match
                    case error: String => error
                    case _: Boolean    => new NIE(nie_letter, nie_number, control_letter)

  override def validateID(input: String): ID | String =
    // Preprocessing the input
    val sanitizedInput =
      input.trim // Handling empty spaces around
        .replace("-", "") // Removing dashes
        .toUpperCase() // Handling lower case
    isValidInput(sanitizedInput) match
      case error: String => error
      case _: Boolean    =>
        // Selecting which type of ID base on initial character type - Letter or Digit
        if sanitizedInput.head.isDigit // Splitting between NIF and NIE
        then
          NIF(sanitizedInput) match
            case error: String => error
            case nif: NIF      => nif
        else
          NIE(sanitizedInput) match
            case error: String => error
            case nie: NIE      => nie

end MinimumViableProduct
