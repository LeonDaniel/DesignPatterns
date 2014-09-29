package behavioral;

/**
 * The dependency injection (DI) pattern allows to avoid hard-coded dependencies and
 * to substitute dependencies either at run-time or at compile time.
 * The pattern is a special case of inversion of control (IoC) technique.
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

interface Repository {
    void save(User user);
}

class DatabaseRepository implements Repository {
    @Override
    public void save(User user) {
        System.out.println("Saving in database repository user " + user.getName());
    }
}

class FileRepository implements Repository {
    @Override
    public void save(User user) {
        System.out.println("Saving in file repository user " + user.getName());
    }
}

class UserService {
    private final Repository repository;

    UserService(Repository repository) {
        this.repository = repository;
    }

    void create(User user) {
        repository.save(user);
    }
}
public class DependencyInjection {

    public static void main(String [] args) {
        User user = new User("Gigi");
        UserService databaseService = new UserService(new DatabaseRepository());
        UserService fileService = new UserService(new FileRepository());

        databaseService.create(user);
        fileService.create(user);
    }
}
