$('#interestsId').load('',{
	},
	function getUserInterests(){
		$.ajax({
			type:"GET",
			url:"../studt/getCategories",
			data:{
				userId:document.getElementsByName("userId").value,
				userType:document.getElementsByName("userType").value
			},
			success: function(data){
	          var htmlcc='<div class="container-fluid">'+
					'<div class="row">';
						$.each(data.response, function(i, interess) {
							htmlcc+='<div class="col-lg-2 col-md-2 col-sm-6">'+
								'<div class="bgsuccess" id="bgsuccess">'+
							    '<input type="checkbox" onclick="isChecked()" name="interr" id="cb'+interess.id+'" value="'+interess.id+'" />'+
								'<label  for="cb'+interess.id+'"><img src="../studt/iconImage?iconScreen=interest&amp;iconId='+interess.id+'&imgPath='+interess.imgPath+'&imgName='+interess.imgName+'" class="interest" alt="" /></label>'+
									'<h4>'+interess.categoryName+'</h4>'+
								'</div>'+
							'</div>';
						});
						htmlcc+='<div class="col-lg-12 col-md-12 col-sm-6">'+
						'<div class="interest-sub-btn pull-right">'+
							'<button onclick="saveInterest()" class="btn con-subbtn">Next</button>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>';
				document.getElementById('interestsId').innerHTML=htmlcc;
				$('#tt').on({
					  "click": function() {
					    $(this).tooltip({ items: "#tt", content: "Displaying on click"});
					    $(this).tooltip("open");
					  },
					  "mouseout": function() {      
					     $(this).tooltip("disable");   
					  }
					});
			},
			error: function(error){
				
				}
		});
	});
function isChecked()
{
	$("input[name='interr']").on('change', function(evt){
		var choosenid = $(this).attr('id');
	  if($("input[name='interr']:checked").length <= 5){
		$("#choosen"+choosenid).toggle();
		}
		if($("input[type=checkbox]:checked").length < 5){
			$("#check5").hide();	
		}
		else{
			$("#check5").show();
		}
		 if($("input[name='interr']:checked").length > 5){
			 $(this).prop('checked', false);
				openPopup("You are allowed to choose 5 interests only!!");

		 }
	 });
	
}
function saveInterest()
{
	var lenn=$("input[name='interr']:checked");
	if(lenn.length == 0){
		 $(this).prop('checked', false);
			openPopup("Please Select Atleast 1 Category");
	 }
	else{
	    var userInterests = [];
		for(var i = 0; i < lenn.length; i++){
			 var interets={};
	        if(lenn[i].checked){
	        	interets.id=lenn[i].value;
	        }
	        userInterests.push({"interests":interets});
	    }
		var obj={
				userInterests:userInterests
		}
	$.ajax({
		type:"POST",
		url:"../studt/saveInterests?userId="+document.getElementById("userId").value+"&userType="+document.getElementById("userType").value,
		data:JSON.stringify(obj),
	    contentType: "application/json",
		success: function(data){
//			openPopup(data.response);
       // window.location.href='../common/subInterests';
        location.reload();
		},
		error: function(){
			console.log("error occured");
		}
	});
	}
}
$('#subInterestsId').load('',{
},
function getSubUserInterests(){
	$.ajax({
		type:"GET",
		url:"../studt/getAllSubcategories",
		data:{
			userId:document.getElementById("userId").value,
			userType:document.getElementById("userType").value
		},
		success: function(data){
			 var htmlcc='';
					$.each(data.response, function(i, interess) {
						htmlcc+='<div class="row after-select-interests">'+
			              ' <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'+
		                   '<div class="heading">'+
		                  '     <h2>'+interess.title+'</h2>'+
		                 '  </div>'+
		              ' </div>'+
		           '</div> <div class="row mt-4">';
						$.each(interess.result, function(j, subCat) {
						htmlcc+='<div class="col-lg-2 col-md-2 col-sm-6">'+
						    '<input type="checkbox" onclick="isSubChecked()" name="subInterr" id="cb'+subCat.id+'" value="'+subCat.id+'"  class="check-with-label" />'+
							'<label class="label-for-check" for="cb'+subCat.id+'">'+subCat.name+'</label>'+
						'</div>';
						});
						htmlcc+='</div>';
					});
					htmlcc+='<div class="col-lg-12 col-md-12 col-sm-6">'+
					'<div class="interest-sub-btn pull-right">'+
						'<button onclick="saveSubInterest()" class="btn con-subbtn">Lets Go</button>'+
					'</div>'+
				'</div>';
			document.getElementById('subInterestsId').innerHTML=htmlcc;
			/*var htmm='';
			$.each(data.response, function(i, cat) {
				htmm+=
					'<div class="row after-select-interests">'+
	              ' <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'+
	                   '<div class="heading">'+
	                  '     <h2>'+cat.title+'</h2>'+
	                 '  </div>'+
	              ' </div>'+
	           '</div> <div class="row mt-4">';
				$.each(cat.result, function(j, subCat) {
					htmm+= 
						'<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">'+
			               '    <label tabindex="0" class="checkbox"  >'+
			                ' <input type="checkbox" value="'+subCat.id+'" tyle="display:none" id="cb'+subCat.name+'"  name="subInterests"   />'+subCat.name+'</label>'+
			              ' </div>';
			});
				htmm+= '</div>';
			});
			htmm+='<div class="col-lg-12 col-md-12 col-sm-6">'+
					'<div class="interest-sub-btn pull-right">'+
						'<button onclick="saveSubInterest()" class="btn btn-primary">Lets Go</button>'+
					'</div>'+
				'</div>';
			document.getElementById('subInterestsId').innerHTML=htmm;*/
		},
		error: function(){
			console.log("error occured");
		}
	});

});
function isSubChecked(){
	$("input[name='subInterr']").on('change', function(evt){
		var choosenid = $(this).attr('id');
	  if($("input[name='subInterr']:checked").length <= 5){
		$("#choosen"+choosenid).toggle();
		}
		if($("input[name='subInterr']:checked").length < 5){
			$("#check5").hide();	
		}
		else{
			$("#check5").show();
		}
		 if($("input[name='subInterr']:checked").length > 5){
			 $(this).prop('checked', false);
				openPopup("You are allowed to choose 5 interests only!!");
		 }
	 });
	}


