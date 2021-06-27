<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.follow.model.*"%>
    
<% 
// DisplayOne的頁面會拿到 userID, groupTourSN
	request.setAttribute("userID", 1);	// 先寫死
	request.setAttribute("followed", 2);	// 先寫死

	
// 	// 從套裝行程列表選行程時要記得傳入 groupTourSN
// 	Integer groupTourSN = (Integer) request.getAttribute("groupTourSN");	
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>friend</title>

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


		
<!-- 		<div style="display:none">抓資料讓愛心一開始就顯示對 -->
<%-- 			<jsp:useBean id="colSvc" scope="page" class="com.collections.model.CollectionsService"></jsp:useBean> --%>
<%-- 			<span class="favorite">${colSvc.getCollectionsByUserid(userID)}</span> --%>
<%-- 			<span class="groupTourSN">${groupTourSN}</span> --%>
<!-- 		</div> -->

		<span class="heart btn" ><i class="fas fa-heart"></i></span>


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
        var userID = 1;		// 先寫死
        var followed = 2;
    
        $.ajax({
                url: "<%=request.getContextPath()%>/follow/follow.do?action=friend&userID=" + userID + "&followed=" + followed,
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