<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diveinfo.model.*"%>

<%
	DiveInfoVO diveinfoVO = (DiveInfoVO) request.getAttribute("diveinfoVO");
%>
<html>
<head>

<%@ include file="../share/backend/Bmeta.file" %>
<title>新增潛點</title>
</head>
<body>
<%@ include file="../share/backend/Bheader.file" %>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<FORM METHOD="post" ACTION="diveinfo.do" enctype="multipart/form-data">
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
					placeholder="請輸入潛點簡介(可省略)"
					value="<%=(diveinfoVO == null) ? "" : diveinfoVO.getView()%>"></td>
			</tr>
			<tr>
				<td>介紹:</td>
				<td>
				
				<input type="TEXT" name="introduction" size="45"
					placeholder="請輸入潛點詳細介紹"
					value="<%=(diveinfoVO == null) ? "" : diveinfoVO.getIntroduction()%>"></td>
			</tr>
			<tr>
				<td>季節:</td>
				<td><input type="checkbox" id="season1" name="season" value="春"
					<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("春")) ? "checked" : ""%>>
					春 <input type="checkbox" id="season1" name="season" value="夏"
					<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("夏")) ? "checked" : ""%>>夏
					<input type="checkbox" id="season3" name="season" value="秋"
					<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("秋")) ? "checked" : ""%>>
					秋<input type="checkbox" id="season4" name="season" value="冬"
					<%=(diveinfoVO == null) ? "" : (diveinfoVO.getSeason().contains("冬")) ? "checked" : ""%>>冬</td>

			</tr>
			<tr>
				<td>地區:</td>
				<td><select name="local">
						<option value="北部"
							<%=(diveinfoVO == null) ? "" : ("北部".equals(diveinfoVO.getLocal())) ? "selected" : ""%>>北部</option>
						<option value="南部"
							<%=(diveinfoVO == null) ? "" : ("南部".equals(diveinfoVO.getLocal())) ? "selected" : ""%>>南部</option>
						<option value="離島"
							<%=(diveinfoVO == null) ? "" : ("離島".equals(diveinfoVO.getLocal())) ? "selected" : ""%>>離島</option>
				</select></td>
			</tr>
			<tr>
				<td>圖片:</td>
				<td>
				<div class="picture"></div>
				<input type="file" name="pic" id="the_file" accept="image/*">
				</td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">

	</FORM>
	
	<%@ include file="../share/backend/Bfooter.file" %>
	<%@ include file="../share/backend/Bjs.file" %>
	<script>
		window.addEventListener("DOMContentLoaded", function() {

			// 顯示圖片
			var the_file = document.getElementById("the_file");
			the_file.addEventListener("change", function(e) {

				var picture = document.getElementsByClassName("picture")[0];
				picture.innerHTML = ""; // 清空東西 

				let reader = new FileReader();
				reader.readAsDataURL(this.files[0]);
				reader.addEventListener("load", function() {

					var pic_src = reader.result; // 取得圖片編碼
					picture.innerHTML = "<img class='preview'>";
					document.querySelector(".preview").setAttribute('src',
							pic_src);
				})
			});

		});
	</script>

</body>
</html>