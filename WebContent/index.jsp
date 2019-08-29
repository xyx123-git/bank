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
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>登录页面</title>
<style type="text/css">
	body{margin:0px; padding:0px; width:100%;text-align:center;
		 }
	.top{
		
		font-size:50px;
		padding-top:150px;
		text-align:center;
		
	}
	.bg{
		padding-left:50px;
		text-align:center;
		background:url("<%=path %>/images/IMG_1336.JPG");
		background-repeat:no-repeat ;
		height:645px;
		background-size:100%
	}
	.mid{
		padding-top:30px;
		text-align:center;

	}
	</style>
</head>
<body>
<div class="bg" >
    	<div class="top">javaBank  
    	</div>
     <form action="Login.do" method="post" name="text">
     <div class="mid" >
    <tr>
       <td><bean:message key="user.username"/>：</td><td><input type="text" name="name" id="username" ></td>
       <br>
       <br>
    </tr>
    <tr>
    <td> <bean:message key="user.password"/>：<input type="password" name="password" value="" id="password"></td>
    <br><br>
     </tr>
     <tr>
     <td><input type="button" name="login" value="<bean:message key="user.button.login"/>" onClick="check1()"></td>
     <td><input type="button" name="register" value="<bean:message key="user.button.register"/>" onClick="check2()"></td>
     <td><input type="reset" name="reset" value="<bean:message key="user.button.reset"/>"></td>
     </tr>
     </div>
     </form>
    <br>
    <br>
     <tr><a href="<%=path %>/changelanguage.do?lang=zh">中文</a>
         <a href="<%=path %>/changelanguage.do?lang=en">英文</a>
     </tr>
      <script language="javascript">
      function check1(){
    	  if(document.getElementById("username").value==""){
    			alert("请输入用户名");
    			document.text.username.focus();
    		}
    	  if(document.getElementById("password").value==""){
    			alert("请输入密码");
    			return false;
    		} 
    	  
    	document.text.submit();
    	}

	function check2(){
		
		var url="<%=path %>/register.jsp";
		window.location.href=url;
	}
	</script>
</body>
</html>