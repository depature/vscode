package config;

import com.atguigu.interceptors.LoginProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMVCConfig implements WebMvcConfigurer {
    @Autowired
    private LoginProtectInterceptor loginProtectInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loginProtectInterceptor).addPathPatterns("headline/**");
    }
}
