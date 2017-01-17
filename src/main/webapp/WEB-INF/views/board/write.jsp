<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<title>Write Article</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

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

		$("#uploadedList").on("click", ".delBtn", function(event) {
			event.preventDefault();
			var that = $(this);
			
			$.ajax({
				url: "deleteFile",
				type: "post",
				data: {fileName:$(this).attr("href")},
				dataType:"text",
				success:function(result) {
					if(result=='deleted') {
						that.parent("li").remove();
					}
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
			
			that.get(0).submit();
		});
		
	});
</script>

</head>
<body>
<div class="col-md-6">
	<h2>글쓰기</h2>
	<form id="registerForm" action="/board/write" method="post">
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label>
			<input type='text' class="form-control" name='title' placeholder='Enter title'/>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Content</label>
			<textarea name='content' class="form-control" row="3" placeholder='Enter content'></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label>
			<input type='text' class="form-control" name='writer' value="${login.uid}" readonly/>
		</div>
		<div>
			<label>FileDrop Here</label>
			<div id="fileDrop"></div>
		</div>
		<div class="col-md-10"></div>
		<div class="col-md-1">
			<button type="submit" class="btn btn-default">글쓰기</button>
		</div>
	</form>
	
	
	
	<ul id="uploadedList">
	
	</ul>

</div>
	
</body>
</html>