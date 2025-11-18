package domain

import Invariants.*

object Rules:

  def isValidInput(input: String): Boolean | String =
    if input.length == 9 && input.forall(_.isLetterOrDigit)
    then true
    else s"Invalid Input: '$input' should be AlphaNumeric and have 9 characters"

  def isValidNumber(number: String): Boolean | String =
    if number.forall(_.isDigit)
    then true
    else s"Invalid Number: '$number' should not contain letters"

  def isValidNIFNumber(nif_number: String): Boolean | String =
    if nif_number.length == 8
    then true
    else s"Invalid NIF Number: '$nif_number' should contain 8 digits"

  def isValidNIENumber(nie_number: String): Boolean | String =
    if nie_number.length == 7
    then true
    else s"Invalid NIE Number: '$nie_number' should contain 7 digits"

  def isValidNIELetter(nie_letter: String): Boolean | String =
    if NIELetter.values.map(_.toString).contains(nie_letter)
    then true
    else s"Invalid NIE Letter: '$nie_letter' is not a valid NIE letter"

  def isValidControlLetter(control_letter: String): Boolean | String =
    if ControlLetter.values.map(_.toString).contains(control_letter)
    then true
    else s"Invalid Control Letter: '$control_letter' is not a valid Control letter"

  def isValidNIF(nif_number: String, control_letter: ControlLetter): Boolean | String =
    if nif_number.toInt % 23 == control_letter.ordinal
    then true
    else s"Invalid NIF: '$nif_number' does not match the control letter '$control_letter'"

  def isValidNIE(nie_letter: NIELetter, nie_number: String, control_letter: ControlLetter): Boolean | String =
    val nie_letter_to_digit = Math.pow(10, 7) * nie_letter.ordinal
    if (nie_letter_to_digit + nie_number.toInt) % 23 == control_letter.ordinal
    then true
    else s"Invalid NIE: '$nie_letter$nie_number' does not match the control letter '$control_letter'"

end Rules
