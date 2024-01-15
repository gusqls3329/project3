package com.team5.projrental;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//MVC가 통신할때 UTF-8 설정으로 한글이 깨지지 않도록
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AutoConfigureMockMvc
@Import({MockMvcConfig.MockMvc.class})
public @interface MockMvcConfig {
    class MockMvc { // inner class
        @Bean
        MockMvcBuilderCustomizer utf8Config() {
            return (builder) -> builder.addFilter(new CharacterEncodingFilter("UTF-8", true));
        }
    }
}
