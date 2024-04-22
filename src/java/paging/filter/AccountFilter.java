/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paging.filter;

import paging.Pagination;
import paging.ParamsPaging;

/**
 *
 * @author Fangl
 */
public class AccountFilter extends Pagination{
    private Boolean gender;
    private String role;
    private Boolean status;
    private String searchValue;
    private String sortBy;

    public AccountFilter() {
        super(1, 0, 0, ParamsPaging.PAGE_SIZE);
        this.gender = true;
        this.role = "";
        this.status = true;
        this.searchValue = "";
        this.sortBy = "";
    }
    

    public AccountFilter(Boolean gender, String role, Boolean status, String searchValue, String sortBy, int page, int totalPage, int offset, int pageSize) {
        super(page, totalPage, offset, pageSize);
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.searchValue = searchValue;
        this.sortBy = sortBy;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    
    
    
    
    
}
