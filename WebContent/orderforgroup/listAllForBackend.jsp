<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderforgroup.model.*"%>

<%	
	OderForGroupService oderForGroupSvc = new OderForGroupService();
	List<OderForGroupVO> list = oderForGroupSvc.getAll();
	pageContext.setAttribute("list", list);	// WHY

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List All for back</title>
<style>
	table{
	  border: solid 1px black;
	  border-collapse: collapse;
	  border-radius: 20%;
	  width: 80%;
	  text-align: center;
	}
	table, th, td {
    border: solid 1px black;
 	}
 	
	.orederlist{
		margin: 20px;
		
		weight: 60%;
	}
	.title{
		border-bottom: 2px solid #000;
	}

</style>
</head>
<body>
		<table class="orederlist order-table">
			<tr>
				<th>訂單編號</th>
				<th>訂購人</th>
				<th>套裝行程編號</th>
				<th>購買日期</th>
				<th>總金額</th>
				<th>修改</th>
			</tr>
<%@ include file="page1.file" %> 			
<c:forEach var="oderForGroupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
			<tr>
				<td>${oderForGroupVO.orderSN}</td>
				<td>${oderForGroupVO.userID}</td>
				<td>${oderForGroupVO.groupTourSN}</td>
				<td><fmt:formatDate value="${oderForGroupVO.purchaseDate}" pattern="yyyy-MM-dd" /></td>
				<td>${oderForGroupVO.totalPrice}</td>
				<td>
					<form method="post" action="orderforgroup.do">
						<input type="hidden" name="orderSN" value="${oderForGroupVO.orderSN}">
						<input type="hidden" name="action" value="getOne_ForUpdate">
						<input type="submit" value="修改">
					</FORM>
				</td>
			</tr>
</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>