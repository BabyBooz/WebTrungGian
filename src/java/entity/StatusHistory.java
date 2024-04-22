
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
public class StatusHistory {
    private int id;
    private Date created_date;
    private int statusChecking;
    private int createdBy;
    private int productId;
    public StatusHistory() {
    }

    public StatusHistory(int statusChecking, int createdBy, int productId) {
        this.statusChecking = statusChecking;
        this.createdBy = createdBy;
        this.productId = productId;
    }

    public StatusHistory(int createdBy, int productId) {
        this.createdBy = createdBy;
        this.productId = productId;
    }

    public StatusHistory(int id,int productId, Date created_date, int statusChecking, int createdBy) {
        this.id = id;
        this.created_date = created_date;
        this.statusChecking = statusChecking;
        this.createdBy = createdBy;
        this.productId = productId;
    }
    

    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getStatusChecking() {
        return statusChecking;
    }

    public void setStatusChecking(int statusChecking) {
        this.statusChecking = statusChecking;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "StatusHistory{" + "id=" + id + ", created_date=" + created_date + ", statusChecking=" + statusChecking + ", createdBy=" + createdBy + '}';
    }
    
    
}
