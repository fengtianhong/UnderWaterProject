<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ChangPwd</title>
	<link href="css/app.css" rel="stylesheet">
</head>
<body>
	<div class="wrapper">
		<div class="main">
			<main class="content">
				<div class="container-fluid p-0">
					<div class="row">
						<div class="col-md-3 col-xl-2">

							<div class="card">
								<div class="list-group list-group-flush" role="tablist">
									<a class="list-group-item list-group-item-action" href="personinfo.jsp" role="tab"> 會員資訊 </a> 
									<a class="list-group-item list-group-item-action" href="personinfochange.jsp" role="tab"> 資訊變更 </a> 
									<a class="list-group-item list-group-item-action active" href="personchangepwd.jsp" role="tab"> 密碼變更</a> 
									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a> 
									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增</a> 
									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a> 
									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a> 
									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a>
								</div>
							</div>
							
						</div>
						<div class="col-md-9 col-xl-10">
							<div class="tab-content">
								<div id="password" role="tabpanel">
									<div class="card">
										<div class="card-body">
											<h5 class="card-title">變更密碼</h5>
											<form action="#" method="post">
												<div class="form-group">
													<label for="inputPasswordCurrent">原始密碼</label>
													<input type="password" class="form-control" id="inputPasswordCurrent">
												</div>
												<div class="form-group">
													<label for="inputPasswordNew">新密碼</label>
													<input type="password" class="form-control" id="inputPasswordNew">
												</div>
												<div class="form-group">
													<label for="inputPasswordNew2">再次確認密碼</label>
													<input type="password" class="form-control" id="inputPasswordNew2">
												</div>
												<button type="submit" class="btn btn-primary">送出</button>
											</form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	
	<script src="js/vendor.js"></script>
	<script src="js/app.js"></script>
	
</body>
</html>