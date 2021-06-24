<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>後台商品修改</title>

</head>

<body>
	<a href='backstageManage.jsp'>回後台商品管理</a>
	<h3>修改~請選擇過濾方式</h3>
	<h3>查詢方式:</h3>
	
	<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>	
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</c:if>
	
	<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
	
	<ul>			
		<li><a href='bk_listAllProduct.jsp'>所有商品</a></li><br>
		
		<li>
			<b>依商品編號查詢:</b>
			<form method="post" action="product.do">
				<b>請輸入編號:</b>
				<input type="text" name="productSN"> 
				<input type="hidden" name="action" value="bk_getOneProduct"> 
				<input type="submit" value="送出">
			</form>
		</li>
	     			   
		<li>
		    <b>依商品類別查詢:</b>
		    <FORM METHOD="post" ACTION="product.do">
		       <b>請選擇類別:</b>
		       <select size="1" name="productClass">
		       		<%-- 動態寫法寫不出來 --%>
<%-- 		       		<c:forEach var="productVO" items="${productSvc.}">  --%>
<%--           			<option value="${productVO.productClass}">${productVO.productClass} --%>
<%--          			</c:forEach> --%>
         			
		            <option value=""></option>
					<option value="蛙鞋">蛙鞋</option>
					<option value="呼吸管">呼吸管</option>
					<option value="面鏡">面鏡</option>
					<option value="防寒曬衣物">防寒曬衣物</option>
					<option value="氣瓶">氣瓶</option>
					<option value="照明燈">照明燈</option>
					<option value="其他配件">其他配件</option>
									
		       </select>
		       <input type="hidden" name="action" value="bk_getProductByClass">
		       <input type="submit" value="送出">
		    </FORM>
		</li>		
	</ul>

</body>
</html>