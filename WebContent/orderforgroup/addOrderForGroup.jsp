<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderforgroup.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.grouptour.model.*"%>


<%  
// 抓取新增失敗時回傳的VO
OderForGroupVO oderForGroupVO = (OderForGroupVO) request.getAttribute("oderForGroupVO");

//這東西應該會放在Session
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");	
GroupTourVO groupTourVO = (GroupTourVO) request.getAttribute("groupTourVO");
%>
<%= oderForGroupVO==null %><%-- 確認有沒有抓到用(可刪) --%>
<%= groupTourVO==null %><%-- 確認有沒有抓到用(可刪) --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add order for group</title>
<style>


</style>
</head>
<body>

<h2>${memberVO.nickName}，歡迎報名套裝行程</h2><bl>

<form method="post" action="<%=request.getContextPath()%>/orderforgroup/orderforgroup.do">
<table>

		

	<tr>
		<td>套裝行程名稱(列出訂購資訊)</td>	
		<td>${groupTourVO.tourName}</td>
	</tr>
	<tr>
		<td>總金額</td>	
		<td>${groupTourVO.price}</td>
	</tr>
	<tr>
		<td>連絡電話(手機) 預設帶入會員資料</td>	
		<td><input type="TEXT" name="phone" size="45" value="${memberVO.phone}" />
		
		</td>
	</tr>
	
	<tr>
		<td>訂購人(這個梅蘭未不會被get)</td>	
		<td><input type="TEXT" size="45" value="${memberVO.userName}" />
		</td>
	</tr>	
	
	<tr>
		<td>身分證字號</td>	
		<td><input type="TEXT" name="personID" size="45" value="${memberVO.personID}" />
		</td>
	</tr>
	<tr>
		<td>出生日期</td>	
		<td><input type="TEXT" class="date" name="birthdate" size="45" value="${memberVO.birthDate}" />
		</td>
	</tr>
	
	
</table>
<input type="hidden" name="userID" value="${memberVO.userID}">
<input type="hidden" name="groupTourSN" value="${groupTourVO.groupTourSN}">
<input type="hidden" name="totalPrice" value="${groupTourVO.price}">
<input type="hidden" name="action" value="insert">
<input type="submit" value="新增">
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
	       });

        
      });

</script>

</html>