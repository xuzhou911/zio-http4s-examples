package zhx.client

import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s._
import zhx.auth.Authenticator
import zhx.servers.Hello1Service
import zhx.servers.Middlewares.{hello2Service, withMiddleware}
import zio.{IO, RIO, Task, ZIO}
import zio.test.Assertion.equalTo
import zio.test.{DefaultRunnableSpec, assert, assertM, suite, testM}
import zio.interop.catz._
import zio.IO._

import scala.concurrent.ExecutionContext.Implicits.global

object TestHello1 extends DefaultRunnableSpec(

  suite("routes suite")(
    testM("root request returns Ok") {
      ZIO.runtime[Any].flatMap { implicit rts =>
        BlazeClientBuilder[Task](global).resource.use { client =>
          val req = Request[Task](Method.GET, uri"http://localhost:8080/")
          assertM(client.status(req), equalTo(Status.Ok))
        }
      }
    },
  )
)