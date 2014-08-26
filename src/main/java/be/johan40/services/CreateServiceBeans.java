package be.johan40.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses = CreateServiceBeans.class)
@EnableTransactionManagement // for enabling @Transactional 
public class CreateServiceBeans {

}
