package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Models.Product;

import java.time.LocalDateTime;
import java.util.Hashtable;

public class Sale {

    private static long nextOrderNum = 1;

    private long orderNum;
    private Hashtable<Long, Integer> productsSold;
    private Member customer;
    private LocalDateTime saleTime;

    public Sale(Member member) {
        this.customer = member;
        this.orderNum = nextOrderNum;
        incrementNextOrderNum();
        productsSold = new Hashtable<>();
        this.saleTime = LocalDateTime.now();
    }

    private static void incrementNextOrderNum() {
        nextOrderNum += 1;
    }

    private static void decrementNextOrderNum() {
        nextOrderNum -= 1;
    }

    public void addProduct(long productSold, int quantity) {
        productsSold.put(productSold, quantity);
    }

    public void removeProduct(long productSold) {
        productsSold.remove(productSold);
    }

    public long getOrderNum() {
        return orderNum;
    }

    public Hashtable<Long, Integer> getProductsSold() {
        return productsSold;
    }

    public Member getCustomer() {
        return customer;
    }

    public void setCustomer(Member member) {
        this.customer = member;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
    }

}
