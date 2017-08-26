<%--
  Created by IntelliJ IDEA.
  User: TianQiangQiang
  Date: 2017/08/16
  Time: 15:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>All Employee</title>
    <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="${ctx}/static/js/jquery/jquery-3.2.1.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Department</th>
                    <th>Operation</th>
                </tr>
                <c:forEach var="emp" items="${pageInfo.list}">
                    <tr>
                        <td>${emp.empId}</td>
                        <td>${emp.empName}</td>
                        <td>${emp.gender}</td>
                        <td>${emp.email}</td>
                        <td>${emp.department.deptName}</td>
                        <td>
                            <button class="btn btn-primary btn-xs">编辑</button>
                            <button class="btn btn-danger btn-xs">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <h3 style="color: #2b669a">当前第${pageInfo.pageNum}页&nbsp;&nbsp;总共${pageInfo.pages}页${pageInfo.total}条记录</h3>
        </div>
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${pageInfo.pageNum==1}">
                            <li class="disabled">
                                <span>首页<span class="sr-only">(current)</span></span>
                            </li>
                            <li class="disabled">
                                <span><span aria-hidden="true">&laquo;</span></span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${ctx}/listAllEmp?pageNumber=1">首页</a></li>
                            <li>
                                <a href="${ctx}/listAllEmp?pageNumber=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="npn">
                        <c:choose>
                            <c:when test="${npn==pageInfo.pageNum}">
                                <li class="active">
                                    <span>${npn}<span class="sr-only">(current)</span></span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${ctx}/listAllEmp?pageNumber=${npn}">${npn}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${pageInfo.pageNum==pageInfo.pages}">
                            <li class="disabled">
                                <span><span aria-hidden="true">&raquo;</span></span>
                            </li>
                            <li class="disabled">
                                <span>末页<span class="sr-only">(current)</span></span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="${ctx}/listAllEmp?pageNumber=${pageInfo.pageNum+1}" aria-label="Previous">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            <li><a href="${ctx}/listAllEmp?pageNumber=${pageInfo.pages}">末页</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
