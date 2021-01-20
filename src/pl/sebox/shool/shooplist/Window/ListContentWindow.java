package pl.sebox.shool.shooplist.Window;

import pl.sebox.shool.shooplist.ListManager;
import pl.sebox.shool.shooplist.Models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ListContentWindow {
    private final ListManager listManager;

    public ListContentWindow(ListManager listManager) {
        this.listManager = listManager;
    }

    public JPanel refreshList(){
        JPanel panel1 = new JPanel(new GridLayout(0, 1));

        for (HashMap.Entry<Product, JButton> entry : listManager.productButtons.entrySet()) {
            entry.getValue().setBackground(Color.white);
            entry.getValue().setBorder(BorderFactory.createEmptyBorder());
            entry.getValue().addActionListener(listManager);
            panel1.add(entry.getValue());
        }
        JTextField name = new JTextField();
        panel1.add(name);
        JButton add = new JButton("dodaj");
        add.setBackground(Color.green);
        add.setBorder(BorderFactory.createEmptyBorder());

        panel1.add(add);
        return panel1;
    }
}
