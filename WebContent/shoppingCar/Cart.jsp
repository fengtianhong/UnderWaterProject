<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.product.model.ProductVO" %>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>購物車</title>
</head>

<body bgcolor="#FFFFFF">

<%Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<font size="+3">購物車內容如下：</font>

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
	
<%-- 	<% else {%> --%>
<!-- 		<h3>購物車中暫無商品 !!</h3>	 -->
<%-- 	<%} %> --%>

</table>

          <form name="checkoutForm" action="<%=request.getContextPath()%>/mall/shoppingCar.html" method="POST">
              <input type="hidden" name="action" value="CHECKOUT"> 
              <input type="submit" value="結帳付款">
          </form>

<%}%>
</body>
</html>