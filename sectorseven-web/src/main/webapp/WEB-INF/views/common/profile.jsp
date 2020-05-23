<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="student-profile">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-10 col-lg-10 student-view">
					<div class="row">
						<div class="col-sm-4 col-lg-4 col-xl-4">
							<div class="container">
								<input type="hidden" value="${userId}" name="userId" id="userId" />
								<input type="hidden" value="${userType}" name="userType"
									id="userType" />
								<div class="row">
									<div class="col-sm-12 col-lg-12">
										<div id="profilediv"></div>
									</div>
									<div class="col-sm-12 col-lg-12">
										<div class="bgsuccess profile-work">
											<ul class="nav nav-pills flex-column" id="myTab"
												role="tablist">

												<li class="nav-item"><a class="nav-link active"
													id="profile-tab" data-toggle="tab" href="#profile"
													role="tab" aria-controls="profile" aria-selected="true">
														<div class="icon parent">
															<img src="../resources/images/pp.png" class="img" />
														</div>
														<div class="profile-content">Personal Info</div>
												</a></li>
												<li class="nav-item"><a class="nav-link"
													id="contact-tab" data-toggle="tab" href="#contact"
													role="tab" aria-controls="contact" aria-selected="false">

														<div class="icon inrest">
															<img src="../resources/images/tl.png" class="img" />
														</div>
														<div class="profile-content">My Intrests</div>
												</a></li>
												<li class="nav-item"><a class="nav-link"
													id="password-tab" data-toggle="tab" href="#password"
													role="tab" aria-controls="password" aria-selected="false">

														<div class="icon inrest">
															<i class="fa fa-wrench" aria-hidden="true" style="color:white"></i>
														</div>
														<div class="profile-content">Change Password</div>
												</a></li>
											</ul>

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-8 col-lg-8 col-xl-8">
							<div class="container">
								<div class="row">
									<div class="col-md-12 col-lg-12 col-xl-12">
										<div class="emp-profile">
											<div class="tab-content profile-tab" id="myTabContent">
												<div class="tab-pane fade show active" id="profile"
													role="tabpanel" aria-labelledby="profile-tab">
													<div class="container">
														<div id="tab1"></div>
														<div class="row edit-buttons">
															<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 ">
																<!-- <div class="edit-btn">
                                                                        <a href="student-info-edit.html"
                                                                            class="btn btn-primary con-subbtn">
                                                                            <i class="fa fa-edit"
                                                                                aria-hidden="true"></i>
                                                                            Edit
                                                                        </a>
                                                                    </div> -->
															</div>
															<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">
																<div class="logout-btn">
																	<a href="../j_spring_security_logout" class="btn con-subbtn"> <i
																		class="fa fa-sign-out " aria-hidden="true"></i> Logout
																	</a>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="tab-pane fade" id="contact" role="tabpanel"
													aria-labelledby="contact-tab">
													<div class="container">
														<div id="tab2"></div>
													</div>
												</div>

												<div class="tab-pane fade" id="password" role="tabpanel"
													aria-labelledby="password-tab">
													<div class="container">
														<div class="row">
															<div class="col-md-12">
																<div class="forgotcontent change-content">
																	<div class="row ">
																		<div class="col-md-12">
																			<div class="forgot-logo">
																				<img src="../resources/images/ennovation-logo.png"
																					alt="logo" class="logo" />

																			</div>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-12 col-sm-12">
																			<div
																				class="container login-sec forgotform changepassword">
																				<div class="row">
																					<div class="col-md-12">
																						<h2>Reset Your Password</h2>
																						<p>
																							Just enter new password below and you're good to
																							go <i class="fa fa-smile-o" aria-hidden="true"></i>
																						</p>
																					</div>
																				</div>
																				<div class="row">
																					<div class="col-md-12">
																					<form>
																						<div class="form-group has-search">
																						<div class="input-group" id="show_hide_password">
																							<input type="password" id="newPass"
																								class="form-rounded"
																								placeholder="Password" required />
																								 <div class="input-group-addon">
																							 <a href=""><i class="fa fa-eye-slash"></i></a>
																							</div>
																						</div>
																						</div>
																						<div class="form-group has-search">
																						<div class="input-group" id="show_hide_passwordcnf">
																							<input type="password" id="confirmPass"
																								class="form-rounded"
																								placeholder="Confirm Password" required />
																									 <div class="input-group-addon">
																							 <a href=""><i class="fa fa-eye-slash"></i></a>
																							</div>
																							</div>
																						</div>
																						</form>
																					</div>
																				</div>
																				<div class="button-container">
																					<button onclick="changePassword()">Submit</button>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="hover_bkgr_fricc">
			<span class="helper"></span>
			<div>
				<div class="popupCloseButton">&times;</div>
				<h4>Enter Your
							Email</h4>
				<h6 id="alreadyThere"></h6>							
				<div class="md-form">
					<div class="form-group has-search">
                        <input type="email" class="form-control form-rounded" id="emailId" placeholder="Type here....">
                    </div>
				</div>
				<div class="hover_bkgr_fricc_footer">
					<button  class="btn con-subbtn trigger_popup_fricc_OTP" onclick="checkEmail()">Send</button>
				</div>
			</div>
		</div>
		
			<div class="hover_bkgr_fricc_mobile">
			<span class="helper"></span>
			<div>
				<div class="popupCloseButton">&times;</div>
				<h4>Enter your Phone
					Number</h4>
					<h6 id="mobalreadyThere"></h6>	
				<div class="md-form">
					<div class="form-group has-search">
                        <input type="text" class="form-control form-rounded" id="phoneNo" placeholder="Type here....">
                    </div>
				</div>
				<div class="hover_bkgr_fricc_footer">
					<button  class="btn con-subbtn trigger_popup_fricc_OTP"  onclick="checkMobile()">Send</button>
				</div>
			</div>
		</div>
		
		<div class="hover_bkgr_fricc_OTP">
			<span class="helper"></span>
			<div>
				<div class="popupCloseButton">&times;</div>
				<h4>Verify OTP?</h4>
				<h6 id="mobileOrmail"></h6>
				<div class="md-form">
					<div class="form-group has-search">
					                        <input type="hidden" id="email" class="form-control form-rounded" placeholder="Type here....">
					                        <input type="hidden" id="mobile" class="form-control form-rounded" placeholder="Type here....">
                        <input type="text" id="otp" class="form-control form-rounded" placeholder="Type here....">
                    </div>
				</div>
				<div class="hover_bkgr_fricc_footer">
					<button type="submit" class="btn con-subbtn " data-toggle="modal"
						data-target="#myModel" onclick="verifyOtp()">Send</button>
				</div>
			</div>
		</div>

		
	</section>
