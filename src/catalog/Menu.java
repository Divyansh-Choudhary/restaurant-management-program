class Menu extends MenuItem {
    public MenuItem[] items;
    public int count;

    public Menu(int id, String name, double price) {
        super(id, name, price);
        items = new MenuItem[100];
        count = 0;
    }

    public void addNewItem(MenuItem item) {
        if (count < items.length) {
            items[count] = item;
            count++;
            System.out.println("Item added successfully.");
        } else {
            System.out.println("Menu is full.");
        }
    }

    public void printFullMenu() {
        if (count == 0) {
            System.out.println("Menu is empty.");
            return;
        }
        System.out.println("\n----- Full Menu -----");
        for (int i = 0; i < count; i++) {
            items[i].displayItem();
        }
    }

    public double getPriceById(int searchId) {
        for (int i = 0; i < count; i++) {
            if (items[i].id == searchId) {
                return items[i].price;
            }
        }
        return -1;
    }
}


               
