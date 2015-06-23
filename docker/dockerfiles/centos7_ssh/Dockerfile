#This file is based on centos7 and build for ssh 
# Author Eric
# 
# VERSION               0.0.1
 
FROM        centos7_http 
MAINTAINER  eric eric.sunah@gmail.com
 
RUN         yum install -y openssh openssh-server openssh-clients net-tools
RUN         mkdir /var/run/sshd
#RUN          ssh-keygen -t rsa1 -f /etc/ssh/ssh_host_rsa_key
RUN         ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key
RUN         ssh-keygen -t dsa -f /etc/ssh/ssh_host_dsa_key
RUN         /bin/echo 'root:mypwd' |chpasswd
RUN         useradd eric
RUN         /bin/echo 'eric:mypwd' |chpasswd
RUN         /bin/sed -i 's/.*session.*required.*pam_loginuid.so.*/session optional pam_loginuid.so/g' /etc/pam.d/sshd
RUN         /bin/echo -e "LANG=\"en_US.UTF-8\"" > /etc/default/local
EXPOSE      22
EXPOSE      80
CMD         /usr/sbin/sshd -D
