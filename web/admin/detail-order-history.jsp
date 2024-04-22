
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
        <title>SB Admin 2 - Detail order</title>

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
                                <h1 class="h3 mb-0 text-gray-800">Detail Order History</h1>
                            </div>

                        </div>


                        <div class="row">
                            <div class="card col-sm-12 mb-4">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-12 mt-0">
                                            <div class="form-group">
                                                <label class="font-weight-bold" for="id">ID</label>
                                                <input type="text" class="form-control" readonly="" name="id" value="${requestScope.ORDERHISTORY.id}">
                                        </div> 
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="font-weight-bold" for="id">status</label>
                                            <c:choose>
                                                <c:when test="${requestScope.ORDERHISTORY.status == '3'}">
                                                    <input type="text" class="form-control text-danger" readonly="" name="status" value="Khieu nai">
                                                </c:when>
                                                <c:when test="${requestScope.ORDERHISTORY.status == '4'}">
                                                    <input type="text" class="form-control text-success" readonly="" name="status" value="Da xu ly">
                                                </c:when>
                                            </c:choose>
<!--                                            <input type="text" class="form-control" readonly="" name="status" value="${requestScope.ORDERHISTORY.status}">-->
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="font-weight-bold" for="id">Seller</label>
                                            <input type="text" class="form-control" readonly="" name="Seller" value="${requestScope.ORDERHISTORY.sellerName}">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label  class="font-weight-bold" for="id">Buyer</label>
                                            <input type="text" class="form-control" readonly="" name="Buyer" value="${requestScope.ORDERHISTORY.buyerName}">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label  class="font-weight-bold" for="id">Product</label>
                                            <input type="text" class="form-control" readonly="" name="Product" value="${requestScope.ORDERHISTORY.productName}">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="font-weight-bold" for="id">price</label>
                                            <input type="text" class="form-control" readonly="" name="price" value="${requestScope.ORDERHISTORY.price}">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="font-weight-bold" for="id">description</label>
                                            <input type="text" class="form-control" readonly=""name="description" value="${requestScope.ORDERHISTORY.description}">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="font-weight-bold" for="id">contact</label>
                                            <input type="text" class="form-control" readonly=""name="contact" value="${requestScope.ORDERHISTORY.contact}">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="font-weight-bold" for="id">Noi Dung an</label>
                                            <input type="text" class="form-control"readonly="" name="Noi Dung an" value="${requestScope.ORDERHISTORY.hidden_content}">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="font-weight-bold" for="id">Ngay tao</label>
                                            <input type="text" class="form-control"readonly="" name="Ngay tao" value="${requestScope.ORDERHISTORY.created_date}">
                                        </div> 
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="font-weight-bold" for="id">Ngay sua doi</label>
                                            <input type="text" class="form-control"readonly="" name="Ngay sua doi" value="${requestScope.ORDERHISTORY.updated_date}">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <a class="btn btn-primary text-start" href="${pageContext.request.contextPath}/complain-request">Back</a>
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
