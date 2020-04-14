import java.io.{BufferedReader, InputStream, InputStreamReader}

class RequestParser {
  def fromInputStream(in: InputStream): Option[Request] = {
    val reader = new BufferedReader(new InputStreamReader(in))
    val requestLine = reader.readLine()

    Option(requestLine).map { line =>
      val Array(method, targetPath, httpVersion) = line.split("\\s")
      Request(method, targetPath, httpVersion)
    }
  }
}

case class Request(
  method: String,
  targetPath: String,
  httpVersion: String,
)
