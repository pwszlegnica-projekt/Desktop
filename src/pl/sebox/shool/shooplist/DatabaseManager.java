package pl.sebox.shool.shooplist;

import pl.sebox.shool.shooplist.Exception.NotLoginException;
import pl.sebox.shool.shooplist.Models.List;
import pl.sebox.shool.shooplist.Models.Product;

import java.util.ArrayList;

public class DatabaseManager {
    private String sessionKey = "";
    private boolean isLogged = false;
    private long expirationDate = 0;
    private String host;
    private String login;
    private String password;

    public void setHost(String host) {
        this.host = host;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
        sessionKey = "logged";
        isLogged = true;
        expirationDate = System.currentTimeMillis() + 10000;
        return true;
    }

    public void checkLogin() throws NotLoginException {
        if (isLogged && System.currentTimeMillis() > expirationDate) {
            if (!login()) {
                throw new NotLoginException();
            }
        }
    }

    public Product getProduct(int id) {
        Product outputProduct = new Product();
        outputProduct.productId = id;
        return outputProduct;
    }

    public List getList(int id) {
        List outputList = new List();
        outputList.listId = id;
        return outputList;
    }

    public ArrayList<List> getLists() {
        ArrayList<List> outputList = new ArrayList<>();

        return outputList;
    }

}
