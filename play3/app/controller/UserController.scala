package controller

import model.User
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.libs.ws._

import java.util.UUID
import javax.inject.{Inject, Singleton}

@Singleton
class UserController @Inject()(ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {
  def home(): Action[AnyContent] = Action {
    Ok(Json.toJson(User(1L, "Martin")))
  }

  def findById(id: Long): Action[AnyContent] = Action {
    Ok(Json.toJson(User(id, UUID.randomUUID().toString)))
  }
}
