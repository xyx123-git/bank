<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
  <tr>
     <td>用户ID</td>
     <td>用户名</td>
     <td>余额</td>
     <td>是否被冻结</td>
     </tr>
     <c:forEach items="${userlist }" var="l">
     <tr>
     <td>${l.userid }</td>
     <td>${l.name }</td>
     <td>${l.amount }</td>
     <td>${l.flag }</td>
     </tr>
     </c:forEach>
     </table>
</body>
</html>