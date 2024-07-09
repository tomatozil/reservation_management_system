package io.demo.purchase.support.config;

import io.demo.purchase.support.argumentresolver.LonginArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ArgumentResolverConfig implements WebMvcConfigurer {

    private final LonginArgumentResolver longinArgumentResolver;

    @Autowired
    public ArgumentResolverConfig(LonginArgumentResolver longinArgumentResolver) {
        this.longinArgumentResolver = longinArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(longinArgumentResolver);
    }
}
