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
        // Add action listener for btnOptions if needed

        btnQuit = new JButton("Quit");
        btnQuit.addActionListener(e -> System.exit(0));

        panel.add(btnStartGame);
        panel.add(btnJoinGame);
        panel.add(btnOptions);
        panel.add(btnQuit);
        add(panel);
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
            System.out.println("Load existing game...");
        } else if (response == 1) {
            System.out.println("Starting a new game...");
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
