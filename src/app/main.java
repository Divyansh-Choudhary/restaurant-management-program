// main execution file for the restaurant project
package app;

import catalog.*;
import people.*;
import billing.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // setting up the main arrays
        Menu mainMenu = new Menu();
        Customer[] customers = new Customer[100];
        int cCount = 0;
        int nextOrderNo = 1; 
        
        // adding some dummy data so we dont have to type it every time we run
        mainMenu.addNewItem(new MenuItem(101, "Burger", 150.0));
        mainMenu.addNewItem(new MenuItem(102, "Pizza", 300.0));
        mainMenu.addNewItem(new MenuItem(103, "Pasta", 250.0));
        mainMenu.addNewItem(new MenuItem(104, "Coke", 50.0));

        int choice;

        do {
            System.out.println("\n--- Restaurant Software ---");
            System.out.println("1. Manage Menu");
            System.out.println("2. Customers & History");
            System.out.println("3. Take Order");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // menu sub-loop
                    int mChoice;
                    do {
                        System.out.println("\n-- Menu --");
                        System.out.println("1. View Menu");
                        System.out.println("2. Add New Item");
                        System.out.println("3. Back");
                        System.out.print("Choice: ");
                        mChoice = sc.nextInt();

                        if (mChoice == 1) {
                            mainMenu.printFullMenu();
                        } else if (mChoice == 2) {
                            System.out.print("Enter Item ID: ");
                            int id = sc.nextInt();
                            System.out.print("Enter Item Name: ");
                            String name = sc.next();
                            System.out.print("Enter Price: ");
                            double price = sc.nextDouble();
                            
                            mainMenu.addNewItem(new MenuItem(id, name, price));
                        }
                    } while (mChoice != 3);
                    break;
                    
                case 2:
                    // customer sub-loop
                    int cChoice;
                    do {
                        System.out.println("\n-- Customers --");
                        System.out.println("1. View All Customers");
                        System.out.println("2. Search Customer History by Mobile");
                        System.out.println("3. Back");
                        System.out.print("Choice: ");
                        cChoice = sc.nextInt();

                        if (cChoice == 1) {
                            if(cCount == 0) {
                                System.out.println("No customers registered yet.");
                            } else {
                                for(int i = 0; i < cCount; i++) {
                                    System.out.println();
                                    customers[i].display();
                                }
                            }
                        } else if (cChoice == 2) {
                            System.out.print("Enter mobile no: ");
                            String phone = sc.next();
                            boolean found = false;
                            
                            for(int i = 0; i < cCount; i++) {
                                if(customers[i].phoneNumber.equals(phone)) {
                                    System.out.println("\nHistory for " + customers[i].name + ":");
                                    if(customers[i].orderCount == 0) {
                                        System.out.println("No orders yet.");
                                    } else {
                                        customers[i].listOrders(); 
                                    }
                                    found = true;
                                    break;
                                }
                            }
                            if(!found) {
                                System.out.println("Customer not found!");
                            }
                        }
                    } while (cChoice != 3);
                    break;
                    
                case 3:
                    // taking a new order
                    System.out.println("\n-- New Order --");
                    System.out.print("Enter mobile no: ");
                    String phone = sc.next();
                    
                    Customer cust = null;
                    
                    // check if old customer
                    for(int i = 0; i < cCount; i++) {
                        if(customers[i].phoneNumber.equals(phone)) {
                            cust = customers[i];
                            System.out.println("Welcome back " + cust.name + "!");
                            break;
                        }
                    }
                    
                    // register if new
                    if(cust == null) {
                        System.out.print("New customer! \nPlease Enter name: ");
                        String name = sc.next();
                        cust = new Customer((cCount + 1), name, phone);
                        customers[cCount++] = cust;
                    }
                    
                    RestaurantOrder order = new RestaurantOrder(nextOrderNo++, cust.id);
                    mainMenu.printFullMenu();
                    
                    // add items loop
                    while(true) {
                        System.out.print("\nEnter item ID (or 0 to print bill): ");
                        int itemId = sc.nextInt();
                        
                        if(itemId == 0) break;
                        
                        if(mainMenu.getPriceById(itemId) == -1) {
                            System.out.println("Wrong ID, try again.");
                            continue;
                        }
                        
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        
                        order.addItemToOrder(itemId, qty);
                        System.out.println("Added!");
                    }
                    
                    // generate bill if they actually bought something
                    if(order.itemCount > 0) {
                        order.calculateTotal(mainMenu);
                        order.printReceipt(mainMenu, cust);
                        cust.addOrderToHistory(order);
                        System.out.println("Order saved.");
                    } else {
                        System.out.println("Order cancelled.");
                        nextOrderNo--; // reduce order no if cancelling order
                    }
                    break;
                    
                case 4:
                    System.out.println("Exiting...");
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
        
        sc.close();
    }
}
