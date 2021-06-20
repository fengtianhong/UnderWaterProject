<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>商品新增頁面</title>


</head>

<body>

	<table id="table-1">
		<h3>
			<a href="selectProduct.jsp">回查詢首頁</a>
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
				<option value="蛙鞋">蛙鞋</option>
				<option value="呼吸器">呼吸器</option>
				<option value="面鏡">面鏡</option>
				<option value="防寒衣">防寒衣</option>
				<option value="氣瓶">氣瓶</option>
				
<%-- 			<%=(productVO == null) ? "" : productVO.getProductClass()%>	 --%>
			</select><br> 
			
			商品名稱:
			<input type="TEXT" name="productName"
				value="<%=(productVO == null) ? "名稱" : productVO.getProductName()%>" /><br> 
			
			商品單價:
			<input type="TEXT" name="productPrice"
				value="<%=(productVO == null) ? "單價" : productVO.getProductPrice()%>" /><br> 
			
			商品數量:
			<input type="TEXT" name="productQuantity"
				value="<%=(productVO == null) ? "數量" : productVO.getProductQuantity()%>" /><br> 
			
			商品狀態:
			<input type="radio" name="productStatus" id="st1" value="1" />
			<label for="st1">上架</label>
			<input type="radio" name="productStatus" id="st2" value="0" checked/>
			<label for="st2">下架</label><br>
						
			商品說明:
			<br>
			<textarea></textarea><br>
			
<%-- 		<input type="TEXT" name="productDetail"	value="<%=(productVO == null) ? "說明" : productVO.getProductDetail()%>" /><br>  --%>		
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
				value="<%=(productVO == null) ? "多少分" : productVO.getRatingPoint()%>" /><br>
			
			評價總人數:
			<input type="TEXT" name="ratingNumber"
				value="<%=(productVO == null) ? "幾個人" : productVO.getRatingNumber()%>" /><br>
			
			<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
		</table><br>
		
		<input type="hidden" name="action" value="insertProduct">
		<input type="submit" value="確認送出">

	</FORM>

</body>
</html>