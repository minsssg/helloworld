<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.List" %>
<%@ page import="com.helloworld.model.User" %>
<%@ page import="com.helloworld.dao.UserDAO" %>
<%@ page import="com.helloworld.model.Bbs" %>
<%@ page import="com.helloworld.dao.BbsDAO" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="Text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/post_ui.css">
<title>Hello World!</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>
	<%
		String userID = null;
		
		if (session.getAttribute("userId") != null) {
			userID = (String) session.getAttribute("userId");
		} 
		
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		}
		
		UserDAO userDAO = new UserDAO();
		
		User user = userDAO.findById(request.getParameter("id"));
	%>
	<nav class="navbar navbar-default" style="background-color: #E4EFFF">
		<div class="navbar-header">
			<img src="images\worldwide.png" width="30" height="30" align="left">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp"> <span
				style="font-weight: bold; font-size: 1.5em; color: #333333">
					Hello World </span></a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			
			<div class="col-lg-1" class="form-group" style="padding: 8px">
				<select name="opt" class="form-control">
					<option value="0">이름</option>
					<option value="1">지역</option>
				</select>
			</div>
			<div class="col-lg-3" class="form-group" style="padding: 8px;">
				<input type="text" class="form-control" placeholder="검색내용"
					name="keyword" maxlength="20"></input>
			</div>
			<div class="col-lg-1" style="padding: 8px;">
				<input type="submit" class="btn btn-primary form-control" value="검색"></input>
			</div>


			<%
				if (userID != null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
						<li><a href="profile.jsp?userID=<%=userID%>">내 정보</a></li>
					</ul></li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>

	<div class="container"
		style="background-color: #F7F9FF; border-radius: 2em">
		<div class="jumbotron" style="background-color: #F7F9FF">
			<div class="container" style="background-color: #F7F9FF">
				<div id="side_left">
					<form action="bbswrite.jsp" method="get">
						<input type="submit" class="btn btn-info" value="글쓰기">
					</form>
				</div> 
				<div id="side_rigth" style="background-color: #DEE1E8">
					<div id="profile_box" style="border-bottom: 1.5px solid gray">
						<p style="text-align: center;">
							<img class='profile' src="images/user.png" align="Middle">
							<%=user.getName()%>
						</p>
					</div>
					<% if(userID.equals(user.getId())) { %>
					<div id="content_box">
						<p align="right">
							<button type="button" id="newFile" class="btn btn-info"
								data-toggle="modal" data-target="#myModal">수정</button>
						</p>
				
						<!-- Modal -->
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog">
								<!-- Modal content -->
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">개인정보 수정</h4>
									</div>
									<form method="post" action="modify.jsp">
										<div class="modal-body">
											<input type="hidden" name="userID" value="<%=userID%>">
											<table style="width: 100%; border-spacing: 10px;">
												<tbody>
													<tr>
														<td>이름</td>
														<td><input type="text" class="form-control" name="userName"
															placeholder="#이름"></td>
													</tr>
													<tr>
														<td>Password</td>
														<td><input type="password" class="form-control" name="userPassword"
															placeholder="#비밀번호"></td>
													</tr>
													<tr>
														<td>성별</td>
														<td>
															<div class="btn-group" data-toggle="buttons">
															<label class="btn btn-primary active"> <input
																type="radio" name="userGender" autocomplete="off" value="남자"
																checked>남자
															</label> <label class="btn btn-primary"> <input type="radio"
																name="userGender" autocomplete="off" value="여자" checked>여자
															</label>
															</div>
														</td>
													</tr>
													<tr>
														<td>전화번호</td>
														<td><input class="form-control"
															name="userPhoneNumber" type="text" placeholder="#전화번호"></td>
													</tr>
												</tbody>
											</table>
										</div>

										<div class="modal-footer">
											<button type="submit" class="btn btn-default">확인</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">닫기</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<% } %>
						<p>ID:  <%=user.getId()%></p>
						<p>성별: <%=user.getGender()%></p>
						<p>전화번호: <%=user.getPhoneNumber()%></p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>