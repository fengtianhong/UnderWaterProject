<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.product.model.ProductVO" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購物車</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">

<style>

/* a.mall { color: #4D2078; PADDING-RIGHT: 2px; PADDING-LEFT: 2px; PADDING-BOTTOM: 2px; PADDING-TOP: 2px; background-color:#EEEBFF; height: 20px; width: 120px; text-align: center; ; border: #A498BD; border-style: outset; border-top-width: 2px; border-right-width: 2px; border-bottom-width: 2px; border-left-width: 2px} */
/* a.mall:hover { BORDER-RIGHT: ##605080 1px outset; PADDING-RIGHT: 2px; BORDER-TOP: #605080 1px outset; PADDING-LEFT: 2px; PADDING-BOTTOM: 2px; BORDER-LEFT: #605080 1px outset; PADDING-TOP: 2px; BORDER-BOTTOM: #605080 1px outset;background-color:#BDAAE2; height: 20px; width: 120px; text-align: center; } */

.front{
margin: 0 auto;
	width: 950px;
	/* 		display: flex; */
	background-color: snow;
	border-radius: 10px;
	box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.4);
	padding: 30px;
	opacity: .9;
}

</style>

</head>

<body bgcolor="#FFFFFF">
<jsp:include page="../share/navbar.jsp" flush="true" />
<div class="front">

<%Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<font size="+3">購物車裡的商品如下：</font>

<table border="1" width="740">
	<tr bgcolor="#999999">
		<th width="50">類別</th><th width="100">商品名稱</th><th width="50">商品價格</th><th width=50">購買數量</th>
		<th width="30"></th>
	</tr>
	
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		ProductVO order = buylist.get(index);
	%>
	
	<tr>
		<td width="100"><div align="center"><b><%=order.getProductClass()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getProductName()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getProductPrice()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getProductQuantity()%></b></div></td>		
		<td>
	        <form name="deleteForm" action="<%=request.getContextPath()%>/mall/shoppingCar.html" method="POST">
	              <input type="hidden" name="action" value="DELETE">
	              <input type="hidden" name="del" value="<%= index %>">
	              <input type="submit" value="刪除">         
	        </form>
	    </td>
	</tr>
	
	<%}%>

</table>
<!-- 		  <form> -->
<%-- 		  	<input type="button" onclick="window.location.href="<%=request.getContextPath()%>/product/ft_listAllProduct.jsp" value="繼續購物"> --%>
<!-- 		  </form> -->
		  		
          <form name="checkoutForm" action="<%=request.getContextPath()%>/mall/shoppingCar.html" method="POST">
              <input type="hidden" name="action" value="CHECKOUT"> 
              <input type="submit" value="結帳付款">
          </form><br>

          <a class="mall" href="<%=request.getContextPath()%>/product/ft_listAllProduct.jsp">繼續購物</a>

<%return;}else%>


	<%{%>
		<h3 class="nothing">購物車裡什麼都沒有 !!</h3>	
		<a class="mall" href="<%=request.getContextPath()%>/product/ft_listAllProduct.jsp">繼續購物</a>	
	<%}%>

</div>
<jsp:include page="../share/footer.jsp" flush="true" />
</body>
</html>