

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>SB Admin 2 - Detail account</title>

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
                                <h1 class="h3 mb-0 text-gray-800">Detail Product</h1>
                            </div>

                            <div class="row">
                                <div class="card col-sm-12 mb-4">
                                    <div class="card-body">
                                        <div class="row">
                                        <c:if test="${requestScope.MESS != ''}">
                                            <p class="text-danger">${requestScope.MESS}</p>
                                        </c:if>
                                        </div>
                                        <form method="post" action="detail-product">
                                            <div class="form-group">
                                                <label for="productId">Product ID</label>
                                                <input type="text" id="productId" name="productId" class="form-control"
                                                       value="${requestScope.PRODUCT_DETAIL.id}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="name">Name</label>
                                            <input type="text" id="name" name="name" class="form-control"
                                                   value="${requestScope.PRODUCT_DETAIL.name}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="image">Image</label>
                                            <input type="text" id="image" name="image" class="form-control"
                                                   value="${requestScope.PRODUCT_DETAIL.image}">
                                        </div>
                                        <div class="form-group">
                                            <label for="price">Price</label>
                                            <input type="number" id="price" name="price" class="form-control"
                                                   value="${requestScope.PRODUCT_DETAIL.price}">
                                        </div>
                                        <div class="form-group">
                                            <label for="description">Description</label>
                                            <textarea id="description" name="description" class="form-control">${requestScope.PRODUCT_DETAIL.description}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="statusFee">Status Fee</label>
                                            <input type="number" id="statusFee" name="statusFee" class="form-control"
                                                   value="${requestScope.PRODUCT_DETAIL.statusFee}">
                                        </div>
                                        <div class="form-group">
                                            <label for="contact">Contact</label>
                                            <input type="text" id="contact" name="contact" class="form-control"
                                                   value="${requestScope.PRODUCT_DETAIL.contact}">
                                        </div>
                                        <div class="form-group">
                                            <label for="hiddenContent">Hidden Content</label>
                                            <textarea id="hiddenContent" name="hiddenContent" class="form-control">${requestScope.PRODUCT_DETAIL.hiddenContent}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="statusProduct">Status Product</label>
                                            <select id="statusProduct" name="statusProduct" class="form-control">
                                                <option value="true" ${requestScope.PRODUCT_DETAIL.statusProduct == true ? 'selected' : ''}>Active</option>
                                                <option value="false" ${requestScope.PRODUCT_DETAIL.statusProduct == false ? 'selected' : ''}>Inactive</option>
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Save Changes</button>
                                    </form>
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
                        <span>Copyright &copy; Your Website 2021</span>
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

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

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
