import MinimumViableProduct.*
import domain.Rules.*
import domain.Invariants.*
import utest.*
import domain.ID

object MinimumViableProductTest extends TestSuite:
  
  val tests = Tests {
    test("NIF happy path"):
      Seq(
        ("12345678Z", "12345678-Z"),
        ("00000001R", "00000001-R"),
        ("99999999R", "99999999-R")
      ).foreach:
        case (input, expected) =>
          NIF(input) match
            case nif: NIF      => assert(nif.toUpperCaseWithDash == expected)
            case error: String => assert(false)

    test("NIF unhappy path"):

      test("Invalid Number"):
        NIF("1234567AT") match
          case error: String => assert(error.startsWith("Invalid Number"))
          case _: NIF        => assert(false)

      test("Invalid NIF Number: Too short"):
        NIF("1234567T") match
          case error: String => assert(error.startsWith("Invalid NIF Number"))
          case _: NIF        => assert(false)

      test("Invalid NIF Number: Too long"):
        NIF("123456789T") match
          case error: String => assert(error.startsWith("Invalid NIF Number"))
          case _: NIF        => assert(false)

      test("Invalid Control Letter"):
        NIF("12345678Ñ") match
          case error: String => assert(error.startsWith("Invalid Control Letter"))
          case _: NIF        => assert(false)

      test("Invalid NIF"):
        NIF("00000001Z") match
          case error: String => assert(error.startsWith("Invalid NIF"))
          case _: NIF        => assert(false)

    test("NIE happy path"):
      Seq(
        ("X0000001R", "X0000001-R"),
        ("Y2345678Z", "Y2345678-Z")
      ).foreach:
        case (input, expected) =>
          NIE(input) match
            case nie: NIE      => assert(nie.toUpperCaseWithDash == expected)
            case error: String => assert(false)

    test("NIE unhappy path"):

      test("Invalid NIE Letter"):
        NIE("A1234567T") match
          case error: String => assert(error.startsWith("Invalid NIE Letter"))
          case _: NIE        => assert(false)

      test("Invalid Number"):
        NIE("Y234567AT") match
          case error: String => assert(error.startsWith("Invalid Number"))
          case _: NIE        => assert(false)

      test("Invalid NIE Number: Too short"):
        NIE("Y234567T") match
          case error: String => assert(error.startsWith("Invalid NIE Number"))
          case _: NIE        => assert(false)

      test("Invalid NIE Number: Too long"):
        NIE("Y23456789T") match
          case error: String => assert(error.startsWith("Invalid NIE Number"))
          case _: NIE        => assert(false)

      test("Invalid Control Letter"):
        NIE("Y2345678Ñ") match
          case error: String => assert(error.startsWith("Invalid Control Letter"))
          case _: NIE        => assert(false)

      test("Invalid NIE"):
        NIE("X0000001Z") match
          case error: String => assert(error.startsWith("Invalid NIE"))
          case _: NIE        => assert(false)

    test("ID happy path"):

      test("Valid input"):
        Seq(
          ("12345678Z", "12345678-Z"),
          ("00000001R", "00000001-R"),
          ("99999999R", "99999999-R"),
          ("X0000001R", "X0000001-R"),
          ("Y2345678Z", "Y2345678-Z")
        ).foreach:
          case (input, expected) =>
            validateID(input) match
              case id: ID        => assert(id.toUpperCaseWithDash == expected)
              case error: String => assert(false)

      test("Handling"):

        test("white spaces"):
          Seq(
            ("  12345678Z  ", "12345678-Z"),
            ("  X1234567L  ", "X1234567-L")
          ).foreach:
            case (input, expected) =>
              validateID(input) match
                case id: ID    => assert(id.toUpperCaseWithDash == expected)
                case _: String => assert(false)

        test("Dash"):
          Seq(
            ("12345678-Z", "12345678-Z"),
            ("X-1234567-L", "X1234567-L")
          ).foreach:
            case (input, expected) =>
              validateID(input) match
                case id: ID    => assert(id.toUpperCaseWithDash == expected)
                case _: String => assert(false)

        test("Lower case"):
          Seq(
            ("12345678z", "12345678-Z"),
            ("00000001r", "00000001-R"),
            ("99999999r", "99999999-R"),
            ("X0000001r", "X0000001-R"),
            ("Y2345678z", "Y2345678-Z")
          ).foreach:
            case (input, expected) =>
              validateID(input) match
                case id: ID    => assert(id.toUpperCaseWithDash == expected)
                case _: String => assert(false)

    test("ID unhappy path"):
      test("Invalid Input: empty"):
        validateID("         ") match
          case error: String => assert(error.startsWith("Invalid Input"))
          case _: ID         => assert(false)

      test("Invalid Input: invisible characters"):
        validateID("\n\r\t\n\r\t\n\r\t") match
          case error: String => assert(error.startsWith("Invalid Input"))
          case _: ID         => assert(false)

      test("Invalid Input: symbols"):
        validateID("@#¢∞¬÷“”≠") match
          case error: String => assert(error.startsWith("Invalid Input"))
          case _: ID         => assert(false)

      test("Invalid Input: too short"):
        validateID("Y") match
          case error: String => assert(error.startsWith("Invalid Input"))
          case _: ID         => assert(false)

      test("Invalid Input: too long - number"):
        validateID("123456789-Z") match
          case error: String => assert(error.startsWith("Invalid Input"))
          case _: ID         => assert(false)

      test("Invalid Input: too long - underscore"):
        validateID("12345678_Z") match
          case error: String => assert(error.startsWith("Invalid Input"))
          case _: ID         => assert(false)

      test("Invalid Input: too long - dot"):
        validateID("12345678.Z") match
          case error: String => assert(error.startsWith("Invalid Input"))
          case _: ID         => assert(false)
  }
