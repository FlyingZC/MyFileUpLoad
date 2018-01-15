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
		//.before()在id为br的前面插入
		//prev("br")返回当前节点的前一个br节点
		//a.find("button"):寻找a节点的button子节点
		//$(this).parent("div").remove();
		$("#br").before("<div>File"+
				i+":<input type='file' name='file"+
				i+"'/><br/>Desc"+
				i+": <input type='text' name='desc"+
				i+"'/><br/><button>删除</button><br/></div>")
				.prev("div").find("button")
				.click(function(){$(this).parent("div").remove();
				i--;
				});
		i++;
	});
});

</script>
</head>
<body>
	<form action="uploadServlet" method="post" enctype="multipart/form-data">
		File1: <input type="file" name="file1"/>
		<br/>
		<br/>
		Desc1: <input type="text" name="desc1"/>
		<br/>
		<br id="br"/>
		<input type="submit" value="Submit"/>
		<input type="button" id="addFile" value="添加附件"/>
	</form>

</body>
</html>