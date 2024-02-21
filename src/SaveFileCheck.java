import java.io.File;

public class SaveFileCheck {

    public static boolean checkSaveFile(String filePath) {
        File file = new File(filePath);

        // Check if the file exists
        if (file.exists()) {
            // Check if the file is not empty
            if (file.length() > 0) {
                System.out.println("Save file exists and is not empty.");
                return true;
            } else {
                System.out.println("Save file exists but is empty.");
                return false;
            }
        } else {
            System.out.println("Save file does not exist.");
            return false;
        }
    }

    public static void main(String[] args) {
        // Example usage
        String filePath = "gameSave.json"; // Path to your save file
        boolean result = checkSaveFile(filePath);
        // You can use 'result' to take further actions
    }
}
