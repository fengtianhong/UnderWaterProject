<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>修改商品資料</title>

</head>

<body>

	<table id="table-1">

		<h3>
			<a href="listAllProduct.jsp">回所有商品</a>
		</h3>

	</table>

	<h3>資料修改:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="product.do" name="form1">
		<table>
			商品編號:
			<input type="TEXT" name="productSN"
				value="<%=productVO.getProductSN()%>" /><br>
				
			商品類別:
			<select name="productClass">
				<option value=""></option>
				<option value="蛙鞋">蛙鞋</option>
				<option value="呼吸器">呼吸器</option>
				<option value="面鏡">面鏡</option>
				<option value="防寒衣">防寒衣</option>
				<option value="氣瓶">氣瓶</option>
				
			</select><br>
			
			商品名稱:
			<input type="TEXT" name="productName"
				value="<%=productVO.getProductName()%>" /><br>
				
			商品單價:
			<input type="TEXT" name="productPrice"
				value="<%=productVO.getProductPrice()%>" /><br>
				
			商品數量:
			<input type="TEXT" name="productQuantity"
				value="<%=productVO.getProductQuantity()%>" /><br>

<!-- 			商品狀態: -->
<!-- 			<input type="TEXT" name="productStatus" -->
<%-- 				value="<%=productVO.getProductStatus()%>" /><br> --%>
				
			商品說明:
			<textarea></textarea><br>
			
			上架時間:
			<input name="productCreateTime" id="f_date1" type="text"><br>

			是否為優惠商品:
			<input type="radio" name="productDiscount" id="op1" value="true" />
			<label for="op1">是</label>
			<input type="radio" name="productDiscount" id="op2" value="false" />
			<label for="op2">否</label><br>

			是否為精選商品:
			<input type="radio" name="productPrime" id="op3" value="true" />
			<label for="op3">是</label>
			<input type="radio" name="productPrime" id="op4" value="false" />
			<label for="op4">否</label><br>
			
			評價總分數:
			<input type="TEXT" name="ratingPoint"
					value="<%=productVO.getRatingPoint()%>" /><br>
			評價總人數:
			<input type="TEXT" name="ratingNumber"
					value="<%=productVO.getRatingNumber()%>" /><br>

			<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
		</table>

		<input type="hidden" name="action" value="getOne_To_Update">
		<input type="hidden" name="productSN" value="<%=productVO.getProductSN()%>">
		<input type="submit" value="送出修改">

	</FORM>

</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
 	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=productVO.getProductCreateTime()%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>

</html>