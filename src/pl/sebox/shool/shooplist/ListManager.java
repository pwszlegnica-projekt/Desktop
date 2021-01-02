package pl.sebox.shool.shooplist;

import pl.sebox.shool.shooplist.Models.List;
import pl.sebox.shool.shooplist.Models.Product;
import pl.sebox.shool.shooplist.Window.ListsWindow;
import pl.sebox.shool.shooplist.Window.WindowMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ListManager implements ActionListener {
    public ArrayList<List> lists;
    public HashMap<List, JButton> listsButtons = new HashMap<>();
    public HashMap<Product, JButton> productButtons = new HashMap<>();
    private final DatabaseManager databaseManager;
    private int currentList = 0;
    private ListsWindow listsWindow;
    public WindowMode windowMode = WindowMode.lists;

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
        this.productButtons.clear();
        for (Product entry : getCurrentList().products) {
            this.productButtons.put(entry, new JButton(entry.name));
        }
        listsWindow.refreshList();
        Log.d(lists.toString());
    }

    public void selectList(List list) {
        selectList(lists.indexOf(list));
    }

    public void refreshList() {
        lists.set(currentList,
                databaseManager.loadDetails(
                        lists.get(
                                currentList
                        )));
        setLists(lists);
    }

    public void backToLists() {
        listsWindow.listContentWindow = null;
        refreshList();
    }

    public void selectList(int index) {
        currentList = index;
        refreshList();
        listsWindow.displayListContent();
    }

    public List getCurrentList() {
        return lists.get(currentList);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (HashMap.Entry<List, JButton> entry : listsButtons.entrySet()) {
            if (actionEvent.getSource() == entry.getValue()) {
                Log.d("Select list: " + entry.getKey().listId);
                selectList(entry.getKey());
            }
        }
        boolean doRefresh = false;
        for (HashMap.Entry<Product, JButton> entry : productButtons.entrySet()) {
            if (actionEvent.getSource() == entry.getValue()) {
                Log.d("Select product: " + entry.getKey().productId);
                databaseManager.markProductAsDone(entry.getKey());
                doRefresh = true;
            }
        }
        if (doRefresh) {
            refreshList();
        }
    }
}
