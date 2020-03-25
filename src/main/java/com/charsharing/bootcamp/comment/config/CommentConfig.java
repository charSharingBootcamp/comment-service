package com.charsharing.bootcamp.comment.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CommentConfig implements WebMvcConfigurer{

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/static/", "classpath/public/")
                    .setCachePeriod(0);
        }


}
