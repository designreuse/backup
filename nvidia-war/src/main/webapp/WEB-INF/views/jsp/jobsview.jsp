<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- <div class="ibox float-e-margins">

	<div class="ibox-title">
		<div class="col-md-4">
			<h5>
				<i class="fa fa-th-large"></i> Docker Images
			</h5>
		</div>
		<div class="col-md-6" style="margin-top: -9px">
			<input type="text" class="form-control input-sm m-b-xs" id="filter"
				placeholder="Enter your Docker Image">
		</div>
		<div class="col-md-2">
			<div class="ibox-tools">
				<a class="refresh" id="job_refresh"> <i class="fa fa-refresh"></i></a>
				<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
				 <a class="close-link"> <i class="fa fa-times"></i> </a>
			</div>
		</div>
	</div>
	<div class="ibox-content" style="overflow-y: auto; max-height: 454px;"> -->

		<table class="footable table" data-filter=#filter>
			<!-- <table class="table table-hover no-margins ">-->
			<thead>
				<tr>
					<th>Image</th>
					<th>Namespace</th>
				</tr>
			</thead>
			<tbody>
				<!-- <ul class="list-group clear-list darker-images"> -->
				<c:forEach items="${dataListWrapper.dockerImages.repositories}"
					var="repository" varStatus="status">
					<%-- <c:choose>
					<c:when test="${fn:length(image.tags) > 0 }"> --%>
					<tr class="gradeX" style="display: table-row ! important;">
						<td>
							<!-- <li class="list-group-item fist-item"> --> <span
							class="i-checks"><input type="radio"
								value="${repository.name }" name="dockers"
								id="CHECKBOX_${status.index}"><span class="docker-text">
									${repository.name }</span> </span> <!-- </li> -->
						</td>
						<td><span class="i-checks"> ${repository.namespace }<input
								type="hidden" name="namespaces" value="${repository.namespace }"
								id="HIDDEN__${status.index}" /></span></td>
					</tr>
					<%-- </c:when>
					</c:choose> --%>
				</c:forEach>
			</tbody>
		</table>
		<!-- </ul> -->

	<!-- </div>

</div> -->


	<!-- Mainly scripts -->
 	<script src="resources/core/js/jquery-2.1.1.js"></script>
<!--	<script src="resources/core/js/bootstrap.min.js"></script>
	<script src="resources/core/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="resources/core/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
 -->

	<!-- Custom and plugin javascript -->
	<script src="resources/core/js/inspinia.js"></script>
	<script src="resources/core/js/plugins/pace/pace.min.js"></script>

	<!-- jQuery UI -->
	<script src="resources/core/js/plugins/jquery-ui/jquery-ui.min.js"></script>

	<script src="resources/core/js/jquery.timeago.js"></script>
	<script src="resources/core/js/grafana.js"></script>
	<script src="resources/core/js/plugins/footable/footable.all.min.js"></script>

	<!-- Sweet alert -->
	<script src="resources/core/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="resources/core/js/plugins/moment/moment-with-locales.js"></script>

	<!-- jQuery UI -->
	<script src="resources/core/js/plugins/iCheck/icheck.min.js"></script>
	
   <!-- <script>
		$(document).ready(function() {
			$('.footable').footable();
			$('.footable2').footable();

		});
   </script> -->
	
	<!-- dynamic arguments textbox start -->
	<script>
		var rowCount = 1;
		function addMoreArguments(frm) {
			rowCount++;
			var recRow = '<div id="rowCount'+rowCount+'"><div class="form-group"><div class="col-md-10"><input type="text" placeholder="Arguments"  name="argument' + rowCount + '" class="form-control argument"></div><div class="col-md-1"><button type="button" class="btn btn-danger btn-xs" onclick="removeRow('
					+ rowCount
					+ ');" style="margin-top: 6px;"><i class="fa fa-minus"></i></button></div><div class="col-md-1"><button type="button" class="btn btn-xs btn-info add-more" onclick="addMoreArguments(this.form);" style="margin-top: 6px;"><i class="fa fa-plus"></i></button></div></div></div>';

			jQuery('#addArguments').append(recRow);
		}

		function removeRow(removeNum) {
			jQuery('#rowCount' + removeNum).remove();
		}
	</script>
<!-- dynamic arguments textbox end -->

<!-- dynamic textbox start -->
	<script>
		var rowCount = 1;
		function addMoreRows(frm) {
			rowCount++;
			var recRow = '<div id="rowCount'+rowCount+'"><div class="form-group"><div class="col-md-5"><input type="text" placeholder="Key"  name="key' + rowCount + '" class="form-control key"></div><div class="col-md-5"><input type="text" placeholder="Value"  name="value' + rowCount + '" class="form-control value"></div><div class="col-md-1"><button type="button" class="btn btn-danger btn-xs" onclick="removeRow('
					+ rowCount
					+ ');" style="margin-top: 6px;"><i class="fa fa-minus"></i></button></div><div class="col-md-1"><button type="button" class="btn btn-xs btn-info add-more" onclick="addMoreRows(this.form);" style="margin-top: 6px;"><i class="fa fa-plus"></i></button></div></div></div>';

			jQuery('#addedRows').append(recRow);
		}

		function removeRow(removeNum) {
			jQuery('#rowCount' + removeNum).remove();
		}
	</script>
	<!-- dynamic textbox end -->
	
	<script>
		var SERVER_IP = $('#SERVER_IP').val();
		$(document).ready(function() {
			
			                     $('.footable').footable();
			                     $('.footable2').footable();
			                     
							$('input[name="dockers"]').on('change', function() {
								var radioValue = $('input[name="dockers"]:checked').val();
								var id = $('input[name="dockers"]:checked').attr('id');
								var index = id.split('_')[1];
								$("#job_name").val(radioValue);
								var namespace = $("#HIDDEN__" + index).val();
								$("#docker_url").val('compute.nvidia.com/' + namespace + '/' + radioValue);
							});				
		});
	</script> 
