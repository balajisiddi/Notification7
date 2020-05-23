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
            <div class="row">
                <div class="col-sm-12 col-lg-12 ">
                    <div class="bgsuccess cbe">
                        <div class="row">
                            <div class="col-sm-12 col-lg-6 col-xl-6">
                                <div class="content">
                                    <h2>Say About Yourself</h2>
                                    <p><img src="../resources/images/bio.jpg" /></p>
                                </div>
                            </div>
                            <div class="col-sm-12 col-lg-6 col-xl-6">
                                <div class="bio-btn">
                                        <div class="form-group">
                                            <textarea class="form-control form-rounded" rows="5"
                                                placeholder="Enter text here 500 characters..."
                                                maxlength="500" id="desc" onkeyup="countChar(this)"></textarea>
                                                    <div id="charNum"></div>
                                                
                                        </div>
                                        <button onclick="updateBio()" class="btn con-subbtn">Submit <i class="fa fa-sign-in"
                                                aria-hidden="true"></i></button>
                                </div>
                            </div>
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
          if(len<100){
        	  openPopup('Please Enter Atleast 100 Characters');  
          }else{
        	  var ob = {};  
         		ob.description=document.getElementById('desc').value;
      		$.ajax({
      			type:"PUT",
      			url:"${pageContext.request.contextPath}/studt/updateDesc?userId="+userId+"&userType="+userType,
      			data: JSON.stringify(ob),
      		    'contentType': 'application/json',
      			success: function(data){
                    location.reload();
      			},
      			error: function(){
      				console.log("error occured");
      			}
      		});  
          }
   		
	}
       </script>