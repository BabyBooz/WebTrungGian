<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detail</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <style>
            body {
                line-height: 1.6;
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }

            .card {
                border: none;
                border-radius: 0;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .card-header {
                background-color: #f0f3f5;
                border-bottom: none;
            }

            .card-body {
                padding: 1.5rem;
            }

            .form-label-horizontal {
                text-align: right;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
            }

            .form-control {
                padding: 0.5rem;
            }

            .form-control[readonly] {
                background-color: #f5f5f5;
            }

            .btn-secondary {
                background-color: red;
                border-color: red;
            }

            .btn-secondary:hover {
                background-color: #e5e5e5;
                border-color: #e5e5e5;
            }

            .btn-secondary:focus {
                box-shadow: none;
            }

            .btn-secondary a {
                color: #333;
                text-decoration: none;
            }
            .left-align {
                text-align: left;
            }

        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="card">
                <div class="card-header">
                    <h3 class="mb-0">Lịch sử trạng thái</h3>
                </div>
                <div class="card-body">
                    <form autocomplete="off">
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label text-md-right"><b>Thời gian</b></label>
                            <div class="col-md-9">
                                <input readonly type="text" class="form-control" value="${detail.created_date}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-md-3 col-form-label text-md-right"><b>Trạng thái</b></label>
                            <div class="col-md-9">
                                <input readonly type="text" class="form-control
                                       <c:if test="${detail.statusChecking == 1 || detail.statusChecking == 2 || detail.statusChecking == 3}">left-align</c:if>"
                                       value="<c:if test="${detail.statusChecking == 0}">Sẵn sàng giao dịch
                                       </c:if><c:if test="${detail.statusChecking == 1}">Bên mua đang kiểm tra hàng
                                       </c:if><c:if test="${detail.statusChecking == 2}">Bên mua khiếu nại sản phẩm
                                       </c:if><c:if test="${detail.statusChecking == 3}">Hoàn thành
                                       </c:if>">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-md-3 col-form-label text-md-right"><b>Mô tả</b></label>
                            <div class="col-md-9">
                                <input readonly type="text" class="form-control
                                       <c:if test="${detail.statusChecking == 1 || detail.statusChecking == 2 || detail.statusChecking == 3}">left-align</c:if>"
                                       value="<c:if test="${detail.statusChecking == 0}">Thu phí đăng yêu cầu
                                       </c:if><c:if test="${detail.statusChecking == 1}">Bên mua xem hàng
                                       </c:if><c:if test="${detail.statusChecking == 2}">Người mua khiếu nại đơn hàng
                                       </c:if><c:if test="${detail.statusChecking == 3}">Người mua xác nhận giao dịch thành công
                                       </c:if>">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-md-3 col-form-label text-md-right"><b>Người thực hiện</b></label>
                            <div class="col-md-9">
                                <div readonly="" class="left-align-text form-control">
                                    <span>
                                        <c:forEach items="${requestScope.accountList2}" var="ac">
                                            <c:if test="${detail.createdBy eq ac.id}">
                                                ${ac.user}
                                            </c:if>
                                        </c:forEach>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" style="font-size: 17px;">
                        <a href="history?id=${detail.productId}">Close</a>
                    </button>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSGFpoO/ufreqqF6MVu4JdG7PhIxZlW8sSJv43gkdSHlua9DmM/" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>