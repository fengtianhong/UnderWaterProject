<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Web Manager System UnderWater</title>
<link rel="stylesheet" href="css/bootstrap.css" />
</head>

<body>
	<nav class="navbar navbar-inverse">
        <div id="navInfo">
            <div id="systemName">
                <h1>後台系統</h1>
            </div>
            <div id="systemInfo">
                <h3 id="welcome"></h3>
            </div>
        </div>
    </nav>

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
                <button class="btn btn-primary" type="button" id="logout">登出</button>
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
			<td>${memberVO.certificationPic}</td>
			<td>${memberVO.personID}</td>
			<td>${memberVO.address}</td>
			<td>${memberVO.createTime}</td>
			<td>${memberVO.status}</td>
			<td>${memberVO.upDateTime}</td>
			<td>${memberVO.ratePeople}</td>
			<td>${memberVO.ratePoint}</td>
			<td>
				<form method="post" ACTION="<%=request.getContextPath()%>/emp/MemberListServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="update" >
			     <input type="hidden" name="empno"  value="${memberVO.userID}">
			     <input type="hidden" name="action"	value="update">
				</form>
			</td>
		</tr>
	</c:forEach>
            </tbody>
        </table>
<%@ include file="page2.file"%>	



















	<script>
        
	//獲取所有的按钮，绑定事件
    const updateBtns = document.getElementsByClassName("update");
    const cancelBtns = document.getElementsByClassName("cancel");
    const updateSureBtn = document.getElementById("updateSure");
    const addSureBtn = document.getElementById("addSure");
    const addMember = document.getElementById("addMember");
    const deleteBtns = document.getElementsByClassName("delete");
    const searchInput = document.getElementById("searchInput");
    const search = document.getElementById("search");
	
	
        

        //顯示“更新”功能的彈跳視窗
        function showUpdateDiv(userID) {
            let updateDiv = document.getElementById("updatePopBox");

            //放數據
            let member = null;
            for (let i = 0; i < memberList.length; i++) {
                if (memberList[i].userID == userID) {
                    member = memberList[i];
                }
            }
		const inputs = document.getElementsByClassName("updateInput");
			inputs[0].value = member.userID;
            inputs[1].value = member.account;
            inputs[2].value = member.nickName;
            inputs[3].value = member.userName;
            inputs[4].value = member.gender;
            inputs[5].value = member.birthDate;
            inputs[6].value = member.phone;
            inputs[7].value = member.certification;
	   		inputs[8].value = member.certificationPic;
	    	inputs[9].value = member.personID;
	    	inputs[10].value = member.address;
	    	inputs[11].value = member.createTime;
	    	inputs[12].value = member.status;
	    	inputs[13].value = member.upDateTime;
	    	inputs[14].value = member.ratePeople;
	    	inputs[15].value = member.ratePoint;
            updateDiv.style.display = "block";
        }


        //隱藏更新會員的視窗
        function hiddenUpdateDiv() {
            let updateDiv = document.getElementById("updatePopBox");
            updateDiv.style.display = "none";
        }


        


        

        
        function eventBind() {
            // 更新
            for (let i = 0; i < memberList.length; i++) {
                updateBtns[i].onclick = function () {
                    showUpdateDiv(memberList[i].userID);
                }
            }
            for (let i = 0; i < cancelBtns.length; i++) {
                cancelBtns[i].onclick = function () {
                    hiddenUpdateDiv();
                    hiddenAddDiv();
                }
            }

            for (let i = 0; i < deleteBtns.length; i++) {
                deleteBtns[i].onclick = function () {
                    memberList.splice(i, 1);
                    printMembers();
                    eventBind();
                }
            }

            search.onclick = function () {
                backUp();
                let keyword = searchInput.value;
                if (keyword != null && keyword != "") {
                    memberList = filterMember(keyword);
                }
                printMembers();
                eventBind();
            }

        }

        

        //更新會員
        updateSureBtn.onclick = function () {
            const inputs = document.getElementsByClassName("updateInput");

            let member = {}
            member.userID = inputs[0].value;
            member.account = inputs[1].value;
            member.nickName = inputs[2].value;
            member.userName = inputs[3].value;
            member.gender = inputs[4].value;
            member.birthDate = inputs[5].value;
            member.phone = inputs[6].value;
            member.certification = inputs[7].value;
	   		member.certificationPic = inputs[8].value;
	   		member.personID = inputs[9].value;
	    	member.address = inputs[10].value;
	   		member.createTime = inputs[11].value;
	    	member.status = inputs[12].value;
	    	member.upDate = inputs[13].value;
	    	member.ratePeople = inputs[14].value;
	    	member.ratePoint = inputs[15].value;
            for (let i = 0; i < memberList.length; i++) {
                if (memberList[i].userID == member.userID) {
                    memberList[i] = member;
                }
            }
            printMembers();
            hiddenUpdateDiv();
            hiddenAddDiv();
            eventBind();
        }

        

       

        //取消登錄，清空cookie
        let logout = document.getElementById("logout");
        logout.onclick = function() {
            delCookie('username');
            delCookie('password');
            window.location = "./webManagerSystemLogin.jsp"
        }
    </script>

	
</body>




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
	height: 450px;
	position: absolute;
	left: 50%;
	top: 20%;
	margin-left: -250px;
	z-index: 100;
	background-color: white;
	text-align: center;
	display: none;
}

.popBox input {
	margin-top: 10px;
}
</style>
</html>