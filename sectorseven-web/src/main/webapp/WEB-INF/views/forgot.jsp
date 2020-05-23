<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="contribution-bg bio">
        <div class="container-fluid">
        <div class="card forgotcontent">
            <div class="row">
                <div class="col-md-12">
                    <div class="forgot-logo">
                        <img src="./resources/images/ennovation-logo.png" alt="ennovation-logo" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-5 ">
                    <div class="left-img">
                        <img src="./resources/images/forgot-password.png" alt="">
                    </div>
                </div>
                <div class="col-sm-12 col-md-7">
                    <div class="container login-sec forgotform">
                        <h2>Forgot Your Password?</h2>
                        <p>Please Enter your Registered UserName
                            </p> 
                        <p>We will send a verification code to your registered mail id</p> 
                            <div class="row">
                                <div class="col-sm-12 col-md-12">
                                    <div class="form-group has-search" >
                                        <span class="fa fa-envelope-o form-control-feedback"></span>
                                        <input type="text" class="form-control form-rounded" id="userName" name="userName"
                                            placeholder="Enter Your UserName" required />
                    
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="button-container">
                               <button onclick="forgotPass()"> Next </button>
                            </div>
                                        <div class="button-container">
                              <div  id="message"></div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="hover_bkgr_fricc_OTP">
			<span class="helper"></span>
			<div>
				<div class="popupCloseButton">&times;</div>
				<h4>Verify OTP?</h4>
				<div class="md-form">
					<div class="form-group has-search">
                        <input type="text" id="otp" class="form-control form-rounded" placeholder="Type here....">
                                                <input type="hidden" id="userId" class="form-control form-rounded" placeholder="Type here....">
                                                <input type="hidden" id="userType" class="form-control form-rounded" placeholder="Type here....">
                        
                    </div>
				</div>
				<div class="hover_bkgr_fricc_footer">
					<button type="submit" class="btn con-subbtn " data-toggle="modal"
						data-target="#myModel" onclick="verifyOtp()">Send</button>
				</div>
				 <div class="button-container">
                              <div  id="message1"></div>
                            </div>
			</div>
		</div>
		<div class="hover_bkgr_fricc_PASS">
			<span class="helper"></span>
			<div>
				<div class="popupCloseButton">&times;</div>
				<h4>ChangePassword</h4>
				<div class="md-form">
					<div class="form-group has-search" id="show_hide_password">
					                        <input type="password" id="newPass" class="form-control form-rounded" placeholder="Type here...." >
                 <div class="input-group-addon">
					 <a onclick="passwordFunction()"><i class="fa fa-eye-slash"></i></a>
																							</div>
                    
                  </div>
                    <div class="form-group has-search" id="show_hide_passwordcnf">
					                        <input type="password" id="confirmPass" class="form-control form-rounded" placeholder="Type here....">
                    <div class="input-group-addon">
					 <a onclick="confirmPassFunction()"><i class="fa fa-eye-slash"></i></a>
								</div>
                    </div>
				</div>
				<div class="hover_bkgr_fricc_footer">
					<button type="submit" class="btn con-subbtn"  onclick="changePassword()">Send</button>
				</div>
				<div class="button-container">
                              <div  id="message2"></div>
                            </div>
	
			</div>
		</div>
    </section>
   
