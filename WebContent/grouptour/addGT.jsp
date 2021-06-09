<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouptour.model.*"%>

<%
	// 抓取新增失敗時回傳的VO
	GroupTourVO groupTourVO = (GroupTourVO) request.getAttribute("groupTourVO");
%>
<%= groupTourVO==null %><%-- 確認有沒有抓到用(可刪) --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Add Group Tour</title>
<style>


</style>
</head>
<body>

<form method="post" action="grouptour.do">
<table>
	<tr>
		<td>套裝行程名稱</td>
		<td><input type="TEXT" name="tourName" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getTourName()%>" /></td>
	</tr>
	<tr>
		<td>行程開始時間</td>
		<td><input type="TEXT" class="startDate" name="startTime" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getStartTime()%>" /></td>
	</tr>
	<tr>
		<td>行程結束時間</td>
		<td><input type="TEXT" class="endDate" name="endTime" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getEndTime()%>" /></td>
	</tr>
	<tr>
		<td>報名開始時間</td>
		<td><input type="TEXT" class="startDate" name="regTime" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getRegTime()%>" /></td>
	</tr>
	<tr>
		<td>報名結束時間</td>
		<td><input type="TEXT" class="endDate" name="closeTime" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getCloseTime()%>" /></td>
	</tr>
	<tr>
		<td>潛點</td>
		<td><input type="TEXT" name="pointSN" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getPointSN()%>" /></td>
	</tr>
	<tr>
		<td>售價</td>
		<td><input type="TEXT" name="price" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getPrice()%>" /></td>
	</tr>
	<tr>
		<td>人數限制</td>
		<td>
			<select name="limitNumder" size="1" value="<%= (groupTourVO==null)?"":groupTourVO.getLimitNumder()%>" />
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>證照資格</td>
		<td><input type="TEXT" name="certificationLimit" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getCertificationLimit()%>" /></td>
	</tr>
	<tr>
		<td>status</td>
		<td><input type="TEXT" name="status" size="45" value="<%= (groupTourVO==null)?"":groupTourVO.getStatus()%>" /></td>
	</tr>

</table>
		<p>行程內容(這裡用ckeditor)</p>
		<textarea class="ckeditor" name="editor1"></textarea>

<input type="hidden" name="action" value="insert">
<input type="submit" value="新增">
</form>


</body>

<%
	java.sql.Date startdate = null;
	
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="https://cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script>
	var editor = CKEDITOR.replace( 'editor1' );
	$.datetimepicker.setLocale('zh');
     var today = new Date();
     $('.startDate').datetimepicker({
	       timepicker:false,       //timepicker:true,
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
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
	       timepicker:false,       //timepicker:true,
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   beforeShowDay: function(date) {
     	  if (  date.getYear() <  today.getYear() || 
		       (date.getYear() == today.getYear() && date.getMonth() <  today.getMonth()) || 
		       (date.getYear() == today.getYear() && date.getMonth() == today.getMonth() && date.getDate() < today.getDate())
	          ) {
	               return [false, ""]
	          }
	          return [true, ""];
  	}});
</script>
</html>