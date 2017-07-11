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


        function destroyOrder(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this order?',function(r){
                    if (r){
                        $.get({
                            url: '<%=path%>/admin/orders_destroyOrder?orderid='+row.orderid,
                            success: function (result) {
                                console.log(result);
                                if (result.success) {
                                    showTip('successfully deleted', 'success');
                                    $('#dg').datagrid('reload');	// reload the book data
                                } else {
                                    showTip('Only order canceled can be deleted!', 'danger');
                                    $('#dg').datagrid('reload');
                                    /*
                                    showTip(result.errorMsg, 'danger');
                                    $.messager.show({	// show error message
                                        title: 'Error',
                                        msg: result.errorMsg
                                    });
                                    */
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
<table id="dg" title="Orders" class="easyui-datagrid"
       url="<%=path%>/admin/orders_getOrders"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="orderid" width="10%">orderid</th>
        <th field="tot_price" width="20%">total price</th>
        <th field="pucharsed" width="5%">state</th>

    </tr>
    </thead>
</table>
<div id="toolbar">

    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-remove" plain="true" onclick="destroyOrder()">Delete Order</a>
</div>



<div id="tip"></div>
</body>
</html>
