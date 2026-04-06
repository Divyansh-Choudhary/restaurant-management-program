package people;
import billing.*;

public class Customer extends Person
{
    public String phoneNumber;
    public int orderCount;
    public RestaurantOrder[] orderHistory;
    
    public Customer(int id, String name, String phoneNumber)
    {
        super(id,name);
        this.phoneNumber = phoneNumber;
        this.orderCount = 0;
        this.orderHistory = new RestaurantOrder[50];
    }

    @Override
    public void display()
    {
        System.out.println("Name:\t"+name);
        System.out.println("ID:\t"+id);
        System.out.println("Phone number:\t"+phoneNumber);
        System.out.println("Order number:\t"+orderCount);
    }
    
    public void addOrderToHistory(RestaurantOrder order)
    {
        orderHistory[orderCount++] = order;
    }
    
    public void listOrders() {
        for(int i = 0; i < orderCount; i++) {
            System.out.println("[ Order #" + orderHistory[i].orderId + " ]");
            System.out.println("Total Items: " + orderHistory[i].itemCount);
            System.out.println("Amount Paid: Rs " + orderHistory[i].finalTotal);
            System.out.println();
        }
    }
}
