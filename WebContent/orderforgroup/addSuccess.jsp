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
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<style>
	.main-container{
		text-align: center;
		margin: 0 auto;
		width: 900px;
		/* 		display: flex; */
		background-color: snow;
		border-radius: 10px;
		box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.4);
		padding: 30px;
		opacity: .9;
		text-align
	}

</style>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<div class="main-container">
	<h2>套裝行程已報名成功!</h2>
	<hr>
    <input class="btn btn-primary btn-user" type ="button" onclick="window.location.href='<%=request.getContextPath()%>/grouptour/frontendListAll.jsp'" value="返回套裝行程列表">
    <input class="btn btn-primary btn-user" type ="button" onclick="window.location.href='<%=request.getContextPath()%>/grouptour/listByUserID.jsp'" value="查看個人訂單(NOTYET)">
</div>


<%-- 成功Alert --%>
<c:if test="${not empty Msg}">
	<script>
		alert("${Msg}");
	</script>
</c:if>
</body>

<jsp:include page="../share/footer.jsp" flush="true" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>

</script>
</html>