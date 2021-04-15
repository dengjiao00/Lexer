<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SelPatient-失败页面</title>
</head>
<style>
* {
	padding: 0;
	margin: 0;
}

body {
	background-image: url("./img/error1.jpg");
	background-size: 100% 100%;
	background-attachment: fixed;
	background-position: center;
}
</style>
<body>
	<div></div>
	<script>
	window.onload = function() {
		var res  = confirm('<%=request.getAttribute("outputMessage")%>');
			if (res) {
				console.log('ok')
				location.href = '/Lex/ReDirectServlet'
			}
		}
	</script>
</body>
</html>