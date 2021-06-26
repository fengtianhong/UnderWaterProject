<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderforgroup.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.grouptour.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add order for group</title>
<style>


</style>
</head>
<body>

<div class="container">
	<h2>套裝行程已報名成功!</h2>
	<hr>
    <input class="btn btn-primary btn-user" type ="button" onclick="window.location.href='<%=request.getContextPath()%>/grouptour/frontendListALL.jsp'" value="返回套裝行程列表">
    <input class="btn btn-primary btn-user" type ="button" onclick="window.location.href='<%=request.getContextPath()%>/grouptour/listByUserID.jsp'" value="查看個人訂單(NOTYET)">
</div>


<%-- 成功Alert --%>
<c:if test="${not empty Msg}">
	<script>
		alert("${Msg}");
	</script>
</c:if>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>

</script>
</html>