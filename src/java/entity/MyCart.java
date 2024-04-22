/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author linhnghiem
 */
public class MyCart {
    private int id;
    private int status_fee;
    private int status_checking;
    private int seller_id;
    private int buyer_id;
    private int product_id;
    private float price;
    private String description;
    private String contact;
    private String hidden_content;
    private Date created_date;
    private Date updated_date;
    private Integer order_history_id;
    

    public MyCart() {
    }

    public MyCart(int id, int status_fee, int status_checking, int seller_id, int buyer_id, int product_id, float price, String description, String contact, String hidden_content, Date created_date, Date updated_date) {
        this.id = id;
        this.status_fee = status_fee;
        this.status_checking = status_checking;
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

    public MyCart(int status_fee, int status_checking, int seller_id, int buyer_id, int product_id, float price, String description, String contact, String hidden_content, Date created_date, Date updated_date) {
        this.status_fee = status_fee;
        this.status_checking = status_checking;
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

    

    public MyCart(int status_fee, int status_checking, int seller_id, int buyer_id, float price, String description, String contact, String hidden_content, Date created_date, Date updated_date) {
        this.status_fee = status_fee;
        this.status_checking = status_checking;
        this.seller_id = seller_id;
        this.buyer_id = buyer_id;
        this.price = price;
        this.description = description;
        this.contact = contact;
        this.hidden_content = hidden_content;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus_fee() {
        return status_fee;
    }

    public void setStatus_fee(int status_fee) {
        this.status_fee = status_fee;
    }

    public int getStatus_checking() {
        return status_checking;
    }

    public void setStatus_checking(int status_checking) {
        this.status_checking = status_checking;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Integer getOrder_history_id() {
        return order_history_id;
    }

    public void setOrder_history_id(Integer order_history_id) {
        this.order_history_id = order_history_id;
    }
    
    @Override
    public String toString() {
        return "MyCart{" + "id=" + id + ", status_fee=" + status_fee + ", status_checking=" + status_checking + ", seller_id=" + seller_id + ", buyer_id=" + buyer_id + ", price=" + price + ", description=" + description + ", contact=" + contact + ", hidden_content=" + hidden_content + ", created_date=" + created_date + ", updated_date=" + updated_date + '}';
    }
    
    
}
