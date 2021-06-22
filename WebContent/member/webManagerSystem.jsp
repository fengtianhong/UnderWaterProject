<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>We bManager System 會員管理系統</title>
</head>
<link rel="stylesheet" href="css/bootstrap.css">
<style>
.navbar h1{
	color:white;
	text-align:center;
}

.navbar h3 {
	color:white;
	margin-top:20px;
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

#managerName {
	width:135px;
	height:80px;
	position:absolute;
	right:0;
	line-height:1em;
}

.table {
	margin-top:50px;
}

.container {
	position:relative !important;
}

.popBox {
	border: solid;
	width: 500px;
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
	margin-top:10px;
}


</style>
<body>
	<nav class="navbar navbar-inverse">
		<div id="navInfo">
			<div id="systemName">
				<h1>會員管理系統</h1>
			</div>
			<div>
				<h3 id="managerName">管理者資訊</h3>
			</div>
		</div>
	</nav>
	
	<div class="container">
	
		<div class="col-lg-1">
			
		</div>
		<div class="col-lg-6">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="搜尋" id="searchInput">
				<span class="input-group-btn">
					 <button class="btn btn-default" type="button">搜尋</button>
				</span>
			</div>
		</div>
		
		<table class="table table-striped table-bordered">
			<thead>
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
					<td>身分證</td>
					<td>地址</td>
					<td>加入時間</td>
					<td>帳號狀態</td>
					<td>更新時間</td>
					<td>評價人數</td>
					<td>評價分數</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody id="content">
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
					<td>9</td>
					<td>10</td>
					<td>11</td>
					<td>12</td>
					<td>13</td>
					<td>14</td>
					<td>15</td>
					<td>16</td>
					<td>
						<button class="update">更新</button>						
					</td>
				</tr>
			</tbody>
		</table>
		
		
		<div class="popBox" id="updatePopBox">
			<h1>更新用戶內容</h1>
			編&#12288;&#12288;號：<input type="text" class="updateInput" readonly /><br />
			帳&#12288;&#12288;號：<input type="text" class="updateInput" /><br />
			姓&#12288;&#12288;名：<input type="text" class="updateInput" /><br />
          	暱&#12288;&#12288;稱：<input type="text" class="updateInput" /><br />
			姓&#12288;&#12288;名：<input type="text" class="updateInput" /><br />
			生&#12288;&#12288;日：<input type="text" class="updateInput" /><br />
			電&#12288;&#12288;話：<input type="text" class="updateInput" /><br />
			證&#12288;&#12288;照：<input type="text" class="updateInput" /><br />
			證照照片：<input type="text" class="updateInput" /><br />
			身分證字號：<input type="text" class="updateInput" /><br />
			地&#12288;&#12288;址：<input type="text" class="updateInput" /><br />
			加入時間：<input type="text" class="updateInput" /><br />
			帳號狀態：<input type="text" class="updateInput" /><br />
			更新時間：<input type="text" class="updateInput" /><br />
			評價人數：<input type="text" class="updateInput" /><br />
			評價總分：<input type="text" class="updateInput" /><br />
			<div style="margin-top:40px;">
				<button class="btn info cancel">取消</button>
				<button class="btn info sure" style="margin-left:20px;" id="updateSure">確定變更</button>
			</div>
		</div>
	</div>
	
<script>
	//隱藏更新版位
	function hiddenUpdateDiv(){
		let updateDiv = document.getElementById("updatePopBox");
		updateDic.style.display = "none;"
	}
	
	const updateBtns = document.getElementsByClassName("update");
    const cancelBtns = document.getElementsByClassName("cancel");
    const updateSureBtn = document.getElementById("updateSure");
    const searchInput = document.getElementById("searchInput");
    const search = document.getElementById("search");
    
	//更新
	updateSurBtn.onclick = function(){
		const inputs = document.getElementByClassName("updateInput");
		
		let student = {}
		
	}
</script>	
	
	
	
	

</body>
</html>