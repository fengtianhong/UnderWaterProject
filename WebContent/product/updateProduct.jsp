<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>商品資料變更</title>

</head>

<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料修改</h3>
				<h4>
					<a href="select_Product.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
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
			<tr>
				<td>商品類別:</td>
				<td><input type="TEXT" name="class" size="45"
					value="<%=productVO.getProductClass()%>" /></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="name" size="45"
					value="<%=productVO.getProductName()%>" /></td>
			</tr>
			<tr>
				<td>商品單價:</td>
				<td><input type="TEXT" name="price" size="45"
					value="<%=productVO.getProductPrice()%>" /></td>
			</tr>
			<tr>
				<td>商品數量:</td>
				<td><input type="TEXT" name="quantity" size="45"
					value="<%=productVO.getProductQuantity()%>" /></td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><input type="TEXT" name="status" size="45"
					value="<%=productVO.getProductStatus()%>" /></td>
			</tr>
			<tr>
				<td>商品說明:</td>
				<td><input type="TEXT" name="detail" size="45"
					value="<%=productVO.getProductDetail()%>" /></td>
			</tr>
			<tr>
				<td>優惠品:</td>
				<td><input type="TEXT" name="discount" size="45"
					value="<%=productVO.getProductDiscount()%>" /></td>
			</tr>
			<tr>
				<td>精選品:</td>
				<td><input type="TEXT" name="prime" size="45"
					value="<%=productVO.getProductPrime()%>" /></td>
			</tr>
			<tr>
				<td>評價總分數:</td>
				<td><input type="TEXT" name="ratingpoint" size="45"
					value="<%=productVO.getRatingPoint()%>" /></td>
			</tr>
			<tr>
				<td>評價總人數:</td>
				<td><input type="TEXT" name="ratingnumber" size="45"
					value="<%=productVO.getRatingNumber()%>" /></td>
			</tr>

			<jsp:useBean id="productSvc" scope="page"
				class="com.product.model.ProductService" />
			<tr>
<!-- 				<td><font color=red><b>*</b></font></td> -->
<!-- 				<td><select size="1" name="productSN"> -->
<%-- 						<c:forEach var="productVO" items="${productSvc.all}"> --%>
<%-- 							<option value="${productVO.productSN}" --%>
<%-- 								${(productVO.productSN==productVO.productSN)?'selected':'' }>${productVO.productName} --%>
<%-- 						</c:forEach> --%>
<!-- 				</select></td> -->
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="productSN" value="<%=productVO.getProductSN()%>"> <input
			type="submit" value="送出修改">
	</FORM>

</body>
</html>