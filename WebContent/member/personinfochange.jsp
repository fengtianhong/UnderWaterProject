<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="css/app.css" rel="stylesheet">
	<title>Under Water 會員</title>
<title>個人資料</title>
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
									<a class="list-group-item list-group-item-action active" href="personinfochange.jsp" role="tab"> 資訊變更</a> 
									<a class="list-group-item list-group-item-action" href="personchangepwd.jsp" role="tab"> 密碼變更</a> 
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
								<div class="tab-pane fade show active" id="account"
									role="tabpanel">

									<div class="card">
										<form action="MemberInfoServlet.do">
											<div class="card-header">
												<input type="text" class="card-title mb-0" name="account"> 
												<input type="submit" value="搜尋">
												<input type="hidden" name="action" value="search_account">
											</div>
										</form>
										<div class="card-body">
											<form>
												<div class="row">
													<div class="col-md-8">
														<div class="form-group">
															<label for="inputUsername">暱稱</label> 
															<input type="text" class="form-control" id="inputUsername">
														</div>
														<div class="form-group">
																<label for="inputUsername">Biography</label>
																<textarea rows="2" class="form-control" id="inputBio"></textarea>
														</div>
														<div class="form-group">
															<div class="form-group col-md-6">
																<label for="inputFirstName">帳號</label> 
																<input type="text" class="form-control" id="inputFirstName">
															</div>
														</div>
														<div class="form-group">
															<div class="form-group col-md-6">
																<label for="inputFirstName">帳號</label> 
																<input type="text" class="form-control" id="inputFirstName">
															</div>
														</div>
														<div class="form-group">
															<div class="form-group col-md-6">
																<label for="inputFirstName">帳號</label> 
																<input type="text" class="form-control" id="inputFirstName">
															</div>
														</div>
														<div class="form-group">
															<label for="inputPwd">密碼</label> 
															<input type="password" class="form-control" id="inputPwd">
														</div>
														<div class="">
															<label for="inputAddress">Address</label> 
															<input type="text" class="form-control" id="inputAddress">
														</div>
														<div class="form-group">
															<label for="inputAddress2">Address 2</label> 
															<input type="text" class="form-control" id="inputAddress2">
														</div>
													</div>
													<div class="col-md-4">
														<div class="text-center">
															<img alt="Charles Hall" src="images/avatars/avatar.jpg" class="rounded-circle img-responsive mt-2" width="128" height="128" />
															<div class="mt-2">
																<span class="btn btn-primary">更新大頭貼</span>
															</div>
																<small>請使用jpg格式，至少128x128像素，獲得最佳效果</small>
															</div>
														</div>
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

</body>
</html>