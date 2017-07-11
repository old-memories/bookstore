<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="../header.jsp"%>
<%@include file="../cart.jsp"%>



<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Book Store</title>
    <script src="<%=path %>/js/jquery-1.12.2.min.js"></script>
    <script src="<%=path %>/js/jquery.easyui.min.js"></script>
    <script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path %>/js/cart.js"></script>
    <script type="text/javascript">

        function addTab(title,url){
            if($('#tt').tabs('exists',title)){
                $('#tt').tabs('select',title);
            }
            else {
                var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
                $('#tt').tabs('add',{
                    title:title,
                    content:content,
                    closable:true
                });
            }

        }
    </script>
</head>
<body>
<div id="templatemo_container">
    <div id="templatemo_menu">
        <ul>
            <li><a href="<%=basePath%>index">Home</a></li>
            <s:if test="#session.user.role== 'admin' ">
                <li><a href="<%=path%>/admin/dashboard" class="current">admin</a></li>
            </s:if>            <li>
                <a><form class="search-form" role="search" method="get" action="<%=path %>/item/action_search">
                    <button hidden type="submit"></button>
                    <input type="text" name="searchName" id="searchName" class="form-control" placeholder="Search Books" style="width:100%">
                </form></a>


            </li>
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
            <li><a style="float: right" href="#" onclick="show_cart()">Cart</a></li>
        </ul>
    </div> <!-- end of menu -->




    <div id="templatemo_content">
        <div id="templatemo_content_left">
            <div class="templatemo_content_left_section">
                <h1>Navigator</h1>
                <ul class="nav">
                    <li><a href="#" onclick="addTab('Edit book','<%=path%>/admin/action_adminBook')">Edit book</a> </li>
                    <li><a href="#"onclick="addTab('Edit category','<%=path%>/admin/action_adminCategory')">Edit category</a> </li>
                    <li><a href="#" onclick="addTab('Edit user','<%=path%>/admin/action_adminUser')">Edit user</a></li>
                    <li><a href="#" onclick="addTab('Edit order','<%=path%>/admin/action_adminOrder')">Edit order</a></li>
                </ul>
            </div>



        </div> <!-- end of content left -->
        <div id="templatemo_content_right">

            <h1>Dashboard</h1>

                <div id="tt" class="easyui-tabs"  style="width:670px;height:800px;">
                    <div title="Help" data-options="iconCls:'icon-help'">
                        <p>This is the admin platform.</p>
                        <p>Book editing and user editing are available.</p>
                        <p>You may have to create a book in a new page.</p>
                        <p>Order deleting are available only after the chosen order being canceled by the user.</p>
                    </div>
                </div>

























        </div>
        <div class="cleaner_with_height">&nbsp;</div>
    </div> <!-- end of content -->

    <div id="templatemo_footer">

        <a href="<%=basePath%>index">Home</a> | <a href="admin.jsp">Admin</a><br />
        <a>Copyright © 2017 <strong>Zhang Ziyang</strong></a>
    </div>
    <!-- end of footer -->
</div> <!-- end of container -->
</body>
</html>