package pl.sebox.shool.shooplist.LoginWindow;

import org.json.JSONObject;
import pl.sebox.decathlon.tools.Network;
import pl.sebox.decathlon.tools.network.HttpResponse;
import pl.sebox.shool.shooplist.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginWindow implements ActionListener {
    public static JButton doLogin;
    public static JButton doRegister;
    public static JFrame frame;
    public static JTextField login;
    public static JPasswordField password;

    public static void login(JFrame frame) {
        LoginWindow.frame = frame;
        JPanel panel = new JPanel(new GridLayout(0, 1));

        login = new JTextField("Email");
        panel.add(login);
        password = new JPasswordField("Hsało");
        panel.add(password);

        doLogin = new JButton("Loguj");
        doLogin.setBorder(BorderFactory.createEmptyBorder());
        panel.add(doLogin);

        doRegister = new JButton("Zarejestruj");
        doRegister.setBorder(BorderFactory.createEmptyBorder());
        panel.add(doRegister);

        doLogin.addActionListener(new LoginWindow());
        doRegister.addActionListener(new LoginWindow());

        frame.setContentPane(panel);
        frame.setVisible(true);
        Credentials credentials = new Credentials();
        while (Credentials.login == null || Credentials.password == null) {
//        while (Credentials.token == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoginWindow.doLogin) {

            Credentials.login = LoginWindow.login.getText();
            Credentials.password = LoginWindow.password.getText();
            try {
                authorize(LoginWindow.login.getText(), LoginWindow.password.getText());
            } catch (Exception ignore){}
        }
        if (e.getSource() == LoginWindow.doRegister) {
            RegisterWindow.register(LoginWindow.frame);
        }
    }

    private void authorize(String login, String pass) {
        HashMap<String, String> pahrms = new HashMap<>();
        pahrms.put("email", login);
        pahrms.put("password", pass);
        Log.add(pahrms.toString());
        HttpResponse response = Network.post("http://127.0.0.1:8080/api/login", pahrms);
        JSONObject jo = new JSONObject(response.body);
        Log.add(jo.toString());


        Credentials.login = login;
        Credentials.password = pass;
    }
}
