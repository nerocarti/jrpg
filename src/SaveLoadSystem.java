import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoadSystem {

    private static final String SAVE_FILE = "gameSave.json";

    public static void saveGame(GameData gameData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(gameData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameData loadGame() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(SAVE_FILE)) {
            return gson.fromJson(reader, GameData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Consider returning a new GameData instance instead
        }
    }
}
