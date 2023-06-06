<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="loadStaffAPI" value="/api/customer"/>
<c:url var="assignmentcustomerAPI" value="/api/assignmentcustomer"/>
<%@ page import="com.laptrinhjavaweb.security.utils.SecurityUtils"%>

<c:url var="customerListURL" value="/admin/customer-list"/>
<html>
<head>
    <title>Danh Sách Khách Hàng</title>
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
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Tìm kiếm</h4>

                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form:form modelAttribute="modelSearch" action="${customerListURL}" id="listForm"
                                       method="GET">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="fullname">Tên khách hàng</label>
                                                <form:input path="fullname" cssClass="form-control" name="fullname"
                                                            value="${modelSearch.fullname}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="phone">Di động</label>
                                                <form:input path="phone" cssClass="form-control" name="phone"
                                                            value="${modelSearch.phone}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="email">Enail</label>
                                                <form:input path="email" cssClass="form-control" name="email"
                                                            value="${modelSearch.email}"/>
                                            </div>
                                        </div>
                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <security:authorize access="hasRole('MANAGER')">
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="staffId">Chọn nhân viên phụ trách</label>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value=""
                                                                     label="---Chọn nhân viên phụ trách---"/>
                                                        <form:options value="${staffmaps.get(1)}"
                                                                      items="${staffmaps}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </security:authorize>
                                        <security:authorize access="hasRole('STAFF')">
                                            <div class="col-sm-4">
                                                <div>
                                                    <input type="hidden" id="staffId" name="staffId" value="<%=SecurityUtils.getPrincipal().getId()%>"/>
                                                </div>
                                            </div>
                                        </security:authorize>
                                        <div class="col-sm-8">
                                            <div>
                                                <button style="float: right; margin-top: 20px" type="button" class="btn btn-success" id="btnSearch">Tìm
                                                    kiếm <i class='fa fa-arrow-right'></i></button>
                                            </div>
                                        </div>
                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>

                <div class="pull-right">
                    <security:authorize access="hasRole('MANAGER')">
                        <Button class="btn btn-white btn-infor btn-bold" data-toggle="tooltip"
                                title="Thêm khách hàng">
                            <a href="<c:url value="/admin/customer-update"/>">
                                <i class="fa fa-plus-circle"></i>
                            </a>
                        </Button>
                    </security:authorize>

                    <Button class="btn btn-white btn-warning btn-bold" data-toggle="tooltip"
                            title="Xóa khách hàng" id="btnDeleteCustomer">
                        <i class="fa fa-trash" aria-hidden="true"></i>
                    </Button>
                </div>
            </div>

        </div><!-- /.row -->
            <br>

            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <display:table name="customers.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${customerListURL}" partialList="true" sort="external"
                                       size="${customers.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="customerList" pagesize="${customers.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <fieldset>
                                    <input type="checkbox" name="checkList" value="${customerList.id}"
                                           id="checkbox_${customerList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>

                            <display:column headerClass="text-left" property="fullname" title="Tên"/>
                            <display:column headerClass="text-left" property="staffName" title="Nhân viên quản lý"/>
                            <display:column headerClass="text-left" property="phone" title="Di động"/>
                            <display:column headerClass="text-left" property="email" title="Email"/>
                            <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                            <display:column headerClass="text-left" property="modifiedBy" title="Người nhập"/>
                            <display:column headerClass="text-left" property="modifiedDate" title="Ngày nhập"/>
                            <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-info " data-toggle="tooltip"
                                            title="Giao khách hàng" onclick="assignmentCustomer(${customerList.id})">
                                        <i class="ace-icon fa fa-user bigger-120"></i>
                                    </button>
                                </security:authorize>


                                <button class="btn btn-xs btn-info " data-toggle="tooltip"
                                        title="Sửa tòa nhà">
                                    <a href="<c:url value="/admin/customer-update-${customerList.id}"/>">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                </button>
                            </display:column>
                        </display:table>
                    </div>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<!-- Modal -->
<div class="modal fade" id="assignmentCustomerModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên giao Khách hàng</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" name="customerId" id="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignCustomer">Giao khách hàng</button>

                <button type="button" class="btn btn-default" data-dismiss="modal">Dong lai</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentCustomer(customerId) {
        openModalAssignmentCustomer();

        $('#customerId').val(customerId);
        var customerID = customerId;
        loadStaff($('#customerId').val());
    }

    function loadStaff(customerId) {
        var ull = "${loadStaffAPI}/" + customerId + "/staffs";
        $.ajax({
            type: "GET",
            url: ull,
            dataType: "json", //Dữ liệu từ
            success: function (respond) {
                console.log('success');
                var row = '';
                $.each(respond.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value="' + item.staffId + '" id="checkbox_' + item.staffId + '" class="check-box-element" ' + item.checked + '></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>'
                })
                $('#staffList tbody').html(row);
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
            },
        });
    }

    function openModalAssignmentCustomer() {
        $('#assignmentCustomerModal').modal();
    }

    $('#btnAssignCustomer').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};
        data['customerId'] = $('#customerId').val();
        //$('#staffList').find('tbody input[type=checkbox]');
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        assignStaff(data);
    });

    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: '${assignmentcustomerAPI}',
            data: JSON.stringify(data),
            dataType: "json", //Dữ liệu từ
            contentType: "application/json",
            success: function (respond) {
                console.log('success');
                location.reload();
                alert("Giao khách hàng thành công!");
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
                location.reload();
                alert("Giao khách hàng thất bại!");
            },
        });
    }

    $('#btnDeleteCustomer').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};
        var customerIds = $('#customerList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = customerIds;
        deleteCustomer(data);

    });

    function deleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            // url: "http://localhost:8080/api/building",
            url: '${loadStaffAPI}',
            data: JSON.stringify(data),
            dataType: "json", //Dữ liệu từ
            contentType: "application/json",
            success: function (respond) {
                console.log('success');
                location.reload();
                alert("Xóa khách hàng thành công!");
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
                location.reload();
                alert("Xóa khách hàng thất bại!");
            },
        });
    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        //call api
        $('#listForm').submit();

    });
</script>
</body>
</html>
