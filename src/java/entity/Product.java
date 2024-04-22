
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author linhnghiem
 */
public class Product {
    private int id;
    private int created_by;
    private String name;
    private String image;
    private Float price;
    private int status_fee;
    private String description;
    private String contact;
    private String hidden_content;
    private int status_product;
    private Date created_date;
    private Date updated_date;

    public Product() {
    }

    public Product(int id, int created_by, String name, String image, Float price, int status_fee, String description, String contact, String hidden_content, int status_product, Date created_date, Date updated_date) {
        this.id = id;
        this.created_by = created_by;
        this.name = name;
        this.image = image;
        this.price = price;
        this.status_fee = status_fee;
        this.description = description;
        this.contact = contact;
        this.hidden_content = hidden_content;
        this.status_product = status_product;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public Product(int id, int created_by, String name, String image, Float price, int status_fee, String description, String contact, String hidden_content, int status_product) {
        this.id = id;
        this.created_by = created_by;
        this.name = name;
        this.image = image;
        this.price = price;
        this.status_fee = status_fee;
        this.description = description;
        this.contact = contact;
        this.hidden_content = hidden_content;
        this.status_product = status_product;
    }

    public Product(int id, int created_by, String name, Float price, int status_fee, String description, String contact, String hidden_content, int status_product) {
        this.id = id;
        this.created_by = created_by;
        this.name = name;
        this.price = price;
        this.status_fee = status_fee;
        this.description = description;
        this.contact = contact;
        this.hidden_content = hidden_content;
        this.status_product = status_product;
    }

    public Product( int created_by, String name, Float price, int status_fee, String description, String contact, String hidden_content, int status_product) {
        this.created_by = created_by;
        this.name = name;
        this.price = price;
        this.status_fee = status_fee;
        this.description = description;
        this.contact = contact;
        this.hidden_content = hidden_content;
        this.status_product = status_product;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getStatus_fee() {
        return status_fee;
    }

    public void setStatus_fee(int status_fee) {
        this.status_fee = status_fee;
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

    public int getStatus_product() {
        return status_product;
    }

    public void setStatus_product(int status_product) {
        this.status_product = status_product;
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", created_by=" + created_by + ", name=" + name + ", image=" + image + ", price=" + price + ", status_fee=" + status_fee + ", description=" + description + ", contact=" + contact + ", hidden_content=" + hidden_content + ", status_product=" + status_product + ", created_date=" + created_date + ", updated_date=" + updated_date + '}';
    }

    
}
