<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello 게시판만들기!</title>
</head>
<body>
	<%
		System.out.println(session.getAttribute("id"));
		session.invalidate();
	%>
	<script>
		location.href = 'home.jsp';
	</script>
</body>
</html>