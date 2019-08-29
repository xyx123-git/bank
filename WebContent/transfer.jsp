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
    <form action="User.do?work=transfer" method="post">
 
   <bean:message key="user.transfer.name"/>：<input type="text" name="towhom" value=""/>
   <bean:message key="user.transfer.money"/>：<input type="text" name="money" value=""/>
    <input type="submit" name="submit" value="转账"/>
    ${information }
    </form>
    ${information }
</body>
</html>