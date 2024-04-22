/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.khanh;

import java.sql.Date;

/**
 *
 * @author Fangl
 */
public class WithdrawDTO {
     private int id;
    private int userId;
    private String bankName;
    private String bankBranch;
    private String accountNumber;
    private String accountName;
    private float price;
    private Integer statusTransaction; // Use Integer to handle NULL status
    private Date createdDate;
    private Date updatedDate;

    public WithdrawDTO() {
    }

    public WithdrawDTO(int id, int userId, String bankName, String bankBranch, String accountNumber, String accountName, float price, Integer statusTransaction, Date createdDate, Date updatedDate) {
        this.id = id;
        this.userId = userId;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.price = price;
        this.statusTransaction = statusTransaction;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public WithdrawDTO(int userId, String bankName, String accountName, float price, Integer statusTransaction) {
        this.userId = userId;
        this.bankName = bankName;
        this.accountName = accountName;
        this.price = price;
        this.statusTransaction = statusTransaction;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getStatusTransaction() {
        return statusTransaction;
    }

    public void setStatusTransaction(Integer statusTransaction) {
        this.statusTransaction = statusTransaction;
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

    @Override
    public String toString() {
        return "WithdrawDTO{" + "id=" + id + ", userId=" + userId + ", bankName=" + bankName + ", bankBranch=" + bankBranch + ", accountNumber=" + accountNumber + ", accountName=" + accountName + ", price=" + price + ", statusTransaction=" + statusTransaction + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + '}';
    }

    
    
}
