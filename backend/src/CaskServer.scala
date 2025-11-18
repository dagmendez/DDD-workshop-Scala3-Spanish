import scala.util.*
object CaskServer extends cask.MainRoutes:

  private val corsHeaders = Seq(
    "Access-Control-Allow-Origin" -> "*",
    "Access-Control-Allow-Methods" -> "POST, OPTIONS",
    "Access-Control-Allow-Headers" -> "Content-Type",
    "Access-Control-Max-Age" -> "86400"
  )

  private def success(formattedId: String): cask.Response[String] = cask.Response(
    formattedId,
    statusCode = 200,
    headers = corsHeaders
  )

  private def failure(errorMessage: String): cask.Response[String] = cask.Response(
    errorMessage,
    statusCode = 400,
    headers = corsHeaders
  )

  @cask.options("/*")
  def handleOptions(): cask.Response[String] = cask.Response("", headers = corsHeaders)

  @cask.get("/")
  def greeting(): cask.Response[String] = cask.Response(
    "Welcome to DDD workshop!",
    headers = corsHeaders
  )

  @cask.get("/give_me_info")
  def noInfo(): cask.Response[String] = cask.Response(
    "No info for you Sir.",
    headers = corsHeaders
  )

  @cask.post("/poc")
  def poc(request: cask.Request): cask.Response[String] =
    ProofOfConcept.validateID(request.text()) match
      case id: api.ID    => success(id.toUpperCaseWithDash)
      case error: String => failure(error)

  @cask.options("/poc")
  def optionsPoc(): cask.Response[String] =
    cask.Response("Options of PoC", headers = corsHeaders)

  @cask.post("/mvp")
  def mvp(request: cask.Request): cask.Response[String] =
    MinimumViableProduct.validateID(request.text()) match
      case id: api.ID    => success(id.toUpperCaseWithDash)
      case error: String => failure(error)

  @cask.options("/mvp")
  def optionsMvp(): cask.Response[String] =
    cask.Response("Options of MVP", headers = corsHeaders)

  @cask.post("/refined")
  def refined(request: cask.Request): cask.Response[String] =
    Refined.validateID(request.text()) match
      case id: api.ID    => success(id.toUpperCaseWithDash)
      case error: String => failure(error)

  @cask.options("/refined")
  def optionsSuperproduct(): cask.Response[String] =
    cask.Response("Options of Refined", headers = corsHeaders)

  initialize()
