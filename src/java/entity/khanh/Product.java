/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.khanh;

/**
 *
 * @author Fangl
 */
public class Product {
    private int id;
    private String createdDate;
    private String updatedDate;
    private Integer createdBy;
    private String name;
    private String image;
    private Double price;
    private Integer statusFee;
    private String description;
    private String contact;
    private String hiddenContent;
    private Boolean statusProduct;

    public Product() {
    }

    public Product(int id, String createdDate, String updatedDate, Integer createdBy, String name, String image, Double price, Integer statusFee, String description, String contact, String hiddenContent, Boolean statusProduct) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.name = name;
        this.image = image;
        this.price = price;
        this.statusFee = statusFee;
        this.description = description;
        this.contact = contact;
        this.hiddenContent = hiddenContent;
        this.statusProduct = statusProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatusFee() {
        return statusFee;
    }

    public void setStatusFee(Integer statusFee) {
        this.statusFee = statusFee;
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

    public String getHiddenContent() {
        return hiddenContent;
    }

    public void setHiddenContent(String hiddenContent) {
        this.hiddenContent = hiddenContent;
    }

    public Boolean getStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(Boolean statusProduct) {
        this.statusProduct = statusProduct;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", name=" + name + ", image=" + image + ", price=" + price + ", statusFee=" + statusFee + ", description=" + description + ", contact=" + contact + ", hiddenContent=" + hiddenContent + ", statusProduct=" + statusProduct + '}';
    }
    
}
