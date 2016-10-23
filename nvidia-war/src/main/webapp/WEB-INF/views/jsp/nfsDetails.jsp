<%@page import="java.util.Random"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DGX1 | Settings</title>

<link href="resources/core/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/core/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="resources/core/css/plugins/footable/footable.core.css" rel="stylesheet">
<link href="resources/core/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<link href="resources/core/css/animate.css" rel="stylesheet">
<link href="resources/core/css/style.css" rel="stylesheet">
<link href="resources/core/css/style2.css" rel="stylesheet">
<link href="resources/core/img/NVLogo_2D_WT2.png" rel="icon"></link>

</head>

<body>

<input type="hidden" id="NVIDIA_HOST" value="${dataListWrapper.nvidiaHost}">
<input type="hidden" id="BOSUN_HOST" value="${dataListWrapper.bosunHost}">
	<div id="wrapper">
		<jsp:include page="menu.jsp"></jsp:include>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top white-bg" role="navigation"
					style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
						href="#"><i class="fa fa-bars"></i> </a>
						<a class="minimalize-styl-2 btn btn-primary "
							href="<c:url value="/nvidia"/>"><i class="fa fa-home"></i> </a>
				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li><span class="m-r-sm text-muted welcome-message">Welcome
							David Williams.</span></li>

					<li ><a class=" count-info"
						href="/nvidia/nvidia#alert_tab"> <i class="fa fa-bell"></i> 
						
						<c:if test="${dataListWrapper.needAckList.size()>0}">
								<span class="label label-danger"> <c:out value="${dataListWrapper.needAckList.size()}"></c:out>	</span>
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
					<div class="col-lg-12">
						<h2 style="float: left; margin-top: 0px;">CLUSTER:
							${dataListWrapper.clusterData.clusterId}</h2>
					</div>
					<!-- <div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title" style="border-width: 0px 0 0;">
								<div class="ibox-tools">
									<i class="fa fa-gear fa-2x" style="float: left;"> </i> <small
										class="text-muted"><b>Metrics From:</b> <span
										id="selected_timeframe"></span></small>&nbsp;&nbsp;
									<div class="btn-group">
										<button data-toggle="dropdown"
											class="btn btn-primary btn-xs dropdown-toggle"
											aria-expanded="false">
											Action <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" id='myid'>
											<li id="1"><a href="#">Last 3 Days</a></li>
											<li id="2"><a href="#">Last 7 Days</a></li>
											<li class="divider"></li>
											<li id="3"><a href="#">Current</a></li>
										</ul>
									</div>
									
									<div class="btn-group" style="padding-right: 20px;">&nbsp;</div>
								</div>
							</div>
						</div>
					</div> -->
				</div>
				<div class="col-lg-12" style="margin-top: 10px;">&nbsp;</div>
				<div class="row">
                <div class="col-lg-12">
					<div class="ibox-title">
							<h5>
								<i class="fa fa-list"></i> NFS Details
							</h5>
							<div class="ibox-tools">
								<a class="btn btn-xs btn-primary" id="updateNFS"> Update </i>
								</a>
								<!--
                                 <button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModal">
                                Add <i class="fa fa-plus"></i> 
                                 </button>
                                 -->
							</div>
							<br>
							<div class="ibox-content table-responsive">
								<span data-bind="text: priceRating" id="nfsError" bgcolor="red"
									hidden></span>
									<span data-bind="text: priceRating1" id="nfsSuccess" bgcolor="red"
									hidden></span>
								<div class="form-group">
									<div class="row">
										<div class="col-md-12">
											<input type="text" placeholder="NFS_Server" id="nfsONE" value="${dataListWrapper.clusterData.nfsone}"
												name="" class="form-control">
										</div>
									</div>
								</div>
							</div>

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
	
	<style>
	*, *:before, *:after {box-sizing:  border-box !important;}


.rowa {
 -moz-column-width: 18em;
 -webkit-column-width: 18em;
 -moz-column-gap: 1em;
 -webkit-column-gap:1em; 
  
}

.itema {
 display: inline-block;
 padding:  .25rem;
 width:  100%; 
}

.wella {
 position:relative;
 display: block;
}
	</style>
			<script>
			
			
			var NVIDIA_HOST = $('#NVIDIA_HOST').val();
			 $('#updateNFS')
				.click(
						function() {
							var nfsone = $("#nfsONE").val();

							if (!nfsone == null || !nfsone == '') {
								$('#nfsError').hide();
								setTimeout(function(){
								    $("#nfsSuccess").fadeOut("slow");
								},5000)
								var clusterId = $("#clusterID").val();
								
								
								var nfsURL = NVIDIA_HOST+"/cluster/updateNfs";
								var object = '{"clusterId":"' + clusterId
										+ '","nfsone":"' + nfsone + '"}';
								console.log(object);
								$.ajax({
									url : nfsURL,
									contentType : 'application/json',
									data : object,
									type : "POST",
									success : function(msg) {
										console.log(msg);
									},
									error : function(msg) {
										console.log(msg);
									}
								});
 								if ($('#nfsSuccess').text() == null
										|| $('#nfsSuccess').text() == "") {

									var html = '<div class="folder" style="color:#006400">'
											+ '<span class="folder_name"> NFS server updated </span>'
											+ '</div>';
									$('#nfsSuccess').append(html);
									$('#nfsSuccess').show();
								} else {
									$('#nfsSuccess').show();
								}
							
							} else {
								if ($('#nfsError').text() == null
										|| $('#nfsError').text() == "") {

									var html = '<div class="folder" style="color:#ff0000">'
											+ '<span class="folder_name"> NFS server should be specified </span>'
											+ '</div>';
									$('#nfsError').append(html);
									$('#nfsError').show();
								} else {
									$('#nfsError').show();
								}
							}
						});
			 </script> 
</body>
</html>
