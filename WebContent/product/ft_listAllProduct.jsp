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
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">

<title>商城商品</title>
<style>

a { color: #4D2078; PADDING-RIGHT: 2px; PADDING-LEFT: 2px; PADDING-BOTTOM: 2px; PADDING-TOP: 2px; background-color:#EEEBFF; height: 20px; width: 120px; text-align: center; ; border: #A498BD; border-style: outset; border-top-width: 2px; border-right-width: 2px; border-bottom-width: 2px; border-left-width: 2px}
              a:hover { BORDER-RIGHT: ##605080 1px outset; PADDING-RIGHT: 2px; BORDER-TOP: #605080 1px outset; PADDING-LEFT: 2px; PADDING-BOTTOM: 2px; BORDER-LEFT: #605080 1px outset; PADDING-TOP: 2px; BORDER-BOTTOM: #605080 1px outset;background-color:#BDAAE2; height: 20px; width: 120px; text-align: center; } 

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

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<a href="<%=request.getContextPath()%>/product/ft_listDiscountProduct.jsp">優惠專區</a>
	<a href="<%=request.getContextPath()%>/product/ft_listPrimeProduct.jsp">精選專區</a>
	<a href="<%=request.getContextPath()%>/product/ft_listAllProduct.jsp">所有商品</a><br>
	<tr>		    			   
		<td>
		    <b>商品分類 請選擇類別:</b>
		    <FORM METHOD="post" ACTION="product_user.do">
		       <select size="1" name="productClass">
		       
		       <jsp:useBean id="productVO" scope="page" class="com.product.model.ProductVO" />
					<option value="蛙鞋">蛙鞋</option>
					<option value="呼吸管">呼吸管</option>
					<option value="面鏡">面鏡</option>
					<option value="防寒曬衣物">防寒曬衣物</option>
					<option value="氣瓶">氣瓶</option>
					<option value="照明燈">照明燈</option>
					<option value="其他配件">其他配件</option>											
		       </select>
		       
		       <input type="hidden" name="action" value="getProductByClass">
		       <input type="submit" value="送出">
		    </FORM>
		</td>
	</tr>
	<hr>
		<h3>所有商品:</h3>			
	<jsp:include page="/shoppingCar/Cart.jsp" flush="true" />
	<table>
		<tr>
			<td>商品編號</td>
			<td>商品類別</td>
			<td>商品名稱</td>
			<td>商品單價</td>
			<td>商品數量</td>
<!-- 			<td>商品狀態</td> -->
			<td>商品圖片</td>
			<td>商品說明</td>
<!-- 			<td>上架時間</td> -->
<!-- 			<td>優惠品</td> -->
<!-- 			<td>精選品</td> -->
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
<%-- 				<td>${productVO.productStatus}</td> --%>
				<td>
					<div class="show_pic">
						<img src="<%=request.getContextPath()%>/product/GetPhoto.do?productSN=${productVO.productSN}">
					</div>
				</td>
				<td>${productVO.productDetail}</td>
<%-- 				<td>${productVO.productCreateTime}</td> --%>
<%-- 				<td>${productVO.productDiscount}</td> --%>
<%-- 				<td>${productVO.productPrime}</td> --%>
				<td>${productVO.ratingPoint}</td>
				<td>${productVO.ratingNumber}</td>
				
				<td><div align="center">購買數量:<input type="number" name="productQuantity" min="1" max="9999" value=1></div><br>
      			<div align="center">   	   <input type="submit" name="Submit" value="加入購物車"></div></td>		
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