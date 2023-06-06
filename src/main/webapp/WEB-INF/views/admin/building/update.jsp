<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Chỉnh sửa tòa nhà</title>
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
                    <form:form modelAttribute="buildings" action="/api/building" id="formEdit" method="GET" class="form-horizontal" role="form">
                        <input type="hidden" id="id" class="form-control" name="id" value= "${buildings.id}"/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name">Tên tòa nhà</label>
                            <div class="col-sm-9">
                                <input type="text" id="name" class="form-control" name="name"
                                       value="${buildings.name}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                            <div class="col-sm-9">
                                <c:if test="${not empty buildings.avatar}">
                                    <c:set var="imagePath" value="/repository${buildings.avatar}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                    <input type="hidden" id="avatar" value="${buildings.avatar}" name="avatar">
                                </c:if>
                                <c:if test="${empty buildings.avatar}">
                                    <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">

                            <label class="col-sm-3 control-label no-padding-right" for="district">Quận hiện có</label>
                            <form:select path="district" class="col-sm-9">
                                <form:option value="" label="---Chọn Quận---" />
                                <form:options value="${districts.get(0)}" items="${districts}"/>
                            </form:select>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="ward">Phường</label>
                            <div class="col-sm-9">
                                <input type="text" id="ward" class="form-control" name="ward"
                                       value="${buildings.ward}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="street">Đường</label>
                            <div class="col-sm-9">
                                <input type="text" id="street" class="form-control" name="street"
                                       value="${buildings.street}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="structure">Kết cấu</label>
                            <div class="col-sm-9">
                                <input type="text" id="structure" class="form-control" name="structure"
                                       value="${buildings.structure}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement">Số tầng
                                hầm</label>
                            <div class="col-sm-9">
                                <input type="number" id="numberOfBasement" class="form-control" name="numberOfBasement"
                                       value="${buildings.numberOfBasement}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="floorArea">Diện tích sàn</label>
                            <div class="col-sm-9">
                                <input type="number" id="floorArea" class="form-control" name="floorArea"
                                       value="${buildings.floorArea}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="direction">Hướng</label>
                            <div class="col-sm-9">
                                <input type="text" id="direction" class="form-control" name="direction"
                                       value="${buildings.direction}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="level">Hạng</label>
                            <div class="col-sm-9">
                                <input type="text" id="level" class="form-control" name="level"
                                       value="${buildings.level}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Diện tích thuê</label>
                            <div class="col-sm-9">
                                <input type="text" id="rentAreas" class="form-control" name="rentAreas"
                                       value="${buildings.rentAreas}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentpricedescription">Mô tả giá
                                thuê</label>
                            <div class="col-sm-9">
                                <input type="text" id="rentpricedescription" class="form-control"
                                       name="rentpricedescription" value="${buildings.rentpricedescription}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="servicefee">Phí dịch vụ</label>
                            <div class="col-sm-9">
                                <input type="text" id="servicefee" class="form-control" name="servicefee"
                                       value="${buildings.servicefee}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="carfee">Phí ô tô</label>
                            <div class="col-sm-9">
                                <input type="text" id="carfee" class="form-control" name="carfee"
                                       value="${buildings.carfee}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="motorbikefee">Phí mô tô</label>
                            <div class="col-sm-9">
                                <input type="text" id="motorbikefee" class="form-control" name="motorbikefee"
                                       value="${buildings.motorbikefee}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="overtimefee">Phí ngoài
                                giờ</label>
                            <div class="col-sm-9">
                                <input type="text" id="overtimefee" class="form-control" name="overtimefee"
                                       value="${buildings.overtimefee}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="waterfee">Tiền nước</label>
                            <div class="col-sm-9">
                                <input type="text" id="waterfee" class="form-control" name="waterfee"
                                       value="${buildings.waterfee}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="electricityfee">Tiền
                                điện</label>
                            <div class="col-sm-9">
                                <input type="text" id="electricityfee" class="form-control" name="electricityfee"
                                       value="${buildings.electricityfee}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="deposit">Đặt cọc</label>
                            <div class="col-sm-9">
                                <input type="text" id="deposit" class="form-control" name="deposit"
                                       value="${buildings.deposit}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="payment">Thanh toán</label>
                            <div class="col-sm-9">
                                <input type="text" id="payment" class="form-control" name="payment"
                                       value="${buildings.payment}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="renttime">Thời hạn thuê</label>
                            <div class="col-sm-9">
                                <input type="text" id="renttime" class="form-control" name="renttime"
                                       value="${buildings.renttime}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="decorationtime">Thời gian trang
                                trí</label>
                            <div class="col-sm-9">
                                <input type="text" id="decorationtime" class="form-control" name="decorationtime"
                                       value="${buildings.decorationtime}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="brokeragefee">Phí môi
                                giới</label>
                            <div class="col-sm-9">
                                <input type="text" id="brokeragefee" class="form-control" name="brokeragefee"
                                       value="${buildings.brokeragefee}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerName">Tên quản lý</label>
                            <div class="col-sm-9">
                                <input type="text" id="managerName" class="form-control" name="managerName"
                                       value="${buildings.managerName}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerPhone">Số điện thoại quản
                                lý</label>
                            <div class="col-sm-9">
                                <input type="text" id="managerPhone" class="form-control" name="managerPhone"
                                       value="${buildings.managerPhone}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại tòa nhà</label>
                            <div class="col-sm-9">
                                <div class="checkbox">
                                    <label class="checkbox-inline" style="display: flex; gap: 20px; padding-left: 0">
                                        <form:checkboxes style="margin: 4px 0 0 0;" path="type" items="${buildingTypes}" name="type" id="type" checked= "" />
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"></label>

                            <div class="col-sm-9">
                                <button type="button" class="btn btn-primary" id="btnAddBuilding">
                                    <c:if test="${buildings.id != null}">
                                        Sửa tòa nhà
                                    </c:if>
                                    <c:if test="${buildings.id == null}">
                                        Thêm tòa nhà
                                    </c:if>
                                </button>
                                <button type="button" class="btn btn-primary">
                                    <a href="/admin/building-list" style="color: white;">
                                        Hủy
                                    </a>
                                </button>
                            </div>
                        </div>

                    </form:form>
                </div>

            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<script>

    var imageBase64 = '';
    var imageName = '';

    $('#btnAddBuilding').click(function (e) {
        e.preventDefault();
        //call api add building
        var data = {};
        var buildingTypes = [];
        var rentAreas = [];
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {
            if(v.name == 'type'){
                buildingTypes.push(v.value);
            }
            if(v.name != 'type'){
                if(v.value != ''){
                    data[""+v.name+""] = v.value;
                }else {
                    data[""+v.name+""] = null;
                }
            }
            if (imageBase64 !== '') {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        data['type'] = buildingTypes;
        var ull = "${buildingAPI}";

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
                    alert("Sửa tòa nhà thành công!");
                } else {
                    alert("Thêm tòa nhà thành công!")
                }
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
                location.reload();
                if (type == "PUT") {
                    alert("Sửa tòa nhà thất bại!");
                } else {
                    alert("Thêm tòa nhà thất bại!")
                }

            },
        });
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>


</body>
</html>
