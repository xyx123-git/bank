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
<form action="Manager.do?work=warm" method="post">
   解冻用户：
   <select name="freezers" id="freezers">
	 <c:forEach items="${freezers}" var="u">
	  <option value="${u.name}">${u.name}</option>
	</c:forEach>
  </select>
  <input type="submit" name="submit" value="解冻"/>
  
  </form>
</body>
</html>