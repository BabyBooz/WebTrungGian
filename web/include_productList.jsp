<%-- 
    Document   : newjsp
    Created on : Feb 21, 2024, 8:37:54 PM
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
        <c:forEach items="${requestScope.listP}" var="p">
        <tr >

            <th scope="col" class="detail">
                <c:choose>
                    <c:when test="${userid eq p.created_by}">
                        <a href="updateDetail?id=${p.id}">Chi Tiết </a>
                    </c:when>
                    <c:otherwise>
                        <a href="detail?id=${p.id}">Chi Tiết </a>
                    </c:otherwise>
                </c:choose> 
            </th>
            <td scope="col">${p.name}</td>
            <td scope="col">${p.contact}</td>
                <c:set var="formattedPrice1" value="${String.format('%.2f', p.price)}" />
            <td scope="col" >${formattedPrice1}</td>
            <td scope="col" >
                ${p.status_fee == 0 ? "Bên Mua" : "Bên Bán"}
            </td>
            <c:set var="formattedPrice2" value="${String.format('%.2f', p.price*(1/10))}" />
            <td scope="col" >${formattedPrice2}</td>
            <td scope="col" >${p.price+(p.price*(1/10))}</td>
            <td scope="col">
                <c:forEach items="${requestScope.accountList}" var="ac">
                    <c:if test="${p.created_by eq ac.id}">
                        ${ac.user}
                    </c:if>
                </c:forEach>
            </td>
            <td scope="col">${p.created_date}</td>
            <td scope="col">${p.updated_date}</td>
            <td scope="col">${p.id}</td>
        </tr>
    </c:forEach>
</body>
</html>
