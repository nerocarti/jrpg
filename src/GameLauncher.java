import javax.swing.*;
import java.awt.*;

public class GameLauncher extends JFrame {

    private JButton btnStartGame, btnJoinGame, btnOptions, btnQuit;
    private JPanel panel;

    public GameLauncher() {
        createComponents();
        setLayout(new FlowLayout());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JRPG Game Launcher");
        setVisible(true);
    }

    private void createComponents() {
        panel = new JPanel();

        btnStartGame = new JButton("Start Game");
        btnStartGame.addActionListener(e -> handleStartGame());

        btnJoinGame = new JButton("Join Game");
        btnJoinGame.addActionListener(e -> handleJoinGame());

        btnOptions = new JButton("Options/Settings");
        btnOptions.addActionListener(e -> openOptionsMenu());
        // Add action listener for btnOptions if needed

        btnQuit = new JButton("Quit");
        btnQuit.addActionListener(e -> System.exit(0));

        panel.add(btnStartGame);
        panel.add(btnJoinGame);
        panel.add(btnOptions);
        panel.add(btnQuit);
        add(panel);
    }
    private void openOptionsMenu() {
        JDialog optionsMenu = new JDialog(this, "Options/Settings", true);
        optionsMenu.setLayout(new FlowLayout());
        optionsMenu.setSize(300, 200);

        JButton videoButton = new JButton("Video");
        videoButton.addActionListener(e -> openVideoSettings());
        JButton audioButton = new JButton("Audio");
        audioButton.addActionListener(e -> openAudioSettings());
        JButton accountButton = new JButton("Account");
        accountButton.addActionListener(e -> openAccountSettings());

        optionsMenu.add(videoButton);
        optionsMenu.add(audioButton);
        optionsMenu.add(accountButton);

        optionsMenu.setVisible(true);
    }
    private void openVideoSettings() {
        String[] options = {"Fullscreen", "Windowed", "Borderless Windowed"};
        String selected = (String) JOptionPane.showInputDialog(this, "Select display mode:",
                "Video Settings", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        // Apply the selected video setting
    }
    private void openAudioSettings() {
        JSlider volumeSlider = new JSlider(0, 100);
        JOptionPane.showMessageDialog(this, volumeSlider, "Audio Settings: Adjust Volume", JOptionPane.PLAIN_MESSAGE);
        int volume = volumeSlider.getValue();
        // Apply the volume setting
    }
    private void openAccountSettings() {
        Object[] options = {"Change Username", "Change Password"};
        int result = JOptionPane.showOptionDialog(this, "Choose an option:",
                "Account Settings", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (result == 0) {
            // Code to change username
            String newUsername = JOptionPane.showInputDialog(this, "Enter new username:");
            // Apply the new username
        } else if (result == 1) {
            // Code to change password
            String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
            // Apply the new password
        }
    }

    private void handleStartGame() {
        String[] options = {"Offline", "Multiplayer"};
        int response = JOptionPane.showOptionDialog(this, "Choose your game mode:", "Game Mode",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (response == 0) {
            setupOfflineGame();
        } else if (response == 1) {
            setupMultiplayerGame();
        }
    }

    private void setupOfflineGame() {
        String[] options = {"Load Game", "New Game"};
        int response = JOptionPane.showOptionDialog(this, "Do you want to load a saved game or start a new one?",
                "Game Setup", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (response == 0) {
            // Load existing game...
            GameData gameData = SaveLoadSystem.loadGame();
            if (gameData == null) {
                gameData = new GameData(); // Consider creating a new game if no save exists
            }
            // Proceed to use the loaded game data
        } else if (response == 1) {
            // Starting a new game...
            GameData gameData = new GameData(); // Initialize with default or new values
            // Setup game data as needed
            SaveLoadSystem.saveGame(gameData); // Save the new game data
        }
    }


    private void setupMultiplayerGame() {
        String roomCode = generateRoomCode();
        JOptionPane.showMessageDialog(this, "Your room code is: " + roomCode);
        System.out.println("Multiplayer game setup with room code: " + roomCode);
    }

    private String generateRoomCode() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    private void handleJoinGame() {
        String roomCode = JOptionPane.showInputDialog(this, "Enter the room code to join:");
        if (roomCode != null && !roomCode.isEmpty()) {
            joinGame(roomCode);
        }
    }

    private void joinGame(String roomCode) {
        System.out.println("Joining game with room code: " + roomCode);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameLauncher());
    }
}
