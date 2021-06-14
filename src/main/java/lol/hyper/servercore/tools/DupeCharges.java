package lol.hyper.servercore.tools;

import lol.hyper.servercore.ServerCore;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public record DupeCharges(ServerCore serverCore) {

    private JSONObject readFile(File file) {
        if (!file.exists()) {
            return null;
        }
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            FileReader reader = new FileReader(file);
            obj = parser.parse(reader);
            reader.close();
        } catch (IOException | ParseException e) {
            serverCore.logger.severe("Unable to read file " + file.getAbsolutePath());
            serverCore.logger.severe("This is bad, really bad.");
            e.printStackTrace();
        }
        return (JSONObject) obj;
    }

    /**
     * Write data to JSON file.
     *
     * @param file        File to write data to.
     * @param jsonToWrite Data to write to file. This much be a JSON string.
     */
    private void writeFile(File file, String jsonToWrite) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(jsonToWrite);
            writer.close();
        } catch (IOException e) {
            serverCore.logger.severe("Unable to write file " + file.getAbsolutePath());
            serverCore.logger.severe("This is bad, really bad.");
            e.printStackTrace();
        }
    }

    public long getDupeCharges(UUID player) {
        File fuck = new File(String.valueOf(serverCore.dupeCharges), player.toString() + ".json");
        JSONObject file = readFile(fuck);
        if (file == null) {
            return 0;
        }
        return (long) file.get("dupecharges");
    }

    public void updateCharges(UUID player, long i) {
        File fuck = new File(String.valueOf(serverCore.dupeCharges), player.toString() + ".json");
        JSONObject file = readFile(fuck);
        if (file == null) {
            file = new JSONObject();
        }
        file.remove("dupecharges");
        file.put("dupecharges", i);
        writeFile(fuck, file.toJSONString());
    }
}
