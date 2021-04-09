package lol.hyper.servercore.tools;

import java.util.HashMap;
import java.util.Random;

public class AutoMessages {

    public static HashMap<Integer, String> messages = new HashMap<>();
    static int lastMessageID = 0;

    public AutoMessages() {
        messages.put(1, "Make sure to /vote! Voting gives you a blue name.");
        messages.put(2, "Every 25 votes, a dupe is enabled for 48 days.");
        messages.put(3, "This map will reset on June 12th.");
    }

    public static String getRandomMessage(){
        Random generator = new Random();
        Object[] values = messages.values().toArray();
        int random = generator.nextInt(values.length);
        while (random == lastMessageID) {
            random = generator.nextInt(values.length);
        }
        lastMessageID = random;
        return (String) values[generator.nextInt(values.length)];
    }
}
