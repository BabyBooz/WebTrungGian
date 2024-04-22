
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">


        <style>
            body{
                line-height:1;

            }

            .card {
                border: none;
                border-radius: 0;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .card-header {
                padding: 1rem;
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

            .form-control:focus {
                box-shadow: none;
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
            }

            .left-align-text span {
                display: inline-block; /* Đảm bảo văn bản được căn lề */
                text-align: left; /* Căn lề văn bản về bên trái */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <h3 class="pull-left">Thông tin đơn trung gian </h3>
                </div>
                <div class="card-body">
                    <form autocomplete="off">
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Mã trung gian</b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <input readonly="" placeholder="" type="text" class="form-control" value="${productDetail.id}">
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class=""><b>Người bán </b></label>
                            </div>
                            <div class="col-md-9">
                                <div readonly="" class="left-align-text form-control">
                                    <span>
                                        <c:forEach items="${requestScope.accountList1}" var="ac">
                                            <c:if test="${productDetail.created_by eq ac.id}">
                                                ${ac.user}
                                            </c:if>
                                        </c:forEach>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Người mua </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <div class="input-group">
                                    <input readonly="" type="text" class="form-control" value="${productDetail.name}">
                                </div>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Trạng thái </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <div readonly="" class="left-align-text form-control">
                                    <span>
                                        <c:choose>                                           
                                            <c:when test="${productDetail.image == 1}">Mới tạo</c:when>
                                            <c:when test="${productDetail.image == 2}">Sẵn sàng giao dịch</c:when>
                                            <c:when test="${productDetail.image == 3}">Hủy</c:when>
                                            <c:when test="${productDetail.image == 4}">Bên mua đang kiểm tra hàng</c:when>
                                            <c:when test="${productDetail.image == 5}">Hoàn thành</c:when>
                                            <c:when test="${productDetail.image == 6}">Bên mua khiếu nại sản phẩm</c:when>
                                            <c:when test="${productDetail.image == 7}">Bên bán đánh dấu khiếu nại không đúng</c:when>
                                            <c:when test="${productDetail.image == 8}">Yêu cầu quản trị viên trung gian</c:when>
                                            <c:when test="${productDetail.image == 9}">Chờ bên mua khiếu nại không đúng</c:when>
                                        </c:choose>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Chủ đề trung gian</b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <textarea rows="2" placeholder="" readonly="" class="form-control">${productDetail.name}</textarea>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Giá tiền</b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <div class="input-group">
                                    <input readonly="" placeholder="Input number" type="text" class="form-control" value="${productDetail.price}">
                                    <!--                                    <div class="input-group-append">
                                                                            <span class="input-group-text">mười một</span>
                                                                        </div>-->
                                </div>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Bên chịu phí trung gian</b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <div readonly="" class="left-align-text form-control">
                                    <span>
                                        <c:choose>
                                            <c:when test="${productDetail.status_fee == 0}">Bên Mua</c:when>
                                            <c:when test="${productDetail.status_fee == 1}">Bên Bán</c:when>
                                        </c:choose>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Phí trung gian </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <div class="input-group">
                                    <input readonly="" placeholder="Input number" type="text" class="form-control" value="${productDetail.price*(1/10)}">
                                    <!--                                    <div class="input-group-append">
                                                                            <span class="input-group-text">phẩy năm năm</span>
                                                                        </div>-->
                                </div>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Tổng tiền bên mua cần thanh toán </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <div readonly="" class="left-align-text form-control">
                                    <span>
                                        <c:choose>
                                            <c:when test="${productDetail.status_fee == 0}">
                                                ${productDetail.price+(productDetail.price*(1/10))} 
                                            </c:when>
                                            <c:when test="${productDetail.status_fee == 1}">
                                                ${productDetail.price} 
                                            </c:when>
                                            <c:otherwise>Unknown</c:otherwise>
                                        </c:choose>
                                        <!--                                    <div class="input-group-append">
                                                                                <span class="input-group-text">mười một phẩy năm năm</span>
                                                                            </div>-->
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Tiền bên bán thực nhận </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <div readonly="" class="left-align-text form-control">
                                    <span><c:choose>
                                            <c:when test="${productDetail.status_fee == 0}">
                                                ${productDetail.price}
                                            </c:when>
                                            <c:when test="${productDetail.status_fee == 1}">
                                                ${productDetail.price - (productDetail.price*(1/10))} 
                                            </c:when>
                                            <c:otherwise>Unknown</c:otherwise>
                                        </c:choose>
                                        <!--                                    <div class="input-group-append">
                                                                                <span class="input-group-text">mười một</span>
                                                                            </div>-->
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Mô tả </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <textarea rows="2" placeholder="" readonly="" class="form-control">${productDetail.description}</textarea>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Phương thức liên hệ </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <textarea rows="2" readonly="" class="form-control">${productDetail.contact}</textarea>
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Nội dung ẩn </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <textarea rows="2" placeholder="" readonly="" class="form-control">${productDetail.hidden_content}</textarea>
                            </div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Thời gian tạo </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <input readonly="" type="text" class="form-control" value="${productDetail.created_date}">
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Cập nhật cuối </b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <input readonly="" type="text" class="form-control" value="${productDetail.updated_date}">
                            </div>
                        </div>
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3">
                                <label class="">
                                    <b>Link chia sẻ đơn trung gian</b>
                                </label>
                            </div>
                            <div class="col-md-9">
                                <input readonly="" type="text" class="form-control" value="https://www.facebook.com/quockhanh.pham.saf2391">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary">
                        <a href="/swp/mysale" style="color: white; text-decoration: unset"> Close </a>
                    </button>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSGFpoO/ufreqqF6MVu4JdG7PhIxZlW8sSJv43gkdSHlua9DmM/" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>