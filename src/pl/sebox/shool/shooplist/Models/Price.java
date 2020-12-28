package pl.sebox.shool.shooplist.Models;


public class Price {
    private long price;
    private VatRate vat;

    public double getPrice() {
        try {
            return ((double) price) / 10000;
        } catch (Exception ignore) {
            return 0;
        }
    }

    public void setPrice(int price) {
        this.price = price * 10000;
    }

    public void setPrice(long price) {
        this.price = price * 10000;
    }

    public void setPrice(double price) {
        this.price = (long) Math.floor(price * 10000);
    }

    public VatRate getVat() {
        return vat;
    }

    public void setVat(VatRate vat) {
        this.vat = vat;
    }

    Price() {
        setPrice(0);
        setVat(VatRate.vat23);
    }

    Price(double price) {
        setPrice(price);
        setVat(VatRate.vat23);
    }

    Price(double price, VatRate vatRate) {
        setPrice(price);
        setVat(vatRate);
    }

    public Price(int price) {
        setPrice(price);
        setVat(VatRate.vat23);
    }

    Price(int price, VatRate vatRate) {
        setPrice(price);
        setVat(vatRate);
    }

    Price(long price) {
        setPrice(price);
        setVat(VatRate.vat23);
    }

    Price(long price, VatRate vatRate) {
        setPrice(price);
        setVat(vatRate);
    }
}
