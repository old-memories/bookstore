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

        var b_url="<%=path%>/admin/categories";
        var url;
        function newCategory(){
            $('#dlg').dialog('open').dialog('setTitle','New Category').dialog('move',{top:50});
            $('#fm').form('clear');
            url = b_url + '_saveCategory';
            console.log(url);
        }
        function editCategory(){
            var row = $('#dg').datagrid('getSelected');
            console.log(row);
            for(var p in row) {
                row["category."+p] = row[p];
            }
            console.log(row);
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','Edit Category').dialog('move',{top:50});
                $('#fm').form('load',row);
                url = b_url + '_updateCategory?categoryid='+row.categoryid;
                console.log(url);
                console.log(row.userid);
            }
        }
        function saveCategory(){
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
        function destroyCategory(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                console.log(b_url + '_destroyCategory?categoryid='+row.categoryid);
                $.messager.confirm('Confirm','Are you sure you want to destroy this category?',function(r){
                    if (r){
                        $.get({
                            url: b_url + '_destroyCategory?categoryid='+row.categoryid,
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
<table id="dg" title="Categories" class="easyui-datagrid"
       url="<%=path%>/admin/categories_getAllCategory"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="categoryid" width="10%">categoryid</th>
        <th field="name" width="20%">name</th>

    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-add" plain="true" onclick="newCategory()">New Category</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-edit" plain="true" onclick="editCategory()">Edit Category</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-remove" plain="true" onclick="destroyCategory()">Remove Category</a>
</div>

<div id="dlg" class="easyui-dialog"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Category Information</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>name:</label>
            <input name="category.name" id="name" class="easyui-textbox" required="true">
        </div>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linlbutton" iconCls="icon-ok" onclick="saveCategory()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#dlg').dialog('close')" style="width:90px">Cancel</a>
</div>
<div id="tip"></div>
</body>
</html>
