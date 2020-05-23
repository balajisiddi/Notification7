var sidemenuSize = "250px";
var mediaSize = "800";
var sideMenu = true;
var sidemenuToggled = false;
var count = 0;
$(document).ready(function(){
    $("#mg-sidenav").css("width",  sidemenuSize); 
    if ($(window).width() <= mediaSize){	
        $("#mg-content").css("margin-left", "0px");
        $("#mg-sidenav").css("left", "-" + sidemenuSize);  
        sideMenu = false;
    }
    else{
        $("#mg-content").css("margin-left", sidemenuSize);
        $("#mg-sidenav").css("left", "0"); 
        sideMenu = true;
    }
})
$(document).on('click','#menuToggle',function(){
    sidemenuToggled = true;
    count++;
    if (sideMenu == false) {
        $("#mg-content").css("margin-left", sidemenuSize);
        $("#mg-sidenav").css("left", "0"); 
        sideMenu = true;
    }
    else{
        $("#mg-content").css("margin-left", "0px");
        $("#mg-sidenav").css("left", "-" + sidemenuSize); 
        sideMenu = false;
    }
})

$(window).resize(function(){
     if(sidemenuToggled == false){
        if ($(window).width() <= mediaSize){	
            $("#mg-content").css("margin-left", "0px");
            $("#mg-sidenav").css("left", "-" + sidemenuSize); 
            sideMenu = false;
        }
        else if($(window).width() > mediaSize){
            $("#mg-content").css("margin-left", sidemenuSize);
            $("#mg-sidenav").css("left", "0"); 
            sideMenu = true;
        }
     }
    
     else if ((count % 2) == 0){
        if ($(window).width() <= mediaSize){	
                $("#mg-content").css("margin-left", "0px");
                $("#mg-sidenav").css("left", "-" + sidemenuSize); 
            }
            else if($(window).width() > mediaSize){
                    $("#mg-content").css("margin-left", sidemenuSize);
                    $("#mg-sidenav").css("left", "0"); 
                }
     }
        // if ($(window).width() <= mediaSize){	
        //     console.log('<=' + mediaSize);
        //     $("#mg-content").css("margin-left", "0px");
        //     $("#mg-sidenav").css("left", "-" + sidemenuSize); 
        // }
        // else if($(window).width() > mediaSize){
        //     console.log('>' + mediaSize);
        //     $("#mg-content").css("margin-left", sidemenuSize);
        //     $("#mg-sidenav").css("left", "0"); 
        // }	
});