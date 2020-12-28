package pl.sebox.shool.shooplist;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.sebox.decathlon.tools.Network;
import pl.sebox.decathlon.tools.network.HttpResponse;
import pl.sebox.shool.shooplist.Exception.NotLoginException;
import pl.sebox.shool.shooplist.Models.List;
import pl.sebox.shool.shooplist.Models.Price;
import pl.sebox.shool.shooplist.Models.Product;
import pl.sebox.shool.shooplist.Models.ProductUnits;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {
    private String sessionKey = "";
    private boolean isLogged = false;
    private long expirationDate = 0;
    private String host;
    private String login;
    private String password;

    public DatabaseManager(String host, String login, String password) {
        this.host = host;
        this.login = login;
        this.password = password;
    }

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

//    public Product getProduct(int id) {
//        Product outputProduct = new Product();
//        outputProduct.productId = id;
//        return outputProduct;
//    }

    public List loadDetails(List list) {
        JSONObject jo = sentRequest("list/" + list.listId);
        JSONArray jsonArray = jo.getJSONArray("products");
        list.products.clear();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.products.add(new Product(
                    jsonArray.getJSONObject(i).getInt("id"),
                    jsonArray.getJSONObject(i).getString("name")
            ));
        }
        return list;
    }

    public ArrayList<List> getLists() {
        ArrayList<List> outputList = new ArrayList<>();
        JSONObject jo = sentRequest("lists");
        JSONArray jsonArray = jo.getJSONArray("lists");
        for (int i = 0; i < jsonArray.length(); i++) {
            outputList.add(new List(
                    jsonArray.getJSONObject(i).getInt("id"),
                    jsonArray.getJSONObject(i).getString("name")
            ));
        }
        return outputList;
    }

    private JSONObject sentRequest(String request) {
        return sentRequest(request, new HashMap<>());
    }

    private JSONObject sentRequest(String request, HashMap<String, String> values) {
        HttpResponse response = Network.post(this.host + "/" + request, values);
        Log.d("Network request");
        Log.d(String.valueOf(response.code));
        Log.d(response.body);
        if (response.code == 200) {
            return new JSONObject(response.body);
        } else {
            return new JSONObject("{\"error\": true}");
        }
    }

}
