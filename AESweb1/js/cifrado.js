//nuestras variables

var cadena = "Habia una vez un patito que decia miau miau miau y se cayo en una coladera y se lastimo la patita y llora T_T";
var clave = "patitocolorpatitocolorpatitocolorpatitocolor";

//la clave forzosamente necesita 16 , 24, 32 Padding PKCS5

//proceso

var cifrado = CryptoJS.AES.encrypt(cadena, clave);

var descifrado = CryptoJS.AES.decrypt(cifrado, clave);

document.getElementById("demo0").innerHTML = cadena;

document.getElementById("demo1").innerHTML = cifrado;

document.getElementById("demo2").innerHTML = descifrado;

document.getElementById("demo3").innerHTML = descifrado.toString(CryptoJS.enc.Utf8);