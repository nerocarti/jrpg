import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JTextField userTextField = new JTextField();
    JLabel passwordLabel = new JLabel("PASSWORD");
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton createAccountButton = new JButton("Create Account");
    UserDao userDao = new UserDao(); // Assuming UserDao handles database operations

    public LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        initializeFrame();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        passwordField.setBounds(150, 220, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        createAccountButton.setBounds(200, 300, 150, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(userTextField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(loginButton);
        container.add(createAccountButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(e -> attemptLogin());
        createAccountButton.addActionListener(e -> openAccountCreationDialog());
    }

    private void attemptLogin() {
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());
        if (userDao.validateUser(username, password)) {
            transitionToGameLauncher();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openAccountCreationDialog() {
        JFrame frame = new JFrame("Create Account");
        JLabel userLabel = new JLabel("Username:");
        JTextField userTextField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());
                userDao.createUser(username, password);  // Assuming 'createUser' adds a new user
                frame.dispose();
                transitionToGameLauncher();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(userLabel);
        frame.add(userTextField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(submitButton);
        frame.pack();
        frame.setVisible(true);
    }

    private void transitionToGameLauncher() {
        new GameLauncher().setVisible(true);
        this.dispose();
    }

    private void initializeFrame() {
        setTitle("Login Form");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] a) {
        new LoginFrame();
    }
}
