
    function show_cart()
    {
        $.ajax({
            url:base_url+'cart/action_cartInfo',
            dataType:'json',
            success:function(data)
            {
                console.log(data.cart);
                data = data.cart;

                $('#cart').modal('show');
                if(data.length == 0)
                    $('#cart').find('.modal-footer').find('#order').attr('disabled','disabled');
                else
                    $('#cart').find('.modal-footer').find('#order').removeAttr('disabled');

                $modal = $('#cart').find('.modal-body').find('.table');
                $modal.find("tr[id!='heading']").remove();
                for(var i=0; i < data.length; i++)
                {
                    console.log(data[i]);
                    $modal.append("<tr>\
    <td style='display:none'>"+data[i].bookid+"</td>\
    <td>"+data[i].bookname+"</td>\
    <td>"+data[i].author+"</td>\
    <td>"+data[i].price/100+"</td>\
    <td>"+ "<span class='btn'onclick='minusAmount(" + i + "," + data[i].bookid  + "," + data[i].price +
                        ")'>-</span>"+
                        "<span class='amount'>" + data[i].amount + "</span>" +
                        "<span class='btn' onclick='addAmount(" + i + "," + data[i].bookid + "," + data[i].price +
                        ")'>+</span></td>" +
                        "<td> <span class='price'>" + data[i].price*data[i].amount/100+"</span></td>\
    <td>" +
                        "<span class='btn' \
                                 onclick='removeBookInCart("+ i + "," + data[i].bookid + ")'>Remove</span></td></tr> "
                    );
                }
            }
        });
    }

function minusAmount(book_index, bookid, price) {
    // console.log(book_index, book_ID, price);
    book_index = parseInt(book_index)+1;
    price = parseInt(price);
    var tuple = $('#cart .modal-body').find('tr:eq('+book_index+')');
    var amount = tuple.find('.amount');
    var num = parseInt(amount.html());
    if(num == 1)
        return;
    $('.modify').addClass('disabled');
    $.post({
        url: base_url + "cart/action_changeAmountInCart",
        data: {
            'bookid':bookid,
            'change':-1
        },
        success: function(msg) {
            if(msg.success) {
                num -= 1;
                amount.html(num);
                tuple.find('.price').html(num*price/100);
            }
            $('.modify').removeClass('disabled');
        }
    });

}

function addAmount(book_index, bookid, price) {
    // console.log(book_index, book_ID);
    $('.modify').addClass('disabled');

    book_index = parseInt(book_index)+1;
    price = parseInt(price);
    var tuple = $('#cart .modal-body').find('tr:eq('+book_index+')');
    var amount = tuple.find('.amount');
    var num = parseInt(amount.html());

    $.post({
        url: base_url + "cart/action_changeAmountInCart",
        data: {
            'bookid':bookid,
            'change':1
        },
        success: function(msg) {
            if(msg.success) {
                num += 1;
                amount.html(num);
                tuple.find('.price').html(num*price/100);
            }
            $('.modify').removeClass('disabled');
        }
    });
}

function removeBookInCart(book_index, bookid) {
    console.log(book_index, bookid);
    $.post({
        url: base_url + "cart/action_removeBookInCart",
        data: {
            'bookid':bookid
        },
        success: function(msg) {
            if(msg.success) {
                book_index = parseInt(book_index)+1;
                $('#cart .modal-body').find('tr:eq('+book_index+')').remove();
                var remain = $('#cart .modal-body').find('tr').length;
                if(remain == 1) {
                    $('#order').addClass('disabled');
                }
            }
        }
    });
}
