package pl.macias.dip;

import com.lyncode.jtwig.mvc.JtwigViewResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

/**
 * Created by macias on 24.02.15.
 */

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {

    public static void main (String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ViewResolver viewResolver () {
        JtwigViewResolver jtwigViewResolver = new JtwigViewResolver();
        jtwigViewResolver.setPrefix("/WEB-INF/views/");
        jtwigViewResolver.setSuffix(".twig");
        jtwigViewResolver.setUseThemeInViewPath(true);
        return jtwigViewResolver;
    }

    @Bean
    public String error () {
        return "error";
    }

}
