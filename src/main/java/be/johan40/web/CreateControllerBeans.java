package be.johan40.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = CreateControllerBeans.class)
@EnableSpringDataWebSupport // spring data & automatische String naar
// Entity converters, dus bij methods  niet meer @RequestMapping(value = "{id}" wel ... 
// "{EigenEntity}" (zeer handig voor clean urls) 
public class CreateControllerBeans extends WebMvcConfigurerAdapter {

	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/JSP/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	// request naar 'static resources' moeten niet naar controler beans
	// wel moeten ze de inhoud doorsturen naar de browser
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations(
				"/images/");
		registry.addResourceHandler("/styles/**").addResourceLocations(
				"/styles/");
		registry.addResourceHandler("/scripts/**").addResourceLocations(
				"/scripts/");
	}

	// deze request mogen rechtstreeks door naar jsp's (niet naar controler beans dus)
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/info").setViewName("info");
	}

	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// leest teksten*.properties
		messageSource.setBasename("classpath:teksten");
		// default true maar als spring dan niets vindt in teksten met de
		// taalcode van de gebruiker dan valt
		// het terug op het bestand met de taalcode van het besturingssysteem
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}

	@Bean
	LocalValidatorFactoryBean validatorFactory() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
		return factory;
	}

	@Override
	// importeer Validator uit org.springframework.validation
	public Validator getValidator() {
		return new SpringValidatorAdapter(validatorFactory().getValidator());
	}

}
