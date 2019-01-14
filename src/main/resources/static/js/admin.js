$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    function getAllBooks() {
        $("#allBooksBody tr").remove();
        $.ajax
        (
            {
                type: 'POST',
                url: "/admin/getAllBooksImproper",
                headers: {
                    'Content-Type': 'application/json',
                    'dataType': 'json'
                },
                data: JSON.stringify({
                    page: parseInt($("#pageOfBooks").val())
                }),
                success: function (result) {
                    if (result.length == 0 && $("#pageOfBooks").val() != 0) {
                        var count = parseInt($("#pageOfBooks").val()) - 1;
                        $("#pageOfBooks").val(count);
                        getAllBooks();
                    }
                    var rows = 10, cols = 4;
                    for (var i = 0; i < rows; i++) {
                        $('#allBooksBody').append('<tr id="row' + i + '">');
                        for (var j = 0; j < cols; j++) {
                            $('#row' + i + '').append('<td id="' + j + '">' + result[i][j] + '</td>');
                        }
                        $('#row' + i + '').append('<td id="' + j + '"><button id="button'+ j +'">Удалить эту книгу</button></td>');
                        $('#allBooksBody').append('</tr>')
                    }
                },
                error: function (request, status, error) {
                    var statusCode = request.status;
                    console.log(statusCode);
                }
            }
        );
    }

    getAllBooks();

    $('#allBooksBody').on('click', 'tr', function () {
        var bookId = $(this).closest('tr').children('td:first').text();
        $.ajax({
            method: 'DELETE',
            url: '/admin/deleteBook/' + bookId
        }).fail(function (error) {
            console.log(error);
        }).done(function (data) {
            alert("Book with id " + bookId);
            getAllBooks();
        })
    });

    $("#leftButton").click(function () {
            var value = parseInt($("#pageOfBooks").val()) - 1;
            $("#pageOfBooks").val(value);
            if ($("#pageOfBooks").val() < 0) {
                $("#pageOfBooks").val(0);
            }
            getAllBooks();
        }
    );

    $("#rightButton").click(function () {
            var value = parseInt($("#pageOfBooks").val()) + 1;
            $("#pageOfBooks").val(value);
            getAllBooks();
        }
    );
});