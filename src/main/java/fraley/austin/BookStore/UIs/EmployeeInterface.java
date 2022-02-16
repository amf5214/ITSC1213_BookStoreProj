package fraley.austin.BookStore.UIs;

import fraley.austin.BookStore.Enums.ProductType;
import fraley.austin.BookStore.Models.BookStore;
import fraley.austin.BookStore.Models.Product;
import fraley.austin.BookStore.Models.Sale;

import java.util.Hashtable;
import java.util.Scanner;

public class EmployeeInterface {

    private Scanner sc;
    private BookStore store;
    private boolean continueRunning;

    public EmployeeInterface(Scanner sc, BookStore store) {
        this.sc = sc;
        this.store = store;
        this.continueRunning = true;
    }

    public void employeeInterface() {
        while(continueRunning) {
            System.out.println("Welcome! Available options:");
            System.out.println("\t 1. Add Inventory Item");
            System.out.println("\t 2. Remove Inventory Item");
            System.out.println("\t 3. Review Current Inventory");
            System.out.println("\t 4. Review Current Orders");
            System.out.println("\t 5. Access Order Details");
            System.out.println("\t 6. Back");

            int userChoice = sc.nextInt();
            switch (userChoice) {
                case 1:
                    addInventoryItem();
                    break;
                case 2:
                    removeInventoryItem();
                    break;
                case 3:
                    reviewCurrentInventory();
                    break;
                case 4:
                    reviewCurrentOrders();
                    break;
                case 5:
                    System.out.println("What order would you like to access?");
                    long orderId = sc.nextLong();
                    store.printSale(orderId);
                    break;
                case 6:
                    this.continueRunning = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void reviewCurrentInventory() {
        System.out.println("----------------------------------");
        System.out.println("Current Items in inventory:");
        Hashtable<Long, Product> products = store.getProducts();
        for(Long productId : products.keySet()) {
            store.printItem(productId);
        }
        System.out.println("----------------------------------");

    }

    private void reviewCurrentOrders() {
        System.out.println("----------------------------------");
        System.out.println("Current Orders");
        Hashtable<Long, Sale> sales = store.getSales();
        for(Long saleId : sales.keySet()) {
            store.printSale(saleId);
        }
        System.out.println("----------------------------------");

    }

    private void removeInventoryItem() {
        System.out.println("----------------------------------");
        System.out.print("Please enter the item number of the items that you would like to remove: ");
        System.out.println();
        long productId = sc.nextLong();
        store.getProducts().remove(productId);
        System.out.println("Item " + productId + " has been removed from inventory.");
        System.out.println("----------------------------------");

    }

    private void addInventoryItem() {
        ProductType pType;

        System.out.println("What type of item would you like to add?");
        System.out.println("\t 1. CD");
        System.out.println("\t 2. DVD");
        System.out.println("\t 3. BOOK");
        int itemType = sc.nextInt();
        sc.nextLine();
        pType = itemType == 1 ? ProductType.CD : itemType == 2 ? ProductType.DVD : ProductType.BOOK;

        System.out.println("What is the name of the item to add?");
        String name = sc.nextLine();

        System.out.println("How much does the item cost exactly?");
        double cost = sc.nextDouble();

        System.out.println("How many of this item are in the store?");
        int quantity = sc.nextInt();

        long productId = store.addProduct(name, quantity, pType, cost);
        System.out.println("Product ID of " + name + " is: " + productId);
    }

}
