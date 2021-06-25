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

<a href="bk_updateProduct_select.jsp">回修改~過濾方式</a>

<table>
	<tr>
		<td>商品編號</td>
		<td>商品類別</td>
		<td>商品名稱</td>
		<td>商品單價</td>
		<td>商品數量</td>
		<td>商品狀態</td>
		<td>商品圖片</td>
		<td>商品說明</td>
		<td>上架時間</td>
		<td>優惠品</td>
		<td>精選品</td>
		<td>評價總分</td>
		<td>評價人數</td>
	</tr>
	<tr>
		<td><%=productVO.getProductSN() %></td>
		<td><%=productVO.getProductClass() %></td>
		<td><%=productVO.getProductName() %></td>
		<td><%=productVO.getProductPrice() %></td>
		<td><%=productVO.getProductQuantity() %></td>
		<td><%=productVO.getProductStatus() %></td>
		<td>
			<div class="show_pic">
				<img src="<%=request.getContextPath()%>/product/GetPhoto.do?productSN=${productVO.productSN}">
			</div>
		</td>
		<td><%=productVO.getProductDetail() %></td>
		<td><%=productVO.getProductCreateTime() %></td>
		<td><%=productVO.getProductDiscount() %></td>
		<td><%=productVO.getProductPrime() %></td>
		<td><%=productVO.getRatingPoint() %></td>
		<td><%=productVO.getRatingNumber() %></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
				<input type="submit" value="上 /下架 變更"> 
				<input type="hidden" name="productSN" value="${productVO.productSN}">
				<input type="hidden" name="action" value="ChangeStatus">
			</FORM>
		</td>
		<td>
			<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/product/product.do">
				<input type="submit" value="資料變更">
				<input type="hidden" name="productSN" value="${productVO.productSN}">
				<input type="hidden" name="action" value="UpdateProduct">
			</FORM>
		</td>
	</tr>
</table>

</body>
</html>