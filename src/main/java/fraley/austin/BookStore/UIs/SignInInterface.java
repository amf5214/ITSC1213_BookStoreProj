package fraley.austin.BookStore.UIs;

import fraley.austin.BookStore.Enums.SignInMessages;
import fraley.austin.BookStore.Models.BookStore;
import fraley.austin.BookStore.Models.Member;
import fraley.austin.BookStore.Security.PasswordEncryptor;

import java.util.Scanner;

public class SignInInterface {

    private boolean continueRunning;
    private BookStore store;
    private Scanner sc;

    public SignInInterface(BookStore store, Scanner sc) {
        this.continueRunning = true;
        this.store = store;
        this.sc = sc;
    }

    public String customerSignIn() {
        String userName = SignInMessages.EMPTY.toString();
        while(continueRunning) {
            System.out.println("--------------------------------");
            System.out.println("CUSTOMER LOGIN");
            System.out.print("Username: ");
            userName = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            System.out.println("--------------------------------");

            boolean passwordCorrect = validateLogin(userName, password);
            if(passwordCorrect) {
                this.continueRunning = false;

            } else {
                System.out.println("Error: Invalid Username/Password");
                System.out.println("Would you like to try again?");
                System.out.println("\t 1. Yes");
                System.out.println("\t 2. No");
                int userChoice = sc.nextInt();
                sc.nextLine();
                if (userChoice == 2) {
                    this.continueRunning = false;
                    userName = SignInMessages.SIGN_IN_INVALID.toString();
                }
            }

        }
        return(userName);
    }

    private boolean validateLogin(String userName, String password) {
        PasswordEncryptor passwordEncryptor = store.getPasswordEncryptor();
        Member currentMember = store.getMember(userName);
        return(passwordEncryptor.checkPassword(password, currentMember));
    }




}
