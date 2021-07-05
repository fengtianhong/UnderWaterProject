<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list", list);
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	
%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../share/backend/Bmeta.file"%>
<title>Web Manager System UnderWater</title>
<style>
.navbar h1 {
	color: white;
	text-align: center;
}

.navbar h3 {
	color: white;
	margin-top: 27px;
}

#navInfo {
	position: relative;
	height: 80px;
}

#systemName {
	width: 500px;
	height: 80px;
	position: absolute;
	left: 50%;
	margin-left: -250px;
}

#systemInfo {
	width: 250px;
	height: 80px;
	position: absolute;
	right: 0;
	line-height: 1em;
}

.table {
	margin-top: 20px;
}

.container {
	position: relative !important;
}

.popBox {
	border: solid;
	width: 750px;
	height: 800px;
	position: absolute;
	left: 42%;
	top: 5%;
	margin-left: -250px;
	z-index: 100;
	background-color: white;
	text-align: center;
	display: block;
}

.popBox input {
	margin-top: 10px;
}
</style>
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
		<%@ include file="../share/backend/Bheader.file"%>
<div class="container">
 		<div class="row">
            <div class="col-lg-1">

            </div>
            <div class="col-lg-6">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="全部查詢" id="searchInput">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" id="search">查詢</button>
                    </span>
                </div>
            </div>

            <div class="col-lg-1" >
            	<form action="WebManagerSystemLogoutServlet.do" method="post">
            		<button class="btn btn-primary" type="submit" id="logout" >登出</button>
            	</form>
            </div>
        </div>
		
		<table class="table table-striped table-bordered">
                <tr>
					<td>編號</td>
                    <td>帳號</td>
                    <td>暱稱</td>
                    <td>姓名</td>
                    <td>性別</td>
                    <td>生日</td>
                    <td>電話</td>
                    <td>證照</td>
                    <td>證照照片</td>
		   			<td>身分證字號</td>
					<td>地址</td>
					<td>加入時間</td>
					<td>帳號狀態</td>
					<td>更新時間</td>
					<td>評價人數</td>
					<td>評價總分</td>
					<td>個人照片</td>
                </tr>
            <%@ include file="page1.file" %> 
           <c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${memberVO.userID}</td>
			<td>${memberVO.account}</td>
			<td>${memberVO.nickName}</td>
			<td>${memberVO.userName}</td>
			<td>${memberVO.gender}</td>
			<td>${memberVO.birthDate}</td>
			<td>${memberVO.phone}</td>
			<td>${memberVO.certification}</td>
			<td><div class="picture"><img class="preview" src="GetImage.do?userid=${memberVO.userID}"></div></td>
			<td>${memberVO.personID}</td>
			<td>${memberVO.address}</td>
			<td>${memberVO.createTime}</td>
			<td>${memberVO.status}</td>
			<td>${memberVO.upDateTime}</td>
			<td>${memberVO.ratePeople}</td>
			<td>${memberVO.ratePoint}</td>
			<td><div class="picture"><img class="preview" src="GetImagepersonPhoto.do?userid=${memberVO.userID}"></div></td>
			<td>
				<form method="post"  action="<%=request.getContextPath()%>/member/MemberListServlet.do" >
					<input type="submit" class="update" value="修改" onclick="showupdatediv();">
					<input type="hidden" name="userid"  value="${memberVO.userID}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
		</tr>
	</c:forEach>
            </tbody>
        </table>
<%@ include file="page2.file"%>	

<div class="popBox" id="popBox updatePopBox">
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

            <h1>更新會員內容</h1>
	<form method="post" action="MemberListServlet.do" name="form1"  enctype="multipart/form-data"><br>
			編&#12288;&#12288;號：<input name="ueserid" type="text" class="updateInput" value="${memberVO.userID}" readonly><br/>
			帳&#12288;&#12288;號：<input name="account" type="text" value="${memberVO.account}" readonly><br />
			暱&#12288;&#12288;稱：<input name="nickname" type="text" value="${memberVO.nickName}"><br />
			姓&#12288;&#12288;名：<input name="username" type="text" value="${memberVO.userName}"><br />
			
			性&#12288;&#12288;別：&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;<input name="gender" type="radio" value="0"${(memberVO.gender=="0")? 'checked' : ''}>男
								<input name="gender" type="radio" value="1"${(memberVO.gender=="1")? 'checked' : ''}>女<br>
			
			生&#12288;&#12288;日：<input name="birthdate" type="text" value="${memberVO.birthDate}" class="birthdate"><br />
			電&#12288;&#12288;話：<input name="phone" type="text" value="${memberVO.phone}"><br />
			證&#12288;&#12288;照：<input name="certification" type="text" value="${memberVO.certification}"><br />
			<input type="file" id="the_file" name="certificationPic" accept="image/*"><br />
<%-- 				<td>證照照片<input  type="text" name="certificationpic" value="${memberVO.certificationPic}"></td> --%>
<%-- 				<td><div class="picture"><img class="preview" src="GetImage.do?userid=${memberVO.userID}"></div></td> --%>
			身分證字號：<input type="text" name="personid" value="${membeVO.personID}"><br />
			地&#12288;&#12288;址：<input type="text" name="address" value="${memberVO.address}"><br />
			加入時間：<input type="text" name="createtime" value="<fmt:formatDate value="${memberVO.createTime}" pattern="yyyy-mm-dd hh:mm:ss"/>" readonly><br />
			帳號狀態：<input type="text" name="status" value="${memberVO.status}"><br />
			更新時間：<input type="text" name="updatetime" value="<fmt:formatDate value="${memberVO.upDateTime}" pattern="yyyy-mm-dd hh:mm:ss"/>" readonly><br />
			評價人數：<input type="text" name="ratepeople" value="${memberVO.ratePeople}"><br />
			評價總分：<input type="text" name="ratepoint" value="${memberVO.ratePoint}">	<br />		
			<input type="file" id="the_file" name="personPhoto" accept="image/*"><br />
<%-- 				<td><div class="picture"><img class="preview" src="GetImagepersonPhoto.do?userid=${memberVO.userID}"></div></td> --%>
			
		<br>
		<div style="margin-top: 40px;">
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="userid" value="${memberVO.userID}">
			<input type="submit" value="確定修改">
			<button type="button" onclick="location.href='webManagerSystem.jsp'">取消</button>
		</div>
	</form> 
</div>
</div>
	<%@ include file="../share/backend/Bfooter.file"%>
	<%@ include file="../share/backend/Bjs.file"%>
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
	
	$.datetimepicker.setLocale('zh');
    var today = new Date();
    $('.birthdate').datetimepicker({
    	theme: '',          //theme: 'dark',
        timepicker: false,   //timepicker: false,預設true
//         step: 60,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d',
// 	       value: new Date(),
        //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
        //startDate:	        '2017/07/10',  // 起始日
        //minDate:           '-1970-01-01', // 去除今日(不含)之前
        maxDate:           '+1970-01-01'  // 去除今日(不含)之後
     });
	//獲取所有的按钮，绑定事件
   const updateSureBtn = document.getElementById("updateSure");
    const addMember = document.getElementById("addMember");
    const searchInput = document.getElementById("searchInput");
    const search = document.getElementById("search");
	
    var popBox;
    window.onload = function(){
    	popBox = document.getElementById("popBox");
    };
    
    //顯示彈跳視窗
    function showupdatediv(){
    	popBox.style.display="block";
    }
    //隱藏彈跳視窗
    function closeupdatediv(){
    	popBox.style.display="none";
    }
    </script>
<style>
	tr, td{
	text-align:center;
	}
</style>


</html>