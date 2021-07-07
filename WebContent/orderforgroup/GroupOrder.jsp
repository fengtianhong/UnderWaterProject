<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderforgroup.model.*"%>
<%@ page import="com.collections.model.*"%>

<%
	Integer userID = (Integer) session.getAttribute("userID");
	OrderForGroupService orderForGroupSvc = new OrderForGroupService();
	List<OrderForGroupVO> list = orderForGroupSvc.getOrderByUserID(userID);
	pageContext.setAttribute("list", list);
	
	CollectionsService colSvc = new CollectionsService();
	List<Integer> listcol = colSvc.getCollectionsByUserid(userID);
	pageContext.setAttribute("listcol", listcol);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List All for back</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<jsp:include page="../share/member/Mmeta.html" flush="true"/>
<style>
	.main-container{
		padding: 30px;
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
		color: #17a2b8;
	}
	.header_orderSN{
		text-decoration: none !important;
	}
	table{
	    width: 100%;
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
	.collection{
		margin-bottom: 50px;
	}
/* 	PUT IN SHARE */
	.content{
	    width: 1000px;
    	margin: 0 auto;
	}

</style>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />
<jsp:include page="../share/member/Mheader.jsp" flush="true"/>
    <div class="main-container">
    
    <div class="collection">
    
    	<h5>您的收藏</h5><hr>
    	
    	<jsp:useBean id="groupTourSvc" scope="page" class="com.grouptour.model.GroupTourService"></jsp:useBean>
        <c:forEach var="groupTourSN" items="${listcol}">
        <div class="coltourName btn btn-outline-info" onclick="location.href='<%=request.getContextPath()%>/grouptour/grouptour.do?action=getOne_ForDisplay&groupTourSN=${groupTourSN}';">
        <i class="fas fa-heart"></i> ${groupTourSN} - ${groupTourSvc.getOne(groupTourSN).tourName}
        </div>
        </c:forEach>        
    </div>
    
        <h5>訂單查詢</h5><hr>

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
					
						<div class="tourName">${orderForGroupVO.groupTourSN} - ${groupTourSvc.getOne(orderForGroupVO.groupTourSN).tourName}</div>
	            		<div class="time">行程時間 : ${groupTourSvc.getOne(orderForGroupVO.groupTourSN).startTime} - ${groupTourSvc.getOne(orderForGroupVO.groupTourSN).endTime}</div>
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
<jsp:include page="../share/member/Mfooter.html" flush="true"/>    
<jsp:include page="../share/footer.jsp" flush="true" />
<script>
//cancel

$(function () {
	
	$(".cancel").on("click", function() { alert("請聯繫客服為您協助!!"); })
	$('#orderG').addClass('active');
})

</script>
</body>
</html>