package com.benz.hib.jpa.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class JpaWithHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaWithHibernateApplication.class, args);
    }


}
