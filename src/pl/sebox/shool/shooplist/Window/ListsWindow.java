package pl.sebox.shool.shooplist.Window;

import pl.sebox.shool.shooplist.ListManager;
import pl.sebox.shool.shooplist.Models.List;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ListsWindow {
    public JPanel panel1 = new JPanel(new GridLayout(0, 1));
    public JFrame frame;
    private final ListManager listManager;
    public ListContentWindow listContentWindow = null;

    public ListsWindow(ListManager listManager, JFrame frame) {
        this.listManager = listManager;
        this.frame = frame;
    }

    public void showWindow() {
        refreshList();
    }

    public void refreshList() {
        if (listContentWindow != null) {
            panel1 = listContentWindow.refreshList();
        } else {
            panel1 = new JPanel(new GridLayout(0, 1));
            for (HashMap.Entry<List, JButton> entry : listManager.listsButtons.entrySet()) {
                entry.getValue().setBackground(Color.white);
                entry.getValue().setBorder(BorderFactory.createEmptyBorder());
                entry.getValue().addActionListener(listManager);
                panel1.add(entry.getValue());
            }
        }

        frame.setContentPane(this.panel1);
        frame.setVisible(true);
    }

    public void displayListContent() {
        listContentWindow = new ListContentWindow(listManager);
        refreshList();
    }


}
