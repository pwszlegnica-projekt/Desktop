package pl.sebox.shool.shooplist.Models;

public class Product {
    public int productId;
    public String name;
    public String description;
    public double amount;
    public ProductUnits productUnit;
    public String img;
    public Price price;

    @Override
    public String toString() {
        return "\n\t\t(" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ')';
    }

    public Product(int productId, String name) {
        this.productId = productId;
        this.name = name;
        this.description = "";
        this.amount = 1;
        this.productUnit = ProductUnits.szt;
        this.img = "";
        this.price = new Price(0);
    }
    public Product(int productId, String name, String description, double amount, ProductUnits productUnit, String img, Price price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.productUnit = productUnit;
        this.img = img;
        this.price = price;
    }
}
