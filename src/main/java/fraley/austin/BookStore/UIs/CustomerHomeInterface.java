package fraley.austin.BookStore.UIs;

import fraley.austin.BookStore.Enums.MembershipType;
import fraley.austin.BookStore.Enums.SignInMessages;
import fraley.austin.BookStore.Models.BookStore;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CustomerHomeInterface {

    private Scanner sc;
    private BookStore store;
    private CustomerInterface customerInterface;
    private boolean continueRunning;

    public CustomerHomeInterface(Scanner sc, BookStore store) {
        this.sc = sc;
        this.store = store;
        this.customerInterface = new CustomerInterface(sc, store);
        this.continueRunning = true;
    }

    public void customerHomeInterface() {
        while(continueRunning) {
            System.out.println("Welcome! Are you currently a member?");
            System.out.println("\t 1. Yes, let's login");
            System.out.println("\t 2. No, sign up");
            System.out.println("\t 3. Back");
            sc.nextLine();
            int userResponse = sc.nextInt();
            sc.nextLine();

            switch(userResponse) {
                case 1:
                    SignInInterface signIn = new SignInInterface(store, sc);
                    String signInUserName = signIn.customerSignIn();
                    if (!signInUserName.equals(SignInMessages.SIGN_IN_INVALID.toString()) && !signInUserName.equals(SignInMessages.EMPTY.toString())) {
                        customerInterface.customerInterface(signInUserName);
                    }
                    break;
                case 2:
                    userSignUp();
                    break;
                case 3:
                    this.continueRunning = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void userSignIn() {
        System.out.println("Please enter your username: ");
        String userName = sc.nextLine();
        if(store.getMembers().containsKey(userName)) {

        } else {
            System.out.println("Error: The username entered is invalid");
        }
    }

    private void userSignUp() {
        System.out.println("Enter username: ");
        String user = sc.nextLine();
        store.signUp(user, MembershipType.BASIC);

        System.out.println("Please enter password: ");
        String password = sc.nextLine();

        store.getPasswordEncryptor().createPassword(password, store.getMember(user));

        System.out.println("Please enter your first name: ");
        String fName = sc.nextLine();

        System.out.println("Please enter your last name: ");
        String lName = sc.nextLine();

        System.out.println("Please enter your birth date, format: YYYY-MM-DD: ");
        String bDateStr = sc.nextLine();
        try {
            LocalDate bDate = LocalDate.parse(bDateStr);
            store.setUserDetails(user, fName, lName, bDate);
        } catch(DateTimeParseException e) {
            System.out.println("Error the date that was entered is not valid");
        }

    }

}
