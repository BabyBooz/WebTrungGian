<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đơn bán của tôi</title>
        <style>
            /* Reset CSS */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }

            .container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }

            .title {
                text-align: center;
                margin-bottom: 20px;
            }

            .info-table {
                width: 100%;
                border-collapse: collapse;
            }

            .info-table tr {
                border-bottom: 1px solid #ccc;
            }

            .info-table td {
                padding: 10px 0;
            }

            .label {
                font-weight: bold;
                width: 150px;
            }

            .value {
                padding-left: 20px;
            }

            /* Hover effect */
            .info-table tr:hover {
                background-color: #f0f0f0;
            }
            body{
                background-color: #ccc;
            }
            .container{
                background-color: #ffffff;
            }
            .mua {
                display: block; /* Hiển thị dạng block để căn giữa */
                margin: 0 auto; /* Căn giữa theo chiều ngang */
                padding: 10px 20px; /* Đặt khoảng cách xung quanh nút */
                font-size: 16px; /* Đặt kích thước chữ */
                background-color: #343a40; /* Màu nền */
                color: #fff; /* Màu chữ */
                border: none; /* Loại bỏ viền */
                border-radius: 5px; /* Bo góc */
                cursor: pointer; /* Con trỏ chuột khi di chuột qua */
                margin-top: 15px;
            }

            .mua:hover {
                background-color: #0056b3; /* Màu nền khi di chuột qua */
            }
            .title {
                text-align: center;
                margin-bottom: 20px;
                position: relative;
            }

            .title a {
                position: absolute;
                top: 50%;
                left: 10px;
                transform: translateY(-50%);
                color: #007bff;
                text-decoration: none;
            }
            input, select {
                width: calc(100% - 10px); /* Độ rộng của input sẽ bằng 100% trừ đi khoảng cách giữa các ô input */
                padding: 8px; /* Khoảng cách nội dung bên trong input */
                border: 1px solid #ccc; /* Viền input */
                border-radius: 4px; /* Bo tròn các góc của input */
                font-size: 16px; /* Kích thước chữ */
                margin-bottom: 10px; /* Khoảng cách dưới cùng của input */
            }
            textarea {
                width: calc(100% - 10px); /* Độ rộng của input sẽ bằng 100% trừ đi khoảng cách giữa các ô input */
                padding: 8px; /* Khoảng cách nội dung bên trong input */
                border: 1px solid #ccc; /* Viền input */
                border-radius: 4px; /* Bo tròn các góc của input */
                font-size: 16px; /* Kích thước chữ */
                margin-bottom: 10px; /* Khoảng cách dưới cùng của input */
            }
        </style>
    </head>
    <body>
        <%
String errorMessage = (String) session.getAttribute("errorMessage");
if (errorMessage != null) {
        %>
        <div class="alert alert-failed" role="alert">
            <%= errorMessage %>
        </div>
        <%
            // Xóa thông điệp từ session sau khi đã hiển thị
            session.removeAttribute("errorMessage");
        }
        %>
        <div class="container">
            <h2 class="title"><a href="javascript:history.go(-1)" style="color: #0056b3">&lt; Back</a> Chi tiết giao dịch</h2>
            <form action="product-details" method="post">
                <table class="info-table">
                    <tr>
                        <td class="label">Mã giao dịch:</td>
                        <td><input disabled="true" type="number" name="id" value="${order.id}"></td>
                    </tr>
                    <input type="hidden" name="orderId" value="${order.id}">
                    <input type="hidden" name="productId" value="${order.product.id}">
                    <tr>
                        <td class="label">Người bán:</td>
                        <td><input disabled="true" type="text" name="seller" value="${order.sellerName}"></td>
                    </tr>        
                    <tr>
                        <td class="label">Trạng thái:</td>
                        <td scope="col" >
                            <c:choose>
                                <c:when test="${order.status == 0}">
                                    <input disabled="true" type="text" name="status" value="Sẵn Sàng Giao Dịch">
                                </c:when>
                                <c:when test="${order.status == 1}">
                                    <input disabled="true" type="text" name="status" value="Bên Mua Xem Hàng">
                                </c:when>
                                <c:when test="${order.status == 2}">
                                    <input disabled="true" type="text" name="status" value="Hoàn Thành">
                                </c:when>
                                <c:when test="${order.status == 3}">
                                    <input disabled="true" type="text" name="status" value="Khiếu Nại">
                                </c:when>
                                <c:when test="${order.status == 5}">
                                    <input disabled="true" type="text" name="status" value="Khiếu Nại Thành Công ">
                                </c:when>
                                <c:otherwise>
                                    <input disabled="true" type="text" name="status" value="Khiếu Nại Thất Bại">
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>    
                    <tr>
                        <td class="label">Chủ đề:</td>
                        <td><input type="text" name="title" required="" value="${order.product.name}" ${order.status > 0 ? 'disabled' : ''}></td>
                    </tr>
                    <tr>
                        <td class="label">Giá:</td>
                        <td><input type="number" name="price" required="" value="${order.price}" ${order.status > 0 ? 'disabled' : ''}></td>
                    </tr>
                    <tr>
                        <td class="label">Phí giao dịch:</td>
                        <td><input disabled="true" type="number" name="transFee" required="" value="${order.price*(1/10)}"></td>
                    </tr>
                    <tr>
                        <td class="label">Người chịu phí:</td>
                        <td>                    
                            <select name="fee" required="" ${order.status > 0 ? 'disabled' : ''}>
                                <option value="0" ${order.product.status_fee == 0 ? 'selected' : ''}>Người mua</option>
                                <option value="1" ${order.product.status_fee == 1 ? 'selected' : ''}>Người bán</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Mô tả</td>
                        <td><textarea name="description" required="" ${order.status > 0 ? 'disabled' : ''}>${order.description}</textarea></td>
                    </tr>
                    <tr>
                        <td class="label">Phương thức liên lạc:</td>
                        <td><input type="text" name="contact" required="" value="${order.contract}" ${order.status > 0 ? 'disabled' : ''}></td>
                    </tr>
                    <tr>
                        <td class="label">Nội dung ẩn:</td>
                        <td><textarea name="hiddenContent" required="" ${order.status > 0 ? 'disabled' : ''}>${order.hiddenContent}</textarea></td>
                    </tr>
                    <tr>
                        <td class="label">Công khai:</td>
                        <td>                        
                            <select name="public" required="" ${order.status > 0 ? 'disabled' : ''}>
                                <option value="1" ${order.product.status_product == 1 ? 'selected' : ''}>Công khai</option>
                                <option value="0" ${order.product.status_product == 0 ? 'selected' : ''}>Riêng tư</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Ngày tạo:</td>
                        <td><input disabled="true" type="text" name="createdDate" required="" value="${order.createdDate}"></td>
                    </tr>
                    <tr>
                        <td class="label">Ngày cập nhật:</td>
                        <td><input disabled="true" type="text" name="updatedDate" required="" value="${order.updatedDate}"></td>
                    </tr>
                    <tr>
                        <td class="label">Share link</td>
                        <td><a href="detail?id=${order.product.id}">http://localhost:9999/swp/detail?id=${order.product.id} </a></td>
                    </tr>
                </table> 
                <input class="mua" type="submit" value="Update" style="display:${order.status > 0 ? 'none' : 'block'}">
            </form>
        </div>
    </body>
</html>