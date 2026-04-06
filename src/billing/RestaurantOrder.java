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
        this.orderedItems = new int[50][3];
        this.itemCount = 0;
        this.finalTotal = 0;
    }
    
    public void addItemToOrder(int id , int qnt){
        for(int i = 0; i < orderedItems.length; i++){
            if(orderedItems[i][0] == id ){
                orderedItems[i][1] += qnt;
                return;
            
            }
        
        }
    
        orderedItems[itemCount][0] = id;
        orderedItems[itemCount][1] = qnt;
        itemCount++;
    }
    
    public double calculateTotal(Menu menu){
        this.finalTotal = 0;

        if(itemCount == 0) return 0.00;
        
        for(int i = 0; i < itemCount; i++){
            int itemId = orderedItems[i][0];
            int itemQnt = orderedItems[i][1];  
            for (int j = 0; j < menu.items.length; j++){
                MenuItem item = menu.items[j];
                if(item.id == itemId){
                    this.finalTotal += item.price*itemQnt;
                    break;
                }
            }
        }
    
        return finalTotal;
    }
    
    public void printReceipt(Menu menu, Customer customer){
        int menuLength = menu.itemCount;
        
        String receipt = "Bill no : " + orderId + "\n";

        receipt += "\nCustomer name: " + customer.name;
        receipt += "\nMobile number: " + customer.mobileNumber;
                
        receipt += "=======================";
        receipt += ">>   Name\tPrice    Qnt    Cost\t";

        if(itemCount == 0){
            receipt += "Nothing\tto\teat.\nNothing\tto\tdrink.\n";
            receipt += "\nWe Hope to see you spending some penny next time.";
        }else{
            for(int i = 0; i < itemCount; i++){
                int itemId = orderedItems[i][0];
                int itemQnt = orderedItems[i][1];  
                for (int j = 0; j < menuLength; j++){
                    MenuItem item = menu.items[j];
                    if(item.id == itemId){
                        receipt+= i + ". " + item.name + "\t" + item.price + "    " + itemQnt + "    " + (double)(item.price * itemQnt);
                    }
                }
            }
            receipt += "\tGrand Total:\t" + this.calculateTotal(menu);

            receipt += "";
        }
        
    }
}
