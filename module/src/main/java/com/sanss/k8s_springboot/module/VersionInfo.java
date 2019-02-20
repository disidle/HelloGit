package com.sanss.k8s_springboot.module;

public class VersionInfo extends BaseInfoModule{
    //# v1.0.1  2019-02-25  huangjinjing
    public String version; //v1.0.1
    public String releasedate; //2019-02-25
    public String author; //huangjinjing


    public String content; //_version.info的全部内容或第一个版本信息的内容

    @Override
    public String toString() {
        return "VersionInfo{" +
                "version='" + version + '\'' +
                ", releasedate='" + releasedate + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }



/*
# v1.0.1  2019-02-25  huangjinjing
1. 增加返回民族属性

# v1.0.0  2019-02-14  huangjinjing
1. 基础功能，初次发布

# v0.0.9  2019-02-14  huangjinjing
1. 初次构建
     */
}
