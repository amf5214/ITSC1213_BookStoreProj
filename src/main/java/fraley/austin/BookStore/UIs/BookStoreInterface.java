package fraley.austin.BookStore.UIs;

import fraley.austin.BookStore.Security.PasswordEncryptor;
import fraley.austin.BookStore.UIs.CustomerHomeInterface;
import fraley.austin.BookStore.UIs.EmployeeInterface;
import fraley.austin.BookStore.Models.BookStore;

import java.util.Scanner;

public class BookStoreInterface {

    private final Scanner sc;
    private final EmployeeInterface employeeInterface;
    private final CustomerHomeInterface customerHomeInterface;
    private boolean continueRunning;

    public BookStoreInterface() {
        this.sc = new Scanner(System.in);
        BookStore store = new BookStore();
        this.employeeInterface = new EmployeeInterface(sc, store);
        this.customerHomeInterface = new CustomerHomeInterface(sc, store);
        continueRunning = true;
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
                    customerHomeInterface.customerHomeInterface();
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
    }

}
