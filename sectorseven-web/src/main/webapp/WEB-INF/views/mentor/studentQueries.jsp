    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="mg-content" id="mg-content"
xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
    <div class="ask-mentor">
<div id="askStudentDiv"></div>
				 <input type="hidden" value="${userId}" name="userId" id="userId"/>
                <input type="hidden" value="${userType}" name="userType" id="userType"/>

        <!-- partial -->
    </div>
    <script>
        (function ($) {

            $(document).ready(function () {

                var arr = []; // List of users

                $(document).on('click', '.msg_head', function () {
                    var chatbox = $(this).parents().attr("rel");
                    $('[rel="' + chatbox + '"] .msg_wrap').slideToggle('slow');
                    return false;
                });


                $(document).on('click', '.close', function () {
                    var chatbox = $(this).parents().parents().attr("rel");
                    $('[rel="' + chatbox + '"]').hide();
                    arr.splice($.inArray(chatbox, arr), 1);
                    displayChatBox();
                    return false;
                });
               $(document).on('click', '#sidebar-user-box', function () {
            	   var userID = $(this).attr("class");
                    var username = $(this).children().text();
                	var selectedId = $(this).children('#selectedId').text();
                    if ($.inArray(username, arr) != -1) {
                        arr.splice($.inArray(username, arr), 1);
                    }
                    arr.unshift(username);
                    getMessages(username,selectedId,userID);
                    displayChatBox();

                
                });

                $(document).on('keypress', 'button', function (e) {
                    if (e.keyCode == 13) {
                        var msg = $(this).val();
                        $(this).val('');
                        if (msg.trim().length != 0) {
                            var chatbox = $(this).parents().parents().parents().attr("rel");
                            $('<div class="msg-right">' + msg + '</div>').insertBefore('[rel="' + chatbox + '"] .msg_push');
                            $('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
                        }
                    }
                });

               /*  $(document).on('keypress', 'textarea', function (e) {
                    if (e.keyCode == 13) {
                        var msg = $(this).val();
                        $(this).val('');
                        if (msg.trim().length != 0) {
                            var chatbox = $(this).parents().parents().parents().attr("rel");
                            $('<div class="msg-right">' + msg + '</div>').insertBefore('[rel="' + chatbox + '"] .msg_push');
                            $('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
                        }
                    }
                }); */
                function displayChatBox() {
                    i = 0; // start position
                    j = 260;  //next position

                    $.each(arr, function (index, value) {
                        if (index < 5) {
                            $('[rel="' + value + '"]').css("right", i);
                            $('[rel="' + value + '"]').show();
                            i = i + j;
                        }
                        else {
                            $('[rel="' + value + '"]').hide();
                        }
                    });
                }
                function getMessages(username,selectedId,userID){
                	var chat = new Object();  

                    chat.type=document.getElementById('userType').value;
                    if(chat.type=='ROLE_STUDENT'){
                  	  chat.studentId=document.getElementById('userId').value;
                      chat.mentorId=selectedId;

                    }
                    if(chat.type=='ROLE_MENTOR'){
                  	  chat.mentorId=document.getElementById('userId').value;
                  	  chat.studentId=selectedId;

                    }
                    $.ajax({
                        type : "GET",
                        url : "${pageContext.request.contextPath}/studt/getMessages",
                        async : false,
                        data: chat,  
                        success : function(data) {
                      	  var statusHTML = '<div />';
                      	statusHTML += '<div class="msg_box" rel="' + userID + '">' +
                        '<div class="msg_head">' + username +
                        '<div class="close">x</div> </div>' +
                        '<div class="msg_wrap"> <div class="msg_body">';
                        var messsages =data.response.reverse();
                          $.each(messsages, function(i, messages) {
                        	  if(messages.type=='ROLE_MENTOR'){
                        	  statusHTML +='<div class="msg-right">'+messages.question+'</div>';
                        	  }
                        	  if(messages.type=='ROLE_STUDENT'){
                        	  statusHTML +='<div class="msg-left">'+messages.question+'</div>';
                        	  }
                          });
                          statusHTML +='<div class="msg_push"></div> </div>' +
                          '<div class="msg_footer"><textarea class="msg_input" rows="4" id="message"></textarea><input type="hidden" value="'+selectedId+'" id="selectedid"/><button onclick="sendChat('+selectedId+')"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button></div>  </div>  </div>';
                         $("body").append(statusHTML);

                        },
                        error : function(e) {
                            alert('Error: ' + e);
                        }
                    }).done(function(data) {
                        console.log(data);
                    });
                }
            });
        })(jQuery);

        function displayList() {
            $.fn.reverse = [].reverse;
            var trigger = $("#trigger"),
                mainTarget = $(".my-nav"),
                targetItem = $('.my-nav__item'),
                html = $("html");

            trigger.on("click", function (event) {
                mainTarget.toggleClass("reveal unreveal");

                targetItem.reverse().each(function (i, el) {
                    setTimeout(function () {
                        $(el).toggleClass("visible");
                    }, i * 68);
                })

                html.on("click", function () {
                    targetItem.removeClass("visible");
                    mainTarget.removeClass("reveal");
                });

                event.preventDefault();
                event.stopPropagation();
            });
        }
        var limit = 10;
        var offset = 0;
        $(document).ready(function() {
      	  getaskStudents();
      	  $("#moreStudents").click(function() {
      		  limit=limit+10;
      		  offset=offset+10;
      		getaskStudents();
             });
        });
         function getaskStudents(){
      	  var userId = document.getElementById('userId').value;
            var userType = document.getElementById('userType').value;
      	  $.ajax({
                type : "GET",
                url : "${pageContext.request.contextPath}/studt/getAllList?limit="+limit+"&offset="+offset+"&userId="+userId+"&userType="+userType+"&type=askStudent",
                async : false,
                success : function(data) {
                    var table = $('<ol class="activity-stream"/>').appendTo($('#askStudentDiv'));
                    if(data.response.length==0){
                    	table.append('No Data');
                    }
              	  $.each(data.response, function(i, student) {
                        $('<li id="sidebar-user-box" />').appendTo(table)
                 .append($('<img class="icon" src="${pageContext.request.contextPath}/studt/getImageUrl?userId='+student.id+'&type=askStudent&imgPath='+student.imgPath+'&imgName='+student.imgName+'"></img><span id="selectedId" style="display:none" >'+student.id+'</span><span id="slider-username" >'+student.name+'</span>'));
                    });
              	if(data.response.length>10){
              		table.append($('<div class="loadmore-btn">'+
    				'<button id="moreMentors" class="btn btn-primary con-subbtn">Load'+
    				'	More</button>'+
    			'</div>'));;
              	}
                   // alert('Success: ' + data);
                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            }).done(function(data) {
                console.log(data);
            });
        }
          function sendChat(selectedID) {
       		var chat = new Object();  
             chat.type=document.getElementById('userType').value;
               if(chat.type=='ROLE_STUDENT'){
             	  chat.studentId=document.getElementById('userId').value;
               	  chat.mentorId=selectedID;
                  chat.message = document.getElementById("message").value; 
               }
               if(chat.type=='ROLE_MENTOR'){
             	  chat.mentorId=document.getElementById('userId').value;
               	  chat.studentId=selectedID;
                  chat.message = document.getElementById("message").value; 

               }

         	  $.ajax({
                   type : "POST",
                   url : "${pageContext.request.contextPath}/studt/messageSending",
                   async : false,
                   data: chat,  
                   success : function(data) {
                	   location.reload();
                      // alert('Success: ' + data);
                   },
                   error : function(e) {
                       alert('Error: ' + e);
                   }
               }).done(function(data) {
                   console.log(data);
               });
            }

    </script>
    </div>