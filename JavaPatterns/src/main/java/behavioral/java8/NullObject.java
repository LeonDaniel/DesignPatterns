package behavioral.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Null Object represents the absence of an object by defining a neutral, “do nothing” behavior.
 This approach has an advantage over using null references, because there’s no need to explicitly check the validity of references before usage.
 */
@FunctionalInterface
interface Sound {
    void play();
}

class SoundSource {
    public static Optional<Sound> getSound() {
        boolean available = Math.random() < 0.5;
        return available ? Optional.of( () -> System.out.println("Playing nice music")) : Optional.empty();
    }
}

public class NullObject {

    public static void main(String[] args) {

        IntStream.range(0, 10).
                mapToObj( i -> SoundSource.getSound()).
                forEach(sound -> sound.orElseGet(() ->
                        () -> System.out.println("Piu piu !!")
                ).play());
    }
}
