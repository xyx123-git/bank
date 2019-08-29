<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="bean"  uri="http://struts.apache.org/tags-bean"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/dtree.css" type="text/css" />
<style type="text/css">
div {
			padding:0px;
			margin:0px;
		}
		
		body {
		 scrollbar-base-color:#AFEEEE;
		 scrollbar-arrow-color:#2F4F4F;
		 scrollbar-shadow-color:#00CED1;
		 padding:0px;
		 margin:auto;
		 text-align:center;
		 background-color:#F08080;
		}
		
		.bitem {
		  
		  background:url(images/131838496444664439.jpg);
		  height:26px;
		  line-height:26px;
		  text-align:center;
		  cursor:pointer;
		  background-repeat: no-repeat;
		  margin-bottom:70px;
			
		}
		.title{
			margin-left:2px;
			background-repeat:no-repeat;
			text-align:center;
			cursor:pointer;
			height:26px;
		  	line-height:26px;
	</style>
	<script language='javascript'>var curopenItem = '1';</script>
	<script language="javascript" type="text/javascript" src="js/menu.js"></script>
	<base target="main" />
</head>
<body target="main">
    <div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/Manager.do?work=beforeFreeze' target='main'><bean:message key="manager.freeze"/></a></tr><br></div>
  	</div>
    <div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/Manager.do?work=findAllLogs' target='main'><bean:message key="manager.checkout"/></a></tr><br></div>
  	</div>
   <div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/Manager.do?work=findAllFreezers' target='main'><bean:message key="manager.unfreeze"/></a></tr><br></div>
  	</div>
    <%--  <input type="button" name="freeze" value="冻结账户" onClick="check1()">
      <input type="button" name="查看所有流水" value="查看" onClick="check2()">
       <input type="button" name="warm" value="解冻账户" onClick="check3()">

      <script language="javascript">
	function check1(){
		var url="<%=path %>/beforeFreezeServlet";
		window.location.href=url;
	}
	function check2(){
		
		var url="<%=path %>/findAllTags";
		window.location.href=url;
	}
    function check3(){
		
		var url="<%=path %>/findAllFreezer";
		window.location.href=url;
	} --%>
   
    
	</script>
</body>
</html>