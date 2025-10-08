import Dominio.errores.*
import Dominio.invariantes.LetraDeControl
import ValidadorCorrectisimo.*
import utest.*

object PruebasValidadorCorrectisimo extends TestSuite:

  val tests = Tests {

    test("NIF"):

      test("Camino feliz"):
        Seq(
          ("12345678Z", "12345678-Z"),
          ("00000001R", "00000001-R"),
          ("99999999R", "99999999-R")
        ).foreach:
          case (input, esperado) =>
            NIF
              .disyuntiva(input)
              .foreach: nif =>
                assert(nif.formateado == esperado)

      test("Camino Infeliz"):

        test("Numero NIF invalido: demasiado corto"):
          NIF.disyuntiva("1234567T") match
            case Left(error) => assert(error == NumeroNIFInvalido("1234567"))
            case Right(_)    => assert(false)

        test("Numero NIF invalido: demasiado largo"):
          NIF.disyuntiva("123456789T") match
            case Left(error) => assert(error == NumeroNIFInvalido("123456789"))
            case Right(_)    => assert(false)

        test("Numero NIF invalido: contiene una letra"):
          NIF.disyuntiva("1234567AT") match
            case Left(error) => assert(error == NumeroNIFInvalido("1234567A"))
            case Right(_)    => assert(false)

        test("Letra de control invalida"):
          NIF.disyuntiva("12345678Ñ") match
            case Left(error) => assert(error == LetraDeControlInvalida("Ñ"))
            case Right(_)    => assert(false)

        test("Numero NIF invalido: no coincide con la letra de control"):
          NIF.disyuntiva("00000001Z") match
            case Left(error) => assert(error == NIFInvalido("00000001", LetraDeControl.Z))
            case Right(_)    => assert(false)

    test("NIE"):

      test("Camino feliz"):
        Seq(
          ("X0000001R", "X-0000001-R"),
          ("Y2345678Z", "Y-2345678-Z")
        ).foreach:
          case (input, esperado) =>
            NIE
              .disyuntiva(input)
              .foreach: result =>
                assert(result.formateado == esperado)

      test("Camino infeliz"):

        test("Letra del NIE invalida"):
          NIE.disyuntiva("A1234567T") match
            case Left(error) => assert(error == LetraNIEInvalida("A"))
            case Right(_)    => false

        test("Numero NIE invalido: demasiado corto"):
          NIE.disyuntiva("Y234567T") match
            case Left(error) => assert(error == NumeroNIEInvalido("234567"))
            case Right(_)    => false

        test("Numero NIE invalido: demasiado largo"):
          NIE.disyuntiva("Y23456789T") match
            case Left(error) => assert(error == NumeroNIEInvalido("23456789"))
            case Right(_)    => false

        test("Numero NIE invalido: contiene una letra"):
          NIE.disyuntiva("Y234567AT") match
            case Left(error) => assert(error == NumeroNIEInvalido("234567A"))
            case Right(_)    => false

        test("NIE invalido: letra de control invalida"):
          NIE.disyuntiva("Y2345678Ñ") match
            case Left(error) => assert(error == LetraDeControlInvalida("Ñ"))
            case Right(_)    => false

    test("IDs"):

      test("Mejorando experiencia de usuario"):

        test("Espacios en blanco"):
          Seq(
            ("  12345678Z  ", "12345678-Z"),
            ("  X1234567L  ", "X-1234567-L")
          ).foreach:
            case (input, esperado) =>
              ID.disyuntiva(input)
                .foreach: id =>
                  assert(id.formateado == esperado)

        test("Guiones"):
          Seq(
            ("12345678-Z", "12345678-Z"),
            ("X-1234567-L", "X-1234567-L")
          ).foreach:
            case (input, esperado) =>
              ID.disyuntiva(input)
                .foreach: id =>
                  assert(id.formateado == esperado)

        test("lower case handling"):
          Seq(
            ("12345678z", "12345678-Z"),
            ("00000001r", "00000001-R"),
            ("99999999r", "99999999-R"),
            ("X0000001r", "X-0000001-R"),
            ("Y2345678z", "Y-2345678-Z")
          ).foreach:
            case (input, esperado) =>
              ID.disyuntiva(input)
                .foreach: id =>
                  assert(id.formateado == esperado)

    test("Camino infeliz"):

      test("Input Invalido: vacio"):
        ID.disyuntiva("") match
          case Left(error) => assert(error == InputInvalido(""))
          case Right(_)    => false

      test("Input Invalido: caracteres invisibles"):
        ID.disyuntiva("\n\r\t") match
          case Left(error) => assert(error == InputInvalido("\n\r\t"))
          case Right(_)    => false

      test("Input Invalido: simbolos"):
        ID.disyuntiva("@#¢∞¬÷“”≠") match
          case Left(error) => assert(error == InputInvalido("@#¢∞¬÷“”≠"))
          case Right(_)    => false

      test("Input Invalido: demasiado corto"):
        ID.disyuntiva("Y") match
          case Left(error) => assert(error == InputInvalido("Y"))
          case Right(_)    => false

      test("Input Invalido: numero demasiado largo"):
        ID.disyuntiva("123456789-Z") match
          case Left(error) => assert(error == InputInvalido("123456789-Z"))
          case Right(_)    => assert(false)

      test("Input Invalido: demasiado largo por guion bajo"):
        ID.disyuntiva("12345678_Z") match
          case Left(error) => assert(error == InputInvalido("12345678_Z"))
          case Right(_)    => assert(false)

      test("Input Invalido: demasiado largo por punto"):
        ID.disyuntiva("12345678.Z") match
          case Left(error) => assert(error == InputInvalido("12345678.Z"))
          case Right(_)    => assert(false)

  }
