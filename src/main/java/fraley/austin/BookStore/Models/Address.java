package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Enums.USState;

public class Address {

    private String streetAddress;
    private String shippingCity;
    private int shippingZipcode;
    private USState shippingState;

    public Address() {
    }

    public Address(String streetAddress, String shippingCity, int shippingZipcode, USState shippingState) {
        this.streetAddress = streetAddress;
        this.shippingCity = shippingCity;
        this.shippingZipcode = shippingZipcode;
        this.shippingState = shippingState;
    }

    public boolean isInstantiated() {
        return(!(streetAddress == null));
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public int getShippingZipcode() {
        return shippingZipcode;
    }

    public void setShippingZipcode(int shippingZipcode) {
        this.shippingZipcode = shippingZipcode;
    }

    public USState getShippingState() {
        return shippingState;
    }

    public void setShippingState(USState shippingState) {
        this.shippingState = shippingState;
    }

    @Override
    public String toString() {
        return("\n\t" + getStreetAddress() + "\n\t" + getShippingCity() + ", " + getShippingState() + " " + getShippingZipcode() );
    }
}
