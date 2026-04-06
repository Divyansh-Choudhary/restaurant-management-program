package catalog;

public class MenuItem {
    public int id;
    public String name;
    public double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void displayItem() {
        System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);
    }
}
