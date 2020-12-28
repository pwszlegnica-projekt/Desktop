package pl.sebox.shool.shooplist;

import pl.sebox.shool.shooplist.Window.ListsWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ListManager listManager = new ListManager(new DatabaseManager("http://127.0.0.1", "", ""));
        ListsWindow listsWindow = new ListsWindow(listManager, frame);
        listManager.setListsWindow(listsWindow);
        listManager.init();
        listsWindow.showWindow();
    }
}
