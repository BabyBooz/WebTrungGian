package dao;

import entity.Account;
import entity.MyCart;
import entity.OrderHistory;
import entity.Product;
import entity.Order1;
import entity.Product1;
import entity.StatusHistory;
import entity.khanh.WithdrawDTO;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import paging.filter.PublicMarketFitler;

public class DAO extends MyDAO {

    public Account login(String user, String pass) {
        String query = "select * from Account where user = ? and password = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9),
                        rs.getInt(10),
                        rs.getFloat(11)
                );
            }
        } catch (Exception e) { //Code thi bat log lai gium
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean register(Account newAccount) {
        String query = "INSERT INTO Account(user, password, email, role, status, token, expiration_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, newAccount.getUser());
            ps.setString(2, newAccount.getPassword());
            ps.setString(3, newAccount.getEmail());
            ps.setInt(4, newAccount.getRole());
            ps.setInt(5, newAccount.getStatus());
            ps.setString(6, newAccount.getToken());
            ps.setTimestamp(7, newAccount.getExpiration_time());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
            return false;
        }
        return true;
    }

    // Phương thức kiểm tra username đã tồn tại hay chưa
    public boolean isUsernameTaken(String username) {
        String query = "SELECT * FROM Account WHERE user = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
        }
        return false;
    }

    // Phương thức kiểm tra username đã tồn tại hay chưa
    public boolean isEmailTaken(String email) {
        String query = "SELECT * FROM Account WHERE email = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
        }
        return false;
    }

