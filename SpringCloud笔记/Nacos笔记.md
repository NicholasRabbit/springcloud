#### 1，Nacos启动准备

一，把conf/nacos-mysql.sql导入数据库，修改conf/applicaiton.properties中的数据库的配置

```properties
### Connect URL of DB:
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```

二，注意检查startup.cmd中的MODE是cluster还是standalone，一般没有集群则改为MODE=standalone

#### 2，Nacos的startup.sh无法在Linux中启动

原因: startup.sh是从windows复制过来的，文件格式是dos，需改为unix

vi  startup.sh : 打开文件

:set  fileformat : 查看文件格式，如果为dos则需修改

:set  fileformat=unix :  修改文件格式

:wq：保存退出

shudown.sh也是同样方法修改

#### 3，Nacos启动报错The server IP list of Nacos is []

原因：启动文件中设置了集群启动模式，而开发环境不具备集群

解决办法一：

./startup.sh  -m  standalone : 启动单体

解决办法二：

修改启动文件startup.sh，把mode=cluster改为 mode=standalone

**注意：Windows环境下则编辑startup.cmd文件中的MODE的值**

