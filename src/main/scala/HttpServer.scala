import java.net.ServerSocket

import IOUtil._
import RequestHandler._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HttpServer extends App {
  val Port = 8080

  val serverSocket = new ServerSocket(Port)
  val parser = new RequestParser

  println(s"HTTP Server Start! Listening at ${Port}!")

  while (true) {
    val socket = serverSocket.accept()

    Future {
      using(socket) { s =>
        val in = s.getInputStream
        val out = s.getOutputStream

        val request = parser.fromInputStream(in)
        request.foreach(r => println(s"${r.method} ${r.targetPath} ${r.httpVersion}"))
        val response = request.map(handleRequest)
        response.foreach(_.writeTo(out))
      }
    }
  }
}
