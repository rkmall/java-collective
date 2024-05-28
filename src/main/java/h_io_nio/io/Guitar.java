package h_io_nio.io;

import java.io.Serializable;

public class Guitar implements Serializable { // must implement Serializable interface
    static final long serialVersionUID = 1889934567L;

    private final String name;
    private final String brand;
    private String style;
    private double price;
    private Company company;

    public Guitar(String name, String brand, Company company) {
        this.name = name;
        this.brand = brand;
        this.company = company;
    }

    public Guitar(String name, String brand, String style, double price, Company company) {
        this.name = name;
        this.brand = brand;
        this.style = style;
        this.price = price;
        this.company = company;
    }

    public String getName() { return name; }

    public String getBrand() { return brand; }

    public String getStyle() { return style; }

    public double getPrice() { return price; }

    public Company getCompany() { return company; }

    @Override
    public String toString() {
        return "Guitar: name = " + name +
                ", brand = " + brand +
                ", style = " + style +
                ", price = " + price +
                ", " + company;
    }
}
