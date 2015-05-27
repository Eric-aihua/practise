#This file is used to create a tomcat container
FROM leador/centos7:mysql
MAINTAINER eric.sunah@gmail.com

#RUN su -l
RUN groupadd -r mysql && useradd -r -g mysql mysql
RUN yum install -y mysql mysql-server
#ADD my.cnf /usr/my.cnf

RUN /bin/echo -e "LANG=\"en_US.UTF-8\"" > /etc/default/local

RUN mysql_install_db --user=root --ldata=/var/lib/mysql/
#RUN /usr/libexec/mysqld --user=root &
#RUN /usr/bin/mysqladmin -u root password ''
#RUN /usr/bin/mysqladmin -u root -h localhost password ''

EXPOSE 3306

CMD /usr/libexec/mysqld --user=root
