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
    <title>Show Orders</title>
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
            <h1>&nbsp&nbsp>&nbsp&nbspmy orders</h1>


            <s:if test="#orders == null">
                <h1>No Order</h1>
            </s:if>

            <s:else>

            <s:iterator value="#orders" var = "od" status="order">
            <div class="order" style="width:100%">
            <ul>
                    <table class="table table-condensed">
                        <tr>
                            <th field="orderid" width="10%">Order id</th>
                            <th field="userid:" width="10%">User id</th>
                            <th field="state" width="5%">State</th>
                        </tr>
                            <td><s:property value="orderid"/></td>
                            <td><s:property value="user.userid"/></td>
                            <td>
                                <s:if test="#od.pucharsed=='N'">Not Pucharsed</s:if>
                                <s:elseif test="#od.pucharsed=='C'">Canceled</s:elseif>
                                <s:else>Pucharsed</s:else>
                            </td>
                        </tr>


                    </table>
                    </ul>
                <table class="table table-condensed">
                    <tr>
                        <th field="bookname" width="20%">bookname</th>
                        <th field="author" width="10%">author</th>
                        <th field="amount" width="10%">amount</th>
                        <th field="price" width="15%">unit Price</th>
                        <th field="tot_price" width="15%">Price</th>
                    </tr>
                    <s:iterator value="books" var="booksorder" status="book">
                        <tr>
                            <td><s:property value="#booksorder.book.bookname"/></td>
                            <td><s:property value="#booksorder.book.author"/></td>
                            <td>
		       				<span class = "amount">
		       					<s:property value="#booksorder.amount"/>
		       				</span>
                            </td>

                            <td><s:property value="#booksorder.book.price/100.0"/></td>
                            <td class="price"><s:property value="#booksorder.book.price*#booksorder.amount/100.0"/></td>
                            <td>

                            </td>
                        </tr>
                    </s:iterator>
                </table>
                <s:if test="#od.pucharsed == 'N'">
                    <div class="detail_button"><a href="<%=path%>/order/action_cancelOrder?orderid=<s:property value="#od.orderid"/>">Cancle Order</a>
                    </div>
                    <div class="buy_now_button"><a href="<%=path%>/order/action_confirmOrder?orderid=<s:property value="#od.orderid"/>">Pay Order</a>
                    </div>
            </s:if>
            </div>
            </s:iterator>
            </s:else>
            <div id="tip"></div>

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
