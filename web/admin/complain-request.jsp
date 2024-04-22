
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
        <title>SB Admin 2 - Manager complain</title>

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
                                <h1 class="h3 mb-0 text-gray-800">Manager Complain</h1>
                            </div>

                            <div class="row mb-4">
                                <div class="card col-sm-12">
                                    <div class="card-body">
                                        <h5 class="card-title">Filter Form</h5>
                                        <form method="get" action="complain-request">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="searchValue">Search Value</label>
                                                <input type="text" id="searchValue" name="searchValue" value="${param.searchValue}" class="form-control">
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="status">Status</label>
                                                <select id="status" name="status" class="form-control">
                                                    <option selected="" value="">Tất Cả</option>
                                                    <option value="3">Khiếu Nại</option>
                                                    <option value="4">Đã Xử Lý</option>
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
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>status</th>
                                                <th>Seller</th>
                                                <th>Buyer</th>
                                                <th>Product</th>
                                                <th>Request Complain</th>
                                                <th>Response Complain</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="ohs" items="${requestScope.ORDERHISTORY}" varStatus="counter">
                                                <tr>
                                                    <td>${counter.count}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${ohs.status == '3'}">
                                                                <span class="text-danger">Khiếu Nại</span>
                                                            </c:when>
                                                            <c:when test="${ohs.status == '4'}">
                                                                <span class="text-success">Đã Xử Lý</span>
                                                            </c:when>
                                                            <c:when test="${ohs.status == '5'}">
                                                                <span class="text-success">Đã Xử Lý</span>
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        ${ohs.sellerName}
                                                    </td>
                                                    <td>${ohs.buyerName}</td>
                                                    <td>${ohs.productName}</td>
                                                    <td>
                                                        <div class="form-gourp">
                                                            <textarea class="form-control" id="request" name="request" readonly="">
                                                                ${ohs.requestComplain}
                                                            </textarea>    
                                                        </div>
                                                    </td>
                                                    <td>

                                                        <div class="form-gourp">
                                                            <textarea class="form-control" id="response" name="response" readonly="">
                                                                ${ohs.responseComplain}
                                                            </textarea>    
                                                        </div>
                                                    </td>
                                                    <td>
                                                        
                                                        <a href="${pageContext.request.contextPath}/DetailOrderHistory?ohsId=${ohs.id}">Detail Order</a>
                                                        
                                                        <c:if test="${ohs.responseComplain == null}">
                                                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal-${counter.count}">
                                                                Do Response
                                                            </button> 
                                                        </c:if>
                                                        <c:if test="${ohs.responseComplain != null}">
                                                            <p class="text-success">Đã Xử Lý</p>
                                                        </c:if>
                                                        <!-- Modal -->
                                                        <div class="modal fade" id="exampleModal-${counter.count}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <form action="${pageContext.request.contextPath}/response-complain" method="post">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Do Response</h5>
                                                                            <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <div class="form-group">
                                                                                <input type="hidden" name="complainId" value="${ohs.id}">
                                                                            </div>

                                                                            <div class="form-group">
                                                                                <textarea id="response" name="response" class="form-control"></textarea>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Xử Lý Khiếu Nại</label>
                                                                                <select class="form-control" id="isPayback" name="isPayback">
                                                                                    <option value="true">Hoàn Tiền Cho Người Mua</option>
                                                                                    <option value="false">Hoàn Tiền Cho Người Bán</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="row d-flex justify-content-center">
                                        <c:set var="pagination_url" value="${pageContext.request.contextPath}/complain-request?" scope="request"></c:set>
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
