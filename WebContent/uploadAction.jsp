<%@ page import="com.helloworld.util.CurrentLocalDateTime"%>
<%@ page import="com.helloworld.model.User" %>
<%@ page import="com.helloworld.dao.UserDAO" %>
<%@ page import="com.helloworld.model.Bbs" %>
<%@ page import="com.helloworld.dao.BbsDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>FILE UPLOAD ACTION</title>
</head>
<body>
	<%
		String userID = null;
		User user = null;
		UserDAO userDAO = new UserDAO();
		if (session.getAttribute("userId") != null){
			userID = (String) session.getAttribute("userId");
			user = userDAO.findById(userID);
		}
		
		if (userID == null)	{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		} else {
			System.out.println(userID);
			String image_folder = application.getRealPath("/images/")+userID;
			
			String now = CurrentLocalDateTime.now();
			String directory = image_folder +"/"+ now;	// userID/파일업로드한 시간
			File dic = new File(directory);//
			boolean chk = dic.mkdir(); // 폴더 만들기!
			System.out.println(chk);
			int maxSize = 1024 * 1024 * 100; // 총 100MB
			String encoding = "UTF-8";
			
			MultipartRequest multipartRequest = new MultipartRequest(
					request, directory, maxSize, encoding,
					new DefaultFileRenamePolicy()); // 
					
			if (multipartRequest.getParameter("bbsTitle").isEmpty() || multipartRequest.getParameter("bbsContent").isEmpty()
					|| multipartRequest.getParameter("lat").isEmpty() || multipartRequest.getParameter("lng").isEmpty()) {
				
				System.out.println(request.getParameter("bbsTitle"));
				System.out.println(request.getParameter("bbsContent"));
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안된 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else  {
			
				/*
				MultipartRequest multipartRequest = new MultipartRequest(
						request, directory, maxSize, encoding,
						new DefaultFileRenamePolicy()); //파일 업로드 실행
				*/
				
				System.out.println(multipartRequest.getParameter("bbsTitle"));
				System.out.println(multipartRequest.getParameter("bbsContent"));
				
				BbsDAO bbsDAO = new BbsDAO();
				Bbs bbs = new Bbs();
				bbs.setTitle(multipartRequest.getParameter("bbsTitle"));
				bbs.setUserId(userID);
				bbs.setUserName(user.getName());
				bbs.setContent( multipartRequest.getParameter("bbsContent"));
				
				String latitude = multipartRequest.getParameter("lat");
				String longitude = multipartRequest.getParameter("lng");
				
				if (latitude.isEmpty()) {
					latitude = "0";
				}
				
				if (longitude.isEmpty()) {
					longitude = "0";
				}
				
				bbs.setLatitude(Double.parseDouble(latitude));
				bbs.setLongitude(Double.parseDouble(longitude));
				
				bbs.setBbsDate(now);
				
				int result = bbsDAO.write(bbs);
				
				if (result == -1){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글쓰기에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {	// 글쓰기 성공!
					
					Enumeration fileNames = multipartRequest.getFileNames();
					
					while(fileNames.hasMoreElements()){
						//System.out.println(fileNames.hasMoreElements());
						String parameter = (String) fileNames.nextElement();
						String fileName = multipartRequest.getOriginalFileName(parameter);// 사용자가 업로드 하고자 하는 파일 네임
						String fileRealName = multipartRequest.getFilesystemName(parameter); // 시스템에 저장된 파일 이름
						if(fileName == null) continue;
						
						if(!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")){
							out.write("업로드 할 수 없는 확장자입니다.");
						}
						//FileDAO fileDao = new FileDAO();
						//int check = fileDao.upload(userID, fileName, fileRealName);
						out.write("유저ID: " + userID + "<br>");
						out.write("파일명: " + fileName + "<br>");
						out.write("실제 파일명: " + fileRealName + "<br>");	
					}
					
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'profile.jsp?userID=" + userID +"'");
					script.println("</script>");
				}
			}
		}
	%>
	<h2>업로드 내용</h2>
</body>
</html>