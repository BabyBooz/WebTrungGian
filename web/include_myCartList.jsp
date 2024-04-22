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
        <c:forEach items="${requestScope.listO}" var="p">
        <tr >
            <th scope="col" class="detail">
                <a href="detailMyCart?id=${p.id}">Chi Tiết </a>
            </th>
            <td scope="col" >
                <c:choose>
                    <c:when test="${p.status_checking == 1}">
                        Bên Mua Xem Hàng
                    </c:when>
                    <c:when test="${p.status_checking == 2}">
                        Hoàn Thành
                    </c:when>
                    <c:otherwise>
                        Khiếu Nại
                    </c:otherwise>
                </c:choose>
            </td>
            <td scope="col">
                <c:forEach items="${requestScope.accountList}" var="acc">
                    <c:if test="${acc.id eq p.seller_id}">
                        ${acc.user}
                    </c:if>
                </c:forEach>
            </td>
            <td scope="col">
                <c:forEach items="${requestScope.product}" var="pro">
                    <c:if test="${pro.id eq p.product_id}">
                        ${pro.name}
                    </c:if>
                </c:forEach>
            </td>
            <td scope="col">${p.contact}</td>
            <td scope="col">
                <c:forEach items="${requestScope.product}" var="pro">
                    <c:if test="${pro.id eq p.product_id}">
                        ${pro.status_product}
                    </c:if>
                </c:forEach>
            </td>
            <c:set var="formattedPrice1" value="${String.format('%.2f', p.price)}" />
            <td scope="col" >${formattedPrice1}</td>
            <td scope="col" >
                ${p.status_fee == 0 ? "Bên Mua" : "Bên Bán"}
            </td>
            <c:set var="formattedPrice2" value="${String.format('%.2f', p.price*(1/10))}" />
            <td scope="col" >${formattedPrice2}</td>
            <td scope="col" >${p.price+(p.price*(1/10))}</td>
            <td scope="col">${p.created_date}</td>
            <td scope="col">${p.updated_date}</td>
            <td scope="col">${p.id}</td>
        </tr>
    </c:forEach>
</body>
</html>
