// lo esta haciendo a un lado react, angular o vue

//funcional

$(function(){
    $("#cifrado").on("click", function(){
        var elhtml = $("#elhtml").val();
        var clave = $("#clave").val();
        //proceso
        var encriptado = CryptoJS.AES.encrypt(elhtml, clave).toString();
        $("#encriptado").val(encriptado);
        console.log(encriptado, clave);
    })
})