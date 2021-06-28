<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diveinfo.model.*"%>

<%
	DiveInfoVO diveinfoVO = (DiveInfoVO) request.getAttribute("diveinfoVO");
	byte[] picTemp = diveinfoVO.getPic();
	session.setAttribute("picTemp", picTemp);
%>
<html>
<head>
<%@ include file="../share/backend/Bmeta.file"%>

<title>更新此頁面</title>
<style type="text/css">
.show_pic {
	width: 400px;
}

img {
	width: 100%;
}

#the_file {
	float: right;
	width: 200px;
}

.container {
	margin: 0 auto;
	width: 1200px;
	display: flex;
}

tr, td {
	padding-bottom: 10px;
}

.preview {
	width: 100%;
}
</style>

</head>
<body>
	<%@ include file="../share/backend/Bheader.file"%>
	<div class="container">
		<div class="col-lg-7">
			<FORM METHOD="post" ACTION="diveinfo.do" name="form1"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>潛點編號:<font color=red><b>*</b></font></td>
						<td><%=diveinfoVO.getPointSN()%><input type="file" name="pic"
							id="the_file" accept="image/*"></td>

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
						<td><input type="checkbox" id="season1" name="season"
							value="春"
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
						<td>原圖片:</td>

						<td><div class="show_pic">
								<img
									src="<%=request.getContextPath()%>/diveinfo/ShowPic?pointSN=${diveinfoVO.pointSN}">
							</div></td>


						<td></td>
					</tr>
					<tr>

						<td><input type="hidden" name="ratePoint" size="45"
							value="<%=diveinfoVO.getRatePoint()%>" /></td>
						<td><input type="hidden" name="ratePeople" size="45"
							value="<%=diveinfoVO.getRatePeople()%>" /></td>
						<td><input type="hidden" name="status" size="45"
							value="<%=diveinfoVO.getStatus()%>" /></td>
					</tr>

				</table>
				<br> <input type="hidden" name="action" value="update">
				<input type="hidden" name="pointSN"
					value="<%=diveinfoVO.getPointSN()%>"> <input
					class="btn btn-primary btn-user" type="submit" value="送出修改">
			</FORM>
		</div>

		<div class="col-lg-4">

			<!-- 圖片預覽 -->
			<div class="card shadow mb-4">
				<!-- Card Header - Accordion -->
				<a href="#collapseCardExample" class="d-block card-header py-3"
					data-toggle="collapse" role="button" aria-expanded="true"
					aria-controls="collapseCardExample">
					<h6 class="m-0 font-weight-bold text-primary">新預覽圖</h6>
				</a>
				<!-- Card Content - Collapse -->
				<div class="collapse show" id="collapseCardExample" style="">
					<div class="card-body">
						<%-- 行程圖片 --%>
						<div class="picture"></div>
					</div>
				</div>
			</div>

			<c:if test="${not empty errorMsgs}">

				<div class="mb-4">
					<div class="card border-left-warning shadow py-2">
						<div class="card-body">
							<div class="row no-gutters align-items-center">
								<div class="col mr-2">

									<div
										class="text-xs font-weight-bold text-warning text-uppercase mb-1">
										Error Messages</div>
									<div class="h5 mb-0 font-weight-bold text-gray-800"></div>

									<div class="card-body" style="color: LightCoral">
										<c:forEach var="message" items="${errorMsgs}">
								- ${message}<br>
										</c:forEach>
									</div>
								</div>
								<div class="col-auto">
									<i class="fas fa-comments fa-2x text-gray-300"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
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
	<%@ include file="../share/backend/Bfooter.file"%>
	<%@ include file="../share/backend/Bjs.file"%>
</body>

</body>
</html>