<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<%= productVO==null %>
<html>
<head>
<meta charset="UTF-8">
<title>商品新增頁面</title>


</head>

<body>

	<table id="table-1">
		<h3>
			<a href="backstageManage.jsp">回商品後台管理</a>
		</h3>
	</table>

	<h3>新增商品:</h3>

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
			商品類別:
			<select name="productClass">
				<option value=""></option>
				<option value="蛙鞋"<%=(productVO == null) ? "" : (productVO.getProductClass()).contains("蛙鞋") ? "selected" : ""%>>蛙鞋</option>
				<option value="呼吸管"<%=(productVO == null) ? "" : (productVO.getProductClass()).contains("呼吸管") ? "selected" : ""%>>呼吸管</option>
				<option value="面鏡"<%=(productVO == null) ? "" : (productVO.getProductClass()).contains("面鏡") ? "selected" : ""%>>面鏡</option>
				<option value="防寒曬衣物"<%=(productVO == null) ? "" : (productVO.getProductClass()).contains("防寒曬衣物") ? "selected" : ""%>>防寒曬衣物</option>
				<option value="氣瓶"<%=(productVO == null) ? "" : (productVO.getProductClass()).contains("氣瓶") ? "selected" : ""%>>氣瓶</option>
				<option value="照明燈"<%=(productVO == null) ? "" : (productVO.getProductClass()).contains("照明燈") ? "selected" : ""%>>照明燈</option>
				<option value="其他配件"<%=(productVO == null) ? "" : (productVO.getProductClass()).contains("其他配件") ? "selected" : ""%>>其他配件</option>
				
			</select><br> 
		
			商品名稱:
			<input type="TEXT" name="productName"
				value="<%=(productVO == null) ? "" : productVO.getProductName()%>" /><br> 
			
			商品單價:
			<input type="TEXT" name="productPrice"
				value="<%=(productVO == null) ? "" : productVO.getProductPrice()%>" /><br> 
			
			商品數量:
			<input type="TEXT" name="productQuantity"
				value="<%=(productVO == null) ? "" : productVO.getProductQuantity()%>" /><br> 
			
			商品狀態:
			<input type="radio" name="productStatus" id="st1" value="上架"
			<%=(productVO == null) ? "" : productVO.getProductStatus().contains("上架") ? "checked" : ""%>/>
			<label for="st1">上架</label>
			<input type="radio" name="productStatus" id="st2" value="下架"
			<%=(productVO == null) ? "" : productVO.getProductDiscount().contains("下架") ? "checked" : ""%> checked/>
			<label for="st2">下架</label><br>
			
			商品圖片:
			<div class="picture"></div>
			<input type="file" name="productPhoto" id="the_file" accept="productPhoto/*"><br>
				
			商品說明:
			<br>
			<textarea name="productDetail"><%=(productVO == null) ? "" : productVO.getProductDetail()%></textarea><br>
			
			上架時間:
			<input name="productCreateTime" id="date" type="text"><br>
			
			是否為優惠商品:
			<input type="radio" name="productDiscount" id="op1" value="是"
			<%=(productVO == null) ? "" : productVO.getProductDiscount().contains("是") ? "checked" : ""%>/>
			<label for="op1">是</label>
			
			<input type="radio" name="productDiscount" id="op2" value="否"
			<%=(productVO == null) ? "" : productVO.getProductDiscount().contains("否") ? "checked" : ""%>/>
			<label for="op2">否</label><br>
			
			是否為精選商品:
			<input type="radio" name="productPrime" id="op3" value="是"
			<%=(productVO == null) ? "" : productVO.getProductPrime().contains("是") ? "checked" : ""%>/>
			<label for="op3">是</label>
			
			<input type="radio" name="productPrime" id="op4" value="否"
			<%=(productVO == null) ? "" : productVO.getProductPrime().contains("否") ? "checked" : ""%>/>
			<label for="op4">否</label><br>
			
			評價總分:
			<input type="TEXT" name="ratingPoint"
				value="<%=(productVO == null) ? "0" : productVO.getRatingPoint()%>" /><br>
			
			評價人數:
			<input type="TEXT" name="ratingNumber"
				value="<%=(productVO == null) ? "0" : productVO.getRatingNumber()%>" /><br>
			
		</table><br>
		
		<input type="hidden" name="action" value="insertProduct">
		<input type="submit" value="新增">

	</FORM>

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {width:250px;}
.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {height:150px;}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#date').datetimepicker({
 	       timepicker:false,
 	       step:1,
 	       format:'Y-m-d',
 		   value:'<%=productVO.getProductCreateTime()%>',	   
		// value: new Date(),
	});
</script>

</html>