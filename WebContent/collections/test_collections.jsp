<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.collections.model.*"%>
    
<% 
// DisplayOne的頁面會拿到 userID, groupTourSN
	request.setAttribute("userID", 1);	// 先寫死
	request.setAttribute("groupTourSN", 6001);	// 先寫死

	
// 	// 從套裝行程列表選行程時要記得傳入 groupTourSN
// 	Integer groupTourSN = (Integer) request.getAttribute("groupTourSN");	
	
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
	
		<h2 class="1"> 我是套裝行程 </h2>


		
		<div style="display:none"><!-- 抓資料讓愛心一開始就顯示對 -->
			<jsp:useBean id="colSvc" scope="page" class="com.collections.model.CollectionsService"></jsp:useBean>
			<span class="favorite">${colSvc.getCollectionsByUserid(userID)}</span>
			<span class="groupTourSN">${groupTourSN}</span>
		</div>
		<!-- 送收藏資料 -->
		<span class="heart btn" ><i class="fas fa-heart"></i></span>

	
	
	
	<h3>報名導至 addOderForGroup 用，userID, GroupTourSN 暫時寫死</h3>
	
	<FORM NAME="orderForm" METHOD="post" ACTION="<%=request.getContextPath()%>/orderforgroup/orderforgroup.do" >
	<input type="hidden" name="userID" value="${userID}">
	<input type="hidden" name="groupTourSN" value="${groupTourSN}">
	<input type="hidden" name="action" value="getOne_ForOrder">
	<input type="submit" value="我要報名">
	</FORM>

<!-- 成功新增Msg -->   
<c:if test="${not empty Msg}">
	<script>alert("${Msg}");</script>
</c:if>

<script src="https://kit.fontawesome.com/d3e24e4d81.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

function init() {
	
	// 抓取收藏
	var favorite = $(".favorite").text();
	var groupTourSN = $(".groupTourSN").text();
	if(favorite.indexOf(groupTourSN) > 0) {
		$(".heart").addClass("-on");
	}
}

$(function () {
	
	init();
	
	// 更新收藏
    $(".heart").on("click", function(){
        // confirm("ADD COLLECTIONS?");
        var that = this;
        var userID = 3;		// 先寫死
        var groupTourSN = $(".groupTourSN").text();
    
        $.ajax({
                url: "<%=request.getContextPath()%>/collections/collections.do?action=favorite&userID=" + userID + "&groupTourSN=" + groupTourSN,
                type: "GET",
                dataType: "text",
                success: function(data){
                    console.log(data);
                    if(data == "delete") {
                    	alert("移除收藏");
                    	$(that).removeClass("-on");
                    }else{
                    	alert("加入收藏");
                    	$(that).addClass("-on");
                    }
                }
        });
    })
    
})
</script>
</body>
</html>