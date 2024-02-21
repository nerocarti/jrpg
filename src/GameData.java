import java.util.List;
import java.util.ArrayList;
public class GameData {
    private int level;
    private List<String> inventoryItems;

    // Constructor, getters, and setters
    public GameData() {
        this.inventoryItems = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<String> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(List<String> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    // Add methods to manipulate inventory items, etc.
}
