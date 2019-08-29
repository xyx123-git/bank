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
<body>
    <div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/deposit.jsp' target='main'><bean:message key="user.deposit"/></a></tr><br></div>
  	</div>
  	<div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/withdrawal.jsp' target='main'><bean:message key="user.withdrawal"/></a></tr><br></div>
  	</div>
    <div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/User.do?work=inquiry' target='main'><bean:message key="user.inquiry"/></a></tr><br></div>
  	</div>
  	<div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/transfer.jsp' target='main'><bean:message key="user.transfer"/></a></tr><br></div>
  	</div>
  	<div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/User.do?work=findLog' target='main'><bean:message key="user.findlogs"/></a></tr><br></div>
  	</div>
  	<div class='bitem'>
  		<div class='title'><tr><a href='<%=path %>/User.do?work=exit' target="_parent"><bean:message key="user.exitsystem"/></a></tr><br></div>
  	</div>
     <%--  <input type="button" name="deposit" value="存款" onClick="check1()">
      <input type="button" name="withdrawal" value="取款" onClick="check2()">
      <input type="button" name="inquiry" value="查询" onClick="check3()">
      <input type="button" name="transfer" value="转账" onClick="check4()">
      <input type="button" name="findlog" value="查询明细" onClick="check5()">
      <input type="button" name="exit" value="退出" onClick="check6()">
      <script language="javascript">
	function check1(){
	  
		var url="<%=path %>/deposit.jsp";
		window.location.href=url;
	}
	function check2(){
		
		var url="<%=path %>/withdrawal.jsp";
		window.location.href=url;
	}
    function check3(){
		
		var url="<%=path %>/inquiryServlet";
		window.location.href=url;
	}
    function check4(){
		
		var url="<%=path %>/transfer.jsp";
		window.location.href=url;
	}
    function check5(){
		
		var url="<%=path %>/findlogServlet";
		window.location.href=url;
	}
    function check6(){
		
		var url="<%=path %>/exitServlet";
		window.location.href=url;
	} --%>
    
	</script>
</body>
</html>