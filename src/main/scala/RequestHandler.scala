import java.nio.file.{Files, Path, Paths}

object RequestHandler {
  val BadRequestHtmlPath: Path = Paths.get("public/400.html")
  val ForbiddenHtmlPath: Path = Paths.get("public/403.html")
  val NotFountHtmlPath: Path = Paths.get("public/404.html")
  val HtmlMime: String = "text/html;charset=utf8"

  def handleRequest(request: Request): Response = {
    val normalizedPath = Paths.get("public", request.targetPath).normalize()
    val path =
      if (Files.isDirectory(normalizedPath)) normalizedPath.resolve("index.html")
      else normalizedPath

    if (!path.startsWith("public/")) {
      Response(Forbidden, HtmlMime, Files.readAllBytes(ForbiddenHtmlPath))
    } else if (!Files.exists(path)) {
      Response(NotFound, HtmlMime, Files.readAllBytes(NotFountHtmlPath))
    } else {
      Response(Ok, MimeDetector.getMime(path.getFileName.toString), Files.readAllBytes(path))
    }
  }
}
