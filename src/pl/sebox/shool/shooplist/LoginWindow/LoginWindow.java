package pl.sebox.shool.shooplist.LoginWindow;

import javax.swing.*;
import java.awt.*;

public class LoginWindow {
    public static Credentials login(JFrame frame){
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JTextField login = new JTextField("Login");
        panel.add(login);
        JTextField password = new JTextField("Login");
        panel.add(password);

        JButton doLogin = new JButton("Loguj");
        doLogin.setBorder(BorderFactory.createEmptyBorder());
        panel.add(doLogin);

        JButton doRegister = new JButton("Zarejestruj");
        doLogin.setBorder(BorderFactory.createEmptyBorder());
        panel.add(doRegister);

        frame.setContentPane(panel);
        frame.setVisible(true);
        Credentials credentials = new Credentials();
        credentials.login = "a@example.com";
        credentials.password = "123456789";
        return credentials;
    }
}
