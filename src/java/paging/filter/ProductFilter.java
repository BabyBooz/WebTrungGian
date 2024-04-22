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
public class ProductFilter  extends Pagination{
    private String searchValue;
    private Boolean status;
    private String sortBy;
    private Double minPrice;
    private Double maxPrice;

    public ProductFilter() {
        super(1, 0, 0, ParamsPaging.PAGE_SIZE);
        this.searchValue = "";
        this.status = null;
        this.sortBy = "Id";
        this.minPrice = 0.0;
        this.maxPrice = null;
    }

    public ProductFilter(String searchValue, Boolean status, String sortBy, Double minPrice, Double maxPrice, int page, int totalPage, int offset, int pageSize) {
        super(page, totalPage, offset, pageSize);
        this.searchValue = searchValue;
        this.status = status;
        this.sortBy = sortBy;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

   

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    
    
    
    
    
    
    
}
