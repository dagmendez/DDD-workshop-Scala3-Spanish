package domain

import domain.Rules.isValidNIELetter
import domain.Rules.isValidControlLetter

object Invariants:

  // Do NOT change the order of the enumeration.
  // The ordinal value of each letter corresponds with number they represent
  enum NieLetter:
    case X // 0
    case Y // 1
    case Z // 2

  object NieLetter:
    def apply(input: String): NieLetter | String =
      isValidNIELetter(input) match
        case error: String => error
        case _: Boolean    => NieLetter.valueOf(input)

  // Do NOT change the order of the enumeration.
  // The ordinal value of each letter corresponds with the remainder of number divided by 23
  enum ControlLetter:
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

  object ControlLetter:
    def apply(input: String): ControlLetter | String =
      isValidControlLetter(input) match
        case error: String => error
        case _: Boolean    => ControlLetter.valueOf(input)

end Invariants
