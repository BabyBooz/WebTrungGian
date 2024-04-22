
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
                    <h1 class="text-light"><a href="index.html">Anh Trung Gian</a></h1>
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
                                    <li><a class="dropdown-item" href="historyRecharge.jsp">Lịch sử giao dịch</a></li>
                                    <li><a class="dropdown-item" href="ViewRecharge">Lịch sử rút tiền </a></li>
                                    <li><a id="withdrawRequest" class="dropdown-item" href="RequestToWithdraw">Yêu cầu rút tiền</a></li>
                                </ul>
                            </div>
                            <div class="dropdown mt-3">
                                <button class="drop" class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown">
                                    Mua Hàng
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item" href="#">Sản Phẩm</a></li>
                                    <li><a class="dropdown-item" href="#">Đơn Hàng</a></li>
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
                                    <li><a class="dropdown-item" href="#">Action</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li><a class="dropdown-item" href="#">Something else here</a></li>
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
            <section id="hero" class="d-flex flex-column justify-content-center align-items-center">
            <div class="hero-container" data-aos="fade-in">
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Manager Withdraw</h1>
                    </div>

                    <div class="row mb-4">
                        <div class="card col-sm-12">
                            <div class="card-body">
                                <h5 class="card-title">Filter Form</h5>
                                <form method="get" action="${pageContext.request.contextPath}/ViewWithdraw">
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="searchValue">Search Value</label>
                                            <input type="text" id="searchValue" name="searchValue" value="${param.searchValue}" class="form-control">
                                        </div>

                                        <div class="form-group col-md-6">
                                            <label for="status">Status</label>
                                            <select id="status" name="status" class="form-control">
                                                <option selected="" value="">Tat ca</option>
                                                <option value="0">Yêu cầu</option>
                                                <option value="1">Chấp nhận</option>
                                                <option value="2">Từ chối</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Apply Filters</button>
                                </form>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="card col-sm-12 mb-4">
                            <div class="card-body">

                                <div class="row">
                                    <c:if test="${requestScope.mess == null}">
                                        <p class="text-success">${requestScope.mess}</p>
                                    </c:if>
                                </div>

                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">STT</th>
                                            <th scope="col">User</th>
                                            <th scope="col">Bank name</th>
                                            <th scope="col">Bank branch</th>
                                            <th scope="col">Account number</th>
                                            <th scope="col">Account name</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Status</th>
                                            <th scope="col">Created date</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="wds" items="${requestScope.WITHDRAWS}" varStatus="counter">
                                            <tr>
                                                <td scope="col">${counter.count}</td>
                                                <td scope="col">${wds.userId}</td>
                                                <td scope="col">${wds.bankName}</td>
                                                <td scope="col">${wds.bankBranch}</td>
                                                <td scope="col">${wds.accountNumber}</td>
                                                <td scope="col">${wds.accountName}</td>
                                                <td scope="col">${wds.price}</td>
                                                <td scope="col">
                                                    <c:choose>
                                                        <c:when test="${wds.statusTransaction == '0'}"> 
                                                            <p class="text-primary">Đang tiếp nhận</p>
                                                        </c:when>
                                                        <c:when test="${wds.statusTransaction == '1'}"> 
                                                            <p class="text-success">Chấp nhận</p>
                                                        </c:when>
                                                        <c:when test="${wds.statusTransaction == '2'}"> 
                                                            <p class="text-danger">Từ chối</p>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td scope="col">${wds.createdDate}</td>
                                                <td scope="col">
                                                    <c:choose>
                                                        <c:when test="${wds.statusTransaction == '0'}">
                                                            <p class="text-success">Đang yêu cầu</p>
                                                        </c:when>
                                                        <c:when test="${wds.statusTransaction == '1'}"> 
                                                            <p class="text-success">Chấp nhận</p>
                                                        </c:when>
                                                        <c:when test="${wds.statusTransaction == '2'}"> 
                                                            <p class="text-danger">Từ chối</p>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
        </section><!-- End Hero -->

        </main><!-- End #main -->

        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </body>

</html>
