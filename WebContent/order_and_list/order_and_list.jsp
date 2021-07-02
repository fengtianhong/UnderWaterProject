<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderforproduct.model.*"%>
<%@ page import="com.orderlist.model.*"%>

<%
	OrderForProductService orderSvc = new OrderForProductService();
	List<OrderForProductVO> olist = orderSvc.getAll();
	pageContext.setAttribute("olist", olist);
%>

<%
	OrderListService listSvc = new OrderListService();
	List<OrderListVO> orlist = listSvc.getAll();
	pageContext.setAttribute("orlist", orlist);
%>

<html>
<head>
<meta charset="UTF-8">
<title>顯示所有訂單與明細</title>

</head>

<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>所有訂單</h3>
				<h4>
					<a href=""></a>
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

	<table>
		<tr>
			<td>訂單編號</td>
			<td>會員編號</td>
			<td>購買日期</td>
			<td>訂單總金額</td>
			<td>訂單狀態</td>
		</tr>

		<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="orderForProductVO" items="${olist}">

		<tr>
			<td>${orderForProductVO.orderSN}</td>
			<td>${orderForProductVO.userID}</td>
			<td>${orderForProductVO.purchaseDate}</td>
			<td>${orderForProductVO.totalPrice}</td>
			<td>${orderForProductVO.orderStatus}</td>
								
<!-- 				<td> -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do"> --%>
<!-- 						<input type="submit" value="上 /下架 變更">  -->
<%-- 						<input type="hidden" name="productSN" value="${productVO.productSN}"> --%>
<!-- 						<input type="hidden" name="action" value="ChangeStatus"> -->
<!-- 					</FORM> -->
<!-- 				</td>			 -->
		</tr>
	</c:forEach>
	
		<tr>
			<td>
				<h3>所有明細</h3>
				<h4>
					<a href=""></a>
				</h4>
			</td>
		</tr>
		
		<tr>
			<td>明細編號</td>
			<td>商品編號</td>
			<td>訂單編號</td>
			<td>購買數量</td>
			<td>商品單價</td>
			<td>商品評價</td>
		</tr>

		<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="orderListVO" items="${orlist}">

		<tr>
			<td>${orderListVO.orderListSN}</td>
			<td>${orderListVO.productSN}</td>
			<td>${orderListVO.orderSN}</td>
			<td>${orderListVO.purchaseQuantity}</td>
			<td>${orderListVO.productPrice}</td>
			<td>${orderListVO.rating}</td>
								
<!-- 				<td> -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do"> --%>
<!-- 						<input type="submit" value="上 /下架 變更">  -->
<%-- 						<input type="hidden" name="productSN" value="${productVO.productSN}"> --%>
<!-- 						<input type="hidden" name="action" value="ChangeStatus"> -->
<!-- 					</FORM> -->
<!-- 				</td>			 -->
		</tr>
	</c:forEach>
		
	</table>

	<%-- <%@ include file="page2.file" %> --%>

</body>
</html>