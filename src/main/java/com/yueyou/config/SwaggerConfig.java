package com.yueyou.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Profile("dev")
public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket creatApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select() //选择哪些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.yueyou.controller"))//controller路径
                .paths(PathSelectors.any())  //对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springmvc+swagger整合")//文档主标题
                .description("我卧推想突破大重量,却发现没有健身搭子一起,很容易受伤 , 所以,开发这么个软件来,帮助我们校园组队吃喝玩乐,学习,找工作等等,希望每个用户都可以找到志同道合的伙伴")//文档描述
                .version("1.0.0")//API的版本
                .termsOfServiceUrl("###")
                .license("LICENSE")
                .licenseUrl("###")
                .build();
    }
}


