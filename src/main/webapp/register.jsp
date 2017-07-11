<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2017/4/12
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>register</title>


    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<div id="templatemo_content_left">
    <div class="container">
        <h2 class="form-signin-heading">Create your Account!</h2>
        <ul>
            <form class="form-signin" action="<%=path %>/auth/action_register" method="post" >
                <label for="username" class="sr-only">Name:</label>
                <input  class="form-control" type="text" id="username" name="user.username"  placeholder="username" required="true"/>
                <label for="password" class="sr-only">Password:</label>
                <input class="form-control" type="password" id="password" name="user.password" placeholder="password" required="true" />
                <br/>
                <button type="submit" class="btn">register</button>
            </form>
            Having an account? Please <a href="<%=basePath%>login.jsp">login</a>
        </ul>
    </div>

</div> <!-- end of content left -->
</body>
</html>
