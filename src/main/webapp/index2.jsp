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
                $("#empAddForm")[0].reset();
                $("#inputEmpName").parent().removeClass("has-success has-error");
                $("#inputEmpName").next("span").text("");
                $("#inputEmail").parent().removeClass("has-success has-error");
                $("#inputEmail").next("span").text("");

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
                if (!validate_addEmployee()) {
                    return false;
                }
                var emp = $("#empAddForm").serialize();
                $.ajax({
                    url: "${ctx}/employee",
                    data: emp,
                    type: "POST",
                    success: function (result) {
                        if (result.statusCode == 1) {
                            $("#empAdd").modal("hide");
                            toPage(totalRecord);
                        } else {
                            if (result.data.map.empName != undefined) {
                                showValidateMessage("#inputEmpName", "error", result.data.map.empName);
                            } else {
                                showValidateMessage("#inputEmpName", "success", "");
                            }
                            if (result.data.map.email != undefined) {
                                showValidateMessage("#inputEmail", "error", result.data.map.email);
                            } else {
                                showValidateMessage("#inputEmail", "success", "");
                            }
                        }
                    }
                });
            });

        });

        function toPage(pageNumber) {
            $.ajax({
                url: "${ctx}/getAllEmpWithJson?pageNumber=" + pageNumber,
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

        function validate_addEmployee() {
            var emp_name = $("#inputEmpName").val().trim();
            var emp_email = $("#inputEmail").val().trim();
            var name_regex = /^[\u4E00-\u9FA5A-Za-z]{2,10}$/;
            var email_regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if (emp_name.match(name_regex) == null) {
                showValidateMessage("#inputEmpName", "error", "用户名为3-10位中文或英文!");
                return false;
            } else {
                var flag = false;
                $.ajax({
                    url: "${ctx}/validateEmpName?empName=" + emp_name,
                    type: "POST",
                    async: false,
                    success: function (result) {
                        if (result.statusCode == 1) {
                            flag = true;
                        }
                    }
                });
                if (!flag) {
                    showValidateMessage("#inputEmpName", "error", "用户名已存在!");
                    return false;
                }
                showValidateMessage("#inputEmpName", "success", "");
            }
            if (emp_email.match(email_regex) == null) {
                showValidateMessage("#inputEmail", "error", "请检查邮箱格式!");
                return false;
            } else {
                showValidateMessage("#inputEmail", "success", "");
            }
            return true;
        }

        function showValidateMessage(element, status, message) {
            $(element).parent().removeClass("has-success has-error");
            if (status == "success") {
                $(element).parent().addClass("has-success");
                $(element).next("span").text(message);
            } else {
                $(element).parent().addClass("has-error");
                $(element).next("span").text(message);
            }
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
                            <span class="help-block"></span>
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
                            <span class="help-block"></span>
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
