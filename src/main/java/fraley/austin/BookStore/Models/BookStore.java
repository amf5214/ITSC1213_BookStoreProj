package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Enums.MembershipType;
import fraley.austin.BookStore.Enums.ProductType;
import fraley.austin.BookStore.Enums.USState;
import fraley.austin.BookStore.Security.PasswordEncryptor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class BookStore extends AbstractBookStore {

    public BookStore() {
        super(new Hashtable<>(), new Hashtable<>(), new Hashtable<>(), new Hashtable<>(), new PasswordEncryptor());
    }

    public long signUp(String userName, MembershipType type) {
        Member currentUser = new Member(userName, type);
        super.getMembers().put(currentUser.getUserName(), currentUser);
        super.getMemberOrders().put(currentUser, new ArrayList<>());
        return(currentUser.getMemberId());
    }

    public void setUserDetails(String userName, String fName, String lName, LocalDate birthDate) {
        Member customer = super.getMembers().get(userName);
        customer.setFName(fName);
        customer.setLName(lName);
        customer.setBirthDate(birthDate);
    }

    public void setMembershipDetails(String userName, String address,  String city, USState state, int zipcode) {
        Membership customerMembership = super.getMembers().get(userName).getMembership();
        customerMembership.getShippingAddress().setStreetAddress(address);
        customerMembership.getShippingAddress().setShippingCity(city);
        customerMembership.getShippingAddress().setShippingState(state);
        customerMembership.getShippingAddress().setShippingZipcode(zipcode);
    }


    public long createSale(Member member) {
        Sale sale = new Sale(member);
        super.getSales().put(sale.getOrderNum(), sale);
        super.getMemberOrders().get(member).add(sale.getOrderNum());
        return sale.getOrderNum();
    }

    public void deleteSale(long orderNum) {
        Sale currentSale = super.getSales().get(orderNum);
        Member currentMember = currentSale.getCustomer();
        super.getSales().remove(currentSale.getOrderNum());
        super.getMemberOrders().get(currentMember).remove(currentSale.getOrderNum());
    }


    public boolean addProductToSale(long orderNum, long productId, int quantity) {
        Product currentProduct = super.getProducts().get(productId);
        if(currentProduct.getQuantity() > quantity) {
            Sale currentSale = super.getSales().get(orderNum);
            currentSale.addProduct(productId, quantity);
            currentProduct.setQuantity(currentProduct.getQuantity()-quantity);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeProductFromSale(long orderNum, long productId) {
        Product currentProduct = super.getProducts().get(productId);
        Sale currentSale = super.getSales().get(orderNum);
        if(currentSale.getProductsSold().containsKey(productId)) {
            int currentQuantity = currentSale.getProductsSold().get(orderNum);
            currentSale.removeProduct(productId);
            currentProduct.setQuantity(currentProduct.getQuantity()+currentQuantity);
            return true;
        } else {
            return false;
        }
    }

    public long addProduct(String name, int quantity, ProductType type, double price) {
        Product newProduct = new Product(name, quantity, price);
        newProduct.setProductType(type);
        super.getProducts().put(newProduct.getProductId(), newProduct);
        return newProduct.getProductId();
    }

    public double calculateSaleCost(long orderNum) {
        double returnSum = 0;
        Sale currentSale = super.getSales().get(orderNum);
        Hashtable<Long, Integer> productsInSale = currentSale.getProductsSold();
        for(Long currentProductId : productsInSale.keySet()) {
            Product currentProduct = super.getProducts().get(currentProductId);
            returnSum += (currentProduct.getUnitPrice() * currentSale.getProductsSold().get(currentProductId));
        }
        return(returnSum);
    }

    public double calculateMemberSpending
            (Member member) {
        double totalCost = 0;
        for(long orderNum : super.getMemberOrders().get(member)) {
            totalCost += calculateSaleCost(orderNum);
        }
        return(totalCost);
    }

    public void printItem(long itemNum) {
        Product currentProduct = super.getProducts().get(itemNum);
        System.out.println("\tItem Number: " + currentProduct.getProductId() + ", Name: " + currentProduct.getItemName()
                + ", Type: " + currentProduct.getProductType() + ", Price: $" + currentProduct.getUnitPrice() + ", Qua: " + currentProduct.getQuantity());
    }

    public void printSale(long orderNum) {
        Sale currentOrder = super.getSales().get(orderNum);
        System.out.println("\n----------------Order-------------");
        System.out.println("Customer Username: " + currentOrder.getCustomer().getUserName());
        System.out.println("Customer Name: " + currentOrder.getCustomer().getFName() + " " + currentOrder.getCustomer().getLName() + "\n");
        System.out.println("Total Cost: $" + calculateSaleCost(orderNum));
        System.out.println("\nItems: \n{");

        for(long currentProductId : currentOrder.getProductsSold().keySet()) {
            Product currentProduct = super.getProducts().get(currentProductId);
            System.out.println("\tItem Number: " + currentProduct.getProductId() + ", Name: " + currentProduct.getItemName()
                    + ", Price: $" + currentProduct.getUnitPrice() + ", Qua: " + currentOrder.getProductsSold().get(currentProductId));
        }
        System.out.println("}");
        System.out.println("--------------End Order--------------\n");
    }


}
