package com.team5.projrental.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final String resourcePath;

    public WebMvcConfiguration(@Value("${file.base-package}") String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /* TODO: 1/17/24
            자동으로 패키지 아래에서 필요한 파일을 찾는것인지,
            아니면 프론트가 /category/pk/fileName.xxx 로 요청 해야 하는 것인지 알아보기.
            --by Hyunmin */

        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:" + resourcePath);
    }
}
