package controller

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.libs.ws.WSClient

import javax.inject.Inject

class WsClientController @Inject()(ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {
  def findByIdTest(): Action[AnyContent] = {
    val users: Seq[String] = Range.inclusive(1, 100_000).map { i =>
      ws
        .url(s"http://localhost:9000/user/$i")
        .addHeader("Accept", "application/json")
        .get()
        .toCompletableFuture
        .get()
        .getBody
    }

    Action {
      Ok(users.last)
    }
  }
}
