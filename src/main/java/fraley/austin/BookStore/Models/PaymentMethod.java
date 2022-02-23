package fraley.austin.BookStore.Models;

import java.time.LocalDate;

public class PaymentMethod {

    private long cardNumber;
    private String cardholder;
    private int cvc;
    private LocalDate expDate;

    public PaymentMethod(long cardNumber, String cardholder, int cvc, LocalDate expDate) {
        this.cardNumber = cardNumber;
        this.cardholder = cardholder;
        this.cvc = cvc;
        this.expDate = expDate;
    }

    public PaymentMethod() {
    }

    public boolean isInstantiated() {
        return(cardNumber != 0);
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public void printCardInfo() {
        System.out.println("--------------Current Payment Method---------------");
        System.out.println("Cardholder Name: " + getCardholder());
        System.out.println("Card Number: " + getCardNumber());
        System.out.println("Card CVC #: " + getCvc());
        System.out.println("Card Expiration Date: " + getExpDate());
        System.out.println("---------------------------------------------------");
    }

    @Override
    public String toString() {
        return("\n\t" + getCardholder() + "\n\t"
                + getCardNumber() + "\n\t"
                + getExpDate() + " " + getCvc());
    }
}
