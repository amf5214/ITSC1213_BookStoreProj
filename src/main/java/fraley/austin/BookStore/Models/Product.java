package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Enums.ProductType;

public class Product {

    private static long nextProductId = 1;

    private String itemName;
    private long productId;
    private ProductType productType;
    private int quantity;
    private double unitPrice;

    public Product(String name, int quantity, double price) {
        this.productId = nextProductId;
        incrementNextProductId();
        this.itemName = name;
        this.quantity = quantity;
        this.unitPrice = price;
    }

    private static void incrementNextProductId() {
        nextProductId += 1;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
