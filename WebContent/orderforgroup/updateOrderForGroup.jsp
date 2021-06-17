<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderforgroup.model.*"%>

<%  
// 抓取新增失敗時回傳的VO
OderForGroupVO oderForGroupVO = (OderForGroupVO) request.getAttribute("oderForGroupVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Order For Group 後台修改訂單</title>
<style>


</style>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/orderforgroup/orderforgroup.do">
<table>
	<tr>
		<td>訂購人userID</td>	
		<td>${oderForGroupVO.userID}</td>
	</tr>
	<tr>
		<td>套裝行程SN名稱(列出訂購資訊)</td>	
		<td>${oderForGroupVO.groupTourSN}</td>
	</tr>
	<tr>
		<td>總金額</td>	
		<td><input type="TEXT" name="phone" size="45" value="${oderForGroupVO.totalPrice}" /></td>
	</tr>
	<tr>
		<td>購買日期</td>	
		<td><input type="TEXT" class="date" name="purchaseDate" size="45" value="${oderForGroupVO.purchaseDate}" /></td>
	</tr>
	<tr>
		<td>連絡電話</td>	
		<td><input type="TEXT" name="phone" size="45" value="${oderForGroupVO.phone}" /></td>
	</tr>
	<tr>
		<td>身分證字號</td>	
		<td><input type="TEXT" name="personID" size="45" value="${oderForGroupVO.userID}" /></td>
	</tr>
	<tr>
		<td>出生日期</td>	
		<td><input type="TEXT" class="date" name="birthdate" size="45" value="${oderForGroupVO.birthdate}" /></td>
	</tr>
</table>
	<input type="hidden" name="action" value="update">
	<input type="submit" value="修改">
</form>

<%-- 錯誤表列 --%>
<c:if test="${not empty errMsg}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errMsg}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>

	      window.addEventListener("DOMContentLoaded", function(){
	    	  
	// dateTimePicker
	$.datetimepicker.setLocale('zh');
     var today = new Date();
     $('.date').datetimepicker({
	       timepicker:false,
	       format:'Y-m-d',
		   beforeShowDay: function(date) {
       	  if (  date.getYear() <  today.getYear() || 
		       (date.getYear() == today.getYear() && date.getMonth() <  today.getMonth()) || 
		       (date.getYear() == today.getYear() && date.getMonth() == today.getMonth() && date.getDate() < today.getDate())
	          ) {
	               return [false, ""]
	          }
	          return [true, ""];
    	}});


        
      });

</script>
</html>