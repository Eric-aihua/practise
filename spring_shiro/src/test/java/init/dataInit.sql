DELETE FROM USERROLES;
DELETE FROM USER;
DELETE FROM role;
DELETE FROM rolerights;
DELETE FROM gcright;
/*name:admin   pwd:admin*/
INSERT INTO user (id, email, name, pwd) VALUES (1, 'admin@leador.com.cn', 'admin', '21232f297a57a5a743894a0e4a801fc3');

INSERT INTO role VALUES(1,'setting','setting');

INSERT INTO USERROLES VALUES(1,1);

INSERT INTO gcright(id,NAME,LABEL) VALUES(1,'dashboard','仪表盘管理');
INSERT INTO gcright(id,NAME,LABEL) VALUES(2,'server','服务器管理');
INSERT INTO gcright(id,NAME,LABEL) VALUES(3,'deployment','部署管理');
INSERT INTO gcright(id,NAME,LABEL) VALUES(4,'application','应用管理');
INSERT INTO gcright(id,NAME,LABEL) VALUES(5,'flow','流程管理');
INSERT INTO gcright(id,NAME,LABEL) VALUES(6,'alert','提醒管理');
INSERT INTO gcright(id,NAME,LABEL) VALUES(7,'setting','系统设置');

INSERT INTO rolerights VALUES(1,1);
INSERT INTO rolerights VALUES(1,2);
INSERT INTO rolerights VALUES(1,3);
INSERT INTO rolerights VALUES(1,4);
INSERT INTO rolerights VALUES(1,5);
INSERT INTO rolerights VALUES(1,6);
INSERT INTO rolerights VALUES(1,7);


