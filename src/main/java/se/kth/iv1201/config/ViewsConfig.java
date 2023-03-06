//package se.kth.iv1201.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring6.view.ThymeleafViewResolver;
//import org.thymeleaf.templatemode.TemplateMode;
//import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
//
//@Configuration
//public class ViewsConfig {
//
//    @Bean
//    public ThymeleafViewResolver viewResolver() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        return viewResolver;
//    }
//
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        // Layout dialect is needed for Thymeleaf fragmented views.
//        templateEngine.addDialect(new LayoutDialect());
//        return templateEngine;
//    }
//
//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//
//        templateResolver.setPrefix("classpath:/templates/");
//        templateResolver.setSuffix(".html");
//        return templateResolver;
//    }
//
//
//}
