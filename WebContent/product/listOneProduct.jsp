<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*" %>

<% 
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>單一商品查詢</title>

</head>

<body>

<a href="selectProduct.jsp">回查詢首頁</a>

<table>
	<tr>
		<td>商品編號</td>
		<td>商品類別</td>
		<td>商品名稱</td>
		<td>商品單價</td>
		<td>商品數量</td>
		<td>商品狀態</td>
		<td>商品說明</td>
		<td>上架時間</td>
		<td>優惠品</td>
		<td>精選品</td>
		<td>評價總分數</td>
		<td>評價總人數</td>
	</tr>
		<tr>
		<td><%=productVO.getProductSN() %></td>
		<td><%=productVO.getProductClass() %></td>
		<td><%=productVO.getProductName() %></td>
		<td><%=productVO.getProductPrice() %></td>
		<td><%=productVO.getProductQuantity() %></td>
		<td><%=productVO.getProductStatus() %></td>
		<td><%=productVO.getProductDetail() %></td>
		<td><%=productVO.getProductCreateTime() %></td>
		<td><%=productVO.getProductDiscount() %></td>
		<td><%=productVO.getProductPrime() %></td>
		<td><%=productVO.getRatingPoint() %></td>
		<td><%=productVO.getRatingNumber() %></td>
	</tr>
</table>

</body>
</html>