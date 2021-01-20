package pl.sebox.shool.shooplist;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.sebox.decathlon.tools.Network;
import pl.sebox.decathlon.tools.network.HttpResponse;
import pl.sebox.shool.shooplist.Exception.NotLoginException;
import pl.sebox.shool.shooplist.LoginWindow.Credentials;
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
    private Credentials credentials;

    public DatabaseManager(String host, Credentials credentials) {
        this.host = host;
        this.credentials = credentials;
    }

    public void setHost(String host) {
        this.host = host;
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

    public boolean markProductAsDone(Product product) {
        sentRequest("product/" + product.productId + "/markAsDone");
        return true;
    }

    private JSONObject sentRequest(String request) {
        return sentRequest(request, new HashMap<>());
    }

    private void authorize(){
        HashMap<String, String> pahrms = new HashMap<>();
        pahrms.put("email", credentials.login);
        pahrms.put("password", credentials.password);
        HttpResponse response = Network.post(this.host + "/login", pahrms);
        JSONObject jo = new JSONObject(response.body);
        Log.add(jo.toString());
    }

    private JSONObject sentRequest(String request, HashMap<String, String> values) {
        if(this.credentials.token == null){
            authorize();
        }
        HttpResponse response = Network.post(this.host + "/" + request, values);
        Log.d("Network request: " + request);
        Log.d(String.valueOf(response.code));
        Log.d(response.body);
        if (response.code == 200) {
            return new JSONObject(response.body);
        } else {
            return new JSONObject("{\"error\": true}");
        }
    }

}
