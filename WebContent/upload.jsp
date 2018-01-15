<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var i=2;
	$("#addFile").click(function(){
		$(this).parent().parent().before("<tr><td>File"+
				i+":</td><td><input type='file' name='file"+
				i+"'/></td></tr><tr><td>Desc"+
				i+":</td><td><input type='text' name='desc"+
				i+"'/></td></tr>"
				+"<tr><td><input type='button' value='删除' id='delete"+
				i+"'></input></td></tr>");
		i++;
	});
	$("#delete2").click(function(){
		alert("hehe");
		var $tr=$(this).parent().parent();
		$tr.prev("tr").remove();
		$tr.remove();
	});
});

</script>
</head>
<body>
uploadServlet
	<form action="#" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>File1:</td>
				<td><input type="file" name="file1"/></td>
			</tr>
			<tr>
				<td>Desc1:</td>
				<td><input type="text" name="desc1"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"/></td>
				<td><input type="button" id="addFile" value="添加附件"/></td>
			</tr>
		</table>
	</form>




</body>
</html>