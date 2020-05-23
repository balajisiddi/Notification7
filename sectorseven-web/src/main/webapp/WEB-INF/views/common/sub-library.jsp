<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="library-inn">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12 col-lg-12">
				<div id="back"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 offset-lg-1 col-lg-10">
					<div class="form-group has-search">
						<span class="fa fa-search form-control-feedback">
							<!--  -->
						</span> <input type="text" class="form-control form-rounded"
							placeholder="Search" id="subCatName" onkeyup="getSubCategories()" placeholder="Search SubcategoryName"/>
					</div>
				</div>
				<div class="col-sm-12 offset-lg-1 col-lg-10">
					<div class="bgsuccess cbe">
						<div id="subLibraryRow"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript">
	$(document).ready(function() {
		getSubCategories();
	  });
     function getSubCategories(){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/getSubCategories",
			data: {
				catId:"${catId}",
			},
			success: function(data){
				console.log(data);
				if(Object.keys(data.response).length === 0){
					$('#subLibraryRow').text("No Data!!");
				}
				var htmlCont='<div class="row">';
				 $.each(data.response, function(index, subCategoury) {
					 var input = document.getElementById("subCatName");
					 var filter = input.value.toUpperCase();
					 if (subCategoury.name.toUpperCase().indexOf(filter) > -1) {
						
					 htmlCont+=
						'<div class="col-sm-6 col-lg-6">'+
                     '<div class="tracking-list">'+
                     '<div class="tracking-item"><div class="tracking-icon status-intransit">'+
                        ' <img src="${pageContext.request.contextPath}/studt/iconImage?iconId='+subCategoury.id+'&iconScreen=subCategory&imgPath='+subCategoury.id+'&imgName='+subCategoury.subcategoryImgName+''+
                           '  alt="events" />'+
                   '  </div>'+
                    ' <div class="tracking-content">'+
                   ' <form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
                   ' <input type="hidden" value="'+subCategoury.id+'" name="subCatId" id="subCatId"/>'+
                      '  <button class="btn btn-info2">'+subCategoury.name+'</button>'+
                   ' </form>'+
                    ' </div></div>'+
                    ' </div>'+
                 '</div>';
					 }
				 });
				 htmlCont+='</div>'
	              document.getElementById('subLibraryRow').innerHTML=htmlCont;        	  
			},
			error: function(){
				console.log("error occured");
			}
		});
	}
     document.getElementById('back').innerHTML='<form action="${pageContext.request.contextPath}/common/getCategories" method="POST">'+
     '  <button class="btn btn-info2">'+
      '<div class="round">'+
       '   <i class="fa fa-arrow-left" aria-hidden="true"></i>'+
      '</div>'+
 ' </button></form>';
	</script>
</div>