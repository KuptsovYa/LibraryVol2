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
                    console.log(result);
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
});