$(document).ready(function() {

    var blockReg = true;

    $('a#reg').click( function(event){
        event.preventDefault();
        $('#overlay_reg').fadeIn(400,
            function(){
                $('#modal_form_reg')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 200);
            });
    });

    $('#modal_close_reg, #overlay_reg').click( function(){
        $('#modal_form_reg')
            .animate({opacity: 0, top: '45%'}, 200,
                function(){
                    $(this).css('display', 'none');
                    $('#overlay_reg').fadeOut(400);
                }
            );
    });

    $('a#login').click( function(event){
        event.preventDefault();
        $('#overlay_login').fadeIn(400,
            function(){
                $('#modal_form_login')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 200);
            });
    });

    $('#modal_close_login, #overlay_login').click( function(){
        $('#modal_form_login')
            .animate({opacity: 0, top: '45%'}, 200,
                function(){
                    $(this).css('display', 'none');
                    $('#overlay_login').fadeOut(400);
                }
            );
    });



    $('#vpassword_reg').focusout(
        function () {
            if($('#vpassword_reg').val() != $('#vpasswordRepeat_reg').val()){
                blockReg = true;
                $('#vinvalidpass_reg').css('display', 'block');
            }else {
                blockReg = false;
                $('#vinvalidpass_reg').css('display', 'none');
            }
        }
    );

    $("#vlogin_reg").focusout(
        function()
        {
            var url = '/loginCheck/'+$('#vlogin_reg').val();
            //console.log($('#vname').val());
            $.ajax
            (
                {
                    type:'GET',
                    url: url,
                    contentType: "application/json",
                    dataType: 'json',
                    // data:JSON.stringify({login:$('#vlogin_reg').val()}),
                    cache:false,
                    success:function(result){
                        alert("ajax in process");
                        if(result == false) {
                            blockReg = true;
                            $('#vinvalidpass_reg').css('display', 'block');
                        }
                        else {
                            blockSubmit = false;
                            $('#vinvalidpass_reg').css('display', 'block');
                        }
                    },
                    error: function(request, status, error) {
                        var statusCode = request.status; // вот он код ответа
                        console.log(statusCode);
                    }
                }
            );
        }
    );


});

function regFunction() {
    if(blockReg == true){
        event.preventDefault();
    }
    else
    {
        $.ajax
        (
            {
                type:'PUT',
                url:'/registration',
                contentType: "application/json",
                dataType: 'json',
                data:JSON.stringify({login:$('#vlogin_reg').val(), password:$('#vpassword_reg').val()}),
                cache: false,
                async: false,
                success: function (result) {
                    alert("Ajax returned");
                    if (result == true) {
                        alert("Success");
                    } else {}
                },
                error: function(request, status, error) {
                    blockSubmit = true;
                    var statusCode = request.status; // вот он код ответа
                    console.log(statusCode);
                }
            }
        )
    }
}

function login() {
    $.ajax
    (
        {
            type:'PUT',
            url:'/login',
            contentType: "application/json",
            dataType: 'json',
            data:JSON.stringify({login:$('#vlogin_login').val(), password:$('#vpassword_login').val()}),
            cache: false,
            async: false,
            success: function (result) {
                alert("Ajax returned");
                if (result == true) {
                    alert("Success");
                } else {}
            },
            error: function(request, status, error) {
                blockSubmit = true;
                var statusCode = request.status; // вот он код ответа
                console.log(statusCode);
            }
        }
    )
}