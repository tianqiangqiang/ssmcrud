<%--
  Created by IntelliJ IDEA.
  User: TianQiangQiang
  Date: 2017/08/13
  Time: 13:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:forward page="${ctx}/listAllEmp" />
