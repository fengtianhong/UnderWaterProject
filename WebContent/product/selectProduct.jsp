<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>UnderWaterShop</title>

</head>

<body>

	<h3>資料查詢</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllProduct.jsp'>查詢所有商品</a><br><br></li>
		
		
		<jsp:useBean id="dao" scope="page" class="com.product.model.ProductDAO" />
  
	  <%-- com.emp.model.EmpDAO dao = new com.emp.model.EmpDAO();
	  	   pageContext.setAttribute("dao", dao);
	  --%>
	   
			   
	<li>
	    <FORM METHOD="post" ACTION="product.do">
	       <b>選擇商品類別:</b>
	       <select size="1" name="empno">
	         <c:forEach var="productVO" items="${dao.all}"> 
	          	<option value="${productVO.productSN}">${productVO.productClass}
	         </c:forEach>   
	       </select>
	       <input type="hidden" name="action" value="getOne_For_Display">
	       <input type="submit" value="送出">
	    </FORM>
	</li>
		  
		  
	<li>
	    <FORM METHOD="post" ACTION="product.do">
	       <b></b>
	       <select size="1" name="empno">
	         <c:forEach var="productVO" items="${dao.all}"> 
	          	<option value="${productVO.productSN}">${productVO.productDiscount}
	         </c:forEach>   
	       </select>
	       <input type="hidden" name="action" value="getOne_For_Display">
	       <input type="submit" value="送出">
	    </FORM>
	</li>
	
			
	<li>
		<form method="post" action="product.do">
			<p>請輸入商品編號:</p>
			<input type="text" name="productSN"> 
			<input type="hidden" name="action" value="getOneProduct"> 
			<input type="submit" value="送出">
		</form>
	</li>
	</ul>

	<h3>新增商品</h3>

	<ul>
  		<li><a href='insertProduct.jsp'>新增商品</a></li>
	</ul>

</body>
</html>