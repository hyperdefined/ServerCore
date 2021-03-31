package lol.hyper.servercore.tools;

import java.util.HashMap;
import java.util.Random;

public class AutoMessages {

    public static HashMap<Integer, String> messages = new HashMap<>();
    static int lastMessageID = 0;

    public AutoMessages() {
        messages.put(1, "Make sure to /vote! Voting gives you a blue name.");
        messages.put(2, ":3");
        messages.put(3, "This is an automated message.");
        messages.put(4, "The server is not final. Things are going to change in the future.");
        messages.put(5, "1.17 update? Probably.");
        messages.put(6, "owo");
        messages.put(7, "xD");
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
