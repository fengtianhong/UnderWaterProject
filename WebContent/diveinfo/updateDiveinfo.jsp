<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diveinfo.model.*"%>

<%
	DiveInfoVO diveinfoVO = (DiveInfoVO) request.getAttribute("diveinfoVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>更新此頁面</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="diveinfo.do" name="form1">
		<table>
			<tr>
				<td>潛點編號:<font color=red><b>*</b></font></td>
				<td><%=diveinfoVO.getPointSN()%></td>
			</tr>
			<tr>
				<td>潛點名稱:</td>
				<td><input type="TEXT" name="pointname" size="45"
					value="<%=diveinfoVO.getPointName()%>" /></td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="latitude" size="45"
					value="<%=diveinfoVO.getLatitude()%>" /></td>
			</tr>
			<tr>
				<td>經度:</td>
				<td><input type="TEXT" name="longitude" size="45"
					value="<%=diveinfoVO.getLongitude()%>" /></td>
			</tr>
			<tr>
				<td>簡介:</td>
				<td><input type="TEXT" name="view" size="45"
					value="<%=diveinfoVO.getView()%>" /></td>
			</tr>
			<tr>
				<td>詳細介紹:</td>
				<td><input type="TEXT" name="introduction" size="45"
					value="<%=diveinfoVO.getIntroduction()%>" /></td>
			</tr>
			<tr>
				<td>季節:</td>
				<td><input type="TEXT" name="season" size="45"
					value="<%=diveinfoVO.getSeason()%>" /></td>
			</tr>
			<tr>
				<td>圖片:</td>
				<td><img
					src="<%=request.getContextPath()%>/diveinfo/ShowPic?pointSN=${diveinfoVO.pointSN}">
				</td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="pic" size="45"
					value="<%=diveinfoVO.getLocal()%>" /></td>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="pic" size="45"
					value="<%=diveinfoVO.getRatePoint()%>" /></td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="job" size="45"
					value="<%=diveinfoVO.getRatePeople()%>" /></td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="job" size="45"
					value="<%=diveinfoVO.getStatus()%>" /></td>
			</tr>



		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="pointSN" value="<%=diveinfoVO.getPointSN()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>

</body>
</html>