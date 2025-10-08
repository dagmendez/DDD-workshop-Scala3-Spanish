import ValidadorObvio.validarID
import utest.*

object PruebasValidadorObvio extends TestSuite:

  val tests = Tests {

    test("NIF"):

      test("Camino feliz"):
        Seq(
          ("12345678Z", "12345678Z"),
          ("00000001R", "00000001R"),
          ("99999999R", "99999999R")
        ).foreach:
          case (input, esperado) => assert(validarID(input) == esperado)

      test("Camino infeliz"):

        test("Numero NIF invalido: demasiado corto"):
          assertThrows[IllegalArgumentException](validarID("1234567T"))

        test("Numero NIF invalido: demasiado largo"):
          assertThrows[IllegalArgumentException](validarID("123456789T"))

        test("Numero NIF invalido: contiene una letra"):
          assertThrows[IllegalArgumentException](validarID("1234567AT"))

        test("Letra de control invalida"):
          assertThrows[IllegalArgumentException](validarID("12345678Ñ"))

        test("Numero NIF invalido: no coincide con la letra de control"):
          assertThrows[IllegalArgumentException](validarID("00000001Z"))

    test("NIE"):

      test("Camino feliz"):
        Seq(
          ("X0000001R", "X0000001R"),
          ("Y2345678Z", "Y2345678Z")
        ).foreach:
          case (input, esperado) => assert(validarID(input) == esperado)

      test("Camino infeliz"):

        test("Letra del NIE invalida"):
          assertThrows[IllegalArgumentException](validarID("A1234567T"))

        test("Numero NIE invalido: demasiado corto"):
          assertThrows[IllegalArgumentException](validarID("Y234567T"))

        test("Numero NIE invalido: demasiado largo"):
          assertThrows[IllegalArgumentException](validarID("Y23456789T"))

        test("Numero NIE invalido: contiene una letra"):
          assertThrows[IllegalArgumentException](validarID("Y234567AT"))

        test("Letra de control invalida"):
          assertThrows[IllegalArgumentException](validarID("Y2345678Ñ"))

        test("NIE invalido: no coincide con la letra de control"):
          assertThrows[IllegalArgumentException](validarID("X0000001Z"))
  }
