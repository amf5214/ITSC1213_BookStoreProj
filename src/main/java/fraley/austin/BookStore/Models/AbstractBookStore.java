package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Security.PasswordEncryptor;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class AbstractBookStore {

    private Hashtable<Long, Sale> sales;
    private Hashtable<Long, Product> products;
    private Hashtable<String, Member> members;
    private Hashtable<Member, ArrayList<Long>> memberOrders;
    private PasswordEncryptor passwordEncryptor;

    public AbstractBookStore(Hashtable<Long, Sale> sales, Hashtable<Long, Product> products,
                             Hashtable<String, Member> members, Hashtable<Member, ArrayList<Long>> memberOrders,
                             PasswordEncryptor passwordEncryptor) {
        this.sales = sales;
        this.products = products;
        this.members = members;
        this.memberOrders = memberOrders;
        this.passwordEncryptor = passwordEncryptor;
    }

    public Hashtable<Long, Sale> getSales() {
        return sales;
    }

    public void setSales(Hashtable<Long, Sale> sales) {
        this.sales = sales;
    }

    public Hashtable<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(Hashtable<Long, Product> products) {
        this.products = products;
    }

    public Hashtable<String, Member> getMembers() {
        return members;
    }

    public void setMembers(Hashtable<String, Member> members) {
        this.members = members;
    }

    public Hashtable<Member, ArrayList<Long>> getMemberOrders() {
        return memberOrders;
    }

    public void setMemberOrders(Hashtable<Member, ArrayList<Long>> memberOrders) {
        this.memberOrders = memberOrders;
    }

    public PasswordEncryptor getPasswordEncryptor() {
        return passwordEncryptor;
    }

    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public Member getMember(String userName) {
        return members.get(userName);
    }

    public Sale getSale(long orderNum) {
        return sales.get(orderNum);
    }
}
