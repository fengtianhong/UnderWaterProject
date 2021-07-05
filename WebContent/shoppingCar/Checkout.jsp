<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.product.model.ProductVO"%>
<%-- <% session.setAttribute("userID", 1); %> --%>

<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
<%
	Integer userID = (Integer) session.getAttribute("userID");
	pageContext.setAttribute("userID", userID);
%>

<%
	String amount = (String) session.getAttribute("amount");
	pageContext.setAttribute("amount", amount);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">

<title>結帳</title>
</head>

<body bgcolor="#FFFFFF">
<jsp:include page="../share/navbar.jsp" flush="true" />

	<hr><p><center>
	
	<table border="1" width="580">
		<tr bgcolor="#999999">
			<th width="220">名稱</th>
			<th width="100">價格</th>
			<th width="100">數量</th>
		</tr>
		
		<%
			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
			String amounts =  (String) session.getAttribute("amount");
		%>
		
		<%	for (int i = 0; i < buylist.size(); i++) {
				ProductVO productVO = buylist.get(i);
				String name = productVO.getProductName();
				Integer price = productVO.getProductPrice();
				Integer quantity = productVO.getProductQuantity();
		%>
		
		<tr>
			<td width="220"><div align="center"><b><%=name%></b></div></td>
			<td width="100"><div align="center"><b><%=price%></b></div></td>
			<td width="100"><div align="center"><b><%=quantity%></b></div></td>
		</tr>
		
		<%
			}
		%>
		
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
			<td><font color="red"><b>$<%=amounts%></b></font></td>
		</tr>
		
	</table>
	
		<FORM name ="createOrder" METHOD="post" ACTION="<%=request.getContextPath()%>/mall/shoppingCar.html">
			
			<input type="hidden" name="productSN" value="${productVO.productSN}">
	      	<input type="hidden" name="Price" value="<%=amounts%>">
				
			<input type="submit" name="buy" value="結帳購買">
	      	<input type="hidden" name="action" value="createOrder">
	      	
	  	</FORM>
	
	<br><p><a href=<%=request.getContextPath()%>/product/ft_listAllProduct.jsp>繼續購物</a>
	</center>

<jsp:include page="../share/footer.jsp" flush="true" />

</body>
</html>
