<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouptour.model.*"%>
<%@ page import="com.diveinfo.model.*"%>

<%
	GroupTourService groupTourSvc = new GroupTourService();
	List<GroupTourVO> list = groupTourSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>套裝行程列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/grouptour/css/frontendListAll.css">
</head>
<%-- 	<%@ include file="../util/meta.html" %> --%>
<body>

<%-- 	<%@ include file="../util/header.html" %> --%>

    <div class="container">
        
        
        
	<input type="search" class="light-table-filter" data-table="order-table" placeholder="請輸入關鍵字">
	<hr>
        <div id="products" class="row list-group">


            <c:forEach var="groupTourVO" items="${list}">
            <div class="item  col-xs-4 col-lg-4">
                <div class="thumbnail">
                		<img class="group list-group-image" src="GetImage.do?id=${groupTourVO.groupTourSN}" />
                    	<div class="caption">
                        <h4 class="group inner list-group-item-heading">${groupTourVO.tourName}</h4>

                        <jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService"></jsp:useBean>
                        <p class="group inner list-group-item-text">
                            ${diveInfoSvc.getOneDiveInfo(groupTourVO.pointSN).pointName}</p>
                        
                        <p class="group inner list-group-item-text">
                            ${groupTourVO.startTime} ~ ${groupTourVO.endTime}</p>
                        <p class="group inner list-group-item-text">
                           	 報名人數: ${groupTourVO.attendNumber} / ${groupTourVO.limitNumder}</p>
                            
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
                    </div>
                </div>
            </div>
            </c:forEach>


        </div>
    </div>
<%--     <%@ include file="../util/footer.html" %> --%>

	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        
</body>

<script>
	// 輕量關鍵字搜索列表
	!function(e){"use strict";var a,n,t=(a=Array.prototype,{init:function(){var t=e.getElementsByClassName("light-table-filter");a.forEach.call(t,function(t){t.oninput=o})}});function o(t){n=t.target;t=e.getElementsByClassName(n.getAttribute("data-table"));a.forEach.call(t,function(t){a.forEach.call(t.tBodies,function(t){a.forEach.call(t.rows,i)})})}function i(t){var e=t.textContent.toLowerCase(),a=n.value.toLowerCase();t.style.display=-1===e.indexOf(a)?"none":"table-row"}e.addEventListener("readystatechange",function(){"complete"===e.readyState&&t.init()})}(document);
	
</script>

</html>