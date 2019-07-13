package com.guli.edu.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){//面向前端网页的接口

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")//指定名字
                .apiInfo(webApiInfo())//调用下方webApiInfo方法
                .select() //过滤
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))//包含admin路径的全部过滤：not
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//包含以error结束的全部过滤：not
                .build();//生效

    }

    @Bean
    public Docket adminApiConfig(){//后台实现的接口

        return new Docket(DocumentationType.SWAGGER_2)//生成swagger文档
                .groupName("adminApi")//指定名字
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))//包含admin路径下的所有接口：and
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()//配置web端基本文本信息
                .title("网站-课程中心API文档")//配文档名字
                .description("本文档描述了课程中心微服务接口定义")//配详细描述
                .version("1.0")//配文档版本
                .contact(new Contact("TCH", "http://baidu.com", "379103164@qq.com"))//文档联系人
                .build();
    }

    private ApiInfo adminApiInfo(){

        return new ApiInfoBuilder()//配置admin端基本文本信息
                .title("后台管理系统-课程中心API文档")//配文档名字
                .description("本文档描述了后台管理系统课程中心微服务接口定义")//配详细描述
                .version("1.0")
                .contact(new Contact("TCH", "http://baidu.com", "379103164@qq.com"))
                .build();
    }
}

