$(document).ready(function() {

    var blockReg = true;

    $('a#go').click( function(event){
        event.preventDefault();
        $('#overlay').fadeIn(400,
            function(){
                $('#modal_form')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 200);
            });
    });

    $('#modal_close, #overlay').click( function(){
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200,
                function(){
                    $(this).css('display', 'none');
                    $('#overlay').fadeOut(400);
                }
            );
    });


    $('#vpasswordreg').focusout(
        function () {
            if($('#vpassword').val() != $('#vpasswordRepeat').val()){
                blockReg = true;
                $('#vinvalidpass').css('display', 'block');
            }else {
                blockReg = false;
                $('#vinvalidpass').css('display', 'none');
            }
        }
    );

    $("#vloginreg").focusout(
        function()
        {
            //console.log($('#vname').val());
            $.ajax
            (
                {
                    type:'PUT',
                    url:'/loginCheck',
                    contentType: "application/json",
                    dataType: 'json',
                    data:JSON.stringify({login:$('#vloginreg').val()}),
                    cache:false,
                    success:function(result){
                        alert("ajax in process");
                        if(result == false) {
                            blockReg = true;
                            $('#vinvalidpass').css('display', 'block');
                        }
                        else {
                            blockSubmit = false;
                            $('#vinvalidpass').css('display', 'block');
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
                type:'POST',
                url:'/registration',
                contentType: "application/json",
                dataType: 'json',
                data:JSON.stringify({login:$('#vloginreg').val(), password:$('#vpasswordreg').val()}),
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
