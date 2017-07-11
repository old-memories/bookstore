<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title><s:property value="#title"/></title>
    <script src="<%= path %>/js/jquery-1.12.2.min.js"></script>
    <script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path %>/js/jquery.easyui.min.js"></script>
    <script src="<%=path %>/js/iframeResizer.contentWindow.min.js"></script>
</head>
<body>
<div class="container-fiuld">
    <div style="width: 30%;min-width: 400px;margin-left: auto;margin-right: auto;">
        <h2 class="heading text-center" >
            Create New Book
        </h2>
        <form class="form-horizontal" method="post" action="<%=path %>/admin/books_saveBook" enctype="multipart/form-data">
                <label for="bookname" class="sr-only">bookname</label>
                    <input type="text" class="form-control" id="bookname" name="book.bookname" required>
                <label for="author" class="sr-only">author</label>
                    <input type="text" class="form-control" id="author" name="book.author" required>
                <label for="price" class="sr-only">price(Cents)</label>
                    <input type="text" class="form-control" id="price" name="book.price" required>
                <label for="category" class="sr-only">category</label>
                    <s:iterator value="#category">
                        <div class="checkbox">
                        <label>
                        <input type="checkbox" name="categoryName" value="<s:property value='categoryid'/>"><s:property value="name"/>
                        </label>
                        </div>
                     </s:iterator>
                <label for="myFile" class="sr-only">Upload image of the book</label>
                    <input type="file" name="myFile">
            <button type="submit" id="create" class="btn btn-primary" style="width:100%"><span>Create</span></button>
        </form>
    </div>
</div>
</body>
</html>
