<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post">
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
	
</body>
</html>