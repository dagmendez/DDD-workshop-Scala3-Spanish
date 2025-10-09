import scala.util.*

object CaskServer extends cask.MainRoutes:

  private val corsHeaders = Seq(
    "Access-Control-Allow-Origin" -> "*",
    "Access-Control-Allow-Methods" -> "POST, OPTIONS",
    "Access-Control-Allow-Headers" -> "Content-Type",
    "Access-Control-Max-Age" -> "86400"
  )

  private def respuestaSatisfactoria(formattedId: String): cask.Response[String] =
    cask.Response(
      formattedId,
      statusCode = 200,
      headers = corsHeaders
    )

  private def respuestaErronea(errorMessage: String): cask.Response[String] =
    cask.Response(
      errorMessage,
      statusCode = 400,
      headers = corsHeaders
    )

  @cask.options("/*")
  def handleOptions(): cask.Response[String] =
    cask.Response("", headers = corsHeaders)

  @cask.get("/")
  def saludo(): cask.Response[String] =
    cask.Response(
      "Bienvenidos al taller DDD con Scala 3!",
      headers = corsHeaders
    )

  @cask.get("/dame_informacion")
  def noInfo(): cask.Response[String] =
    cask.Response(
      "No tengo información que darte en este momento",
      headers = corsHeaders
    )

  @cask.post("/obvio")
  def obvio(request: cask.Request): cask.Response[String] =
    Try(ValidadorObvio.validarID(request.text())) match
      case Success(result) => respuestaSatisfactoria(result)
      case Failure(error)  => respuestaErronea(error.getMessage)

  @cask.options("/obvio")
  def opcionesObvio(): cask.Response[String] =
    cask.Response("Opciones de Obvio", headers = corsHeaders)

  initialize()
