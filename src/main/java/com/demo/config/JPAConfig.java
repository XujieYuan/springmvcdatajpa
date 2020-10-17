package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;


//@EnableJpaRepositories: this tells Spring Data JPA to look for repository classes in the specified package
// in order to inject relevant code at runtime.
//@EnableTransactionManagement: this tells Spring Data JPA to generate code for transaction management at runtime.
//To enable Spring Data JPA, we need to create two beans: EntityManagerFactory and JpaTransactionManager.
//So create another configuration class named JpaConfig

@Configuration
@EnableJpaRepositories(basePackages = {"com.demo.model"})
@EnableTransactionManagement
public class JPAConfig {

    //the first method creates an instance of EntityManagerFactory to manage the persistence unit SalesDB
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("PokemonDB");

        return factoryBean;
    }

    //last method creates an instance of JpaTransactionManagerfor the EntityManagerFactory created by the first method.
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}