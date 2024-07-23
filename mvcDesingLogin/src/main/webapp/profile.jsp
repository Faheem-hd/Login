
<%@page import="in.sp.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
User user= (User)session.getAttribute("session_user");

%>

<h3>Welcome</h3>
<h2>Name:<%=user.getName() %></h2>
<h2>Name:<%=user.getEmail() %></h2>
<h2>Name:<%=user.getCity() %></h2>

<a href="Logout">Logout</a>
</body>
</html>