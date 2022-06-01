<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.helloworld.dao.UserDAO" %>
<%@ page import="com.helloworld.model.User" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello 게시판만들기!</title>
</head>
<body>
	<%
	
		String id = request.getParameter("id");
		String password = request.getParameter("password");
	
		String userID = null;
		if (session.getAttribute("userId") != null){
			userID = (String) session.getAttribute("userId");
		}
		if (userID != null)	{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인 된 아이디 입니다.')");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(id, password);
		
		if (result == 1){	// 로그인 성공!
			User user = userDAO.findById(id);
			session.setAttribute("userId", id);//session 부여!
			session.setAttribute("user", user);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
		else if (result == 0){	
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if (result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디 입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if (result == -2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생하였습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
</body>
</html>