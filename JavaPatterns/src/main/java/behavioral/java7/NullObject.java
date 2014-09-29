package behavioral.java7;

import java.util.ArrayList;
import java.util.List;

/**
 * Null Object represents the absence of an object by defining a neutral, “do nothing” behavior.
 This approach has an advantage over using null references, because there’s no need to explicitly check the validity of references before usage.
 */
interface Sound {
    void play();
}

class Music implements Sound {
    public void play() {
        System.out.println("Playing music");
    }
}

class NullSound implements Sound {
    private static NullSound instance;
    private NullSound() {}

    public static NullSound getInstance() {
        if (instance == null) {
            instance = new NullSound();
        }
        return instance;
    }

    public void play() {
        System.out.println("Piu piu!!!");
    }
}

class SoundSource {
    public static Sound getSound() {
        boolean available = Math.random() < 0.5;
        return available ? new Music() : NullSound.getInstance();
    }
}
public class NullObject {

    public static void main(String[] args) {

        List<Sound> list = new ArrayList<>();
        for(int i = 0 ; i < 10; i++) {
            list.add(SoundSource.getSound());
        }
        list.forEach( sound -> sound.play());
    }

}
