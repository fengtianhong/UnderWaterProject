<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.partymember.model.*" %>
<jsp:useBean id="diveInfoSvc" class="com.diveinfo.model.DiveInfoService" />
<jsp:useBean id="partyMemberSvc" class="com.partymember.model.PartyMemberService" />
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>我舉辦的揪團 修改內容審核報名資格</title>
	<link rel="stylesheet" href="../share/index.css">
    <link rel="stylesheet" href="css/partyIHostDetail.css">
    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<main>
<h4></h4>
<h4>我舉辦的揪團: ${partyVO.partySN} 修改內容 / 審核報名資格</h4>
<section class="party">
	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<table>
			<tr>
				<td class="partySN">揪團編號： </td>
				<td><input type="text" name="partySN" value="${partyVO.partySN}" readonly></td>
			</tr>
			<tr>
				<td class="partyHost">主揪人： </td>
				<td><input type="text" name="partyHost" value="${partyVO.partyHost}" readonly></td>
			</tr>
			<tr>
				<td class="partyTitle">揪團主旨: </td>
				<td><input type="text" name="partyTitle" value="${partyVO.partyTitle}"></td>
			</tr>
			<tr>
				<td class="date">活動日期: </td>
				<td>
					<input type="date" name="startDate" value="${partyVO.startDate}"> 至  <input type="date" name="endDate" value="${partyVO.endDate}">
				</td>
			</tr>
			<tr>
				<td class="regDate">報名開放日期: </td>
				<td><input type="date" name="regDate" value="${partyVO.regDate}"></td>
			</tr>
			<tr>
				<td class="closeDate">報名截止日期: </td>
				<td><input type="date" name="closeDate" value="${partyVO.closeDate}"></td>
			</tr>
			<tr>
				<td class="partyLocation">揪團潛點: </td>
				<td>
					<select size="" name="partyLocation">
					<c:forEach var="diveInfoVO" items="${diveInfoSvc.getAll()}">
						<option value="${diveInfoVO.pointSN}" ${diveInfoVO.pointSN == partyVO.partyLocation? "selected" : ""}>${diveInfoVO.pointName}
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="partyMinSize">最低成團人數(如何和下方審核連動): </td>
				<td>
					<c:if test="${partyVO.status == '1' or partyVO.status == '4'}">
						<input type="number" value="${partyVO.partyMinSize}" disabled>
					</c:if>
					<c:if test="${partyVO.status == '0' or partyVO.status == '2' or partyVO.status == '3' or partyVO.status == '5'}">
						<input type="number" min="${partyMemberSvc.findByPartySNAndStatus(partyVO.partySN, '1').size()}" max="20" name="partyMinSize" value="${partyVO.partyMinSize}">
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="sizenow">目前已通過審核人數(如何和下方審核連動): </td>
				<td>${partyMemberSvc.findByPartySNAndStatus(partyVO.partySN, "1").size()}</td>
			<tr>
				<td class="status">揪團狀態: </td>
				<c:if test="${partyVO.status == 0}">
					<td>
						<select size="" name="status">
							<option value="0" selected>熱烈報名中
							<option value="3">取消
						</select>
					</td>
				</c:if>
				<c:if test="${partyVO.status == 1}">
					<td>
						<select size="" name="status">
							<option value="1" selected>已額滿
							<option value="4">已成團(仍可報名)
						</select>
					</td>
				</c:if>
				<c:if test="${partyVO.status == 2}">
					<td>
						<select size="" name="status">
							<option value="2" selected>已結束
						</select>
					</td>
				</c:if>
				<c:if test="${partyVO.status == 3}">
					<td>
						<select size="" name="status">
							<option value="3" selected>已取消
						</select>
					</td>
				</c:if>
				<c:if test="${partyVO.status == 4}">
					<td>
						<select size="" name="status">
							<option value="1" ${partyVO.status == "1"? "selected": ""}>額滿
							<option value="3" ${partyVO.status == "3"? "selected": ""}>取消
							<option value="4" selected>已成團(仍可報名)
						</select>
					</td>
				</c:if>
				<c:if test="${partyVO.status == 5}">
					<td>
						<select size="" name="status">
							<option value="5" selected>此揪團已被下架
						</select>
					</td>
				</c:if>
			</tr>
			<tr>
				<td class="partyDetail">詳細內容: (CKEditor還沒測) </td>
				<td><textarea name="partyDetail" maxlength=100>${partyVO.partyDetail}</textarea></td>
			</tr>
		</table>
	<!-- 待測試 -->
		<button type="button" onclick="location.href='<%=request.getContextPath()%>/party/partyIHost.jsp'" class="btn btn-outline-info btn-sm">回上頁(放棄揪團內容變更)</button>
		<button type="submit" name="action" value="submitUpdate" class="btn btn-outline-info btn-sm">確認修改揪團內容</button>
	</form>
</section>


<!-- ======================== 報名狀況  ============================== -->

