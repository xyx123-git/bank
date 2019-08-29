<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="bean"  uri="http://struts.apache.org/tags-bean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
您好：${sessionScope.name }
      <form action="User.do?work=withdrawal" method="post">
      <bean:message key="user.withdrawal.money"/>：<input type="text" name="leave" value=""/>
      <input type="submit" name="submit" value="取款"/>
      ${information }
      </form>
</body>
</html>