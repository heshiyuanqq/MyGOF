<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>this is listUser.jsp</title>
</head>
<body>

		<table border="1" cellpadding="0" cellspacing="0">
				<tr>
				    <td>id</td>
				    <td>用户名</td>
				    <td>密码</td>
				    <td>年龄</td>
				    <td>地址</td>
				    <td>爱好</td>
				</tr>
				
				<c:forEach items="${userList }" var="user">
					<tr>
						<td>${user.id }</td>
						<td>${user.username }</td>
						<td>${user.password }</td>
						<td>${user.age }</td>
						<td>${user.address }</td>
						<td>${user.hobbys }</td>
					</tr>
				</c:forEach>
		</table>
		
		
		<button onclick="addUser()">添加用户</button>
</body>

<script type="text/javascript">
		function  addUser(){
			window.location.href="${pageContext.request.contextPath}/user/addUserPage.do";
		}
</script>
</html>