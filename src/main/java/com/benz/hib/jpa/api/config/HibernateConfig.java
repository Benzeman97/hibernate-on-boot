package com.benz.hib.jpa.api.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties(HibernateProperties.class)
public class HibernateConfig {

    private HibernateProperties hibernateProperties;

    public HibernateConfig(HibernateProperties hibernateProperties)
    {
        this.hibernateProperties=hibernateProperties;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(hibernateProperties.getDriver());
        dataSource.setUrl(hibernateProperties.getUrl());
        dataSource.setUsername(hibernateProperties.getUserName());
        dataSource.setPassword(hibernateProperties.getPassword());
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource)
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(hibernateProperties.getPackageToScan());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

  /*  @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(packageToScan);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(jpaProperties());
        return entityManagerFactory;
    }

    private Properties jpaProperties()
    {
        Properties properties=new Properties();
        properties.put("hibernate.dialect",dialect);
        properties.put("hibernate.show_sql",showSql);
        properties.put("hibernate.hbm2ddl.auto",ddlAuto);
        return properties;
    }*/

    private Properties hibernateProperties()
    {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect",this.hibernateProperties.getDialect());
        hibernateProperties.put("hibernate.show_sql",this.hibernateProperties.isShowSql());
        hibernateProperties.put("hibernate.hbm2ddl.auto",this.hibernateProperties.getDdlAuto());
        return hibernateProperties;
    }

   /* @Bean
    public JpaTransactionManager transactionManager()
    {
      JpaTransactionManager transactionManager =new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
      return transactionManager;
    }*/

    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }

}
