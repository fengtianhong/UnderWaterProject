<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta charset="UTF-8">
<title>All Product</title>

</head>

<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>所有商品</h3>
				<h4>
					<a href="selectProduct.jsp">回selectProduct</a>
				</h4>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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

		<%-- 	<%@ include file="page1.file" %>  --%>

		<c:forEach var="productVO" items="${list}">

			<tr>
				<td>${productVO.productSN}</td>
				<td>${productVO.productClass}</td>
				<td>${productVO.productName}</td>
				<td>${productVO.productPrice}</td>
				<td>${productVO.productQuantity}</td>
				<td>${productVO.productStatus}</td>
				<td>${productVO.productDetail}</td>
				<td>${productVO.productCreateTime}</td>
				<td>${productVO.productDiscount}</td>
				<td>${productVO.productPrime}</td>
				<td>${productVO.ratingPoint}</td>
				<td>${productVO.ratingNumber}</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
						<input type="submit" value="上下架變更"> 
						<input type="hidden" name="productSN" value="${productVO.productSN}">
						<input type="hidden" name="action" value="getOne_To_OffShelf">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/product/product.do">
						<input type="submit" value="變更商品資料">
						<input type="hidden" name="productSN" value="${productVO.productSN}">
						<input type="hidden" name="action" value="getOne_To_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

	<%-- <%@ include file="page2.file" %> --%>

</body>
</html>