<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title><s:property value="#title"/></title>
    <script src="<%=path %>/js/jquery-1.12.2.min.js"></script>
    <script src="<%=path %>/js/jquery.easyui.min.js"></script>
    <script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        function showTip(tip,type){
            var $tip = $('#tip');
            $tip.attr('class', 'alert alert-' + type).text(tip).css('margin-left', - $tip.outerWidth() / 2).fadeIn(500).delay(1000).fadeOut(500);
        }

        var b_url="<%=path%>/admin/books";
        var url;
        function newBook(){
            $('#dlg').dialog('open').dialog('setTitle','New Book').dialog('move',{top:50});
            $('#fm').form('clear');
            url = b_url + '_saveBook';
            console.log(url);
        }
        function editBook(){
            var row = $('#dg').datagrid('getSelected');
            console.log(row);
            for(var p in row) {
                row["book."+p] = row[p];
            }
            console.log(row);
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','Edit Book').dialog('move',{top:50});
                $('#fm').form('load',row);
                url = b_url + '_updateBook?bookid='+row.bookid;
                console.log(url);
                console.log(row.bookid);
            }
        }
        function saveBook(){
            console.log(url);
            $.ajax({
                url: url,
                type: "GET",
                data : $("#fm").serialize(),
                success: function(result){
                    // var result = eval('('+result+')');
                    if (result.errorMsg){
                        showTip(result.errorMsg,'danger');
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        console.log("saved");
                        showTip('saved','success');
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the Book data
                    }
                },
                error:function(xhr,status,error){
                    alert('status='+status+',error='+error);
                }
            });

        }
        function destroyBook(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                console.log(b_url + '_destroyBook?bookid='+row.bookid);
                $.messager.confirm('Confirm','Are you sure you want to destroy this book?',function(r){
                    if (r){
                        $.get({
                            url: b_url + '_destroyBook?bookid='+row.bookid,
                            success: function (result) {
                                console.log(result);
                                if (result.success) {
                                    showTip('removed', 'success');
                                    $('#dg').datagrid('reload');	// reload the book data
                                } else {
                                    showTip(result.errorMsg, 'danger');
                                    $.messager.show({	// show error message
                                        title: 'Error',
                                        msg: result.errorMsg
                                    });
                                }
                            },
                            error: function (xhr, status, error) {
                                alert('status=' + status + ',error=' + error);
                            }

                        });
                    }
                });
            }
        }
    </script>
</head>
<body>
<table id="dg" title="Books" class="easyui-datagrid"
     url="<%=path%>/admin/books_getBooks"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="bookid" width="10%">bookid</th>
        <th field="bookname" width="15%">bookname</th>
        <th field="author" width="15%">author</th>
        <th field="price" width="15%">price</th>
        <th field="imageid" width="15%">imageid</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="<%=path%>/admin/books_createBook" class="easyui-linkbutton"
       iconCls="icon-add" plain="true" >New Book</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-edit" plain="true" onclick="editBook()">Edit Book</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-remove" plain="true" onclick="destroyBook()">Remove Book</a>
</div>

<div id="dlg" class="easyui-dialog"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Book Information</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>bookname:</label>
            <input name="book.bookname" id="bookname" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>author:</label>
            <input name="book.author" id="author" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>price:</label>
            <input name="book.price" id="price" class="easyui-textbox" required="true">
        </div>
        <div class="fitem" hidden>
            <label>imageid:</label>
            <input name="book.imageid" id="imageid" class="easyui-textbox" required="true">
        </div>


    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linlbutton " iconCls="icon-ok" onclick="saveBook()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#dlg').dialog('close')" style="width:90px">Cancel</a>
</div>
<div id="tip"></div>
</body>
</html>
