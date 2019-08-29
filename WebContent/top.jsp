<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="bean"  uri="http://struts.apache.org/tags-bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		.top { padding: 0;width:1280px; height: 244px;  no-repeat 0 100%;align:center;
			background-color:#DB7093;
		 }
		.text{	
			margin-left:450px;
			margin-top:20px;
			align:center;
			font-size:28px;
			font-style:normal;
			font-stretch:normal;
			width:350px;
			color:pink
		}
		.rigth{
			margin:10px;
			margin-left:790px;
			width:550px;
			color:red;
		}
	</style>
	 
  </head>
  <body class="top">
  	<table >
  		<div class="text">
  		<p >javaBank<bean:message key="top.welcome"/></p></div>
  		 
  	</table>
  </body>
</html>