<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2017/4/25
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Cart</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <tr id="heading">
                        <th field="bookid" style="display:none">bookid</th>
                        <!-- <th field="isbn">isbn</th> -->
                        <th field="bookname" width="25%">bookname</th>
                        <th field="author" width="15%" style="overflow: hidden;">author</th>
                        <th field="price" width="15%">price</th>
                        <th filed="amount" width="25%">amount</th>
                        <th filed="total" width="15%">total</th>
                        <th filed="operation" width="20%">operation</th>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a class="btn" id="order" role="button" href="<%=basePath%>order/action_createOrder">Order</a>
            </div>
        </div>
    </div>
</div>

