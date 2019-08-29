<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${name }您已下线</h1>
<%session.invalidate(); %>
<a href="index.jsp">返回登录界面 </a>
</body>
</html>