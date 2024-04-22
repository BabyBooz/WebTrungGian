/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.khanh;

import context.DBContext;
import dao.MyDAO;
import entity.khanh.WithdrawDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import paging.filter.WithdrawFilter;

/**
 *
 * @author Fangl
 */
public class WithdrawDAO extends MyDAO {

    public boolean addWithdraw(WithdrawDTO withdraw) {
        String sql = "INSERT INTO withdraw (userid, bank_name, bank_branch, account_number, account_name, price, status_transaction) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, withdraw.getUserId());
            ps.setString(2, withdraw.getBankName());
            ps.setString(3, withdraw.getBankBranch());
            ps.setString(4, withdraw.getAccountNumber());
            ps.setString(5, withdraw.getAccountName());
            ps.setFloat(6, withdraw.getPrice());
            ps.setInt(7, withdraw.getStatusTransaction());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public WithdrawDTO getWithdrawById(Integer withdrawId) {
        String sql = "SELECT * FROM withdraw where id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, withdrawId);
            rs = ps.executeQuery();
            if (rs.next()) {
                WithdrawDTO withdraw = new WithdrawDTO();
                withdraw.setId(rs.getInt("id"));
                withdraw.setUserId(rs.getInt("userid"));
                withdraw.setBankName(rs.getString("bank_name"));
                withdraw.setBankBranch(rs.getString("bank_branch"));
                withdraw.setAccountNumber(rs.getString("account_number"));
                withdraw.setAccountName(rs.getString("account_name"));
                withdraw.setPrice(rs.getFloat("price"));
                withdraw.setStatusTransaction(rs.getInt("status_transaction"));
                withdraw.setCreatedDate(rs.getDate("created_date"));
                withdraw.setUpdatedDate(rs.getDate("updated_date"));
                return withdraw;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
        public WithdrawDTO getWithdrawIsRequested(Integer userId) {
        String sql = "SELECT * FROM withdraw where userid = ? and status_transaction = 0";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                WithdrawDTO withdraw = new WithdrawDTO();
                withdraw.setId(rs.getInt("id"));
                withdraw.setUserId(rs.getInt("userid"));
                withdraw.setBankName(rs.getString("bank_name"));
                withdraw.setBankBranch(rs.getString("bank_branch"));
                withdraw.setAccountNumber(rs.getString("account_number"));
                withdraw.setAccountName(rs.getString("account_name"));
                withdraw.setPrice(rs.getFloat("price"));
                withdraw.setStatusTransaction(rs.getInt("status_transaction"));
                withdraw.setCreatedDate(rs.getDate("created_date"));
                withdraw.setUpdatedDate(rs.getDate("updated_date"));
                return withdraw;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WithdrawDTO> getAllWithdraws() {
        List<WithdrawDTO> withdraws = new ArrayList<>();
        String sql = "SELECT * FROM withdraw";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WithdrawDTO withdraw = new WithdrawDTO();
                withdraw.setId(rs.getInt("id"));
                withdraw.setUserId(rs.getInt("userid"));
                withdraw.setBankName(rs.getString("bank_name"));
                withdraw.setBankBranch(rs.getString("bank_branch"));
                withdraw.setAccountNumber(rs.getString("account_number"));
                withdraw.setAccountName(rs.getString("account_name"));
                withdraw.setPrice(rs.getFloat("price"));
                withdraw.setStatusTransaction(rs.getInt("status_transaction"));
                withdraw.setCreatedDate(rs.getDate("created_date"));
                withdraw.setUpdatedDate(rs.getDate("updated_date"));
                withdraws.add(withdraw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return withdraws;
    }
    
    public List<WithdrawDTO> getWithdrawsByFilterUser(WithdrawFilter filter, Integer UserID) {
        List<WithdrawDTO> withdraws = new ArrayList<>();
        String sql = "SELECT * FROM withdraw WHERE status_transaction IN (0, 1, 2) and userid = ? AND (bank_name LIKE ? OR bank_branch LIKE ? OR account_number LIKE ? OR account_name LIKE ?) \n";
        if (filter.getStatus() != null) {
            sql += " AND status_transaction = ? \n";
        }
        sql += " Order by id desc  ";
        try {
            int indexParam = 1;
            ps = connection.prepareStatement(sql);
            ps.setInt(indexParam++, UserID);
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            if (filter.getStatus() != null) {
                ps.setInt(indexParam++, filter.getStatus());
            }
            System.out.println("" + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                WithdrawDTO withdraw = new WithdrawDTO();
                withdraw.setId(rs.getInt("id"));
                withdraw.setUserId(rs.getInt("userid"));
                withdraw.setBankName(rs.getString("bank_name"));
                withdraw.setBankBranch(rs.getString("bank_branch"));
                withdraw.setAccountNumber(rs.getString("account_number"));
                withdraw.setAccountName(rs.getString("account_name"));
                withdraw.setPrice(rs.getFloat("price"));
                withdraw.setStatusTransaction(rs.getInt("status_transaction"));
                withdraw.setCreatedDate(rs.getDate("created_date"));
                withdraw.setUpdatedDate(rs.getDate("updated_date"));
                withdraws.add(withdraw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return withdraws;
    }
    
    public List<WithdrawDTO> getWithdrawsByFilterUser2(WithdrawFilter filter, Integer UserID) {
        List<WithdrawDTO> withdraws = new ArrayList<>();
        String sql = "SELECT * FROM withdraw WHERE status_transaction =4 and userid = ? AND (bank_name LIKE ? OR bank_branch LIKE ? OR account_number LIKE ? OR account_name LIKE ?) \n";
        if (filter.getStatus() != null) {
            sql += " AND status_transaction = ? \n";
        }
        sql += " Order by id desc  ";
        try {
            int indexParam = 1;
            ps = connection.prepareStatement(sql);
            ps.setInt(indexParam++, UserID);
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            if (filter.getStatus() != null) {
                ps.setInt(indexParam++, filter.getStatus());
            }
            System.out.println("" + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                WithdrawDTO withdraw = new WithdrawDTO();
                withdraw.setId(rs.getInt("id"));
                withdraw.setUserId(rs.getInt("userid"));
                withdraw.setBankName(rs.getString("bank_name"));
                withdraw.setBankBranch(rs.getString("bank_branch"));
                withdraw.setAccountNumber(rs.getString("account_number"));
                withdraw.setAccountName(rs.getString("account_name"));
                withdraw.setPrice(rs.getFloat("price"));
                withdraw.setStatusTransaction(rs.getInt("status_transaction"));
                withdraw.setCreatedDate(rs.getDate("created_date"));
                withdraw.setUpdatedDate(rs.getDate("updated_date"));
                withdraws.add(withdraw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return withdraws;
    }
    
    public List<WithdrawDTO> getWithdrawsByFilter(WithdrawFilter filter) {
        List<WithdrawDTO> withdraws = new ArrayList<>();
        String sql = "SELECT * FROM withdraw WHERE (bank_name LIKE ? OR bank_branch LIKE ? OR account_number LIKE ? OR account_name LIKE ?) ";
        if (filter.getStatus() != null) {
            sql += "AND status_transaction = ? \n";
        }
        sql += " Order by id desc \n ";
        sql += " LIMIT ? OFFSET ? ";
        try {
            int indexParam = 1;
            ps = connection.prepareStatement(sql);
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            ps.setString(indexParam++, "%" + filter.getSearchValue() + "%");
            if (filter.getStatus() != null) {
                ps.setInt(indexParam++, filter.getStatus());
            }
            ps.setInt(indexParam++, filter.getPageSize());
            ps.setInt(indexParam++, filter.getOffset());
            
            rs = ps.executeQuery();
            while (rs.next()) {
                WithdrawDTO withdraw = new WithdrawDTO();
                withdraw.setId(rs.getInt("id"));
                withdraw.setUserId(rs.getInt("userid"));
                withdraw.setBankName(rs.getString("bank_name"));
                withdraw.setBankBranch(rs.getString("bank_branch"));
                withdraw.setAccountNumber(rs.getString("account_number"));
                withdraw.setAccountName(rs.getString("account_name"));
                withdraw.setPrice(rs.getFloat("price"));
                withdraw.setStatusTransaction(rs.getInt("status_transaction"));
                withdraw.setCreatedDate(rs.getDate("created_date"));
                withdraw.setUpdatedDate(rs.getDate("updated_date"));
                withdraws.add(withdraw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return withdraws;
    }

    public boolean updateWithdrawStatusById(int withdrawId, int status) {
        boolean success = false;
        String sql = "UPDATE withdraw SET status_transaction = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, withdrawId);
            int rowsAffected = ps.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public WithdrawDTO getLastWithdraw() {
        WithdrawDTO withdraw = null;
        String sql = "SELECT * FROM withdraw ORDER BY created_date DESC LIMIT 1";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                withdraw = new WithdrawDTO();
                withdraw.setId(rs.getInt("id"));
                withdraw.setUserId(rs.getInt("userid"));
                withdraw.setBankName(rs.getString("bank_name"));
                withdraw.setBankBranch(rs.getString("bank_branch"));
                withdraw.setAccountNumber(rs.getString("account_number"));
                withdraw.setAccountName(rs.getString("account_name"));
                withdraw.setPrice(rs.getFloat("price"));
                withdraw.setStatusTransaction(rs.getInt("status_transaction"));
                withdraw.setCreatedDate(rs.getDate("created_date"));
                withdraw.setUpdatedDate(rs.getDate("updated_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return withdraw;
    }
    
    public List<WithdrawDTO> getWithdrawById(int id) {
        WithdrawDTO withdraw = null;
        List<WithdrawDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM withdraw where id =?";
        try {
            
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                withdraw = new WithdrawDTO();
                withdraw.setId(rs.getInt("id"));
                withdraw.setUserId(rs.getInt("userid"));
                withdraw.setBankName(rs.getString("bank_name"));
                withdraw.setBankBranch(rs.getString("bank_branch"));
                withdraw.setAccountNumber(rs.getString("account_number"));
                withdraw.setAccountName(rs.getString("account_name"));
                withdraw.setPrice(rs.getFloat("price"));
                withdraw.setStatusTransaction(rs.getInt("status_transaction"));
                withdraw.setCreatedDate(rs.getDate("created_date"));
                withdraw.setUpdatedDate(rs.getDate("updated_date"));
                list.add(withdraw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Integer countTotalWithDraw() {
        String sql = "SELECT count(*) as total FROM withdraw";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
