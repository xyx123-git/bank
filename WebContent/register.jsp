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
   <form action="Register.do" method="post">
  <bean:message key="user.username"/>：<input type="text" name="name" value=""/>
   <bean:message key="user.password"/>：<input type="text" name="password" value=""/>
   <input type="submit" name="submit" value="<bean:message key="user.button.register"/>"/>
   </form>
</body>
</html>