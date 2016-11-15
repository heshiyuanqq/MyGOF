<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>this is addUserPage!</title>
</head>
<body>
<h1>添加用户</h1>
		<form action="${pageContext.request.contextPath}/user/addUser.do" method="get">
				用户名:<input type="text" name="username"><br/>
				密码:<input type="text" name="password"><br/>
				年龄:<input type="text" name="age"><br/>
				地址:<input type="text" name="address"><br/>
				爱好:<select name="hobbys" multiple="multiple">
						<option value="music">听音乐</option>
						<option value="movie">看电影</option>
						<option value="walk">走路</option>
						<option value="run">跑步</option>
						<option value="climb">爬山</option>
						<option value="fly">飞翔</option>
						<option value="swim">游泳</option>
						<option value="fight">大家</option>
					</select>
				<input type="submit" value="添加">
		</form>
</body>
</html>