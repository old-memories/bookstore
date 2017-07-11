<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/icon.css">
    <link href="<%=path%>/css/templatemo_style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/bootstrap/css/bootstrap.css">
    <script type="text/javascript">
        const base_url = '<%= basePath%>';
    </script>
</head>
