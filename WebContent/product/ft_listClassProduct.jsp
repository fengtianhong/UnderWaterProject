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
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">

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
<jsp:include page="../share/navbar.jsp" flush="true" />
	<div class="front">
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
	
	<jsp:include page="/shoppingCar/Cart.jsp" flush="true" />
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
			<FORM name ="shoppingForm" METHOD="post" ACTION="<%=request.getContextPath()%>/mall/shoppingCar.html">
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
				
				<td><div align="center">購買數量:<input type="number" name="productQuantity" min="1" max="9999" value=1></div></td>
      			<td><div align="center">       <input type="submit" name="Submit" value="加入購物車"></div></td>
			</tr>
				<input type="hidden" name="productSN" value="${productVO.productSN}">			
      			<input type="hidden" name="action" value="ADD">
			</FORM>    		
		</c:forEach>
	</table>
	</div>
	<%-- <%@ include file="page2.file" %> --%>
<jsp:include page="../share/footer.jsp" flush="true" />
</body>
</html>