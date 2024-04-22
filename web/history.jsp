
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>History</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="css/home.css"/>
        <link rel="stylesheet" href="css/bootstrap.css"/>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.18.2/bootstrap-table.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.18.2/bootstrap-table.min.js"></script>

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

            .table-wrapper {
                overflow-x: auto;
            }

            .table-title h2 {
                margin-bottom: 20px;
            }

            .table-title b {
                color: #343a40;
            }

            footer {
                background-color: #343a40;
                color: #ffffff;
                text-align: center;
                padding: 10px 0;
                position: fixed;
                bottom: 0;
                width: 100%;
            }

            .avatar {
                width: 30px;
                height: 30px;
                border-radius: 50%;
                margin-left: 10px;
            }

            .btn-secondary {
                background-color: #dc3545;
                border-color: red;

            }

            .btn-secondary:hover {
                background-color: #e5e5e5;
                border-color: #e5e5e5;
                color: black;
            }

            /* Custom style for buttons */
            .btn-filter {
                background-color: #343a40;
                color: #fff;
                border: none;
                padding: 8px 20px;
                cursor: pointer;

            }

            .btn-filter:hover {
                background-color: #23272b;
            }

            .btn-filter-group {
                margin-top: 10px;
            }
        </style>

    </head>
    <body>
        <div class="container-xl">
            <div class="card-header" style="background-color: #E9ECEF ">
                <h2 class="text-left" style="font-size: 30px;">Lịch sử trạng thái</h2>
            </div>
            <div class="table-responsive" style="margin-bottom: 50px; overflow-x: scroll; overflow-y: visible;">
                <table id="myTable" class="table table-striped table-bordered table-hover table-fixed text-nowrap " >
                    <thead>
                        <tr id="headerRow" style="font-size: 20px;">

                            <th scope="col">Thời gian</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Mô tả</th>
                            <th scope="col">Người thực hiện</th>
                            <th scope="col" style="position: sticky; right: 0;">Chi tiết</th>
                        </tr>
                    </thead>

                    <tr id="searchRow">
                        <td>
                            <div class="input-group">
                                <input type="date" class="form-control" style="font-size: revert;" placeholder="Từ" id="searchStartDate" onchange="searchTable(0)">
                                <span class="input-group-text">-</span>
                                <input type="date" class="form-control" style="font-size: revert;" placeholder="Đến" id="searchEndDate" onchange="searchTable(0)">
                            </div>
                        </td>
                        <td>
                            <select class="form-select" id="searchStatus" onchange="searchByStatus()" style="height: 34px; font-size: revert;">
                                <option value="">Tất cả</option>                                       
                                <option value="0">Sẵn sàng giao dịch</option>                                       
                                <option value="1">Bên mua đang kiểm tra hàng</option>
                                <option value="2">Bên mua khiếu nại sản phẩm</option>
                                <option value="3">Hoàn thành</option>
                            </select>
                        </td>
                        <td><input type="text" id="searchDescribe" class="form-control" onkeyup="searchTable(2)" style="font-size: 15px;"></td>
                        <td><input type="text" id="searchUser" class="form-control" onkeyup="searchTable(3)" style="font-size: 15px;"></td>
                        <td style="position: sticky; right: 0;">
                        </td>
                    </tr>

                    <tbody>
                        <c:forEach items="${requestScope.history}" var="h">
                            <tr style="font-size: 17px;">
                                <td scope="col">${h.created_date}</td>
                                <td scope="col">
                                    <c:choose>                                          
                                        <c:when test="${h.statusChecking == 0}">Sẵn sàng giao dịch</c:when>
                                        <c:when test="${h.statusChecking == 1}">Bên mua đang kiểm tra hàng</c:when>
                                        <c:when test="${h.statusChecking == 2}">Bên mua khiếu nại sản phẩm</c:when>
                                        <c:when test="${h.statusChecking == 3}">Hoàn thành</c:when>
                                    </c:choose>
                                </td>
                                <td scope="col">
                                    <c:choose>                                          
                                        <c:when test="${h.statusChecking == 0}">Thu phí đăng yêu cầu</c:when>
                                        <c:when test="${h.statusChecking == 1}">Bên mua xem hàng</c:when>
                                        <c:when test="${h.statusChecking == 2}">Người mua khiếu nại đơn hàng</c:when>
                                        <c:when test="${h.statusChecking == 3}">Người mua xác nhận giao dịch thành công</c:when>
                                    </c:choose>
                                </td>
                                <td scope="col">
                                    <c:forEach items="${requestScope.accountList2}" var="ac">
                                        <c:if test="${h.createdBy eq ac.id}">
                                            ${ac.user}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td style="position: sticky; right: 0;">
                                    <button type="button" class="mr-1 btn btn-info" data-toggle="tooltip" style="font-size: 17px;">
                                        <i class="fa fa-info"></i> 
                                        <a href="detail2?id=${h.id}" style="color: white; text-decoration: unset"> Chi tiết</a>
                                    </button>
                                </td>
                            </tr>  
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" style="font-size: 17px;">
                    <a href="mySalesOrder" style="color: white; text-decoration: unset"> Close </a>
                </button>
            </div>
        </div>
        <!--        <script>
                    function searchTable(columnIndex) {
                        var input, filter, table, tr, td, i, txtValue;
                        input = document.getElementById("search" + (columnIndex === 0 ? "Date" : columnIndex === 2 ? "Describe" : "User"));
                        filter = input.value.toUpperCase();
                        table = document.getElementById("myTable");
                        tr = table.getElementsByTagName("tr");
        
                        for (i = 1; i < tr.length; i++) {
                            td = tr[i].getElementsByTagName("td")[columnIndex];
                            if (td) {
                                txtValue = td.textContent || td.innerText;
                                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                    tr[i].style.display = ""; // Hiển thị lại hàng nếu tìm thấy kết quả
                                } else {
                                    tr[i].style.display = tr[i].id === "headerRow" || tr[i].id === "searchRow" ? "" : "none"; // Ẩn hàng chỉ khi không phải là hàng tiêu đề hoặc hàng tìm kiếm
                                }
                            }
                        }
                    }
                </script>-->
        <script>
            function searchTable(columnIndex) {
                var inputStart, inputEnd, inputUser, table, tr, td, i, startDate, endDate;
                inputStart = document.getElementById("searchStartDate").value; // Lấy giá trị ngày bắt đầu
                inputEnd = document.getElementById("searchEndDate").value; // Lấy giá trị ngày kết thúc
                inputDescribe = document.getElementById("searchDescribe").value.toUpperCase(); // Lấy giá trị mô tả
                inputUser = document.getElementById("searchUser").value.toUpperCase(); // Lấy giá trị tìm kiếm người thực hiện
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");

                for (i = 1; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[columnIndex];
                    if (td) {
                        var txtValue = td.textContent || td.innerText;

                        // Kiểm tra tìm kiếm người thực hiện
                        if (columnIndex === 3) {
                            if (txtValue.toUpperCase().indexOf(inputUser) > -1) {
                                tr[i].style.display = "";
                            } else {
                                tr[i].style.display = tr[i].id === "headerRow" || tr[i].id === "searchRow" ? "" : "none";
                            }
                        }else if(columnIndex === 2){
                            if (txtValue.toUpperCase().indexOf(inputDescribe) > -1) {
                                tr[i].style.display = "";
                            } else {
                                tr[i].style.display = tr[i].id === "headerRow" || tr[i].id === "searchRow" ? "" : "none";
                            }
                        } else if (columnIndex === 0) { // Kiểm tra tìm kiếm theo thời gian
                            var dateValue = new Date(txtValue);
                            if (inputStart && inputEnd) {
                                startDate = new Date(inputStart);
                                endDate = new Date(inputEnd);
                                if (dateValue >= startDate && dateValue <= endDate) {
                                    tr[i].style.display = "";
                                } else {
                                    tr[i].style.display = tr[i].id === "headerRow" || tr[i].id === "searchRow" ? "" : "none";
                                }
                            } else {
                                tr[i].style.display = ""; // Hiển thị tất cả các hàng nếu không có ngày bắt đầu hoặc kết thúc
                            }
                        }
                    }
                }
            }

        </script>
        <script>
            function searchByStatus() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("searchStatus");
                filter = input.value; // Không cần chuyển đổi thành chữ hoa nữa
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");

                for (i = 1; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[1]; // Vị trí của cột trạng thái là 1
                    if (td) {
                        txtValue = td.textContent || td.innerText;

                        if (filter === "0" && txtValue.trim() !== "Sẵn sàng giao dịch") {
                            tr[i].style.display = tr[i].id === "headerRow" || tr[i].id === "searchRow" ? "" : "none"; // Ẩn hàng nếu trạng thái không phải "Sẵn sàng giao dịch"
                        } else if (filter === "1" && txtValue.trim() !== "Bên mua đang kiểm tra hàng") {
                            tr[i].style.display = tr[i].id === "headerRow" || tr[i].id === "searchRow" ? "" : "none"; // 
                        } else if (filter === "2" && txtValue.trim() !== "Bên mua khiếu nại sản phẩm") {
                            tr[i].style.display = tr[i].id === "headerRow" || tr[i].id === "searchRow" ? "" : "none"; // 
                        } else if (filter === "3" && txtValue.trim() !== "Hoàn thành") {
                            tr[i].style.display = tr[i].id === "headerRow" || tr[i].id === "searchRow" ? "" : "none"; // 
                        } else {
                            tr[i].style.display = ""; // Hiển thị lại hàng nếu tìm thấy kết quả
                        }
                    }
                }
            }

        </script>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSGFpoO/ufreqqF6MVu4JdG7PhIxZlW8sSJv43gkdSHlua9DmM/" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>


    </body>
</html>