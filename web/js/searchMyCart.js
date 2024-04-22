
$(document).ready(function () {



    $('#mySelect').change(function () {
        sendForm();
    });

    $('#mySelect1').change(function () {
        sendForm();
    });
    
    $('#mySelect2').change(function () {
        sendForm();
    });
    
    $('#input1').on('input', function () {
        sendForm();
    });
    $('#input2').on('input', function () {
        sendForm();
    });
    $('#input3').on('input', function () {
        sendForm();
    });
    $('#input4').on('input', function () {
        sendForm();
    });
    $('#input5').on('input', function () {
        sendForm();
    });
    $('#input6').on('input', function () {
        sendForm();
    });
    $('#input7').on('input', function () {
        sendForm();
    });
    $('#input8').on('input', function () {
        sendForm();
    });
    $('#input9').on('input', function () {
        sendForm();
    });
    $('#input10').on('input', function () {
        sendForm();
    });


    function sendForm() {
        var formData = $('#myForm').serialize();
        $.ajax({
            type: 'POST',
            url: 'searchMyCart',
            data: formData,
            success: function (response) {
                $("#infor").html(response);
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

});

 