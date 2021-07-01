<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.product.model.ProductVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">

<title></title>
</head>

<body bgcolor="#FFFFFF">
<jsp:include page="../share/navbar.jsp" flush="true" />

<img src=""> <font size="+3">商城 - 結帳 </font>
<hr><p><center>

<table border="1" width="720">
	<tr bgcolor="#999999">
		<th width="200">類別</th>
		<th width="100">名稱</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
		<th width="120"></th>
	</tr>
	
	<%
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String amount =  (String) request.getAttribute("amount");
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
			ProductVO productVO = buylist.get(i);
			String pclass = productVO.getProductClass();
			String name = productVO.getProductName();
			Integer price = productVO.getProductPrice();
			Integer quantity = productVO.getProductQuantity();
	%>
	<tr>
		<td width="200"><div align="center"><b><%=pclass%></b></div></td>
		<td width="100"><div align="center"><b><%=name%></b></div></td>
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
		<td></td>
		<td> <font color="red"><b>$<%=amount%></b></font> </td>
		<td></td>
	</tr>
</table>

<p><a href=<%=request.getContextPath()%>/product/ft_searchProduct.jsp>繼續購物</a>
</center>

<jsp:include page="../share/footer.jsp" flush="true" />

</body>
</html>
