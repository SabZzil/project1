<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
<title>Login</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

</head>
<body>

<div class="col-md-6">
	<form action="/user/loginPost" method="post">
		<div class="form-group">
			<label for="exampleInputEmail1">아이디</label>
			<input type="text" class="form-control" name="uid" placeholder="ID"/>
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">비밀번호</label>
			<input type="password" class="form-control" name="upw" placeholder="PASSWORD"/>
		</div>
		
		<button type="submit" class="btn btn-default">Login</button>
	</form>
</div>

</body>
</html>