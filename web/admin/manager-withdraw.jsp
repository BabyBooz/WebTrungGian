
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
        <title>SB Admin 2 - Manager withdraw</title>

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
                                <h1 class="h3 mb-0 text-gray-800">Manager Withdraw</h1>
                            </div>

                            <div class="row mb-4">
                                <div class="card col-sm-12">
                                    <div class="card-body">
                                        <h5 class="card-title">Filter Form</h5>
                                        <form method="get" action="${pageContext.request.contextPath}/ManagerWithdraw">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="searchValue">Search Value</label>
                                                <input type="text" id="searchValue" name="searchValue" value="${param.searchValue}" class="form-control">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="status">Status</label>
                                                <select id="status" name="status" class="form-control">
                                                    <option selected="" value="">Tất Cả</option>
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
                                                <th>STT</th>
                                                <th>User</th>
                                                <th>Bank name</th>
                                                <th>Bank branch</th>
                                                <th>Account number</th>
                                                <th>Account name</th>
                                                <th>Price</th>
                                                <th>Status</th>
                                                <th>Created date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="wds" items="${requestScope.WITHDRAWS}" varStatus="counter">
                                                <tr>
                                                    <td>${counter.count}</td>
                                                    <td>${wds.userId}</td>
                                                    <td>${wds.bankName}</td>
                                                    <td>${wds.bankBranch}</td>
                                                    <td>${wds.accountNumber}</td>
                                                    <td>${wds.accountName}</td>
                                                    <td>${wds.price}</td>
                                                    <td>
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
                                                    <td>${wds.createdDate}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${wds.statusTransaction == '0'}"> 
                                                                <a class="btn btn-success" href="${pageContext.request.contextPath}/UpdateWithdrawStatus?wdsID=${wds.id}&status=1">Chấp nhận</a>
                                                                <a class="btn btn-danger" href="${pageContext.request.contextPath}/UpdateWithdrawStatus?wdsID=${wds.id}&status=2">Từ chối</a>
                                                            </c:when>
                                                            <c:when test="${wds.statusTransaction == '1'}"> 
                                                                <p class="text-success">Bạn đã chấp nhận</p>
                                                            </c:when>
                                                            <c:when test="${wds.statusTransaction == '2'}"> 
                                                                <p class="text-danger">Bạn đã từ chối</p>
                                                            </c:when>
                                                        </c:choose>
<!--                                                        <a href="${pageContext.request.contextPath}/DetailWithdraw?wdsID=${wds.id}">Detail withdraw</a>-->
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="row d-flex justify-content-center">
                                        <c:set var="pagination_url" value="${pageContext.request.contextPath}/ManagerWithdraw?" scope="request"></c:set>
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
