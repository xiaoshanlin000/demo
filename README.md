# demo
学习并使用netty 5.0 + spring boot data jpa库<br/>
server 目录是服务端<br/> 
数据库使用mysql 并 创建了sldb数据库,并执行server/sql/t_user.sql<br/>
运行方式:<br/>
cd ./server <br/>
mvn package <br/>
cd target<br/>
chmod a+x *.sh<br/>
./start.sh<br/>
在浏览器地址中输入:<br/>
http://localhost:21121/shanlin/demo/register.action?req={"head":{"username":"test"},"password":"123456"}
