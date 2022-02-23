package fraley.austin.BookStore.UIs;

import fraley.austin.BookStore.Enums.MembershipType;
import fraley.austin.BookStore.Enums.USState;
import fraley.austin.BookStore.Models.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AccntManagementInterface {

    private BookStore store;
    private Scanner sc;
    private boolean continueRunning;

    public AccntManagementInterface(Scanner sc, BookStore store) {
        this.store = store;
        this.sc = sc;
        this.continueRunning = true;
    }

    public void accntManagementInterface(String userName) {
        while (continueRunning) {
            System.out.println("Chose a menu item: ");
            System.out.println("\t 1. View Account Details");
            System.out.println("\t 2. Change shipping address");
            System.out.println("\t 3. Change Payment Method");
            System.out.println("\t 4. Change Membership Type");
            System.out.println("\t 5. Back");

            int userResponse = sc.nextInt();
            sc.nextLine();

            switch (userResponse) {
                case 1:
                    viewAccountDetails(userName);
                    break;
                case 2:
                    changeShippingDetails(userName);
                    break;
                case 3:
                    changePaymentMethod(userName);
                    break;
                case 4:
                    changeMembershipType(userName);
                    break;
                case 5:
                    this.continueRunning = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void changeMembershipType(String userName) {
        Membership currentMembership = store.getMember(userName).getMembership();
        MembershipType alternativeMembershipType = currentMembership.getMType().equals(MembershipType.BASIC) ? MembershipType.PREMIUM : MembershipType.BASIC;
        boolean continueChanging = true;
        while(continueChanging) {
            System.out.println("You currently have a " + currentMembership.getMType().toString().toLowerCase() + " membership");
            System.out.println("Are you sure you would like to change your membership type?");
            System.out.println("\t 1. Yes, I would like to change my membership to " + alternativeMembershipType);
            System.out.println("\t 2. No, I don't want to change my membership type.");
            int userChoice = sc.nextInt();
            sc.nextLine();

            switch(userChoice) {
                case 1:
                    currentMembership.setMType(alternativeMembershipType);
                    currentMembership.setMembershipFee();
                    break;
                case 2:
                    continueChanging = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void changePaymentMethod(String userName) {
        PaymentMethod customerPayment = store.getMember(userName).getPayMethod();
        boolean continueChanging = true;
        while(continueChanging) {
            if (customerPayment.isInstantiated()) {
                customerPayment.printCardInfo();
                System.out.println("Is this correct or would you like to change your payment method?");
                System.out.println("\t 1. Keep this payment");
                System.out.println("\t 2. Continue and change payment method");

                int userChoice = sc.nextInt();
                sc.nextLine();
                if (userChoice == 1) {
                    continueChanging = false;
                }
            }
            if (continueChanging) {
                PaymentMethod newPaymentMethod;
                System.out.println("------------------New Payment Method-------------------");
                System.out.println("What is the card number?");
                long cardNumber = sc.nextLong();
                sc.nextLine();
                System.out.println("What is the cardholder name?");
                String cardHolder = sc.nextLine();
                System.out.println("What is the CVC number?");
                int cvc = sc.nextInt();
                sc.nextLine();
                System.out.println("What is the expiration date? Format: YYYY-MM-DD");
                String expDateStr = sc.nextLine();
                try {
                    LocalDate expDate = LocalDate.parse(expDateStr);
                    newPaymentMethod = new PaymentMethod(cardNumber, cardHolder, cvc, expDate);
                    store.getMember(userName).setPayMethod(newPaymentMethod);
                    continueChanging = false;
                    System.out.println("-----------------------------------------------------");
                } catch (DateTimeParseException e) {
                    System.out.println("Error the date that was entered is not valid");
                }

            }
        }
    }

    private void changeShippingDetails(String userName) {
        Address currentAddress = store.getMember(userName).getMembership().getShippingAddress();
        boolean continueChanging = true;
        while(continueChanging) {
            if(currentAddress.isInstantiated()) {
                System.out.println("--------------Current Shipping Address--------------");
                System.out.println(currentAddress);
                System.out.println("----------------------------------------------------");
                System.out.println("Is this the correct shipping address or would you like to change it?");
                System.out.println("\t 1. Keep this shipping address");
                System.out.println("\t 2. Change your shipping address");
                int userChoice = sc.nextInt();
                sc.nextLine();
                if(userChoice == 1) {continueChanging = false;}
            }
            if (continueChanging) {
                Address newShippingAddress;
                System.out.println("------------------New Shipping Address-------------------");

                System.out.println("What is your street address?");
                String streetAddress = sc.nextLine();

                System.out.println("What is the city of your address?");
                String city = sc.nextLine();

                System.out.println("What state are you in? Example: NC");
                String state = sc.nextLine();
                USState shippingState = USState.valueOf(state);

                System.out.println("What is your zipcode?");
                int zipcode = sc.nextInt();
                sc.nextLine();

                System.out.println("----------------------------------------------------------");

                newShippingAddress = new Address(streetAddress, city, zipcode, shippingState);
                store.getMember(userName).getMembership().setShippingAddress(newShippingAddress);
                continueChanging = false;

            }
        }
    }

    private void viewAccountDetails(String userName) {
        Member currentMember = store.getMember(userName);
        Membership currentMembership = currentMember.getMembership();
        System.out.println("-------------Account Details-------------");

        System.out.println("Member Details");
        System.out.println("\t Member ID: " + currentMember.getMemberId());
        System.out.println("\t Username: " + userName);
        System.out.println("\t Name: " + currentMember.getFName() + " " + currentMember.getLName());
        System.out.println("\t Birthdate: " + currentMember.getBirthDate());

        System.out.println("Membership Details");
        System.out.println("\t Membership ID: " + currentMembership.getMembershipId());
        System.out.println("\t Member Type: " + currentMembership.getMType());
        String memberStatus = currentMembership.isGoodStanding() ? "Good Standing" : "Bad Standing";
        System.out.println("\t Membership Fee: $" + currentMembership.getMembershipFee());
        System.out.println("\t Member Status: " + memberStatus);
        System.out.println("Shipping Address: " + currentMembership.getShippingAddress());
        System.out.println("Payment Method: " + currentMember.getPayMethod());

        System.out.println("-----------------------------------------");
    }

}
