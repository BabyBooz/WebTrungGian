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
        <link rel="stylesheet" href="css/recharge.css"/>
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
                                    <li><a class="dropdown-item" href="ViewWithdraw">Lịch sử giao dịch</a></li>
                                    <li><a class="dropdown-item" href="ViewRecharge">Lịch sử rút tiền </a></li>
                                    <li><a class="dropdown-item" href="RequestInfor">Yêu cầu rút tiền </a></li>
                                    <!--<li><a id="withdrawRequest" class="dropdown-item" href="RequestToWithdraw">Yêu cầu rút tiền</a></li>-->
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
            <section>
                <div class="container mt-5" style="max-width: 100%;">
                    <div class="card">
                        <div class="card-header" style="background-color: #E9ECEF;">
                            <h3 class="mb-0">Yêu cầu nạp tiền</h3>
                        </div>
                        <div class="card-body">
                            <form action="ajaxServlet" id="frmCreateOrder" method="post">
                                <div class="form-group row">
                                    <label class="col-md-3 col-form-label text-md-right" style="text-align: end;"><b>Chọn phương thức</b></label>
                                    <div class="col-md-9">
                                        <div class="form-check" style="padding-left: 0px;">
                                            <div>
                                                <input type="radio" id="money1" value="" name="deposit_method" required>
                                                <label for="money1">PAYMENT GATEWAY (CHARGING AN ADDITIONAL 3% SERVICE FEE OR 3K FOR TRANSACTIONS BELOW 100K)</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group row">
                                    <label class="col-md-3 col-form-label text-md-right" style="text-align: end;"><b>Số tiền (VND)</b></label>
                                    <div class="col-md-9">
                                        <input id="tien" type="number" style="width: 100%;" placeholder="Số tiền cần nạp (Tối thiểu 10,000 vnđ)" name="amount" oninput="formatCurrency(this)" required style="width: 626.85px" value="10000">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-md-3 col-form-label text-md-right" style="text-align: end;"><b>Mô tả</b></label>
                                    <div class="col-md-9">
                                        <textarea type="text" style="width: 100%;" id="description" class="description" placeholder="Ghi chú khoản nạp khi cần thiết" style="overflow-y: auto;width: 646.85px;padding-bottom: 60px;font-family: sans-serif"></textarea>
                                    </div>
                                </div>
                                <div class="deposit" style="padding-top:0;text-align: center;" >
                                    <button class="mr-1 btn btn-success" onclick="LoadError()" type="submit" id="naptien">Nạp Tiền</button>
                                </div>
                                <br><!-- comment -->
                                
                            </form>
                        </div>
                    </div>
                </div>
            </section>

        </main><!-- End #main -->
        <script>
            // chuyen doi so tien thanh kieur int sau do chuyen sang text
            document.getElementById('tien').addEventListener('input', function () {
                var numberInput = parseInt(this.value);
                var textOutput = numbered.stringify(numberInput);
                document.getElementById('number_text').value = textOutput;
            });
            window.addEventListener('DOMContentLoaded', function () { //bat event khi tai lai trang la doc luon
                var numberInput = document.getElementById('tien').value;
                var textOutput = numbered.stringify(parseInt(numberInput));
                document.getElementById('number_text').value = textOutput;
            });

            function LoadError() {
                var element = document.getElementById('naptien');
                var value = document.getElementById('tien').value;
                if (value < 10000) {
                    element.type = "button";
                } else {
                    element.type = "submit";
                }
                $.ajax({
                    url: "ajaxServlet",
                    type: "POST",
                    data: {amount: $("#tien").val()}, // Gửi giá trị số tiền từ input với id là "tien" đến servlet
                    success: function (response) {
                        var error = document.getElementById('error');
                        error.innerHTML = response;
                    },
                    error: function (xhr, status, error) {
                        console.error('Error:', error); // Xử lý lỗi nếu request không thành công
                    }
                });
            }


        </script>


        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="js/searchMarket.js"></script>
    </body>

</html>

