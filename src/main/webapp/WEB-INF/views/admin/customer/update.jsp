<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Chỉnh sửa khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form:form modelAttribute="customers" action="/api/customer" id="formEdit" method="GET" class="form-horizontal" role="form">
                        <input type="hidden" id="id" class="form-control" name="id" value= "${customers.id}"/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="fullname">Tên đầy đủ</label>
                            <div class="col-sm-9">
                                <input type="text" id="fullname" class="form-control" name="fullname"
                                       value="${customers.fullname}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="phone">Số điện thoại</label>
                            <div class="col-sm-9">
                                <input type="text" id="phone" class="form-control" name="phone"
                                       value="${customers.phone}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="email">Email</label>
                            <div class="col-sm-9">
                                <input type="text" id="email" class="form-control" name="email"
                                       value="${customers.email}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="companyName">Tên công ty</label>
                            <div class="col-sm-9">
                                <input type="text" id="companyName" class="form-control" name="companyName"
                                       value="${customers.companyName}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="demand">Nhu cầu</label>
                            <div class="col-sm-9">
                                <input type="text" id="demand" class="form-control" name="demand"
                                       value="${customers.demand}"/>
                            </div>
                        </div>

                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-3 control-label no-padding-right" for="">Ghi chú</label>--%>
                            <%--<div class="col-sm-9">--%>
                                <%--<input type="number" id="" class="form-control" name=""--%>
                                       <%--value="${customers.}"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"></label>

                            <div class="col-sm-9">
                                <button type="button" class="btn btn-primary" id="btnAddCustomer">
                                    <c:if test="${customers.id != null}">
                                        Cập nhật khách hàng
                                    </c:if>
                                    <c:if test="${customers.id == null}">
                                        Thêm khách hàng
                                    </c:if>
                                </button>
                                <button type="button" class="btn btn-primary">
                                    <a href="/admin/customer-list" style="color: white;">
                                        Hủy
                                    </a>
                                </button>
                            </div>
                        </div>

                    </form:form>
                </div>
            </div><!-- /.row -->

            <c:forEach var="item" items="${transactionCodes}">
                <form:form modelAttribute="customers" action="/api/transaction" id="formEditTransaction_${item.key}" method="GET" class="form-horizontal" role="form">
                    <div class="row" style="margin-top: 40px; ">
                        <div class="col-xs-12">
                            <div class="widget-box">
                                <div class="widget-header">
                                    <h4 class="widget-title">
                                            ${item.value}
                                                <input type="hidden" id="code" class="form-control" name="code" value="${item.value}"/>
                                                <input type="hidden" id="customerId" class="form-control" name="customerId" value="${customers.id}"/>
                                        <Button class="btn btn-white btn-infor btn-bold" data-toggle="tooltip" title="Thêm giao dịch" type="button" id="btnAddTransaction_${item.key}">
                                                <i class="fa fa-plus-circle"></i>
                                        </Button>
                                    </h4>

                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main">
                                        <table id="buildingList" class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>Ngày tạo</th>
                                                <th>Ghi chú</th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <c:forEach var="itemTransaction" items="${transaction}">
                                                <c:if test="${item.value == itemTransaction.code}">
                                                    <tr>
                                                        <td>${itemTransaction.modifiedDate}</td>
                                                        <td>${itemTransaction.note}</td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                            <tr>
                                                <td></td>
                                                <td>
                                                    <input type="text" id="note" class="form-control" name="note"
                                                           value=""/>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div><!-- /.row -->
                </form:form>

            </c:forEach>


        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<script>

    $('#btnAddCustomer').click(function (e) {
        e.preventDefault();
        //call api add building
        var data = {};
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {
            if(v.value != ''){
                data[""+v.name+""] = v.value;
            }else {
                data[""+v.name+""] = null;
            }
        });
        var ull = "${customerAPI}";

        if (data['id'] != null){
            ull = ull + "/" + data['id'];
            var type = 'PUT';
        }
        else {
            var type = 'POST';
        }
        $.ajax({
            type: type,
            url: ull,
            data: JSON.stringify(data),
            dataType: "json", //Dữ liệu từ
            contentType: "application/json",
            success: function (respond) {
                console.log('success');
                location.reload();
                if (type == "PUT") {
                    alert("Cập nhập khách hàng thành công!");
                } else {
                    alert("Thêm khách hàng thành công!")
                }
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
                location.reload();
                if (type == "PUT") {
                    alert("Cập nhập khách hàng thất bại!");
                } else {
                    alert("Thêm khách hàng thất bại!")
                }

            },
        });
    });

    <c:forEach var="item" items="${transactionCodes}">
    $('#btnAddTransaction_${item.key}').click(function (e) {
        e.preventDefault();
        //call api add building
        var data = {};
        var formDataTransaction = $('#formEditTransaction_${item.key}').serializeArray();
        $.each(formDataTransaction, function (index, v) {
            if(v.value != ''){
                data[""+v.name+""] = v.value;
            }else {
                data[""+v.name+""] = null;
            }
        });
        <%--data['code'] = ${item.key};--%>
        var ulll = "${customerAPI}/" + data['customerId'];

        $.ajax({
            type: "POST",
            url: ulll,
            data: JSON.stringify(data),
            dataType: "json", //Dữ liệu từ
            contentType: "application/json",
            success: function (respond) {
                console.log('success');
                location.reload();
                alert("Thêm giao dịch thành công!");
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
                location.reload();
                alert("Thêm giao dịch thất bại!");

            },
        });
    });
    </c:forEach>
</script>


</body>
</html>
