<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouptour.model.*"%>

<%  // 抓取新增失敗時回傳的VO
	GroupTourVO groupTourVO = (GroupTourVO) request.getAttribute("groupTourVO");
%>
<%= groupTourVO==null %><%-- 確認有沒有抓到用(可刪) --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Group Tour</title>
<style>
	img{
		width: 100%;
	}
	.picture{
		width: 400px;
	}


</style>
</head>
<body>

<form method="post" action="grouptour.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>套裝行程名稱</td>
		<td><input type="TEXT" name="tourName" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getTourName()%>" /></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td>套裝行程圖(IO & OUT)</td>
		<td>
			<input type="file" id="the_file" name="tourPic" accept="image/*">
			<div class="picture"><img class='preview' src="GetImage.do?id=${groupTourVO.groupTourSN}"></div>
		</td>
	</tr>
	<tr>
		<td>行程開始時間</td>
		<td><input type="TEXT" class="startDate" name="startTime" value="<%= (groupTourVO==null)?"":groupTourVO.getStartTime()%>" /></td>
	</tr>
	<tr>
		<td>行程結束時間</td>
		<td><input type="TEXT" class="endDate" name="endTime" value="<%= (groupTourVO==null)?"":groupTourVO.getEndTime()%>" /></td>
	</tr>
	<tr>
		<td>報名開始時間</td>
		<td><input type="TEXT" class="startDate" name="regTime" value="<%= (groupTourVO==null)?"":groupTourVO.getRegTime()%>" /></td>
	</tr>
	<tr>
		<td>報名結束時間</td>
		<td><input type="TEXT" class="endDate" name="closeTime" value="<%= (groupTourVO==null)?"":groupTourVO.getCloseTime()%>" /></td>
	</tr>
	
	<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService"></jsp:useBean>
	<tr>
		<td>潛點</td>
		<td><select name="pointSN" size="1" >
			<c:forEach var="diveInfoVO" items="${diveInfoSvc.all}">
				<option value="${diveInfoVO.pointSN}" ${(groupTourVO.pointSN==diveInfoVO.pointSN)? 'selected':''}>${diveInfoVO.pointName}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>售價</td>
		<td><input type="TEXT" name="price" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getPrice()%>" /></td>
	</tr>
	<tr>
		<td>人數限制</td>
		<td><span>${groupTourVO.attendNumber} / </span>
			<select name="limitNumder" size="1">
				<option value="1" ${(groupTourVO.limitNumder==1)? 'selected':'' }>1
				<option value="2" ${(groupTourVO.limitNumder==2)? 'selected':'' }>2
				<option value="3" ${(groupTourVO.limitNumder==3)? 'selected':'' }>3
				<option value="4" ${(groupTourVO.limitNumder==4)? 'selected':'' }>4
				<option value="5" ${(groupTourVO.limitNumder==5)? 'selected':'' }>5
				<option value="6" ${(groupTourVO.limitNumder==6)? 'selected':'' }>6
				<option value="7" ${(groupTourVO.limitNumder==7)? 'selected':'' }>7
				<option value="8" ${(groupTourVO.limitNumder==8)? 'selected':'' }>8
				<option value="9" ${(groupTourVO.limitNumder==9)? 'selected':'' }>9
				<option value="10" ${(groupTourVO.limitNumder==10)? 'selected':'' }>10
			</select>
		</td>
	</tr>
	<tr>
		<td>證照資格</td>
		<td>
			<select name="certificationLimit" size="1">
				<option value="0" ${(groupTourVO.certificationLimit==0)? 'selected':'' }>不須證照
				<option value="1" ${(groupTourVO.certificationLimit==1)? 'selected':'' }>PADI OW / SSI OW
				<option value="2" ${(groupTourVO.certificationLimit==2)? 'selected':'' }>PADI AOW / SSI AOW
			</select>
		</td>
	</tr>
	<tr>
		<td>status</td>
		<td><input type="TEXT" name="status" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getStatus()%>" /></td>
	</tr>

</table>
		<p>行程內容</p>
		<div><textarea name="content"><%=(groupTourVO==null)?"":groupTourVO.getContent()%></textarea></div>


<input type="hidden" name="groupTourSN" value="<%=(groupTourVO==null)?"":groupTourVO.getGroupTourSN()%>">
<input type="hidden" name="attendNumber" value="<%=(groupTourVO==null)?"":groupTourVO.getAttendNumber()%>">
<input type="hidden" name="action" value="update">
<input type="submit" value="修改">
<input type ="button" onclick="window.location.href='<%=request.getContextPath()%>/grouptour/backendListGT.jsp'" value="上一頁">

</form>

<%-- 錯誤表列 --%>
<c:if test="${not empty errMsg}">
	<script>
		alert("請修正以下錯誤: "+ "${errMsg}");
	</script>
</c:if>

<%-- <c:if test="${not empty errMsg}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errMsg}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>


</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>

	// dateTimePicker
	$.datetimepicker.setLocale('zh');
     var today = new Date();
     $('.startDate').datetimepicker({
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
     
     $('.endDate').datetimepicker({
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
     
     window.addEventListener("DOMContentLoaded", function(){

    	// 顯示上傳圖片

	var the_file = document.getElementById("the_file");
		the_file.addEventListener("change", function(e) {

			var picture = document.getElementsByClassName("picture")[0];
			picture.innerHTML = ""; // 清空東西 

			let reader = new FileReader();
			reader.readAsDataURL(this.files[0]);
			reader.addEventListener("load",
					function() {

						var pic_src = reader.result; // 取得圖片編碼
						picture.innerHTML = "<img class='review'>";
						document.querySelector(".review").setAttribute('src',
								pic_src);
					})
		});

	});
</script>
</html>