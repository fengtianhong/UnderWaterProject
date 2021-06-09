<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form>
	<div>欲報名揪團編號: ${partyVO.partySN} 待改!!!!!</div>
	<div><input type="text" name="userID" placeholder="待改" readonly></div>
	<div>
		<select size="" name="gender">
			<option value="0" selected>男
			<option value="1">女
			<option value="2">其他
		</select>
	</div>
	<div><input type="text" name="email" placeholder="待補"></div>
	<div><input type="text" name="phone" placeholder="待補"></div>
	<div><input type="date" name="birthdate" placeholder="待補"></div>
	<div><input type="text" name="personID" placeholder="待補"></div>
	<div>
		<select size="" name="certification">
			<option value="none">無相關證照(待確認)
			<option value="A1">PADI OW
			<option value="A2">PADI AOW
			<option value="B1">SSI OW
			<option value="B2">SSI AOW
		</select>
	</div>
	<div>
		<label>請上傳證照翻拍檔案</label>
		<input type="file" id="p_file">
	</div>
	<div>
		<ul id="p_display">預覽圖</ul>
	</div>
	<div><textarea style="width:300px;height:100px;" placeholder="其他備註"></textarea></div>
</form>

<script>
	document.querySelector('#p_file').addEventListener('change', function() {
// 		document.querySelector('#p_display').innerHTML = "";
		
		let file = this.files[0];
		let reader = new FileReader();
		reader.readAsDataURL(file);
		reader.addEventListener('load', function() {
			console.log(reader.result);
// 			console.log(document.querySelector('#test'));
			let pic = reader.result; 
			
// 			document.querySelector('#test').setAttribute('src', reader.result);
// 			document.querySelector('#test').src= reader.result;
			對! 很有道理耶
			let html = `<li><img src=${reader.result}></li>`;
			document.querySelector('#p_display').innerHTML = html;
		})
		是喔@@ 這個我還沒嘗試到 被影響到
		
// 		for (let i = 0; i < this.files.length; i++) {
// 			let file = this.files[i];
// 			let reader = new FileReader();
// 			reader.readAsDataURL(file);
// 			reader.addEventListener('load', function() { 好像沒進來這耶
// 				我先寫多張 因為多張的話傳一張也會成功  一張~
// 				console.log(${reader.result});
// 				let html =`<li><img src="${reader.result}" class="preview"></li>`;
// 				document.querySelector('#p_display').insertAdjacentHTML('beforeend', html);
// 				document.querySelector('#p_display').innerHTML = html;
// 			})
// 		}
	});
		
		
		
</script>

</body>
</html>