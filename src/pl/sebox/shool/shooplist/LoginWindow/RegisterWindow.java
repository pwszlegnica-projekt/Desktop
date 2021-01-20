package pl.sebox.shool.shooplist.LoginWindow;

import pl.sebox.shool.shooplist.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow implements ActionListener {
    public static JTextField login;
    public static JTextField mail;
    public static JPasswordField password;

    public static void register(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        login = new JTextField("Imie");
        panel.add(login);
        mail = new JTextField("Email");
        panel.add(mail);
        password = new JPasswordField("Hsało");
        panel.add(password);


        JButton doRegister = new JButton("Zarejestruj");
        doRegister.setBorder(BorderFactory.createEmptyBorder());
        panel.add(doRegister);
        doRegister.addActionListener(new RegisterWindow());

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
