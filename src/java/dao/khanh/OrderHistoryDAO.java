/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.khanh;

import dao.AccountDAO;
import dao.MyDAO;
import entity.OrderHistory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import paging.filter.ComplainFilter;

/**
 *
 * @author Fangl
 */
public class OrderHistoryDAO extends MyDAO {

    private AccountDAO accountDAO = new AccountDAO();

    public OrderHistory getOrderHistoryById(Integer id) {
        List<OrderHistory> list = new ArrayList<>();
        xSql = "select * from `order_history` where id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                OrderHistory orderHistory = new OrderHistory(
                        rs.getInt("status"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date"));
                orderHistory.setIsComplain(rs.getBoolean("is_complain"));
                orderHistory.setRequestComplain(rs.getString("request_complain"));
                orderHistory.setResponseComplain(rs.getString("response_complain"));
                orderHistory.setIsPayback(rs.getBoolean("is_payback"));
                orderHistory.setId(rs.getInt("id"));
                return orderHistory;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Integer countTotalOrderHistory() {
        xSql = "select count(*) as total from `order_history`";
        try {
            ps = connection.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
               return rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public OrderHistory getOrderHistoryComplainById(Integer id) {
        List<OrderHistory> list = new ArrayList<>();
        xSql = "select * from `order_history` where id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                OrderHistory orderHistory = new OrderHistory(
                        rs.getInt("status"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date"));
                orderHistory.setIsComplain(rs.getBoolean("is_complain"));
                orderHistory.setRequestComplain(rs.getString("request_complain"));
                orderHistory.setResponseComplain(rs.getString("response_complain"));
                orderHistory.setIsPayback(rs.getBoolean("is_payback"));
                orderHistory.setId(rs.getInt("id"));
                return orderHistory;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<OrderHistory> getOrderHistoryComplain(ComplainFilter complainFilter) {
        List<OrderHistory> list = new ArrayList<>();
        xSql = "SELECT oh.*, seller.user AS seller_name, buyer.user AS buyer_name, product.name AS product_name "
                + "FROM order_history oh "
                + "LEFT JOIN Account seller ON oh.seller_id = seller.id "
                + "LEFT JOIN Account buyer ON oh.buyer_id = buyer.id "
                + "LEFT JOIN Product product ON oh.product_id = product.id "
                + "WHERE is_complain = true ";
        if (complainFilter.getStatus() != null) {
            xSql += "AND oh.status = ? ";
        } else {
            xSql += "AND oh.status IN (3, 4,5) ";
        }
        xSql += "AND (buyer.user LIKE ? OR seller.user LIKE ? OR product.name LIKE ?) ";
        xSql += " Order by Id Desc \n LIMIT ? OFFSET ? ";
        try {
            ps = connection.prepareStatement(xSql);
            int paramIndex = 1;
            if (complainFilter.getStatus() != null) {
                ps.setInt(paramIndex++, complainFilter.getStatus());
            }
            ps.setString(paramIndex++, "%" + complainFilter.getSearchValue() + "%");
            ps.setString(paramIndex++, "%" + complainFilter.getSearchValue() + "%");
            ps.setString(paramIndex++, "%" + complainFilter.getSearchValue() + "%");
            ps.setInt(paramIndex++, complainFilter.getPageSize());
            ps.setInt(paramIndex++, complainFilter.getOffset());
            System.out.println(""+ ps.toString());
            rs = ps.executeQuery();
            
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory(
                        rs.getInt("status"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date"));
                orderHistory.setIsComplain(rs.getBoolean("is_complain"));
                orderHistory.setRequestComplain(rs.getString("request_complain"));
                orderHistory.setResponseComplain(rs.getString("response_complain"));
                orderHistory.setIsPayback(rs.getBoolean("is_payback"));
                orderHistory.setId(rs.getInt("id"));
                list.add(orderHistory);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean updateOrderHistory(OrderHistory orderHistory) {
        boolean success = false;
        xSql = "UPDATE `order_history` SET buyer_id = ?, "
                + "status = ?, is_complain = ?, request_complain = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, orderHistory.getBuyer_id());
            ps.setInt(2, orderHistory.getStatus());
            ps.setBoolean(3, orderHistory.getIsComplain());
            ps.setString(4, orderHistory.getRequestComplain());
            ps.setInt(5, orderHistory.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating order history: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
        return success;
    }

    public boolean updateIsComplain(int orderHistoryId, boolean isComplain) {
        boolean success = false;
        xSql = "UPDATE `order_history` SET is_complain = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setBoolean(1, isComplain);
            ps.setInt(2, orderHistoryId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating is_complain: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
        return success;
    }

    public boolean complainOrder(int orderHistoryId, String requestComplain) {
        boolean success = false;
        xSql = "UPDATE `order_history` SET is_complain = true, request_complain = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setString(1, requestComplain);
            ps.setInt(2, orderHistoryId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error complaining order: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
        return success;
    }

    public boolean updateComplainResponse(int orderHistoryId, String responseComplain, boolean isPayback, Integer status) {
        boolean success = false;
        xSql = "UPDATE `order_history` SET response_complain = ?, is_payback = ?, status = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setString(1, responseComplain);
            ps.setBoolean(2, isPayback);
            ps.setInt(3, status);
            ps.setInt(4, orderHistoryId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating complain response: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
        return success;
    }

}
