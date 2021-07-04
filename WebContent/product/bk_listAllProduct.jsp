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
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="../share/backend/Bmeta.file" %>
	
<title>所有商品</title>
<style>

.front{
margin: 0 auto;
	width: 800px;
	/* 		display: flex; */
	background-color: snow;
	border-radius: 10px;
	box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.4);
	padding: 30px;
	opacity: .9;
}

	.show_pic {
	height: 150px;
	width: 200px;
	overflow: hidden;
}

.show_pic>img {
	cursor: pointer;
	transition-duration: 0.5s;
	height: 100%;
	width: 100%;
	transition-duration: 0.5s;
}

.show_pic>img:hover {
	transform: scale(1.2);
}
</style>
</head>

<body>
<%@ include file="../share/backend/Bheader.file" %>
	<div class="container">
	
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
			</c:forEach>
		</table>
		
		<%-- <%@ include file="page2.file" %> --%>		
	</div>
	
<%@ include file="../share/backend/Bfooter.file" %>
<%@ include file="../share/backend/Bjs.file" %>
</body>

</html>