package src.main.java;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    // Definindo usuário e senha fixos no programa
    private static final String USERNAME = "****";
    private static final String PASSWORD = "****";
    //Sim isso é uma péssima prática 

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                    new MainFrame();
                    dispose();
                } 
                
                
                else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
                }
            }
        });

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userField = new JTextField(20);
        userField.setBounds(100, 20, 165, 25);
        panel.add(userField);

        JLabel passLabel = new JLabel("Senha:");
        passLabel.setBounds(10, 50, 80, 25);
        panel.add(passLabel);

        passField = new JPasswordField(20);
        passField.setBounds(100, 50, 165, 25);
        panel.add(passField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 255, 25);
        panel.add(loginButton);
    }
}
