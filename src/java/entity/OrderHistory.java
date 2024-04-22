
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.AccountDAO;
import java.sql.Date;

/**
 *
 * @author linhnghiem
 */
public class OrderHistory {
    private int id;
    private int status;
    private int seller_id;
    private String sellerName;
    private int buyer_id;
    private String buyerName;
    private int product_id;
    private String productName;
    private float price;
    private String description;
    private String contact;
    private String hidden_content;
    private Date created_date;
    private Date updated_date;
    private Boolean isComplain;
    private String requestComplain;
    private String responseComplain;
    private Boolean isPayback;

    public OrderHistory() {
    }

    public OrderHistory(int id, int status, int seller_id, int buyer_id, int product_id, float price, String description, String contact, String hidden_content, Date created_date, Date updated_date) {
        this.id = id;
        this.status = status;
        this.seller_id = seller_id;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.price = price;
        this.description = description;
        this.contact = contact;
        this.hidden_content = hidden_content;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }
    public OrderHistory( int status, int seller_id, int buyer_id, int product_id, float price, String description, String contact, String hidden_content, Date created_date, Date updated_date) {
        this.status = status;
        this.seller_id = seller_id;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.price = price;
        this.description = description;
        this.contact = contact;
        this.hidden_content = hidden_content;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public OrderHistory( int status, int seller_id, int buyer_id, int product_id, float price, String description, String contact, String hidden_content) {
        this.status = status;
        this.seller_id = seller_id;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.price = price;
        this.description = description;
        this.contact = contact;
        this.hidden_content = hidden_content;
    }

    
    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    

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

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }
    
    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHidden_content() {
        return hidden_content;
    }

    public void setHidden_content(String hidden_content) {
        this.hidden_content = hidden_content;
    }

    public Boolean getIsComplain() {
        return isComplain;
    }

    public void setIsComplain(Boolean isComplain) {
        this.isComplain = isComplain;
    }

    public String getRequestComplain() {
        return requestComplain;
    }

    public void setRequestComplain(String requestComplain) {
        this.requestComplain = requestComplain;
    }

    public String getResponseComplain() {
        return responseComplain;
    }

    public void setResponseComplain(String responseComplain) {
        this.responseComplain = responseComplain;
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

    public Boolean getIsPayback() {
        return isPayback;
    }

    public void setIsPayback(Boolean isPayback) {
        this.isPayback = isPayback;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    
    
    
    @Override
    public String toString() {
        return "OrderHistory{" + "id=" + id + ", status=" + status + ", seller_id=" + seller_id + ", buyer_id=" + buyer_id + ", product_id=" + product_id + ", price=" + price + ", description=" + description + ", contact=" + contact + ", hidden_content=" + hidden_content + '}';
    }

        
    
}
