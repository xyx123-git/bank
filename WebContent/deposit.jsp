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
        <form action="User.do?work=deposit" method="post">
        <bean:message key="user.deposit.money"/>：<input type="text" name="deposit" value=""/>
        <input type="submit" name="submit" value="存款" onClick=check()/>
        ${information }
        </form>
</body>
</html>