Ps：接口测试应用GAT接口自动化测试框架，简要介绍如下：

##框架适用范围服务端接口：

1基于Rest协议的接口（移动端调用的接口基本都是此类接口）
2Web Service类接口Web ：框架基于Web驱动程序封装

##使用要求
1支持的语言：Java
2环境要求：Eclipse / Idea，JDK 8.0 +，Maven，testng 

##框架特点
1提供多种接口测试方式。即单一接口测试，多接口业务流程测试。当前多见的为单一接口的测试。
2根据用户需求不同，不同的接口测试方式，用例开发难易度不同。
3用例开发门生物学低，用户只需通过接口用例数据填入格式化文件即可自动通过工具生成用例。
4对于高级需求，框架提供自定义配置包括数据结构，精确匹配测试结果等。
5框架对于不同域名下的相同接口支持自定义配置，只需要简单修改测试平台配置即可轻松将用例应用在不同平台上。
6框架对于不同协议接口的支持，近乎无缝连接。
7框架支持可配置。

##约定规则
1.目前暂只支持post，get方式的请求。
2.案例抛异常则自动重试
3.支持MyBatis数据库


##函数助手
> 测试用例excel表中可以使用‘__funcName(args)’占位符，在执行过程中如果判断含有该占位符，且funcName存在，则会执行相应的函数后进行替换。先支持函数如下：

- __random(param1,param2):随机生成一个定长的字符串(不含中文)。param1:长度(非必填，默认为6)，param2：纯数字标识(非必填，默认为false)。
- __randomText(param1): 随机生成一个定长的字符串(含中文)。param1:长度(非必填，默认为6)
- __randomStrArr(param1,param2,param3)：随机生成一个定长字符串数组。param1:数组长度(非必填，默认为1)，param2：单个字符串长度（非必填，默认6），param3：纯数字标识(非必填，默认为false)。
- __date(param1)： 生成执行该函数时的格式化字符串。param1为转换的格式，默认为‘yyyy-MM-dd’。
 
- __day(DatesPlus,yyyyMMdd,0,0,0)   获取加某个时间的时间，可自定义格式   (String formatEnum ,int day,int month,int year)

```
//若param中值为：
{"username":"__random(6，true)","date":"__day(DatesPlus,yyyy-MM-dd",2)"}

//实际执行时，username的值会替换为长度为6的数字随机数如：
{"username":"653495","date":"20191203"}
```

## 待优化

- 优化代码结构
- 支持excel
- 支持delete，put等方法
- 支持UI
- 待加+++++
