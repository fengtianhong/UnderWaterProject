<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.collections.model.*"%>
<%@ page import="com.grouptour.model.*"%>


<%  
	// DisplayOne的頁面會拿到 userID, groupTourSN
	request.setAttribute("userID", 2);	// 先寫死
	GroupTourVO groupTourVO = (GroupTourVO) request.getAttribute("groupTourVO"); //listAll給的(KEEP)
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.picture{
		width: 100%;
	}
	.preview{
		max-width: 100%;
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

		<h2>${groupTourVO.tourName}</h2>
		
<div>
<table>


	<tr>
		<td>套裝行程圖片</td>
		<td>
			<div class="picture">
			<img class='preview' src="<%=request.getContextPath()%>/grouptour/GetImage.do?id=${groupTourVO.groupTourSN}">
			</div>
		</td>
	</tr>
	<tr>
		<td>行程開始時間</td>
		<td>${groupTourVO.startTime}</td>
	</tr>
	<tr>
		<td>行程結束時間</td>
		<td>${groupTourVO.endTime}</td>	
	</tr>
	<tr>
		<td>報名開始時間</td>
		<td>${groupTourVO.regTime}</td>
	</tr>
	<tr>
		<td>報名結束時間(要做倒數)</td>
		<td>${groupTourVO.closeTime}</td>
	</tr>
	
	<tr>
		<td>潛點</td>
		<td>
			<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService"></jsp:useBean>
			${diveInfoSvc.getOneDiveInfo(groupTourVO.pointSN).pointName}
		</td>
	</tr>
	<tr>
		<td>售價</td>
		<td>price</td>
	</tr>
	<tr>
		<td>人數限制</td>
		<td><span class="attendNumber">${groupTourVO.attendNumber}</span> / <span class="limitNumder">${groupTourVO.limitNumder}</span></td>
	</tr>
	<tr>
		<td>證照資格</td>
		<td>
			<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"></jsp:useBean>
	        <c:if test="${groupTourVO.certificationLimit} == '0'">不限</c:if>
	        <c:if test="${groupTourVO.certificationLimit} == '1'">PADI OW / SSI OW</c:if>
	        <c:if test="${groupTourVO.certificationLimit} == '2'">PADI AOW / SSI AOW</c:if>
		</td>
	</tr>
</table>
		<p>行程內容</p>
		<div>
		<p>${groupTourVO.content}</p>
		</div>
		
</div>	

		
		<div><!-- 抓收藏、報名用(有空把他寫在DAO好了 好醜)   -->
			<jsp:useBean id="colSvc" scope="page" class="com.collections.model.CollectionsService"></jsp:useBean>
			<span class="favorite">${(userID == null)?"":colSvc.getCollectionsByUserid(userID)}</span>
			<jsp:useBean id="orderSvc" scope="page" class="com.orderforgroup.model.OrderForGroupService"></jsp:useBean>
			<span class="order_list">${(userID == null)?"":orderSvc.checkRepeatOrder(userID)}</span>
		</div>
		
		<!-- 收藏資料(ajax) -->
		<span class="heart btn" ><i class="fas fa-heart"></i></span>

	
	<FORM NAME="orderForm" METHOD="post" ACTION="<%=request.getContextPath()%>/orderforgroup/orderforgroup.do" >
		<input type="hidden" class="userID" name="userID" value="${userID}">
		<input type="hidden" class="groupTourSN" name="groupTourSN" value="${groupTourVO.groupTourSN}">
		<input type="hidden" name="action" value="getOne_ForOrder">
		<input class="nonattend_btn" type="submit" value="我要報名" >
		<input class="attend_btn" type="submit" value="已報名" disabled  style="display:none">
	</FORM>

<!-- 成功新增Msg -->   
<c:if test="${not empty errMsg}">
	<script>alert("${errMsg}");</script>
</c:if>
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
	
	// 抓取訂單
	var orderList = $(".order_list").text();
	if(orderList.indexOf(groupTourSN) > 0) {
		$(".attend_btn").show();
		$(".nonattend_btn").hide();
	}
	
	// 抓取是否已額滿	>>上面三駝都可以寫在 c:if
	var attendNumber = $(".attendNumber").text();
	var limitNumder = $(".limitNumder").text();
	if(attendNumber>=limitNumder) {
		$(".attend_btn").val("已額滿");
		$(".attend_btn").show();
		$(".nonattend_btn").hide();
	}
	
}

$(function () {
	
	init();
	
	// 更新收藏
    $(".heart").on("click", function(){
        // confirm("ADD COLLECTIONS?");
        var that = this;
        var userID = $(".userID").val();		// 先寫死
        var groupTourSN = $(".groupTourSN").text();
        console.log(userID, "+",groupTourSN);
    
        $.ajax({
          		url: "<%=request.getContextPath()%>/collections/collections.do?action=favorite&userID=" + userID + "&groupTourSN=" + groupTourSN,
<%--            url: "<%=request.getContextPath()%>/collections/collections.do", --%>
                type: "GET",
                dataType: "text",
//                 data: {
//                 	"action": "favorite",
//                 	"userID": userID,
//                 	"groupTourSN": groupTourSN,
//                 },
                success: function(data){
                    console.log(data);
                    if(data == "delete") {
                    	alert("移除收藏");
                    	$(that).removeClass("-on");
                    }else if(data == "add"){
                    	alert("加入收藏");
                    	$(that).addClass("-on");
                    }else if(data == "fail"){	// 
                    	alert("請確認是否已登入");
                    }
                }
        });
    })
    
    
})
</script>
</body>
</html>