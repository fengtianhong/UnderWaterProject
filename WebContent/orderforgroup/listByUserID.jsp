<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderforgroup.model.*"%>

<%
	
	// Integer userID = (Integer) session.getAttribute("userID");	// userID 存在session
	OrderForGroupService orderForGroupSvc = new OrderForGroupService();
	List<OrderForGroupVO> list = orderForGroupSvc.getOrderByUserID(1);	// UserID先寫死
	pageContext.setAttribute("list", list);	// WHY
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List All for back</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<style>
	.main-container{
		margin: 0 auto;
		width: 600px;
		background-color: snow;
		border-radius: 10px;
		box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.4);
		padding: 30px;
		opacity: .9;
	}
	.picture{
		width: 100px;
		height: 100px;
	}
	.list-group-image{
		width: 100px;
		height: 100px;
		/*圖片撐滿 */
		object-fit: cover;		
    	object-position: center;
	}
	.cancel{
		position: absolute;
	    right: 5%;
	    top: 14px;
	    color: firebrick;
	}
	.gray_title{
		color: DarkGray;
		font-weight: lighter;
		padding-right: 10px;
	}
	.orderSN{
		color: teal;
	}
	.header_orderSN{
		text-decoration: none !important;
	}
	tr,td{
		padding: 0 5px;
	}
	.subtotal{
		text-align: center;
    	padding-left: 25px;
	}
	.totalPrice{
		text-align: end;
	}

</style>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />
    <div class="main-container">
        <h5>套裝行程訂單查詢</h5><hr>


	<%@ include file="page1.file" %>
    <c:forEach var="orderForGroupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">          
         <div class="card shadow mb-4">
           <!-- Card Header - Accordion -->
           <a href="#collapse${orderForGroupVO.orderSN}" class="d-block card-header py-3 collapsed header_orderSN" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="collapse${orderForGroupVO.orderSN}">
               <h6 class="m-0 font-weight-bold text-primary"><span class="gray_title">訂單編號</span>
               <span class="orderSN">#${orderForGroupVO.orderSN}</span></h6>
               <span class="cancel">取消訂單</span>
           </a>
           <!-- Card Content - Collapse -->
           <div class="collapse" id="collapse${orderForGroupVO.orderSN}" style="">
              <div class="card-body">
              	      
                <div>
                <table>
				<tr>
					<td><div class="picture">
						<img class="list-group-image" src="<%=request.getContextPath()%>/grouptour/GetImage.do?id=${orderForGroupVO.groupTourSN}" />
						</div>
					</td>
					<td>
						<div><span class="gray_title">付款方式</span>信用卡付款</div>
					    <jsp:useBean id="groupTourSvc" scope="page" class="com.grouptour.model.GroupTourService"></jsp:useBean>
					
						<div class="tourName">${orderForGroupVO.groupTourSN} - ${groupTourSvc.getOne(orderForGroupVO.groupTourSN).tourName}</div>
	            		<div class="time">${groupTourSvc.getOne(orderForGroupVO.groupTourSN).startTime} - ${groupTourSvc.getOne(orderForGroupVO.groupTourSN).endTime}</div>
					</td>
					<td class="subtotal">小計
						<div class="pricedetail">$ ${orderForGroupVO.totalPrice}</div>
					</td>
				</tr>
				</table>
                </div> 
                <hr>
                <div class='totalPrice'>總金額   $  ${orderForGroupVO.totalPrice}</div>  
                                       
              </div>
            </div>
            </div>
	</c:forEach>
	<div class='pageNumber'><%@ include file="page2.file" %></div>

    
<!-- container end --></div>
      
<jsp:include page="../share/footer.jsp" flush="true" />
<script>
//cancel

$(function () {
	
	$(".cancel").on("click", function() { alert("請聯繫客服為您協助!!"); })
	
})

</script>
</body>
</html>