<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.collections.model.*"%>
    
<% 
	CollectionsService colSvc = new CollectionsService();
	CollectionsVO colVO = (CollectionsVO) request.getAttribute("colVO");
	
	// 從套裝行程列表選行程時要記得傳入 groupTourSN
	Integer groupTourSN = (Integer) request.getAttribute("groupTourSN");	
	
	boolean isFavorite = false;
	if (colVO != null) {	// 覺得 EL 可以做到
		List<Integer> list = colSvc.getCollectionsByUserid(colVO.getUserID());
		// pageContext.setAttribute("list", list);
		isFavorite = list.contains(groupTourSN);
		request.setAttribute("isFavorite", isFavorite);
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple Group Tour Page</title>

<style>
	.GPimg{
		width: 30%;
	}
    h2{
      display: inline-block;
    }
	.heart{
	    cursor: pointer;
	    color: pink;
	 }
	.heart.-on{
	    color: red;
	 }
  
</style>


</head>
<body>

	<h3>This is for Collections Test.userID, GroupTourSN 暫時寫死</h3>
	<br>
	<img class="GPimg" alt="" src="<%=request.getContextPath()%>/collections/kenting_isolation.jpg">
	
	<FORM NAME="heartForm" METHOD="post" ACTION="<%=request.getContextPath()%>/collections/collections.do" >
		<h2> 我是套裝行程 </h2>
<!-- 		抓資料讓愛心一開始就顯示對 > DONE -->

		<%=isFavorite %>

		<span class="heart btn" onclick="heartForm.submit()">
			<i class="fas fa-heart"></i>
			<input type="hidden" name="action" value="favorite">
			<input type="hidden" name="userID" value="1">
			<input type="hidden" name="groupTourSN" value="6001">
		</span>
		<c:if test="${not empty Msg}">
			<i style="color: red">${Msg}</i>
		</c:if>
	</FORM>
	
	
	
	<h3>報名導至 addOderForGroup 用，userID, GroupTourSN 暫時寫死</h3>
	
	<FORM NAME="orderForm" METHOD="post" ACTION="<%=request.getContextPath()%>/orderforgroup/orderforgroup.do" >
	<input type="hidden" name="userID" value="1">
	<input type="hidden" name="groupTourSN" value="6001">
	<input type="hidden" name="action" value="getOne_ForOrder">
	<input type="submit" value="我要報名">
	</FORM>

    


<script src="https://kit.fontawesome.com/d3e24e4d81.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

function init() {
	if (<%=isFavorite%>) {
		$(".heart").addClass("-on");
		console.log("isFavorite");
	}
    console.log("hi");
}

$(function () {

    init();
    $(".heart").on("click", function() {
        confirm("ADD COLLECTIONS?");
        $(this).toggleClass("-on");
    })
})
</script>
</body>
</html>