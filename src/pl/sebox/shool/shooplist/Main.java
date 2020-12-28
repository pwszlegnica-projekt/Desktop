package pl.sebox.shool.shooplist;

import pl.sebox.shool.shooplist.Models.List;
import pl.sebox.shool.shooplist.Window.ListsWindow;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ListManager listManager = new ListManager(new DatabaseManager("http://127.0.0.1", "", ""));
//        List chosedList = lists.get(0);
//        Log.d("Chosed list: " + chosedList.toString());
//        chosedList = databaseManager.loadDetails(chosedList);
//        lists.set(0, chosedList);
//        Log.d("Detailed list: " + chosedList.toString());
        ListsWindow listsWindow = new ListsWindow(listManager);
        listManager.setListsWindow(listsWindow);
        listManager.init();
        listsWindow.showWindow();
    }
}
