<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="library-main">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12 col-lg-12">
                <div id="back"></div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row mt-3">
                <div class="col-sm-12 col-lg-12">
                    <div class="heading">
                        <h2>Am I The One?</h2>
                    </div>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-sm-12 col-lg-4">
                    <div class="left-img">
                        <img src="${pageContext.request.contextPath}/studt/getAmITheOneImage?subcategoryId=26&imgPath=/commonImages/amItheOne/2&imgName=am-i-the-one-bg.png" alt="am-i-the" />
                    </div>
                </div>
                <div class="col-sm-12 col-lg-8">
                    <div class="right-blogs">
                        <div class="row mb-2">
                            <div class="col-lg-12 mt-2">
                                <p class="pt-3 pl-3">
                                <div id="amITheOne"></div>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script type="text/javascript">
	$(document).ready(function() {
		getAmITheOne();
	  });
     function getAmITheOne(){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/getAmITheOne",
			data: {
				subcategoryId:"${subCatId}",
			},
			success: function(data){

				document.getElementById('amITheOne').innerHTML=data.response.description;        	  
			},
			error: function(){
				console.log("error occured");
			}
		});
	}
     document.getElementById('back').innerHTML='<form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
     ' <input type="hidden" value="${subCatId}" name="subCatId" id="subCatId"/>'+
     '  <button class="btn btn-info2">'+
      '<div class="round">'+
       '   <i class="fa fa-arrow-left" aria-hidden="true"></i>'+
      '</div>'+
 ' </button></form>';
	</script>
 </div>
