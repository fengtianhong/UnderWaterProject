<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.qa.model.*"%>

<%  // 抓取新增失敗時回傳的VO
	QaVO qaVO = (QaVO) request.getAttribute("qaVO");
%>
<%= qaVO==null %><%-- 確認有沒有抓到用(可刪) --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add QA</title>
<style>
	td{
		padding: 5px;
	}
	.col1{ 
 		width: 325px;  
 	} 

</style>
</head>
<body>

<div class="container" style="margin: 0 auto; width: 500px;">

<form method="post" action="qa.do">
<table>
	<h2>問與答新增</h2>
	<tr>
		<td>問題</td>	
		<td><input type="TEXT" name="question" class="col1" value="<%=(qaVO==null)?"":qaVO.getQuestion()%>" /></td>
	</tr>
	<tr>
		<td>解答</td>
		<td><textarea name="answer" class="col1" style="height: 50px;">${qaVO.answer}</textarea></td>
	</tr>
	<tr>
		<td>選單分類</td>
		<td><input type="TEXT" name="menu" class="col1" value="${qaVO.menu}" /></td>
	</tr>
	<tr>
		<td>選單子分類</td>
		<td><input type="TEXT" name="submenu" class="col1" value="${qaVO.submenu}" /></td>
	</tr>
	<tr>
		<td>系統分類</td>
		<td>
			<select name="system" class="col1">
				<option value="0" ${(qaVO.system==0)? 'selected':'' }>測試
				<option value="1" ${(qaVO.system==1)? 'selected':'' }>潛水地圖
				<option value="2" ${(qaVO.system==2)? 'selected':'' }>潛水團 (揪團/套裝行程)
				<option value="3" ${(qaVO.system==3)? 'selected':'' }>商城
				<option value="4" ${(qaVO.system==4)? 'selected':'' }>潛水分享
				<option value="5" ${(qaVO.system==5)? 'selected':'' }>會員相關
				<option value="6" ${(qaVO.system==6)? 'selected':'' }>其他
			</select>
		</td>
	</tr>
	<tr>
		<td>為熱門問題</td>
		<td>
			<div>
			    <input type="checkbox" name="popularQuestion" value="true" ${(qaVO.popularQuestion == false)?"":"checked"}>
			    <label for="popularQuestion"></label>
		  	</div>
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="新增">
<input type ="button" onclick="window.location.href='<%=request.getContextPath()%>/qa/backendList.jsp'" value="上一頁">


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

</div>

<%-- 成功Alert --%>
<c:if test="${not empty Msg}">
	<script>alert("${Msg}");</script>
</c:if>

</body>
</html>