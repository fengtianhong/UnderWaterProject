<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diveinfo.model.*"%>

<%
	DiveInfoVO diveinfoVO = (DiveInfoVO) request.getAttribute("DiveInfoVO");
%>

<html>
<head>
<title>新增潛點</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<script>
			alert("請修正以下錯誤:");
			<c:forEach var="message" items="${errorMsgs}">
			alert("${message}");
			</c:forEach>
		</script>
	</c:if>
	<FORM METHOD="post" ACTION="diveifno.do">
		<table>
			<tr>
				<td>潛點名稱:</td>
				<td><input type="TEXT" name="pointname" size="45"
					placeholder="請輸入潛點名稱"
					value="<%=(diveinfoVO == null) ? "" : diveinfoVO.getPointName()%>"></td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="latitude" size="45"
					placeholder="請輸入緯度座標"
					value="<%=(diveinfoVO == null) ? "" : diveinfoVO.getLatitude()%>"></td>
			</tr>
			<tr>
				<td>經度:</td>
				<td><input type="TEXT" name="longitude" size="45"
					placeholder="請輸入經度座標"
					value="<%=(diveinfoVO == null) ? "" : diveinfoVO.getLongitude()%>"></td>
			</tr>
			<tr>
				<td>簡介:</td>
				<td><input type="TEXT" name="view" size="45"
					placeholder="請輸入潛點簡介"
					value="<%=(diveinfoVO == null) ? "" : diveinfoVO.getView()%>"></td>
			</tr>
			<tr>
				<td>介紹:</td>
				<td><input type="TEXT" name="introduction" size="45"
					placeholder="請輸入潛點詳細介紹"
					value="<%=(diveinfoVO == null) ? "" : diveinfoVO.getIntroduction()%>"></td>
			</tr>
			<tr>
				<td>季節:</td>
				<td><input type="checkbox" id="season1" name="season1"
					value="春"
					<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("春")) ? "checked" : ""%>>
					春 <input type="checkbox" id="season1" name="season2" value="夏"
					<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("夏")) ? "checked" : ""%>>夏
					<input type="checkbox" id="season3" name="season3" value="秋"
					<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("秋")) ? "checked" : ""%>>
					秋<input type="checkbox" id="season4" name="season4" value="冬"
					<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("冬")) ? "checked" : ""%>>冬</td>

			</tr>
			<tr>
				<td>地區:</td>
				<td><select name="local">
						<option value="北部"
							<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("北部")) ? "selected" : ""%>>北部</option>
						<option value="南部"
							<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("南部")) ? "selected" : ""%>>南部</option>
						<option value="離島"
							<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("離島")) ? "selected" : ""%>>離島</option>
				</select></td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="pointname" size="45"
					placeholder="請輸入潛點名稱"
					value="<%=(diveinfoVO == null) ? "" : diveinfoVO.getPointName()%>"></td>
			</tr>
		</table>
	</FORM>
</body>
</html>