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
        String receipt = "\n===================================\n";
        receipt += "          RESTAURANT BILL          \n";
        receipt += "===================================\n";
        receipt += "Order No : " + orderId + "\n";
        receipt += "Customer : " + customer.name + "\n";
        receipt += "Mobile   : " + customer.phoneNumber + "\n";
        receipt += "-----------------------------------\n";
        
        // We put two tabs after 'Item' to give the names enough room
        receipt += "Item\t\tQty\tPrice\tTotal\n"; 
        receipt += "-----------------------------------\n";
    
        if(itemCount == 0){
            receipt += "No items ordered.\n";
        } else {
            for(int i = 0; i < itemCount; i++){
                int itemId = orderedItems[i][0];
                int itemQnt = orderedItems[i][1];  
                
                for (int j = 0; j < menu.count; j++){
                    MenuItem item = menu.items[j];
                    if(item.id == itemId){
                        
                        String tabFix = "\t";
                        if(item.name.length() < 8) {
                            tabFix = "\t\t";
                        }
                        
                        receipt += item.name + tabFix + itemQnt + "\t" + item.price + "\t" + (item.price * itemQnt) + "\n";
                    }
                }
            }
            receipt += "-----------------------------------\n";
            receipt += "\t\tGRAND TOTAL: Rs " + this.calculateTotal(menu) + "\n";
            receipt += "===================================\n";
        }
        System.out.println(receipt);
    }
}
