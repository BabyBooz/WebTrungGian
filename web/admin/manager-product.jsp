

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
        <title>SB Admin 2 - Manager product</title>

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

                        <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h3 mb-0 text-gray-800">Manager Account</h1>
                            </div>

                            <div class="row mb-4">
                                <div class="card col-sm-12">
                                    <div class="card-body">
                                        <h5 class="card-title">Filter Form</h5>
                                        <form method="post" action="manager-product">
                                            
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="searchValue">Search Value</label>
                                                    <input type="text" id="searchValue" value="${param.searchValue}" name="searchValue" class="form-control">
                                                </div>

                                                <div class="form-group col-md-6">
                                                    <label for="sortBy">Sort By</label>
                                                    <select id="sortBy" name="sortBy" class="form-control">
                                                        <option value="id" ${param.sortBy == 'id' ? 'selected' : ''}>Id</option>
                                                        <option value="price" ${param.sortBy == 'price' ? 'selected' : ''}>Price</option>
                                                        <option value="status" ${param.sortBy == 'status' ? 'selected' : ''}>Status</option>
                                                        <option value="name" ${param.sortBy == 'name' ? 'selected' : ''}>Product name</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-row">

                                                <div class="form-group col-md-6">
                                                    <label for="status">Status</label>
                                                    <select id="status" name="status" class="form-control">
                                                        <option value="true"  ${param.status == 'true' ? 'selected' : ''}>Active</option>
                                                        <option value="false" ${param.status == 'false' ? 'selected' : ''}>Inactive</option>
                                                    </select>
                                                </div>
                                                
                                                <div class="form-group col-md-3">
                                                    <label for="minPrice">Min Price</label>
                                                    <input type="number" id="minPrice" name="minPrice" class="form-control" value="${param.minPrice}" step="0.01" placeholder="Enter min price">
                                                </div>
                                                
                                                <div class="form-group col-md-3">
                                                    <label for="maxPrice">Max Price</label>
                                                    <input type="number" id="maxPrice" name="maxPrice" class="form-control" value="${param.maxPrice}" step="0.01" placeholder="Enter max price">
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
                                        <div class="row d-flex align-items-end">
                                            <div class="col-sm-1 ml-auto">
                                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/add-product-admin">Add Product</a>
                                        </div>
                                    </div>
                                    <c:if test="${requestScope.MESS != ''}">
                                        <div class="row">
                                            <p class="text-danger">${requestScope.MESS}</p>
                                        </div>
                                    </c:if>
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Name</th>
                                                <th>Image</th>
                                                <th>Price</th>
                                                <th>Description</th>
                                                <th>Contact</th>
                                                <th>Status</th>
                                                <th>Action</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="product" items="${requestScope.PRODUCTS}" varStatus="counter">
                                                <tr>
                                                    <td>${counter.count}</td>
                                                    <td>${product.name}</td>
                                                    <td>${product.image}</td>
                                                    <td>${product.price}</td>
                                                    <td>${product.description}</td>
                                                    <td>${product.contact}</td>
                                                    <td>${product.statusProduct}</td>
                                                    <td>
                                                        <a href="${pageContext.request.contextPath}/detail-product?product_id=${product.id}" class="btn btn-primary">Detail</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="row d-flex justify-content-center">
                                        <c:set var="pagination_url" value="${pageContext.request.contextPath}/manager-product?" scope="request"></c:set>
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
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">

                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

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
