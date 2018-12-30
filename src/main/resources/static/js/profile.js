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
            },
            error: function (request, status, error) {
                var statusCode = request.status;
                console.log(statusCode);
            }
        }
    );

    if ($('#cpName').val() == "" ||
        $('#cpSecondName').val() == "" ||
        $('#cpMiddleName').val() == "") {
        $('#cpName').prop('readOnly', false);
        $('#cpSecondName').prop('readOnly', false);
        $('#cpMiddleName').prop('readOnly', false);
        $('#changePersonalButton').prop('disabled', false);
    }

    $('#addbooksbtn').click(function () {

        var book = {
            author: $('#vbook_author').val(),
            title: $('#vbook_title').val(),
            content: $('#vbook_content').val()
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
                title: $('#vbook_title').val(),
                content: $('#vbook_content').val()
            })
        }).fail(function (error) {
            console.log(error);
        }).done(function (data) {
            console.log(data);
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
        }
    });

    $.ajax
    (
        {
            type: 'GET',
            url: "/profile/getAllBooks",
            success: function (result) {
                var rows = 10, cols = 3;
                for (var i = 0; i < rows; i++) {
                    $('#allBooks').append('<tr id="row'+ i +'">');
                    for (var j = 0; j < cols; j++) {
                        $('#row'+ i +'').append( '<td>' + result[i][j] + '</td>');
                    }
                    $('#allBooks').append('</tr>')
                }
                console.log(result);
            },
            error: function (request, status, error) {
                var statusCode = request.status;
                console.log(statusCode);
            }
        }
    );

});