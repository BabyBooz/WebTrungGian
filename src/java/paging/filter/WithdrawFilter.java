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
public class WithdrawFilter extends Pagination {

    String searchValue;
    Integer status;

    public WithdrawFilter() {
        super(1, 0, 0, ParamsPaging.PAGE_SIZE);
        this.searchValue = "";
    }

    public WithdrawFilter(String searchValue, Integer status, int page, int totalPage, int offset, int pageSize) {
        super(page, totalPage, offset, pageSize);
        this.searchValue = searchValue;
        this.status = status;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

}