<h4>報名狀況(按報名先後)</h4>
<section class="registration">
	<c:if test="${empty partyMembers}">
		<div class="alert">目前尚無會員報名喔!</div>
	</c:if>
	
	<c:if test="${not empty partyMembers}">
		<table>
			<tr>
				<td>報名序號</td>
				<td>報名會員</td>
				<td>最高證照</td>
				<td>報名時間(待修)</td>
				<td>報名狀態</td>
			</tr>
		<c:forEach var="partyMember" items="${partyMembers}">
			<form method="post" action="<%=request.getContextPath()%>/party/party.do">
				<tr>
					<td>${partyMember.partyMemberSN}
						<input class="partyMemberSN" type="hidden" name="partyMemberSN" value="${partyMember.partyMemberSN}">
						<input type="hidden" name="partySN" value="${partyVO.partySN}">
					</td>
					<td><button type="button" data-toggle="modal" href="#show${partyMember.partyMemberSN}" class="btn btn-warning btn-sm">${memberSvc.getone(partyMember.partyMember).userName}</button>
						<div class="modal" tabindex="-1" id="show${partyMember.partyMemberSN}">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title">會員報名資料</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
							      <table>
							        <tr>
							        	<td>姓名 	</td>
							        	<td>${memberSvc.getone(partyMember.partyMember).userName}</td>
							        </tr>
							        <tr>
							        	<td>綽號  </td>
							        	<td>${memberSvc.getone(partyMember.partyMember).nickName}</td>
							        </tr>
							        <tr>
							        	<td>性別  </td>
							        	<td>${partyMember.gender == '0'? "男" : "女"}</td>
							        </tr>
							        <tr>
							       		<td>E-mail  </td>
							       		<td>${partyMember.email}</td>
							       	</tr>
							       	<tr>
								        <td>連絡電話  </td>
								        <td>${partyMember.phone}</td>
								    </tr>
							        <tr>
							        	<td>最高潛水證照  </td>
								        <c:if test="${partyMember.certification == '1'}">
											<td>PADI OW / SSI OW</td>
										</c:if>
										<c:if test="${partyMember.certification == '2'}">
											<td>PADI AOW / SSI AIW</td>
										</c:if>
										<c:if test="${partyMember.certification == '0' or partyMember.certification == null}">
											<td>無</td>
										</c:if>
									</tr>
							        <tr>
							        	<td>證照圖片  </td>
							        	<td><img class="showpic" src="<%=request.getContextPath()%>/party/partyPic.do?action=certificationPic&&sn=${partyMember.partyMemberSN}"></td>
									</tr>
									<tr>
								        <td>備註  </td>
								        <td>${partyMember.comment}</td>
								    </tr>
							      </table>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						      </div>
						    </div>
						  </div>
						</div>
					</td>
					<c:if test="${partyMember.certification == '1'}">
						<td>PADI OW / SSI OW</td>
					</c:if>
					<c:if test="${partyMember.certification == '2'}">
						<td>PADI AOW / SSI AIW</td>
					</c:if>
					
					<c:if test="${partyMember.certification == '0' or partyMember.certification == null}">
						<td>無</td>
					</c:if>
					<td><fmt:formatDate value="${partyMember.appliedTime}" pattern="yyyy-MM-dd HH:mm" /></td>
					<c:if test="${partyMember.status == '0'}">
						<td>
							<button type="button" name="status" value="1" class="btn btn-outline-success btn-sm accept">接受</button>
							<button type="button" name="status" value="2" class="btn btn-outline-danger btn-sm reject">不接受</button>
						</td>
					</c:if>
					<c:if test="${partyMember.status == '1'}">
						<td><button disabled class="btn btn-outline-success btn-sm">已接受</button></td>
					</c:if>
					<c:if test="${partyMember.status == '2'}">
						<td><button disabled class="btn btn-secondary btn-sm">已拒絕</button></td>
					</c:if>
				</tr>
			</form>
		</c:forEach>
		</table>
	</c:if>
</section>

</main>

<jsp:include page="../share/footer.jsp" flush="true" />

<!-- <script src="../vendors/jquery/jquery-3.5.1.min.js"></script> -->
<script>
	let accept = $('.accept');
	let reject = $('.reject');
	
	accept.on('click', function(){
		let that = this;
		
		let input1 = $(this.closest('tr')).find('input[name="partyMemberSN"]')[0];
		let partyMemberSN = $(input1).val();
		
		let input2 = $(this.closest('tr')).find('input[name="partySN"]')[0];
		let partySN = $(input2).val();
		
		if (confirm('確定接受報名序號' + partyMemberSN + '成為團員? 提醒您: 確認後無法修改!')) {
			let status = '1';
			
			$.ajax({
				url:"<%=request.getContextPath()%>/party/party.do",
				type: "get",
				dataType: "text",	// 指預期從server回傳的文字
				data: {
					"action":  "updatePartyMemberStatus",
					"partyMemberSN": partyMemberSN,
					"status": status,
					"partySN": partySN
				},
				success: function(data) {
					let new_html = `<button disabled class="btn btn-outline-success btn-sm">已接受</button>`;
					$(that.closest('td')).html(new_html);
				}
			});
		} else {
			return;
		}
		
	});
	
	reject.on('click', function(){
		let that = this;
		
		let input1 = $(this.closest('tr')).find('input[name="partyMemberSN"]')[0];
		let partyMemberSN = $(input1).val();
		
		let input2 = $(this.closest('tr')).find('input[name="partySN"]')[0];
		let partySN = $(input2).val();
		
		if (confirm('確定拒絕報名序號' + partyMemberSN + '成為團員? 提醒您: 確認後無法修改!')) {
			let status = '2';
			
			$.ajax({
				url:"<%=request.getContextPath()%>/party/party.do",
				type: "get",
				dataType: "text",
				data: {
					"action":  "updatePartyMemberStatus",
					"partyMemberSN": partyMemberSN,
					"status": status,
				},
				success: function(data) {
					let new_html = `<button disabled class="btn btn-secondary btn-sm">已拒絕</button>`;
					$(that.closest('td')).html(new_html);
				}
			});
		} else {
			return;
		}
		
	});
</script>

</body>
</html>