package behavioral

/**
 * In addition to composition (“HAS-A”) and inheritance (“IS-A”), Scala offers a special kind of object relation – requirement (“REQUIRES-A”),
 * expressed as self-type annotations. Self-types let us specify additional type expectations for an object, without exposing them in the inheritance hierarchy.
 * We can use self-type annotations together with traits to implement dependency injection
 *
 * @author Daniel Leon
 */
object DependencyInjection {

  case class User(name  : String)

  trait Repository {
    def save(user: User)
  }

  trait DatabaseRepository extends Repository {
    override def save(user : User) = {
      println("Saving in database repository user : " + user.name)
    }
  }

  trait FileRepository extends Repository {
    override def save(user : User) = {
      println("Saving in file repository user : " + user.name)
    }
  }

 class UserService { self: Repository => // requires Repository
    def create(user: User) {
      save(user)
    }
  }

  def main(args : Array[String]) {

    val frank = User("Frankie")
    val userService = new UserService with DatabaseRepository
    val fileService = new UserService with FileRepository

    userService.create(frank)
    fileService.create(frank)
  }
}
