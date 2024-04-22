/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Order1 {
    private int id;
    private int status;
    private int sellerId;
    private int buyerId;
    private int productId;
    private float price;
//    private int unit;
    private String description;
    private String contract;
    private String hiddenContent;
    private Date createdDate;
    private Date updatedDate;
    private String sellerName;
    private String buyerName;
    private String productName;
    private Account seller;
    private Account buyer;
    private Product1 product;

    public Order1(int status, int sellerId, int buyerId, int productId, float price, String description, String contract, String hiddenContent) {
        this.status = status;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.price = price;
        this.description = description;
        this.contract = contract;
        this.hiddenContent = hiddenContent;
    }

    public Order1(int id, float price, String description, String contract, String hiddenContent) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.contract = contract;
        this.hiddenContent = hiddenContent;
    }

    
    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHiddenContent() {
        return hiddenContent;
    }

    public void setHiddenContent(String hiddenContent) {
        this.hiddenContent = hiddenContent;
    }
    
    public Order1() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Account getSeller() {
        return seller;
    }

    public void setSeller(Account seller) {
        this.seller = seller;
    }

    public Account getBuyer() {
        return buyer;
    }

    public void setBuyer(Account buyer) {
        this.buyer = buyer;
    }

    public Product1 getProduct() {
        return product;
    }

    public void setProduct(Product1 product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", status=" + status + ", sellerId=" + sellerId + ", buyerId=" + buyerId + ", productId=" + productId + ", price=" + price + ", description=" + description + ", contract=" + contract + ", hiddenContent=" + hiddenContent + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", sellerName=" + sellerName + ", buyerName=" + buyerName + ", productName=" + productName + ", seller=" + seller + ", buyer=" + buyer + ", product=" + product + '}';
    }



    
    
}
