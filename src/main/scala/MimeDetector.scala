import java.nio.file.{Files, Paths}

object MimeDetector {
  def getMime(fileName: String): String =
    Files.probeContentType(Paths.get(fileName))
}
