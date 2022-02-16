package fraley.austin.BookStore.UIs;

import fraley.austin.BookStore.Enums.MembershipType;
import fraley.austin.BookStore.Formats.DateFormats;
import fraley.austin.BookStore.Models.BookStore;
import fraley.austin.BookStore.Models.Member;

import java.time.LocalDate;
import java.util.Scanner;

public class CustomerHomeInterface {

    private Scanner sc;
    private BookStore store;
    private CustomerInterface customerInterface;
    private boolean continueRunning = true;

    public CustomerHomeInterface(Scanner sc, BookStore store) {
        this.sc = sc;
        this.store = store;
        this.customerInterface = new CustomerInterface();
    }

    public void accntManagementInterface() {
        while(continueRunning) {
            System.out.println("Welcome! Are you currently a member?");
            System.out.println("\t 1. Yes, let's login");
            System.out.println("\t 2. No, sign up");
            sc.nextLine();
            int userResponse = sc.nextInt();
            sc.nextLine();

            switch(userResponse) {
                case 1:
                    userSignIn();
                    break;
                case 2:
                    userSignUp();
                    break;
                default:
                    break;
            }
        }
    }

    private void userSignIn() {
        System.out.println("Please enter your username: ");
        String userName = sc.nextLine();
        try {
            Member currentMember = store.getMember(userName);
            System.out.println(currentMember.getMemberId());
        } catch(NullPointerException e){
            System.out.println("Error: User not found. Please try again.");
        }
    }

    private void userSignUp() {
        System.out.println("Enter username: ");
        String user = sc.nextLine();
        store.signUp(user, MembershipType.BASIC);

        System.out.println("Please enter your first name: ");
        String fName = sc.nextLine();

        System.out.println("Please enter your last name: ");
        String lName = sc.nextLine();

        System.out.println("Please enter your birth date (format: MM/DD/YYYY): ");
        String bDateStr = sc.nextLine();
        LocalDate bDate = LocalDate.parse(bDateStr, DateFormats.BIRTHDATEFORMAT);

        store.setUserDetails(user, fName, lName, bDate);


    }

}
