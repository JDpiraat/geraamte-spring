package be.johan40.web;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import be.johan40.dao.CreateDAOBeans;
import be.johan40.datasource.CreateDataSourceBean;
import be.johan40.services.CreateServiceBeans;

public class Initializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { // TODO
		CreateDataSourceBean.class, CreateDAOBeans.class,
				CreateServiceBeans.class,
		// CreateRestClientBeans.class,
		// CreateMailBeans.class,
		// CreateCommonBeans.class,
		// CreateSecurityFilter.class,
		// CreateAOPBeans.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { CreateControllerBeans.class,
		/*
		 * TODO CreateRestControllerBeans.class
		 */};
	}

	@Override
	protected String[] getServletMappings() {		
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		context.setAttribute("contextPath", context.getContextPath());
		super.onStartup(context);
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter utf8Filter = new CharacterEncodingFilter();
		utf8Filter.setEncoding("UTF-8");
		return new Filter[] { utf8Filter, new OpenEntityManagerInViewFilter() }; 
		// OpenEntityManagerInViewFilter zorgt ervoor dat de EntityManager nog in de 
		// presentationlayer terbeschikking is en je dus bv het geasocieerde adres nog kan opvragen
		
		// onderstaande niet nodig als je met spring RegisterSecurityFilter
		// werkt en daar dit wordt ge-insert bij de before:
		// new HiddenHttpMethodFilter()
	}

}