function saveSubInterest()
{
	var lenn=$("input[name='subInterr']:checked");
	if(lenn.length == 0){
		 $(this).prop('checked', false);
			openPopup("Please Select Atleast 1 Interests");
	 }
	else if(lenn.length < 5){
		 $(this).prop('checked', false);
			openPopup("Please Select Atleast 5 Interests");
	 }
	else if((lenn.length == 5)){
		
	    var userSubInterests = [];
	    var result=[];
		for(var i = 0; i < lenn.length; i++){
			var inter={};
	        if(lenn[i].checked){
	        	inter.id=lenn[i].value;
	        }
	        result.push(inter);
	    }
		userSubInterests.push({result:result});
		var obj={
		};
		obj=userSubInterests;
	$.ajax({
		type:"POST",
		url:"../studt/saveAllRecSubcategories?userId="+document.getElementById("userId").value+"&userType="+document.getElementById("userType").value,
		data:JSON.stringify(obj),
	    contentType: "application/json",
		success: function(data){
       // alert("Saved Successfully");
        location.reload();
		},
		error: function(){
			console.log("error occured");
		}
	});
	}
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
/*function checkedCat(name){
alert("sa"+name)
$("input[name='subInterests']").on('change', function(evt){
var choosenid = $(this).attr('id');
if($("input[name='subInterests']:checked").length <= 5){
$("#choosen"+choosenid).toggle();
}
if($("input[name='subInterests']:checked").length < 5){
	$("#check5").hide();	
}
else{
	$("#check5").show();
}
 if($("input[name='subInterests']:checked").length > 5){
	 $(this).prop('checked', false);
	 alert("You are allowed to choose 5 interests only!!");
 }
 var lenn=$("input[name='subInterests']:checked");
 var htmm='<div class="row mb-5">';
	for(var i = 0; i < lenn.length; i++){
		 htmm+='<div class="col-sm-6 col-md-2 col-lg-2 col-xl-2 " >'+
	    ' <div>'+
	    '   <label tabindex="0"  class="checkbox active" for=""><input type="checkbox" value="valueofcheckbox" />'+lenn[i].value+'</label>'+
	  ' </div>'+
	' </div>';
		}
	htmm+='</div>';
	document.getElementById('subChecked').innerHTML=htmm;
});
}*/