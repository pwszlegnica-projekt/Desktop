package pl.sebox.shool.shooplist.Models;

import java.util.ArrayList;

public class List {
    public ArrayList<Product> products = new ArrayList<>();
    public int listId;
    public String name;

    public List() {
    }

    public List(int listId, String name, ArrayList<Product> products) {
        this.products = products;
        this.listId = listId;
        this.name = name;
    }

    public List(int listId, String name) {
        this.listId = listId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "List{" +
                "listId=" + listId +
                ", name='" + name + "\n" +
                ", products=" + products + "\n" + '\'' +
                '}';
    }
}
