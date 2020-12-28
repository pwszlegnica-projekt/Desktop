package pl.sebox.shool.shooplist.Window;

import pl.sebox.shool.shooplist.ListManager;
import pl.sebox.shool.shooplist.Log;
import pl.sebox.shool.shooplist.Models.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ListsWindow{
    public JPanel panel1 = new JPanel(new GridLayout(0, 1));
    public JFrame frame = new JFrame("");
    private final ListManager listManager;

    public ListsWindow(ListManager listManager) {
        this.listManager = listManager;
    }

    public void showWindow() {
        refreshList();
    }

    public void refreshList(){
        panel1 = new JPanel(new GridLayout(0, 1));
        for (HashMap.Entry<List, JButton> entry : listManager.listsButtons.entrySet()) {
            entry.getValue().setBackground(Color.white);
            entry.getValue().setBorder(BorderFactory.createEmptyBorder());
            entry.getValue().addActionListener(listManager);
            panel1.add(entry.getValue());
        }

        frame.setContentPane(this.panel1);
        panel1.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }


}
