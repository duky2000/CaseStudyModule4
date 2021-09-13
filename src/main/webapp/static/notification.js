$(document).ready(function(){




    var down = false;

    $('#bell').click(function(e){

        var color = $(this).text();
        if(down){

            $('#box').css('height','0px');
            $('#box').css('opacity','0');
            down = false;
        }else{

            $('#box').css('height','auto');
            $('#box').css('opacity','1');
            down = true;

        }

    });

    var down2 = false;

    $('#bell2').click(function(e){

        var color2 = $(this).text();
        if(down2){

            $('#box2').css('height','0px');
            $('#box2').css('opacity','0');
            down2 = false;
        }else{

            $('#box2').css('height','auto');
            $('#box2').css('opacity','1');
            down2 = true;

        }

    });



});