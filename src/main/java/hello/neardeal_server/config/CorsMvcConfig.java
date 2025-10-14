package hello.neardeal_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**")
                .allowedOrigins("https://near-deal.vercel.app", "http://localhost:3000")
                .exposedHeaders("Authorization"); // ← 새 액세스 토큰 읽으려면 필수
    }
}
