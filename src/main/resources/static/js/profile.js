$(document).ready(function() {

     $('#addbooksbtn').click(function() {
         // var book = {};
         // book["author"] = $('#vbook_author').val();
         // book["title"] = $('#vbook_title').val();
         // book["content"] = $('#vbook_content').val();

         var book = {
             author: $('#vbook_author').val(),
             title:  $('#vbook_title').val(),
             content:$('#vbook_content').val()
         };
         console.log(book);
         var tmp = JSON.stringify(book);
         console.log(tmp);
         // console.log(book);
         // var tmp = JSON.parse(book);
         // console.log(tmp);
         // $.ajax
         // (
         //     {
         //         type: 'PUT',
         //         url: '/registration',
         //         dataType: 'json',
         //         contentType: "application/json",
         //         data:
         //             // JSON.stringify(
         //             // book
         //             {
         //                login: "login",
         //                password: "password"
         //             // author:$('#vbook_author').val(),
         //             // title:$('#vbook_title').val(),
         //             // content:$('#vbook_content').val()
         //             },
         //         // ),
         //         cache: false,
         //         async: false,
         //         success: function (result) {
         //             alert($('#vbook_author').val() + "  " + $('#vbook_title').val() + "  " + $('#vbook_content').val());
         //             alert("Ajax returned");
         //             if (result) {
         //                 alert("Success");
         //             } else {
         //             }
         //         },
         //         error: function (request, status, error) {
         //             var statusCode = request.status;
         //             console.log(statusCode);
         //             console.log(status);
         //             console.log(error)
         //         }
         //     }
         // )
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