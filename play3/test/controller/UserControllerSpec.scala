package controller

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

class UserControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  "HomeController GET" should {
    "render the home page from a new instance of the controller" in {
      val controller = new UserController(stubControllerComponents())
      val home = controller.home().apply(FakeRequest(GET, "/home"))

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include("{\"id\":1,\"name\":\"Martin\"}")
    }

    "render the home page from the application" in {
      val controller = inject[UserController]
      val home = controller.home().apply(FakeRequest(GET, "/home"))

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include("{\"id\":1,\"name\":\"Martin\"}")
    }

    "render the home page from the router" in {
      val request = FakeRequest(GET, "/home")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include("{\"id\":1,\"name\":\"Martin\"}")
    }
  }
}
