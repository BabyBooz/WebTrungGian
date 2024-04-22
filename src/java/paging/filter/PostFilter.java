/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.filter;

import paging.Pagination;
import paging.ParamsPaging;

/**
 *
 * @author Fangl
 */
public class PostFilter extends Pagination {

    private String searchValue;

    public PostFilter() {
        super(1, 0, 0, ParamsPaging.PAGE_SIZE);
        this.searchValue = "";
    }

    public PostFilter(String searchValue, int page, int totalPage, int offset, int pageSize) {
        super(page, totalPage, offset, pageSize);
        this.searchValue = searchValue;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    

}
