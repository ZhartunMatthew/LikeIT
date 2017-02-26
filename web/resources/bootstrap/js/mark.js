var up = document.getElementById("up");
var down = document.getElementById("down");
var flag = false, flag2 = false;
up.onclick = function(){
    if (flag == false) {
        up.setAttribute("fill","#5bea4f");
        down.setAttribute("fill","#a5a5a5");
        flag = true;
        flag2=false;
    }
    else {
        up.setAttribute("fill","#a5a5a5");
        flag = false;
        flag2 = false;
    }
}
down.onclick = function(){
    if (flag2 == false) {
        down.setAttribute("fill","#ff7600");
        flag2 = true;
        flag = false;
        up.setAttribute("fill","#a5a5a5");
    }
    else {
        down.setAttribute("fill","#a5a5a5");
        flag2 = false;
        flag = true;
    }
}