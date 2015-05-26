#This file is used to create a tomcat container
FROM leador/centos7:ssh
MAINTAINER eric

RUN mkdir /root/tools
RUN yum install -y tar
ADD apache-tomcat-7.0.34 /root/tools/tomcat
ADD jdk1.7.0_55 /root/tools/jdk
ADD run.sh /run.sh
RUN chmod u+x /*.sh

RUN cd /root/tools
#RUN tar -xvf /root/tools/apache-tomcat-7.0.34.tar.gz
#RUN tar -xvf /root/tools/jdk-7u55-linux-x64.tar.gz

ENV JAVA_HOME /root/tools/jdk
ENV CATALINE_HOME /root/tools/tomcat
RUN /bin/echo -e "LANG=\"en_US.UTF-8\"" > /etc/default/local

EXPOSE 8080
CMD ${CATALINE_HOME}/bin/catalina.sh run
