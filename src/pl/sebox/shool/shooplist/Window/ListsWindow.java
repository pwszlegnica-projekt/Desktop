package pl.sebox.shool.shooplist.Window;

import pl.sebox.shool.shooplist.Models.List;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ListsWindow {
    public JPanel panel1 = new JPanel(new GridLayout(0, 1));
    public JFrame frame = new JFrame("");
    private HashMap<List, JButton> lists = new HashMap<>();

    public ListsWindow() {
    }

    public void showWindow() {
        for (HashMap.Entry<List, JButton> entry : lists.entrySet()) {
            lists.put(entry.getKey(), new JButton(entry.getKey().name));
            entry.getValue().setBackground(Color.white);
            entry.getValue().setBorder(BorderFactory.createEmptyBorder());
            panel1.add(entry.getValue());
        }

        frame.setContentPane(this.panel1);
        panel1.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
//        frame.pack();
        frame.setVisible(true);
    }

    public void setLists(ArrayList<List> lists) {
        for (List entry : lists) {
            this.lists.put(entry, new JButton(entry.name));
        }
    }


}
