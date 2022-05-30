package com.itheima.reggie.config;

/**
 * Copyright (c) 2018 王牌后台 All rights reserved.
 *
 * https://www.wangpai.io
 *
 * 版权所有，侵权必究！
 */


import com.google.common.base.Predicates;
import com.itheima.reggie.common.JacksonObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig extends WebMvcConfigurationSupport {
    /**
     * 创建 API 应用
     * apiInfo 增加 API 相关信息
     * 通过 select() 函数返回一个 ApiSelectorBuilder 实例，用来控制哪些接口暴露给 Swagger 来展现
     * 本例采用指定扫描的包路径来定义指定要建立 API 的目录
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 用来展示该 API 的基本信息
                .select()   // 返回一个 ApiSelectorBuilder 实例，用来控制哪些接口暴露给 Swagger 来展现
                .apis(RequestHandlerSelectors.basePackage("com.itheima.reggie"))// 配置包扫描路径（根据自己项目调整，通常配置为控制器路径）
                .paths(PathSelectors.any()) //
                .build();
    }

    /**
     * 创建 API 的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://xxx/swagger-ui.html
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("餐厅外卖系统")
                .description("餐厅外卖 APIs")
                .termsOfServiceUrl("http://localhost:8080/")
                .contact(new Contact("xiaozhi", "github/heng-star", "2895695886@qq.com"))
                .version("1.0")
                .build();
    }

    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    /**
     * 扩展mvc框架的消息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0,messageConverter);
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("doc.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        /** swagger配置 */
//        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
//        //super.addResourceHandlers(registry);
//    }

//    /**
//     * 创建API应用
//     * apiInfo() 增加API相关信息
//     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
//     * 指定扫描的包路径来定义指定要建立API的目录。
//     * @return
//     */
//    @Bean
//    public Docket coreApiConfig(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(adminApiInfo())
//                .groupName("adminApi")
//                .select()
//                //只显示admin下面的路径
//                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
//                .build();
//    }
//
//    private ApiInfo adminApiInfo(){
//        return new ApiInfoBuilder()
//                .title("尚融宝后台管理系统--api文档")
//                .description("尚融宝后台管理系统接口描述")
//                .version("1.0")
//                .contact(new Contact("李燕茹","http://baidu.com","728831102@qq.com"))
//                .build();
//    }
}

