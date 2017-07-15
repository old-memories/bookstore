<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2017/4/19
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<%@include file="cart.jsp"%>

<html>
<head>
    <title>Show Profile</title>
    <script src="<%=path %>/js/jquery-1.12.2.min.js"></script>
    <script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path %>/js/cart.js"></script>

</head>
<body>
<div id="templatemo_container">
    <div id="templatemo_menu">
        <ul>
            <li><a href="<%=basePath%>index">Home</a></li>
            <s:if test="#session.user.role== 'admin' ">
                <li><a href="<%=path%>/admin/dashboard">admin</a></li>
            </s:if>            <li>
            <a><form class="search-form" role="search" method="get" action="<%=path %>/item/action_search">
                <button hidden type="submit"></button>
                <input type="text" name="searchName" id="searchName" class="form-control" placeholder="Search Books" style="width:100%">
            </form></a>


        </li>
            <li><a style="float: right"  onclick="show_cart()">Cart</a></li>

            <s:if test="#session.user == null">
                <li><a style="float: right" href="<%=basePath%>register">Register</a></li>
                <li><a style="float: right" href="<%=path %>/auth/action_login">Login</a></li>
            </s:if>
            <s:else>
                <li><a style="float: right; color:red" href="<%=path%>/auth/action_logout">exit</a></li>
                <li><a  style="float: right" href="<%=path %>/order/action_getUserOrders">orders</a></li>
                <li><a  style="float: right" href="<%=path %>/user/action_getUserProfile">Welcome:
                    <s:property value="#session.user.username"/></a></li>
            </s:else>
        </ul>
    </div> <!-- end of menu -->



    <div id="templatemo_content">



        <div id="templatemo_content_right">
            <h1>&nbsp&nbsp>&nbsp&nbspmy profile</h1>


            <form class="form-horizontal" method="post" action="<%=path%>/user/action_updateUserProfile">
                    <label for="username" class="sr-only">username</label>
                        <p id="username" class="form-control"><s:property value="#session.user.username"/></p>
                <label for="message" class="sr-only">message</label>
                <textarea id="message" name="user.message" cols="30" rows="4"><s:property value="#user.message"/></textarea>
                <button type="submit" id="create" class="btn btn-primary" style="width:100%"><span>Change</span></button>
            </form>
            <h2>User image</h2>
                <img class="img-show" id = "img-show" src="<%= path%>/image/action_viewImage?filename=<s:property value='#user.imageid'/>" alt="<s:property value='#user.username' />">
            <form class="form-horizontal" method="post" action="<%=path%>/image/action_uploadUserImage" enctype="multipart/form-data">
                    <label for="myFile" class="sr-only">Upload image</label>
                        <input type="file" name="myFile"/>
                <button type="submit" id="create_image" class="btn btn-primary" style="width:100%"><span>Change</span></button>

            </form>










        </div> <!-- end of content right -->
        <div class="cleaner_with_height">&nbsp;</div>
    </div> <!-- end of content -->

    <div id="templatemo_footer">

        <a href="<%=basePath%>index">Home</a> | <a href="<%=path%>/admin/dashboard">Admin</a><br />
        <a>Copyright © 2017 <strong>Zhang Ziyang</strong></a>
    </div>
    <!-- end of footer -->
</div> <!-- end of container -->
</body>
</html>
