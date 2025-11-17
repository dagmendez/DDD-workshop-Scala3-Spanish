import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import api.API
import api.ID
import scala.compiletime.constValue

object Refined extends API:

  private type ValidInput = ValidInput.T
  private object ValidInput extends RefinedType[String, Alphanumeric & FixedLength[9]]

  private type ValidNIFNumber = ValidNIFNumber.T
  private object ValidNIFNumber extends RefinedType[String, FixedLength[8] & ForAll[Digit]]

  private type ValidNIENumber = ValidNIENumber.T
  private object ValidNIENumber extends RefinedType[String, FixedLength[7] & ForAll[Digit]]

  private inline val nieLetters = "XYZ"
  private type ValidNIELetter = ValidNIELetter.T
  private object ValidNIELetter extends RefinedType[String, Match["[XYZ]{1}"]]

  private inline val controlLetters = "TRWAGMYFPDXBNJZSQVHLCKE"
  private type ValidControlLetter = ValidControlLetter.T
  private object ValidControlLetter extends RefinedType[String, Match["[TRWAGMYFPDXBNJZSQVHLCKE]{1}"]]

  class NIF private (nif_number: ValidNIFNumber, control_letter: ValidControlLetter) extends ID:
    override def toUpperCaseWithDash: String = s"$nif_number-$control_letter"

  object NIF:
    def apply(input: String): NIF | String =
      val (number, letter) = input.splitAt(input.length() - 1)
      (for
        nif_number <- ValidNIFNumber.either(number)
        control_letter <- ValidControlLetter.either(letter)
        nif <- Either.cond(
          nif_number.value.toInt % 23 == controlLetters.indexOf(control_letter.value),
          new NIF(nif_number, control_letter),
          s"Invalid NIF: $nif_number does not match control letter $control_letter."
        )
      yield nif) match
        case Left(error) => error
        case Right(nif)  => nif

  class NIE private (nie_letter: ValidNIELetter, nie_numbre: ValidNIENumber, control_letter: ValidControlLetter)
      extends ID:
    override def toUpperCaseWithDash: String = s"$nie_letter-$nie_numbre-$control_letter"

  object NIE:
    def apply(input: String): NIE | String =
      val (head, tail) = (input.head.toString(), input.tail.toString())
      val (number, letter) = tail.splitAt(tail.length() - 1)
      (for
        nie_letter <- ValidNIELetter.either(head)
        nie_number <- ValidNIENumber.either(number)
        control_letter <- ValidControlLetter.either(letter)
        remainder = ((Math.pow(10, 7) * nieLetters.indexOf(nie_letter.value.toString)) + nie_number.value.toInt) % 23
        nie <- Either.cond(
          remainder == controlLetters.indexOf(control_letter.value),
          new NIE(nie_letter, nie_number, control_letter),
          s"Invalid NIE: $nie_letter-$nie_number does not match control letter $control_letter."
        )
      yield nie) match
        case Left(error) => error
        case Right(nie)  => nie

  override def validateID(input: String): ID | String =
    // Preprocessing the input
    val sanitizedInput =
      input.trim // Handling empty spaces around
        .replace("-", "") // Removing dashes
        .toUpperCase() // Handling lower case
    ValidInput.either(sanitizedInput) match
      case Left(error) => error
      case Right(validInput) =>
        if validInput.value.head.isDigit
        then NIF(validInput.value)
        else NIE(validInput.value)

end Refined
