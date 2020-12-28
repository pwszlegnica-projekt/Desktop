package pl.sebox.shool.shooplist;

import pl.sebox.shool.shooplist.Models.List;
import pl.sebox.shool.shooplist.Window.ListsWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ListManager implements ActionListener {
    public ArrayList<List> lists;
    public HashMap<List, JButton> listsButtons = new HashMap<>();
    private DatabaseManager databaseManager;
    private int currentList = 0;
    private ListsWindow listsWindow;

    public ListManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void setListsWindow(ListsWindow listsWindow) {
        this.listsWindow = listsWindow;
    }

    public void init() {
        setLists(databaseManager.getLists());
    }

    public void setLists(ArrayList<List> lists) {
        this.lists = lists;
        for (List entry : lists) {
            this.listsButtons.put(entry, new JButton(entry.name));
        }
        listsWindow.refreshList();
        Log.d(lists.toString());
    }

    public void selectList(List list) {
        selectList(lists.indexOf(list));
    }

    public void selectList(int index) {
        currentList = index;
        lists.set(index,
                databaseManager.loadDetails(
                        lists.get(
                                currentList
                        )));
        setLists(lists);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Log.d("-------------------------");
        for (HashMap.Entry<List, JButton> entry : listsButtons.entrySet()) {
            if (actionEvent.getSource() == entry.getValue()) {
                Log.d("Select list: " + entry.getKey().listId);
                selectList(entry.getKey());
            }
        }
    }
}
