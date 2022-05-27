<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="helloworld.model.User" %>
<%@ page import="helloworld.dao.UserDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id = "user" class="helloworld.model.User" scope="page"/>
<jsp:setProperty name="user" property="id"/>
<jsp:setProperty name="user" property="password"/>
<jsp:setProperty name="user" property="name"/>
<jsp:setProperty name="user" property="gender"/>
<jsp:setProperty name="user" property="phoneNumber"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello 게시판만들기!</title>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("id") != null){
			userID = (String) session.getAttribute("id");
		}
		if (userID != null)	{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인 된 아이디 입니다.')");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
			
		if (user.getId() == null || user.getPassword() == null || user.getName() == null
		|| user.getGender() == null || user.getPhoneNumber() == null ){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
			UserDAO userDAO = new UserDAO();
			int result = userDAO.join(user);
			String directory = application.getRealPath("/images/") + user.getId();
			// System.out.println(directory);
			boolean check;
			
			if (result == -1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디 입니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
			else {	// 회원가입 성공!
				session.setAttribute("id", user.getId());//session 부여!
				
				File f = new File(directory);
				check = f.mkdir();
				System.out.println(check);
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('회원가입 성공!')");
				script.println("location.href = 'main.jsp'");
				script.println("</script>");
			}
		}
	%>
</body>
</html>