<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="categorie library-main-page">
		<div class="container-fluid">
			<div class="row mt-3">
				<div class="col-sm-12 col-lg-12">
					<div class="activity-heading pl-3">
						<h2>Library</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-lg-12 col-xl-12">
					<div class="form-group has-search">
						<span class="fa fa-search form-control-feedback"><!--  --></span> 
					<input type="text" class="form-control form-rounded" id="catName" onkeyup="getCategories()" placeholder="Search Categories" />
					</div>
				</div>
			</div>
			<div id="libraryRow"></div>
						<div id="libraryRow1"></div>
			
		</div>
	</section>
	<script type="text/javascript">
	//to get categouries in library
  $(document).ready(function() {
	  getCategories();
  });
	  function getCategories(){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/getCategories",
			success: function(data){
				console.log(data);
				if(Object.keys(data.response).length === 0){
					$('#libraryRow1').text("No Data!!");
				}
				var htmlCont='<div class="row">';
				 $.each(data.response, function(index, categoury) {
					 var input = document.getElementById("catName");
					 var filter = input.value.toUpperCase();
					 if (categoury.categoryName.toUpperCase().indexOf(filter) > -1) {
						 htmlCont+='<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"><div class="bgsuccess cbe">'+
						 '<div class="cate-img icon">'+
						 '<img src="${pageContext.request.contextPath}/studt/iconImage?iconId='+categoury.id+'&iconScreen=category&imgPath='+categoury.imgPath+'&imgName='+categoury.imgName+'"></img>'+
						 '</div>'+
						'<h4>'+categoury.categoryName+'</h4><div class="library-viewmore-btn btn-view">'+
						' <form action="${pageContext.request.contextPath}/common/subCategories" method="POST">'+
		                   ' <input type="hidden" value="'+categoury.id+'" name="catId" id="catId"/>'+
		                      '  <button class="btn btn big-btn"> View More </button>'+
		                   ' </form></div></div></div>';
					 }
	             });
				 htmlCont+='</div>';
	              document.getElementById('libraryRow').innerHTML=htmlCont;        	  

			},
			error: function(){
				console.log("error occured");
			}
		});
	
  }
	</script>
</div>