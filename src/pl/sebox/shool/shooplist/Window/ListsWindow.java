package pl.sebox.shool.shooplist.Window;

import pl.sebox.shool.shooplist.ListManager;
import pl.sebox.shool.shooplist.Models.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        //JPanel panelMenu = new JPanel(new GridLayout(0, 1));
        JPanel panel0 = new JPanel(null);
        MenuPanel menuPanel = new MenuPanel();
        panel0.add(menuPanel);
        menuPanel.setLayout(null);
        menuPanel.setSize(100, 50);
        panel0.add(panel1);
        panel1.setLocation(0, 50);
        panel1.setSize(frame.getContentPane().getSize().width + 10, frame.getContentPane().getSize().height - 50);
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                listManager.backToLists();
            }
        });
        frame.setContentPane(panel0);
        frame.setVisible(true);
    }

    public void displayListContent() {
        listContentWindow = new ListContentWindow(listManager);
        refreshList();
    }


}
