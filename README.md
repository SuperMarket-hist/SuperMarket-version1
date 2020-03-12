# SuperMarket-version1
#### 使用Druid数据库连接池，MySQL数据库部署于服务器端，在servlet中使用方法如下
```
  //建立连接
  Connection getConn = JDBCTool.getConn();
  //进行数据库操作
  Statement sta = getConn.createStatement();
  //得到结果
  ResultSet rs = sta.executeQuery(sql);
```
#### 使用MD5加盐策略对用户密码进行保护，防止数据库泄露造成损失
```
  String fpass = MD5Demo.md5(MD5Demo.md5(STAFFID) + PASSWORD);
```
#### 前端页面使用bootstrap框架进行设计
