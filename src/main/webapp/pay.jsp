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
    <title>Pay</title>

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
            <h1>&nbsp&nbsp>&nbsp&nbspPay the Order</h1>




                        <li>
                            Total Price: <span class="price">￥<s:property value="#totalPrice/100.0"/></span>
                        </li>
                        <li>
                        orderid: <span class="orderid"><s:property value="#orderid"/></span>
                        </li>
                    <h2>&nbsp&nbsp>&nbsp&nbspinformation</h2>
                    <table class="table table-bordered">
                        <tr>
                            <th width="15%">bookname</th>
                            <th width="15%">author</th>
                            <th width="15%">price</th>
                            <th width="15%">amount</th>
                            <th width="15%">total price</th>
                            <th>operation</th>
                        </tr>
                        <s:iterator value="#books">
                            <tr>
                                <td><s:property value="book.bookname"/></td>
                                <td><s:property value="book.author"/></td>
                                <td><s:property value="book.price/100.0"/></td>
                                <td><s:property value="amount"/></td>
                                <td><s:property value="amount*book.price/100.0"/></td>
                                <td>
                                    <div class="detail_button"><a href="<%=path%>/item/action_showBookByid?bookid=<s:property value="book.bookid"/>">Detail</a></div>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                    <div class="cleaner">&nbsp;</div>
            <div class="buy_now_button"><a href="<%=path%>/order/action_confirmOrder?orderid=<s:property value='#orderid'/>">Pay Order</a> </div>
            <div class="detail_button"><a href="<%=path %>/order/action_getUserOrders">View Orders</a> </div>





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
