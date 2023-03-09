package se.kth.iv1201.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

/**
 * This class configures the Thymeleaf view resolver and template engine.
 * It is used to resolve the templates and render the views.
 * For example, it is needed to split the views into different fragments.
 */
@Configuration
public class ViewsConfig {

    /**
     * Creates a Thymeleaf view resolver.
     * @return an instance of ThymeleafViewResolver.
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    /**
     * Creates a Thymeleaf template engine.
     * @return  an instance of SpringTemplateEngine.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // Layout dialect is needed for Thymeleaf fragmented views.
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    /**
     * Creates a Thymeleaf template resolver.
     * @return an instance of SpringResourceTemplateResolver.
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

}
