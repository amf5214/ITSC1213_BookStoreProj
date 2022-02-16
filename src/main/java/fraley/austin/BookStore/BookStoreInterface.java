package fraley.austin.BookStore;

import fraley.austin.BookStore.UIs.CustomerHomeInterface;
import fraley.austin.BookStore.UIs.EmployeeInterface;
import fraley.austin.BookStore.Models.BookStore;

import java.util.Scanner;

public class BookStoreInterface {

    private Scanner sc;
    private BookStore store;
    private EmployeeInterface employeeInterface;
    private CustomerHomeInterface customerHomeInterface;
    private boolean continueRunning = true;

    BookStoreInterface() {
        this.sc = new Scanner(System.in);
        this.store = new BookStore();
        this.employeeInterface = new EmployeeInterface(sc, store);
        this.customerHomeInterface = new CustomerHomeInterface(sc, store);
    }

    public void run() {
        while(continueRunning) {
            System.out.println("How will you be logging in?");
            System.out.println("\t 1. Customer");
            System.out.println("\t 2. Employee");
            System.out.println("\t 3. Exit");
            int userChoice = sc.nextInt();

            switch(userChoice) {
                case 1:
                    customerHomeInterface.accntManagementInterface();
                    break;
                case 2:
                    employeeInterface.employeeInterface();
                    break;
                case 3:
                    continueRunning = false;
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }

}
