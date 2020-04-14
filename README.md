# http-server

## Usage

```sh
$ sbt run
$ curl -i http://localhost:8080
HTTP/1.1 200 OK
Date: Tue, 14 Apr 2020 15:06:06 GMT
Server: HttpServer
Content-Type: text/html; charset=utf8
Content-Length: 145
Connection: Close

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>HTTP Server</title>
</head>
<body>
<h1>200 OK</h1>
</body>
</html>
```
