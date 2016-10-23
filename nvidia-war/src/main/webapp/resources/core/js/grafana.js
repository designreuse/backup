
var NVIDIA_GRAFANA_HOST = $('#NVIDIA_GRAFANA_HOST').val();


var cluster_cur = {
		one :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=1&fullscreen&theme=light",
		two : NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=2&fullscreen&theme=light",
		three : NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=3&fullscreen&theme=light",
		four :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=4&fullscreen&theme=light"
};


var cluster_pre = {
		one : NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=2&fullscreen&theme=light",
		two : NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=1&fullscreen&theme=light",
		three :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=3&fullscreen&theme=light",
		four : NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=4&fullscreen&theme=light"
};

var node1_cur = {
		one :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=5&fullscreen&theme=light",
		two : NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=6&fullscreen&theme=light",
		three :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=8&fullscreen&theme=light",
		four : NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=9&fullscreen&theme=light"
};

var node1_pre = {
		one :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=6&fullscreen&theme=light",
		two :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=5&fullscreen&theme=light",
		three :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=8&fullscreen&theme=light",
		four :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=9&fullscreen&theme=light"
};


var node2_cur = {
		one :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=7&fullscreen&theme=light",
		two :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=7&fullscreen&theme=light",
		three :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=10&fullscreen&theme=light",
		four :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-current?panelId=11&fullscreen&theme=light"
};

var node2_pre = {
		one :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=7&fullscreen&theme=light",
		two :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=7&fullscreen&theme=light",
		three :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=10&fullscreen&theme=light",
		four :NVIDIA_GRAFANA_HOST+"/dashboard-solo/db/customer-1-last-7-days?panelId=11&fullscreen&theme=light"
};
