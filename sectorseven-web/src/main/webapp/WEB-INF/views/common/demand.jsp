<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="library-main demand-supply" id="demandAndSupply">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
				<form action="${pageContext.request.contextPath}/common/subCategory" method="POST">
				<input type="hidden" value="${subCatId}" name="subCatId" id="subCatId"/>
				<button class="btn btn-info2">
						<div class="round">
							<i class="fa fa-arrow-left" aria-hidden="true">
								<!--  -->
							</i>
						</div>
					</button>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<div class="activity-heading mt-2 pl-1">
						<h2>Demand And Supply</h2>
					</div>
				</div>
			</div>
			<hr class="hr-line" />
		</div>
		 <div class="container-fluid">
      <div class="row demand-sec" id="barChartsData">
        <div class="col-sm-12 col-lg-6">
          <div class="row interests">
            <div class="col-sm-12 col-lg-12">
              <div class="heading">
                <h2>No.of Employees</h2>
              </div>
            </div>
            <div class="col-sm-12 col-lg-12">
              <div class="chart-container">
                <canvas id="bar-chartcanvas"></canvas>
              </div>
            </div>
            <div class="col-sm-12 col-lg-12">
              <div class="chart-content mt-3">
                <p id="longTitle0">${careeSubCat.jobsDescription}</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-12 col-lg-6">
          <div class="row interests">
            <div class="col-sm-12 col-lg-12">
              <div class="heading">
                <h2>Salary</h2>
              </div>
            </div>
            <div class="col-sm-12 col-lg-12">
              <div class="chart-container">
                <canvas id="bar-chartcanvas1"></canvas>
              </div>
            </div>
            <div class="col-sm-12 col-lg-12">
              <div class="chart-content mt-3">
                <p id="longTitle1">${careeSubCat.salaryDescription}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
	</section>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
	<script type="text/javascript">
	
	
	$("#demandAndSupply").ready(function(){
		//options for cart
	    var options = {
	      responsive: true,
	      title: {
	        display: true,
	        position: "top",
	        fontSize: 18,
	        fontColor: "#111"
	      },
	      legend: {
	        display: true,
	        position: "bottom",
	        labels: {
	          fontColor: "#333",
	          fontSize: 16
	        }
	      },
	      scales: {
	        yAxes: [{
	          ticks: {
	            min: 0
	          }
	        }]
	      }
	    };
		//for no. of employees
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/getDemand",
			data: {
				subcategoryId: "${subCatId}",
				demandType: 0
			},
			success: function(data){
				console.log(data);
				if(Object.keys(data.response.year1Data.data).length === 0){
					$('#barChartsData').html('<div class="mtdata">No Data!</div>');
				}
				$('#longTitle0').append(data.response.longTitle); 
					//get the bar chart canvas
				    var ctx = $("#bar-chartcanvas");
				    //bar chart data
				    var data = {
				      labels: data.response.countries,
				      datasets: [
				        {
				          label: data.response.year1Data.yearLabel,
				          data: data.response.year1Data.data,
				          backgroundColor: [
				            data.response.year1Data.color,
				            data.response.year1Data.color,
				            data.response.year1Data.color,
				            data.response.year1Data.color,
				            data.response.year1Data.color
				          ],
				          borderColor: [
				            "rgba(255, 255, 255, .4)",
				            "rgba(255, 255, 255, .4)",
				            "rgba(255, 255, 255, .4)",
				            "rgba(255, 255, 255, .4)",
				            "rgba(255, 255, 255, .4)"
				          ],
				          borderWidth: 1
				        },
				        {
				          label: data.response.year2Data.yearLabel,
				          data: data.response.year2Data.data,
				          backgroundColor: [
				        	  data.response.year2Data.color,
				        	  data.response.year2Data.color,
				        	  data.response.year2Data.color,
				        	  data.response.year2Data.color,
				        	  data.response.year2Data.color
				          ],
				          borderColor: [
			        		"rgba(255, 255, 255, .4)",
				            "rgba(255, 255, 255, .4)",
				            "rgba(255, 255, 255, .4)",
				            "rgba(255, 255, 255, .4)",
				            "rgba(255, 255, 255, .4)"
				          ],
				          borderWidth: 1
				        }
				      ]
				    };
				  
				    //create Chart class object
				    var chart = new Chart(ctx, {
				      type: "bar",
				      data: data,
				      options: options
				    });
			},
			error: function(){
				console.log("error occured");
			}
		});
		
		//for salary
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/getDemand",
			data: {
				subcategoryId: "${subCatId}",
				demandType: 1
			},
			success: function(data){
				console.log(data);
				if(Object.keys(data.response.year1Data.data).length === 0){
					$('#barChartsData').html('<div class="mtdata">No Data!</div>');
				}
				$('#longTitle1').append(data.response.longTitle);
				    //get the bar chart canvas
				    var ctx = $("#bar-chartcanvas1");
				  
				    //bar chart data
				    var data = {
				      labels: data.response.countries,
				      datasets: [
				        {
				          label:  data.response.year1Data.yearLabel,
				          data: data.response.year1Data.data,
				          backgroundColor: [
				        	  data.response.year1Data.color,
					            data.response.year1Data.color,
					            data.response.year1Data.color,
					            data.response.year1Data.color,
					            data.response.year1Data.color
				          ],
				          borderColor: [
				        	  "rgba(255, 255, 255, .4)",
					            "rgba(255, 255, 255, .4)",
					            "rgba(255, 255, 255, .4)",
					            "rgba(255, 255, 255, .4)",
					            "rgba(255, 255, 255, .4)"
				          ],
				          borderWidth: 1
				        },
				        {
				          label: data.response.year2Data.yearLabel,
				          data: data.response.year2Data.data,
				          backgroundColor: [
				        	  data.response.year2Data.color,
				        	  data.response.year2Data.color,
				        	  data.response.year2Data.color,
				        	  data.response.year2Data.color,
				        	  data.response.year2Data.color
				          ],
				          borderColor: [
				        	  "rgba(255, 255, 255, .4)",
					          "rgba(255, 255, 255, .4)",
					          "rgba(255, 255, 255, .4)",
					          "rgba(255, 255, 255, .4)",
					          "rgba(255, 255, 255, .4)"
				          ],
				          borderWidth: 1
				        }
				      ]
				    };
				  
				    //create Chart class object
				    var chart = new Chart(ctx, {
				      type: "bar",
				      data: data,
				      options: options
				    });
			},
			error: function(){
				console.log("error occured");
			}
		});
	});
	
  </script>
</div>