https://deepcompute-dev.nvidia.com
   - head node and reverse proxy
   - LDAP
   - influxDB (through websocket only)
   - Ports viewable to outside: 22, 443
   - Ports viewable only to encrypted websocket: 8086

https://csms0-dev.nvidia.com       
   - quay
   - Ports viewable to outside: 443

http://dgx1-bootstrap-repo.nvidia.com 
   - Ubuntu repository
   - Ports viewable to outside: 80 (IP restricted)

http://52.35.182.204               
   - app server via tomcat
   - Ports viewable to outside: none

Ports available within cloud on each machine:
   - 22 (ssh), 80 (http), 443 (https)

Ports available within the cloud on app server:
   - 3000 (grafana)

Ports available within the cloud on head node:
   - 8086 (influxdb)

As seen from above there are only two entry points into the cloud (soon to be one). 
Everything should be proxied through the head node.  So rather than a status push to 
http://ip.address/put it would be https://deepcompute-dev.nvidia.com/appserver/put. 
All of those endpoints need to be defined though I have started ones for grafana
and several other services.
