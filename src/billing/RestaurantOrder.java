package billing;

import catalog.*;
import people.*;

public class RestaurantOrder implements Billable{

    public int orderId;
    public int customerId;
    public int[][] orderedItems;
    public int itemCount;
    public double finalTotal;
    
    public RestaurantOrder(int orderId , int customerId ){
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedItems = new int[50][2];
        this.itemCount = 0;
        this.finalTotal = 0;
    }
    
    public void addItemToOrder(int id , int qnt){
        for(int i = 0; i < itemCount; i++){
            if(orderedItems[i][0] == id ){
                orderedItems[i][1] += qnt;
                return;
            
            }
        
        }
    
        orderedItems[itemCount][0] = id;
        orderedItems[itemCount][1] = qnt;
        itemCount++;
    }

    @Override
    public double calculateTotal(Menu menu){
        this.finalTotal = 0;

        if(itemCount == 0) return 0.00;
        
        for(int i = 0; i < itemCount; i++){
            int itemId = orderedItems[i][0];
            int itemQnt = orderedItems[i][1];
            double price = menu.getPriceById(itemId);
            if(price != -1)
                this.finalTotal += price * itemQnt;        
        }
    
        return finalTotal;
    }
    
    @Override
    public void printReceipt(Menu menu, Customer customer){
        String receipt = "\n=== Bill no: " + orderId + " ===\n";
        receipt += "Customer name: " + customer.name;
        receipt += "\nMobile number: " + customer.phoneNumber;
        receipt += "\n=======================\n";
        receipt += "Name\tPrice\tQty\tCost\n";

        if(itemCount == 0){
            receipt += "No items ordered.\n";
        } else {
            for(int i = 0; i < itemCount; i++){
                int itemId = orderedItems[i][0];
                int itemQnt = orderedItems[i][1];  
                
                for (int j = 0; j < menu.count; j++){
                    MenuItem item = menu.items[j];
                    if(item.id == itemId){
                        receipt += item.name + "\t" + item.price + "\t" +
                                   itemQnt + "\t" + (item.price * itemQnt) + "\n";
                    }
                }
            }
            receipt += "\tGrand Total:\t" + this.calculateTotal(menu) + "\n";
        }
        System.out.println(receipt);
    }
}