// Phương thức kiểm tra username đã tồn tại hay chưa
    public Account getAccountByUser(String user) {
        String query = "SELECT * FROM Account WHERE user = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9),
                        rs.getInt(10));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
        }
        return null;
    }

    public boolean changeAccountStatus(int id, int status) {
        String query = "UPDATE account SET status = ? WHERE id=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(2, id);
            ps.setInt(1, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
            return false;
        }
        return true;
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

    public void updateAccount(String user, String email, int phone, String describes, int id) {
        String query = "UPDATE `Account` SET `user` = ?\n"
                + ", `email` = ?\n"
                + ", `phone` = ?\n"
                + ", `describes` = ?\n"
                + "WHERE `id` = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, email);
            ps.setInt(3, phone);
            ps.setString(4, describes);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Account checkAccount(String user) {
        String query = "select * from Account where [user] =?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9),
                        rs.getInt(10));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void changePass(String password, int id) {
        String query = "UPDATE `Account` SET `password` = ? where id =?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Account getAccountID(int id) {
        xSql = "select * from account where id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9),
                        rs.getInt(10),
                        rs.getFloat(11)
                );
            }
        } catch (Exception e) {
            System.out.println("asd");
        }
        return null;
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        xSql = "select * from Account";
        try {
            ps = connection.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9),
                        rs.getInt(10)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductMarket() {
        List<Product> list = new ArrayList<>();

        xSql = "select * from product";
        try {
            ps = connection.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductMarketByStatus(int status_product) {
        List<Product> list = new ArrayList<>();
        xSql = "select * from product where status_product = 1";
        try {
            ps = connection.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductMarketById(int id) {
        List<Product> list = new ArrayList<>();
        xSql = "select * from product where id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductMarket(int id) {
        xSql = "select * from product where id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getProductMarketByName(String name) {
        List<Product> list = new ArrayList<>();
        xSql = "select * from product where `name` like ? and where status_product =1";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductMarketByContact(String contact) {
        List<Product> list = new ArrayList<>();
        xSql = "select * from product where contact =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setString(1, contact);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductMarketByPrice(float min, float max) {
        List<Product> list = new ArrayList<>();
        xSql = "select * from product where price >= ? and price <= ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setFloat(1, min);
            ps.setFloat(2, max);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductMarketByFee(int status_fee) {
        List<Product> list = new ArrayList<>();
        try {
            if (status_fee == 2) {
                xSql = "select * from product where status_fee = 1 or status_fee = 0 and status_product =0";
                ps = connection.prepareStatement(xSql);
            } else {
                xSql = "select * from product where status_fee = ?";
                ps = connection.prepareStatement(xSql);
                ps.setInt(1, status_fee);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductMarketByUser(String user) {
        List<Product> list = new ArrayList<>();
        xSql = "SELECT \n"
                + "    p.id AS product_id,\n"
                + "    p.`name`, \n"
                + "    p.contact, \n"
                + "    p.price, \n"
                + "    a.user AS account_user,\n"
                + "    p.`created_date`, \n"
                + "    p.`updated_date` \n"
                + "FROM \n"
                + "    product p\n"
                + "JOIN \n"
                + "    Account a ON p.created_by = a.id and a.`user` like '%?%';";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertOrder(OrderHistory order) {
        xSql = "INSERT INTO `order_history` (`status`, `seller_id`, `buyer_id`, `product_id`, `price`, `description`, `contact`, `hidden_content`)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, order.getStatus());
            ps.setInt(2, order.getSeller_id());
            ps.setInt(3, order.getBuyer_id());
            ps.setInt(4, order.getProduct_id());
            ps.setFloat(5, order.getPrice());
            ps.setString(6, order.getDescription());
            ps.setString(7, order.getContact());
            ps.setString(8, order.getHidden_content());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        xSql = "delete from product where id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<OrderHistory> getOrder(int id) {
        List<OrderHistory> list = new ArrayList<>();
        xSql = "select * from `order_history` where buyer_id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderHistory(
                        rs.getInt("status"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateWallet(float wallet, int userid) {
        xSql = "UPDATE `account`\n"
                + "SET `wallet` = ?\n"
                + "WHERE `id` = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setFloat(1, wallet);
            ps.setInt(2, userid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Boolean updateStatusProduct(int productId, int statusProduct) {
        xSql = "UPDATE product\n"
                + "SET \n"
                + "    status_product = ?\n"
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, statusProduct);
            ps.setInt(2, productId);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateProduct(Product p, int productId) {
        xSql = "UPDATE product\n"
                + "SET \n"
                + "    name = ?,\n"
                + "    price = ?,\n"
                + "    status_fee = ?,\n"
                + "    description = ?,\n"
                + "    hidden_content =?,\n"
                + "    contact = ?,\n"
                + "    status_product =?\n"
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setString(1, p.getName());
            ps.setFloat(2, p.getPrice());
            ps.setInt(3, p.getStatus_fee());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getHidden_content());
            ps.setString(6, p.getContact());
            ps.setInt(7, p.getStatus_product());
            ps.setInt(8, productId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getProductPage() {
        xSql = "SELECT count(*) FROM product WHERE status_product = 1 and status_checking =0 LIMIT 1000; ";
        try {
            ps = connection.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getMyCartPage(int buyer_id) {
        xSql = "SELECT count(*) FROM myCart WHERE buyer_id = ? LIMIT 1000";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, buyer_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Product> getProductMarketPage(int index) {
        List<Product> list = new ArrayList<>();
        xSql = "SELECT * FROM product where status_product =1 and status_checking =0 ORDER BY id LIMIT 5 OFFSET ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Integer countTotalProductMarket() {
        xSql = "SELECT count(*) as total FROM product";
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

    public List<Product> getProductMarketByFilter(PublicMarketFitler publicMarketFitler) {
        List<Product> list = new ArrayList<>();
        xSql = "select * from product p \n"
                + "left join account cb on p.created_by = cb.id\n"
                + "where (p.name like ? or cb.user like ?) \n";
        if (publicMarketFitler.getStatus() != null) {
            xSql += "and status_product = ?\n";
        }
        xSql += "ORDER BY p.id\n"
                + "LIMIT ? OFFSET ? ;";
        try {
            ps = connection.prepareStatement(xSql);
            Integer paramIndex = 1;
            ps.setString(paramIndex++, "%" + publicMarketFitler.getSearchValue() + "%");
            ps.setString(paramIndex++, "%" + publicMarketFitler.getSearchValue() + "%");
            if (publicMarketFitler.getStatus() != null) {
                ps.setInt(paramIndex++, publicMarketFitler.getStatus());
            }
            ps.setInt(paramIndex++, publicMarketFitler.getPageSize());
            ps.setInt(paramIndex++, publicMarketFitler.getOffset());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int addProduct(Product1 product) {
        System.out.println(product.toString());
        String query = "INSERT INTO product (created_by, name, image, price, status_fee, description, contact, hidden_content, status_product, status_checking) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, product.getCreated_by());
            ps.setString(2, product.getName());
            ps.setString(3, product.getImage());
            ps.setFloat(4, product.getPrice());
            ps.setInt(5, product.getStatus_fee());
            ps.setString(6, product.getDescription());
            ps.setString(7, product.getContact());
            ps.setString(8, product.getHidden_content());
            ps.setInt(9, product.getStatus_product());
            ps.setInt(10, 0);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int productId = generatedKeys.getInt(1); // Lấy ID từ kết quả trả về
                return productId;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
            return 0;
        }
        return 0;
    }

    public boolean addOrder(Order1 order) {
        String query = "INSERT INTO order_history (status, seller_id, buyer_id, product_id, price, description, contact, hidden_content) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, order.getStatus());
            ps.setInt(2, order.getSellerId());
            ps.setInt(3, order.getBuyerId());
            ps.setInt(4, order.getProductId());
            ps.setFloat(5, order.getPrice());
            ps.setString(6, order.getDescription());
            ps.setString(7, order.getContract());
            ps.setString(8, order.getHiddenContent());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng logger để ghi log
            return false;
        }
        return true;
    }

    public List<Order1> listOrder(int limit, int offset, String orderBy, boolean ascending, Map<String, Object> filters) {
        List<Order1> orders = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT o.id, o.status, o.seller_id, o.buyer_id, o.product_id, ");
        queryBuilder.append("o.price, o.description, o.contact, o.hidden_content, ");
        queryBuilder.append("o.created_date, o.updated_date, ");
        queryBuilder.append("seller.user AS seller_name, buyer.user AS buyer_name, ");
        queryBuilder.append("p.name AS product_name ");
        queryBuilder.append("FROM `order_history` o ");
        queryBuilder.append("LEFT OUTER JOIN account seller ON o.seller_id = seller.id ");
        queryBuilder.append("LEFT OUTER JOIN account buyer ON o.buyer_id = buyer.id ");
        queryBuilder.append("LEFT OUTER JOIN product p ON o.product_id = p.id ");
        // Build WHERE clause based on filters
        if (filters != null && !filters.isEmpty()) {
            queryBuilder.append(" WHERE ");
            int count = 0;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                if (count > 0) {
                    queryBuilder.append(" AND ");
                }
                queryBuilder.append(entry.getKey()).append(" LIKE ?");
                count++;
            }
        }

        // Add ORDER BY clause
        if (orderBy != null && !orderBy.isEmpty()) {
            queryBuilder.append(" ORDER BY ").append(orderBy);
            if (!ascending) {
                queryBuilder.append(" DESC");
            }
        }

        // Add LIMIT and OFFSET clauses
        queryBuilder.append(" LIMIT ? OFFSET ?");

        String query = queryBuilder.toString();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Set filter parameters
            int parameterIndex = 1;
            if (filters != null && !filters.isEmpty()) {
                for (Object value : filters.values()) {
                    ps.setObject(parameterIndex++, value);
                }
            }

            // Set LIMIT and OFFSET parameters
            ps.setInt(parameterIndex++, limit);
            ps.setInt(parameterIndex, offset);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order1 order = extractOrderFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        return orders;
    }

    private Order1 extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order1 order = new Order1();
        order.setId(rs.getInt("id"));
        order.setStatus(rs.getInt("status"));
        order.setSellerId(rs.getInt("seller_id"));
        order.setBuyerId(rs.getInt("buyer_id"));
        order.setProductId(rs.getInt("product_id"));
        order.setPrice(rs.getFloat("price"));
        order.setDescription(rs.getString("description"));
        order.setContract(rs.getString("contact"));
        order.setHiddenContent(rs.getString("hidden_content"));
        order.setCreatedDate(rs.getDate("created_date"));
        order.setUpdatedDate(rs.getDate("updated_date"));
        order.setSellerName(rs.getString("seller_name"));
        order.setBuyerName(rs.getString("buyer_name"));
        order.setProductName(rs.getString("product_name"));
        // Assuming Account and Product details are fetched separately
        // You may need to modify this part according to your application's architecture
        return order;
    }

    public Order1 getOrderById(int id) {
        Order1 order = new Order1();
        xSql = "SELECT o.id, o.status, o.seller_id, o.buyer_id, o.product_id, "
                + "o.price, o.description, o.contact, o.hidden_content, "
                + "o.created_date, o.updated_date,"
                + "seller.user AS seller_name, buyer.user AS buyer_name "
                + "FROM `order_history` o "
                + "LEFT OUTER JOIN account seller ON o.seller_id = seller.id "
                + "LEFT OUTER JOIN account buyer ON o.buyer_id = buyer.id  "
                + "WHERE o.id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                order.setId(rs.getInt("id"));
                order.setStatus(rs.getInt("status"));
                order.setSellerId(rs.getInt("seller_id"));
                order.setBuyerId(rs.getInt("buyer_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setPrice(rs.getFloat("price"));
                order.setDescription(rs.getString("description"));
                order.setContract(rs.getString("contact"));
                order.setHiddenContent(rs.getString("hidden_content"));
                order.setCreatedDate(rs.getDate("created_date"));
                order.setUpdatedDate(rs.getDate("updated_date"));
                order.setSellerName(rs.getString("seller_name"));
                order.setBuyerName(rs.getString("buyer_name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return order;
    }

    public Product1 getProductById(int id) {
        Product1 product = new Product1();
        xSql = "select * from product where id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setCreated_by(rs.getInt("created_by"));
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getFloat("price"));
                product.setStatus_fee(rs.getInt("status_fee"));
                product.setDescription(rs.getString("description"));
                product.setContact(rs.getString("contact"));
                product.setHidden_content(rs.getString("hidden_content"));
                product.setStatus_product(rs.getInt("status_product"));
                product.setCreated_date(rs.getDate("created_date"));
                product.setUpdated_date(rs.getDate("updated_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return product;
    }

    public boolean updateOrder(Order1 order) {
        String query = "UPDATE `order_history` SET price=?, description=?, contact=?, hidden_content=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setFloat(1, order.getPrice());
            ps.setString(2, order.getDescription());
            ps.setString(3, order.getContract());
            ps.setString(4, order.getHiddenContent());
            ps.setInt(5, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e); // Hoặc sử dụng logger để ghi log
            return false;
        }
        return true;
    }

    public boolean updateProduct(Product1 product) {
        String query = "UPDATE `product` SET name=?, price=?, status_fee=?, description=?, contact=?, hidden_content=?, status_product=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setInt(3, product.getStatus_fee());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getContact());
            ps.setString(6, product.getHidden_content());
            ps.setInt(7, product.getStatus_product());
            ps.setInt(8, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e); // Hoặc sử dụng logger để ghi log
            return false;
        }
        return true;
    }

    public OrderHistory getOrderId2(int productId) {
        xSql = "select * from order_history where product_id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new OrderHistory(rs.getInt("id"),
                        rs.getInt("status"),
                        rs.getInt("seller_id"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateStatus1Order(int id) {
        xSql = "UPDATE order_history SET status =  1 WHERE id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertMyCart(MyCart myCart) {
        xSql = "insert into myCart (`status_fee`,`status_checking`, `seller_id`, `buyer_id`, `product_id`, `price`, `description`, `contact`, `hidden_content`, `order_history_id`)\n"
                + "value(?, ?, ?, ?, ?, ?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, myCart.getStatus_fee());
            ps.setInt(2, myCart.getStatus_checking());
            ps.setInt(3, myCart.getSeller_id());
            ps.setInt(4, myCart.getBuyer_id());
            ps.setInt(5, myCart.getProduct_id());
            ps.setFloat(6, myCart.getPrice());
            ps.setString(7, myCart.getDescription());
            ps.setString(8, myCart.getContact());
            ps.setString(9, myCart.getHidden_content());
            ps.setInt(10, myCart.getOrder_history_id());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStatusHistory(StatusHistory statusHistory) {
        xSql = "insert into statusHistory (`statusChecking`, `createdBy`,`productId`)\n"
                + "value(?, ?,?)";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, statusHistory.getStatusChecking());
            ps.setInt(2, statusHistory.getCreatedBy());
            ps.setInt(3, statusHistory.getProductId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStatusProduct(int id) {
        xSql = "UPDATE product SET status_checking =  1 WHERE id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateStatusMyCartTo4(int id) {
        xSql = "UPDATE mycart SET status_checking = 4 WHERE id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void updateStatusMyCartTo5(int id) {
        xSql = "UPDATE mycart SET status_checking = 5 WHERE id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    

    public List<MyCart> getMyOrderPage(int index, int buyer_id) {
        List<MyCart> list = new ArrayList<>();
        xSql = "SELECT * FROM myCart where buyer_id =? ORDER BY id LIMIT 5 OFFSET ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, buyer_id);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MyCart(rs.getInt("id"),
                        rs.getInt("status_fee"),
                        rs.getInt("status_checking"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<MyCart> getMyCartsById(int id) { //Get by ID ma tra ve 1 list ?
        List<MyCart> list = new ArrayList<>();
        xSql = "select * from myCart where id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MyCart(rs.getInt("id"),
                        rs.getInt("status_fee"),
                        rs.getInt("status_checking"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public MyCart getMyCartById(int id) {
        xSql = "select * from myCart where id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                MyCart mycart = new MyCart(rs.getInt("id"),
                        rs.getInt("status_fee"),
                        rs.getInt("status_checking"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date"));
                mycart.setOrder_history_id(rs.getInt("order_history_id"));
                return mycart;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public MyCart getMyCartByOrderHistoryId(int id) {
        xSql = "select * from myCart where order_history_id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                MyCart mycart = new MyCart(rs.getInt("id"),
                        rs.getInt("status_fee"),
                        rs.getInt("status_checking"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date"));
                mycart.setOrder_history_id(rs.getInt("order_history_id"));
                return mycart;
            }
        } catch (Exception e) {
        }
        return null;
    }
    

    // updateStatusChecking.java
    public void updateStatusChekingOrder(int productId) {
        xSql = "UPDATE order_history SET status =  2 WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateStatusChekingCart(int productId) {
        xSql = "UPDATE myCart SET status_checking = 2 where product_id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateStatusChekingCart2(int productId) {
        xSql = "UPDATE myCart SET status_checking = 3 where product_id =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertStatusHistory2(StatusHistory statusHistory) {
        xSql = "insert into statusHistory (`statusChecking`, `createdBy`,`productId`)\n"
                + "value(?, ?,?)";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, statusHistory.getStatusChecking());
            ps.setInt(2, statusHistory.getCreatedBy());
            ps.setInt(3, statusHistory.getProductId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // updateStatusChecking.java

    public int getMySaleOrderPage(int sellerId) {
        xSql = "SELECT count(*) FROM order_history WHERE seller_id = ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, sellerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    ////////////////////
    public Product getProductByID(int id) {
        xSql = "select * from product where id =? limit 1000";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public StatusHistory getDetailStatusByID(int id) {
        xSql = "select * from statushistory where id =? limit 1000;";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new StatusHistory(rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getDate("created_date"),
                        rs.getInt("statusChecking"),
                        rs.getInt("createdBy"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public void updateProduct(String name, Float price, int status_fee, String description, String contact, String hidden_conten, int status_product, int id) {
        String query = "UPDATE Product SET name = ? "
                + ", price = ? "
                + ", status_fee = ? "
                + ", description = ? "
                + ", contact = ? "
                + ", hidden_content = ? "
                + ", status_product = ? "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.setInt(3, status_fee);
            ps.setString(4, description);
            ps.setString(5, contact);
            ps.setString(6, hidden_conten);
            ps.setInt(7, status_product);
            ps.setInt(8, id);

            System.out.println("DEBUG: " + ps.toString());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<StatusHistory> getStatusHistoryByID(int id) {
        List<StatusHistory> list = new ArrayList<>();
        xSql = "select * from statushistory where productId =? limit 1000;";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new StatusHistory(rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getDate("created_date"),
                        rs.getInt("statusChecking"),
                        rs.getInt("createdBy")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    }
     
    public List<OrderHistory> getOrderHistoryPage(int index, int idSeller) {
        List<OrderHistory> list = new ArrayList<>();
        xSql = "select * from order_history where seller_id =? ORDER BY id LIMIT 5 OFFSET ?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, idSeller);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderHistory(
                        rs.getInt("id"),
                        rs.getInt("status"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void insertWithDraw(WithdrawDTO withdraw) {
        xSql = "INSERT INTO `ite2`.`withdraw` (`userid`, `bank_name`, `account_name`, `price`, `status_transaction`) VALUES (?,?,?,?,?);";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, withdraw.getUserId());
            ps.setString(2, withdraw.getBankName());
            ps.setString(3, withdraw.getAccountName());
            ps.setFloat(4, withdraw.getPrice());
            ps.setInt(5, withdraw.getStatusTransaction());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 public static void main(String[] args) {
    DAO dao = new DAO();
    List<OrderHistory> list = dao.getOrderHistoryPage(1, 1);
     for (OrderHistory orderHistory : list) {
         System.out.println(orderHistory);
     }
     
    
}

}
