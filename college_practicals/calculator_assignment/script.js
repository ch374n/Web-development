var btnPlus    = document.getElementById("btn_plus"),
    btnMinus   = document.getElementById("btn_minus"),
    btnEqual   = document.getElementById("btn_equal"),
    btnDiv     = document.getElementById("btn_div"),
    btnMul 	   = document.getElementById("btn_mul"),
    display    = document.getElementById("display"),
    btnClear   = document.getElementById("btn_clear");


var num1 = 0,
    num2 = 0, 
    operator = '';

listener = function() {
    display.value += this["id"].slice(-1);
}

for(var i = 0; i <= 9; i++) {
    document.getElementById("btn_" + i).onclick = listener;
}


btnPlus.addEventListener("click", function() { display.value += '+';});
btnMinus.addEventListener("click", function() { display.value += '-';});
btnEqual.addEventListener("click", function() { display.value = eval(display.value);});
btnDiv.addEventListener("click", function() { display.value += '/';});
btnMul.addEventListener("click", function() { display.value += '*';});
btnClear.addEventListener("click", function() { display.value = '';});