package com.atguigu.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringIoCInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    //root容器配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MapperJavaConfig.class, ServiceJavaConfig.class,DataSourceJavaConfig.class};
    }
    //web容器配置
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcJavaConfig.class};
    }
    //dispatch拦截路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