</div>
 <script type="text/javascript">

	 function passwordFunction(){
		 if($('#show_hide_password input').attr("type") == "text"){
	         $('#show_hide_password input').attr('type', 'password');
	         $('#show_hide_password i').addClass( "fa-eye-slash" );
	         $('#show_hide_password i').removeClass( "fa-eye" );
	     }else if($('#show_hide_password input').attr("type") == "password"){
	         $('#show_hide_password input').attr('type', 'text');
	         $('#show_hide_password i').removeClass( "fa-eye-slash" );
	         $('#show_hide_password i').addClass( "fa-eye" );
	     }	 
	 }
	 function confirmPassFunction(){
		 if($('#show_hide_passwordcnf input').attr("type") == "text"){
	         $('#show_hide_passwordcnf input').attr('type', 'password');
	         $('#show_hide_passwordcnf i').addClass( "fa-eye-slash" );
	         $('#show_hide_passwordcnf i').removeClass( "fa-eye" );
	     }else if($('#show_hide_passwordcnf input').attr("type") == "password"){
	         $('#show_hide_passwordcnf input').attr('type', 'text');
	         $('#show_hide_passwordcnf i').removeClass( "fa-eye-slash" );
	         $('#show_hide_passwordcnf i').addClass( "fa-eye" );
	     }	 
	 }
     
 
 function openVerifyOtpPopup(userId,userType){
	 document.getElementById('userId').value=userId;
	 document.getElementById('userType').value=userType;
    $('.hover_bkgr_fricc_OTP').show();
    $('.popupCloseButton').click(function(){
        $('.hover_bkgr_fricc_OTP').hide();
	});
 }
 function openChangePassPopup(userId,userType){
	 document.getElementById('userId').value=userId;
	 document.getElementById('userType').value=userType;
     $('.hover_bkgr_fricc_PASS').show();
   $('.popupCloseButton').click(function(){
       $('.hover_bkgr_fricc_PASS').hide();
	});
}
    function forgotPass(){
    	var obj={
    			username:document.getElementById("userName").value
    	};
      		$.ajax({
      			type:"POST",
      			url:"${pageContext.request.contextPath}/studt/forgotPassword",
      			data: JSON.stringify(obj),
      		    'contentType': 'application/json',
      			success: function(data){
      				console.log(data);
      				if(data.statusCode=='202'){
          				openPopup("Invalid UserName");
      				}
      				if(data.statusCode=='200'){
                        openVerifyOtpPopup(data.response.userId,data.response.userType);
      				}
      			},
      			error: function(){
      				console.log("error occured");
      			}
      		});  
	}
   
    function verifyOtp(){
		var obj={
				userId:document.getElementById("userId").value,
  				userType:document.getElementById("userType").value,
  				otp:document.getElementById("otp").value
		};
		var userId=document.getElementById("userId").value;
		var userType=document.getElementById("userType").value;
		var otp=document.getElementById("otp").value;
      		$.ajax({
      			type:"POST",
      			url:"${pageContext.request.contextPath}/studt/verifyOtp?userId="+userId+"&userType="+userType+"&otp="+otp+"&emailId=&phoneNo=",
      			success: function(data){
                     if(data.statusCode=='202'){
           				openPopup("You Entered Incorrect OTP");

       				}
       				if(data.statusCode=='200'){
                        openChangePassPopup(document.getElementById("userId").value,document.getElementById("userType").value);
       				}
      			},
      			error: function(){
      				console.log("error occured");
      			}
      		});  
	}
    function changePassword(){
    	var newpass=$('#newPass').val();
		   var confirmpass=$('#confirmPass').val();

		   if(newpass != confirmpass){
   				openPopup("Passwords Should Be Same");
		   }else{
			   
      		$.ajax({
      			type:"POST",
      			url:"${pageContext.request.contextPath}/studt/changePassword?userId="+document.getElementById("userId").value+"&userType="+document.getElementById("userType").value+"&password="+newpass,
      			success: function(data){
      				if(data.statusCode=='202'){
           				openPopup("Something Went Wrong");
       				}
       				if(data.statusCode=='200'){
          				//location.href='${pageContext.request.contextPath}/login';
       					openSucessPopup();
       				}
      			},
      			error: function(){
      				console.log("error occured");
      			}
      		});  
		   }
	}
    function openSucessPopup(){
        const swalWithBootstrapButtons = Swal.mixin({
        	 customClass: {
        		    confirmButton: 'btn btn-primary con-subbtn'
        		  },
        	buttonsStyling: false,
        	allowOutsideClick: false
        	})

        	swalWithBootstrapButtons.fire({
        	  title: 'Status',
 	      html:"<div>Password Changed Successfully</div>",
        	  confirmButtonText: 'OK',
        	  reverseButtons: true
        	}).then(function(result) {
        		if (result.value) {
        		location.href="${pageContext.request.contextPath}/login";
                 }
        	})
    } 
    function openPopup(val){
    	alert("sdf"+val);
        const swalWithBootstrapButtons = Swal.mixin({
        	 customClass: {
        		    confirmButton: 'btn btn-primary con-subbtn'
        		  },
        	buttonsStyling: false,
        	allowOutsideClick: false
        	})

        	swalWithBootstrapButtons.fire({
        	  title: 'Status',
 	      html:"<div>"+val+"</div>",
        	  confirmButtonText: 'OK',
        	  reverseButtons: true
        	}).then(function(result) {
        		if (result.value) {
        			window.location.href="${pageContext.request.contextPath}/login";
                 }
        	})
    } 
   
    </script>