package com.yjs;

import com.yjs.listener.ApplicationStartedEventListener;
import com.yjs.listener.ApplicationStartingEventListener;
import com.yjs.listener.ClosedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class TijiangjApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(TijiangjApplication.class);
        springApplication.addListeners(new ApplicationStartingEventListener());
        springApplication.addListeners(new ApplicationStartedEventListener());
        springApplication.addListeners(new ClosedEventListener());
        springApplication.setHeadless(false);
        springApplication.run(args);
    }

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);    // 允许cookies跨域
        config.addAllowedOrigin("*");        // 允许向该服务器提交请求的URI
        config.addAllowedHeader("*");        // 允许访问的头信息
        config.addAllowedMethod("*");        // 允许所有请求方法
        config.setMaxAge(18000L);            // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
