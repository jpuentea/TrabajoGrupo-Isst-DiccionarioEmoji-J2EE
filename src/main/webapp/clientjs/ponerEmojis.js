/**
 * ponerEmojis.js
 */

var n = 0;

function insertarEmoji(emoji){ 
    var campo = document.getElementById('campo');
    campo.focus()
    campoInicial= campo.innerHTML
    campo.innerHTML = campoInicial + '<img width="43px" height="43px" src='+emoji.src+'>'; 
    
    var campo2 = document.getElementById('traduccion');
    var x = emoji.src;
    
    var pulsada = emoji.alt;
   
    if(n>0){
    	pulsada = pulsada.toLowerCase();
    }
    campo2.value += pulsada+" ";
    n++
} 

function borrar1(){
    document.getElementById("traduccionAEmoji").innerHTML = "Aqu&iacute; aparecer&aacute; la traducci&oacute;n.";
    document.getElementById("textareaATraducirEspEmo").value = "";
}

function borrar2(){
    document.getElementById("campo").innerHTML = "";
    document.getElementById("traduccion").value = "";
}
