基金服务thrift协议文件
注：thrift接口的返回值必须是结构体，并且必须有一个类型为TResponseStatusStruct名称为responseStatusStruct的属性。

作用：
     管理thrift协议文件，使用maven thrift 插件打包jar，给依赖者使用
使用方法如下：
    1、从官网下载thrift（http://www.apache.org/dyn/closer.cgi?path=/thrift/0.9.2/thrift-0.9.2.exe）
    2、修改pom.xml文件thriftExecutable节点值，这里可以是本地配置的环境变量或者指定thrift绝对路径,请根据自己环境选择配置
       比如：
                  环境变量中的命令：   <thriftExecutable>thrift</thriftExecutable>
          本地thrift软件的绝对路径：   <thriftExecutable>D:\devsoft\thrift.exe</thriftExecutable>
    3、自己本地开发：mvn clean compile install -Dmaven.test.skip
    4、  给别用使用：mvn clean compile deploy -Dmaven.test.skip
    5、使用者建立依赖，如： <dependency>
                                 <groupId>com.gdut.pptservice</groupId>
                                 <artifactId>shareppt</artifactId>
                                 <version>1.0-SNAPSHOT</version>
                             </dependency>
