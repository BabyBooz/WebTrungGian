<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.DecimalFormat" %>

<html lang="vi">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Anh Trung Gian</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">
        <style>
            .pagination {
                display: flex;
                justify-content: center;
                margin-bottom: 30px;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
            }

            .pagination a.active {
                background-color: #555;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
            .drop {
                background-color: #343a40;
                color: #ffffff;
                border: none;
                padding: 10px 60px;
                cursor: pointer;
                border-radius: 20px;
                width: 260px;
            }
            .logout {
                background-color: #343a40;
                color: #ffffff;
                border: none;
                margin-top: 16px;
                border-radius: 20px;
                width: 260px;
                text-align: center;
            }
            .logout a{
                color: #ffffff;
                margin-left: 73px;
                font: 16px sans-serif;
            }
            .drop:hover {
                background-color: #23272b;
            }

        </style>
    </head>
    <body>

        <!-- ======= Mobile nav toggle button ======= -->
        <i class="bi bi-list mobile-nav-toggle d-xl-none"></i>

        <!-- ======= Header ======= -->
        <header id="header">
            <div class="d-flex flex-column">

                <div class="profile">
                    <img src="assets/img/pikachu.png" alt="" class="img-fluid rounded-circle">
                    <h1 class="text-light"><a href="information">Anh Trung Gian</a></h1>
                </div>

                <nav id="navbar" class="nav-menu navbar">
                    <ul>
                        <li>
                            <a href="#">Số Dư : ${wallet}</a>
                            <a href="login.jsp">Account: ${name} </a> 
                        </li>     
                        <div>
                            <div class="dropdown mt-3">
                                <button class="drop" class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown">
                                    Thanh Toán
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item" href="recharge">Nạp tiền</a></li>
                                    <li><a class="dropdown-item" href="ViewWithdraw">Lịch sử giao dịch</a></li>
                                    <li><a class="dropdown-item" href="ViewRecharge">Lịch sử rút tiền </a></li>
                                    <li><a class="dropdown-item" href="RequestInfor">Yêu cầu rút tiền </a></li>
                                </ul>
                            </div>
                            <div class="dropdown mt-3">
                                <button class="drop" class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown">
                                    Trung Gian
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item" href="publicMarket">Chợ Công Khai</a></li>
                                    <li><a class="dropdown-item" href="mySalesOrder">Đơn Bán Của Tôi</a></li>
                                    <li><a class="dropdown-item" href="myOrder">Đơn Mua Của Tôi </a></li>
                                </ul>
                            </div>
                            <div class="dropdown mt-3">
                                <button class="drop" class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown">
                                    Dịch Vụ
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item" href="contact.jsp">Contact</a></li>
                                </ul>
                            </div>
                            <button class="logout" type="button" >
                                <a href="login.jsp">Đăng Xuất</a>
                            </button>
                        </div>
                    </ul>
                </nav><!-- .nav-menu -->
            </div>
        </header><!-- End Header -->

        <main id="main">
            <%
            String successMessage = (String) session.getAttribute("successMessage");
            if (successMessage != null) {
            %>
            <div class="alert alert-success" role="alert">
                <%= successMessage %>
            </div>
            <%
                // Xóa thông điệp từ session sau khi đã hiển thị
                session.removeAttribute("successMessage");
            }
            %>

            <!-- ======= Breadcrumbs ======= -->
            <section>
                <div class="container-xl" style="max-width: 100%;">
                    <div class="card-header" style="background-color: #E9ECEF; display: flex; ">
                        <h2 class="text-left" style="font-size: 30px;">Đơn Bán Của Tôi</h2>
                        <button class="addP" onclick="window.location.href = 'addProduct';" style="    border-radius: 50%;margin-left: 68%;">Tạo đơn bán</button>
                        <p class="text-danger" style="padding: 4px; color: red">
                            ${mess}
                        </p>

                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table-fixed text-nowrap">
                            <thead>
                                <tr>
                                    <th scope="col">Chi tiết</th>
                                    <th scope="col">Trạng thái</th>
                                    <th scope="col">Người mua</th>
                                    <th scope="col">Chủ đề</th>
                                    <th scope="col">Liên lạc</th>
                                    <th scope="col">Công khai</th>
                                    <th scope="col">Người chịu phí</th>
                                    <th scope="col">Giá</th>
                                    <th scope="col">Phí giao dịch</th>
                                    <th scope="col">Ngày tạo</th>
                                    <th scope="col">Ngày cập nhật</th>
                                    <th scope="col">Mã trung gian</th>
                                    <th scope="col">Lịch Sử Trạng Thái</th>
                                </tr>
                            </thead>
                            <form action="searchOrder" id="myForm">
                                <tr style="background-color: #F8F9FA;">
                                    <td class="scope-detail"></td>
                                    <td class="scope-price">
                                        <select class="form-select" name="status_checking" id="mySelect" style="height: 38px; font-size: 15px;background-color: #F8F9FA;border: 1px solid #dee2e6;">
                                            <option value="">Tất Cả</option>
                                            <option value="0">Sẵn Sàng Giao Dịch</option>
                                            <option value="1">Bên Mua Xem Hàng</option>
                                            <option value="2">Hoàn Thành</option>
                                            <option value="3">Khiếu Nại</option>
                                            <option value="4">Khiếu Nại Thành Công</option>
                                            <option value="5">Khiếu Nại Thất Bại</option>
                                        </select>
                                    </td>
                                    <td><input name="buyer_id"  type="text" class="form-control" id="input1"></td>
                                    <td><input name="nameProduct" type="text" class="form-control" id="input2"></td>
                                    <td><input name="contact" type="text" class="form-control" id="input3"></td>

                                    <td class="scope-price">
                                        <select class="form-select" name="status_product" id="mySelect1" style="height: 38px; font-size: 15px;background-color: #F8F9FA;border: 1px solid #dee2e6;">
                                            <option value="">Tất Cả</option>
                                            <option value="0">Ẩn</option>
                                            <option value="1">Hiện</option>
                                        </select>
                                    </td>
                                    <td class="scope-price">
                                        <select class="form-select" name="status_fee" id="mySelect2" style="height: 38px; font-size: 15px;background-color: #F8F9FA;border: 1px solid #dee2e6;">
                                            <option value="">Tất Cả</option>
                                            <option value="0">Bên Mua</option>
                                            <option value="1">Bên Bán</option>
                                        </select>
                                    </td>
                                    <td class="scope-fee" >
                                        <div class="input-group" style="display: ruby;">
                                            <input type="number" class="form-control" name="startPrice" placeholder="Từ" id="input4" style="width: 70px;">
                                            <span class="input-group-text" style="background-color: #F8F9FA;">-</span>
                                            <input type="number" class="form-control" name="endPrice" placeholder="Đến" id="input5" style="width: 80px;">
                                        </div>
                                    </td>
                                    <td></td>
                                    <td>
                                        <div class="input-group" style="display: ruby;">
                                            <input type="date" class="form-control" placeholder="Từ" name="start_created_date" id="input6" style="width: 160px;">
                                            <span class="input-group-text" style="background-color: #F8F9FA;">-</span>
                                            <input type="date" class="form-control" placeholder="Đến" name="end_created_date" id="input7" style="width: 160px;">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group" style="display: ruby;">
                                            <input type="date" class="form-control" placeholder="Từ" name="start_updated_date" id="input8" style="width: 160px;">
                                            <span class="input-group-text" style="background-color: #F8F9FA;">-</span>
                                            <input type="date" class="form-control" placeholder="Đến" name="end_updated_date" id="input9" style="width: 160px;">
                                        </div>
                                    </td>
                                    <td><input name="id"  type="number" class="form-control" id="input10"></td>
                                    <td></td>
                                </tr> 
                            </form>
                            <tbody id="infor">
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
                                                <c:when test="${o.status == 5}">
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

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="pagination">
                    <c:forEach begin="1" end="${endP}" var="i">
                        <a href="mySalesOrder?index=${i}">${i}</a>    
                    </c:forEach>
                </div>
            </section>

        </div>
    </section>

</main><!-- End #main -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<!--        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assets/vendor/typed.js/typed.umd.js"></script>
        <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

         Template Main JS File 
        <script src="assets/js/main.js"></script>-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="js/searchOrder.js"></script>
</body>

</html>

