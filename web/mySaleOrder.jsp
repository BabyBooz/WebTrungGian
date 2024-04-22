<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trang Chủ</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="css/home.css"/>
        <link rel="stylesheet" href="css/bootstrap.css"/>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>

        <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.22.2/dist/bootstrap-table.min.css">         
        <script src="https://unpkg.com/bootstrap-table@1.22.2/dist/bootstrap-table.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-resizable-columns@0.2.3/dist/jquery.resizableColumns.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/jquery-resizable-columns@0.2.3/dist/jquery.resizableColumns.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }

            .card-header{
                padding: .75rem 1.25rem;
            }

            header {
                background-color: #343a40;
                color: #ffffff;
                padding: 10px 0;
            }

            .container-xl {
                margin-top: 20px;
            }

            .menu_iconb {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .avatar {
                width: 30px;
                height: 30px;
                border-radius: 50%;
                margin-left: 10px;
            }

            .offcanvas-body .dropdown {
                margin-bottom: 10px;
            }

            th{
                text-align: center;
                width: 200px;
            }

            .btn-info{
                background-color: #31B0D5;
                border-color: #31B0D5;
            }
            .btn-info:hover{
                background-color: #5BC0DE;
                border-color: #5BC0DE;
            }

            .btn-outline-primary{
                --bs-btn-hover-border-color: #a1aec2;
                --bs-btn-hover-bg: #c0cbdb;
                --bs-btn-active-bg: #c0cbdb;
                --bs-btn-active-border-color: #a1aec2;
                --bs-btn-active-color: #0d6efd;
            }

            /* Style for buttons */
            .drop {
                background-color: #343a40;
                color: #ffffff;
                border: none;
                padding: 8px 20px;
                cursor: pointer;
                border-radius: 20px;
            }

            .drop:hover {
                background-color: #23272b;
            }
        </style>
    </head>
    <body>

        <header>
            <div class="container-xl menu_iconb">
                <button style="color: #3c763d;" class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
                    <img src="images/menu_icon.png" alt="Avatar">
                </button>
                <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
                    <div class="offcanvas-header">
                        <h5 class="offcanvas-title" id="offcanvasExampleLabel">EShop</h5>
                        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                        <div class="dropdown">
                            <button class="drop" class="btn btn-secondary dropdown-toggle" type="button" id="paymentDropdown" data-bs-toggle="dropdown">
                                Quản lý thanh toán
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="paymentDropdown">
                                <li><a class="dropdown-item" href="recharge.jsp">Nạp tiền</a></li>
                                <li><a class="dropdown-item" href="#">Lịch sử giao dịch</a></li>
                                <li><a class="dropdown-item" href="#">Yêu cầu rút tiền</a></li>
                            </ul>
                        </div>
                        <div class="dropdown">
                            <button class="drop" class="btn btn-secondary dropdown-toggle" type="button" id="buyDropdown" data-bs-toggle="dropdown">
                                Mua Hàng
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="buyDropdown">
                                <li><a class="dropdown-item" href="#">Sản Phẩm</a></li>
                                <li><a class="dropdown-item" href="#">Đơn Hàng</a></li>
                            </ul>
                        </div>
                        <div class="dropdown">
                            <button class="drop" class="btn btn-secondary dropdown-toggle" type="button" id="middlemanDropdown" data-bs-toggle="dropdown">
                                Trung Gian
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="middlemanDropdown">
                                <li><a class="dropdown-item" href="publicMarket">Chợ Công Khai</a></li>
                                <li><a class="dropdown-item" href="mysale">Đơn Bán Của Tôi</a></li>
                                <li><a class="dropdown-item" href="#">Đơn Mua Của Tôi </a></li>
                            </ul>
                        </div>
                        <div class="dropdown">
                            <button class="drop" class="btn btn-secondary dropdown-toggle" type="button" id="serviceDropdown" data-bs-toggle="dropdown">
                                Dịch Vụ
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="serviceDropdown">
                                <li><a class="dropdown-item" href="#">Action</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <h1><a href="homePage.jsp" style="color: white; text-decoration: unset"> Trang Chủ </a></h1>
                <div>
                    <a href="#"><img style="margin-right: 10px;" src="images/dt.png" alt="#" />0328424790</a>
                    <a href="login.jsp">Log in <img style="margin-right: 15px;" src="images/5.png" alt="#" />${name} </a>
                    <img src="images/avatar.png" alt="Avatar" class="avatar"> <!-- Avatar tròn -->
                </div>
            </div>
        </header>

        <section>
            <div class="container-xl">
                <div class="card-header" style="background-color: #E9ECEF ">
                    <h2 class="text-left" >Đơn bán của tôi</h2>
                </div>
                <div class="table-responsive" style="margin-bottom: 50px; overflow-x: scroll; overflow-y: visible;">
                    <table class="table table-striped table-bordered table-hover table-fixed text-nowrap">
                        <thead>
                            <tr>
                                <th scope="col">Mã Trung Gian</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col">Người mua</th>
                                <th scope="col">Chủ đề trung gian</th>
                                <th scope="col">Phương thức liên hệ</th>
                                <th scope="col">Công khai / Riêng tư</th>
                                <th scope="col">Giá tiền</th>
                                <th scope="col">Bên chịu phí</th>
                                <th scope="col">Phí giao dịch</th>
                                <th scope="col">Tổng phí thu người mua</th>
                                <th scope="col">Tiền thực nhận khi thành công</th>
                                <th scope="col">Thời gian tạo</th>
                                <th scope="col">Cập Nhật Cuối</th>
                                <th scope="col" style="width: 300px; position: sticky; right: 0;">Chi tiết</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td><input name="id"  type="number" class="form-control"></td>

                                <td>
                                    <select class="form-select" style="height: 34px; font-size: revert;">
                                        <option value="">Tất cả</option>                                       
                                        <option value="0">Sẵn sàng giao dịch</option>                                       
                                        <option value="1">Bên mua đang kiểm tra hàng</option>
                                        <option value="2">Hoàn thành</option>
                                        
                                    </select>
                                </td>
                                <td><input type="text" class="form-control"></td>
                                <td><input type="text" class="form-control"></td>
                                <td><input type="text" class="form-control"></td>
                                <td>
                                    <select class="form-select" style="height: 34px; font-size: revert;">
                                        <option value="">Tất cả</option>
                                        <option value="1">Ấn chỉ ai có link mới xem được</option>
                                        <option value="0">Hiện công khai ai cũng xem được</option>
                                    </select>
                                </td>
                                <td><input type="text" class="form-control"></td>
                                <td>
                                    <select class="form-select" style="height: 34px; font-size: revert;">
                                        <option value="">Tất cả</option>
                                        <option value="1">Bên Mua</option>
                                        <option value="0">Bên Bán</option>
                                    </select>
                                </td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><input type="text" class="form-control"></td>
                                <td><input type="text" class="form-control"></td>

                                <td style="position: sticky; right: 0;">
                                    <!--                                    <button type="button" class="mr-1 btn btn-outline-danger rounded-pill">
                                                                            <i class="fa fa-remove"></i> Bỏ lọc
                                                                        </button>
                                                                        <button type="button" class="mr-1 btn btn-outline-primary rounded-pill">
                                                                            Thu gọn >>
                                                                        </button>-->
                                </td>
                            </tr>
                        </tbody>
                        <tbody>
                            <c:forEach items="${requestScope.listPro}" var="p">
                                <tr>
                                    <td scope="col">${p.id}</td>
                                    <td scope="col">
                                        <c:choose>                                          
                                            <c:when test="${p.image == 0}">Sẵn sàng giao dịch</c:when>
                                            <c:when test="${p.image == 1}">Bên mua đang kiểm tra hàng</c:when>
                                            <c:when test="${p.image == 2}">Hoàn thành</c:when>
                                            <c:otherwise>Unknown</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td scope="col">${p.id}</td>
                                    <td scope="col">${p.name}</td>
                                    <td scope="col">${p.contact}</td>
                                    <td scope="col">
                                        <c:choose>
                                            <c:when test="${p.status_product == 0}">Hiện công khai ai cũng xem được</c:when>
                                            <c:when test="${p.status_product == 1}">Ấn chỉ ai có link mới xem được</c:when>
                                            <c:otherwise>Unknown</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td scope="col">${p.price}</td>
                                    <td scope="col">
                                        <c:choose>
                                            <c:when test="${p.status_fee == 0}">Bên Mua</c:when>
                                            <c:when test="${p.status_fee == 1}">Bên Bán</c:when>
                                            <c:otherwise>Unknown</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td scope="col">${p.price*(1/10)}</td>
                                    <td scope="col">
                                        <c:choose>
                                            <c:when test="${p.status_fee == 0}">
                                                ${p.price+(p.price*(1/10))} 
                                            </c:when>
                                            <c:when test="${p.status_fee == 1}">
                                                ${p.price} 
                                            </c:when>
                                            <c:otherwise>Unknown</c:otherwise>
                                        </c:choose></td>
                                    <td scope="col">
                                        <c:choose>
                                            <c:when test="${p.status_fee == 0}">
                                                ${p.price} <!-- Số tiền thực nhận khi là bên mua -->
                                            </c:when>
                                            <c:when test="${p.status_fee == 1}">
                                                ${p.price - (p.price*(1/10))} <!-- Số tiền thực nhận khi là bên bán -->
                                            </c:when>
                                            <c:otherwise>Unknown</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td scope="col">${p.created_date}</td>
                                    <td scope="col">${p.updated_date}</td>
                                    <td style="position: sticky; right: 0;">
                                        <c:choose>
                                            <c:when test="${p.image == 2}">
                                                <!-- Hiển thị button chi tiết -->
                                                <button type="button" class="mr-1 btn btn-info" data-toggle="tooltip">
                                                    <i class="fa fa-info"></i> 
                                                    <a href="detail1?id=${p.id}" style="color: white; text-decoration: unset"> Chi tiết</a>
                                                </button>
                                                <button type="button" class="mr-1 btn btn-outline-primary" data-toggle="tooltip" 
                                                        style="">
                                                    <i class="fa fa-history" ></i> 
                                                    <a href="history?id=${p.id}" style="color: black; text-decoration: unset"> Lịch sử trạng thái</a>
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <!-- Chuyển hướng đến trang chi tiết JSP khác -->
                                                <button type="button" class="mr-1 btn btn-info" data-toggle="tooltip">
                                                    <i class="fa fa-info"></i>
                                                    <a href="detail3?id=${p.id}" style="color: white; text-decoration: unset">Chi Tiết</a>
                                                </button>
                                                <button type="button" class="mr-1 btn btn-outline-primary" data-toggle="tooltip" 
                                                        style="">
                                                    <i class="fa fa-history" ></i> 
                                                    <a href="history?id=${p.id}" style="color: black; text-decoration: unset"> Lịch sử trạng thái</a>
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>      
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>

        <footer class="footer  py-3" style="margin-top: 20px">
            <div class="container-xl">
                <p class="p2">© 2024 Trang Chủ | Designed by <a href="https://www.facebook.com/quockhanh.pham.saf2391" target="_blank">Quốc Khánh</a></p>
            </div>
        </footer>      

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function () {
                $('table').resizableColumns();
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSGFpoO/ufreqqF6MVu4JdG7PhIxZlW8sSJv43gkdSHlua9DmM/" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
