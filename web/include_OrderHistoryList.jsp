<%-- 
    Document   : include_myCartList
    Created on : Mar 7, 2024, 12:04:23 PM
    Author     : linhnghiem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${requestScope.orders}" var="o">
        <tr style="background-color: #F8F9FA;">
            <th scope="col" class="detail">
                <a href="product-details?id=${o.id}">Chi tiết</a>
            </th>
            <td scope="col" >
                <c:choose>
                    <c:when test="${o.status == 0}">
                        Sẵn Sàng Giao Dịch
                    </c:when>
                    <c:when test="${o.status == 1}">
                        Bên Mua Xem Hàng
                    </c:when>
                    <c:when test="${o.status == 2}">
                        Hoàn Thành
                    </c:when>
                    <c:when test="${o.status == 3}">
                        Khiếu Nại
                    </c:when>
                    <c:when test="${o.status == 4}">
                        Khiếu Nại Thành Công 
                    </c:when>
                    <c:otherwise>
                        Khiếu Nại Thất Bại
                    </c:otherwise>
                </c:choose>
            </td>
            <td scope="col">
                <c:forEach items="${requestScope.accountList}" var="a">
                    <c:if test="${o.buyer_id == a.id}">
                        ${a.user}
                    </c:if>
                </c:forEach>
            </td>
            <td scope="col">
                <c:forEach items="${requestScope.product}" var="p">
                    <c:if test="${p.id == o.product_id}">
                        ${p.name}
                    </c:if>
                </c:forEach>
            </td>
            <td scope="col">
                ${o.contact}
            </td>
            <td scope="col">
                <c:forEach items="${requestScope.product}" var="p">
                    <c:if test="${p.id == o.product_id}">
                        ${p.status_product == 0 ? "ẩn" : "hiện"}
                    </c:if>
                </c:forEach>
            </td>
            <td scope="col" >
                <c:forEach items="${requestScope.product}" var="p">
                    <c:if test="${p.id == o.product_id}">
                        ${p.status_fee == 0 ? "Bên Mua" : "Bên Bán"}
                    </c:if>
                </c:forEach>
            </td>
            <c:set var="formattedPrice2" value="${String.format('%.2f', o.price*(1/10))}" />

            <td scope="col" >
                ${o.price}
            </td>
            <td scope="col" >
                ${formattedPrice2}
            </td>
            <td scope="col" >
                ${o.created_date}
            </td>
            <td scope="col">
                ${o.updated_date}
            </td>
            <td scope="col">${o.id}</td>
            <th scope="col" class="detail">
                <c:forEach items="${requestScope.product}" var="p">
                    <c:if test="${p.id == o.product_id}">
                        <a href="history?id=${p.id}">Trạng Thái</a>
                    </c:if>
                </c:forEach>
            </th>
        </tr>
    </c:forEach>
</body>
</html>
