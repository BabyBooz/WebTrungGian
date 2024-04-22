/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.khanh;

import paging.filter.AccountFilter;
import dao.MyDAO;
import entity.Account;
import entity.khanh.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import paging.filter.ProductFilter;

/**
 *
 * @author Fangl
 */
public class ProductDAO extends MyDAO {

    public List<Product> findAllByFilter(ProductFilter productFilter) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product \n"
                + "WHERE (name LIKE ? OR description LIKE ?) ";
        if (productFilter.getStatus() != null) {
            query += "AND status_product = ? ";
        }
        if (productFilter.getMinPrice() != null) {
            query += "AND price >= ? ";
        }
        if (productFilter.getMaxPrice() != null) {
            query += "AND price <= ? ";
        }
        query += "ORDER BY ";
        switch (productFilter.getSortBy()) {
            case "name":
            case "description":
            case "price":
            case "status":
            case "id":
                query += productFilter.getSortBy();
                break;
            default:
                query += "id"; // Default sorting by id
                break;
        }
        query += " \nLIMIT ? OFFSET ? ";
        try {
            ps = connection.prepareStatement(query);
            String searchValuePattern = "%" + productFilter.getSearchValue() + "%";
            ps.setString(1, searchValuePattern);
            ps.setString(2, searchValuePattern);
            int parameterIndex = 3; // Start index for additional parameters
            if (productFilter.getStatus() != null) {
                ps.setBoolean(parameterIndex++, productFilter.getStatus());
            }
            if (productFilter.getMinPrice() != null) {
                ps.setDouble(parameterIndex++, productFilter.getMinPrice());
            }
            if (productFilter.getMaxPrice() != null) {
                ps.setDouble(parameterIndex++, productFilter.getMaxPrice());
            }
            ps.setInt(parameterIndex++, productFilter.getPageSize());
            ps.setInt(parameterIndex, productFilter.getOffset());
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStatusProduct(rs.getBoolean("status_product"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    

    private Product resultSetToProduct(ResultSet resultSet) throws SQLException {
        // Extract data from ResultSet and create a Product object
        int id = resultSet.getInt("id");
        String createdDate = resultSet.getString("created_date");
        String updatedDate = resultSet.getString("updated_date");
        int createdBy = resultSet.getInt("created_by");
        String name = resultSet.getString("name");
        String image = resultSet.getString("image");
        double price = resultSet.getDouble("price");
        int statusFee = resultSet.getInt("status_fee");
        String description = resultSet.getString("description");
        String contact = resultSet.getString("contact");
        String hiddenContent = resultSet.getString("hidden_content");
        boolean statusProduct = resultSet.getBoolean("status_product");

        Product product = new Product();
        product.setId(id);
        product.setCreatedDate(createdDate);
        product.setUpdatedDate(updatedDate);
        product.setCreatedBy(createdBy);
        product.setName(name);
        product.setImage(image);
        product.setPrice(price);
        product.setStatusFee(statusFee);
        product.setDescription(description);
        product.setContact(contact);
        product.setHiddenContent(hiddenContent);
        product.setStatusProduct(statusProduct);

        return product;
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO product (created_date, updated_date, created_by, name, image, price, status_fee, description, contact, hidden_content, status_product) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, product.getCreatedDate());
            ps.setString(2, product.getUpdatedDate());
            ps.setInt(3, product.getCreatedBy());
            ps.setString(4, product.getName());
            ps.setString(5, product.getImage());
            ps.setDouble(6, product.getPrice());
            ps.setInt(7, product.getStatusFee());
            ps.setString(8, product.getDescription());
            ps.setString(9, product.getContact());
            ps.setString(10, product.getHiddenContent());
            ps.setBoolean(11, product.getStatusProduct());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToProduct(resultSet);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Double getMaxPrice() {
        Double maxPrice = null;
        String query = "SELECT MAX(price) AS max_price FROM Product";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxPrice = rs.getDouble("max_price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxPrice;
    }

    public Integer countTotalProduct() {
        String sql = "SELECT count(*) as total FROM product";
        try {
            ps = con.prepareStatement(sql);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET created_date=?, updated_date=?, created_by=?, name=?, image=?, price=?, status_fee=?, description=?, contact=?, hidden_content=?, status_product=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, product.getCreatedDate());
            ps.setString(2, product.getUpdatedDate());
            ps.setInt(3, product.getCreatedBy());
            ps.setString(4, product.getName());
            ps.setString(5, product.getImage());
            ps.setDouble(6, product.getPrice());
            ps.setInt(7, product.getStatusFee());
            ps.setString(8, product.getDescription());
            ps.setString(9, product.getContact());
            ps.setString(10, product.getHiddenContent());
            ps.setBoolean(11, product.getStatusProduct());
            ps.setInt(12, product.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Assuming you have initialized ProductFilter with proper values for pagination
        ProductFilter productFilter = new ProductFilter();
        productFilter.setPageSize(10); // Example page size
        productFilter.setOffset(0);    // Example offset
        productFilter.setSearchValue("");
        productFilter.setMinPrice(20.00);
        productFilter.setMaxPrice(22.0);
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAllByFilter(productFilter);

        // Display the retrieved products
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
