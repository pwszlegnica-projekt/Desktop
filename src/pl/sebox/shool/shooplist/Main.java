package pl.sebox.shool.shooplist;

import pl.sebox.shool.shooplist.Models.List;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager("http://127.0.0.1", "", "");
        ArrayList<List> lists = databaseManager.getLists();
        Log.d(String.valueOf(lists));
        List chosedList = lists.get(0);
        Log.d("Chosed list: " + chosedList.toString());
        chosedList = databaseManager.loadDetails(chosedList);
        lists.set(0, chosedList);
        Log.d("Detailed list: " + chosedList.toString());
    }
}
