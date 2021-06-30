package lol.hyper.servercore.tools;

import lol.hyper.servercore.ServerCore;

import java.util.ArrayList;
import java.util.Random;

public class AutoMessages {

    private ServerCore serverCore;

    public AutoMessages(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    public final ArrayList<String> messages = new ArrayList<>();
    static int lastMessageID = 0;

    public String getRandomMessage() {
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
