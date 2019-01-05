$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });


    $.ajax
    (
        {
            type: 'GET',
            url: "/profile/getPersonal",
            success: function (result) {
                $('#cpName').val(result.firstName);
                $('#cpSecondName').val(result.lastName);
                $('#cpMiddleName').val(result.middleName);
                $('.panel-body').append("<input id=\"personalMsg\" value=\"Персональные данные можно изменять только один раз\" readonly='readonly'>");
                if ($('#cpName').val() == "" ||
                    $('#cpSecondName').val() == "" ||
                    $('#cpMiddleName').val() == "") {
                    $('#cpName').prop('readOnly', false);
                    $('#cpSecondName').prop('readOnly', false);
                    $('#cpMiddleName').prop('readOnly', false);
                    $('#changePersonalButton').prop('disabled', false);
                    $('#addbooksbtn').prop('disabled', true);
                } else {
                    $('#changePersonalButton').prop('disabled', true);
                    $('#addbooksbtn').prop('disabled', false);

                }
            },
            error: function (request, status, error) {
                var statusCode = request.status;
                console.log(statusCode);
            }
        }
    );



    $('#addbooksbtn').click(function () {
        var name = $('#cpName').val().slice(0, 1) + ".";
        var middleName = $('#cpMiddleName').val().slice(0, 1) + ". ";
        var author = name + middleName + $('#cpSecondName').val();
        $.ajax({
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            url: '/profile/addBook',
            dataType: 'json',
            data: JSON.stringify({
                author: author,
                title: $('#vbook_title').val(),
                content: $('#vbook_content').val()
            })
        }).fail(function (error) {
            console.log(error);
        }).done(function (data) {
            console.log(data);
            getAllBooks();
        })

    });

    $('#changePersonalButton').click(function addNewPersonalInfo() {

        alert("CLICK");
        alert($('#cpName').val() == "" ||
            $('#cpSecondName').val() == "" ||
            $('#cpMiddleName').val() == "");
        if ($('#cpName').val() == "" ||
            $('#cpSecondName').val() == "" ||
            $('#cpMiddleName').val() == "") {
            alert("Заполните все поля");
        } else {
            $('#personalMsg').hide();
            var personalInfo = {
                firstName: $('#cpName').val(),
                lastName: $('#cpSecondName').val(),
                middleName: $('#cpMiddleName').val()
            };
            $.ajax
            (
                {
                    type: 'POST',
                    url: "/profile/addPersonal",
                    headers: {
                        'Content-Type': 'application/json',
                        'dataType': 'json'
                    },
                    data: JSON.stringify(personalInfo),
                    success: function (result) {
                    },
                    error: function (request, status, error) {
                        var statusCode = request.status;
                        console.log(statusCode);
                    }
                }
            );
            $('#changePersonalButton').prop('disabled', true);
            $('#addbooksbtn').prop('disabled', false);
        }
    });

    var personal = false;
    $("#allBooksButton").prop('disabled', true);

    $("#personalBooksButton").click(function () {
        personal = true;
        $("#personalBooksButton").prop('disabled', true);
        $("#allBooksButton").prop('disabled', false);
        getAllBooks(personal);
    });

    $("#allBooksButton").click(function () {
        personal = false;
        $("#allBooksButton").prop('disabled', true);
        $("#personalBooksButton").prop('disabled', false);
        getAllBooks(personal);
    });

    $("#leftButton").click(function () {
            var value = parseInt($("#pageOfBooks").val()) - 1;
            $("#pageOfBooks").val(value);
            if ($("#pageOfBooks").val() < 0) {
                $("#pageOfBooks").val(0);
            }
            getAllBooks(personal);
        }
    );

    $("#rightButton").click(function () {
            var value = parseInt($("#pageOfBooks").val()) + 1;
            $("#pageOfBooks").val(value);
            getAllBooks(personal);
        }
    );

    function getAllBooks(personal) {
        $("#allBooksBody tr").remove();
        $.ajax
        (
            {
                type: 'POST',
                url: "/profile/getAllBooks",
                headers: {
                    'Content-Type': 'application/json',
                    'dataType': 'json'
                },
                data: JSON.stringify({
                    page: parseInt($("#pageOfBooks").val()),
                    personal: personal
                }),
                success: function (result) {
                    if (result.length == 0 && $("#pageOfBooks").val() != 0 ) {
                        var count = parseInt($("#pageOfBooks").val()) - 1;
                        $("#pageOfBooks").val(count);
                        getAllBooks();
                    }
                    var rows = 10, cols = 3;
                    for (var i = 0; i < rows; i++) {
                        $('#allBooksBody').append('<tr id="row' + i + '">');
                        for (var j = 0; j < cols; j++) {
                            $('#row' + i + '').append('<td>' + result[i][j] + '</td>');
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

    getAllBooks(personal);
});


