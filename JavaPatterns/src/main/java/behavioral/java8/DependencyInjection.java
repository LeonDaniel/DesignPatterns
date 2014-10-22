package behavioral.java8;

import java.util.function.Consumer;

/**
 * The dependency injection (DI) pattern allows to avoid hard-coded dependencies and
 * to substitute dependencies either at run-time or at compile time.
 * The pattern is a special case of inversion of control (IoC) technique.
 *
 * For the simple cases where we want to inject only one behavior that can be enclosed in a lambda, we can use
 * a consumer function that takes the resource instance and uses it in the processing
 *
 * @author Daniel Leon
 */

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class UserService {
    private final Consumer<User> repository;

    UserService(Consumer<User> repository) {
        this.repository = repository;
    }

    void create(User user) {
        repository.accept(user);
    }
}

public class DependencyInjection {

    public static void main(String [] args) {
        User Frank = new User("Frank");
        UserService databaseService = new UserService(user -> System.out.println("Saving in database repository user : " + user.getName()));
        UserService fileService = new UserService(user -> System.out.println("Saving in file repository user : " + user.getName()));

        databaseService.create(Frank);
        fileService.create(Frank);
    }
}
