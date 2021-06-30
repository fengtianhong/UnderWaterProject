<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouptour.model.*"%>
<%@ page import="com.diveinfo.model.*"%>

<%
	GroupTourService groupTourSvc = new GroupTourService();
	List<GroupTourVO> list = groupTourSvc.getFrontendAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">   
<title>套裝行程列表</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">	
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/grouptour/css/frontendListAll.css"> --%>

<style>

	.container{
		margin: 0 auto;  
 		width: 1200px; 
		display: flex;
	}
	.filter{
		
	}
 	.item{ 
 		margin: 18px; 
 		border: 1px solid white;
		background-color: #eee;
 		padding: 5px; 
 		border-radius: 20px;
 		box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.4);
 		
 	} 
 	.item:hover{
 		opacity: 0.99;
 		transform: scale(1.01);
 		box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.7);
 	}
	.picture{
		border: 1px solid white;
		height: 180px;
		overflow: hidden;
		margin: 8px;
		margin-top: 15px;
 		border-radius: 50% 20% / 10% 40%; 
	}
	.list-group-image{
		width: 257px;
		height: 200px;
		/*圖片撐滿 */
		object-fit: cover;		
    	object-position: center;
	}
	.caption{
		padding: 15px;
		
	}
	.pointName{
		color: DarkSlateGray;
	}
	
	.btn-success{
		float: right;
	}
	.inner-text{
		padding-left: 9px;
	}
	.detail{
		margin-top: 7px;
	}
/* 上方搜尋欄 */
	.bar{
		height: 100px;
		margin: 0 auto;  
 		width: 1200px; 
		display: flex;
	}
	.page2{
		margin-top: 50px;
		text-align: center;
	}
	.attend_btn{
    	position: relative;
    	bottom: 45px;
    	left: 96px;
    	cursor: default !important;
	}
	
		
</style>
</head>

<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

	<div class="bar"></div>
	
    
    <div class="filter"><input type="search" class="light-table-filter" data-table="order-table" placeholder="請輸入關鍵字"></div>   
    <div class="container">
        
        
	<hr>
        <div id="products" class="row justify-content-center">

<%@ include file="page1.file"%>	
            <c:forEach var="groupTourVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
            <div class="item col-lg-3 col-md-6 col-sm-6">
                <div class="thumbnail">
                
                <div class="picture">
                		<img class="list-group-image" src="GetImage.do?id=${groupTourVO.groupTourSN}" />
                </div>
                <div class="caption">
                        <h6><b>${groupTourVO.tourName}</b></h6>

                        <jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService"></jsp:useBean>
                        <small class="pointName">
                            ${diveInfoSvc.getOneDiveInfo(groupTourVO.pointSN).pointName}</small>
                        
                        <p class="detail">
                            <i class="fas fa-calendar-day"></i><span class="inner-text">${groupTourVO.startTime} ~ ${groupTourVO.endTime}</span>
                            <br>
                        	<i class="fas fa-child"></i><span class="inner-text">${groupTourVO.attendNumber} / ${groupTourVO.limitNumder}</span>
                        </p>
                            
                        <div class="row">
                            <div class="col-xs-12 col-md-6">
                                <p class="lead">
                                    $ ${groupTourVO.price}</p>
                         </div>
                            <div class="col-xs-12 col-md-6">
                                <form method="post" action="grouptour.do" class="btn-div">
                                <input type="hidden" name="action" value="getOne_ForDisplay">
                                <input type="hidden" name="groupTourSN" value="${groupTourVO.groupTourSN}">
                                <input class="btn btn-success" type="submit" value="More">
                                </form>
                            </div>
                        </div>
                        <c:if test="${groupTourVO.attendNumber >= groupTourVO.limitNumder}">
                        		<input class="btn btn-danger attend_btn" style="color: white; background-color: LightCoral; border: LightCoral;" type="button" value="已額滿" >
                        </c:if>
                </div>
                </div>
            </div>
            </c:forEach>


        </div>
    </div>
    
 
<div class="page2"><%@ include file="page2.file"%></div>
<jsp:include page="../share/footer.jsp" flush="true" />
<script src="https://kit.fontawesome.com/d3e24e4d81.js" crossorigin="anonymous"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        
</body>

<script>
	// 輕量關鍵字搜索列表
	!function(e){"use strict";var a,n,t=(a=Array.prototype,{init:function(){var t=e.getElementsByClassName("light-table-filter");a.forEach.call(t,function(t){t.oninput=o})}});function o(t){n=t.target;t=e.getElementsByClassName(n.getAttribute("data-table"));a.forEach.call(t,function(t){a.forEach.call(t.tBodies,function(t){a.forEach.call(t.rows,i)})})}function i(t){var e=t.textContent.toLowerCase(),a=n.value.toLowerCase();t.style.display=-1===e.indexOf(a)?"none":"table-row"}e.addEventListener("readystatechange",function(){"complete"===e.readyState&&t.init()})}(document);
	
</script>

</html>