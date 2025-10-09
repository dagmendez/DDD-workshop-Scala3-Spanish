import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object ValidadorObvio:

  private def esLetraCorrecta(numero: Int, letra: Char): Boolean =
    "TRWAGMYFPDXBNJZSQVHLCKE".toCharArray.apply(numero % 23) == letra

  private def esNIFValido(nif: String): Boolean =
    val PATRON_NIF = "^\\d{8}[A-Z]$"
    if nif.matches(PATRON_NIF)
    then esLetraCorrecta(nif.dropRight(1).toInt, nif.last)
    else false

  private def esNIEValido(nif: String): Boolean =
    val PATRON_NIE = "^[XYZ]\\d{7}[A-Z]$"
    if nif.matches(PATRON_NIE)
    then
      val numero_cola = nif.tail.dropRight(1).toInt
      val numero = ("XYZ".toCharArray.indexOf(nif.head) * Math.pow(10, 7).toInt) + numero_cola
      esLetraCorrecta(numero, nif.last)
    else false

  def validarID(id: String): String =
    if id.head match
        case nif if nif.isDigit  => esNIFValido(id)
        case nie if nie.isLetter => esNIEValido(id)
    then id
    else throw new IllegalArgumentException(s"ID: $id invalido")

  @main def run(): Unit =
    println("""
        | *----------------------------*
        | | Validador de IDs Españoles |
        | *----------------------------*
        |
        | Introduce tu ID (NIF o NIE) para ser validado
        |
        | Para detener el programa escribe salir y presiona intro
        |
        |""".stripMargin)

    @tailrec
    def loop(): Unit =
      val inputDelUsuario = scala.io.StdIn.readLine("Introduce un ID: ")
      val comandoDeSalida = "salir"
      if inputDelUsuario.matches(comandoDeSalida) then ()
      else {
        Try(validarID(inputDelUsuario)) match
          case Failure(excepcion) => println(excepcion.getMessage)
          case Success(id)        => println(s"el ID $id es válido")
        loop()
      }

    loop()
