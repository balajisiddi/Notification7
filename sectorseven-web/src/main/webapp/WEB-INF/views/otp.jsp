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
                        <img src="../img/ennovation-logo.png" alt="ennovation-logo" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-5 ">
                    <div class="left-img">
                        <img src="../img/otp.png" alt="">
                    </div>
                </div>
                <div class="col-sm-12 col-md-7 ">
                    <div class="container login-sec forgotform">
                        <h2>Enter Your 4 digit recivery code</h2>
                        <form>
                            <div class="row">
                                <div class="col-md-12">                                 
                                    <div id="divOuter">
                                        <div id="divInner">
                                            <input id="partitioned" type="text" maxlength="4" required/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="button-container">
                                <a href="change-password.html">Submit</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </section>
   
</div>
 <script type="text/javascript">
 function countChar(val) {
     var len = val.value.length;
     if (len >= 500) {
       val.value = val.value.substring(0, 500);
     } else {
       $('#charNum').text(500 - len);
     }
   };
    function updateBio(){
    	var userId="${userId}";
    	var userType="${userType}";
    	var len = document.getElementById("desc").value.length;
    	alert("Asd"+len);
          if(len<100){
        	  document.getElementById("lengg").innerHTML='Please Enter Atleast 100 Characters';  
          }else{
        	  var ob = {};  
         		ob.description=document.getElementById('desc').value;
      		$.ajax({
      			type:"PUT",
      			url:"${pageContext.request.contextPath}/studt/updateDesc?userId="+userId+"&userType="+userType,
      			data: JSON.stringify(ob),
      		    'contentType': 'application/json',
      			success: function(data){
                     alert("Success")
                     window.location.reload();
      			},
      			error: function(){
      				console.log("error occured");
      			}
      		});  
          }
   		
	}
    </script>