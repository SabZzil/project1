<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write Article</title>

<style>
	#fileDrop {
		width:200px;
		height:50px;
		background-color:gray;
	}
</style>

<script type="text/javascript" src="/resources/js/upload.js"></script>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
	$(document).ready(function(){
		$("#fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();
		});
		
		$("#fileDrop").on("drop", function(event) {
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			
			var file = files[0];
			
			var formData = new FormData();
			
			formData.append("file", file);
			
			$.ajax({
				url: '/board/uploadAjax',
				data: formData,
				dataType:'text',
				processData: false,
				contentType: false,
				type: 'POST',
				success: function(data) {
					var fileInfo = getFileInfo(data);
// fileInfo = {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName}
					var html = "<li><img src='" + fileInfo.imgsrc + "'/>"
					+ "<a href='" + fileInfo.getLink + "'>" + fileInfo.fileName + "</a>"
					+ " " + "<a href='" + fileInfo.fullName + "' class='delBtn'>X</a>" 
					+ "</li>";
					
					$("#uploadedList").append(html);
				}
			});
		});
		
		$("#registerForm").submit(function(event) {
			event.preventDefault();
			
			var that = $(this);
			
			var str = "";
			
			$("#uploadedList .delBtn").each(function(index) {
				str += "<input type='hidden' name='files[" + index + "]' "
					+ "value='" + $(this).attr("href") + "'>";
			});
			
			that.append(str);
		});
		
	});
</script>

</head>
<body>

	<form id="registerForm" action="/board/write" method="post">
		<div>
			<label>Title</label>
			<input type='text' name='title' placeholder='Enter title'/>
		</div>
		<div>
			<label>Content</label>
			<textarea name='content' row="3" placeholder='Enter content'></textarea>
		</div>
		<div>
			<label>Writer</label>
			<input type='text' name='writer' placeholder='Enter writer'/>
		</div>
		<input type="submit" value="글쓰기"/>
	</form>
	
	<div>
		<label>FileDrop Here</label>
		<div id="fileDrop"></div>
	</div>
	
	<ul id="uploadedList">
	
	</ul>
	
</body>
</html>