<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Nvidia | Dashboard</title>

    <link href="resources/core/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/core/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="resources/core/css/plugins/dataTables/datatables.min.css" rel="stylesheet">
    <!-- Morris -->
    <link href="resources/core/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <link href="resources/core/css/animate.css" rel="stylesheet">
    <link href="resources/core/css/style.css" rel="stylesheet">

</head>

<body>
    <div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="img/profile_small.jpg" />
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">David Williams</strong>
                             </span> </span> </a>
                       
                    </div>
                    <div class="logo-element">
                        Nvidia
                    </div>
                </li>
                <li class="active">
                    <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboards</span> </a>
                    
                </li>
                <li>
                    <a href="notifications.html"><i class="fa fa-diamond"></i> <span class="nav-label">Notifications</span></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Metrics</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="GPU0.html">GPU 0</a></li>
                        <li><a href="">GPU 1</a></li>
                        <li><a href="">GPU 2</a></li>
                        <li><a href="">GPU 3</a></li>
                        <li><a href="">CPU </a></li>
                    </ul>
                </li>
               <li>
                    <a href="Management.html"><i class="fa fa-desktop"></i> <span class="nav-label">Management</span>  </a>
                </li>
                <li>
                    <a href="Logout.html"><i class="fa fa-sign-out"></i> <span class="nav-label">Logout</span>  </a>
                </li>
               
               

            </ul>

        </div>
    </nav>

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
        <nav class="navbar navbar-static-top white-bg" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
            <form role="search" class="navbar-form-custom" action="search_results.html">
                <div class="form-group">
                    <!-- <input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search"> -->
                </div>
            </form>
        </div>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <span class="m-r-sm text-muted welcome-message">Welcome to Nvidia Dashboard.</span>
                </li>
               
                <li class="dropdown">
                    <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell"></i>  <span class="label label-danger">8</span>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="mailbox.html">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> You have 16 messages
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="profile.html">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="grid_options.html">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <div class="text-center link-block">
                                <a href="notifications.html">
                                    <strong>See All Alerts</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </div>
                        </li>
                    </ul>
                </li>


                <li>
                    <a href="login.html">
                        <i class="fa fa-sign-out"></i> Log out
                    </a>
                </li>
                <li>
                    <a class="right-sidebar-toggle">
                        <i class="fa fa-tasks"></i>
                    </a>
                </li>
            </ul>

        </nav>
        </div>


        <div class="wrapper wrapper-content">
        
        <div class="row">
			<div class="col-lg-12">
				<iframe src="http://play.grafana.org/dashboard/db/big-dashboard?from=1456684656736&to=1456799857056&theme=light" width="100%" height="750px" frameborder="0" scrolling="no"></iframe>
			</div>
		
		</div>
	<br/>
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Notifications</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#">Config option 1</a>
                                </li>
                                <li><a href="#">Config option 2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" >
                    <thead>
                    <tr>
                        <th>Rendering engine</th>
                        <th>Browser</th>
                        <th>Platform(s)</th>
                        <th>Engine version</th>
                        <th>CSS grade</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="gradeX">
                        <td>Trident</td>
                        <td>Internet
                            Explorer 4.0
                        </td>
                        <td>Win 95+</td>
                        <td class="center">4</td>
                        <td class="center">X</td>
                    </tr>
                    <tr class="gradeC">
                        <td>Trident</td>
                        <td>Internet
                            Explorer 5.0
                        </td>
                        <td>Win 95+</td>
                        <td class="center">5</td>
                        <td class="center">C</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Trident</td>
                        <td>Internet
                            Explorer 5.5
                        </td>
                        <td>Win 95+</td>
                        <td class="center">5.5</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Trident</td>
                        <td>Internet
                            Explorer 6
                        </td>
                        <td>Win 98+</td>
                        <td class="center">6</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Trident</td>
                        <td>Internet Explorer 7</td>
                        <td>Win XP SP2+</td>
                        <td class="center">7</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Trident</td>
                        <td>AOL browser (AOL desktop)</td>
                        <td>Win XP</td>
                        <td class="center">6</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Firefox 1.0</td>
                        <td>Win 98+ / OSX.2+</td>
                        <td class="center">1.7</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Firefox 1.5</td>
                        <td>Win 98+ / OSX.2+</td>
                        <td class="center">1.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Firefox 2.0</td>
                        <td>Win 98+ / OSX.2+</td>
                        <td class="center">1.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Firefox 3.0</td>
                        <td>Win 2k+ / OSX.3+</td>
                        <td class="center">1.9</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Camino 1.0</td>
                        <td>OSX.2+</td>
                        <td class="center">1.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Camino 1.5</td>
                        <td>OSX.3+</td>
                        <td class="center">1.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Netscape 7.2</td>
                        <td>Win 95+ / Mac OS 8.6-9.2</td>
                        <td class="center">1.7</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Netscape Browser 8</td>
                        <td>Win 98SE+</td>
                        <td class="center">1.7</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Netscape Navigator 9</td>
                        <td>Win 98+ / OSX.2+</td>
                        <td class="center">1.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.0</td>
                        <td>Win 95+ / OSX.1+</td>
                        <td class="center">1</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.1</td>
                        <td>Win 95+ / OSX.1+</td>
                        <td class="center">1.1</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.2</td>
                        <td>Win 95+ / OSX.1+</td>
                        <td class="center">1.2</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.3</td>
                        <td>Win 95+ / OSX.1+</td>
                        <td class="center">1.3</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.4</td>
                        <td>Win 95+ / OSX.1+</td>
                        <td class="center">1.4</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.5</td>
                        <td>Win 95+ / OSX.1+</td>
                        <td class="center">1.5</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.6</td>
                        <td>Win 95+ / OSX.1+</td>
                        <td class="center">1.6</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.7</td>
                        <td>Win 98+ / OSX.1+</td>
                        <td class="center">1.7</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Mozilla 1.8</td>
                        <td>Win 98+ / OSX.1+</td>
                        <td class="center">1.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Seamonkey 1.1</td>
                        <td>Win 98+ / OSX.2+</td>
                        <td class="center">1.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Gecko</td>
                        <td>Epiphany 2.20</td>
                        <td>Gnome</td>
                        <td class="center">1.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Webkit</td>
                        <td>Safari 1.2</td>
                        <td>OSX.3</td>
                        <td class="center">125.5</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Webkit</td>
                        <td>Safari 1.3</td>
                        <td>OSX.3</td>
                        <td class="center">312.8</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Webkit</td>
                        <td>Safari 2.0</td>
                        <td>OSX.4+</td>
                        <td class="center">419.3</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Webkit</td>
                        <td>Safari 3.0</td>
                        <td>OSX.4+</td>
                        <td class="center">522.1</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Webkit</td>
                        <td>OmniWeb 5.5</td>
                        <td>OSX.4+</td>
                        <td class="center">420</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Webkit</td>
                        <td>iPod Touch / iPhone</td>
                        <td>iPod</td>
                        <td class="center">420.1</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Webkit</td>
                        <td>S60</td>
                        <td>S60</td>
                        <td class="center">413</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Opera 7.0</td>
                        <td>Win 95+ / OSX.1+</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Opera 7.5</td>
                        <td>Win 95+ / OSX.2+</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Opera 8.0</td>
                        <td>Win 95+ / OSX.2+</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Opera 8.5</td>
                        <td>Win 95+ / OSX.2+</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Opera 9.0</td>
                        <td>Win 95+ / OSX.3+</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Opera 9.2</td>
                        <td>Win 88+ / OSX.3+</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Opera 9.5</td>
                        <td>Win 88+ / OSX.3+</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Opera for Wii</td>
                        <td>Wii</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Nokia N800</td>
                        <td>N800</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Presto</td>
                        <td>Nintendo DS browser</td>
                        <td>Nintendo DS</td>
                        <td class="center">8.5</td>
                        <td class="center">C/A<sup>1</sup></td>
                    </tr>
                    <tr class="gradeC">
                        <td>KHTML</td>
                        <td>Konqureror 3.1</td>
                        <td>KDE 3.1</td>
                        <td class="center">3.1</td>
                        <td class="center">C</td>
                    </tr>
                    <tr class="gradeA">
                        <td>KHTML</td>
                        <td>Konqureror 3.3</td>
                        <td>KDE 3.3</td>
                        <td class="center">3.3</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeA">
                        <td>KHTML</td>
                        <td>Konqureror 3.5</td>
                        <td>KDE 3.5</td>
                        <td class="center">3.5</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeX">
                        <td>Tasman</td>
                        <td>Internet Explorer 4.5</td>
                        <td>Mac OS 8-9</td>
                        <td class="center">-</td>
                        <td class="center">X</td>
                    </tr>
                    <tr class="gradeC">
                        <td>Tasman</td>
                        <td>Internet Explorer 5.1</td>
                        <td>Mac OS 7.6-9</td>
                        <td class="center">1</td>
                        <td class="center">C</td>
                    </tr>
                    <tr class="gradeC">
                        <td>Tasman</td>
                        <td>Internet Explorer 5.2</td>
                        <td>Mac OS 8-X</td>
                        <td class="center">1</td>
                        <td class="center">C</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Misc</td>
                        <td>NetFront 3.1</td>
                        <td>Embedded devices</td>
                        <td class="center">-</td>
                        <td class="center">C</td>
                    </tr>
                    <tr class="gradeA">
                        <td>Misc</td>
                        <td>NetFront 3.4</td>
                        <td>Embedded devices</td>
                        <td class="center">-</td>
                        <td class="center">A</td>
                    </tr>
                    <tr class="gradeX">
                        <td>Misc</td>
                        <td>Dillo 0.8</td>
                        <td>Embedded devices</td>
                        <td class="center">-</td>
                        <td class="center">X</td>
                    </tr>
                    <tr class="gradeX">
                        <td>Misc</td>
                        <td>Links</td>
                        <td>Text only</td>
                        <td class="center">-</td>
                        <td class="center">X</td>
                    </tr>
                    <tr class="gradeX">
                        <td>Misc</td>
                        <td>Lynx</td>
                        <td>Text only</td>
                        <td class="center">-</td>
                        <td class="center">X</td>
                    </tr>
                    <tr class="gradeC">
                        <td>Misc</td>
                        <td>IE Mobile</td>
                        <td>Windows Mobile 6</td>
                        <td class="center">-</td>
                        <td class="center">C</td>
                    </tr>
                    <tr class="gradeC">
                        <td>Misc</td>
                        <td>PSP browser</td>
                        <td>PSP</td>
                        <td class="center">-</td>
                        <td class="center">C</td>
                    </tr>
                    <tr class="gradeU">
                        <td>Other browsers</td>
                        <td>All others</td>
                        <td>-</td>
                        <td class="center">-</td>
                        <td class="center">U</td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th>Rendering engine</th>
                        <th>Browser</th>
                        <th>Platform(s)</th>
                        <th>Engine version</th>
                        <th>CSS grade</th>
                    </tr>
                    </tfoot>
                    </table>
                        </div>

                    </div>
                </div>
            </div>
            </div>
		


        <div class="footer">
            
            <div>
                <strong>Copyright</strong> Nvidia &copy; 2016
            </div>
        </div>

        </div>
        <div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">

                    <li class="active"><a data-toggle="tab" href="#tab-1">
                        Notes
                    </a></li>
                    <li><a data-toggle="tab" href="#tab-2">
                        Projects
                    </a></li>
                    <li class=""><a data-toggle="tab" href="#tab-3">
                        <i class="fa fa-gear"></i>
                    </a></li>
                </ul>

                <div class="tab-content">


                    <div id="tab-1" class="tab-pane active">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> Latest Notes</h3>
                            <small><i class="fa fa-tim"></i> You have 10 new message.</small>
                        </div>

                        <div>

                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a1.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">

                                        There are many variations of passages of Lorem Ipsum available.
                                        <br>
                                        <small class="text-muted">Today 4:21 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a2.jpg">
                                    </div>
                                    <div class="media-body">
                                        The point of using Lorem Ipsum is that it has a more-or-less normal.
                                        <br>
                                        <small class="text-muted">Yesterday 2:45 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a3.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        Mevolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                                        <br>
                                        <small class="text-muted">Yesterday 1:10 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a4.jpg">
                                    </div>

                                    <div class="media-body">
                                        Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the
                                        <br>
                                        <small class="text-muted">Monday 8:37 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a8.jpg">
                                    </div>
                                    <div class="media-body">

                                        All the Lorem Ipsum generators on the Internet tend to repeat.
                                        <br>
                                        <small class="text-muted">Today 4:21 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a7.jpg">
                                    </div>
                                    <div class="media-body">
                                        Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.
                                        <br>
                                        <small class="text-muted">Yesterday 2:45 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a3.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        The standard chunk of Lorem Ipsum used since the 1500s is reproduced below.
                                        <br>
                                        <small class="text-muted">Yesterday 1:10 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a4.jpg">
                                    </div>
                                    <div class="media-body">
                                        Uncover many web sites still in their infancy. Various versions have.
                                        <br>
                                        <small class="text-muted">Monday 8:37 pm</small>
                                    </div>
                                </a>
                            </div>
                        </div>

                    </div>

                    <div id="tab-2" class="tab-pane">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-cube"></i> Latest projects</h3>
                            <small><i class="fa fa-tim"></i> You have 14 projects. 10 not completed.</small>
                        </div>

                        <ul class="sidebar-list">
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Business valuation</h4>
                                    It is a long established fact that a reader will be distracted.

                                    <div class="small">Completion with: 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Contract with Company </h4>
                                    Many desktop publishing packages and web page editors.

                                    <div class="small">Completion with: 48%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Meeting</h4>
                                    By the readable content of a page when looking at its layout.

                                    <div class="small">Completion with: 14%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-primary pull-right">NEW</span>
                                    <h4>The generated</h4>
                                    <!--<div class="small pull-right m-t-xs">9 hours ago</div>-->
                                    There are many variations of passages of Lorem Ipsum available.
                                    <div class="small">Completion with: 22%</div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Business valuation</h4>
                                    It is a long established fact that a reader will be distracted.

                                    <div class="small">Completion with: 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Contract with Company </h4>
                                    Many desktop publishing packages and web page editors.

                                    <div class="small">Completion with: 48%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Meeting</h4>
                                    By the readable content of a page when looking at its layout.

                                    <div class="small">Completion with: 14%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-primary pull-right">NEW</span>
                                    <h4>The generated</h4>
                                    <!--<div class="small pull-right m-t-xs">9 hours ago</div>-->
                                    There are many variations of passages of Lorem Ipsum available.
                                    <div class="small">Completion with: 22%</div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>

                        </ul>

                    </div>

                    <div id="tab-3" class="tab-pane">

                        <div class="sidebar-title">
                            <h3><i class="fa fa-gears"></i> Settings</h3>
                            <small><i class="fa fa-tim"></i> You have 14 projects. 10 not completed.</small>
                        </div>

                        <div class="setings-item">
                    <span>
                        Show notifications
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example">
                                    <label class="onoffswitch-label" for="example">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Disable Chat
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" checked class="onoffswitch-checkbox" id="example2">
                                    <label class="onoffswitch-label" for="example2">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Enable history
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example3">
                                    <label class="onoffswitch-label" for="example3">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Show charts
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example4">
                                    <label class="onoffswitch-label" for="example4">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Offline users
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox" id="example5">
                                    <label class="onoffswitch-label" for="example5">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Global search
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox" id="example6">
                                    <label class="onoffswitch-label" for="example6">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Update everyday
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example7">
                                    <label class="onoffswitch-label" for="example7">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="sidebar-content">
                            <h4>Settings</h4>
                            <div class="small">
                                I belive that. Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                                And typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.
                                Over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                            </div>
                        </div>

                    </div>
                </div>

            </div>



        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="resources/core/js/jquery-2.1.1.js"></script>
    <script src="resources/core/js/bootstrap.min.js"></script>
    <script src="resources/core/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="resources/core/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- jQuery UI -->
    <script src="resources/core/js/plugins/jquery-ui/jquery-ui.min.js"></script>


    <script src="resources/core/js/plugins/jeditable/jquery.jeditable.js"></script>

    <script src="resources/core/js/plugins/dataTables/datatables.min.js"></script>

       <!-- Custom and plugin javascript -->
    <script src="resources/core/js/inspinia.js"></script>
    <script src="resources/core/js/plugins/pace/pace.min.js"></script>

    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function(){
            $('.dataTables-example').DataTable({
                dom: '<"html5buttons"B>lTfgitp',
                buttons: [
                    { extend: 'copy'},
                    {extend: 'csv'},
                    {extend: 'excel', title: 'ExampleFile'},
                    {extend: 'pdf', title: 'ExampleFile'},

                    {extend: 'print',
                     customize: function (win){
                            $(win.document.body).addClass('white-bg');
                            $(win.document.body).css('font-size', '10px');

                            $(win.document.body).find('table')
                                    .addClass('compact')
                                    .css('font-size', 'inherit');
                    }
                    }
                ]

            });

            /* Init DataTables */
            var oTable = $('#editable').DataTable();

            /* Apply the jEditable handlers to the table */
            oTable.$('td').editable( '../example_ajax.php', {
                "callback": function( sValue, y ) {
                    var aPos = oTable.fnGetPosition( this );
                    oTable.fnUpdate( sValue, aPos[0], aPos[1] );
                },
                "submitdata": function ( value, settings ) {
                    return {
                        "row_id": this.parentNode.getAttribute('id'),
                        "column": oTable.fnGetPosition( this )[2]
                    };
                },

                "width": "90%",
                "height": "100%"
            } );


        });

        function fnClickAddRow() {
            $('#editable').dataTable().fnAddData( [
                "Custom row",
                "New row",
                "New row",
                "New row",
                "New row" ] );

        }
    </script>

</body>
</html>
