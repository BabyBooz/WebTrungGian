
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>SB Admin 2 - Manager Market</title>

        <jsp:include page="components/head.jsp"></jsp:include>

        </head>

        <body id="page-top">

            <!-- Page Wrapper -->
            <div id="wrapper">

                <!-- Sidebar -->
            <jsp:include page="components/slidebar.jsp"></jsp:include>
                <!-- End of Sidebar -->

                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                    <jsp:include page="components/topbar.jsp"></jsp:include>

                        -<!-- comment -->   <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h3 mb-0 text-gray-800">Manager Market</h1>
                            </div>

                            <div class="row mb-4">
                                <div class="card col-sm-12">
                                    <div class="card-body">
                                        <h5 class="card-title">Filter Form</h5>
                                        <form method="get" action="${pageContext.request.contextPath}/ManagerPublicMarket">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="searchValue">Search Value</label>
                                                <input type="text" id="searchValue" name="searchValue" value="${param.searchValue}" class="form-control">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="status">Status</label>
                                                <select id="status" name="status" class="form-control">
                                                    <option selected="" value="">Tat ca</option>
                                                    <option value="1">Đang hiện</option>
                                                    <option value="0">Đang ẩn</option>
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
                                                <th scope="col">Chủ Đề</th>
                                                <th scope="col">Liên Hệ</th>
                                                <th scope="col">Giá Tiền</th>
                                                <th scope="col">Bên Chịu Phí</th>
                                                <th scope="col">Phí Trung Gian</th>
                                                <th scope="col">Tổng Phí</th>
                                                <th scope="col">Người Bán</th>
                                                <th scope="col">Thời Gian Tạo</th>
                                                <th scope="col">Cập Nhật Cuối</th>
                                                <th scope="col">Mã Trung Gian</th>
                                                <th scope="col">Tình trạng</th>
                                                <th scope="col">Hành động</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.MARKETS}" var="p">
                                                <tr >
                                                    <th scope="col">${p.name}</th>
                                                    <th scope="col">${p.contact}</th>
                                                        <c:set var="formattedPrice1" value="${String.format('%.2f', p.price)}" />
                                                    <th scope="col" >${formattedPrice1}</th>
                                                    <th scope="col" >
                                                        ${p.status_fee == 0 ? "Bên Mua" : "Bên Bán"}
                                                    </th>
                                                    <c:set var="formattedPrice2" value="${String.format('%.2f', p.price*(1/10))}" />
                                                    <th scope="col" >${formattedPrice2}</th>
                                                    <th scope="col" >${p.price+(p.price*(1/10))}</th>
                                                    <th scope="col">
                                                        <c:forEach items="${requestScope.accountList}" var="ac">
                                                            <c:if test="${p.created_by eq ac.id}">
                                                                ${ac.user}
                                                            </c:if>
                                                        </c:forEach>
                                                    </th>
                                                    <th scope="col">${p.created_date}</th>
                                                    <th scope="col">${p.updated_date}</th>
                                                    <th scope="col">${p.id}</th>
                                                    <th scope="col">
                                                        <c:choose>
                                                            <c:when test="${p.status_product == '1'}">
                                                                <p class="text-primary">Đang hiện</p>
                                                            </c:when>
                                                             <c:when test="${p.status_product == '0'}">
                                                                 <p class="text-danger">Đang ẩn</p>
                                                            </c:when>
                                                        </c:choose>
                                                    </th>
                                                    <th>
                                                        <c:choose>
                                                            <c:when test="${p.status_product == '1'}">
                                                                <a href="${pageContext.request.contextPath}/UpdateMarketProductStatus?product_id=${p.id}&product_status=0" class="btn btn-warning">Ẩn sản phẩm</a>
                                                            </c:when>
                                                             <c:when test="${p.status_product == '0'}">
                                                                 <a href="${pageContext.request.contextPath}/UpdateMarketProductStatus?product_id=${p.id}&product_status=1" class="btn btn-success">Hiện sản phẩm</a>
                                                            </c:when>
                                                        </c:choose>
                                                    </th>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="row d-flex justify-content-center">
                                        <c:set var="pagination_url" value="${pageContext.request.contextPath}/ManagerPublicMarket?" scope="request"></c:set>
                                            <div class="text-center mt-5">
                                                <nav class="text-center" aria-label="Page navigation example">
                                                <c:choose>          
                                                    <c:when test="${requestScope.paging.getTotalPage() < 2}">
                                                        <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                                            <ul class="pagination">
                                                                <c:forEach begin="1" end="${requestScope.paging.getTotalPage()}" var="i">
                                                                    <li class="page-item ${i == requestScope.paging.page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                                                                    </c:forEach>
                                                            </ul>
                                                        </nav>
                                                    </c:when>
                                                    <c:when test="${requestScope.paging.getPage() < 2}">
                                                        <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                                            <ul class="pagination">                               
                                                                <c:forEach begin="1" end="${requestScope.paging.getTotalPage()}" var="i">
                                                                    <li class="page-item ${i == requestScope.paging.page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                                                                    </c:forEach>
                                                                <li class="page-item"><a class="page-link" href="${pagination_url}page=${requestScope.paging.page+1}">Next</a></li>
                                                            </ul>
                                                        </nav>
                                                    </c:when>
                                                    <c:when test="${requestScope.paging.getPage()+1 > requestScope.paging.getTotalPage()}">
                                                        <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                                            <ul class="pagination">
                                                                <li class="page-item"><a class="page-link" href="${pagination_url}page=${requestScope.paging.page-1}">Previous</a></li>
                                                                    <c:forEach begin="1" end="${requestScope.paging.getTotalPage()}" var="i">
                                                                    <li class="page-item ${i == requestScope.paging.page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                                                                    </c:forEach>
                                                            </ul>
                                                        </nav>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                                            <ul class="pagination">
                                                                <li class="page-item"><a class="page-link" href="${pagination_url}page=${requestScope.paging.page-1}">Previous</a></li>
                                                                    <c:forEach begin="1" end="${requestScope.paging.getTotalPage()}" var="i">
                                                                    <li class="page-item ${i == requestScope.paging.page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                                                                    </c:forEach>
                                                                <li class="page-item"><a class="page-link" href="${pagination_url}page=${requestScope.paging.page+1}">Next</a></li>
                                                            </ul>
                                                        </nav>
                                                    </c:otherwise>
                                                </c:choose>
                                            </nav>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">

                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Bootstrap core JavaScript-->
        <script src="admin/assets/vendor/jquery/jquery.min.js"></script>
        <script src="admin/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="admin/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="admin/assets/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="admin/assets/vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="admin/assets/js/demo/chart-area-demo.js"></script>
        <script src="admin/assets/js/demo/chart-pie-demo.js"></script>

    </body>
</html>
