# SuperMarket-version1
## 细节设计
1. 设计密码MD5加密，禁止数据库直接存放明文密码，初步设计为【fpassword = MD5(MD5(username) + password)】
2. 设计Druid数据库连接池，为后期增加功能做准备
3. 设计BaseDAO进行开发代码简略，减少重复造轮子的现象
