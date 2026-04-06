package people;
import billing.*;

class Customer extends person
{
    String phoneNumber;
    int orderCount;
    RestaurantOrder[] orderHistory;
    Customer(int id, String name, String phoneNumber)
    {
        super(id,name);
        this.phoneNumber=phoneNumber;
        this.orderCount=0;
        this.orderHistory = new RestaurantOrder[50];
    }
    void display()
    {
        System.out.println("Name:\t"+name);
        System.out.println("ID:\t"+id);
        System.out.println("Phone number:\t"+phoneNumber);
        System.out.println("Order number:\t"+orderCount);
    }
    void addOrderToHistory(RestaurantOrder x)
    {
        orderHistory[orderCount++]=x;
    }
    void listorder()
    {
        for(int i=1; i<=orderCount; i++)
        {
            System.out.println("Bill number "+i+":\t"+orderHistory[i].orderID+"\nAnd the number of items were:\t"+orderHistory[i].itemCount+"\nThe total amount for this order is:\t"+orderHistory[i].finalTotal);
        }
    }
}
