package refined

import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import scala.compiletime.constValue
import scala.compiletime.ops.any.ToString

object Refined extends API:

  private type Input = Input.T
  private object Input extends RefinedType[String, Alphanumeric & FixedLength[9]]

  private type NIFNumber = NIFNumber.T
  private object NIFNumber extends RefinedType[String, FixedLength[8] & ForAll[Digit]]

  private type NIENumber = NIENumber.T
  private object NIENumber extends RefinedType[String, FixedLength[7] & ForAll[Digit]]

  private inline val nieLetters = "XYZ"
  private inline val nieLettersRegex = "[" + constValue[ToString[nieLetters.type]] + "]{1}"
  private type NIELetter = NIELetter.T
  private object NIELetter extends RefinedType[String, Match[nieLettersRegex.type]]

  private inline val controlLetters = "TRWAGMYFPDXBNJZSQVHLCKE"
  private inline val controlLettersRegex = "[" + constValue[ToString[controlLetters.type]] + "]{1}"
  private type ControlLetter = ControlLetter.T
  private object ControlLetter extends RefinedType[String, Match[controlLettersRegex.type]]

  class NIF private (nif_number: NIFNumber, control_letter: ControlLetter) extends ID:
    override def toUpperCaseWithDash: String = s"$nif_number-$control_letter"

  object NIF:
    def apply(input: String): NIF | String =
      val (number, letter) = input.splitAt(input.length() - 1)
      (for
        nif_number <- NIFNumber.either(number)
        control_letter <- ControlLetter.either(letter)
        nif <- Either.cond(
          nif_number.value.toInt % 23 == controlLetters.indexOf(control_letter.value),
          new NIF(nif_number, control_letter),
          s"Invalid NIF: $nif_number does not match control letter $control_letter."
        )
      yield nif) match
        case Left(error) => error
        case Right(nif)  => nif

  class NIE private (nie_letter: NIELetter, nie_numbre: NIENumber, control_letter: ControlLetter) extends ID:
    override def toUpperCaseWithDash: String = s"$nie_letter$nie_numbre-$control_letter"

  object NIE:
    def apply(input: String): NIE | String =
      val (head, tail) = (input.head.toString(), input.tail.toString())
      val (number, letter) = tail.splitAt(tail.length() - 1)
      (for
        nie_letter <- NIELetter.either(head)
        nie_number <- NIENumber.either(number)
        control_letter <- ControlLetter.either(letter)
        remainder = ((Math.pow(10, 7) * nieLetters.indexOf(nie_letter.value.toString)) + nie_number.value.toInt) % 23
        nie <- Either.cond(
          remainder == controlLetters.indexOf(control_letter.value),
          new NIE(nie_letter, nie_number, control_letter),
          s"Invalid NIE: $nie_letter$nie_number does not match control letter $control_letter."
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
    Input.either(sanitizedInput) match
      case Left(error) => error
      case Right(validInput) =>
        if validInput.value.head.isDigit
        then NIF(validInput.value)
        else NIE(validInput.value)

end Refined
