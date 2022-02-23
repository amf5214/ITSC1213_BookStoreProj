package fraley.austin.BookStore.UIs;

import fraley.austin.BookStore.Models.BookStore;
import fraley.austin.BookStore.Models.Sale;

import java.util.Scanner;

public class CustomerInterface {

    private BookStore store;
    private Scanner sc;
    private boolean continueRunning;
    private AccntManagementInterface accntManagementInterface;

    public CustomerInterface(Scanner sc, BookStore store) {
        this.store = store;
        this.sc = sc;
        this.continueRunning = true;
        this.accntManagementInterface = new AccntManagementInterface(sc, store);
    }

    public void customerInterface(String userName) {
        while(continueRunning) {
            System.out.println("Chose a menu item: ");
            System.out.println("\t 1. Create Order");
            System.out.println("\t 2. Cancel Order");
            System.out.println("\t 3. Manage Order");
            System.out.println("\t 4. Manage Account " + userName);
            System.out.println("\t 5. Back");
            
            int userResponse = sc.nextInt();
            sc.nextLine();
            
            switch(userResponse) {
                case 1:
                    createOrder(userName);
                    break;
                case 2:
                    cancelOrder();
                    break;
                case 3:
                    manageOrder();
                    break;
                case 4:
                    accntManagementInterface.accntManagementInterface(userName);
                    break;
                case 5:
                    this.continueRunning = false;
                    break;
                default:
                    break;
            }
            
        }
    }

    private void manageOrderInterface(long currentSaleId) {
        Sale currentSale = store.getSale(currentSaleId);
        boolean continueCreating = true;
        while(continueCreating) {
            System.out.println("What would you like to do?");
            System.out.println("\t 1. Add more items");
            System.out.println("\t 2. Remove items");
            System.out.println("\t 3. Review order");
            System.out.println("\t 4. Complete order");
            System.out.println("\t 5. Cancel Order");
            int userChoice = sc.nextInt();
            sc.nextLine();
            switch (userChoice) {
                case 1:
                    System.out.println("What items would you like to add?");
                    long itemNum = sc.nextLong();
                    System.out.println("How many would you like to add?");
                    int itemQuantity = sc.nextInt();
                    currentSale.addProduct(itemNum, itemQuantity);
                    break;
                case 2:
                    System.out.println("What item would you like to remove?");
                    long itemToRemove = sc.nextLong();
                    currentSale.removeProduct(itemToRemove);
                    break;
                case 3:
                    store.printSale(currentSaleId);
                    break;
                case 4:
                    continueCreating = false;
                    break;
                case 5:
                    store.getSales().remove(currentSale);
                    continueCreating = false;
                    break;
            }
        }
    }

    private void manageOrder() {
        System.out.println("What order would you like to manage?");
        long currentSaleId = sc.nextLong();
        sc.nextLine();
        manageOrderInterface(currentSaleId);
    }

    private void cancelOrder() {
        System.out.println("What order would you like to cancel?");
        long orderNum = sc.nextLong();
        sc.nextLine();
        store.getSales().remove(orderNum);
    }

    private void createOrder(String userName) {
        long currentSaleId = store.createSale(store.getMember(userName));
        System.out.println("Your order number is " + currentSaleId);
        manageOrderInterface(currentSaleId);
    }


}
