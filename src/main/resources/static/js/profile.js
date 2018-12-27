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
                alert("ajax in process");
                console.log(result);
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

    if($('#cpName').val() == "" ||
        $('#cpSecondName').val() == "" ||
        $('#cpMiddleName').val() == ""){
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
        if($('#cpName').val() == "" ||
            $('#cpSecondName').val() == "" ||
            $('#cpMiddleName').val() == ""){
            alert("Заполните все поля");
        }else {
            $('#personalMsg').hide();
            var personalInfo = {
                firstName: $('#cpName').val(),
                lastName: $('#cpSecondName').val(),
                middleName: $('#cpMiddleName').val()
            };
            console.log(personalInfo);
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
                        alert("ajax in process");
                        console.log(result);
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