package io.demo.purchase.support.config;

import io.demo.purchase.support.argumentresolver.LonginArgumentResolver;
import io.demo.purchase.support.interceptor.AdminCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    private final AdminCheckInterceptor adminCheckInterceptor;
    private final LonginArgumentResolver longinArgumentResolver;

    @Autowired
    public GlobalConfig(
            AdminCheckInterceptor adminCheckInterceptor,
            LonginArgumentResolver longinArgumentResolver
    ) {
        this.adminCheckInterceptor = adminCheckInterceptor;
        this.longinArgumentResolver = longinArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminCheckInterceptor)
                .addPathPatterns("/admin/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(longinArgumentResolver);
    }
}
