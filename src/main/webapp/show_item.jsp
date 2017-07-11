<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2017/4/19
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%><html>
<%@include file="cart.jsp"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Book Detail</title>
    <script src="<%=path %>/js/jquery-1.12.2.min.js"></script>
    <script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path %>/js/cart.js"></script>
    <script type="text/javascript">
        function showTip(tip,type){
            var $tip = $('#tip');
            $tip.attr('class', 'alert alert-' + type).text(tip).css('margin-left', - $tip.outerWidth() / 2).fadeIn(500).delay(1000).fadeOut(500);
        }
        // after document loaded, bind events
        $(function() {
            console.log("hello");
            $('.amount-increase').bind('click', addBookNum);
            $('.amount-decrease').bind('click', minusBookNum);
        });

        function addBookNum()
        {
            amount = $('.amount-input').first().val();
            amount = parseInt(amount) + 1;
            $('.amount-input').first().val(amount);
        }

        function minusBookNum()
        {
            amount = $('.amount-input').first().val();
            if(amount > 1)
                amount = parseInt(amount) - 1;
            $('.amount-input').first().val(amount);
        }

        function add_to_cart(book_id)
        {

            //console.log(base_url+'item/action_addToCart');
            $.ajax({
                url: base_url + 'cart/action_addToCart',
                type: 'POST',
                data: {
                    'bookid': book_id,
                    'amount': $('.amount-input').first().val()
                },
                success: function (msg) {
                    //console.log(msg.success);

                    if (msg.success) {
                        $('#order').removeClass('disabled');
                        showTip('Added to your cart!', 'success');
                    }
                    else {
                        showTip('some thing go wrong', 'danger');
                    }
                },
                error:function(xhr,status,error){
                    alert('status='+status+',error='+error);
                }
            });
            //alert("receive messgae");

            //showTip('Wrong','danger');
        }
    </script>

    <style type="text/css">

        .amount-input {
            color: #666;
            font-size: 12px;
            margin: 0;
            padding: 3px 2px 0 3px;
            height: 26px;
            border: 1px solid #a7a6ac;
            width: 36px;
            line-height: 26px;
        }
        .amount-btn {
            display: inline-block;
            vertical-align: middle;
            text-align: center;
        }
        .amount-unit {
            vertical-align: middle;
            margin-left: 5px;
        }
        .amount-increase {
            width: 16px;
            height: 12px;
            overflow: hidden;
            cursor: pointer;
            border: 1px solid #a7a6ab;
            display: block;
            line-height: 12px;
            font-size: 16px;
            margin-bottom: 3px;
        }
        .amount-decrease {
            width: 16px;
            height: 12px;
            overflow: hidden;
            cursor: pointer;
            border: 1px solid #a7a6ab;
            display: block;
            line-height: 12px;
            font-size: 16px;
            margin-bottom: 3px;
        }
    </style>


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
            <li><a style="float: right" href="#" onclick="show_cart()">Cart</a></li>

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

    <div id="templatemo_header">
        <div id="templatemo_special_offers">
            <p>
                <span>25%</span> discounts for
                purchase over $ 40
            </p>
            <a href="#" style="margin-left: 50px;">Read more...</a>
        </div>


        <div id="templatemo_new_books">
            <ul>
                <li>Suspen disse</li>
                <li>Maece nas metus</li>
                <li>In sed risus ac feli</li>
            </ul>
            <a href="#" style="margin-left: 50px;">Read more...</a>
        </div>
    </div> <!-- end of header -->

    <div id="templatemo_content">

        <div id="templatemo_content_left">
            <div class="templatemo_content_left_section">
                <h1>Genre</h1>
                <ul>

                </ul>
            </div>



        </div> <!-- end of content left -->

        <div id="templatemo_content_right">
            <h1>bookname:<s:property value="#book.bookname"/></h1>
            <h2>category:</h2>
            <s:iterator value="#book_category" status="stat">
                <h2><s:property value="name"/></h2>
            </s:iterator>
            <ul>
                <h2>author:<s:property value="#book.author"/></h2>
                <li>Price: <s:property value='#book.price/100'/></li>
                <li>Amount:
                    <span class="amount-widget">
			    			<input type="text" class="amount-input" value="1" maxlength="8" title="input number of books:"/>
			    			<span class="amount-btn">
			                    <span class="fa fa-angle-up amount-increase" click="addBookNum()"></span>
			                    <span class="fa fa-angle-down amount-decrease" click="minusBookNum()"></span>
                            </span>
                    </span>
                </li>
                <button class="btn btn-default" onclick="add_to_cart(<s:property value='#book.bookid'/>)">add to cart</button>
            </ul>

           <h2>information about books</h2>
            <img class="img-show" id = "img-show" src="<%= path%>/image/action_viewImage?filename=<s:property value='#book.imageid'/>" alt="<s:property value='#book.bookname' />">
            <div id="tip"></div>


            <div class="cleaner_with_height">&nbsp;</div>

        </div> <!-- end of content right -->

        <div class="cleaner_with_height">&nbsp;</div>
    </div> <!-- end of content -->

    <div id="templatemo_footer">

        <a href="<%=basePath%>index">Home</a> | <a href="<%=path%>/admin/dashboard">Admin</a><br />
        <a>Copyright © 2017 <strong>Zhang Ziyang</strong></a>
    </div>
    <!--  Free CSS Template www.templatemo.com -->
</div> <!-- end of container -->
<!-- templatemo 086 book store -->
<!--
Book Store Template
http://www.templatemo.com/preview/templatemo_086_book_store
-->
</body>
</html>
