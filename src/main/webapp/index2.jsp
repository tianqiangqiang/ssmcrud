<%--
  Created by IntelliJ IDEA.
  User: TianQiangQiang
  Date: 2017/08/20
  Time: 16:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>All Employee</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/static/js/jquery/jquery-3.2.1.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        var totalRecord;
        $(function () {
            toPage(1);

            $("#emp_add").on("click", function () {

                $.ajax({
                    url: "${ctx}/department/getAllDept",
                    type: "GET",
                    success: function (result) {
                        $("#inputDept").empty();
                        $.each(result.data.departments, function (index, item) {
                            $("#inputDept").append("<option value='" + item.deptId + "'>" + item.deptName + "</option>");
                        });
                    }
                });

                $("#empAdd").modal({
                    backdrop: 'static',
                    keyboard: false
                });

            });

            $("#save_btn").on("click", function () {
                var emp = $("#empAddForm").serialize();
                $.ajax({
                    url: "${ctx}/employee/addEmployee",
                    data: emp,
                    type: "POST",
                    success: function (result) {
                        $("#empAdd").modal("hide");
                        toPage(totalRecord);
                    }
                });
            });

        });

        function toPage(pageNumber) {
            $.ajax({
                url: "${ctx}/employee/getAllEmpWithJson?pageNumber=" + pageNumber,
                type: "GET",
                success: function (result) {
                    buildEmpTable(result);
                    buildPageInfo(result);
                    buildPageBar(result);
                }
            });
        }

        function buildEmpTable(result) {
            $("#empData").empty();
            var emps = result.data.pageInfo.list;
            $.each(emps, function (index, item) {
                var empIdTd = $("<td></td>").append(item.empId);
                var empNameTd = $("<td></td>").append(item.empName);
                var empGenderTd = $("<td></td>").append(item.gender);
                var empEmailTd = $("<td></td>").append(item.email);
                var empDeptTd = $("<td></td>").append(item.department.deptName);
                var operationTd = $("<td></td>").append('<button class="btn btn-primary btn-xs">编辑</button>&nbsp;<button class="btn btn-danger btn-xs">删除</button>');
                $("<tr></tr>").append(empIdTd).append(empNameTd).append(empGenderTd).append(empEmailTd).append(empDeptTd).append(operationTd).appendTo($("#empData"));
            });
        }

        function buildPageInfo(result) {
            var pageInfo = result.data.pageInfo;
            $("#currentPage").html(pageInfo.pageNum);
            $("#totalPage").html(pageInfo.pages);
            $("#totalRecord").html(pageInfo.total);
            totalRecord = result.data.pageInfo.total;
        }

        function buildPageBar(result) {
            var pagination = $(".pagination");
            pagination.empty();
            var addItem = "";
            if (result.data.pageInfo.pageNum == "1") {
                addItem += "<li class=\"disabled\"><span>首页<span class=\"sr-only\">(current)</span></span></li><li class=\"disabled\"><span><span aria-hidden=\"true\">&laquo;</span></span></li>";
            } else {
                addItem += "<li><a onclick='toPage(" + 1 + ")'>首页</a></li><li><a aria-label=\"Previous\" onclick='toPage(" + (result.data.pageInfo.pageNum - 1) + ")'><span aria-hidden=\"true\">&laquo;</span></a></li>";
            }
            $.each(result.data.pageInfo.navigatepageNums, function (index, item) {
                if (item == result.data.pageInfo.pageNum) {
                    addItem += "<li class='active'><span>" + item + "<span class='sr-only'>(current)</span></span></li>";
                } else {
                    addItem += "<li><a onclick='toPage(" + item + ")'>" + item + "</a></li>";
                }
            });
            if (result.data.pageInfo.pageNum == result.data.pageInfo.pages) {
                addItem += "<li class=\"disabled\"><span><span aria-hidden=\"true\">&raquo;</span></span></li><li class=\"disabled\"><span>末页<span class=\"sr-only\">(current)</span></span></li>";
            } else {
                addItem += "<li><a aria-label=\"Previous\" onclick='toPage(" + (result.data.pageInfo.pageNum + 1) + ")'><span aria-hidden=\"true\">&raquo;</span></a></li><li><a onclick='toPage(" + result.data.pageInfo.pages + ")'>末页</a></li>";
            }
            pagination.append(addItem);
        }
    </script>
</head>
<body>
<%--员工添加--%>
<div class="modal fade" id="empAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Add Employee</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="empAddForm">
                    <div class="form-group">
                        <label for="inputEmpName" class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="inputEmpName"
                                   placeholder="EmpName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="inlineRadio1" value="男" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="inlineRadio2" value="女"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="inputEmail" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDept" class="col-sm-2 control-label">Department</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputDept" name="deptId"></select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save_btn">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="close_btn">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="emp_add">新增</button>
            <button class="btn btn-danger" id="emp_delete">删除</button>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Department</th>
                    <th>Operation</th>
                </tr>
                </thead>
                <tbody id="empData"></tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <h3 style="color: #2b669a">当前第&nbsp;<span
                    id="currentPage"></span>&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;总共&nbsp;<span id="totalPage"></span>&nbsp;页&nbsp;<span
                    id="totalRecord"></span>&nbsp;条记录</h3>
        </div>
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination"></ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
