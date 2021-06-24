<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	byte[] photoTemp = null;
	System.out.print(productVO.getProductPhoto());
	
	if (productVO.getProductPhoto() != null) {
		photoTemp = productVO.getProductPhoto();
	}
	session.setAttribute("photoTemp", photoTemp);
	
%>
<%= productVO==null %>
<html>
<head>
<meta charset="UTF-8">
<title>修改商品資料</title>

</head>

<body>

	<table id="table-1">

		<h3>
			<a href="bk_updateProduct_select.jsp">回修改~過濾方式</a>
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
			
			
			商品編號:<font color=red><b>*</b></font>
			<%=productVO.getProductSN()%><br>
				
			商品類別:
			<select name="productClass">
				<option value="蛙鞋"<%=(productVO.getProductClass()).contains("蛙鞋") ? "selected" : ""%>>蛙鞋</option>			
				<option value="呼吸管"<%=(productVO.getProductClass()).contains("呼吸管") ? "selected" : ""%>>呼吸管</option>
				<option value="面鏡"<%=(productVO.getProductClass()).contains("面鏡") ? "selected" : ""%>>面鏡</option>
				<option value="防寒曬衣物"<%=(productVO.getProductClass()).contains("防寒曬衣物") ? "selected" : ""%>>防寒曬衣物</option>
				<option value="氣瓶"<%=(productVO.getProductClass()).contains("氣瓶") ? "selected" : ""%>>氣瓶</option>
				<option value="照明燈"<%=(productVO.getProductClass()).contains("照明燈") ? "selected" : ""%>>照明燈</option>
				<option value="其他配件"<%=(productVO.getProductClass()).contains("其他配件") ? "selected" : ""%>>其他配件</option>
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
			
			商品狀態:<font color=red><b>*</b></font>
			<%=productVO.getProductStatus()%><br>
			
			商品圖片:
			<div class="show_pic">
				<img src="<%=request.getContextPath()%>/product/GetPhoto?productSN=${productVO.productSN}">
			</div>

			<input type="file" name="productPhoto" id="the_file" accept="productPhoto/*">
			<div class="show_pic"></div>
			<div class="picture"></div>
			
			商品說明:
			<br>
			<textarea name="productDetail" value="<%=productVO.getProductDetail()%>"><%=productVO.getProductDetail()%></textarea><br>
					
			上架時間:
			<input name="productCreateTime" id="date" type="text"><br>

			是否為優惠商品:
			<input type="radio" name="productDiscount" id="op1" value="是" 
			<%=(productVO.getProductDiscount()).contains("是") ? "checked" : ""%>/>
<%-- 			<%=(productVO == null) ? "" : (productVO.getProductDiscount()).equals(true) ? "checked" : ""%>/> --%>
			<label for="op1">是</label>
			
			<input type="radio" name="productDiscount" id="op2" value="否"
			<%=(productVO.getProductDiscount()).contains("否") ? "checked" : ""%>/>
<%-- 			<%=(productVO == null) ? "" : (productVO.getProductDiscount()).equals(false) ? "checked" : ""%>/> --%>
			<label for="op2">否</label><br>

			是否為精選商品:
			<input type="radio" name="productPrime" id="op3" value="是"
			<%=(productVO == null) ? "" : (productVO.getProductPrime()).contains("是") ? "checked" : ""%>/>
			<label for="op3">是</label>
			
			<input type="radio" name="productPrime" id="op4" value="否"
			<%=(productVO == null) ? "" : (productVO.getProductPrime()).contains("否") ? "checked" : ""%>/>
			<label for="op4">否</label><br>
			
			評價總分:<font color=red><b>*</b></font>
			<%=productVO.getRatingPoint()%><br>

			評價人數:<font color=red><b>*</b></font>
			<%=productVO.getRatingNumber()%><br>

		</table>

		<input type="hidden" name="action" value="getOne_To_Update">
		<input type="hidden" name="productSN" value="<%=productVO.getProductSN()%>">
		<input type="hidden" name="productStatus" value="<%=productVO.getProductStatus()%>">
		<input type="hidden" name="ratingPoint" value="<%=productVO.getRatingPoint()%>">
		<input type="hidden" name="ratingNumber" value="<%=productVO.getRatingNumber()%>">
		<input type="submit" value="修改">

	</FORM>
	
	<script>
 		window.addEventListener("DOMContentLoaded", function() {

 			顯示圖片
 			var the_file = document.getElementById("the_file");
 			the_file.addEventListener("change", function(e) {

			var picture = document.getElementsByClassName("picture")[0];
 			picture.innerHTML = ""; // 清空東西 

 			let reader = new FileReader();
 			reader.readAsDataURL(this.files[0]);
 			reader.addEventListener("load", function() {

 			var pic_src = reader.result; // 取得圖片編碼
 			picture.innerHTML = "<img class='preview'>";
 			document.querySelector(".preview").setAttribute('src', pic_src);
 				})
 			});
		});
	</script>
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