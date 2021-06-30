<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderforgroup.model.*"%>

<%	
	OrderForGroupService orderForGroupSvc = new OrderForGroupService();
	List<OrderForGroupVO> list = orderForGroupSvc.getAll();
	pageContext.setAttribute("list", list);	// WHY

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../share/backend/Bmeta.file" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>List All Order for Backend</title>
<style type="text/css">
	.container{
	margin: 0 auto;  
 	width: 1200px; 
 	}
    table{
    width:100%;
    table-layout: fixed;
   }
  .tbl-header{
    background-color: #4e73df; 
/*     background-color: rgba(255,255,255,0.3);  */
   }
  .tbl-content{
    height:400px;
    overflow-x:auto;
    margin-top: 0px;
    border: 1px solid rgba(20,24,78,0.3);
    background-color: rgba(255,255,255,0.3); 
/*     border: 1px solid rgba(255,255,255,0.3);   */
  }
  th{
    padding: 10px 10px;
    text-align: left;
    font-weight: 500;
    color: white;
    text-transform: uppercase;
  }
  td{
    padding: 8px;
    text-align: left;
    vertical-align:middle;
    font-weight: 300;
    color: gray;
    border-bottom: solid 1px rgba(20,24,78,0.3); 
  }
 
  section{
    margin-top: 30px;
  }

/*   button { */
/*     color: #fff; */
/*   } */
  /* for custom scrollbar for webkit browser*/
  
  ::-webkit-scrollbar {
      width: 6px;
  } 
  ::-webkit-scrollbar-track {
      -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
  } 
  ::-webkit-scrollbar-thumb {
      -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
  }
</style>
</head>
<body>
<%@ include file="../share/backend/Bheader.file" %>

<div class="container">

    <section>
   		<div class="main">
        <h1>All Orders For Group Tour</h1>
        
        <div class="tbl-header">
          <table cellpadding="0" cellspacing="0" border="0">
            <thead>
              <tr>
                <th>Order SN</th>
                <th>Customer Name</th>
                <th width="30%">Group Tour</th>
                <th>Amount Due</th>
                <th>Payment Status</th>
                <th>Action</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
            <c:forEach var="orderForGroupVO" items="${list}">
                
              <tr>
				<td>${orderForGroupVO.orderSN}</td>
				<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"></jsp:useBean>          
				<td>${memberSvc.getone(orderForGroupVO.userID).userName}</td>
           		<jsp:useBean id="groupTourSvc" scope="page" class="com.grouptour.model.GroupTourService"></jsp:useBean>
				<td width="30%">${orderForGroupVO.groupTourSN} - ${groupTourSvc.getOne(orderForGroupVO.groupTourSN).tourName}</td>
				<td>$ ${orderForGroupVO.totalPrice}</td>
				<td>Pending(NY)</td>
				<td>
					<form method="post" action="orderforgroup.do">
						<input type="hidden" name="orderSN" value="${orderForGroupVO.orderSN}">
						<input type="hidden" name="groupTourSN" value="${orderForGroupVO.groupTourSN}">
						<input type="hidden" name="action" value="getOne_ForUpdate">
                        <button type="submit" class="custom-btn btn-2">Update</button>
					</FORM>
				</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        </div>
      </section>
 </div>      
<%@ include file="../share/backend/Bfooter.file" %>
</body>
<%@ include file="../share/backend/Bjs.file" %>
<script>
    // '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
    $(window).on("load resize ", function() {
    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
    $('.tbl-header').css({'padding-right':scrollWidth});
    }).resize();
</script>
</html>