<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.File"%>
<%@ page import="com.helloworld.model.Bbs"%>
<%@ page import="com.helloworld.dao.BbsDAO"%>
<%@ page import="com.helloworld.model.User"%>
<%@ page import="com.helloworld.dao.UserDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="Text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/post_ui.css">
<title>Hello World!</title>

</head>
<body>
   <%
      String userID = null;
   	  BbsDAO bbsDAO = new BbsDAO();
   	  UserDAO userDAO = new UserDAO();
      long bbsID = 0L;

      if (session.getAttribute("userId") != null) {
         userID = (String) session.getAttribute("userId");
      }
      if (request.getParameter("bbsID") != null) {
         bbsID = Long.parseLong(request.getParameter("bbsID"));
      }
	
      System.out.println("user id = " + userID);
      Bbs bbs = bbsDAO.findById(bbsID);
      System.out.println("bbs = " + bbs);
      User user = userDAO.findById(bbs.getUserId());
      
      String date = bbs.getBbsDate();
      String user_path = application.getRealPath("/images/" + bbs.getUserId() + "/");
      String dir_path = user_path + bbs.getBbsDate();
      System.out.println(dir_path);
      File dir = new File(dir_path);
      
      System.out.println("dir: " + dir.isDirectory());
      
      String files[] = dir.list();
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
               <option value="0">??????</option>
               <option value="1">??????</option>
            </select>
         </div>

         <div class="col-lg-3" class="form-group" style="padding: 8px;">
            <input type="text" class="form-control" placeholder="????????????"
               name="keyword" maxlength="20"></input>
         </div>

         <div class="col-lg-1" style="padding: 8px;">
            <input type="submit" class="btn btn-primary form-control" value="??????"></input>
         </div>

         <%
            if (userID != null) {
         %>
         <ul class="nav navbar-nav navbar-right">
            <li class="dropdown"><a href="#" class="dropdown-toggle"
               data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">????????????<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li><a href="logoutAction.jsp">????????????</a></li>
                  <li><a href="profile.jsp?userID=<%=userID%>">??? ??????</a></li>
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
               <div style="text-align: left; padding-top: 5px">
                  <h2><%=bbs.getTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
               .replaceAll("\n", "<br>")%></h2>
               </div>

               <div
                  style="text-align: left; border-bottom: 1.5px solid gray; padding-top: 5px">
                  <p><%=date.substring(0, 4) + "???" + date.substring(4, 6) + "???" + date.substring(6, 8) + "???"
               + date.substring(8, 10) + "???" + date.substring(10, 12) + "???"%></p>
               </div>

               <div style="padding-top: 20px; padding-bottom: 20px">
               <%if(files != null && files.length > 0) { %>
                  <div id="myCarousel" class="carousel slide" data-ride="carousel"
                     style="width: 600px; text-align: right; display: block; margin: 0 auto">
                     <ol class="carousel-indicators">
                        <%
                           for (int i = 0; i < files.length; i++) {
                        %>
                        <li id="slide<%=i%>" data-target="#myCarousel"
                           data-slide-to="<%=i%>"></li>
                        <%
                           }
                        %>
                     </ol>
                     <div class="carousel-inner" style="width: 600px; height: 400px">
                        <%
                           for (int i = 0; i < files.length; i++) {
                        %>
                        <div id="item<%=i%>" class="item">
                           <img src="images/<%=bbs.getUserId() + "/" + date + "/" + files[i]%>" style="width: 600px; height: 400px">
                        </div>
                        <%
                           }
                        %>
                     </div>
                     <a class="left carousel-control" href="#myCarousel"
                        data-slide="prev"> <span
                        class="glyphicon glyphicon-chevron-left"></span>
                     </a> <a class="right carousel-control" href="#myCarousel"
                        data-slide="next"> <span
                        class="glyphicon glyphicon-chevron-right"></span>
                     </a>
                  </div>
				<%
               		}
				%>
                  <div style="text-align: left; padding-top: 10px; height: 250px">
                     <span style="font-size: 1.5em"><%= bbs.getContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %> </span>
                  </div>
				<%
					System.out.println("user = " + user);
				%>
                  <div style="padding-bottom: 20px">
                     <a href="profile.jsp?userID=<%= user.getId() %>" class="btn btn-primary">??????</a>
                     <%
                     	System.out.println("2. user id = " + userID);
                     	if(userID.equals(bbs.getUserId())) {
                     %>
                     <div style="float: right;">
                    	 <a href="update.jsp?bbsID=<%=bbsID %>" class="btn btn-primary" >??????</a>
                     	<a href="deleteAction.jsp?bbsID=<%=bbsID %>" class="btn btn-primary" >??????</a>
                     </div>
                     <% 
                     	}
                     %>
                  </div>
               </div>
            </div>
            
            <div id="side_rigth">
               <div id="profile_box" style="border-bottom: 1.5px solid gray">
                  <p style="text-align: center;" style="border-style: outset">
                  <%
                  	File img = new File(user_path);
                  %>
                     <img class='profile' src="images\user.png" align="Middle"
                        style="padding: 5px"> <a href="profile.jsp?userID=<%=user.getId()%>"><%=user.getName()%></a>
                  </p>
               </div>

               <div id="content_box">
                  <p>ID: <%=user.getId()%></p>
                  <p>??????: <%=user.getGender()%></p>
                  <p>????????????: <%=user.getPhoneNumber()%></p>
               </div>
            </div>
         </div>
      </div>
   </div>
   
   <script>
      var li = document.getElementById("slide0");
      var img = document.getElementById("item0");
      li.setAttribute("class", "active");
      img.setAttribute("class", "item active");
   </script>
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>
</body>
</html>