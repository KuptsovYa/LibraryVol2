$(document).ready(function() {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

     $('#addbooksbtn').click(function() {

         var book = {
             author: $('#vbook_author').val(),
             title:  $('#vbook_title').val(),
             content:$('#vbook_content').val()
         };
         console.log(book);
         var tmp = JSON.stringify(book);
         console.log(tmp);
         $.ajax({
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/profile/addBook',
                dataType: 'json',
                data: JSON.stringify({
                    author: $('#vbook_author').val(),
                    title:  $('#vbook_title').val(),
                    content:$('#vbook_content').val()
                })
            }).fail(function (error) {
               console.log(error);
            }).done(function (data) {
                console.log(data);
            })

     });
});

function showAll() {

}

var f = function () {
    $.ajax({
        method: 'POST',
        headers: {
            'contentType': 'application/json',
            'dataType': 'json'
        },
        url: '/profile/addBook',
        // dataType: 'json',
        data: book
    }).fail(function (error) {
        console.log(error);
    }).done(function (data) {
        console.log(data);
    })
};