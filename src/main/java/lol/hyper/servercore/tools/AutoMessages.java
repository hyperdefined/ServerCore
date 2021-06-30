package lol.hyper.servercore.tools;

import java.util.ArrayList;
import java.util.Random;

public class AutoMessages {

    public final ArrayList<String> messages = new ArrayList<>();
    private int lastMessageID;

    public String getRandomMessage() {
        Random generator = new Random();
        Object[] values = messages.toArray();
        int random = generator.nextInt(values.length);
        while (random == lastMessageID) {
            random = generator.nextInt(values.length);
        }
        lastMessageID = random;
        return (String) values[random];
    }
}
