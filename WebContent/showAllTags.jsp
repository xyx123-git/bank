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
     <td>明细ID</td>
     <td>明细类型</td>
     <td>操作金额</td>
     <td>操作人ID</td>
     </tr>
     <c:forEach items="${tags }" var="l">
     <tr>
     <td>${l.id }</td>
     <td>${l.type }</td>
     <td>${l.money }</td>
     <td>${l.userid }</td>
     </tr>
     </c:forEach>
     </table>
</body>
</html>