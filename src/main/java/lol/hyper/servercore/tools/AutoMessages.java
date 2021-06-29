package lol.hyper.servercore.tools;

import java.util.ArrayList;
import java.util.Random;

public class AutoMessages {

    public static final ArrayList<String> messages = new ArrayList<>();
    static int lastMessageID = 0;

    public AutoMessages() {
        messages.add("Want to talk to the homies privately? Use /party!");
        messages.add("This is an automated message, don't worry!");
        messages.add("Post some cool stuff on the subreddit: /r/DestroyMC");
        messages.add("Use /help for all commands.");
    }

    public static String getRandomMessage() {
        Random generator = new Random();
        Object[] values = messages.toArray();
        int random = generator.nextInt(values.length);
        while (random == lastMessageID) {
            random = generator.nextInt(values.length);
        }
        lastMessageID = random;
        return (String) values[generator.nextInt(values.length)];
    }
}
