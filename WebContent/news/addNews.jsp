<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>

<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<%=newsVO == null%>
<html>
<head>
<title>新增最新消息</title>
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
	<FORM METHOD="post" ACTION="news.do" enctype="multipart/form-data">
		<table>
			<tr>
				<td>標題:</td>
				<td><input type="TEXT" name="title" size="45"
					placeholder="請輸入最新消息標題"
					value="<%=(newsVO == null) ? "" : newsVO.getTitle()%>"></td>
			</tr>
			<tr>
				<td>內容:</td>
				<td><textarea name="content" rows="6" cols="40"><%=(newsVO == null) ? "" : newsVO.getContent()%></textarea>
				</td>
			</tr>
			<tr>
				<td>圖片:</td>
				<td>
					<div class="picture"></div> <input type="file" name="image"
					id="the_file" accept="image/*">
				</td>
			</tr>
			<tr>
				<td>新聞上架日期:</td>
				<td><input name="newsDate" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>資料來源:</td>
				<td><input type="TEXT" name="newsFrom" size="45"
					placeholder="請輸入資料來源"
					value="<%=(newsVO == null) ? "" : newsVO.getNewsFrom()%>"></td>
			</tr>
			<tr>
				<td>類型:</td>
				<td><select name="newsType">
						<option value="0"
							<%=(newsVO == null) ? "" : ("0".equals(newsVO.getNewsType())) ? "selected" : ""%>>潛點</option>
						<option value="1"
							<%=(newsVO == null) ? "" : ("1".equals(newsVO.getNewsType())) ? "selected" : ""%>>商品</option>
						<option value="2"
							<%=(newsVO == null) ? "" : ("2".equals(newsVO.getNewsType())) ? "selected" : ""%>>揪團</option>
				</select></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">

	</FORM>

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<%
	java.sql.Date newsDate = null;
	try {
		newsDate = newsVO.getNewsDate();
	} catch (Exception e) {
		newsDate = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=newsDate%>' // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>

<script>
	window.addEventListener("DOMContentLoaded", function() {

		// 顯示圖片
		var the_file = document.getElementById("the_file");
		the_file.addEventListener("change", function(e) {

			var picture = document.getElementsByClassName("picture")[0];
			picture.innerHTML = ""; // 清空東西 

			let reader = new FileReader();
			reader.readAsDataURL(this.files[0]);
			reader.addEventListener("load",
					function() {

						var pic_src = reader.result; // 取得圖片編碼
						picture.innerHTML = "<img class='preview'>";
						document.querySelector(".preview").setAttribute('src',
								pic_src);
					})
		});

	});
</script>
</html>