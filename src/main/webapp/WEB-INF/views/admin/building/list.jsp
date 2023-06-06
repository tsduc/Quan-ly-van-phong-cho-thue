<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="loadStaffAPI" value="/api/building"/>
<c:url var="assignmentbuildingAPI" value="/api/assignmentbuilding"/>
<%@ page import="com.laptrinhjavaweb.security.utils.SecurityUtils"%>

<c:url var="buildingListURL" value="/admin/building-list"/>
<html>
<head>
    <title>Danh Sách Tòa Nhà</title>
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
                                <form:form modelAttribute="modelSearch" action="${buildingListURL}" id="listForm"
                                           method="GET">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="name">Tên toà nhà</label>
                                                    <form:input path="name" cssClass="form-control" name="name"
                                                                value="${modelSearch.name}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="floorArea">Diện tích sàn</label>
                                                    <input type="number" id="floorArea" class="form-control"
                                                           name="floorArea" value="${modelSearch.floorArea}"/>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>


                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="district">Quận hiện có</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="" label="---Chọn Quận---"/>
                                                        <form:options value="${districts.get(0)}" items="${districts}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="ward">Phường</label>
                                                    <form:input path="ward" cssClass="form-control" name="ward"
                                                                value="${modelSearch.ward}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="street">Đường</label>
                                                    <form:input path="street" cssClass="form-control" name="street"
                                                                value="${modelSearch.street}"/>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>


                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="numberOfBasement">Số tầng hầm</label>
                                                    <input type="number" id="numberOfBasement"
                                                           class="form-control" name="numberOfBasement"
                                                           value="${modelSearch.numberOfBasement}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="direction">Hướng</label>
                                                    <input type="text" id="direction" class="form-control"
                                                           name="direction" value="${modelSearch.direction}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="level">Hạng</label>
                                                    <input type="number" id="level" class="form-control" name="level"
                                                           value="${modelSearch.level}"/>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaFrom">Diện tích từ</label>
                                                    <input type="number" id="rentAreaFrom" class="form-control"
                                                           name="rentAreaFrom" value="${modelSearch.rentAreaFrom}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaTo">Diện tích đến</label>
                                                    <input type="number" id="rentAreaTo" class="form-control"
                                                           name="rentAreaTo" value="${modelSearch.rentAreaTo}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPirceFrom">Giá thuê từ</label>
                                                    <input type="number" id="rentPirceFrom" class="form-control"
                                                           name="rentAreaTo" value="${modelSearch.rentPirceFrom}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPirceTo">Giá thuê đến</label>
                                                    <input type="number" id="rentPirceTo" class="form-control"
                                                           name="rentPirceTo" value="${modelSearch.rentPirceTo}"/>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="managerName">Tên quản lý</label>
                                                    <input type="text" id="managerName" class="form-control"
                                                           name="managerName" value="${modelSearch.managerName}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="managerPhone">Điện thoại quản lý</label>
                                                    <input type="number" id="managerPhone" class="form-control"
                                                           name="managerPhone" value="${modelSearch.managerPhone}"/>
                                                </div>
                                            </div>
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
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="checkbox" style="display: flex; gap: 20px; padding-left: 12px">
                                                <form:checkboxes style="margin: 4px 0 0 0;" path="buildingTypes"  items="${buildingTypes}" name="buildingTypes" id="buildingTypes" checked= "" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-success" id="btnSearch" style="margin-left: 12px;">Tìm
                                                kiếm <i class='fa fa-arrow-right'></i></button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>


                    <div class="pull-right">
                        <security:authorize access="hasRole('MANAGER')">
                            <Button class="btn btn-white btn-infor btn-bold" data-toggle="tooltip"
                                    title="Thêm tòa nhà">
                                <a href="<c:url value="/admin/building-update"/>">
                                    <i class="fa fa-plus-circle"></i>
                                </a>
                            </Button>
                        </security:authorize>

                        <Button class="btn btn-white btn-warning btn-bold" data-toggle="tooltip"
                                title="Xóa tòa nhà" id="btnDeleteBuilding">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </Button>
                    </div>
                </div>

            </div><!-- /.row -->
            <br>

            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <display:table name="buildings.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${buildingListURL}" partialList="true" sort="external"
                                       size="${buildings.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="buildingList" pagesize="${buildings.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <fieldset>
                                    <input type="checkbox" name="checkList" value="${buildingList.id}"
                                           id="checkbox_${buildingList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>

                            <display:column headerClass="text-left" property="name" title="Tên sản phẩm"/>
                            <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                            <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                            <display:column headerClass="text-left" property="managerPhone" title="Số điện thoại"/>
                            <display:column headerClass="text-left" property="floorArea" title="Diện tích sàn"/>
                            <display:column headerClass="text-left" property="rentprice" title="Giá thuê"/>
                            <display:column headerClass="text-left" property="servicefee" title="Phí dịch vụ"/>
                            <display:column headerClass="col-actions" title="Thao tác">

                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-info " data-toggle="tooltip"
                                            title="Giao toa nha" onclick="assignmentBuilding(${buildingList.id})">
                                        <i class="ace-icon fa fa-user bigger-120"></i>
                                    </button>
                                </security:authorize>

                                <button class="btn btn-xs btn-info " data-toggle="tooltip"
                                        title="Sửa tòa nhà">
                                    <a href="<c:url value="/admin/building-update-${buildingList.id}"/>">
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
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
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
                <input type="hidden" name="buildingId" id="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao toa nha</button>

                <button type="button" class="btn btn-default" data-dismiss="modal">Dong lai</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentBuilding(buildingId) {
        openModalAssignmentBuilding();

        $('#buildingId').val(buildingId);
        var buildingID = buildingId;
        loadStaff($('#buildingId').val());
    }

    function loadStaff(buildingId) {
        var ull = "${loadStaffAPI}/" + buildingId + "/staffs";
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
                    <%--<td><input type="checkbox" value="${item.id}" id="checkbox_1"></td>--%>
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

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};
        data['buildingId'] = $('#buildingId').val();
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
            url: '${assignmentbuildingAPI}',
            data: JSON.stringify(data),
            dataType: "json", //Dữ liệu từ
            contentType: "application/json",
            success: function (respond) {
                console.log('success');
                location.reload();
                alert("Giao tòa nhà thành công!");
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
                location.reload();
                alert("Giao tòa nhà thất bại!");
            },
        });
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = buildingIds;
        deleteBuilding(data);

    });

    function deleteBuilding(data) {
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
                alert("Xóa tòa nhà thành công!");
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
                location.reload();
                alert("Xóa tòa nhà thất bại!");
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
