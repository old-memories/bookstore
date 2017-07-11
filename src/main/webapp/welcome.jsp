<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2017/4/11
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html>
<head>
    <title>welcome</title>
</head>
<body>
    <h1>welcome: <s:property value="#session.user.username"></s:property> .</h1>
    <h1>Your Order has been committed.</h1>
    <h1><a href="<%=path%>/order/action_allOrders">Click here</a> to check your orders.</h1>

</body>
</html>
