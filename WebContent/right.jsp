<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.util.* ,java.text.SimpleDateFormat" %> 
  
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
		body{
			background-color:#F08080;
			
		}
	</style>
  </head>
  <%!String getDate() 
{ 
Date now = new Date(); 
SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��"); 
return sdf.format(now); 
} 
%>
  <body>
  	<div class="nav" id="container">
	<div class="main_top">
		<ul class="main_top_title">
			<li>������������Ϣ</li>
		</ul>
	</div>
	<table class="main_con1" cellpadding="0" cellspacing="0">
		<tr class="main_con_title">	
			<td colspan="4">���������йز���</td>
		</tr>
		<tr id="maindetail1" onmouseover="changecolor(this);" onmouseout="changecolor(this);">	
			<td class="main_con_item1">ϵͳ���ƣ�</td>
			<td class="main_con_item2">ѧ����Ϣ����ϵͳ</td>
			<td class="main_con_item3">����������ϵͳ��</td>
			<td class="main_con_item4">windows XP</td>
		</tr>
		<tr id="maindetail1" onmouseover="changecolor(this);" onmouseout="changecolor(this);">	
			<td  class="main_con_item1">��������</td>
			<td  class="main_con_item2"><%=request.getServerName()%></td>
			<td  class="main_con_item3">�������˿ڣ�</td>
			<td  class="main_con_item4"><%=request.getServerPort()%></td>
		</tr>
		<tr id="maindetail1" onmouseover="changecolor(this);" onmouseout="changecolor(this);">	
			<td class="main_con_item1">ʹ��Э�飺</td>
			<td class="main_con_item2"><%=request.getProtocol() %></td>
			<td class="main_con_item3">ϵͳ�汾�ţ�</td>
			<td class="main_con_item4">1.4</td>
		</tr>
		<tr id="maindetail1" onmouseover="changecolor(this);" onmouseout="changecolor(this);">	
			<td class="main_con_item1">������ʱ�䣺</td>
			<td class="main_con_item2"><%=getDate()%></td>
			<td class="main_con_item3">����ֱ��ʣ�</td>
			<td class="main_con_item4">1024*768</td>
		</tr>
	</table>
	<table class="main_con1" cellpadding="0" cellspacing="0">
		<tr class="main_con_title">	
			<td colspan="4">ϵͳ��ݹ������</td>
		</tr>
		<tr>	
			<td colspan="2" class="main_con_item1">���ù������ӣ�</td>
		</tr>
	</table>
	<table class="main_con1" cellpadding="0" cellspacing="0">
		<tr class="main_con_title">	
			<td colspan="4">ϵͳά����ϵ��ʽ ����֧��</td>
		</tr>
		<tr id="maindetail1" onmouseover="changecolor(this);" onmouseout="changecolor(this);">	
			<td class="main_con_item1">ѧУ���ƣ�</td>
			<td class="main_con_item2">aaaaaa</td>
			<td class="main_con_item3">ͨѸ��ַ��</td>
			<td class="main_con_item4">aaaaaaaaaa</td>
		</tr>
		<tr id="maindetail1" onmouseover="changecolor(this);" onmouseout="changecolor(this);">	
			<td class="main_con_item1">�������룺</td>
			<td class="main_con_item2">330000</td>
			
		</tr>
		
	</table>
</div>
  </body>
</html>