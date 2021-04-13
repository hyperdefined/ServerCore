package lol.hyper.servercore.tools;

import java.util.HashMap;
import java.util.Random;

public class AutoMessages {

    public static HashMap<Integer, String> messages = new HashMap<>();
    static int lastMessageID = 0;

    public AutoMessages() {
        messages.put(1, "Make sure to /vote! Voting gives you a blue name. You also get 2 dupe charges!");
        messages.put(2, "Hey retards, check out the subreddit!");
        messages.put(3, "THE MAP IS NOT GOING TO RESET ANYMORE.");
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
