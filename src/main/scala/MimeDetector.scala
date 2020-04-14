import java.nio.file.spi.FileTypeDetector
import java.nio.file.{Files, Path, Paths}

object MimeDetector {
  def getMime(fileName: String): String = {
    val path = Paths.get(fileName)

    getContentType(path) match {
      case Some(mime) => mime
      case None => HtmlMimeDetector.getContentType(path) match {
        case Some(mime) => mime
        case None => null
      }
    }
  }

  def getContentType(path: Path): Option[String] =
    Option(Files.probeContentType(path))
}

object HtmlMimeDetector {
  def getContentType(path: Path): Option[String] = {
    Option(HtmlFileTypeDetector.probeContentType(path))
  }
}

object HtmlFileTypeDetector extends FileTypeDetector {
  override def probeContentType(path: Path): String = {
    if (path.getFileName.toString.endsWith(".html")) "text/html; charset=utf8"
    else null
  }
}
