<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DGX1 | Jobs</title>

<link href="resources/core/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/core/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="resources/core/css/plugins/footable/footable.core.css"
	rel="stylesheet">
<link href="resources/core/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
<link href="resources/core/css/plugins/iCheck/custom.css"
	rel="stylesheet">
<link href="resources/core/css/animate.css" rel="stylesheet">
<link href="resources/core/css/style.css" rel="stylesheet">
<link href="resources/core/css/style2.css" rel="stylesheet">
<link href="resources/core/img/NVLogo_2D_WT2.png" rel="icon"></link>

</head>

<body>
	<input type="hidden" id="NVIDIA_HOST" value="${dataListWrapper.nvidiaHost}">
	<input type="hidden" id="BOSUN_HOST" value="${dataListWrapper.bosunHost}">
	<input type="hidden" id="GRAFANA_HOST" value="${dataListWrapper.grafanaHost}">
	<div id="wrapper">
		<jsp:include page="menu.jsp"></jsp:include>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top white-bg" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a> <a
							class="minimalize-styl-2 btn btn-primary "
							href="<c:url value="/nvidia"/>"><i class="fa fa-home"></i> </a>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><span class="m-r-sm text-muted welcome-message">Welcome
								David Williams.</span></li>

						<li><a class="count-info" href="/nvidia/nvidia#alert_tab">
								<i class="fa fa-bell"></i> <c:if
									test="${dataListWrapper.needAckList.size()>0}">
									<span class="label label-danger"> <c:out
											value="${dataListWrapper.needAckList.size()}"></c:out>
									</span>
								</c:if>

						</a></li>
						<li><a href="/nvidia/logout"> <i class="fa fa-sign-out"></i>
								Log out
						</a></li>

					</ul>

				</nav>
			</div>
			<div class="wrapper wrapper-content">
				<div class="row">
					<div class="col-lg-6">
						<div class="ibox float-e-margins">

							<div class="ibox-title">
								<div class="col-md-4">
									<h5>
										<i class="fa fa-th-large"></i> Docker Images
									</h5>
								</div>
								<div class="col-md-6" style="margin-top: -9px">
									<input type="text" class="form-control input-sm m-b-xs"
										id="filter" placeholder="Search Images">
								</div>
								<div class="col-md-2">
									<div class="ibox-tools">
										<a class="refresh" id="job_refresh"> <i
											class="fa fa-refresh"></i></a> <a class="collapse-link"> <i
											class="fa fa-chevron-up"></i></a>
										<!--  <a class="close-link"> <i class="fa fa-times"></i> </a> -->
									</div>
								</div>
							</div>

							<div id="job_refresh_data" class="ibox-content"
								style="overflow-y: auto; max-height: 454px;">
								<jsp:include page="jobsview.jsp"></jsp:include>
							</div>
						</div>

						<%-- <div class="ibox-content" style="overflow-y: auto; max-height: 454px;">
                                <div class="form-group">
                                	<label class="col-md-2 control-label" style="margin-top: 10px;">Search</label>
                                		<div class="col-md-10">
                                			 <input type="text" class="form-control pull-left" placeholder="Enter your Docker image" name="search" id="search">
                                		</div>
                                </div>
                                <hr>
                                 <table class="table table-hover no-margins ">
								<thead>
									<tr>
										<th>Image</th>
										<th>Namespace</th>
									</tr>
								</thead>
								<tbody>
									<!-- <ul class="list-group clear-list darker-images"> -->
										<c:forEach items="${dataListWrapper.dockerImages.repositories}" var="repository" varStatus="status">
											<c:choose>
											<c:when test="${fn:length(image.tags) > 0 }"> 
											<tr>
											<td>
												<!-- <li class="list-group-item fist-item"> -->
													<span class="i-checks"><input type="radio" value="${repository.name }" name="dockers" id="CHECKBOX_${status.index}"><span class="docker-text"> ${repository.name }</span> </span> 
												<!-- </li> -->
												</td>
												<td>
												<span class="i-checks"> <input type="text" name="namespaces" value="${repository.namespace }" id="HIDDEN__${status.index}" disabled="disabled"/></span>
												<span class="i-checks"> ${repository.namespace }<input type="hidden" name="namespaces" value="${repository.namespace }" id="HIDDEN__${status.index}"/></span>
												</td>
												</tr>
											</c:when>
											</c:choose>
										</c:forEach>
										</tbody>
										</table>
									<!-- </ul> -->
                                </div> --%>

					</div>
					<div class="col-lg-6">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>
									<i class="fa fa-exclamation-triangle"></i> New Jobs
								</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a>
									<!-- <a class="close-link">
											<i class="fa fa-times"></i>
									</a> -->
								</div>
							</div>
							<div class="ibox-content">
								<form id="newJobForm" action="" role="form"
									class="form-horizontal" method="POST">
									<div class="form-group">
										<div class="col-lg-10">
											<input type="text" placeholder="Job Name"
												class="form-control" id="job_name">
										</div>
									</div>
									<div class="form-group">
										<div class="col-lg-10">
											<input type="text" placeholder="Image Url" value=""
												class="form-control" id="docker_url">
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-5">
											<select placeholder="Node name" class="form-control" id="node_name">
												<!-- <option>Select Cluster</option>  -->
												<c:forEach items="${dataListWrapper.clusterDataList}" var="clusterData" varStatus="status">
													<option value="${clusterData.clusterId}">${clusterData.clusterId}</option>
													
												</c:forEach>
											</select>
											  <input type="hidden" id="NFS_ONE_ID" value="">  
										</div>

										<div class="col-md-5">
											<select class="form-control" id="node_response" >
												<option value=""></option>
											</select>
										</div>

									</div>

									<%-- <div class="form-group">
										<div class="col-lg-10">
											<select placeholder="Node name" value="" class="form-control"
												id="node_name">
												<c:forEach items="${dataListWrapper.clusterDataList}"
													var="clusterData" varStatus="status">
													<c:forEach items="${dataListWrapper.nodeInfoDataList}"
														var="nodeInfoData" varStatus="status">
														<c:if test="${nodeInfoData.clusterId==clusterData.clusterId}">
															<option value="${nodeInfoData.name}~${nodeInfoData.gpuConfiguration}:${clusterData.nfs_one}">${clusterData.clusterId}:${nodeInfoData.name}</option>
														</c:if>
													</c:forEach>
												</c:forEach>
											</select>
										</div>
									</div> --%>
								<!-- 	<div class="form-group">
										<div class="col-lg-10">
											<input type="number" placeholder="Container Port" value=""
												class="form-control" id="container_port">
										</div>
									</div> -->
									<!-- adding dynamic parameters textboxes -->
									<div class="panel panel-default">
										<div class="panel-heading">Scheduler Parameters</div>
										<div class="panel-body">
											<div class="form-group">
												<div class="col-md-5">
													<select  id="dropdown1" name="dropdown1" class="form-control dropdown"
														 onchange="setValue('1')">
														<option value="ContainerPort">Container Port</option>
														<option value="NFSVolume">NFS Volume</option>
														<option value="LocalVolume">Local Volume</option>
														<!-- <option value=""></option> -->
													</select>
													
													<!-- <input class="form-control key" value="key" style="width: 150px;margin-left: 1px; position: absolute; margin-top: -29px;  height: 25px; border: none;"> -->
													<!-- <input type="text" placeholder="Key" id="key1"
														name="key1" class="form-control key"/> -->
												</div>
												<div class="col-md-5">
													<input type="text" placeholder="Value" id="dropvalue1" 
														name="dropvalue1" class="form-control dropvalue">
												</div>
												<div class="col-md-2">
													<button type="button" class="btn btn-xs btn-primary add-more"
														onclick="addMoreDropDwon(this.form);" style="margin-top: 6px;">
														<i class="fa fa-plus"></i>
													</button>
												</div>
											</div>
											<div id="addedDropDownRows"></div>
											<div class="form-group">
												<div class="col-md-5">
													<input type="text" placeholder="Key" id="key1" name="key1"
														class="form-control key">
												</div>
												<div class="col-md-5">
													<input type="text" placeholder="Value" id="valu1"
														name="value1" class="form-control value">
												</div>
												<div class="col-md-2">
													<button type="button" class="btn btn-xs btn-primary add-more"
														onclick="addMoreRows(this.form);" style="margin-top: 6px;">
														<i class="fa fa-plus"></i>
													</button>
												</div>
											</div>
											<div id="addedRows"></div>
											<!-- <button id="b1" class="btn add-more" type="button">+</button> -->
										</div>
									</div>
									<!-- adding dynamic parameters textboxes end -->
									
									<!-- adding dynamic Arguments textboxes -->
									<div class="panel panel-default">
										<div class="panel-heading">Application Parameters</div>
										<div class="panel-body">
											<div class="form-group">
												<div class="col-md-10">
													<input type="text" placeholder="Space Separated Parameters" id="argument"
														name="argument" class="form-control argument">
												</div>
												
												
												 <div class="col-md-2">
													<!-- 
													This button is commented as per requirement 
													
													<button type="button" class="btn btn-xs btn-primary add-more"
														onclick="addMoreArguments(this.form);" style="margin-top: 6px;">
														<i class="fa fa-plus"></i>
													</button> -->
												</div>
											</div>
											<div id="addArguments"></div>
										</div> 
										
										
										
									</div>
									<!-- adding dynamic Arguments textboxes end -->
									
									
									<!-- 
									
									This Div is commented as per requirement 
									
									<div class="modal-footer">
										<span class="text-danger"
											style="float: left; padding-left: 5px;" id="NEWJOB_JSON">
										</span>
									</div> -->
									<div class="form-group">
										<div class="col-lg-10">
											<button type="button" class="btn btn-sm btn-primary"
												id="start12">Submit</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="row" >
					<div class="col-md-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>
									<i class="fa fa-list"></i> Jobs
								</h5>
								<!-- <div class="ibox-tools">
										<button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModal5">Add <i class="fa fa-plus"></i></button>
                                    </div> -->
							</div>
							<c:choose>
								<c:when
									test="${fn:length(dataListWrapper.jobTaskDataList) > 0 }">
									<div class="ibox-content">
										<table class="table table-hover no-margins">
											<thead>
												<tr>
													<th>Job Name</th>
													<th>Time started</th>
													<th>Owner</th>
													<th>Port</th>
													<th>Status</th>
													<th>Options</th>
												</tr>
											</thead>
											<tbody>
												<input type="hidden" value="${fn:length(dataListWrapper.jobTaskDataList)}" id="JOBS_STARTED_AT_COUNT" />
												<c:forEach items="${dataListWrapper.jobTaskDataList}"
													var="jobTaskData" varStatus="status">
													<tr>

														<td>${jobTaskData.appId}</td>
														<input type="hidden" value='${jobTaskData.startedAt}' id="JOBS_STARTED_AT_${status.index}" />
														<td id="JOBS_STARTED_AT_NEW_${status.index}"></td>
														<td><span data-toggle="tooltip" data-placement="top"
															title="ram@gmail.com">${jobTaskData.owner}</span></td>
														<td><a
															href='http://${jobTaskData.host}:${jobTaskData.port}'
															target='_blank'>${jobTaskData.port}</a></td>
															
														<td>${jobTaskData.status}</td>
														
														<td width="15%"><a class="btn"
															style="padding: 0px 0px;" data-toggle="popover"
															data-placement="left" data-html="true"
															data-content="
																<li><span>Link to container : ${jobTaskData.image} </span></li>
																<li><span>Job ID: ${jobTaskData.appId} </span></li>
																<li><span>Host : ${jobTaskData.host} </span></li>
																<li><span>Status : ${jobTaskData.status} </span></li>
																<li><span>Additional arguments : NA </span></li>
																<li><span>NFS server : NA </span></li>
																<c:if test="${jobTaskData.state!=null && jobTaskData.message!=null}">
																<br>
																<b><span><center>LastTaskFailure</center></span></b><hr style='margin:0px;'>
																<li><span>Host : ${jobTaskData.host} </span></li>
																<li><span>Message : ${jobTaskData.message} </span></li>
																<li><span>State : ${jobTaskData.state} </span></li></c:if>"><i
																class="fa fa-eye primary" title="Details"></i></a> <span style="padding: 0px 0px;">&nbsp;</span>
															<!--<c:if test="${jobTaskData.status}!='In queue'">	
															
																<a 
															href='http://${jobTaskData.host}:1234/containers/digits/attach?stdout=true&stderr=true&stream=true&logs=false' target='_blank' disabled="disabled">
															<i class="fa fa-desktop fa-1x"></i>
															</a>
															</c:if> -->
															<c:choose>
															<c:when test="${jobTaskData.status=='Launched'}">
															<a style="cursor: pointer;" data-toggle="modal" data-target="#containerx" data-disp="" data-jobhost ="${jobTaskData.host}" data-containername="${jobTaskData.containerName}"> <i class="fa fa-desktop primary" title="Console"></i></a>
															</c:when>
															<c:otherwise>
															<a href="http://${jobTaskData.host}:2375/containers/${jobTaskData.containerName}/attach?stdout=true&stderr=true&stream=true&logs=false" id="container" target="blank" disabled="disabled" class="btn-disabled"><i class="fa fa-desktop fa-1x"></i></a>
															</c:otherwise>
															</c:choose> 
															<span style="padding: 0px 0px;">&nbsp;</span>
															<a class="btn job_details" style="padding: 0px 0px;" href="${jobTaskData.appId}">
																<i class="fa fa-times-circle fa-1x text-danger"></i></a>
																</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div id="containerx" class="modal fade" role="dialog">
				<div class="modal-dialog modal-lg">

					<div class="col-md-12">
						<div class="modal-content modal-lg">
							<div class="modal-header bg-primary">
								<button type="button" class="close intervalCancel" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title">Container Console</h4>
							</div>
							<div class="modal-body alert-modal">
								<p></p>
								<span id="container_console"></span>
								<!-- <span id="container_console"><img alt="image" src="resources/core/img/default-loader.gif" width="220" /></span> -->
								<div class="col-sm-12" style="padding-left: 0px !important; display:none" id="container_actions">
								<input type="hidden" id="container_subject">	
										<!--  <a class="btn btn-primary btn-xs" href="#" id="alert_ack">Acknowledge</a> 
										<a class="btn btn-warning btn-xs ng-disabled=" href="#" id="alert_close">Close</a>  -->
								</div>
							</div>
							<!-- <div class="modal-footer">
								<small class="text-success" id="container_confirmation" style="float:left;padding-bottom:3px;"></small>	
								<button type="button" class="btn btn-white" data-dismiss="modal">cancel</button>
							</div> -->
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<!--  <div class="pull-right">
                10GB of <strong>250GB</strong> Free.
            </div> -->
				<div>
					Copyright &copy; 2016 NVIDIA Corporation
				</div>
			</div>
		</div>

	</div>

	<!-- Mainly scripts -->
	<script src="resources/core/js/jquery-2.1.1.js"></script>
	<script src="resources/core/js/bootstrap.min.js"></script>
	<script src="resources/core/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="resources/core/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>


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
	<!-- dynamic textbox start -->

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
	<script>
		function setValue(k){
			var name = $('#dropdown'+k).val();
			if(name=='NFSVolume'){
				$('#dropvalue'+k).val($('#NFS_ONE_ID').val());
			}
		}
		var rowCount = 1;
		function addMoreDropDwon(frm) {
			rowCount++;
			var recRow = '<div id="rowCount'+rowCount+'"><div class="form-group"><div class="col-md-5"><select class="form-control dropdown" placeholder="Dropdown" name="dropdown'+rowCount+'" id="dropdown'+rowCount+'" onchange="setValue('+rowCount+')"><option value="ContainerPort">Container Port</option><option value="NFSVolume">NFS Volume</option><option value="LocalVolume">Local Volume</option></select></div><div class="col-md-5"><input type="text" placeholder="DropValue"  name="dropvalue' + rowCount +'" id="dropvalue'+rowCount +'" class="form-control dropvalue"></div><div class="col-md-1"><button type="button" class="btn btn-danger btn-xs" onclick="removeRow('
					+ rowCount
					+ ');" style="margin-top: 6px;"><i class="fa fa-minus"></i></button></div><div class="col-md-1"><button type="button" class="btn btn-xs btn-primary add-more" onclick="addMoreDropDwon(this.form);" style="margin-top: 6px;"><i class="fa fa-plus"></i></button></div></div></div>';

			jQuery('#addedDropDownRows').append(recRow);
		}

		function removeRow(removeNum) {
			jQuery('#rowCount' + removeNum).remove();
		}
	</script>
	<script>
		var rowCount = 1;
		function addMoreRows(frm) {
			rowCount++;
			var recRow = '<div id="rowCount'+rowCount+'"><div class="form-group"><div class="col-md-5"><input type="text" placeholder="Key"  name="key' + rowCount + '" class="form-control key"></div><div class="col-md-5"><input type="text" placeholder="Value"  name="value' + rowCount + '" class="form-control value"></div><div class="col-md-1"><button type="button" class="btn btn-danger btn-xs" onclick="removeRow('
					+ rowCount
					+ ');" style="margin-top: 6px;"><i class="fa fa-minus"></i></button></div><div class="col-md-1"><button type="button" class="btn btn-xs btn-primary add-more" onclick="addMoreRows(this.form);" style="margin-top: 6px;"><i class="fa fa-plus"></i></button></div></div></div>';

			jQuery('#addedRows').append(recRow);
		}

		function removeRow(removeNum) {
			jQuery('#rowCount' + removeNum).remove();
		}
	</script>
	<script> 
	var NVIDIA_HOST = $('#NVIDIA_HOST').val();
		$(document).ready(function() {
		//$("#node_name").change(function() {
			              var selectedClusterId = $("#node_name").val();
			              var cluster_req = NVIDIA_HOST +"/clusterInfo/"
				          var text = "";
					   $.ajax({
						   type : "POST",
						   url : cluster_req + selectedClusterId,
						   contentType : 'application/json',
						   success : function(data) {
						   console.log(data);
							for (i = 0; i < data.length; i++) {
								if (data[i]) {
									var key = data[i].split('~');
									var nfsvalue=key[1].split(':');
									var lastvalues=nfsvalue[1].split('-');
	                           	    nfsone = lastvalues[0];
									$('#NFS_ONE_ID').val(nfsone);
									text += "<option value="+data[i]+">" + key[0] + "</option>";
								} 
								//  $("#node_response").html(text);
							}
							//var nodescount = data.length;
							console.log(text);
							$("#node_response").html(text);
						  },
						    error : function(error) {
							console.log(error);
						  }
					   }); 
			// });	 
						  $('input[name="dockers"]').on('change', function() {
								var radioValue = $('input[name="dockers"]:checked').val();
								var id = $('input[name="dockers"]:checked').attr('id');
								var index = id.split('_')[1];
								$("#job_name").val(radioValue);
								var namespace = $("#HIDDEN__" + index).val();
								$("#docker_url").val('compute.nvidia.com/' + namespace + '/' + radioValue);
							}); 
							    $('.footable').footable();
								$('.footable2').footable();
								
								$("#job_refresh").click(function(event) {
									var ta = NVIDIA_HOST + "/refresh/api/jobs";
									$('#job_refresh_data').load(ta);
								});
								
							var count = $('#JOBS_STARTED_AT_COUNT').val();
							if (count) {
								var xco;
								for (xco = 0; xco < count; xco++) {
									var date = $('#JOBS_STARTED_AT_' + xco).val();
									var m = moment(date);
									if (m.isValid()) {
										//alert(" THIS IS VALUID "+m);
										var elem = document.getElementById("JOBS_STARTED_AT_NEW_" + xco);
										if (elem) {
											elem.innerHTML = m;
										}
										console.log("--------------------------------------------------------------");
										console.log(date);
										console.log(elem);
										console.log("--------------------------------------------------------------");

									} else {
										//alert(" THIS IS NOT VALUID "+m);
										console.log("--------------------------------------------------------------");
										console.log(" THERE IS NO TASK DATA IN THE JSON FOR ");
										console.log("--------------------------------------------------------------");
									}
								}
							}
							$('#containerx').on('show.bs.modal', function(e) {
								
								$('#container_confirmation').html('');
								
								$('#container_actions').css("display", "none");
								//data-disp=" fir 1 st one" data-notificationID="1"
								var jobhost = e.relatedTarget.dataset.jobhost;
								var containername = e.relatedTarget.dataset.containername;
								//var jobhost = $(this).data('jobhost');
								//var containername = $(this).data('containername');
								console.log(e);
								console.log("-----------------");
								console.log($(this));
								//
								console.log("JobHost:"+jobhost+",ContainerName:"+containername);
								//alert(yourParameter);
								//var xyz = decodeURIComponent("http://localhost/nvidia/api/ack/key/"+yourParameter);

								//alert(xyz);
								var eurl = NVIDIA_HOST+"/container";
								//var eurl='http://52.24.185.151:2375/containers/attach7/attach?stdin=true&stdout=true&stderr=true&stream=true&logs=false';
								//var data1={jobhost:jobhost,containerName:containerName};
								var obj = {};

								obj.job_host = jobhost;
								obj.container_name = containername;
								
								$("#container_console").html('');
								
					        	var timeInt=setInterval(function(){
								$.ajax({
									type : "POST",
									contentType : 'application/json',
									//dataType : 'json',
									data: JSON.stringify(obj),
									//data : ({jobhost: jobhost, containerName:containername}),
									url : eurl,
									success : function(msg) {
										//alert(msg);
										$('img[src$="resources/core/img/default-loader.gif"]').remove();
										//console.log(msg);
										//console.log('{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}');
										// console.log(msg);
										//$('#container_console').html(msg.message);
										$("#container_console").append(msg.message+"<br/>");
										$('#container_subject').val(jobhost);
										$('#container_actions').css("display", "");
										
									},
									error : function() {
										console.log("Failed to load Information. Please report to admin");
									}
								});
								}, 5000);
						
								$(".intervalCancel").on("click", function(){
		                        	console.log("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		                        	clearInterval(timeInt);
		                        });

							});
                               
                             /*   $('#close_container_stream').click( function() {
   								var eurl = NVIDIA_HOST+"/api/close/container";
   								$.ajax({
   									type : "POST",
   									contentType : 'application/json',
   									url : eurl,
   									success : function(msg) {
   										$("#container_console").html(msg.message);
   										
   									},
   									error : function() {
   										console.log("Failed to load Information. Please report to admin");
   									}
   								});
   							}); */
							$('#start12').click(function(er) {
								
												/* UUID Generator */
												var d = new Date().getTime();
				    							var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
				        						var r = (d + Math.random()*16)%16 | 0;
				        						d = Math.floor(d/16);
				       							return (c=='x' ? r : (r&0x3|0x8)).toString(16);
				    							});

				    							console.log(er);

												var job_name = $("#job_name").val();
												var docker_url = $("#docker_url").val();
												var node_response = $("#node_response").val();
												//var job_container_port = $("#container_port").val();
												
												var gpuconfigurationcount;
												var nfsone;
												var node_name;
												if(node_response){
													try {
                                                	  var values = node_response.split('~');
                                                	  var nextvalues = values[1].split(':');
                                                	  var lastvalues=nextvalues[1].split('-');
                                                	  node_name=lastvalues[1];
                                                	  gpuconfigurationcount=[nextvalues[0]];
                                                	  nfsone = lastvalues[0];
													} catch(err) {
											    	    //alert();
											    	}
							                    }
												
												//var job_container_port = $("#job_network").val();//BRIDGE
												//alert(job_name+" -- "+docker_url);

												var keys = $(".key").map(function() {
													return $(this).val();
												}).get();
												var values = $(".value").map(function() {
													return $(this).val();
												}).get();
												
												var parameters = [];
												$.each(keys, function(index, val) {
													if (val != '') {
														var parameter = {};
														parameter.key = val;
														parameter.value = values[index];
														parameters.push(parameter);
													}
												});
												
												//Dropdown option values
												var dropdowns = $(".dropdown").map(function() {
													return $(this).val();
												}).get();
												
												var dropdownvalues = $(".dropvalue").map(function() {
													return $(this).val();
												}).get();
												
												var parameters = [];
												var portMappingArray = [];
												
												$.each(dropdowns, function(index, val) {
												if(val=='ContainerPort'){
													try {
											          job_container_port=parseInt(dropdownvalues[index]);
													  
/* 													   var portMapping = job_container_port != '' ? ',{"port":0,"protocol":"tcp","labels":null,"containerPort":'
																+ job_container_port + ',"hostPort":0,"servicePort":0}]}'
																: '},';
 */	   
                                                   if(!isNaN(job_container_port))											        	   
													{
                                                 var portMapping = job_container_port != '' ? '{"port":0,"protocol":"tcp","labels":null,"containerPort":"' /* != '' ? */
                                                                 	+ job_container_port + '","hostPort":0,"servicePort":0}'
                                                          	:'}';
                                                 portMappingArray.push(portMapping);	
												}
 											           if(isNaN(job_container_port[index]))
													{
														job_container_port = '';
													}
 													}catch(err) {
											    	    //alert();
											    	}
												/* var parameters = [];
												var job_container_port = '';
												
												$.each(dropdowns, function(index, val) {
												if(val=='ContainerPort'){
													try {
											           job_container_port=parseInt(dropdownvalues[index]);
													if(isNaN(job_container_port))
													{
														job_container_port = '';
													}
													}catch(err) {
											    	    //alert();
											    	} */
												} if(val=='NFSVolume'){
												  {
													 var nfsparm1 = {};
													 nfsparm1.key = 'volume-driver';
													 nfsparm1.value = 'nfs';
													 parameters.push(nfsparm1);
													 
													 var nfsparm2 = {};
													 nfsparm2.key = 'volume';
													 nfsparm2.value = dropdownvalues[index];
													 
													// "value": "10.20.13.62/opt/nfs/data:/data"
													 parameters.push(nfsparm2);
												  }
												} else if(val=='LocalVolume'){
													 var parameter = {};
													 parameter.key = 'volume';
													 parameter.value = dropdownvalues[index];
													 parameters.push(parameter);
													}
												});
												
												$.each(keys, function(index, val) {
													if (val != '') {
														var parameter = {};
														parameter.key = val;
														parameter.value = values[index];
														parameters.push(parameter);
													}
												});
												/**
												   As per  requirement nazeer commented and added new
												
												var arguments = $(".argument").map(function() {
													return $(this).val();
												}).get();
												*/
												var arguments=$(".argument").val();
												
												var arguments_array = arguments.split(' ');
												
												/*
												  As per  requirement nazeer commented and added new
												  
												alert(arguments_array);
												 $.each(arguments, function(index,val) {
													if (val != '') {
														arguments_array.push(val);
													}
												}); */  
												
												//Later will remove
												var param1 = {};
												/*param1.key = 'volumes-from';
												param1.value = 'nvidia-volumes';
												parameters.push(param1);*/

												var param2 = {};
												/*param2.key = 'device';
												param2.value = '/dev/nvidiactl';
												parameters.push(param2);*/

												var param3 = {};
												/*param3.key = 'device';
												param3.value = '/dev/nvidia-uvm';
												parameters.push(param3);*/
												
												var param5 = {};
												param5.key = 'attach';
												param5.value = 'stdout';
												parameters.push(param5);
												
												var param6 = {};
												param6.key = 'attach';
												param6.value = 'stdin';
												parameters.push(param6);

												var param7 = {};
												param7.key = 'label';
												param7.value = uuid;
												parameters.push(param7);

												if (gpuconfigurationcount) {
									                var xco;
									                for (xco = 0; xco < gpuconfigurationcount; xco++) {
													var param4 = {};
													param4.key = 'device';
													param4.value = '/dev/nvidia'+xco;
													parameters.push(param4);
	                    						 }
	                                            }

												/* var param5 = {};
												param5.key = 'device';
												param5.value = '/dev/nvidia1';
												parameters.push(param5); */

												var params = JSON.stringify(parameters);
												//alert(params);
												var args=JSON.stringify(arguments_array);
												//alert(args)
												//var ta = '{"id":"'+job_name+'","image":"'+docker_url+'","container_port":"'+job_container_port+'"}';
												/*var ta = '{"id":"'+job_name+'","cpus":4,"mem":128,"instances":1, '+
														//'"constraints":[["hostname","UNIQUE",""]],"container":{"type":"DOCKER","docker":{"image":"'+docker_url+'","network":"BRIDGE",  '+
														'"container":{"type":"DOCKER","docker":{"image":"'+docker_url+'","network":"BRIDGE",  '+
														//'	"portMappings":[{"containerPort":'+job_container_port+',"hostPort":0,"servicePort":0,"protocol":"tcp"}]}},  '+
														'	"forcePullImage":true,"uris":["https://compute.nvidia.com/dockercfg.tar.gz"]}';*/
												//
												//console.log('Params '+params);
											    //alert("portmapping" + job_container_port );
												/* var portMapping =  portMappingArray != '' ? ',"portMappings":[{"port":0,"protocol":"tcp","labels":null,"containerPort":'
														+  portMappingArray + ',"hostPort":0,"servicePort":0}]},'
														: '},'; */
														
												var ta = '{"id":"'
														+ job_name
														+ '","fetch":[],"portDefinitions":[],"cpus":1,"cmd":null,"args":'+args+',"uris":["https://compute.nvidia.com/dockercfg.tar.gz"], '
														+ '"container":{"docker":{"forcePullImage":null,"image":"'
														+ docker_url
														+ '","parameters":'
														+ params
														+ ',"privileged":null,"network":"BRIDGE" '
														+ ',"portMappings":['
														+ portMappingArray +']},'
														+ '"volumes":null,"type":"DOCKER"},"disk":0,"mem":128,"instances":1,"constraints":[["hostname","UNIQUE",""]]}';
												//alert(ta);
												var eurl2 = NVIDIA_HOST + "/rest/job/api?jobHost="+node_name;
												console.log('Finally ' + ta);
												console.log('JobHost : '+node_name);
												$.ajax({
													url : eurl2,
													contentType : 'application/json',
													data : ta,
													type : "POST",
													success : function(msg) {
														console.log(msg);
														$('#NEWJOB_JSON').html(msg.message);
														window.location.reload();

													},
													error : function(msg) {
														console.log(msg);
														$('#NEWJOB_JSON').html(msg.message);
													}
												});

											});
							/* function displayVals(){
								 var check = $('#check:checked').val();
								 if(check){
								     $("#job_network").val(check);
								  }
								  else{
								  	$("#job_network").val('');
								 }
							}
								 var qqqq = window.setInterval( function(){
								 displayVals()},10 ); */
						});
		$('.job_details').click(function(e) {
			e.preventDefault();
			var xhref = $(this).attr('href');
			console.log(e);
			console.log("--------------------");
			console.log(xhref);

			swal({
				title : "Are you sure to kill " + xhref,
				text : "You will not be able to recover this",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes",
				closeOnConfirm : false
			}, function() {
				var ta = NVIDIA_HOST + "/rest/job/delete";
				$.ajax({
					url : ta,
					contentType : 'application/json',
					data : xhref,
					type : "DELETE",
					success : function(msg) {
						console.log(msg);
						swal("Killed!", "Your job has been killed.", "success");
						$("#myModal").modal('hide');
						window.location.reload();

					},
					error : function(msg) {
						console.log(msg);
						swal("Sorry!", "Your request cannot be completed now. Please try later.", "success");
					}

				});

			});
			});
	</script>
	
	  <script type="text/javascript">
		$("#node_name").change(function() {
			var selectedClusterId = $("#node_name option:selected").val();
			var cluster_req = NVIDIA_HOST +"/clusterInfo/"
			var text = "";
				$.ajax({
					type : "POST",
					url : cluster_req + selectedClusterId,
					contentType : 'application/json',
					success : function(data) {
					console.log(data);
						for (i = 0; i < data.length; i++) {
							if (data[i]) {
								var key = data[i].split('~');
								var nfsvalue=key[1].split(':');
								var lastvalues=nfsvalue[1].split('-');
                           	    nfsone = lastvalues[0];
								$('#NFS_ONE_ID').val(nfsone);
								text += "<option value="+data[i]+">" + key[0] + "</option>";
							} 
							//  $("#node_response").html(text);
						}
						//var nodescount = data.length;
						console.log(text);
						$("#node_response").html(text);

					},
					error : function(error) {
						console.log(error);
					}
				});
		}); 
		</script> 
		 
		<!-- <script>
		 window.onload = clusterNodes() {
			var selectedClusterId = ('#clusterID').val();
			alert(selectedClusterId);
			var cluster_req = "http://" + SERVER_IP + "/nvidia/clusterInfo/"
			var text = "";
			//if (selectedClusterId != 'Select Cluster') {
				$.ajax({
					type : "POST",
					url : cluster_req + selectedClusterId,
					contentType : 'application/json',
					success : function(data) {
					console.log(data);
						for (i = 0; i < data.length; i++) {
							if (data[i]) {
								var key = data[i].split('~');
								var nfsvalue=key[1].split(':');
								
								$('#NFS_ONE_ID').val(nfsvalue[1]);
								
								text += "<option value="+data[i]+">" + key[0] + "</option>";
							} 
							//  $("#node_response").html(text);
						}
						//var nodescount = data.length;
						console.log(text);
						$("#node_response").html(text);

					},
					error : function(error) {
						console.log(error);
					}
				});
			// }else {
			//	text += "<option value"+""+">" +'Select Node'+ "</option>";
			//	$("#node_response").html(text);
			// }
			
		} 
	</script> -->
	<!-- <script type="text/javascript">
		$(document).ready(function() {
			$("#job_refresh").click(function(event) {
				//alert("ss");
				var ta = "http://" + SERVER_IP + "/nvidia/refresh/api/jobs";
				$('#job_refresh_data').load(ta);
			});
		});
	</script> -->

</body>
</html>
