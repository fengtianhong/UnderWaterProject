<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<% 
	List<ProductVO> list = (List<ProductVO>) request.getAttribute("list");
	pageContext.setAttribute("list", list);
%>

	
 
<html>
<head>
<meta charset="UTF-8">
<title>顯示所有商品</title>

</head>

<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>所有商品資料</h3>
				<h4>
					<a href="ft_searchProduct.jsp">回商城查詢首頁</a>
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
			<td>商品圖片</td>
			<td>商品說明</td>
			<td>上架時間</td>
			<td>優惠品</td>
			<td>精選品</td>
			<td>評價總分</td>
			<td>評價人數</td>
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
				<td>
					<div class="show_pic">
						<img src="<%=request.getContextPath()%>/product/GetPhoto.do?productSN=${productVO.productSN}">
					</div>
				</td>
				<td>${productVO.productDetail}</td>
				<td>${productVO.productCreateTime}</td>
				<td>${productVO.productDiscount}</td>
				<td>${productVO.productPrime}</td>
				<td>${productVO.ratingPoint}</td>
				<td>${productVO.ratingNumber}</td>
			</tr>
		</c:forEach>
	</table>

	<%-- <%@ include file="page2.file" %> --%>

</body>
</html>