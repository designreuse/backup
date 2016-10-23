#!/bin/bash                                
rm -rf ~/.docker*
sudo docker login -u \$oauthtoken -p ${1} -e ${3} ${6}
echo $?
sudo tar cvfz dockercfg.tar.gz ~/.docker*
sudo mv dockercfg.tar.gz ${4}/${5}.tar.gz
sudo docker logout ${6}
sudo rm -rf ~/.docker*
exit 0
