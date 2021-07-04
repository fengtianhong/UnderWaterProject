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
	<%@ include file="../share/backend/Bmeta.file" %>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>訂單主檔與明細</title>
</head>

<style type="text/css">

.container{
	margin: 0 auto;  
 	width: 1200px; 
 	}
 	
  table{
    width:100%;
    table-layout: fixed;
   }
 
  td{
    padding: 8px;
    text-align: left;
    vertical-align:middle;
    font-weight: 300;
    color: gray;
    border-bottom: solid 1px rgba(20,24,78,0.3); 
  }

</style>

<body>
<%@ include file="../share/backend/Bheader.file" %>
	<div class="container">
		<table>
			<h3>訂單主檔</h3>				
			<tr>
				<td>訂單編號</td>
				<td>會員編號</td>
				<td>購買日期</td>
				<td>訂單總金額</td>
				<td>訂單狀態</td>
			</tr>
			
		<c:forEach var="orderForProductVO" items="${olist}">
			<tr>
				<td>${orderForProductVO.orderSN}</td>
				<td>${orderForProductVO.userID}</td>
				<td>${orderForProductVO.purchaseDate}</td>
				<td>${orderForProductVO.totalPrice}</td>
				<td>${orderForProductVO.orderStatus}</td>
			</tr>
		</c:forEach>	
		</table>
		
		<table>
			<h3>明細內容</h3>					
			<tr>
				<td>明細編號</td>
				<td>商品編號</td>
				<td>訂單編號</td>
				<td>購買數量</td>
				<td>商品單價</td>
				<td>商品評價</td>
			</tr>
	
		<c:forEach var="orderListVO" items="${orlist}">
	
			<tr>
				<td>${orderListVO.orderListSN}</td>
				<td>${orderListVO.productSN}</td>
				<td>${orderListVO.orderSN}</td>
				<td>${orderListVO.purchaseQuantity}</td>
				<td>${orderListVO.productPrice}</td>
				<td>${orderListVO.rating}</td>							
			</tr>
		</c:forEach>		
		</table>
	
	</div>
<%@ include file="../share/backend/Bfooter.file" %>
<%@ include file="../share/backend/Bjs.file" %>

</body>
</html>