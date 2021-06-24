<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>商品查詢首頁</title>

</head>

<body>

	<h3>商品查詢</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		
	<li><a href='listDiscountProduct.jsp'>優惠專區</a><br><br></li>		
	<li><a href='listAllProduct.jsp'>查詢所有商品</a><br><br></li>		
	<jsp:useBean id="dao" scope="page" class="com.product.model.ProductDAO" />
		
	<li>
		<form method="post" action="product.do">
			<b>請輸入編號:</b>
			<input type="text" name="productSN"> 
			<input type="hidden" name="action" value="getOneProduct"> 
			<input type="submit" value="送出">
		</form>
	</li>
     			   
	<li>
	    <FORM METHOD="post" ACTION="product.do">
	       <b>類別查詢:</b>
	       <select size="1" name="productClass">
	            <option value=""></option>
				<option value="蛙鞋">蛙鞋</option>
				<option value="呼吸器">呼吸器</option>
				<option value="面鏡">面鏡</option>
				<option value="防寒衣">防寒衣</option>
				<option value="氣瓶">氣瓶</option>
	       </select>
	       <input type="hidden" name="action" value="getProductByClass">
	       <input type="submit" value="送出">
	    </FORM>
	</li>
		  	  
<!-- 	<li> -->
<!-- 	    <FORM METHOD="post" ACTION="product.do"> -->
<!-- 	       <b>查詢優惠商品</b> -->
<!-- 	       <select size="1" name="productSN"> -->
<%-- 	         <c:forEach var="productVO" items="${dao.all}">  --%>
<%-- 	          	<option value="${productVO.productSN}">${productVO.productDiscount} --%>
<%-- 	         </c:forEach>    --%>
<!-- 	       </select> -->
<!-- 	       <input type="hidden" name="action" value="getOneProduct"> -->
<!-- 	       <input type="submit" value="送出"> -->
<!-- 	    </FORM> -->
<!-- 	</li> -->
				
	</ul>

</body>
</html>