</div>
<script>
var parent=null;

$(window).load(function () {
	$(".trigger_popup_fricc").click(function(){
	       $('.hover_bkgr_fricc').show();
	    });
    $(".trigger_popup_fricc1").click(function(){
       $('.hover_bkgr_fricc').show();
       if(document.getElementById('userType').value=='ROLE_PARENT'){
    	   parent=0;
   	  }
    });
    $('.popupCloseButton').click(function(){
        $('.hover_bkgr_fricc').hide();
    });
});
$(window).load(function () {
	
    $(".trigger_popup_fricc2").click(function(){
       $('.hover_bkgr_fricc').show();
       if(document.getElementById('userType').value=='ROLE_PARENT'){
    	   parent=1;
   	  }
    });
    $('.popupCloseButton').click(function(){
        $('.hover_bkgr_fricc').hide();
    });
});

$(window).load(function () {
	 $(".trigger_popup_fricc_mobile").click(function(){
	       $('.hover_bkgr_fricc_mobile').show();
	    });
    $(".trigger_popup_fricc_mobile1").click(function(){
       $('.hover_bkgr_fricc_mobile').show();
       if(document.getElementById('userType').value=='ROLE_PARENT'){
    	   parent=0;
   	  }
    });
    $('.popupCloseButton').click(function(){
        $('.hover_bkgr_fricc_mobile').hide();
    });
   
});
$(window).load(function () {
    $(".trigger_popup_fricc_mobile2").click(function(){
       $('.hover_bkgr_fricc_mobile').show();
       if(document.getElementById('userType').value=='ROLE_PARENT'){
    	   parent=1;
   	  }
    });
    $('.popupCloseButton').click(function(){
        $('.hover_bkgr_fricc_mobile').hide();
    });
});
	 	  $(document).ready(function() {
		  getProfileDetails();
		  getMyInterests();
	  });
	 	  function openVerifyOtpPopup(mobileOrMail){
	 		  console.log(mobileOrMail);
 		       $('.hover_bkgr_fricc_OTP').show();
 		       if(mobileOrMail=="mobile"){
 		    	  $('#mobileOrmail').text("Check your mobile for the OTP!")
 		       }
 		       else{
 		    	  $('#mobileOrmail').text("Check your mail for the OTP!")
 		       }
  		    $('.popupCloseButton').click(function(){
  		        $('.hover_bkgr_fricc_OTP').hide();
	 		});

	 	  }
	 	  function checkEmail(){
	 		  console.log($('#emailId').val());
	 		 $.ajax({
		          type : "POST",
		          url : "${pageContext.request.contextPath}/common/checkEmail",
		          data: {
		        	  email: $('#emailId').val()
		          },
		          success : function(data) {
		        	  console.log(data);
		        	  if(data){
		        		  sendOtp();
		        	  }
		        	  else{
		        		  $('#alreadyThere').css("display", "show").fadeIn(500);
		        		  var alreadythere='<span>Email already Exists</span>';
		        		  $('#alreadyThere').html(alreadythere);
		        		  $("#emailId").focus(function(){
		        			  $('#alreadyThere').css("display", "hide").fadeOut(500);
		        			});
		        	  }
			          },
		          error : function(e) {
		              alert('Error: ' + e);
		          }
		      }).done(function(data) {
		          console.log(data);
		      });
	 		
	 	  }
	 	  
	 	 function checkMobile(){
	 		  console.log($('#emailId').val());
	 		 $.ajax({
		          type : "POST",
		          url : "${pageContext.request.contextPath}/common/checkMobile",
		          data: {
		        	  mobile: $('#phoneNo').val()
		          },
		          success : function(data) {
		        	  console.log(data);
		        	  if(data){
		        		  sendOtp();
		        	  }
		        	  else{
		        		  $('#mobalreadyThere').css("display", "show").fadeIn(500);
		        		  var alreadythere='<span>Mobile Number already Exists</span>';
		        		  $('#mobalreadyThere').html(alreadythere);
		        		  $("#phoneNo").focus(function(){
		        			  $('#mobalreadyThere').css("display", "hide").fadeOut(500);
		        			});
		        	  }
			          },
		          error : function(e) {
		              alert('Error: ' + e);
		          }
		      }).done(function(data) {
		          console.log(data);
		      });
	 		
	 	  }
	 	 function sendOtp(){
		      var phoneNo ;
		      var userId = document.getElementById('userId').value;
		      var userType = document.getElementById('userType').value;
		      var emailId;
		      if(document.getElementById('emailId')!=null ){
		    	  emailId=document.getElementById('emailId').value;
		            $('#email').val(document.getElementById('emailId').value); 
		      }
		       if(document.getElementById('phoneNo')!=null){
		    	  phoneNo=document.getElementById('phoneNo').value;
		      }
                if(!document.getElementById('phoneNo').value){
  		    	  phoneNo="";
                }
                if(!document.getElementById('emailId').value){
                	emailId="";
                  }
                   if(parent==null){
                	   parent="";
                   }
			  $.ajax({
		          type : "POST",
		          url : "${pageContext.request.contextPath}/studt/sendotp?&userId="+userId+"&userType="+userType+"&phoneNo="+phoneNo+"&emailId="+emailId+"&parent="+parent,
		          async : false,
		          success : function(data) {
	        		  
	        		       if(document.getElementById('phoneNo').value){
	        		    	   openVerifyOtpPopup("mobile");
     	        		       $('#mobile').val(document.getElementById('phoneNo').value);
	        	                }
        	                if(document.getElementById('emailId').value){
        	                	openVerifyOtpPopup("mail");
     	        		       $('#email').val(document.getElementById('emailId').value);
        	                  }
			          },
		          error : function(e) {
		              alert('Error: ' + e);
		          }
		      }).done(function(data) {
		          console.log(data);
		      });
	 		
	 	}
	 	function verifyOtp(){
		      var phoneNo ;
		      var userId = document.getElementById('userId').value;
		      var userType = document.getElementById('userType').value;
		      var otp = document.getElementById('otp').value;

		      var emailId;
		      if(document.getElementById('email')!=null ){
		    	  emailId=document.getElementById('email').value;
		      }
		       if(document.getElementById('mobile')!=null){
		    	  phoneNo=document.getElementById('phoneNo').value;
		      }
                if(!document.getElementById('mobile').value){
  		    	  phoneNo="";
                }
                if(!document.getElementById('email').value){
                	emailId="";
                  }
                if(parent==null){
             	   parent="";
                }
			  $.ajax({
		          type : "POST",
		          url : "${pageContext.request.contextPath}/studt/verifyOtp?&userId="+userId+"&userType="+userType+"&phoneNo="+phoneNo+"&emailId="+emailId+"&otp="+otp+"&parent="+parent,
		          async : false,
		          success : function(data) {
	        		  openPopup("Profile picture changed Successfully");
	        		  location.reload();
			          },
		          error : function(e) {
		              alert('Error: ' + e);
		          }
		      }).done(function(data) {
		          console.log(data);
		      });
	 		
	 	}
	 	function openPopup(val){
	 	    const swalWithBootstrapButtons = Swal.mixin({
	 	    	 customClass: {
	 	    		    confirmButton: 'btn con-subbtn'
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
	 	    	})
	 	} 
	   function getProfileDetails(){
		  var userId = document.getElementById('userId').value;
	      var userType = document.getElementById('userType').value;
		  $.ajax({
	          type : "GET",
	          url : "${pageContext.request.contextPath}/studt/getMyProfileInfo?&userId="+userId+"&userType="+userType,
	          async : false,
	          success : function(data) {
	        	  var htmlContent;
	        	  var htmlContent1;
        			  htmlContent='<div class="row"><div class="col-sm-12 col-lg-12"><div class="bgsuccess text-center">'+
        			  '<div class="row">'+
        			  '<div class="col-sm-12 col-lg-12">'+
        			  '<div class="profile-upload-img">'+
        			  '<div class="circle">'+
	        		  '<img src="${pageContext.request.contextPath}/studt/userImage?userId='+userId+'&userType='+userType+'&imgPath='+data.response.result.studentImgPath+'&imgName='+profile.studentImgName+'"'+
	        		   '   alt="" class="profile-pic"/>'+
	        		   '</div>'+
	        		   '<div class="p-image">'+
						'<i class="fa fa-camera upload-button"></i>'+
															'<input class="file-upload" type="file" multiple accept="image/*" id="fileUploader" onchange="changeImage()"/>'+
														'</div>'+
														'</div>'+
														'</div>'+
														'</div>'+
														'<div class="row">'+
														'<div class="col-sm-12 col-lg-12">'+
														'<div class="profile-content">';
		        		 if(userType.localeCompare("ROLE_STUDENT")==0 || userType.localeCompare("ROLE_MENTOR")==0 || userType.localeCompare("ROLE_SCHOOL_TEACHER")==0){
		        			 var nameContent='<h4>'+data.response.result.firstName+' '+data.response.result.lastName+'</h4>';
		        			 htmlContent+=nameContent;
		        		 }
	         		 if(userType.localeCompare("ROLE_STUDENT")==0){
	        		   var classContent='<P>'+data.response.result.studentClass+'</P>';
	        		   htmlContent+=classContent;
	         		    }
	         		 if(userType.localeCompare("ROLE_MENTOR")==0 || userType.localeCompare("ROLE_SCHOOL_TEACHER")==0){
		        		   var classContent='<P>'+data.response.result.expertise+'</P>';
		        		   htmlContent+=classContent;
		         		    }
	         		var closedDivTag='</div></div></div></div></div>';  
	         		htmlContent+=closedDivTag;
	        		 if(userType.localeCompare("ROLE_STUDENT")==0 || userType.localeCompare("ROLE_MENTOR")==0 || userType.localeCompare("ROLE_SCHOOL_TEACHER")==0){
        		   htmlContent1='<div class="row">'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <label>User ID</label>'+
        		 '</div>'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <p>'+data.response.result.userName+'</p>'+
        		 '</div>'+
        		 '</div>'+
        		 '<div class="row">'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <label>First Name</label>'+
        		 '</div>'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <p>'+data.response.result.firstName+'</p>'+
        		 '</div>'+
        		 '</div>'+
        		 '<div class="row">'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <label>Last Name</label>'+
        		 '</div>'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <p>'+data.response.result.lastName+'</p>'+
        		 '</div>'+
        		 '</div>'+
        		 '<div class="row">'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		 '    <label>Email</label>'+
        		 '</div>'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <p>'+data.response.result.email+'</p>'+
                 ' <div class="edit-btn">'+
        		  '<a class="btn con-subbtn trigger_popup_fricc">'+
                   '<i class="fa fa-edit"'+
                      ' aria-hidden="true"></i> Edit'+
              ' </a>'+
              '</div>'+
        		 '</div>'+
        		 '</div>'+
        		 '<div class="row">'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		 '    <label>Mobile</label>'+
        		 '</div>'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <p>'+data.response.result.mobile+'</p>'+
        		  '<div class="edit-btn">'+
                  '<a class="btn con-subbtn trigger_popup_fricc_mobile">'+
                      '<i class="fa fa-edit"'+
                         ' aria-hidden="true"></i> Edit'+
                 ' </a>'+
              '</div>'+
        		 '</div>'+
        		 '</div>'+
        		 '<div class="row">'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <label>DateOfBirth</label>'+
        		 '</div>'+
        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
        		  '   <p>'+data.response.result.date_of_birth+'</p>'+
        		 '</div>'+
        		 '</div>';
	          }
        		 if(userType.localeCompare("ROLE_STUDENT")==0){
        			          	var addedHtmlContent2='<div class="row">'+
            		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
            		  '   <label>School</label>'+
            		 '</div>'+
            		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
            		  '   <p>'+data.response.result.school.schoolName+'</p>'+
            		 '</div>'+
            		 '</div>'+
            		 '<div class="row">'+
            		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
            		  '   <label>Class</label>'+
            		 '</div>'+
            		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
            		  '   <p>'+data.response.result.studentClass+'</p>'+
            		 '</div>'+
            		 '</div>'+
            		 '<div class="row">'+
            		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
            		  '   <label>Section</label>'+
            		 '</div>'+
            		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
            		  '   <p>'+data.response.result.section+'</p>'+
            		 '</div>'+
            		 '</div>'+
            		 '<div class="row">'+
            		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
            		  '   <label>Academic Year</label>'+
            		 '</div>'+
            		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
            		  '   <p>'+data.response.result.academicYear+'</p>'+
            		 '</div>'+
            		 '</div>';
        			 
        		htmlContent1+=addedHtmlContent2;		  
        		 }
        		 if(userType.localeCompare("ROLE_PARENT")==0){
	        		   htmlContent1='<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <label>User ID</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.userName+'</p>'+
	        		 '</div>'+
	        		 '</div>'+
	        		 '<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <label>Father Name</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.fatherName+'</p>'+
	        		 '</div>'+
	        		 '</div>'+
	        		 '<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <label>Mother Name</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.motherName+'</p>'+
	        		 '</div>'+
	        		 '</div>'+
	        		 '<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		 '    <label>Father Email</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.fatherEmail+'</p>'+
	                 ' <div class="edit-btn">'+
	                 '<a class="btn con-subbtn trigger_popup_fricc1">'+
	                   '<i class="fa fa-edit"'+
	                      ' aria-hidden="true"></i> Edit'+
	              ' </a>'+
	              '</div>'+
	        		 '</div>'+
	        		 '</div>'+
	        		 '<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		 '    <label>Mother Email</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.motherEmail+'</p>'+
	                 ' <div class="edit-btn">'+
	                 '<a class="btn con-subbtn trigger_popup_fricc2">'+
	                   '<i class="fa fa-edit"'+
	                      ' aria-hidden="true"></i> Edit'+
	              ' </a>'+
	              '</div>'+
	        		 '</div>'+
	        		 '</div>'+
	        		 '<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		 '    <label>Father Mobile</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.fatherMobile+'</p>'+
	        		  '<div class="edit-btn">'+
	        		  '<a class="btn con-subbtn trigger_popup_fricc_mobile1">'+
                      '<i class="fa fa-edit"'+
                         ' aria-hidden="true"></i> Edit'+
                 ' </a>'+
	              '</div>'+
	        		 '</div>'+
	        		 '</div>'+
	        		 '<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		 '    <label>Mother Mobile</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.motherMobile+'</p>'+
	        		  '<div class="edit-btn">'+
	        		  '<a class="btn con-subbtn trigger_popup_fricc_mobile2">'+
                      '<i class="fa fa-edit"'+
                         ' aria-hidden="true"></i> Edit'+
                 ' </a>'+
	              '</div>'+
	        		 '</div>'+
	        		 '</div>'+
	        		 '<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <label>City</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.city+'</p>'+
	        		 '</div>'+
	        		 '</div>'+
	        		 '<div class="row">'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <label>State</label>'+
	        		 '</div>'+
	        		 '<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">'+
	        		  '   <p>'+data.response.result.state+'</p>'+
	        		 '</div>'+
	        		 '</div>';
		          }
        		 
        		 $("#profilediv").append(htmlContent);
        		 $("#tab1").append(htmlContent1);

        		           },
	          error : function(e) {
	              alert('Error: ' + e);
	          }
	      }).done(function(data) {
	          console.log(data);
	      });
	  }
	   function getMyInterests(){
			  var userId = document.getElementById('userId').value;
		      var userType = document.getElementById('userType').value;
			  $.ajax({
		          type : "GET",
		          url : "${pageContext.request.contextPath}/studt/getInitialInterests?&userId="+userId+"&userType="+userType,
		          async : false,
		          success : function(data) {
		        	  var table=$('<div class="row"/>').appendTo($('#tab2'));
	        		   $.each(data.response, function(j, careers) {
	        		       var tab=$('<div class="col-sm-6 col-lg-4"/>').appendTo(table);
	        		       tab.append($('<div class="bgsuccess interest"><div class="inreset-img icon"><img src="${pageContext.request.contextPath}/studt/iconImage?iconId='+careers.id+'&iconScreen=category&imgPath='+careers.categoryImagPath+'&imgName='+careers.categoryImagName+'" alt=""></div><h4>'+careers.categoryName+'</h4></div>'));
	        		   });  
			          },
		          error : function(e) {
		              alert('Error: ' + e);
		          }
		      }).done(function(data) {
		          console.log(data);
		      });
		  }
	   function changeImage(){
		   var fd = new FormData();    
		    fd.append( 'file', $("#fileUploader")[0].files[0]);
			  var userId = document.getElementById('userId').value;
		      var userType = document.getElementById('userType').value;
// 		      alert("df"+JSON.stringify(fd));
			  $.ajax({
		          type : "POST",
		          enctype: 'multipart/form-data',
		          url : "${pageContext.request.contextPath}/studt/updateProfileImage?&userId="+userId+"&userType="+userType,
		          data: fd,
		          processData: false,  
		          contentType: false,
		          success : function(data) {
		        	  openPopup("Profile picture changed Successfully");
		        	  },
		          error : function(e) {
		              alert('Error: ' + e);
		          }
		      }).done(function(data) {
		          console.log(data);
		      });
		  }
	   function changePassword(){
		   var newpass=$('#newPass').val();
		   var confirmpass=$('#confirmPass').val();
		   if(newpass==''){
			   openPopup("Password Should not be empty");
			   return false;
		   }
		   if(confirmpass==''){
			   openPopup("confirmpass Should not be empty");
			   return false;
		   }
		   if(newpass != confirmpass){
			   openPopup("Passwords Should Be Same");
		   }else{
			   var userId= document.getElementById('userId').value;
	    	   var userType= document.getElementById('userType').value;
	    		  $.ajax({
			          type : "POST",
			          url : "${pageContext.request.contextPath}/studt/changePassword?userId="+userId+"&userType="+userType+"&password="+newpass,
			          async : false,
			          contentType: 'application/json',
			          success : function(data) {
			        	  openPopup(data.statusMessage);
		        	  },
			          error : function(e) {
			        	  alert("a"+JSON.stringify(e));
			        	  openPopup(e.statusMessage);
			          }
			      }).done(function(data) {
			          console.log(data);
			      });
		   }
		    
		  }
	   
	   function openPopup(val){
	       const swalWithBootstrapButtons = Swal.mixin({
	       	 customClass: {
	       		    confirmButton: 'btn btn-primary con-subbtn'
	       		  },
	       	buttonsStyling: false,
	       	allowOutsideClick: false
	       	})

	       	swalWithBootstrapButtons.fire({
	       	  title:"Status",	
		      html:"<div>"+val+"</div>",
	       	  confirmButtonText: 'OK',
	       	  reverseButtons: true
	       	}).then(function(result) {
	       		if (result.value) {
	       			if(val == 'Change Password Successfully'){
		       			location.href = "${pageContext.request.contextPath}/common/profile";
	       			}
	                }
	       	})
	   } 
	  </script>
<script>
	$(document).ready(function() {
		var readURL = function(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('.profile-pic').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		$(".file-upload").on('change', function() {
			readURL(this);
		});
		$(".upload-button").on('click', function() {
			$(".file-upload").click();
		});
	});
	
	   $("#show_hide_password a").on('click', function(event) {
	        event.preventDefault();
	        if($('#show_hide_password input').attr("type") == "text"){
	            $('#show_hide_password input').attr('type', 'password');
	            $('#show_hide_password i').addClass( "fa-eye-slash" );
	            $('#show_hide_password i').removeClass( "fa-eye" );
	        }else if($('#show_hide_password input').attr("type") == "password"){
	            $('#show_hide_password input').attr('type', 'text');
	            $('#show_hide_password i').removeClass( "fa-eye-slash" );
	            $('#show_hide_password i').addClass( "fa-eye" );
	        }
	    });
	   
	   $("#show_hide_passwordcnf a").on('click', function(event) {
	        event.preventDefault();
	        if($('#show_hide_passwordcnf input').attr("type") == "text"){
	            $('#show_hide_passwordcnf input').attr('type', 'password');
	            $('#show_hide_passwordcnf i').addClass( "fa-eye-slash" );
	            $('#show_hide_passwordcnf i').removeClass( "fa-eye" );
	        }else if($('#show_hide_passwordcnf input').attr("type") == "password"){
	            $('#show_hide_passwordcnf input').attr('type', 'text');
	            $('#show_hide_passwordcnf i').removeClass( "fa-eye-slash" );
	            $('#show_hide_passwordcnf i').addClass( "fa-eye" );
	        }
	    });
</script>