$(document).ready(function() {

     $('#addbooksbtn').click(function() {
         var book = {};
         book["author"] = $('#vbook_author').val();
         book["title"] = $('#vbook_title').val();
         book["content"] = $('#vbook_content').val();
         // console.log(book);
         // var tmp = JSON.parse(book);
         // console.log(tmp);
         $.ajax
         (
             {
                 type: 'POST',
                 url: '/addBook',
                 contentType: "application/json",
                 dataType: 'json',
                 data: JSON.stringify(book
                     // {
                     // author:$('#vbook_author').val(),
                     // title:$('#vbook_title').val(),
                     // content:$('#vbook_content').val()
                    // }
                 ),
                 cache: false,
                 success: function (result) {
                     alert($('#vbook_author').val() + "  " + $('#vbook_title').val() + "  " + $('#vbook_content').val());
                     alert("Ajax returned");
                     if (result) {
                         alert("Success");
                     } else {
                     }
                 },
                 error: function (request, status, error) {
                     var statusCode = request.status;
                     console.log(statusCode);
                     console.log(status);
                     console.log(error)
                 }
             }
         )
     });
});

function showAll() {

}