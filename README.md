# OutApi
web服务导出api文档
兼容swagger  优先显示swagger配置中文注释，如果没有配置，会通过有道翻译导出注释

现支持导出格式
1:默认对象
2：yapi 数据库的可导入json

导出教程

1下载项目打包，然后在需要导出api的服务引入jar包

2如果是gradle编译运行
gradle 配置
compileJava {
    options.compilerArgs << "-parameters"
}

如果是idea编译运行
idea配置
在运行 javac 配置  -parameters

3具体导出代码查看 out.api.test下案例




