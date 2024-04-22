package dao;

import paging.filter.AccountFilter;
import entity.Account;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends MyDAO implements Serializable {

    public Account getByEmail(String email) {
        String query = "select * from Account where email = ?";
        try {
            ps = connection.prepareStatement(query); // tao query transaction
            ps.setString(1, email); // gan email vao dau ? thu nhat
            rs = ps.executeQuery(); //thuc thi query
            while (rs.next()) {
                Account account = new entity.Account(rs.getInt("id"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("phone"),
                        rs.getString("describes"));
                account.setStatus(rs.getInt("status"));
                return account;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account updateAccountPassword(entity.Account account) {
        String query = "update Account set password = ? where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, account.getPassword());
            ps.setInt(2, account.getId());
            if (ps.executeUpdate() > 0) {
                return getByEmail(account.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getById(Integer accountId) {
        String query = "select * from Account where id = ?";
        try {
            ps = connection.prepareStatement(query); // tao query transaction
            ps.setInt(1, accountId); // gan email vao dau ? thu nhat
            rs = ps.executeQuery(); //thuc thi query
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUser(rs.getString("user"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getInt("phone"));
                account.setDescribes(rs.getString("describes"));
                account.setToken(rs.getString("token"));
                account.setStatus(rs.getInt("status"));
                account.setWallet(rs.getFloat("wallet"));
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteUserToken(int id) {
        String query = "UPDATE account SET token = null WHERE id=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
            return false;
        }
        return true;
    }

    public Account updateUserToken(Account account) {
        String query = "UPDATE account SET token = ? WHERE id= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, account.getToken());
            ps.setInt(2, account.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
            return null;
        }
        return account;
    }

    public Integer countTotalAccount() {
        String query = "select count(*) as total from account";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
            return 0;
        }
        return 0;
    }

    public List<Account> findAllByFilter(AccountFilter accountFilter) {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM Account \n"
                + "WHERE (email LIKE ? OR phone LIKE ? OR user LIKE ?) ";
        if (!accountFilter.getRole().isEmpty()) {
            query += "AND role = ? ";
        }
        if (accountFilter.getStatus() != null) {
            query += "AND status = ? ";
        }
        query += "ORDER BY ";
        switch (accountFilter.getSortBy()) {
            case "email":
            case "phone":
            case "user":
            case "id":
            case "role":
                query += accountFilter.getSortBy();
                break;
            default:
                query += "id"; // Default sorting by id
                break;
        }
        query += " \nLIMIT ? OFFSET ? ";
        try {
            ps = connection.prepareStatement(query);
            String searchValuePattern = "%" + accountFilter.getSearchValue() + "%";
            ps.setString(1, searchValuePattern);
            ps.setString(2, searchValuePattern);
            ps.setString(3, searchValuePattern);
            int parameterIndex = 4; // Start index for additional parameters
            if (!accountFilter.getRole().isEmpty()) {
                ps.setString(parameterIndex++, accountFilter.getRole());
            }
            if (accountFilter.getStatus() != null) {
                ps.setBoolean(parameterIndex++, accountFilter.getStatus());
            }
            ps.setInt(parameterIndex++, accountFilter.getPageSize());
            ps.setInt(parameterIndex, accountFilter.getOffset());
            rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUser(rs.getString("user"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getInt("phone"));
                account.setDescribes(rs.getString("describes"));
                account.setToken(rs.getString("token"));
                account.setStatus(rs.getInt("status"));
                account.setCreated_date(rs.getDate("created_date"));
                account.setRole(rs.getInt("role"));
                account.setWallet(rs.getFloat("wallet"));
                accounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Account updateAccount(entity.Account account) {
        String query = "update Account\n"
                + "set password = ?,\n"
                + "email = ? ,\n"
                + "phone = ?,\n"
                + "role = ?,\n"
                + "status = ? \n"
                + "where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, account.getPassword());
            ps.setString(2, account.getEmail());
            ps.setInt(3, account.getPhone());
            ps.setInt(4, account.getRole());
            ps.setInt(5, account.getStatus());
            ps.setInt(6, account.getId());
            if (ps.executeUpdate() > 0) {
                return getByEmail(account.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean updateAccountStatus(entity.Account account) {
        String query = "update Account\n"
                + "set status = ? \n"
                + "where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, account.getStatus());
            ps.setInt(2, account.getId());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAccountWallet(int accountId, float newWalletBalance) {
        boolean success = false;
        xSql = "UPDATE `account` SET wallet = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setFloat(1, newWalletBalance);
            ps.setInt(2, accountId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating account wallet: " + e.getMessage());
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

    public Account insertAccount(entity.Account account) {
        String query = "INSERT INTO `eshop_2024`.`account`\n"
                + "(`user`,\n"
                + "`password`,\n"
                + "`email`,\n"
                + "`phone`,\n"
                + "`describes`,\n"
                + "`created_date`,\n"
                + "`role`,\n"
                + "`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, account.getUser());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setInt(4, account.getPhone());
            ps.setString(5, account.getDescribes());
            ps.setDate(6, new Date(System.currentTimeMillis()));
            ps.setInt(7, account.getRole());
            ps.setInt(8, account.getStatus());
            if (ps.executeUpdate() > 0) {
                return getByEmail(account.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountById(Integer accountId) {
        String query = "select * from `account` where id = ?";
        try {
            ps = connection.prepareStatement(query); // tao query transaction
            ps.setInt(1, accountId); // gan email vao dau ? thu nhat
            rs = ps.executeQuery(); //thuc thi query
            while (rs.next()) {
                Account account = new entity.Account(rs.getInt("id"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("phone"),
                        rs.getString("describes"));
                account.setStatus(rs.getInt("status"));
                account.setWallet(rs.getFloat("wallet"));
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // Creating a sample AccountFilter instance
        AccountFilter accountFilter = new AccountFilter();
        accountFilter.setSearchValue("");
        accountFilter.setPageSize(10);
        accountFilter.setOffset(0);
        accountFilter.setRole("1");
        accountFilter.setStatus(true);
        accountFilter.setSortBy("email");

        AccountDAO accountDAO = new AccountDAO();
        // Creating a DAO instance
        // Calling findAllByFilter method and printing results
        List<Account> filteredAccounts = accountDAO.findAllByFilter(accountFilter);
        for (Account account : filteredAccounts) {
            System.out.println(account);
        }
    }
}
