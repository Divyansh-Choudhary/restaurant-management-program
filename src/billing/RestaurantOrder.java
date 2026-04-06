package billing;

import catalog.*;
import people.*;

public class RestaurantOrder implements Billable{

    int orderId;
    int customerId;
    int[][] orderedItems;
    int itemCount;
    double finalTotal;
    
      RestaurantOrder(int orderId , int customerId ){
          this.orderId = orderId;
          this.customerId = customerId;
          this.orderedItems = new int[50][3];
          this.itemCount = 0;
          this.finalTotal = 0;
      }
      void addItemToOrder(int id , int qnt){
          for(int i=0; i<orderedItems.length; i++){
              if(orderedItems[i][0] == id ){
                  orderedItems[i][1] += qnt;
                  return;

              }

          }

          ordredItems[itemCount++] = {id,qnt};
      }

      void calculateTotal(Menu menu){
          int menuLength = menu.itemCount;

          for(int i=0; i<itemCount; i++){
              int itemId = orderedItems[i][0];
              int itemQnt = orderedItems[i][1];  
              for (int j=0; j<menuLength; j++){
                  MenuItem item = menu.items[j];
                  if(item.id == itemId){
                      finalTotal += item.price*itemQnt;
                      break;
                  }
              }
          }


      }
      
      void printReceipt(Menu menu, Customer cust){
          int menuLength = menu.itemCount;

          String receipt = "Bill no : " + orderId + "\n";

          for(int i = 0; i < cust.length; i++){
              if(cust.id == customerId){
                  receipt += "\nCustomer name: " + cust.name;
                  receipt += "\nMobile number: " + cust.mobileNumber;
              }
          }

          receipt += "=======================";
          receipt += ">>   Name\tPrice    Qnt    Cost";
          for(int i=0; i<itemCount; i++){
              int itemId = orderedItems[i][0];
              int itemQnt = orderedItems[i][1];  
              for (int j=0; j<menuLength; j++){
                  MenuItem item = menu.items[j];
                  if(item.id == itemId){
                      receipt+= i + ". " + item.name + "\t" + item.price "    " + itemQnt + "    " + (double)(item.price * itemQnt);
                  }
              }
          }
          receipt += "\tGrand Total:\t" + finalTotal;

      }
}
