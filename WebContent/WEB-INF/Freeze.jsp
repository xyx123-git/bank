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
  <form action="Manager.do?work=freeze" method="post">
   被冻结用户：
   <select name="names" id="names">
	 <c:forEach items="${names}" var="u">
	  <option value="${u.name}">${u.name}</option>
	</c:forEach>
  </select>
  <input type="submit" name="submit" value="冻结"/>
  
  </form>
</body>
</html>