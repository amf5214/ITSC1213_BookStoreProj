package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Enums.MembershipType;
import fraley.austin.BookStore.Enums.ProductType;
import fraley.austin.BookStore.Enums.USState;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class BookStore {

    private Hashtable<Long, Sale> sales;
    private Hashtable<Long, Product> products;
    private Hashtable<String, Member> members;
    private Hashtable<Member, ArrayList<Long>> memberOrders;

    public BookStore() {
        this.sales = new Hashtable<>();
        this.products = new Hashtable<>();
        this.members = new Hashtable<>();
        this.memberOrders = new Hashtable<>();
    }

    public long signUp(String userName, MembershipType type) {
        Member currentUser = new Member(userName, type);
        this.members.put(currentUser.getUserName(), currentUser);
        this.memberOrders.put(currentUser, new ArrayList<>());
        return(currentUser.getMemberId());
    }

    public void setUserDetails(String userName, String fName, String lName, LocalDate birthDate) {
        Member customer = members.get(userName);
        customer.setFName(fName);
        customer.setLName(lName);
        customer.setBirthDate(birthDate);
    }

    public void setMembershipDetails(String userName, String address,  String city, USState state, int zipcode) {
        Membership customerMembership = members.get(userName).getMembership();
        customerMembership.setShippingAddress(address);
        customerMembership.setShippingCity(city);
        customerMembership.setShippingState(state);
        customerMembership.setShippingZipcode(zipcode);
    }

    public Member getMember(String userName) {
        return members.get(userName);
    }

    public Hashtable<Long, Product> getProducts() {
        return(products);
    }

    public Hashtable<Long, Sale> getSales() {
        return(sales);
    }

    public long createSale(Member member) {
        Sale sale = new Sale(member);
        sales.put(sale.getOrderNum(), sale);
        memberOrders.get(member).add(sale.getOrderNum());
        return sale.getOrderNum();
    }

    public void deleteSale(long orderNum) {
        Sale currentSale = sales.get(orderNum);
        Member currentMember = currentSale.getCustomer();
        sales.remove(currentSale.getOrderNum());
        memberOrders.get(currentMember).remove(currentSale.getOrderNum());
    }

    public Sale getSale(long orderNum) {
        return sales.get(orderNum);
    }

    public boolean addProductToSale(long orderNum, long productId, int quantity) {
        Product currentProduct = products.get(productId);
        if(currentProduct.getQuantity() > quantity) {
            Sale currentSale = sales.get(orderNum);
            currentSale.addProduct(productId, quantity);
            currentProduct.setQuantity(currentProduct.getQuantity()-quantity);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeProductFromSale(long orderNum, long productId) {
        Product currentProduct = products.get(productId);
        Sale currentSale = sales.get(orderNum);
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
        products.put(newProduct.getProductId(), newProduct);
        return newProduct.getProductId();
    }

    public double calculateSaleCost(long orderNum) {
        double returnSum = 0;
        Sale currentSale = sales.get(orderNum);
        Hashtable<Long, Integer> productsInSale = currentSale.getProductsSold();
        for(Long currentProductId : productsInSale.keySet()) {
            Product currentProduct = products.get(currentProductId);
            returnSum += (currentProduct.getUnitPrice() * currentSale.getProductsSold().get(currentProductId));
        }
        return(returnSum);
    }

    public double calculateHowMuchHasBeenSpent(Member member) {
        double totalCost = 0;
        for(long orderNum : memberOrders.get(member)) {
            totalCost += calculateSaleCost(orderNum);
        }
        return(totalCost);
    }

    public void printItem(long itemNum) {
        Product currentProduct = products.get(itemNum);
        System.out.println("\tItem Number: " + currentProduct.getProductId() + ", Name: " + currentProduct.getItemName()
                + ", Type: " + currentProduct.getProductType() + ", Price: $" + currentProduct.getUnitPrice() + ", Qua: " + currentProduct.getQuantity());
    }

    public void printSale(long orderNum) {
        Sale currentOrder = sales.get(orderNum);
        System.out.println("\n----------------Order-------------");
        System.out.println("Customer Username: " + currentOrder.getCustomer().getUserName());
        System.out.println("Customer Name: " + currentOrder.getCustomer().getFName() + " " + currentOrder.getCustomer().getLName() + "\n");
        System.out.println("Total Cost: $" + calculateSaleCost(orderNum));
        System.out.println("\nItems: \n{");

        for(long currentProductId : currentOrder.getProductsSold().keySet()) {
            Product currentProduct = products.get(currentProductId);
            System.out.println("\tItem Number: " + currentProduct.getProductId() + ", Name: " + currentProduct.getItemName()
                    + ", Price: $" + currentProduct.getUnitPrice() + ", Qua: " + currentOrder.getProductsSold().get(currentProductId));
        }
        System.out.println("}");
        System.out.println("--------------End Order--------------\n");
    }

}
